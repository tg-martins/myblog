package br.com.myblog.controller.dto;

import java.time.LocalDate;

import br.com.myblog.model.Categoria;
import br.com.myblog.model.Post;
import br.com.myblog.repository.CategoriaRepository;

public class FormDto {

	private Long id;
	private String autor;
	private String titulo;
	private String texto;
	private String categoria;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public static Post converter(FormDto dto, CategoriaRepository repository) {
		Post post = new Post();
		Categoria categoria = repository.findByNome(dto.getCategoria());
		
		post.setAutor(dto.getAutor());
		post.setData(LocalDate.now());
		post.setTitulo(dto.getTitulo());
		post.setTexto(dto.getTexto());
		post.setCategoria(categoria);
		
		return post;
	}

}
