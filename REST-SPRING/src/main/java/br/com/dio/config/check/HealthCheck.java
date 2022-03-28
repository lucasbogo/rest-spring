package br.com.dio.config.check;

import java.net.InetAddress;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class HealthCheck implements HealthIndicator {

	@Override
	public Health health() {   //Método para métrica persnonalizada
		try {
			InetAddress adress = InetAddress.getByName("localhost"); // verificar se o IP está acessível
			if(adress.isReachable(10000))  // se o endereço estiver disponível em até 10 segundos...
					return Health.up().build();
				}catch (Exception e) {
					return Health.down().withDetail("motivo", e.getMessage()).build();
				}
				return Health.down().withDetail("motivo", "motivo desconhecido").build();
				
	}

}
