package br.com.diego.financas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.diego.financas.repository.ContaRepository;

@Service
public class ContaService implements IContaService {

	@Autowired
	private ContaRepository contaRepository;
	
}
