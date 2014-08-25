package br.com.porquesim.eventmanager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EventManager {

	private Map<Class<? extends Event>, List<EventConfiguration>> targets = new LinkedHashMap<Class<? extends Event>, List<EventConfiguration>>();
	
	private Map<String, List<EventListener>> map = new LinkedHashMap<String, List<EventListener>>();
	
	private List<Object> sources = new ArrayList<Object>();
	
	private static EventManager INSTANCE = new EventManager();
	
	public static EventManager get() {
		return INSTANCE;
	}
	
	private EventManager() {

	}
	
	public void registerListener(Object caller) {

		Method[] methods = caller.getClass().getDeclaredMethods();
		for(Method m : methods) {
			System.out.println(m.getName() + " => " + m.isAnnotationPresent(ListenTo.class));
			if(m.isAnnotationPresent(ListenTo.class)) {
				ListenTo listener = m.getAnnotation(ListenTo.class);
				Class<? extends Event> evtClass = listener.event();
				EventConfiguration config = new EventConfiguration(caller, m); 
				if(targets.get(evtClass) == null) {
					targets.put(evtClass, new LinkedList<EventConfiguration>());
				}
				targets.get(evtClass).add(config);
			}
			
		}
		
	}
	
	public void registerListener(String event, EventListener listener) {
		
		if(map.get(event) == null) {
			map.put(event, new LinkedList<EventListener>());
		}
		map.get(event).add(listener);
	}

	public void fire(Event evt) {
		
		List<EventConfiguration> currentTargets = targets.get(evt.getClass());
		
		if(currentTargets != null) {
			try {
				for(int i = 0; i < currentTargets.size(); i++) {
					
					EventConfiguration config = currentTargets.get(i);
					config.getMethod().invoke(config.getListener(), evt);
	
				}
			
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	public void fire(String evt) {
		
		List<EventListener> currentTargets = map.get(evt);

		if(currentTargets != null) {
	
			for(int i = 0; i < currentTargets.size(); i++) {
				
				EventListener listener = currentTargets.get(i);
				listener.on(evt);

			}
		}
		
	}
	
}
