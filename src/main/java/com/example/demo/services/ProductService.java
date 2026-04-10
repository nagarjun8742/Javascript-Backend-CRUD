package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    public List<Product> getAll() {
        return repo.findAll();
    }

    public Optional<Product> getById(Long id) {
        return repo.findById(id);
    }

    public Product create(Product product) {
        return repo.save(product);
    }

    public Product update(Long id, Product updated) {
        return repo.findById(id).map(p -> {
            p.setName(updated.getName());
            p.setDescription(updated.getDescription());
            p.setPrice(updated.getPrice());
            p.setQuantity(updated.getQuantity());
            return repo.save(p);
        }).orElseThrow(() -> new RuntimeException("Product not found: " + id));
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
