package br.com.fbms.aluraflix.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.fbms.aluraflix.model.Categoria;

public class CategoriaForm {
	
	@NotNull @NotEmpty @Length(min = 5)
	private String titulo;
	
	@NotNull @NotEmpty @Length(min = 5)
	private String cor;

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}
	
	public Categoria converter() {
		return new Categoria(titulo, cor);
	}
	

}
