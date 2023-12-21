package com.petcare.rest.webservices.restful.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petcare.rest.webservices.restful.model.Product;
import com.petcare.rest.webservices.restful.service.ProductService;


@RestController
@RequestMapping("is/v1")
public class ProductController {
    ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/products")
    public List<Product> getAllProduct(){
        return service.getAllProduct();
    }

    @GetMapping("/product/{id}")
    public Optional<Product> getProductById(@PathVariable int id){
        return service.getProductById(id);
    }

    @GetMapping("products/{type}")
    public List<Product> getProductByType(@PathVariable String type) {
        return service.getProductByType(type);
    }

    @GetMapping("products/inStock")
    public List<Product> getProductInStock(){
        return service.getProductInStock();
    }

    @GetMapping("products/outStock")
    public List<Product> getProductOutStock(){
        return service.getProductOutStock();
    }

    @GetMapping("/products/constraints/{constraint}")
    public ResponseEntity<List<Product>> getProductsByConstraint(@PathVariable String constraint) {
        List<Product> products = service.getProductByConstraint(constraint);

        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.ok(products);
    }

}
