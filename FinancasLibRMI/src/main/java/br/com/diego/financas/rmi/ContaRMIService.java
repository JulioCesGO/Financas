package br.com.diego.financas.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import br.com.diego.financas.modelo.Conta;
import br.com.diego.financas.modelo.Movimentacao;

public interface ContaRMIService extends Remote {

	public List<Conta> getAllContas() throws RemoteException;

	public Conta getContaById(Integer id) throws RemoteException;

	public List<Movimentacao> getAllMovimentacoesDaContaId(Integer id) throws RemoteException;

	public void adicionarConta(Conta conta) throws RemoteException;

	public void adicionarMovimentacao(Conta conta, Movimentacao movimentacao) throws RemoteException;

	public void removeConta(Conta conta) throws RemoteException;

}


