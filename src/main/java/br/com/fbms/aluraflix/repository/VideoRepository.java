package br.com.fbms.aluraflix.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fbms.aluraflix.model.Video;

public interface VideoRepository extends JpaRepository<Video, Long>{

	Page<Video> findByTitulo(String titulo, Pageable paginacao);

}
