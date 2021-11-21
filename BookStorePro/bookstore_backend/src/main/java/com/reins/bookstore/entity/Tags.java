
package com.reins.bookstore.entity;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Node
public class Tags {


	@Id @GeneratedValue private Long id;

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	private String tag;

	private Tags() {
	};

	public Tags(String tag) {
		this.tag = tag;
	}


	@Relationship(type = "LINK")
	public Set<Tags> links;

	public void linkWith(Tags tag) {
		if (links == null) {
			links = new HashSet<>();
		}
		links.add(tag);
	}

	public String toString() {
		return this.tag + "'s links => "
			+ Optional.ofNullable(this.links).orElse(
					Collections.emptySet()).stream()
						.map(Tags::getTag)
						.collect(Collectors.toList());
	}

}
