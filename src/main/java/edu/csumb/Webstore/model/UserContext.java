package edu.csumb.Webstore.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModelProperty;

public class UserContext {

    public String username;
    public String password;
}