package io.platform.enablement.config;

import org.h2.jdbcx.JdbcDataSource;
import org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl;
import org.hibernate.jpa.boot.internal.PersistenceUnitInfoDescriptor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceUnitInfo;
import javax.sql.DataSource;
import java.util.*;

public class JpaEntityManagerFactory {

    private String DB_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private String DB_USER_NAME = "sa";
    private String DB_PASSWORD = "";
    private Class[] classes;

    public JpaEntityManagerFactory(Class[] classes) {
        this.classes = classes;
    }

    public EntityManager getEntityManager() {
        return getEntityManagerFactory().createEntityManager();
    }

    protected EntityManagerFactory getEntityManagerFactory() {
        PersistenceUnitInfo persistenceUnitInfo = getPersistenceUnitInfo(getClass().getSimpleName());
        Map<String,Object> configuration = new HashMap<>();
        EntityManagerFactoryBuilderImpl entityManagerFactoryBuilder = new EntityManagerFactoryBuilderImpl(new PersistenceUnitInfoDescriptor(persistenceUnitInfo),configuration);
        entityManagerFactoryBuilder.generateSchema();
        return entityManagerFactoryBuilder.build();
    }

    protected HibernatePersistenceUnitInfo getPersistenceUnitInfo(String name) {
        return new HibernatePersistenceUnitInfo(name,getEntities(),getProperties());
    }

    protected DataSource getDataSource() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL(DB_URL);
        dataSource.setUser(DB_USER_NAME);
        dataSource.setPassword(DB_PASSWORD);
        return dataSource;
    }

    protected Properties getProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect","org.hibernate.dialect.H2Dialect");
        properties.put("hibernate.id.new_generator_mappings","false");
        properties.put("hibernate.hbm2ddl.auto","create");
        properties.put("hibernate.show_sql","true");
        properties.put("hibernate.format_sql","true");
        properties.put("hibernate.connection.datasource",getDataSource());
        return properties;
    }

    protected List<String> getEntities() {
        List<String> names = new ArrayList<>();
        for (Class cls : classes) {
            names.add(cls.getName());
        }//end for
        return names;
    }
}
