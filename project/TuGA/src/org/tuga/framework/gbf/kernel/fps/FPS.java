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

package org.tuga.framework.gbf.kernel.fps;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * 
 * @author David de Almeida Ferreira
 * http://davidferreira-fz.blogspot.com
 * davidferreira.fz@gmail.com
 */
public abstract class FPS
{
	protected int iFPS = 0;		
	
	
	FPS()
	{
		iniciar();
	}
	
	public void iniciar(){}
	
	public int getFPS()
	{
		return iFPS;
	}
	
	protected long getTicks()
	{
		return System.currentTimeMillis();
	}
	
	public float getSegundos()
	{
		return 0;
	}
	
	public void desenhar(Graphics2D graphics)
	{
        graphics.setColor(Color.LIGHT_GRAY);		
        graphics.drawString("FPS:"+iFPS, 10,20);
	}
	
	
	abstract public void atualizar();
}
