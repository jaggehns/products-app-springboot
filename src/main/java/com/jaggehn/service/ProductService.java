package com.jaggehn.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jaggehn.entity.Product;
import com.jaggehn.repository.ProductRepository;

import org.modelmapper.ModelMapper;

import com.jaggehn.dto.ProductDTO;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;

	@Autowired
	private ModelMapper modelMapper;

	public ProductDTO saveProduct(ProductDTO productDTO) {
		Product product = modelMapper.map(productDTO, Product.class);
		Product savedProduct = repository.save(product);
		return modelMapper.map(savedProduct, ProductDTO.class);
	}

	public List<ProductDTO> getProducts() {
		List<Product> products = repository.findAll();
		return products.stream()
				.map(product -> modelMapper.map(product, ProductDTO.class))
				.collect(Collectors.toList());
	}

	public ProductDTO getProductById(int id) {
		Product product = repository.findById(id).orElse(null);
		return modelMapper.map(product, ProductDTO.class);
	}

	public String deleteByProduct(int id) {
		repository.deleteById(id);
		return "Product Deleted !! " + id;
	}

	public ProductDTO updateProduct(ProductDTO productDTO) {
		Product existingProduct = repository.findById(productDTO.getId()).orElse(null);
		if (existingProduct != null) {
			modelMapper.map(productDTO, existingProduct);
			Product updatedProduct = repository.save(existingProduct);
			return modelMapper.map(updatedProduct, ProductDTO.class);
		}
		return null;
	}
	
	public Page<ProductDTO> getProductsPaginated(Pageable pageable) {
	    Page<Product> productPage = repository.findAll(pageable);
	    return productPage.map(product -> modelMapper.map(product, ProductDTO.class));
	}
	
	public Page<ProductDTO> searchProducts(String query, Pageable pageable) {
	    Page<Product> products;

	    try {
	        int id = Integer.parseInt(query);
	        Product product = repository.findById(id).orElse(null);

	        if (product != null) {
	            return new PageImpl<>(Arrays.asList(modelMapper.map(product, ProductDTO.class)), pageable, 1);
	        } else {
	            return Page.empty();
	        }
	    } catch (NumberFormatException e) {
	        products = repository.findByNameContainingIgnoreCase(query, pageable);
	    }

	    return products.map(product -> modelMapper.map(product, ProductDTO.class));
	}
	
}