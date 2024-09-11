package com.dragand.ecommerce.dto;

import com.dragand.ecommerce.model.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {

    /**
     * Builds a Customer object from the corresponding dto class - CustomerRequest
     * @param request
     * @return
     */
    public Customer toCustomer(CustomerRequest request) {

        if (request == null) {
            return null;
        }

        return Customer.builder()
                .id(request.id())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .address(request.address())
                .email(request.email())
                .build();

    }

    public CustomerResponse fromCustomer(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getAddress()
        );
    }
}
