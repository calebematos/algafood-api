package com.calebematos.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.calebematos.algafood.api.assembler.FormaPagamentoAssembler;
import com.calebematos.algafood.api.assembler.FormaPagamentoInputDisassembler;
import com.calebematos.algafood.api.model.FormaPagamentoModel;
import com.calebematos.algafood.api.model.input.FormaPagamentoInput;
import com.calebematos.algafood.domain.model.FormaPagamento;
import com.calebematos.algafood.domain.repository.FormaPagamentoRepository;
import com.calebematos.algafood.domain.service.FormaPagamentoService;

@RestController
@RequestMapping("/formas-pagamento")
public class FormaPagamentoController {

	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;
	
	@Autowired
	private FormaPagamentoService formaPagamentoService;
	
	@Autowired
	private FormaPagamentoAssembler formaPagamentoAssembler;
	
	@Autowired
	private FormaPagamentoInputDisassembler formaPagamentoInputDisassembler;
	
	public List<FormaPagamentoModel> listar(){
		return formaPagamentoAssembler.toCollectionModel(formaPagamentoRepository.findAll());
	}
	
	@GetMapping("/{formaPagamentoId}")
	public FormaPagamentoModel buscar(@PathVariable Long formaPagamentoId) {
		FormaPagamento formaPagamento = formaPagamentoService.buscar(formaPagamentoId);
		return formaPagamentoAssembler.toModel(formaPagamento);
	}
	
	@PostMapping
	public ResponseEntity<FormaPagamentoModel> adicionar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
		FormaPagamento formaPagamento = formaPagamentoInputDisassembler.toDomainObject(formaPagamentoInput);
		formaPagamento = formaPagamentoService.salvar(formaPagamento);
		return ResponseEntity.status(HttpStatus.CREATED).body(formaPagamentoAssembler.toModel(formaPagamento));
	}
	
	@PutMapping("/{formaPagamentoId}")
	public ResponseEntity<FormaPagamentoModel> atualizar(@PathVariable Long formaPagamentoId, @RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
		FormaPagamento formaPagamento = formaPagamentoInputDisassembler.toDomainObject(formaPagamentoInput);
		FormaPagamento formaPagamentoAtual = formaPagamentoService.buscar(formaPagamentoId);
		BeanUtils.copyProperties(formaPagamento, formaPagamentoAtual, "id");
		formaPagamentoAtual = formaPagamentoService.salvar(formaPagamentoAtual);
		return ResponseEntity.ok(formaPagamentoAssembler.toModel(formaPagamentoAtual));
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{formaPagamentoId}")
	public void remover(@PathVariable Long formaPagamentoId) {
		formaPagamentoService.excluir(formaPagamentoId);
	}
}