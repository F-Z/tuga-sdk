/*
	TuGA Game API SDK
	Copyright (C) 2007-2008 David de Almeida Ferreira <DukItan Software>
--------------------------------------------------------------------------
	This library is free software; you can redistribute it and/or
	modify it under the terms of the GNU Library General Public
	License (LGPL) as published by the Free Software Foundation; either
	version 2 of the License.
--------------------------------------------------------------------------	
	http://tuga-sdk.googlecode.com
*/
package org.tuga.middleware.input;
import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;


/**
 * Manipulação de Eventos
 * 
 * @author David Ferreira <davidferreira.fz@gmail.com>
 * @author Cidcley Teixeira <cidcley@gmail.com> *
 */
public class EventManager implements AWTEventListener
{
	private static EventManager instance;
	private Action action;
	

    public static EventManager getInstance()
    {   	
    	if (instance == null) {
        	instance = new EventManager();
        }

        return instance;
    }

    private EventManager()
    {
    	action = new Action();
    	
    	System.out.println("@ EventManager ready!");
    }

	protected void keyPressed(KeyEvent e) 
	{
		action.keyPressed(e);
	}
    
	protected void keyReleased(KeyEvent e)
	{
    	KeyEvent nextPress = (KeyEvent) Toolkit.getDefaultToolkit().getSystemEventQueue().peekEvent(KeyEvent.KEY_PRESSED);
    	
    	if ((nextPress == null) || (nextPress.getWhen() != e.getWhen()) ||
    		(nextPress.getKeyCode() != e.getKeyCode())) {
    		action.keyReleased(e);
    	}  	
	}    
    
	public void eventDispatched(AWTEvent evento)
	{  	
      	if (evento.getID() == KeyEvent.KEY_PRESSED) {
        	keyPressed((KeyEvent) evento);
      	}
      	if (evento.getID() == KeyEvent.KEY_RELEASED) {
      		keyReleased((KeyEvent) evento);
      	}		
	}
	
	public Action action()
	{
		return action;
	}


}
