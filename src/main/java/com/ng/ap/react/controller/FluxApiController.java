package com.ng.ap.react.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ng.ap.react.assembler.FluxPopulationAssembler;
import com.ng.ap.react.model.PopulationModel;
import com.ng.ap.react.service.PopulationService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/nbpop/api")
public class FluxApiController {

	@Autowired
	FluxPopulationAssembler populationAssembler;
	
	@Autowired
	PopulationService populationService;

	@GetMapping(path= "/{id}")
	public Mono<EntityModel<PopulationModel>> findOne(@PathVariable String id) {

		return populationAssembler.toModel(
				populationService.findOneModel(id));
	}
	
	@GetMapping(path= "/all"
			,
			produces = {
					MediaTypes.HAL_JSON_VALUE,
////					MediaType.APPLICATION_STREAM_JSON_VALUE, 
////					MediaType.APPLICATION_STREAM_JSON_VALUE,
////					MediaType.TEXT_EVENT_STREAM_VALUE
//					MediaType.APPLICATION_NDJSON_VALUE,
					MediaType.APPLICATION_JSON_VALUE
					}
			)
	public ResponseEntity<Flux<EntityModel<PopulationModel>>> findAll() {
		
		return ResponseEntity.ok(populationAssembler
				.toFluxCollectionModel(populationService.findAllModel()));
	}
}