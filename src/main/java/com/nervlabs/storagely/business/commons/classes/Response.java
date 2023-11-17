package com.nervlabs.storagely.business.commons.classes;

import org.springframework.http.HttpStatus;

import com.nervlabs.storagely.business.commons.enums.Types;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response {
	private HttpStatus httpStatus;
	private String message;
	private Object data;
	private Types type;
	private int qty;
}
