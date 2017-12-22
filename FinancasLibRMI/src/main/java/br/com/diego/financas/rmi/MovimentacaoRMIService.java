package br.com.diego.financas.rmi;

import java.util.List;

import br.com.diego.financas.modelo.Conta;
import br.com.diego.financas.modelo.Movimentacao;

public interface MovimentacaoRMIService {

	public List<Movimentacao> getMovimentacaoPorConta(Conta conta);

	public boolean adicionaMovimentacao(Movimentacao movimentacao);

}
