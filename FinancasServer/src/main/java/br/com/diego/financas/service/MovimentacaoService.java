package br.com.diego.financas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.diego.financas.repository.MovimentacaoRepository;

@Service
public class MovimentacaoService implements IMovimentacaoService {

	@Autowired
	private MovimentacaoRepository momentacaoRepository;
	
	
}
