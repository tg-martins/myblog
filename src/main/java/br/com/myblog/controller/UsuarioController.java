package br.com.myblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.myblog.controller.dto.UsuarioDTO;
import br.com.myblog.service.UsuarioService;

@Controller
public class UsuarioController {

	@Autowired
	UsuarioService service;

	@PostMapping("/loginForm")
	public String novoUsuario(UsuarioDTO dto) {
		service.insert(UsuarioDTO.converter(dto));
		return "redirect:/login";

	}

}
