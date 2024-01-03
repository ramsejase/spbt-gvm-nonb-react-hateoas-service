package com.ng.ap.react.assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.reactive.SimpleReactiveRepresentationModelAssembler;
import org.springframework.hateoas.server.reactive.WebFluxLinkBuilder;
import org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.WebFluxLink;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.ng.ap.react.controller.MonoApiController;
import com.ng.ap.react.model.PopulationEntity;
import com.ng.ap.react.model.PopulationModel;
import com.ng.ap.react.model.PopulationModel.PopulationRecord;
import com.ng.ap.react.repository.AsyncCsvReaderRepository;
import com.ng.ap.react.service.PopulationService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class MonoPopulationAssembler implements
	SimpleReactiveRepresentationModelAssembler<PopulationModel> {

	@Autowired
	PopulationService service;
	
	@Autowired
	AsyncCsvReaderRepository reader;

	@Value("${report.population}")
	String pinputFile;

	Class<MonoApiController> controllerClass = MonoApiController.class; 
	Class<PopulationModel> resourceType = PopulationModel.class; 
	
	@Override
	public Mono<CollectionModel<EntityModel<PopulationModel>>> toCollectionModel(
			Flux<? extends PopulationModel> entities, ServerWebExchange exchange) {

		return SimpleReactiveRepresentationModelAssembler.super.toCollectionModel(entities, exchange);
	}
	
	@Override
	public Mono<EntityModel<PopulationModel>> toModel(PopulationModel entity, ServerWebExchange exchange) {

		return SimpleReactiveRepresentationModelAssembler.super.toModel(entity, exchange);
	}
	
	@Override
	public CollectionModel<EntityModel<PopulationModel>> addLinks(
			CollectionModel<EntityModel<PopulationModel>> resources, ServerWebExchange exchange) {

		WebFluxLink wlink = WebFluxLinkBuilder
				.linkTo(WebFluxLinkBuilder.methodOn(controllerClass)
						.findAll(exchange))
						.withSelfRel();
		
		// version:2.2.0
		// netty is throwing error when blocked. With Tomcat, WebfluxLink /blocking is working fine
		resources.add(wlink.toMono().block());
		
		return SimpleReactiveRepresentationModelAssembler.super.addLinks(resources, exchange);
	}
	
	@Override
	public EntityModel<PopulationModel> addLinks(EntityModel<PopulationModel> resource, ServerWebExchange exchange) {

		WebFluxLink wlink = WebFluxLinkBuilder
				.linkTo(WebFluxLinkBuilder.methodOn(controllerClass)
						.findOne(resource.getContent().population.ID(), exchange))
						.withSelfRel();
		// version:2.2.0
		// netty is throwing error when blocked. With Tomcat, WebfluxLink /blocking is working fine
		resource.add(wlink.toMono().block());
		
		return SimpleReactiveRepresentationModelAssembler.super.addLinks(resource, exchange);
	}
	
	public PopulationModel findOneModel(String id) {
		
		return service.findOneEntity(id).map(this::voTOdto).block();
	}
	
	private PopulationModel voTOdto(PopulationEntity entity) {
		
		PopulationModel model = new PopulationModel();
		PopulationRecord record = new PopulationRecord(
					entity.getID(), entity.getSortOrder(), 
					entity.getLocID(), entity.getNotes(), entity.getISO3_code(), entity.getISO2_code(), 
					entity.getSDMX_code(), entity.getLocTypeID(), entity.getLocTypeName(), entity.getParentID(), entity.getLocation(), 
					entity.getVarID(), entity.getVariant(), entity.getTime(), entity.getTPopulation1Jan(), entity.getTPopulation1July(), 
					entity.getTPopulationMale1July(), entity.getTPopulationFemale1July(), entity.getPopDensity(), entity.getPopSexRatio(), 
					entity.getMedianAgePop(), entity.getNatChange(), entity.getNatChangeRT(), entity.getPopChange(), entity.getPopGrowthRate(), 
					entity.getDoublingTime(), entity.getBirths(), entity.getBirths1519(), entity.getCBR(), entity.getTFR(), entity.getNRR(), entity.getMAC(), 
					entity.getSRB(), entity.getDeaths(), entity.getDeathsMale(), entity.getDeathsFemale(), entity.getCDR(), entity.getLEx(), 
					entity.getLExMale(), entity.getLExFemale(), entity.getLE15(), entity.getLE15Male(), entity.getLE15Female(), entity.getLE65(), 
					entity.getLE65Male(), entity.getLE65Female(), entity.getLE80(), entity.getLE80Male(), entity.getLE80Female(), 
					entity.getInfantDeaths(), entity.getIMR(), entity.getLBsurvivingAge1(), entity.getUnder5Deaths(), entity.getQ5(), entity.getQ0040(), 
					entity.getQ0040Male(), entity.getQ0040Female(), entity.getQ0060(), entity.getQ0060Male(), entity.getQ0060Female(), entity.getQ1550(), 
					entity.getQ1550Male(), entity.getQ1550Female(), entity.getQ1560(), entity.getQ1560Male(), entity.getQ1560Female(), entity.getNetMigrations(), 
					entity.getCNMR());
		model.population = record;
		return model;
	}
}
