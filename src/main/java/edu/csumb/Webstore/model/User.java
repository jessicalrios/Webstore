package edu.csumb.Webstore.model;

import java.util.HashMap;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModelProperty;


@Document(collection = "User")

public class User
{
    // @ApiModelProperty(required = false, hidden = true)
    @Id
    private String username;
    private String password;
    
    @ApiModelProperty(required = false, hidden = true)
    private HashMap<String, Integer> card = new HashMap<>();

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    /* public User( String username, String password, HashMap<String, Integer> card){
         this.username = username;
         this.password = password;
         this.card = card;
     }*/
    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername(){
        return username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

    public HashMap<String, Integer> getCard(){
        return card;
    }
    public void setCard(HashMap<String,Integer> card){
        this.card = card;
    }
    

    public void appendHashMap(Integer id, String quantity, HashMap<String, Integer> map){
        //for( Integer m : map.values()){
        if(map.containsValue(id)){
            map.put(quantity, id);
            return;
        }
        //}
        return;
    }
}