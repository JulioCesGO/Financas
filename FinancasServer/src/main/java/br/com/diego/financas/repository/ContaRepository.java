package br.com.diego.financas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.diego.financas.modelo.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer> {

}
