package org.javaland.graph.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableNeo4jRepositories(basePackages = {"org.javaland.graph.example.repository"})
@EntityScan("org.javaland.graph.example.entity")
public class ExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExampleApplication.class, args);
	}
}
