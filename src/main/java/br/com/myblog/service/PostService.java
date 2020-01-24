package br.com.myblog.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.myblog.model.Categoria;
import br.com.myblog.model.Post;
import br.com.myblog.repository.PostRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository repository;

	public Page<Post> findAll(Pageable paginacao) {
		return repository.findAll(paginacao);
	}

	public Optional<Post> findById(Long id) {
		return repository.findById(id);
	}

	@Transactional
	public Page<Post> findByCategory(Categoria categoria, Pageable paginacao) {
		return repository.findByCategoria(categoria, paginacao);

	}

	@Transactional
	public void insert(Post post) {
		repository.save(post);
	}

}
