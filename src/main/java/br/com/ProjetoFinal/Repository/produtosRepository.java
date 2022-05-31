package br.com.ProjetoFinal.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;

import br.com.ProjetoFinal.Models.Produtos;

public interface produtosRepository extends JpaRepository<Produtos, Long> {

	@Query(value = "SELECT * FROM Produtos WHERE (:q IS NULL OR (UPPER(nome) LIKE UPPER(CONCAT('%', :q, '%'))"
			+ "OR UPPER(descricao) LIKE UPPER(CONCAT('%', :q, '%'))))"
			+ "AND (:min_preco IS NULL OR preco >= :min_preco)"
			+ "AND (:max_preco IS NULL OR preco <= :max_preco)", nativeQuery = true)
	
	Page<Produtos> BuscarProdutos(@Param("max_preco") Double max_preco, 
			 @Param("min_preco") Double min_preco, @Param("q") String q,
			 @PageableDefault(page = 0, size = 10, sort = "id") Pageable pageable);
}