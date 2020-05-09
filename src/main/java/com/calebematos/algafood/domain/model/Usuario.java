package com.calebematos.algafood.domain.model;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
public class Usuario {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	private String email;

	private String senha;

	@CreationTimestamp
	private OffsetDateTime dataCadastro;

	@ManyToMany
	@JoinTable(name = "usuario_grupo", 
			joinColumns = @JoinColumn(name = "usuario_id"), 
			inverseJoinColumns = @JoinColumn(name = "grupo_id"))
	private Set<Grupo> grupos = new HashSet<>();
	
	public boolean adicionarGrupo(Grupo grupo) {
		return this.grupos.add(grupo);
	}
	
	public boolean removerGrupo(Grupo grupo) {
		return this.grupos.remove(grupo);
	}
	
	public boolean isNovo() {
	    return getId() == null;
	}
	
}
