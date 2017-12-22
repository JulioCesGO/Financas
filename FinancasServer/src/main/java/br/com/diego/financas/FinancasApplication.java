package br.com.diego.financas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiServiceExporter;

import br.com.diego.financas.rmi.ContaRMIService;

@SpringBootApplication
public class FinancasApplication {

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
