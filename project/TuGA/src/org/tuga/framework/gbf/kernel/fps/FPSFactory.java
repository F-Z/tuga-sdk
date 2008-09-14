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



/**
 * 
 * @author David de Almeida Ferreira
 * http://davidferreira-fz.blogspot.com
 * davidferreira.fz@gmail.com
 */
public class FPSFactory
{
    static public final int MAXIMO  = 0;
    static public final int FIXO    = 30;   
	static public final int FIXO_60 = 60;

	private int tipo = FIXO;	
	
	public void setFPS(int tipo)
	{
		if (MAXIMO>=0){
			this.tipo=tipo; 
		}
	}
	
	public FPS getFPS()
	{
		FPS timer = null;
		
		switch (tipo)
		{
			case MAXIMO:
				    timer = new FPSContador();
				break;
			
			case FIXO_60:
			case FIXO:
			default:
                    FPSLimitado tfx = new FPSLimitado();
                    tfx.setLimite(tipo);			    
				    timer = tfx;
			    break;
		}
		
		
		return timer;
	} 
	
}
