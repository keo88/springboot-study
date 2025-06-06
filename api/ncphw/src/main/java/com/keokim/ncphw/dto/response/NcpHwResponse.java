package com.keokim.ncphw.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NcpHwResponse<T> {
	private String code;
	private String message;
	private T data;

	public static NcpHwResponse<?> success() {
		NcpHwResponse<?> response = new NcpHwResponse<>();
		response.setCode("200");
		response.setMessage("OK");
		response.setData(null);
		return response;
	}
}
