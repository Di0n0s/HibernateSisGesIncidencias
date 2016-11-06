/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HibernateSisGesIncidencias;


import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author sfcar
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    private static final ServiceRegistry serviceRegistry;
    
    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            //sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            Configuration configuration = new Configuration();
            configuration.configure();
            serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            sessionFactory = new Configuration().configure().buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {//Fabrica de sesiones
        return sessionFactory;
    }
    
    public static void destroy(){
        StandardServiceRegistryBuilder.destroy(serviceRegistry);
    }
    
}
