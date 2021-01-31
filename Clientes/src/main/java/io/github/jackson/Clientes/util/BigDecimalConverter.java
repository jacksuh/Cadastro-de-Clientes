package io.github.jackson.Clientes.util;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/*converter valores bigdecimal*/
@Component
public class BigDecimalConverter{
	
	/*retira a virgula e coloca  ponto*/
	// 1000.00 -> 1000.00
	public BigDecimal converter(String value) {
		
		if(value == null) {
			return null;
		}
		
		value = value.replace('.', ',').replace(',','.');
		
		//value = value.replace(target:".", replacement:"").replace(target:",", replacement".");
		return new BigDecimal(value);
	}
	
}






