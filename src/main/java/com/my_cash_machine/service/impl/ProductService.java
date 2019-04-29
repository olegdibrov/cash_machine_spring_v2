package com.my_cash_machine.service.impl;

import com.my_cash_machine.domen.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.my_cash_machine.domen.Product;
import com.my_cash_machine.repository.ProductRepository;

import java.util.Collection;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductService() {
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Boolean delete(int id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Product update(Product product) {
        return productRepository.save(product);
    }

    public Product findById(int id) {
        return productRepository.findById(id).get();
    }

    public Collection<Product> findAll() {
        Iterable<Product> itr = productRepository.findAll();
        return (Collection<Product>) itr;
    }
}
