package org.launchcode.hellospring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.AttributedString;
import java.util.ArrayList;
import java.util.List;

@Controller
 // If every method in your class will take a response body, but it at the top of your class
public class HelloController {

    //Handles request of the form/hello?name=LaunchCode (used to be  @GetMapping("hello/{name}")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = "hello")
    public String hello(@RequestParam String name, Model model) {
        String thegreeting = "Hello, " + name + "!";
        model.addAttribute("greeting",thegreeting);
        return "hello";
    }

    //Handles requests of the form /hello/LaunchCode
    @GetMapping("hello/{name}")
    public String helloAgain(@PathVariable String name, Model model) {
        String theGreeting = "Hello, "+name +"!";
        model.addAttribute("greeting",theGreeting);
        return "hello";
    }

    //returns a message based off of the language that is selected
    @RequestMapping(value = "hello", method = RequestMethod.POST)
    public static String createMessage(@RequestParam String name,@RequestParam String language) {
        String greeting="";
        if (name.equals("")){
            greeting = "World";
        }else if (language.equals("English")){
            greeting= "Hello There, ";
        } else if (language.equals("French")){
            greeting= "Bonjour mon ami/e, ";
        } else if (language.equals("German")){
            greeting= "Hallo mein Freund, ";
        } else if (language.equals("Spanish")){
            greeting= "Hola mi amiga/o, ";
        } else if (language.equals("Swedish")) {
            greeting = "Hej min vän, ";
        }

        return greeting + name +"!";
    }

    ///This form works because we already have a handler method created that accepts "name" and tells it what to do with "name"
   //Lives at /hello/form
    @GetMapping("form")
    public String helloForm(){
        return "form";
    }

    //to Pass this list of names into the template you need a model object
    @GetMapping("hello-names")
    public String helloNames(Model model){
        List<String> names = new ArrayList<>();
        names.add("LaunchCode");
        names.add("Java");
        names.add("JavaScript");
        //the 1st argument is the value the template will see the 2nd argument is the value the variable should have
        model.addAttribute("names",names);
        return "hello-list";

    }
}
