package com.example.influxdb;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.influxdb.client.QueryApi;
import com.influxdb.query.FluxRecord;
import com.influxdb.query.FluxTable;

@SpringBootTest
class InfluxdbApplicationTests {

	@Autowired
	private QueryApi queryApi;

	@Test
	void contextLoads() {
	}

	@Test
	void testInfluxDb() {
		String flux = "from(bucket:\"home\") |> range(start: 0)";
		List<FluxTable> query = queryApi.query(flux);

		for (FluxTable fluxTable : query) {
			List<FluxRecord> records = fluxTable.getRecords();

			for (FluxRecord fluxRecord : records) {
				System.out.println(fluxRecord.getTime() + " " + fluxRecord.getValueByKey("_value"));
			}
		}
	}

}
