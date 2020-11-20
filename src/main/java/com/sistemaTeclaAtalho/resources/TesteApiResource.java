package com.sistemaTeclaAtalho.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/teste")
public class TesteApiResource {
	
	@GetMapping
	public String testeApi() {
		return "Sucesso: Api está ativa!";
	}

}
