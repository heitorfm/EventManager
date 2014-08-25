package br.com.porquesim.eventmanager;

import java.util.EventObject;

public class Event extends EventObject  {

	private static final long serialVersionUID = 1L;

	public Event(Object source) {
		super(source);
	}

	public void setSource(Object source) {
		this.source = source;
	}

}
