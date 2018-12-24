/**
 * 
 */
package com.haili.ins.common.utils;

import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.haili.ins.common.enums.ResponseCodeEnum;
import com.haili.ins.common.exception.ServiceException;




/**
 * @author new
 *
 */
public class BeanValidator {
	
	public static<T> void validate(T bean) throws ServiceException {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<T>> constraintViolations=validator.validate(bean);
		
		StringBuffer sbf = new StringBuffer();
		
		for (Iterator<ConstraintViolation<T>> iterator = constraintViolations.iterator(); iterator.hasNext();) {
			ConstraintViolation<T> constraintViolation = iterator.next();
			//sbf.append(constraintViolation.getPropertyPath());
			//sbf.append(":");
			sbf.append(constraintViolation.getMessage());
			sbf.append(" ");
			//sbf.append(";");
		}
		if(sbf.length()>0){
			throw new ServiceException(ResponseCodeEnum.PARAM_VRFY_FAIL.getCode(), sbf.toString());
		}
		
	}

}
