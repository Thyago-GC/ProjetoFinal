package br.com.ProjetoFinal.Controller.Dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.ProjetoFinal.Models.Produtos;
import br.com.ProjetoFinal.Repository.produtosRepository;

public class ProdutosDto {

	@NotNull @NotEmpty 
	private String nome;
	@NotNull @NotEmpty 
	private String descricao;
	@NotNull  
	private Double preco;

	public ProdutosDto() {}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Produtos converterCadastro() {
		return new Produtos(nome, descricao, preco);
	}
	
	public Produtos atualizarProduto(Long id, produtosRepository produtosRepository) {
		Produtos produto = produtosRepository.getById(id);
		produto.setNome(this.nome);
		produto.setDescricao(this.descricao);
		produto.setPreco(this.preco);
		return produto;
	}
	
}