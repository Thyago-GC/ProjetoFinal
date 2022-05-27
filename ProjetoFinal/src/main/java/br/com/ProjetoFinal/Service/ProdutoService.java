package br.com.ProjetoFinal.Service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.ProjetoFinal.Controller.Dto.ProdutosDto;
import br.com.ProjetoFinal.ExceptionHandler.NotFoundException;
import br.com.ProjetoFinal.Models.Produtos;
import br.com.ProjetoFinal.Repository.produtosRepository;

@Service
public class ProdutoService {

	
	@Autowired
	private produtosRepository produtosRepository;
	
	public Optional<Produtos> buscaId(Long id) {
		Optional<Produtos> produto = Optional.of(produtosRepository.findById(id).orElseThrow(
				() -> new NotFoundException("Nao encontrado, ID:" + id)));
		return produto;
	}
	
    public Page<Produtos> listarTodos(Pageable pageable) {
        return produtosRepository.findAll(pageable);
    }
	
	@Transactional
    public Produtos cadastroProduto(ProdutosDto produtos) {
    	Produtos produto = produtos.converterCadastro();
    	return produtosRepository.save(produto);
    }
    
    @Transactional
    public void deletarProduto(Long id) {
    	Optional<Produtos> produto = Optional.of(produtosRepository.findById(id).orElseThrow(
				() -> new NotFoundException("Nao encontrado, ID:" + id)));  
    	produtosRepository.deleteById(id);
    }
	
    @Transactional
    public Optional<Produtos> atualizarCadastro(Long id,ProdutosDto produtos ){
    	Optional<Produtos> produto = Optional.of(produtosRepository.findById(id).orElseThrow(
				() -> new NotFoundException("Nao encontrado, ID:" + id)));
    	if(produto.isPresent()) {
    		produtos.atualizarProduto(id, produtosRepository);
    	}
    	return produto;
    }
     
    public List<Produtos> buscaProdutos(Double max_preco, Double min_preco,String q){
    	return produtosRepository.BuscarProdutos(max_preco, min_preco, q);
    }
    
    public List<Produtos> findAll(){
    	return produtosRepository.findAll();
    }
}