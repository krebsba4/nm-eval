package com.nm.service;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import reactor.netty.http.client.HttpClient;

@Configuration
public class ServiceConfig {

	@Bean
	public WebClient webClient() {
		HttpClient client = HttpClient.create().tcpConfiguration(tcpClient -> {
			tcpClient = tcpClient.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 1500);
			tcpClient = tcpClient
					.doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(60000, TimeUnit.MILLISECONDS)));
			return tcpClient;
		});

		ClientHttpConnector connector = new ReactorClientHttpConnector(client);

		return WebClient.builder().clientConnector(connector).build();
	}
	
	@Bean
	public String RANDOM_CAT_PICTURE_URL() {
		return "https://aws.random.cat/meow";
	}
}
