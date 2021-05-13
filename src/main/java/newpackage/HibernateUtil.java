package newpackage;


import entity.Category;
import entity.Manufacturer;
import entity.Product;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nohop
 */
public class HibernateUtil {
    private final static SessionFactory FACTORY;
    
    static{
        Configuration conf = new Configuration(); 
        Properties pro = new Properties();
        pro.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
        pro.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        pro.put(Environment.URL, "jdbc:mysql://localhost:3306/jsfsaledb");
        pro.put(Environment.USER, "root");
        pro.put(Environment.PASS, "Hien2904");
        conf.setProperties(pro);
        conf.addAnnotatedClass(Category.class);
        conf.addAnnotatedClass(Product.class);
        conf.addAnnotatedClass(Manufacturer.class);
        
        
        
        conf.setProperties(pro);
        ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
        FACTORY = conf.buildSessionFactory(registry);
    }
    public static SessionFactory getSessionFactory()
    {
        return FACTORY;
    }
    
}
