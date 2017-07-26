package com.ensat.services;


import com.ensat.entities.Product;
import com.ensat.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Iterable<Product> listAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Integer id) {
        return productRepository.findOne(id);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Integer id) {
        productRepository.delete(id);
    }

	@Override
	public int getProductIdByName(String productName) {	
		return productRepository.findProductIdByName(productName);
	}

	@Override
	public void updateProduct(Product product ,Integer id) {
		Product uProduct= productRepository.findOne(id);
		uProduct.setDetails(product.getDetails());
		uProduct.setPrice(product.getPrice());
		uProduct.setProductName(product.getProductName());
		uProduct.setQuantity(product.getQuantity());
		productRepository.save(uProduct);
	}

}
