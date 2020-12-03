package br.com.agibank.typeprocessor;

import br.com.agibank.typeprocessor.dto.ResultadoDTO;
import br.com.agibank.typeprocessor.exceptions.InstanceException;
import br.com.agibank.typeprocessor.model.Cliente;
import br.com.agibank.typeprocessor.model.Entity;
import br.com.agibank.typeprocessor.model.Venda;
import br.com.agibank.typeprocessor.model.Vendedor;
import br.com.agibank.typeprocessor.service.commandselector.ServiceCommandBuilder;
import br.com.agibank.typeprocessor.service.instancechain.generic.InstanceClient;
import br.com.agibank.typeprocessor.service.result.ClientResultService;
import br.com.agibank.typeprocessor.service.result.SaleResultService;
import br.com.agibank.typeprocessor.service.result.SellerResultService;
import br.com.agibank.typeprocessor.util.Constants;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TypeprocessorApplicationTests {
	@Autowired private InstanceClient instanceClient;
	@Autowired private ServiceCommandBuilder serviceCommandBuilder;
	@Autowired private SaleResultService saleResultService;
	@Autowired private SellerResultService sellerResultService;
	@Autowired private ClientResultService clientResultService;

	@Test
	void testeEntityFactoryVenda() throws InstanceException {
		String key = "any";
		String lineVenda = "003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo";
		Entity elem = this.instanceClient.getEntityInstance(lineVenda, key);
		MatcherAssert.assertThat(elem, instanceOf(Venda.class));
	}

	@Test
	void testeEntityFactoryVendedor() throws InstanceException {
		String key = "any";
		String lineVendedor = "001ç3245678865434çPauloç40000.99";
		Entity elem = this.instanceClient.getEntityInstance(lineVendedor, key);
		MatcherAssert.assertThat(elem, instanceOf(Vendedor.class));
	}

	@Test
	void testeEntityFactoryCliente() throws InstanceException {
		String key = "any";
		String lineCliente = "002ç2345675433444345çEduardo PereiraçRural";
		Entity elem = this.instanceClient.getEntityInstance(lineCliente, key);
		MatcherAssert.assertThat(elem, instanceOf(Cliente.class));
	}

	@Test
	void testeCommandBuilderVenda() throws InstanceException {
		String key = "any";
		String lineVenda = "003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo";
		Entity elem = this.instanceClient.getEntityInstance(lineVenda, key);
		this.serviceCommandBuilder.execute(elem.getClass().getName(), elem, key);
		ResultadoDTO ans1 = saleResultService.getResult().stream().filter(p-> p.getType().equals(Constants.RESULTADO.VENDA_MAIS_CARA)).findAny().get();
		ResultadoDTO ans2 = saleResultService.getResult().stream().filter(p-> p.getType().equals(Constants.RESULTADO.PIOR_VENDEDOR)).findAny().get();
		MatcherAssert.assertThat(ans1, instanceOf(ResultadoDTO.class));
		MatcherAssert.assertThat(ans2, instanceOf(ResultadoDTO.class));
	}

	@Test
	void testeCommandBuilderVendedor() throws InstanceException {
		String key = "any";
		String lineVendedor = "001ç3245678865434çPauloç40000.99";
		Entity elem = this.instanceClient.getEntityInstance(lineVendedor, key);
		this.serviceCommandBuilder.execute(elem.getClass().getName(), elem, key);
		ResultadoDTO ans1 = sellerResultService.getResult().stream().filter(p-> p.getType().equals(Constants.RESULTADO.NUMERO_VENDEDORES)).findAny().get();
		MatcherAssert.assertThat(ans1, instanceOf(ResultadoDTO.class));
	}

	@Test
	void testeCommandBuilderCliente() throws InstanceException {
		String key = "any";
		String lineCliente = "002ç2345675433444345çEduardo PereiraçRural";
		Entity elem = this.instanceClient.getEntityInstance(lineCliente, key);
		this.serviceCommandBuilder.execute(elem.getClass().getName(), elem, key);
		ResultadoDTO ans1 = clientResultService.getResult().stream().filter(p-> p.getType().equals(Constants.RESULTADO.NUMERO_CLIENTES)).findAny().get();
		MatcherAssert.assertThat(ans1, instanceOf(ResultadoDTO.class));
	}

	@Test
	void testeNumeroCliente() throws InstanceException {
		String key = "any";
		String line1 = "002ç2345675433444345çEduardo PereiraçRural";
		String line2 = "002ç2345675434544345çJose da SilvaçRural";
		Entity elem1 = this.instanceClient.getEntityInstance(line1, key);
		Entity elem2 = this.instanceClient.getEntityInstance(line2, key);

		this.serviceCommandBuilder.execute(elem1.getClass().getName(), elem1, key);
		this.serviceCommandBuilder.execute(elem2.getClass().getName(), elem2, key);

		ResultadoDTO ans1 = clientResultService.getResult().stream().filter(p-> p.getType().equals(Constants.RESULTADO.NUMERO_CLIENTES)).findAny().get();
		assertEquals("2", ans1.getValue());
	}

	@Test
	void testeNumeroVendedor() throws InstanceException {
		String key = "any";
		String line1 = "001ç1234567891234çPedroç50000";
		String line2 = "001ç3245678865434çPauloç40000.99";
		Entity elem1 = this.instanceClient.getEntityInstance(line1, key);
		Entity elem2 = this.instanceClient.getEntityInstance(line2, key);

		this.serviceCommandBuilder.execute(elem1.getClass().getName(), elem1, key);
		this.serviceCommandBuilder.execute(elem2.getClass().getName(), elem2, key);

		ResultadoDTO ans1 = sellerResultService.getResult().stream().filter(p-> p.getType().equals(Constants.RESULTADO.NUMERO_VENDEDORES)).findAny().get();
		assertEquals("2", ans1.getValue());
	}

	@Test
	void testeVendaMaisCara() throws InstanceException {
		String key = "any";

		String line1 = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro";
		String line2 = "003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo";
		String line3 = "003ç01ç[1-340-100,2-33-1.50,3-40-0.10]çPamela";
		Entity elem1 = this.instanceClient.getEntityInstance(line1, key);
		Entity elem2 = this.instanceClient.getEntityInstance(line2, key);
		Entity elem3 = this.instanceClient.getEntityInstance(line3, key);
		this.serviceCommandBuilder.execute(elem1.getClass().getName(), elem1, key);
		this.serviceCommandBuilder.execute(elem2.getClass().getName(), elem2, key);
		this.serviceCommandBuilder.execute(elem3.getClass().getName(), elem3, key);

		ResultadoDTO ans1 = saleResultService.getResult().stream().filter(p-> p.getType().equals(Constants.RESULTADO.VENDA_MAIS_CARA)).findAny().get();
		assertEquals("01", ans1.getValue());
	}

	@Test
	void testePiorVendedor() throws InstanceException {
		String key = "any";

		String line1 = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro"; //1000+60+124
		String line2 = "003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo"; //340+49.5+4
		String line3 = "003ç01ç[1-340-100,2-33-1.50,3-40-0.10]çPamela"; //34000+
		Entity elem1 = this.instanceClient.getEntityInstance(line1, key);
		Entity elem2 = this.instanceClient.getEntityInstance(line2, key);
		Entity elem3 = this.instanceClient.getEntityInstance(line3, key);
		this.serviceCommandBuilder.execute(elem1.getClass().getName(), elem1, key);
		this.serviceCommandBuilder.execute(elem2.getClass().getName(), elem2, key);
		this.serviceCommandBuilder.execute(elem3.getClass().getName(), elem3, key);

		ResultadoDTO ans1 = saleResultService.getResult().stream().filter(p-> p.getType().equals(Constants.RESULTADO.PIOR_VENDEDOR)).findAny().get();
		assertEquals("Paulo", ans1.getValue());
	}

}
