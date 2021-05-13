/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;


import entity.Product;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author nohop
 */
public class ProductService implements Service<Product>{

    @Override
    public List<Product> getAll(Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Product> pro = builder.createQuery(Product.class);
        Root<Product> root = pro.from(Product.class);
        pro.select(root);
        Query query = session.createQuery(pro);
        
        return query.getResultList();
    }

    @Override
    public List<Product> getByKeyword(String keyword, Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Product> pro = builder.createQuery(Product.class);
        Root<Product> root = pro.from(Product.class);
        pro.select(root);
        
        if(!keyword.isEmpty())
        {
            String p = String.format("%%%s%%", keyword);
            Predicate predicate1 = builder.like(root.get("name").as(String.class), p);
            Predicate predicate2 = builder.like(root.get("id").as(String.class), p);
            pro = pro.where(builder.or(predicate1,predicate2));
        }
        
        Query query = session.createQuery(pro);
        return query.getResultList();
    }

    @Override
    public Product getById(int id, Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Product> pro = builder.createQuery(Product.class);
        Root<Product> root = pro.from(Product.class);
        pro.select(root);
        
        Predicate p = builder.equal(root.get("id"), id);
        pro = pro.where(p);
        
        Query query = session.createQuery(pro);
        return (Product) query.getSingleResult();
    }


    
}
