package br.com.ProjetoFinal.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.ProjetoFinal.Models.Produtos;

public interface produtosRepository extends JpaRepository<Produtos, Long> {

	@Query(value = "SELECT * FROM Produtos WHERE (:q IS NULL OR (UPPER(nome) LIKE UPPER(CONCAT('%', :q, '%'))"
			+ "OR UPPER(descricao) LIKE UPPER(CONCAT('%', :q, '%'))))"
			+ "AND (:min_preco IS NULL OR preco >= :min_preco)"
			+ "AND (:max_preco IS NULL OR preco <= :max_preco)", nativeQuery = true)
	List<Produtos> BuscarProdutos(@Param("max_preco") Double max_preco, 
			 @Param("min_preco") Double min_preco, @Param("q") String q);
}