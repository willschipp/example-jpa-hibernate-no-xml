package io.platform.enablement.config;

import io.platform.enablement.entity.Person;
import org.junit.Test;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class JpaEntityManagerFactoryIT {

    @Test
    public void testInsertAndFind() {
        //get a new entity manager
        EntityManager entityManager = new JpaEntityManagerFactory(new Class[]{io.platform.enablement.entity.Person.class}).getEntityManager();
        //create a person
        Person person = new Person();
        person.setId(1l);
        person.setFirstName("John");
        person.setLastName("Doe");
        //save
        entityManager.getTransaction().begin();
        entityManager.persist(person);
        entityManager.getTransaction().commit();
        //now retrieve
        Person result = entityManager.find(Person.class,1l);
        assertNotNull(result);
        assertEquals("John",result.getFirstName());
    }
}
