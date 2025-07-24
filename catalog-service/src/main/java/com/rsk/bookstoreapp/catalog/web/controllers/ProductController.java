package com.rsk.bookstoreapp.catalog.web.controllers;

import com.rsk.bookstoreapp.catalog.domain.PagedResult;
import com.rsk.bookstoreapp.catalog.domain.Product;
import com.rsk.bookstoreapp.catalog.domain.ProductNotFoundException;
import com.rsk.bookstoreapp.catalog.domain.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/products")
class ProductController {
    private final ProductService productService;

    ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    PagedResult<Product> getProducts(@RequestParam(name="page", defaultValue = "1") int pageNo){
        return productService.getProducts(pageNo);
    }

    @GetMapping("/{code}")
    ResponseEntity<Product> getProductByCode(@PathVariable(name = "code") String code){
        return productService.getProductByCode(code)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> ProductNotFoundException.forCode(code));
    }
}
