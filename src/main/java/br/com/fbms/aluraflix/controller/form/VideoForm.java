package br.com.fbms.aluraflix.controller.form;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.fbms.aluraflix.model.Categoria;
import br.com.fbms.aluraflix.model.Video;
import br.com.fbms.aluraflix.repository.CategoriaRepository;

public class VideoForm {

	@NotNull @NotEmpty @Length(min = 5)
	private String titulo;
	
	@NotNull @NotEmpty @Length(min = 10)
	private String descricao;
	
	@NotNull @NotEmpty
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
	
	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getUrl() {
		return url;
	}

	public Video converter(CategoriaRepository categoriarepository) {
		Optional<Categoria> optionalCategoria = categoriarepository.findById(idCategoria);
		if(optionalCategoria.isPresent())
			return new Video(titulo, descricao, url, optionalCategoria.get());
		
		return new Video(titulo, descricao, url, categoriarepository.findById(Long.valueOf(1)).get());
	}

}