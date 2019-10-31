package io.platform.enablement.config;

import org.junit.Test;

import static org.junit.Assert.*;

public class JpaEntityManagerFactoryTest {

    @Test
    public void getEntityManager() {
        JpaEntityManagerFactory jpaEntityManagerFactory = new JpaEntityManagerFactory(new Class[]{io.platform.enablement.entity.Person.class});
        assertNotNull(jpaEntityManagerFactory.getEntityManager());
    }

    @Test
    public void getEntityManagerFactory() {
        JpaEntityManagerFactory jpaEntityManagerFactory = new JpaEntityManagerFactory(new Class[]{io.platform.enablement.entity.Person.class});
        assertNotNull(jpaEntityManagerFactory.getEntityManagerFactory());
    }

}