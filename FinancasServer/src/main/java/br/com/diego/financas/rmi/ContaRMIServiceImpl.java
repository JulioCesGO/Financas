package br.com.diego.financas.rmi;

import java.rmi.RemoteException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.diego.financas.modelo.Conta;
import br.com.diego.financas.repository.ContaRepository;

public class ContaRMIServiceImpl implements ContaRMIService {

	@Autowired
	private ContaRepository contaRepository;
	
	@Override
	public List<Conta> getAllContas() {
		return contaRepository.findAll();
	}

	@Override
	public boolean adicionarConta(Conta conta) throws RemoteException {
		return contaRepository.save(conta) != null;
	}

	@Override
	public boolean removeConta(Conta conta) throws RemoteException {
		contaRepository.delete(conta);
		return true;
	}

}
