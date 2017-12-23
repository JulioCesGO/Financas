package br.com.st.controler;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;
import java.util.List;

import br.com.diego.financas.modelo.Movimentacao;
import br.com.diego.financas.modelo.TipoTransacao;
import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.*;

import br.com.diego.financas.modelo.Conta;
import br.com.diego.financas.rmi.ContaRMIService;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class Cindex extends SelectorComposer<Component> {

	private static final ContaRMIService contaRMIService = carregaContaRMIService();

	private static ContaRMIService carregaContaRMIService() {
		System.out.println(">> Cliente iniciou");

		ContaRMIService contaRMIService = null;
		try {
			Registry registry = LocateRegistry.getRegistry("localhost", 1099);
			contaRMIService = (ContaRMIService) registry.lookup("ContaRMIService");
			if (contaRMIService != null) {
				System.out.println(">> lookup obteve com sucesso!");
			} else {
				System.out.println(">> lookup NÃO obteve nenhum objeto!");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return contaRMIService;
	}

	/**
	 * Adicionar no controler o codigo abaixo
	 */
	private static final long serialVersionUID = 1L;

	// Aqui ele ira vincular o botão com a textbox //
	@Wire
	private Textbox txtbxBanco;
	@Wire
	private Textbox txtbxAgencia;
	@Wire
	private Textbox txtbxConta;
	@Wire
	private Textbox txtbxTitular;
	@Wire
	private Intbox txtbxMovIdConta;
	@Wire
	private Doublebox doublebxMovValor;
	@Wire
	private Textbox txtbxMovTipo;

	@Wire
	private Listbox lstbxContasCadastradas;

	@Wire
	private Listbox lstbxMovimentacoes;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		atualizaDadosDaTela();
	}

	@Listen("onClick = #btnListMov")
	public void listarMovimentacoes() throws RemoteException {
		atualizaDadosDaTela();
	}

	@Listen("onClick = #btnCadMovRemocao")
	public void cadastrarMovimentacaoRemocao() throws RemoteException {
		Integer idConta = this.txtbxMovIdConta.getValue();
		Double valorMovimentacao = this.doublebxMovValor.getValue();

		if (idConta == 0 || valorMovimentacao == 0) {
			return;
		}

		Conta conta = contaRMIService.getContaById(idConta);

		Movimentacao mov = new Movimentacao();
		mov.setValor(BigDecimal.valueOf(valorMovimentacao));
		mov.setDataMovimentacao(new Date());
		mov.setTipo(TipoTransacao.SAIDA);

		contaRMIService.adicionarMovimentacao(conta, mov);

		atualizaDadosDaTela();
	}

	@Listen("onClick = #btnCadMovAdicao")
	public void cadastrarMovimentacaoAdicao() throws RemoteException {
		Integer idConta = this.txtbxMovIdConta.getValue();
		Double valorMovimentacao = this.doublebxMovValor.getValue();

		if (idConta == 0 || idConta == null || valorMovimentacao == 0 || valorMovimentacao == null) {
			return;
		}

		Conta conta = contaRMIService.getContaById(idConta);

		Movimentacao mov = new Movimentacao();
		mov.setValor(BigDecimal.valueOf(valorMovimentacao));
		mov.setDataMovimentacao(new Date());
		mov.setTipo(TipoTransacao.ENTRADA);

		contaRMIService.adicionarMovimentacao(conta, mov);

		atualizaDadosDaTela();
	}

	@Listen("onClick = #btnCad")
	public void addCadastro() throws RemoteException {
		String valorBanco = this.txtbxBanco.getValue();
		String valorAgencia = this.txtbxAgencia.getValue();
		String valorNumeroConta = this.txtbxConta.getValue();
		String valorTitular = this.txtbxTitular.getValue();

		if (isNullOuBranco(valorBanco) || isNullOuBranco(valorAgencia) || isNullOuBranco(valorNumeroConta) || isNullOuBranco(valorTitular)) {
			return;
		}

		Conta conta = new Conta();
		conta.setBanco(valorBanco);
		conta.setAgencia(valorAgencia);
		conta.setNumero(valorNumeroConta);
		conta.setTitular(valorTitular);

		contaRMIService.adicionarConta(conta);

		atualizaDadosDaTela();
	}


	private boolean isNullOuBranco(String valor) {
		return valor == null || valor.trim() == "";
	}

	@Listen("onClick = #btnDel")
	public void dellCadastro() throws RemoteException {
		String valorBanco = this.txtbxBanco.getValue();
		String valorAgencia = this.txtbxAgencia.getValue();
		String valorNumeroConta = this.txtbxConta.getValue();
		String valorTitular = this.txtbxTitular.getValue();

		Conta conta = new Conta();
		conta.setBanco(valorBanco);
		conta.setAgencia(valorAgencia);
		conta.setNumero(valorNumeroConta);
		conta.setTitular(valorTitular);

		contaRMIService.adicionarConta(conta);

		atualizaDadosDaTela();
	}

	private void atualizaDadosDaTela() throws RemoteException {
		List<Conta> todasContas = contaRMIService.getAllContas();
		this.lstbxContasCadastradas.setModel(new ListModelArray<Conta>(todasContas));

		Integer idConta = this.txtbxMovIdConta.getValue();
		if (idConta != null && idConta != 0) {
			List<Movimentacao> todasMovimentacoes = contaRMIService.getAllMovimentacoesDaContaId(idConta);
			this.lstbxMovimentacoes.setModel(new ListModelArray<Movimentacao>(todasMovimentacoes));
		}
	}

}
