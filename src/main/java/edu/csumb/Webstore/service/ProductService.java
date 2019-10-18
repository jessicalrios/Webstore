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
        productRepository.insert(new Product("APTAIN OBVIOUS MEN'S COSTUME", "captain.jpg", 49.99, 5,
                "Material: 100% Polyester, Exclusive of Decorations"));
        productRepository.insert(new Product("ADULT TROPICAL TOURIST COSTUME", "tropical.jpg", 34.99, 10,
                "Straw hat, jumbo sunglasses, lei, sandals, and other accessories sold separately"));
        productRepository.insert(new Product("DISNEY TOY STORY REX INFLATABLE COSTUME FOR ADULTS", "toy.jpg", 89.99, 1,
                "Green transparent vision port in character mouth"));
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

}