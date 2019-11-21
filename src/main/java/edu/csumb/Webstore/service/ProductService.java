//The service file is where the heavy lifting is done.
//You will do all logic, all database access(Special database operations defined in the repository).
//Basically all your actual code is here!
package edu.csumb.Webstore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import edu.csumb.Webstore.model.Product;
import edu.csumb.Webstore.repositories.ProductRepository;

//Remember to annotate what type of class this is!
@Service
public class ProductService implements CommandLineRunner{
    // We need to autowire the database here. If you are stuck, look at
    // ProductController!
    @Autowired
    ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        //Title, pic, price, stock, description
        productRepository.insert(new Product("Dozen roses", "rose.jpg", 25.99, 10,
                "Roses are one color per bunch. (Red, White, Blue)"));
        productRepository.insert(new Product("Purple Orchids", "orchids.jpg", 30.99, 15,
                "Large single Orchids come in a brown vase. High quality good for gifting."));
        productRepository.insert(new Product("Sunflowers per dozen", "sunflower.jpg", 15.99, 8,
                "They come in orange and yellow color. Good for center pieces."));
    }

    public Iterable<String> example() {
        List<String> sList = new ArrayList<>();
        sList.add("Test");
        return sList;
    }

    public Optional<Product> getProduct(String id) {
        return productRepository.findById(id);
    }

    public Iterable<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public void addProduct(Product product){
        productRepository.insert(new Product(product.getName(), product.getImageURL(), product.getPrice(), product.getStock(), product.getDescription()));
    }

    public void changeQuantity(String id, Integer amount){
        Product product = productRepository.findById(id).get();
        product.setStock(product.getStock() - amount);
        productRepository.deleteById(id);
        productRepository.insert(product);
    }

    

}