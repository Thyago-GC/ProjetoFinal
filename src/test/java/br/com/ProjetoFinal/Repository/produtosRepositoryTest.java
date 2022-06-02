package br.com.ProjetoFinal.Repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;

import br.com.ProjetoFinal.Models.Produtos;

@DataJpaTest
public class produtosRepositoryTest {

	@Autowired
	private produtosRepository repository;

	@Autowired
	private TestEntityManager em;

	@Test
	public void deveRetornarListaComTodosOsProdutosCadastrados() {
		Page<Produtos> produto = repository.BuscarProdutos(null, null, null, null);
		assertFalse(produto.isEmpty());
	}

	@Test
	public void deveRetornarUmProdutoCadastradoCujoNomeOuDescricaoSejaIgualParamentro() {
		String paramentro = "celular";
		Page<Produtos> produto = repository.BuscarProdutos(null, null, paramentro, null);
		assertFalse(produto.isEmpty());
	}

	@Test
	public void naoDeveRetornarUmProdutoCujoNomeOuDescricaoNaoEstejaCadastrado() {
		String paramentro = "Bola";
		Page<Produtos> produto = repository.BuscarProdutos(null, null, paramentro, null);
		assertTrue(produto.isEmpty());
	}

	@Test
	public void deveRetornarProdutosComPrecoMinimoDe700() {
		Double precoMin = 700.0;
		Page<Produtos> produto = repository.BuscarProdutos(null, precoMin, null, null);
		assertFalse(produto.isEmpty());
	}

	@Test
	public void naoDeveRetornarProdutosComPrecoMinimoDe1000() {
		Double precoMin = 1000.0;
		Page<Produtos> produto = repository.BuscarProdutos(null, precoMin, null, null);
		assertTrue(produto.isEmpty());
	}

	@Test
	public void deveRetornarProdutosComPrecoMaximoDe500() {
		Double precoMax = 500.0;
		Page<Produtos> produto = repository.BuscarProdutos(precoMax, null, null, null);
		assertFalse(produto.isEmpty());
	}

	@Test
	public void naoDeveRetornarProdutosComPrecoMaximoDe250() {
		Double precoMax = 250.0;
		Page<Produtos> produto = repository.BuscarProdutos(precoMax, null, null, null);
		assertTrue(produto.isEmpty());
	}

	@Test
	public void deveRetornarProdutosDentroDoRangeDePrecoMaximoEMinimo() {
		Double precoMax = 700.0;
		Double precoMin = 500.0;
		Page<Produtos> produto = repository.BuscarProdutos(precoMax, precoMin, null, null);
		assertFalse(produto.isEmpty());
	}

	@Test
	public void naoDeveRetornarProdutosDentroDoRangeDePrecoMaximoEMinimo() {
		Double precoMax = 250.0;
		Double precoMin = 100.0;
		Page<Produtos> produto = repository.BuscarProdutos(precoMax, precoMin, null, null);
		assertTrue(produto.isEmpty());
	}

	@Test
	public void deveRetornarProdutosQuePossuemValoresReferentesAosTresParamentros() {
		Double precoMax = 900.0;
		Double precoMin = 500.0;
		String parametro = "novo";
		Page<Produtos> produto = repository.BuscarProdutos(precoMax, precoMin, parametro, null);
		assertFalse(produto.isEmpty());
	}

	@Test
	public void naodeveRetornarProdutosQuePossuemValoresReferentesAosTresParamentros() {
		Double precoMax = 100.0;
		Double precoMin = 200.0;
		String parametro = "usado";
		Page<Produtos> produto = repository.BuscarProdutos(precoMax, precoMin, parametro, null);
		assertTrue(produto.isEmpty());
	}

	@BeforeEach
	public void insereProdutos() {

		Produtos p1 = new Produtos();
		p1.setNome("celular");
		p1.setDescricao("novo");
		p1.setPreco(700.0);
		em.persist(p1);

		Produtos p2 = new Produtos();
		p2.setNome("tablet");
		p2.setDescricao("novo");
		p2.setPreco(500.0);
		em.persist(p2);

		Produtos p3 = new Produtos();
		p3.setNome("cama");
		p3.setDescricao("usado");
		p3.setPreco(300.0);
		em.persist(p3);

		Produtos p4 = new Produtos();
		p4.setNome("TV");
		p4.setDescricao("novo");
		p4.setPreco(900.0);
		em.persist(p4);
	}

}