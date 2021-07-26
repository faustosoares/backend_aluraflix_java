package br.com.fbms.aluraflix.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "aluraflix", name = "categoria")
public class Categoria {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String cor;
	
	public Categoria() {}
	
	public Categoria(String titulo, String cor) {
		this.titulo = titulo;
		this.cor = cor;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getCor() {
		return cor;
	}
	
	public void setCor(String cor) {
		this.cor = cor;
	}

	@Override
	public String toString() {
		return "Categoria [id=" + id + ", titulo=" + titulo + ", cor=" + cor + "]";
	}
	
	
}
