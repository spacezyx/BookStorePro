package com.reins.bookstore;

import com.reins.bookstore.entity.Tags;
import com.reins.bookstore.repository.TagsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableTransactionManagement
@EnableNeo4jRepositories
@Component
public class BookstoreApplication {

    public static void main(String[] args) {SpringApplication.run(BookstoreApplication.class, args);
    }

    @Bean
    CommandLineRunner demo(TagsRepository tagsRepository) {
        return args -> {

            tagsRepository.deleteAll();

            Tags classic = new Tags("古典小说");
            Tags famous = new Tags("世界名著");
            Tags social = new Tags("社会");
            Tags english = new Tags("中英双语");
            Tags child = new Tags("儿童文学");
            Tags imagine = new Tags("想象");
            Tags kong = new Tags("武侠小说");
            Tags science = new Tags("科幻");
            Tags physics = new Tags("物理");
            Tags magic = new Tags("魔幻小说");

            List<Tags> team = Arrays.asList(classic, famous, social,english,child, imagine, kong,science,physics,magic);

            tagsRepository.save(classic);
            tagsRepository.save(famous);
            tagsRepository.save(social);
            tagsRepository.save(english);
            tagsRepository.save(child);
            tagsRepository.save(imagine);
            tagsRepository.save(kong);
            tagsRepository.save(science);
            tagsRepository.save(physics);
            tagsRepository.save(magic);


            physics = tagsRepository.findByTag(physics.getTag());
            physics.linkWith(science);
            tagsRepository.save(physics);

            magic = tagsRepository.findByTag(magic.getTag());
            magic.linkWith(science);
            magic.linkWith(child);
            tagsRepository.save(magic);

            kong = tagsRepository.findByTag(kong.getTag());
            kong.linkWith(magic);
            tagsRepository.save(kong);

            science = tagsRepository.findByTag(science.getTag());
            science.linkWith(magic);
            science.linkWith(physics);
            tagsRepository.save(science);

            child = tagsRepository.findByTag(child.getTag());
            child.linkWith(imagine);
            tagsRepository.save(child);

            imagine = tagsRepository.findByTag(imagine.getTag());
            imagine.linkWith(science);
            imagine.linkWith(physics);
            imagine.linkWith(magic);
            tagsRepository.save(imagine);


            social = tagsRepository.findByTag(social.getTag());
            social.linkWith(classic);
            tagsRepository.save(social);

            english = tagsRepository.findByTag(english.getTag());
            english.linkWith(famous);
            tagsRepository.save(english);


            classic = tagsRepository.findByTag(classic.getTag());
            classic.linkWith(famous);
            classic.linkWith(social);
            tagsRepository.save(classic);

            famous = tagsRepository.findByTag(famous.getTag());
            famous.linkWith(classic);
            tagsRepository.save(famous);


            List<Tags> teammates = tagsRepository.findtags(magic.getTag());
            System.out.println("The following have Greg as a teammate...");
            teammates.stream().forEach(person -> System.out.println("\t" + person.getTag()));
        };
    }



}
