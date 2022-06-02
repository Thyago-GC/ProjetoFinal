package br.com.ProjetoFinal.Service;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.ProjetoFinal.Models.Produtos;
import br.com.ProjetoFinal.Repository.produtosRepository;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

	@Mock
	private produtosRepository repository;
	
	@InjectMocks
	private ProdutoService service;
	
	@Test
	public void deveCadastrarProduto() {
		Produtos produto = new Produtos();
		produto.setNome("celular");
		produto.setDescricao("teste");
		produto.setPreco(600.0);

		when(repository.save(any(Produtos.class))).thenReturn(produto);

		Produtos produtoCadastrado = repository.save(produto);
		assertThat(produtoCadastrado.getNome()).isNotNull();
	}
}