package com;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest(classes = RentalApplication.class)
class SpringBootJPAIntegrationTest {

    @Autowired
    private GenericEntityRepository genericEntityRepository;

    @Test
    void genericEntityIntegrationTest() {
        //Give generic entity repository
        // when new saved entity
        GenericEntity genericEntity = genericEntityRepository
                .save(new GenericEntity("test"));
        // and search saved entity:
        Optional<GenericEntity> foundEntity = genericEntityRepository
                .findById(genericEntity.getId());
        //Then
        Assertions.assertTrue(foundEntity.isPresent());
        Assertions.assertEquals(genericEntity.getValue(), foundEntity.get().getValue());
    }
}
