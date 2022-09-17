package com.diego.supermercado.web.controller;

import com.diego.supermercado.domain.dto.Product;
import com.diego.supermercado.domain.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    @ApiOperation("Get all market products")
    public ResponseEntity<List<Product>> getAll() {
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ApiOperation("Search a product with an ID")
    @ApiResponses({
            @ApiResponse(code=200 , message="OK"),
            @ApiResponse(code=404, message="Product not found")
    })
    public ResponseEntity<Product> getProduct(@PathVariable("id") int productId) {
        return productService.getProduct(productId)
                .map(product -> new ResponseEntity<Product>(product, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/category/{id}")
    @ApiOperation("Obtains a list of products that belong to a specific category ID")
    public ResponseEntity<List<Product>> getProductByCategory(@PathVariable("id") int categoryId) {
       // return productService.getByCategory(categoryId)
       //         .map(products -> new ResponseEntity<>(products, HttpStatus.OK))
       //         .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

        Optional<List<Product>> products = productService.getByCategory(categoryId);

        if (products.isPresent() && !products.get().isEmpty())
            return new ResponseEntity<>(products.get(), HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("/save")
    @ApiOperation("Save a new product in the database")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Deletes the product of the ID specified")
    public ResponseEntity deleteProduct(@PathVariable("id") int productId) {
        if (productService.delete(productId)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @ApiOperation("Updates the product of the ID specified")
    public ResponseEntity updateProduct(@PathVariable("id") int productId, @RequestBody Product product) {
        if (productService.update(productId, product)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }


}
