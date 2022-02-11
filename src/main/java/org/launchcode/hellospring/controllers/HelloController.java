package org.launchcode.hellospring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody // If every method in your class will take a response body, but it at the top of your class
@RequestMapping("/hello")// Says every single method within this class should begin with /hello
public class HelloController {

    //Handles request at path/hello
//   @GetMapping("hello")
//   @ResponseBody
//   public String hello(){
//       return "Goodbye, Spring!";
//   }


    //lives at /hello/goodbye
    @GetMapping("goodbye")
    public String goodbye() {
        return "Goodbye, Spring!";
    }

    //Handles request of the form/hello?name=LaunchCode (used to be  @GetMapping("hello/{name}")
    //the current @RequestMapping handles get and post requests at the same time
    //This is dynamic Handler which means it accepts data
    //lives at /hello/hello
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = "hello")
    public String helloWithQueryParam(@RequestParam String name) {
        return "Hello " + name + "!";
    }

    //Handles requests of the form /hello/LaunchCode
    // Used to say @GetMapping("hello/{name}") changed @GetMapping since we added it to the top of the class
//    @GetMapping("{name}")
//    public String helloWithPathParam(@PathVariable String name) {
//        return"Hello, "+name +"!";
//    }

    //returns a message based off of the language that is selected
    @GetMapping(value = "", method = RequestMethod.POST)
    public static String createMessage(@RequestParam String name,@RequestParam String language) {
        String greeting="";
        if (name== null){
            greeting = "World";
        }else if (language.equals("English")){
            greeting= "Hello There, ";
        } else if (language.equals("French")){
            greeting= "Bonjour mon ami/e, ";
        } else if (language.equals("German")){
            greeting= "Hallo mein Freund, ";
        } else if (language.equals("Spanish")){
            greeting= "Hola mi amiga/o, ";
        } else if (language.equals("Swedish")){
            greeting= "Hej min vän, ";


        return greeting + name +"!";
    }

    ///This form works because we already have a handler method created that accepts "name" and tells it what to do with "name"
   //Lives at /hello/form
    @GetMapping(value="form", method = {RequestMethod.GET})
    public String helloForm(){
        return"<html>" +
                "<body>" +
                "<form action= 'hello' method='post'>" + // submit a request to /hello
                "<input type='text' name='name'>" +
                "<select name='language' id='language'> " +
                "<option value='English'> English </option>" +
                "<option value='French'> French </option>" +
                "<option value='German'> German </option>" +
                "<option value='Spanish'> Spanish </option>" +
                "<option value='Swedish'> Swedish </option>" +
                "</select>" +
                "<input type='submit' value='Greet me!'>" +
                "</form>" +
                "</body>" +
                "</html>";
    }
}
