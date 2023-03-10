package com.global.OnlineShoping.dao;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.global.OnlineShoping.exception.*;

import com.global.OnlineShoping.model.*;


public class CartDAO extends DAO {
	
	public CartDAO(){
	
	}

	public Cart insert(Cart cart) throws CartException {
		try{
			begin();            
			getSession().save(cart);     
            commit();
            return cart;
		} catch (HibernateException e){
			rollback();
            throw new CartException("Could not save the cart", e);
		}
	}
	
	public void update(Cart cart) throws CategoryException {
        try {
            begin();
            getSession().update(cart);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new CategoryException("Could not save the cart", e);
        }
    }
	
	public User update(User user) throws CategoryException {
        try {
            begin();
            getSession().update(user);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new CategoryException("Could not save the user", e);
        }
        return user;
    }
	
	public List<Cart> list(){
		begin();
		Query q = getSession().createQuery("from Cart");
		List<Cart> cart1 = q.list();
		commit();
		return cart1;
	}
	
	public Cart updateCart(Cart cart) throws CategoryException {
        try {
            begin();
            getSession().update(cart);
            commit();
            return cart;
        } catch (HibernateException e) {
            rollback();
            throw new CategoryException("Could not save the cart", e);
        }
    }
	

}
