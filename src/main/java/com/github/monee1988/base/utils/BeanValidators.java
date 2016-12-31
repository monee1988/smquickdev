package com.github.monee1988.base.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

/**
 * hibernate验证工具类
 */
public class BeanValidators {

	/**
	 *
	 * @param validator
	 * @param object
	 * @param groups
	 * @throws ConstraintViolationException
	 */
	public static void validateWithException(Validator validator, Object object, Class<?>... groups)
			throws ConstraintViolationException {
		Set constraintViolations = validator.validate(object, groups);
		if (!constraintViolations.isEmpty()) {
			throw new ConstraintViolationException(constraintViolations);
		}
	}

	/**
	 *
	 * @param e
	 * @return
	 */
	public static List<String> extractMessage(ConstraintViolationException e) {
		return extractMessage(e.getConstraintViolations());
	}

	/**
	 *
	 * @param constraintViolations
	 * @return
	 */
	public static List<String> extractMessage(Set<? extends ConstraintViolation> constraintViolations) {
		List<String> errorMessages = new ArrayList<String>();
		for (ConstraintViolation violation : constraintViolations) {
			errorMessages.add(violation.getMessage());
		}
		return errorMessages;
	}

	/**
	 *
	 * @param e
	 * @return
	 */
	public static Map<String, String> extractPropertyAndMessage(ConstraintViolationException e) {
		return extractPropertyAndMessage(e.getConstraintViolations());
	}

	/**
	 *
	 * @param constraintViolations
	 * @return
	 */
	public static Map<String, String> extractPropertyAndMessage(Set<? extends ConstraintViolation> constraintViolations) {
		Map<String, String> errorMessages = new HashMap<String,String>();
		for (ConstraintViolation violation : constraintViolations) {
			errorMessages.put(violation.getPropertyPath().toString(), violation.getMessage());
		}
		return errorMessages;
	}

	/**
	 *
	 * @param e
	 * @return
	 */
	public static List<String> extractPropertyAndMessageAsList(ConstraintViolationException e) {
		return extractPropertyAndMessageAsList(e.getConstraintViolations(), " ");
	}

	/**
	 *
	 * @param constraintViolations
	 * @return
	 */
	public static List<String> extractPropertyAndMessageAsList(Set<? extends ConstraintViolation> constraintViolations) {
		return extractPropertyAndMessageAsList(constraintViolations, " ");
	}

	/**
	 *
	 * @param e
	 * @param separator
	 * @return
	 */
	public static List<String> extractPropertyAndMessageAsList(ConstraintViolationException e, String separator) {
		return extractPropertyAndMessageAsList(e.getConstraintViolations(), separator);
	}

	/**
	 *
	 * @param constraintViolations
	 * @param separator
	 * @return
	 */
	public static List<String> extractPropertyAndMessageAsList(Set<? extends ConstraintViolation> constraintViolations,
			String separator) {
		List<String> errorMessages = new ArrayList<String>();
		for (ConstraintViolation violation : constraintViolations) {
			errorMessages.add(violation.getPropertyPath() + separator + violation.getMessage());
		}
		return errorMessages;
	}
}