package br.com.myblog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.myblog.model.Categoria;
import br.com.myblog.model.Post;

public interface PostRepository  extends JpaRepository<Post, Long>{

	Page<Post> findByCategoria(Categoria categoria, Pageable paginacao);
	

}
