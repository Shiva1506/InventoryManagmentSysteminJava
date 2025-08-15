package com.example.demo.controller;

import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.SaleRepository;
import com.example.demo.entity.Product;
import com.example.demo.entity.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private SaleRepository saleRepo;

    @GetMapping
    public Map<String, Object> getStats() {
        List<Product> products = productRepo.findAll();
        List<Sale> sales = saleRepo.findAll();

        int totalProducts = products.size();
        int totalQuantity = products.stream().mapToInt(Product::getQuantity).sum();
        double totalSales = sales.stream().mapToDouble(Sale::getTotalPrice).sum();

        Map<String, Object> stats = new HashMap<>();
        stats.put("totalProducts", totalProducts);
        stats.put("totalQuantity", totalQuantity);
        stats.put("totalSales", totalSales);

        return stats;
    }
}