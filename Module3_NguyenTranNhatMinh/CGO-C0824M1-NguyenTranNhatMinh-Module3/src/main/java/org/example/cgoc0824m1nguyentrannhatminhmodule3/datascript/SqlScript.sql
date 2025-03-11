-- Tạo cơ sở dữ liệu
CREATE DATABASE ProductDB;
USE ProductDB;

-- Tạo bảng Category
CREATE TABLE categories (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Tạo bảng Product
CREATE TABLE products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL,
    quantity INT NOT NULL,
    color VARCHAR(50),
    description TEXT,
    category_id INT,
    FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE SET NULL
);
-- Chèn dữ liệu mẫu vào bảng categories
INSERT INTO categories (name) VALUES 
('Electronics'), 
('Clothing'), 
('Home Appliances'), 
('Books');

-- Chèn dữ liệu mẫu vào bảng products
INSERT INTO products (name, price, quantity, color, description, category_id) VALUES 
('Laptop Dell XPS 13', 400000000, 10, 'Trắng', 'Laptop Dell cao cấp với màn hình 13 inch', 1),
('iPhone 14 Pro', 300000000, 15, 'Trắng', 'Điện thoại thông minh của Apple với camera 48MP', 1),
('T-shirt Nike', 200000000, 50, 'Trắng', 'Áo thun thể thao chất lượng cao', 2),
('Refrigerator LG Smart',100000000, 5, 'Trắng', 'Tủ lạnh thông minh với công nghệ tiết kiệm điện', 3);


select * from products;