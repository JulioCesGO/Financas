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
			if(contaRMIService != null) {
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

	// Aqui ele ira vincular o bot�o com a textbox //
	@Wire
	private Textbox txtbxAgencia;
	@Wire
	private Textbox txtbxConta;
	@Wire
	private Textbox txtbxTitular;
	@Wire
	private Textbox txtbxSaldo;

	@Wire
	private Listbox lstbxOperacoesRealizadas;

	private ArrayList<LinkedHashMap<String, String>> listaOperacoes = new ArrayList<LinkedHashMap<String, String>>();

	// Metodo que ira ser acionado ao clicar no btnCad //
	@Listen("onClick = #btnCad")
	public void addCadastro() throws RemoteException {
		String ag = this.txtbxAgencia.getValue();
		String cc = this.txtbxConta.getValue();
		String tt = this.txtbxTitular.getValue();
		String sd = this.txtbxSaldo.getValue();

		LinkedHashMap<String, String> hash = new LinkedHashMap<String, String>();
		hash.put("agencia", ag);
		hash.put("conta", cc); // Aqui ele mostra o valor anterior
		hash.put("titular", tt);
		hash.put("saldo", sd);
		listaOperacoes.add(hash); // Adiciona a lista

		Conta conta = new Conta();
		conta.setTitular(tt);
		conta.setNumero("1234");
		conta.setAgencia(ag);

		contaRMIService.adicionarConta(conta);

		this.lstbxOperacoesRealizadas.setModel(new ListModelArray<LinkedHashMap<String, String>>(this.listaOperacoes));
	}

}
