package com.prog.payment.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "prog.payment.config")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentConfig {

	private String timeZone;
	
}
