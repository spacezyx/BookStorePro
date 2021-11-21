package com.reins.bookstore.repository;

import com.reins.bookstore.entity.Tags;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TagsRepository extends Neo4jRepository<Tags, Long> {

	Tags findByTag(String tag);

	//查询两重关系内能连接到的所有tag
	@Query("match n=(a)-[*1..2]-(b) where a.tag=$tag return n")
	List<Tags> findtags(@Param("tag") String tag);

}
