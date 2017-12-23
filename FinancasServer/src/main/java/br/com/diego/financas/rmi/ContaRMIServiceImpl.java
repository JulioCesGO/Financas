package br.com.diego.financas.rmi;

import java.rmi.RemoteException;
import java.util.List;

import br.com.diego.financas.modelo.Movimentacao;
import br.com.diego.financas.repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.diego.financas.modelo.Conta;
import br.com.diego.financas.repository.ContaRepository;
import org.springframework.stereotype.Service;

@Service
public class ContaRMIServiceImpl implements ContaRMIService {

	@Autowired
	private ContaRepository contaRepository;

	@Autowired
	private MovimentacaoRepository movimentacaoRepository;

	@Override
	public List<Conta> getAllContas() {
		return contaRepository.findAll();
	}

	public Conta getContaById(Integer id) throws RemoteException {
		return contaRepository.findOne(id);
	}

	@Override
	public void adicionarConta(Conta conta) throws RemoteException {
		contaRepository.save(conta);
	}

	@Override
	public void adicionarMovimentacao(Conta conta, Movimentacao movimentacao) throws RemoteException {
		movimentacao.setConta(conta);
		movimentacaoRepository.save(movimentacao);
		contaRepository.save(conta);
	}

	@Override
	public void removeConta(Conta conta) throws RemoteException {
		contaRepository.delete(conta);
	}

	public List<Movimentacao> getAllMovimentacoesDaContaId(Integer id) throws RemoteException {
		return movimentacaoRepository.findAllByContaId(id);
	}

}
