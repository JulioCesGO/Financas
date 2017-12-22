package br.com.diego.financas.rmi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.diego.financas.modelo.Conta;
import br.com.diego.financas.modelo.Movimentacao;
import br.com.diego.financas.repository.MovimentacaoRepository;

public class MovimentacaoRMIServiceImpl implements MovimentacaoRMIService {

	@Autowired
	private MovimentacaoRepository movimentacaoRepository;
	
	@Override
	public List<Movimentacao> getMovimentacaoPorConta(Conta conta) {
		return movimentacaoRepository.findAllByContaId( conta.getId() );
	}

	@Override
	public boolean adicionaMovimentacao(Movimentacao movimentacao) {
		return movimentacaoRepository.save(movimentacao) != null;
	}

}
