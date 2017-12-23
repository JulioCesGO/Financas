package br.com.st.controler;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.zkoss.zhtml.Link;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.ListModelArray;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;

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
//	@Wire
//	private Textbox txtbxSaldo;

	@Wire
	private Listbox lstbxContasCadastradas;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

		List<Conta> todasContas = contaRMIService.getAllContas();
		this.lstbxContasCadastradas.setModel(new ListModelArray<Conta>(todasContas));
	}

	// Metodo que ira ser acionado ao clicar no btnCad //
	@Listen("onClick = #btnCad")
	public void addCadastro() throws RemoteException {
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

		List<Conta> todasContas = contaRMIService.getAllContas();
		this.lstbxContasCadastradas.setModel(new ListModelArray<Conta>(todasContas));
	}

}
