package br.com.alura.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	
	/*@RequestMapping("/")
	@ResponseBody
	public String index() {
		System.out.println("Bem vindos a Caelum");
		return "Estou no meu index";
	}*/
	
	@RequestMapping("/")
	@ResponseBody
	public String showMessage() {
		System.out.println("Mensagem sendo mostrada");
		return "Mensagem sendo mostrada";
	}

}
