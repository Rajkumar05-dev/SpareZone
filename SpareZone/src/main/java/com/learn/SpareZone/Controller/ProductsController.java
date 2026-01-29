package com.learn.SpareZone.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.learn.SpareZone.Dtos.ProductsDto;
import com.learn.SpareZone.Entities.Products;
import com.learn.SpareZone.Repositories.ProductRepository;
import com.learn.SpareZone.Service.FileService;

import jakarta.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/products")
public class ProductsController {
	
	@Value("${product.image.path}")
	private String imagePath;
	
	@Autowired
	private FileService fileService;
	@Autowired
	private ProductRepository productRepository;

	@GetMapping("/serach/{name}")
	public ResponseEntity<List<ProductsDto>> serachProduct(@PathVariable String name) {
		return new ResponseEntity<List<ProductsDto>>(HttpStatus.FOUND);
	}

	@PutMapping("/{pid}/category/{cid}")
	public ResponseEntity<ProductsDto> assignCategory(@PathVariable Long pid, @PathVariable Long cid) {
		return new ResponseEntity<ProductsDto>(HttpStatus.OK);
	}
	@PostMapping("/{productId}/image")
	public ResponseEntity<String> uploadImage(
	        @PathVariable Long productId,
	        @RequestParam("productImage") MultipartFile productImage) throws IOException {

	    if (productImage.isEmpty()) {
	        return new ResponseEntity<>("Image is empty", HttpStatus.BAD_REQUEST);
	    }

	    Products product = productRepository.findById(productId)
	            .orElseThrow(() -> new RuntimeException("Product not found"));

	    String imageName = fileService.uploadfile(productImage, imagePath);
	    product.setProductsImage(imageName);
	    productRepository.save(product);

	    return new ResponseEntity<>("Image uploaded successfully", HttpStatus.OK);
	}

	 
	@GetMapping("/{productId}/image")
	public void serveProductImage(
	        @PathVariable Long productId,
	        HttpServletResponse response) throws IOException {

	    Products product = productRepository.findById(productId)
	            .orElseThrow(() -> new RuntimeException("Product not found"));

	    InputStream resource = fileService.getResource(imagePath, product.getProductsImage());

	    String contentType = Files.probeContentType(
	            Paths.get(imagePath, product.getProductsImage()));

	    response.setContentType(contentType);
	    StreamUtils.copy(resource, response.getOutputStream());
	}

	 
}
