/**
 *
 */
package com.haili.ins.common.utils;

import com.haili.ins.common.constants.HailiInsConstant;
import com.haili.ins.common.exception.CommonServiceException;

import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


/**
 * @author new
 *
 */
public class BeanValidator {

    public static <T> void validate(T bean) throws CommonServiceException {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(bean);

        StringBuffer sbf = new StringBuffer();

        for (Iterator<ConstraintViolation<T>> iterator = constraintViolations.iterator(); iterator.hasNext(); ) {
            ConstraintViolation<T> constraintViolation = iterator.next();
            //sbf.append(constraintViolation.getPropertyPath());
            //sbf.append(":");
            sbf.append(constraintViolation.getMessage());
            sbf.append(" ");
            //sbf.append(";");
        }
        if (sbf.length() > 0) {
            throw new CommonServiceException(HailiInsConstant.PARAM_VRFY_FAIL, sbf.toString());
        }

    }

}
