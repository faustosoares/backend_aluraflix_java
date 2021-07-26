package br.com.fbms.aluraflix.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fbms.aluraflix.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

	Page<Categoria> findByTitulo(String titulo, Pageable paginacao);

}
