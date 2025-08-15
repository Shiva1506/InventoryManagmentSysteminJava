package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.entity.Sale;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.SaleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SaleService {
    @Autowired
    private SaleRepository saleRepo;

    @Autowired
    private ProductRepository productRepo;

    public Sale processSale(Long productId, int quantity) {
        Product product = productRepo.findById(productId).orElseThrow();
        if (product.getQuantity() < quantity) {
            throw new RuntimeException("Not enough stock");
        }
        product.setQuantity(product.getQuantity() - quantity);
        productRepo.save(product);

        Sale sale = new Sale();
        sale.setProduct(product);
        sale.setQuantity(quantity);
        sale.setTotalPrice(product.getPrice() * quantity);
        sale.setSaleDate(LocalDateTime.now());

        return saleRepo.save(sale);
    }

    public List<Sale> getAllSales() {
        return saleRepo.findAll();
    }
}