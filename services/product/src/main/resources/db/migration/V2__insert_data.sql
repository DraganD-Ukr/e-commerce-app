INSERT INTO category (name, description)
VALUES
    ('Electronics', 'Latest electronics and gadgets'),
    ('Accessories', 'Mobile and computer accessories'),
    ('Home Appliances', 'Household electronics and appliances'),
    ('Gaming', 'Gaming consoles and accessories');


INSERT INTO product (name, description, available_quantity, price, category_id)
VALUES
    ('Smartphone X', 'Latest smartphone with 128GB storage', 100, 799.99, 1),
    ('Laptop Pro', 'High-performance laptop with 16GB RAM', 50, 1299.99, 1),
    ('Bluetooth Speaker', 'Portable Bluetooth speaker with deep bass', 150, 59.99, 2),
    ('Wireless Mouse', 'Ergonomic wireless mouse with long battery life', 200, 29.99, 2),
    ('Air Conditioner', 'Energy-efficient 1.5-ton air conditioner', 30, 499.99, 3),
    ('Smart TV', '55-inch 4K Ultra HD Smart TV', 40, 699.99, 3),
    ('Gaming Console X', 'Next-gen gaming console with 1TB storage', 80, 499.99, 4),
    ('Gaming Headset', 'Noise-cancelling gaming headset with mic', 120, 79.99, 4);


