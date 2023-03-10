package com.global.OnlineShoping.controller;


import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.global.OnlineShoping.dao.CartDAO;
import com.global.OnlineShoping.dao.CategoryDAO;
import com.global.OnlineShoping.dao.DAO;
import com.global.OnlineShoping.dao.UserDAO;
import com.global.OnlineShoping.model.Advert;
import com.global.OnlineShoping.model.Cart;
import com.global.OnlineShoping.model.User;


@Controller
@RequestMapping("/cart/*")
public class cartController extends DAO{

	@Autowired
	@Qualifier("advertDao")
	Advert advertDao;
	
	@Autowired
	@Qualifier("categoryDao")
	CategoryDAO categoryDao;
	
	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;
	
	@Autowired
	@Qualifier("cartDao")
	CartDAO cartDao;
	
	@Autowired
	ServletContext servletContext;
	
	
	
	@RequestMapping(value = "/cart/insert", method = RequestMethod.POST)
	public ModelAndView addCategory(@ModelAttribute("cart") Cart cart, BindingResult result, HttpServletRequest request) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		User u = (User)session.getAttribute("user");
		cart.setUser(u);
		u.setCart(cart);
		Cart cd=null;
//		Cart c = cartDao.insert(cart);
//		User user1 = cartDao.update(u);
//		 getSession().update(u);
		 List<Cart> c=cartDao.list();
		 int i=0;
		 Cart cw = null;
		 for(Cart cc:c){
			 if(u.getPersonID()==cc.getId()){
				 cw = cartDao.updateCart(cc);
				 i=1;
				 return new ModelAndView("user-cart","c",cw);
			 }
		 }
	 if (i==0){
		  cd = cartDao.insert(cart);
	 }
	
		return new ModelAndView("user-cart","c",cd);
	}
}
