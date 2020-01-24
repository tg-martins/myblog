package br.com.myblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.myblog.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

	Categoria findByNome(String category);

}
