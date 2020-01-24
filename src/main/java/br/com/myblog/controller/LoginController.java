package br.com.myblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

	@GetMapping("/loginForm")
	public String loginForm() {
		return "loginForm";
	}

	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}

	@GetMapping("/logout-success")
	public String logoutPage(RedirectAttributes attributes) {
		attributes.addFlashAttribute("sucesso", "Você efetuou logout com sucesso!");

		return "redirect:/posts";
	}

}
