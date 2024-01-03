package com.ng.ap.react.hateoas;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonProperty;

import reactor.core.publisher.Flux;

public class FluxCollectionModel<T extends RepresentationModel<T>> {

	private Flux<T> content;
	
	private final List<Link> links;

	public FluxCollectionModel() {
		this.links = new ArrayList<>();
	}
//	
//	protected FluxCollectionModel(Flux<T> content, Iterable<Link> newlinks) {
//
//		Assert.notNull(content, "Content must not be null!");
//		Assert.isTrue(!(content instanceof Collection), "Content must not be a collection! Use CollectionModel instead!");
//
//		this.links = new ArrayList<>();
//		this.content = content;
//		
//		newlinks.iterator().forEachRemaining(links::add);
//	}

	public FluxCollectionModel(Flux<T> content) {
		this.content = content; 
		this.links = Links.NONE.toList();
	}
	
	public void add(Link link) {

		Assert.notNull(link, "Link must not be null!");

		this.links.add(link);
	}
	
	@JsonProperty("data")
	public Iterable<T> getContent() {
		return content.toIterable();
	}
	
	@JsonProperty("links")
	public Links getLinks() {
		return Links.of(links);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public boolean equals(@Nullable Object obj) {

		if (this == obj) {
			return true;
		}

		if (obj == null || !obj.getClass().equals(this.getClass())) {
			return false;
		}

		T that = (T) obj;

		return getLinks().equals(that.getLinks());
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return this.links.hashCode();
	}	
}