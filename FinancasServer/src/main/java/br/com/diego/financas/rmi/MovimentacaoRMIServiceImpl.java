package br.com.diego.financas.rmi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.diego.financas.modelo.Conta;
import br.com.diego.financas.modelo.Movimentacao;
import br.com.diego.financas.repository.MovimentacaoRepository;
import org.springframework.stereotype.Service;

@Service
public class MovimentacaoRMIServiceImpl implements MovimentacaoRMIService {

	@Autowired
	private MovimentacaoRepository movimentacaoRepository;

	@Override
	public List<Movimentacao> getMovimentacaoPorConta(Conta conta) {
		return movimentacaoRepository.findAllByContaId(conta.getId());
	}

	@Override
	public void adicionaMovimentacao(Movimentacao movimentacao) {
		movimentacaoRepository.save(movimentacao);
	}

}
