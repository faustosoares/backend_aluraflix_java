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

import br.com.fbms.aluraflix.controller.dto.VideoDto;
import br.com.fbms.aluraflix.controller.form.AtualizacaoVideoForm;
import br.com.fbms.aluraflix.controller.form.VideoForm;
import br.com.fbms.aluraflix.model.Video;
import br.com.fbms.aluraflix.repository.CategoriaRepository;
import br.com.fbms.aluraflix.repository.VideoRepository;

@RestController
@RequestMapping("/videos")
public class VideoController {

	
	@Autowired
	private VideoRepository repository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public Page<VideoDto> lista(@RequestParam(required = false) String titulo,
			@PageableDefault(sort = "titulo", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		
		if(titulo == null) {
			Page<Video> filmes = repository.findAll(paginacao);
			return VideoDto.converter(filmes);
		}else {
			Page<Video> filmes = repository.findByTitulo(titulo, paginacao);
			return VideoDto.converter(filmes);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<VideoDto> detalhar(@PathVariable Long id) {
		Optional<Video> filme = repository.findById(id);
		if (filme.isPresent()) {
			return ResponseEntity.ok(new VideoDto(filme.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<VideoDto> cadastrar(@RequestBody @Valid VideoForm form, UriComponentsBuilder uriBuilder) {
		Video video = form.converter(categoriaRepository);
		repository.save(video);
		URI uri = uriBuilder.path("/videos/{id}").buildAndExpand(video.getId()).toUri();
		return ResponseEntity.created(uri).body(new VideoDto(video));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<VideoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoVideoForm form) {
		Optional<Video> optional = repository.findById(id);
		if (optional.isPresent()) {
			Video video = form.atualizar(id, repository, categoriaRepository);
			return ResponseEntity.ok(new VideoDto(video));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Video> optional = repository.findById(id);
		if (optional.isPresent()) {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
	
	
	
	
	
	
	
}
