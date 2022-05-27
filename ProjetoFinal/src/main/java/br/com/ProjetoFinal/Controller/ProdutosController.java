package br.com.ProjetoFinal.Controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import br.com.ProjetoFinal.Controller.Dto.ProdutosDto;
import br.com.ProjetoFinal.Models.Produtos;
import br.com.ProjetoFinal.Service.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/produtos")
@Api(value = "Controller de produtos")
public class ProdutosController {

	@Autowired
	private ProdutoService service;

	@ApiOperation(value = "Buscando lista de Produtos")
	@GetMapping
	public ResponseEntity<Page<Produtos>> listarProdutos(
			@PageableDefault(page = 0, size = 10, sort = "id") Pageable pageable) {
		return ResponseEntity.ok(service.listarTodos(pageable));
	}

	@ApiOperation(value = "Cadastro de Produtos")
	@PostMapping
	public ResponseEntity<Produtos> cadastrarProduto(@RequestBody @Valid ProdutosDto dto,
			UriComponentsBuilder uriBuilder) {
		Produtos produto = service.cadastroProduto(dto);
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(produto.getId()).toUri();
		return ResponseEntity.created(uri).body(produto);
	}

	@ApiOperation(value = "Consulta de Produtos")
	@GetMapping("/busca")
	public List<Produtos> buscarProdutos(@RequestParam(required = false) Double max_preco,
			@RequestParam(required = false) Double min_preco, @RequestParam(required = false) String q) {
		if (max_preco == null && min_preco == null && q == null) {
			List<Produtos> prod = service.findAll();
			return prod;
		} else {
			List<Produtos> produtos = service.buscaProdutos(max_preco, min_preco, q);
			return produtos;
		}
	}

	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
	@ApiResponse(code = 404, message = "Not Found!") })
	@ApiOperation(value = "Buscando Produto Por ID")
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Produtos>> BuscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(service.buscaId(id));
	}

	@ApiOperation(value = "Atualizando Produto")
	@PutMapping("/{id}")
	public ResponseEntity<Optional<Produtos>> atualizarProduto(@PathVariable Long id,
			@RequestBody @Valid ProdutosDto dto) {
		return ResponseEntity.ok().body(service.atualizarCadastro(id, dto));
	}

	@ApiOperation(value = "Remover Produto")
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> removerProduto(@PathVariable Long id) {
		service.deletarProduto(id);
		return ResponseEntity.ok().build();
	}

}