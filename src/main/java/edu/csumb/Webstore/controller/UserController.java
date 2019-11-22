//The controller file determines where someone can access your service.
//The controller shouldn't be doing any logic, and you should NEVER import the database into controller.
//Use the service for any logic, or even one line functions. 99 % of the time, the controller should only have
//the return statement, and nothing else!

package edu.csumb.Webstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.csumb.Webstore.model.User;
import edu.csumb.Webstore.model.CartContext;
import edu.csumb.Webstore.service.UserService;
import io.swagger.annotations.ApiOperation;
import edu.csumb.Webstore.model.Product;
import edu.csumb.Webstore.model.UserContext;
@RestController
public class UserController
{
    //This is autowiring(Telling spring to just connect to the dang service automatically) for us.
    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.POST, value = "/auth/checkauth/")
    @ApiOperation(value = "authenticize the user.")
    public String checkAuth(@RequestBody UserContext userContext)
    {
        return userService.checkAuth(userContext.username, userContext.password);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/auth/checkout/")
    @ApiOperation(value = "Checks out the user.")
    public void checkout(@RequestBody UserContext userContext)
    {
        userService.checkout(userContext.username);
    }


    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    @ApiOperation(value = "Add product in the user.")
    public void addToCard(@RequestBody CartContext cartContext)
    {
        userService.addToCard(cartContext.username, cartContext.product, cartContext.amount);
    }

    @RequestMapping(value = "/changeQuantity", method = RequestMethod.PUT)
    @ApiOperation(value = "Changes the quantity in the cart")
    public void changeQuantity(@RequestBody CartContext cartContext)
    {
        userService.changeQuantity(cartContext.username, cartContext.product, cartContext.amount);
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.PUT)
    @ApiOperation(value = "Checks out the user.")
    public void addProduct(@RequestBody UserContext userContext)
    {
        userService.checkout(userContext.username);
    }

    @RequestMapping(value="/user/adduser", method=RequestMethod.POST)
    // @ApiOperation(value = "Adding user")
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }
    

}