package br.com.diego.financas.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import br.com.diego.financas.modelo.Conta;

public interface ContaRMIService extends Remote {

	public List<Conta> getAllContas() throws RemoteException;

	public boolean adicionarConta(Conta conta) throws RemoteException;

	public boolean removeConta(Conta conta) throws RemoteException;

}
