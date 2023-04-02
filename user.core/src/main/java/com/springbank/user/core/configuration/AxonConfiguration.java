package com.springbank.user.core.configuration;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;


import org.axonframework.eventhandling.tokenstore.TokenStore;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.extensions.mongo.DefaultMongoTemplate;
import org.axonframework.extensions.mongo.MongoTemplate;
import org.axonframework.extensions.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
import org.axonframework.extensions.mongo.eventsourcing.tokenstore.MongoTokenStore;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.json.JacksonSerializer;
import org.axonframework.springboot.autoconfig.AxonAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


@Configuration
public class AxonConfiguration
{
    @Value("${ spring.data.mongodb.host:127.0.0.1}")
    private String mongoHost;

    @Value("${ spring.data.mongodb.port:27017}")
    private String mongoPort;
    @Value("${ spring.data.mongodb.database:user}")
    private String mongoDatabase;




    @Bean
    public MongoClient mongo()
    {

        StringBuilder builder = new StringBuilder();
        builder.append("mongodb://")
                .append(mongoHost)
                .append(":")
                .append(mongoPort)
                .append("/")
                .append(mongoDatabase);
     return MongoClients.create(builder.toString());

    }


    @Bean
    public MongoTemplate axonMongoTemplate() {
        return DefaultMongoTemplate.builder().mongoDatabase(mongo()).build();
    }

    @Bean
    public TokenStore axonMongoTokenStore(){
        return
                MongoTokenStore
                        .builder()
                        .mongoTemplate(axonMongoTemplate())
                        .serializer( axonSerializer())
                        .build();

    }
    @Primary
    @Bean

    public Serializer axonSerializer()
    {
     return JacksonSerializer.defaultSerializer();
    }
    @Bean
    public EventStorageEngine axonEventStorageEngine(){
       return MongoEventStorageEngine
                .builder()

                .mongoTemplate(axonMongoTemplate())
                .build();
    }

    @Bean
    public EventStore axonEventStore()
    {

        return EmbeddedEventStore.builder()
                .storageEngine(axonEventStorageEngine())

                .build();
    }





}
