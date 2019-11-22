//The service file is where the heavy lifting is done.
//You will do all logic, all database access(Special database operations defined in the repository).
//Basically all your actual code is here!
package edu.csumb.Webstore.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.csumb.Webstore.model.User;
import edu.csumb.Webstore.repositories.UserRepository;

//Remember to annotate what type of class this is!
@Service
public class UserService{
    // We need to autowire the database here. If you are stuck, look at
    // ProductController!
    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductService productService;

    /*@Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();

        userRepository.insert(new User("Jessica831", "42sfad_1"));
        userRepository.insert(new User("Jessiieeeee", "4aafaf"));
        userRepository.insert(new User("Riiiioosss", "adfsadfad"));
    }
*/
    public String checkAuth(String username, String password) {
        Iterable<User> users = userRepository.findAll();

        for(User user : users){
            if(user.getUsername().equals(username) && user.getPassword().equals(password))
                return "valid";
        }

        return "invalid";
    }

    public void addUser(User user)
    {
        userRepository.insert(user);
    }

    public void addToCard(String username, String product, Integer amount) {
       
        User user = userRepository.findById(username).get();
        HashMap<String, Integer> card = user.getCard();

        if(card.containsKey(product))
            card.put(product, card.get(product)+amount);
        else
            card.put(product, amount);
        // user.set
        user.setCard(card);
        userRepository.deleteById(username);
        userRepository.insert(user);
    }

    public void changeQuantity(String username, String product, Integer amount){

        User user = userRepository.findById(username).get();
        HashMap<String, Integer> card = user.getCard();

        if( card.get(product) + amount <= 0)
            card.remove(product);
        else
            card.put(product, card.get(product) + amount);

            userRepository.deleteById(username);
            userRepository.insert(user);
    }

    public void checkout(String username){

        User user = userRepository.findById(username).get();
        HashMap<String, Integer> card = user.getCard();


        for (Map.Entry mapElement : card.entrySet()) { 
            String product = (String)mapElement.getKey(); 
            int quantity = ((Integer)mapElement.getValue()); 

            productService.changeQuantity(product, quantity);
        } 
        
        userRepository.deleteById(username);
        user.setCard(new HashMap<String, Integer>());
        userRepository.insert(user);

    }

}