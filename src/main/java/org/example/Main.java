package org.example;

import org.example.data.configuration.SessionFactoryUtil;

import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {
        ///  CREATE DB
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        session.close();

        /// CONSOLE UI
        Engine engine = new Engine();
        engine.run();
    }
}
