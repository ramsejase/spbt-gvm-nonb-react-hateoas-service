package com.ng.ap.react.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.reactive.WebFluxLinkBuilder;
import org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.WebFluxLink;
import org.springframework.stereotype.Component;

import com.ng.ap.react.controller.FluxApiController;
import com.ng.ap.react.model.PopulationModel;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class FluxPopulationAssembler {
	
	Class<FluxApiController> controllerClass = FluxApiController.class; 
	Class<PopulationModel> resourceType = PopulationModel.class; 
	
	public Flux<EntityModel<PopulationModel>> toFluxModel(
			Flux<? extends PopulationModel> entities) {

		return entities.flatMap(this::toModel);
	}
	
	public Flux<EntityModel<PopulationModel>> toFluxCollectionModel(
			Flux<? extends PopulationModel> entities) {

		Flux<EntityModel<PopulationModel>> models = toFluxModel(entities);

		return models;
		
//		Link link = WebFluxLinkBuilder
//				.linkTo(WebFluxLinkBuilder.methodOn(controllerClass)
//						.findAll())
//						.withSelfRel()
//						.toMono()
//						.block();
//		
//		EntityModel<Flux<EntityModel<PopulationModel>>> collectionModel = EntityModel
//				.of(models)
//				.add(link);
//
//		return Mono.just(collectionModel);
	}
	

//	public Mono<CollectionModel<EntityModel<PopulationModel>>> toFluxCollectionModel(
//			Flux<? extends PopulationModel> entities) {
//
//		Flux<EntityModel<PopulationModel>> models = toFluxModel(entities);
//
//		Link link = WebFluxLinkBuilder
//				.linkTo(WebFluxLinkBuilder.methodOn(controllerClass)
//						.findAll())
//						.withSelfRel()
//						.toMono()
//						.block();
//
//		CollectionModel<EntityModel<PopulationModel>> collectionModel = CollectionModel
//				.of(models.toIterable())
//				.add(link);
//
//		return Mono.just(collectionModel);
//	}

	
	public Mono<EntityModel<PopulationModel>> toModel(PopulationModel entity) {

		EntityModel<PopulationModel> resource = EntityModel.of(entity);
		return Mono.just(addLinks(resource));
	}

	
	public EntityModel<PopulationModel> addLinks(EntityModel<PopulationModel> resource) {

		WebFluxLink wlink = WebFluxLinkBuilder
				.linkTo(WebFluxLinkBuilder.methodOn(controllerClass)
						.findOne(resource.getContent().population.ID()))
						.withSelfRel();
		// version:2.2.0
		// netty is throwing error when blocked. With Tomcat, WebfluxLink /blocking is working fine
		return resource.add(wlink.toMono().block());		
	}

}