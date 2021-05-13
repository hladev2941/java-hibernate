/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author nohop
 */
public interface Service<T> {
    public List<T> getAll(Session session);
    public List<T> getByKeyword(String keyword,Session session);
    public T getById(int id,Session session);
    
}
