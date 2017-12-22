package br.com.diego.financas.rmi;

import java.rmi.RemoteException;
import java.util.List;

import br.com.diego.financas.modelo.Conta;
import br.com.diego.financas.modelo.Movimentacao;

public interface MovimentacaoRMIService {

	public List<Movimentacao> getMovimentacaoPorConta(Conta conta)  throws RemoteException;

	public boolean adicionaMovimentacao(Movimentacao movimentacao)  throws RemoteException;

}
