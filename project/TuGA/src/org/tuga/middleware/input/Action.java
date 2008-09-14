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

import java.awt.event.KeyEvent;



public class Action implements Teclas
{
    protected boolean[] keys;	
    
    
	Action()
	{
		 keys = new boolean[1024];
	}
	
	void keyPressed(KeyEvent e)
	{
		 keys[e.getKeyCode()] = true;
	}
	
	void keyReleased(KeyEvent e)
	{
		keys[e.getKeyCode()] = false;
	}
	
	
	public boolean isKeyPressed(int keyCode)
	{
       	return keys[keyCode];	
	}

	public boolean isEsquerda()
	{
		return isKeyPressed(KeyEvent.VK_LEFT);		
	}		
	
	public boolean isDireita()
	{
		return isKeyPressed(KeyEvent.VK_RIGHT);		
	}	
	
	public boolean isCima()
	{
		return isKeyPressed(KeyEvent.VK_UP);
	}

	public boolean isBaixo()
	{
		return isKeyPressed(KeyEvent.VK_DOWN);
	}			
	
	public boolean isBotaoA()
	{
		return isKeyPressed(VK_COLORED_KEY_0);		
	}
	public boolean isBotaoB()
	{
		return isKeyPressed(VK_COLORED_KEY_1);		
	}
	public boolean isBotaoC()
	{
		return isKeyPressed(VK_COLORED_KEY_2);		
	}
	public boolean isBotaoD()
	{
		return isKeyPressed(VK_COLORED_KEY_3);		
	}	
}
