package com.example.demo.controller;

import com.example.demo.entity.Sale;
import com.example.demo.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SaleController {
    @Autowired
    private SaleService saleService;

    @PostMapping("/{productId}/{quantity}")
    public Sale processSale(@PathVariable Long productId, @PathVariable int quantity) {
        return saleService.processSale(productId, quantity);
    }

    @GetMapping
    public List<Sale> allSales() {
        return saleService.getAllSales();
    }
}