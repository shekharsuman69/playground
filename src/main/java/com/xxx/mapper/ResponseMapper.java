package com.xxx.mapper;

/**
 * Abstraction interface to process and format the response received.
 * 
 * @author Shekhar Suman
 * @version 1.0
 * @since 2017-02-03
 *
 * @param <T>
 */
public interface ResponseMapper<T> {

	/**
	 * This method takes string response and formats it in the target <T>
	 * format.
	 * 
	 * @param str
	 * @return
	 */
	T mapRespone(String str);
}
