package com.calebematos.algafood.api.model.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioSemSenhaInput {


	@NotBlank
	private String nome;
	
	@Email
	@NotBlank
	private String email;
}
