package br.com.porquesim.eventmanager;

import java.lang.reflect.Method;

public class EventConfiguration {

	private Object listener;

	private Method method;


	public EventConfiguration(Object listener, Method method) {
		setListener(listener);
		setMethod(method);
	}
	
	/**
	 * @return the listener
	 */
	public Object getListener() {
		return listener;
	}

	/**
	 * @param listener
	 *            the listener to set
	 */
	public void setListener(Object listener) {
		this.listener = listener;
	}

	/**
	 * @return the methods
	 */
	public Method getMethod() {
		return method;
	}

	/**
	 * @param methods
	 *            the methods to set
	 */
	public void setMethod(Method method) {
		this.method = method;
	}

}
