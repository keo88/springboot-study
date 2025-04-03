package com.example.influxdb.base;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.QueryApi;

@Configuration
public class InfluxDBConfig {

	@Value("${influx.url}")
	private String url;

	@Value("${influx.org}")
	private String org;

	@Value("${influx.token}")
	private String token;

	@Bean
	InfluxDBClient createInfluxDBClient() {

		return InfluxDBClientFactory.create(url, token.toCharArray(), org);
	}

	@Bean
	QueryApi queryApi(InfluxDBClient influxDBClient) {
		return influxDBClient.getQueryApi();
	}

}
