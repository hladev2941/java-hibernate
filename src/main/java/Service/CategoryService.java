/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import entity.Category;
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
public class CategoryService implements Service<Category>{

    @Override
    public List<Category> getAll(Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Category> cats = builder.createQuery(Category.class);
        Root<Category> root = cats.from(Category.class);
        cats.select(root);
        Query query = session.createQuery(cats);
        
        return query.getResultList();
    }

    @Override
    public List<Category> getByKeyword(String keyword, Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Category> cats = builder.createQuery(Category.class);
        Root<Category> root = cats.from(Category.class);
        cats.select(root);
        
        if(!keyword.isEmpty())
        {
            String p = String.format("%%%s%%", keyword);
            Predicate predicate1 = builder.like(root.get("name").as(String.class), p);
            Predicate predicate2 = builder.like(root.get("id").as(String.class), p);
            cats = cats.where(builder.or(predicate1,predicate2));
        }
        
        Query query = session.createQuery(cats);
        return query.getResultList();
    }
    

    @Override
    public Category getById(int id, Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Category> cats = builder.createQuery(Category.class);
        Root<Category> root = cats.from(Category.class);
        cats.select(root);
        
        Predicate p = builder.equal(root.get("id"), id);
        
        Query query = session.createQuery(cats);
        return (Category) query.getSingleResult();
        
    }

}
