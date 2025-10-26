package org.example.data.configuration;

import org.example.data.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class SessionFactoryUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();

            // Declare DB Sets
            configuration.addAnnotatedClass(Client.class);
            configuration.addAnnotatedClass(Vehicle.class);
            configuration.addAnnotatedClass(Employee.class);
            configuration.addAnnotatedClass(Cargo.class);
            configuration.addAnnotatedClass(Company.class);

            ServiceRegistry serviceRegistry
                    = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }

        return sessionFactory;
    }
}
