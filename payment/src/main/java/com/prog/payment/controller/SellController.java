package com.prog.payment.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.prog.payment.config.PaymentConfig;
import com.prog.payment.data.vo.SellVO;
import com.prog.payment.service.SellService;

@RestController
@RequestMapping("/sell")
public class SellController {
	private final SellService sellService;
	private final PagedResourcesAssembler<SellVO> assembler;
	private final PaymentConfig config;
	
	@GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
	public SellVO findById(@PathVariable("id") Long id) {
		SellVO SellVO = sellService.findById(id);
		SellVO.add(linkTo(methodOn(SellController.class).findById(id)).withSelfRel());
		return SellVO;
	}
	
	@GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
	public ResponseEntity<?> findAll(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "12") int size,
			@RequestParam(value = "direction", defaultValue = "desc") String direction) {
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "date"));
		
		Page<SellVO> Sells = sellService.findAll(pageable);
		Sells.stream()
			.forEach(p -> p.add(linkTo(methodOn(SellController.class).findById(p.getId())).withSelfRel()));
		
		PagedModel<EntityModel<SellVO>> pageModel = assembler.toModel(Sells);
		
		return new ResponseEntity<>(pageModel,HttpStatus.OK);
	}
	
	@PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"}, 
			consumes = {"application/json", "application/xml", "application/x-yaml"})
	public SellVO create(@RequestBody SellVO sellVO) {
		sellVO.setDate(LocalDateTime.now(ZoneId.of(config.getTimeZone())));
		SellVO vo = sellService.create(sellVO);
		vo.add(linkTo(methodOn(SellController.class).findById(vo.getId())).withSelfRel());
		return vo;
	}
	
	@PutMapping(produces = {"application/json", "application/xml", "application/x-yaml"}, 
			consumes = {"application/json", "application/xml", "application/x-yaml"})
	public SellVO update(@RequestBody SellVO productVO) {
		SellVO vo = sellService.update(productVO);
		vo.add(linkTo(methodOn(SellController.class).findById(vo.getId())).withSelfRel());
		return vo;
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") Long id){
		sellService.delete(id);
	}
	
	@Autowired
	public SellController(SellService sellService, PagedResourcesAssembler<SellVO> assembler, PaymentConfig config) {
		super();
		this.sellService = sellService;
		this.assembler = assembler;
		this.config = config;
	}
}
