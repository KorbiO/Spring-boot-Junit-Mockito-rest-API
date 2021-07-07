package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exeception.ProductAlreadyExistsException;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.sun.el.stream.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository){

        this.productRepository =productRepository;
    }

    @Override
    public Product addProduct(Product product) throws ProductAlreadyExistsException {
        if(productRepository.existsById(product.getId())){
            throw new ProductAlreadyExistsException();
        }
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {

        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Product getProductByid(int id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product deleteProductById(int id) {
        Product product = null;
        java.util.Optional<Product> optional = productRepository.findById(id);
        if (optional.isPresent()) {
            product = productRepository.findById(id).get();
            productRepository.deleteById(id);
        }
        return product;
    }

}