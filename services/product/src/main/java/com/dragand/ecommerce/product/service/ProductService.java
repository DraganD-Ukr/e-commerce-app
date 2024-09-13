package com.dragand.ecommerce.product.service;

import com.dragand.ecommerce.product.dto.*;
import com.dragand.ecommerce.product.exception.ProductPurchaseException;
import com.dragand.ecommerce.product.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;


    public Integer createProduct(ProductRequest request) {
        var product = productMapper.toProduct(request);
        return productRepository.save(product).getId();
    }


    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {

//         Extract product IDs
        var productIds = request
                .stream()
                .map(ProductPurchaseRequest::productId)
                .toList();

//        Fetch Stored Products from the Database
        var storedProducts = productRepository.findAllByIdInOrderById(productIds);

//        Check if all products exist
        if (productIds.size() != storedProducts.size()) {
            throw new ProductPurchaseException("One or more products does not exists");
        }

//        Sort the Requests by Product ID
        var sortedRequest = request
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();

//        Initialize a List for Purchase Responses
        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();

//        Process each Product Purchase
        for (int i = 0; i < storedProducts.size(); i++) {

            var product = storedProducts.get(i);
            var productRequest = sortedRequest.get(i);
//            Check Available Quantity
            if (product.getAvailableQuantity() <= productRequest.quantity()){
                throw new ProductPurchaseException("Insufficient stock quantity for product with id " + product.getId());
            }
//            Update Product Quantity
            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
//            Save the Updated Product
            productRepository.save(product);
//            Add Purchase Response to List
            purchasedProducts.add(productMapper.toProductPurchaseResponse(product, productRequest.quantity()));

        }
        return purchasedProducts;
    }


    public ProductResponse findById(Integer productId) {
        return productRepository.findById(productId)
                .map(productMapper::toProductResponse)
                .orElseThrow( () -> new EntityNotFoundException("Product with id " + productId + " not found") );
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }
}
