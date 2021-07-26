package br.com.fbms.aluraflix.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.fbms.aluraflix.model.Categoria;
import br.com.fbms.aluraflix.repository.CategoriaRepository;

public class AtualizacaoCategoriaForm {
	
	@NotNull @NotEmpty @Length(min = 5)
	private String titulo;
	
	@NotNull @NotEmpty @Length(min = 3)
	private String cor;
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public Categoria atualizar(Long id, CategoriaRepository categoriaRepository) {
		Categoria categoria = categoriaRepository.getOne(id);
		categoria.setTitulo(this.titulo);
		categoria.setCor(this.cor);
		return categoria;
	}
	
}
