package com.product.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.product.DataPublisher;
import com.product.model.Product;
import com.product.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
  
    @Autowired
    private DataPublisher dp;

    @GetMapping("/getAll")
    public ResponseEntity< List < Product >> getAllProduct() {
        return ResponseEntity.ok().body(productService.getAllProduct());
    }

	  @PostMapping("/add") public ResponseEntity < Product >
	  createProduct(@RequestBody Product product, @RequestParam("msg") String msg) {
		  dp.setTempObj(msg);
		  return
	  ResponseEntity.ok().body(this.productService.createProduct(product));
		  
		  }
    @PutMapping("/update/{id}")
    public ResponseEntity < Product > updateProduct(@PathVariable long id, @RequestBody Product product) {
    	  dp.setTempObj("Updated Producted:"+id);
    	product.setId(id);
      
        return ResponseEntity.ok().body(this.productService.updateProduct(product));
    }
    

    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteProduct(@PathVariable long id) {
    	 dp.setTempObj("Deleted Producted"+id);
        this.productService.deleteProduct(id);
        return HttpStatus.OK;
    }


}
