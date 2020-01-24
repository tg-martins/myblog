package br.com.myblog.controller.dto;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.myblog.model.Usuario;

public class UsuarioDTO {

	private Long id;
	private String nome;
	private String email;
	private String senha;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	public static Usuario converter(UsuarioDTO dto) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String senha = encoder.encode(dto.getSenha());

		Usuario user = new Usuario();
		
		user.setNome(dto.getNome());
		user.setEmail(dto.getEmail());
		user.setSenha(senha);
		
		return user;
	}

}
