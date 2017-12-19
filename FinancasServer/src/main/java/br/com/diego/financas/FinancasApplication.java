package br.com.diego.financas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiServiceExporter;

import br.com.diego.financas.rmi.ContaRMIService;
import br.com.diego.financas.rmi.ContaRMIServiceImpl;

@SpringBootApplication
public class FinancasApplication {

	@Bean
	ContaRMIService contaService() {
	    return new ContaRMIServiceImpl();
	}
	
	@Bean
	RmiServiceExporter exporter(ContaRMIService implementation) {
	    Class<ContaRMIService> serviceInterface = ContaRMIService.class;
	    RmiServiceExporter exporter = new RmiServiceExporter();
	    exporter.setServiceInterface(serviceInterface);
	    exporter.setService(implementation);
	    exporter.setServiceName(serviceInterface.getSimpleName());
	    exporter.setRegistryPort(1099); 
	    return exporter;
	}
	public static void main(String[] args) {
		SpringApplication.run(FinancasApplication.class, args);
	}
	
	
}
