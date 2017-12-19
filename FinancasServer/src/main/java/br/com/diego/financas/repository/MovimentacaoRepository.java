package br.com.diego.financas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.diego.financas.modelo.Movimentacao;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Integer> {

	@Query("select m from Movimentacao m where m.conta.id = :id")
	public List<Movimentacao> findAllByContaId( @Param("id") Integer id);
}
