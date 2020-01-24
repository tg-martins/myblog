package br.com.myblog.util;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.myblog.model.Categoria;
import br.com.myblog.model.Post;
import br.com.myblog.model.Usuario;
import br.com.myblog.repository.CategoriaRepository;
import br.com.myblog.repository.PostRepository;
import br.com.myblog.repository.UsuarioRepository;

@Component
public class Mock {

	@Autowired
	PostRepository postRepository;
	
	@Autowired
	UsuarioRepository userRepository;

	@Autowired
	CategoriaRepository catergoriaRepository;

	@PostConstruct
	public void savePosts() {
	//Dados para polular o DB
  
		var c1 = new Categoria("Java");
		var c2 = new Categoria("Design");
		var c3 = new Categoria("C Sharp");
		var c4 = new Categoria("HTML");
		var c5 = new Categoria("JavaScript");
		var c6 = new Categoria("Python");
		var c7 = new Categoria("PHP");
		var c8 = new Categoria("Ruby");
		var c9 = new Categoria("Perl");
		var c10 = new Categoria("Spring Framework");
		var c11 = new Categoria("Entity Framework");
		var c12 = new Categoria("Others");

		catergoriaRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12));

		List<Categoria> categorias = Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12);

		for (Categoria x : categorias) {

			Post post = new Post();
			post.setAutor("Thiago Martins");
			post.setData(LocalDate.now());
			post.setTitulo("Teste " + x.getNome());
			post.setCategoria(x);
			post.setTexto(
					"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");

			postRepository.save(post);
			
			
		}
	
		Usuario usuario = new Usuario();
		
		usuario.setEmail("teste@teste.com");
		usuario.setNome("Thiago");
		usuario.setSenha("$2a$10$ajICTvIMBwbSA8CmoJOdcOuYIdWU315UF7coQ1bfdZBjbCS1XGDUS");
		
		userRepository.save(usuario);

	}
}
