package br.com.fbms.aluraflix.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fbms.aluraflix.controller.dto.CategoriaDto;
import br.com.fbms.aluraflix.controller.dto.VideoDto;
import br.com.fbms.aluraflix.controller.form.AtualizacaoCategoriaForm;
import br.com.fbms.aluraflix.controller.form.AtualizacaoVideoForm;
import br.com.fbms.aluraflix.controller.form.CategoriaForm;
import br.com.fbms.aluraflix.controller.form.VideoForm;
import br.com.fbms.aluraflix.model.Categoria;
import br.com.fbms.aluraflix.model.Video;
import br.com.fbms.aluraflix.repository.CategoriaRepository;
import br.com.fbms.aluraflix.repository.VideoRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	
	@Autowired
	private CategoriaRepository repository;
	
	@Autowired
	private VideoRepository videoRepository;
	
	@GetMapping
	public Page<CategoriaDto> lista(@RequestParam(required = false) String titulo,
			@PageableDefault(sort = "titulo", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		
		if(titulo == null) {
			Page<Categoria> categorias = repository.findAll(paginacao);
			return CategoriaDto.converter(categorias);
		}else {
			Page<Categoria> categorias = repository.findByTitulo(titulo, paginacao);
			return CategoriaDto.converter(categorias);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoriaDto> detalhar(@PathVariable Long id) {
		Optional<Categoria> categoria = repository.findById(id);
		if (categoria.isPresent()) {
			return ResponseEntity.ok(new CategoriaDto(categoria.get()));
		}
		
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional
	public ResponseEntity<CategoriaDto> cadastrar(@RequestBody @Valid CategoriaForm form, UriComponentsBuilder uriBuilder) {
		Categoria categoria = form.converter();
		repository.save(categoria);
		URI uri = uriBuilder.path("/categorias/{id}").buildAndExpand(categoria.getId()).toUri();
		return ResponseEntity.created(uri).body(new CategoriaDto(categoria));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<CategoriaDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoCategoriaForm form) {
		Optional<Categoria> optional = repository.findById(id);
		if (optional.isPresent()) {
			Categoria categoria = form.atualizar(id, repository);
			return ResponseEntity.ok(new CategoriaDto(categoria));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Categoria> optional = repository.findById(id);
		if (optional.isPresent()) {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/{id}/videos")
	public Page<VideoDto> listarVideosDaCategoria(@PathVariable Long id,
			@PageableDefault(sort = "titulo", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		
		Optional<Categoria> opCategoria = repository.findById(id);
		if(opCategoria.isPresent()) {
			Page<Video> videosDaCategoria = videoRepository.findByCategoria(opCategoria.get(), paginacao);	
			return VideoDto.converter(videosDaCategoria); 
		}
		
		return null;
	}
	
	
	
	
	
	
	
	
	
	
}
