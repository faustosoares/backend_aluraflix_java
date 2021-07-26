package br.com.fbms.aluraflix.controller.form;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.fbms.aluraflix.model.Categoria;
import br.com.fbms.aluraflix.model.Video;
import br.com.fbms.aluraflix.repository.CategoriaRepository;
import br.com.fbms.aluraflix.repository.VideoRepository;

public class AtualizacaoVideoForm {
	
	@NotNull @NotEmpty @Length(min = 5)
	private String titulo;
	
	@NotNull @NotEmpty @Length(min = 10)
	private String descricao;
	
	@NotNull @NotEmpty @Length(min = 3)
	private String url;
	
	private Long idCategoria;

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public Video atualizar(Long id, VideoRepository videoRepository, CategoriaRepository categoriaRepository) {
		Video video = videoRepository.getOne(id);
		video.setTitulo(this.titulo);
		video.setDescricao(this.descricao);
		video.setDescricao(this.url);

		Optional<Categoria> optionalCategoria = categoriaRepository.findById(idCategoria);
		if(optionalCategoria.isPresent())
			video.setCategoria(optionalCategoria.get());
		else
			video.setCategoria(categoriaRepository.getById(Long.valueOf(1)));

		return video;
	}
	
}
