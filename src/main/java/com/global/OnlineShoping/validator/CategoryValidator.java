package com.global.OnlineShoping.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.global.OnlineShoping.dao.*;
import com.global.OnlineShoping.exception.*;
import com.global.OnlineShoping.model.*;

@Component
public class CategoryValidator implements Validator {

	@Autowired
	@Qualifier("categoryDao")
	CategoryDAO categoryDAO;
	
	public boolean supports(Class aClass) {
		return Category.class.equals(aClass);
	}

	public void validate(Object obj, Errors errors) {
		Category newCategory = (Category) obj;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "error.invalid.category", "Category Required");
		
		if (errors.hasErrors()) {
            return;//Skip the rest of the validation rules
        }
        
	
		try {
			Category c = categoryDAO.get(newCategory.getTitle());
			if(c !=null)
				errors.rejectValue("title", "error.invalid.category", "Category already Exists");
			
		} catch (CategoryException e) {
			System.err.println("Exception in Category Validator");
		}
		
		
		
	
	}
}
