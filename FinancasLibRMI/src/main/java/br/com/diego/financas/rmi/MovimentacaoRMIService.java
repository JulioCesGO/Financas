package br.com.diego.financas.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import br.com.diego.financas.modelo.Conta;
import br.com.diego.financas.modelo.Movimentacao;

public interface MovimentacaoRMIService extends Remote {

	public List<Movimentacao> getMovimentacaoPorConta(Conta conta)  throws RemoteException;

	public void adicionaMovimentacao(Movimentacao movimentacao)  throws RemoteException;

}
