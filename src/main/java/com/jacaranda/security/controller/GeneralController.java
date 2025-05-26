package com.jacaranda.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GeneralController {
	
	 	@GetMapping("/")
	    public String index() {
	        return "index";
	    }
	 	
	 	@GetMapping("/index")
	    public String index2() {
	        return "index";
	    }

}
