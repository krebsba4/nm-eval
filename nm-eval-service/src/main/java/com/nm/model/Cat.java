package com.nm.model;

import java.io.Serializable;
import java.util.UUID;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
@JsonDeserialize(builder = Cat.CatBuilder.class)
public class Cat implements Serializable {

	@JsonPOJOBuilder(withPrefix = "")
	public static class CatBuilder{}
	
	private static final long serialVersionUID = -6359913833007673082L;
	@Getter
	private final String id = UUID.randomUUID().toString();
	
	private String file;
}
