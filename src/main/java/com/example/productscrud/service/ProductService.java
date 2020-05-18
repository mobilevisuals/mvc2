package com.example.productscrud.service;

import com.example.productscrud.domain.Product;
import com.example.productscrud.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public void save(Product product)
    {
        productRepository.save(product);
    }

    public List<Product> listAll()
    {
       return productRepository.findAll();
    }

    public Optional<Product> findProduct(long id)
    {
        return productRepository.findById(id);
    }

    @Transactional
    public void delete(long id)
    {
        productRepository.deleteById(id);
    }
}
