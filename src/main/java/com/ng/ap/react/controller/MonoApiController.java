package com.ng.ap.react.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

import com.ng.ap.react.assembler.MonoPopulationAssembler;
import com.ng.ap.react.model.PopulationModel;
import com.ng.ap.react.service.PopulationService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/bpop/api")
public class MonoApiController {
		
	@Autowired
	MonoPopulationAssembler populationAssembler;
	
	@Autowired
	PopulationService populationService;

	@GetMapping(path= "/{id}")
	public Mono<EntityModel<PopulationModel>> findOne(@PathVariable String id, ServerWebExchange exchange) {

		return populationAssembler.toModel(
				populationService.findOneModel(id), exchange);
	}

	@GetMapping(path= "/all", 
			produces = {MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public Mono<CollectionModel<EntityModel<PopulationModel>>> findAll(ServerWebExchange exchange) {
		
		return populationAssembler
				.toCollectionModel(populationService.findAllModel(), exchange);
	}	
}