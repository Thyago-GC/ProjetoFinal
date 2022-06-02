package br.com.ProjetoFinal.Controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ProjetoFinal.Models.Produtos;
import br.com.ProjetoFinal.Service.ProdutoService;

@WebMvcTest({ProdutosController.class})
public class ProdutosControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ProdutoService service;
	
    @Test
    public void deveCadastrarProduto() throws Exception {

        Produtos produto = new Produtos("teste", "teste", 678.0);

        when(service.cadastroProduto(any())).thenReturn(produto);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/produtos")
                        .content(asJsonString(produto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated());
    }
    
    @Test
    public void deveRetornarErroNoCadastrarProduto() throws Exception {

        Produtos produto = new Produtos("", "", 678.0);

        when(service.cadastroProduto(any())).thenReturn(produto);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/produtos")
                        .content(asJsonString(produto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isInternalServerError());
    }
    
	@Test
	public void deveRetornarBuscaDeTodosOsProdutos() throws Exception {
		List<Produtos> produtoList = Arrays.asList(
                new Produtos("teste", "teste", 789.0),
                new Produtos("teste", "teste", 789.0),
                new Produtos("teste", "teste", 789.0)
        );
	    Page<Produtos> produtosPage = new PageImpl<>(produtoList);

        when(service.listarTodos(Mockito.any(PageRequest.class))).thenReturn(produtosPage);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/produtos"))
                .andExpect(status().isOk())
                .equals(produtosPage.hasContent());
	}
    
	@Test
	public void deveRetornarBuscaDeProdutosPorParametro() throws Exception {
		List<Produtos> produtoList = Arrays.asList(
                new Produtos("celular", "teste", 700.0),
                new Produtos("tablet", "teste", 300.0),
                new Produtos("tv", "teste", 1000.0)
        );
	    Page<Produtos> produtosPage = new PageImpl<>(produtoList);
	    
	    double preco_max = 700.0;
	    double preco_min = 500.0;
	    String q = "celular";
	    
        when(service.buscaProdutos(eq(preco_max), eq(preco_min) ,eq(q) ,Mockito.any(PageRequest.class))).thenReturn(produtosPage);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/produtos/busca?q=celular&min_preco=500&max_preco=700"))
                .andExpect(status().isOk())
                .equals(produtosPage.hasContent());
	}
	
    
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}