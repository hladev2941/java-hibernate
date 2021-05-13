/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import entity.Manufacturer;
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
public class ManufacturerService implements Service<Manufacturer>{

    @Override
    public List<Manufacturer> getAll(Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Manufacturer> manu = builder.createQuery(Manufacturer.class);
        Root<Manufacturer> root = manu.from(Manufacturer.class);
        manu.select(root);
        Query query = session.createQuery(manu);
        
        return query.getResultList();
    }

    @Override
    public List<Manufacturer> getByKeyword(String keyword, Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Manufacturer> manu = builder.createQuery(Manufacturer.class);
        Root<Manufacturer> root = manu.from(Manufacturer.class);
        manu.select(root);
        
        if(!keyword.isEmpty())
        {
            String p = String.format("%%%s%%", keyword);
            Predicate predicate1 = builder.like(root.get("name").as(String.class), p);
            Predicate predicate2 = builder.like(root.get("id").as(String.class), p);
            manu = manu.where(builder.or(predicate1,predicate2));
        }
        
        Query query = session.createQuery(manu);
        return query.getResultList();
    }

    @Override
    public Manufacturer getById(int id, Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Manufacturer> manu = builder.createQuery(Manufacturer.class);
        Root<Manufacturer> root = manu.from(Manufacturer.class);
        manu.select(root);
        
        Predicate p = builder.equal(root.get("id"), id);
        
        Query query = session.createQuery(manu);
        return (Manufacturer) query.getSingleResult();
    }

}
