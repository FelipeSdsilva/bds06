package com.devsuperior.movieflix.resources.exceptions;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OAuthCustomError implements Serializable{
	private static final long serialVersionUID = 1L;

	private String error;

	@JsonProperty("error_description")
	private String erroDecription;

	public OAuthCustomError() {
	}

	public OAuthCustomError(String error, String erroDecription) {
		super();
		this.error = error;
		this.erroDecription = erroDecription;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getErroDecription() {
		return erroDecription;
	}

	public void setErroDecription(String erroDecription) {
		this.erroDecription = erroDecription;
	}
}
