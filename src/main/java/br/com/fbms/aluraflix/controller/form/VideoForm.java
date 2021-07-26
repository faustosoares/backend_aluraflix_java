package br.com.fbms.aluraflix.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.fbms.aluraflix.model.Video;

public class VideoForm {

	@NotNull @NotEmpty @Length(min = 5)
	private String titulo;
	
	@NotNull @NotEmpty @Length(min = 10)
	private String descricao;
	
	@NotNull @NotEmpty
	private String url;
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Video converter() {
		return new Video(titulo, descricao, url);
	}

}