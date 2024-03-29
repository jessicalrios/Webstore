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

import edu.csumb.Webstore.model.Product;
import edu.csumb.Webstore.service.ProductService;
import io.swagger.annotations.ApiOperation;

@RestController
public class ProductController
{

    //This is autowiring(Telling spring to just connect to the dang service automatically) for us.
    @Autowired
    ProductService productService;

    //REQUESTMAPPING
    //We are setting a request mapping with request type GET. You can change these to POST or anything else you want!
    //The value is the location of the api. So this would be HEROKU/products/example
    
    //APIOPERATION
    //This is comments that will be displayed in swagger. Remember to do this or you will lose points!

    //EXAMPLE()
    //We are returning an Iterable, which means a List! Use Iterable<Datatype> when you want to return many.
    //For example Iterable<Product>
    @RequestMapping(method = RequestMethod.GET, value = "/products/example")
    @ApiOperation(value = "An example of a api function to get you started." )
    public Iterable<String> example()
    {
        //ALL LOGIC SHOULD BE IN THE SERVICE. EVEN IF IT'S JUST ONE LINE!
        //IF YOU HAVE ANY LOGIC IN THE CONTROLLER IT IS BAD!
        //So we are calling the service function we want.
        return productService.example();
    }

    //NETWORKING QUICK REFERENCE
    //IF @RequestMapping(method = RequestMethod.GET, value = "/example/{var}")
    //Then you should use @RequestParam to get the variable, like so
    // public returnType getVar(@RequestParam DataType var)

    @RequestMapping(method = RequestMethod.GET, value = "/products/get/{id}")
    public java.util.Optional<Product> getProduct(@RequestParam String id)
    {
        return productService.getProduct(id);
    }

    //IF  @RequestMapping(method = RequestMethod.POST, value = "/example")
    //Then you should use @RequestBody to get the variable, like so.
    //public returnType foo(@RequestBody dataType var)

    @RequestMapping(method = RequestMethod.GET, value = "/products/getAll")
    public Iterable<Product> getAllProducts()
    {
        return productService.getAllProducts();
    }

    //Put and Delete will use either. They can also be compiled.
    // So IF @RequestMapping(value = "/{pathVar}", method = RequestMethod.PUT)
    //public void foo(@RequestParam dataType pathVar, @RequestBody dataType postVar)
    @RequestMapping(value = "/products/add", method = RequestMethod.PUT)
    public void addProduct(@RequestBody Product product)
    {
        productService.addProduct(product);
    }
}