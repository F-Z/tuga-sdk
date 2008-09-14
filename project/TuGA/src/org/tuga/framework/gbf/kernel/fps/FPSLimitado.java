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
public class FPSLimitado extends FPSContador
{
	private int FRAME_MAXIMO = 30;
	private long tempoInicial = 0;
	private float tempoPassado = 0;
	
	FPSLimitado(){}
	
	public void iniciar()
	{
		tempoInicial = getTicks();
	}
	
	public void atualizar()
	{
		
	    while( (getTicks() - tempoInicial) < 1000 / FRAME_MAXIMO )
	    {
	        //wait...
	        //se tempo atual menos o tempo inicial for menor que
	        // 1000/60, faz com que entre em espera
	        //até que se complete o tempo
	    }

	    iFPS = FRAME_MAXIMO;
	    tempoPassado = tempoInicial;
	    tempoInicial = getTicks();	
	    tempoPassado = tempoInicial - tempoPassado;
	}
	
	public void setLimite(int limite)
	{
	    if (limite>0){
	        FRAME_MAXIMO = limite;
	    }
	}
	
	public float getSegundos() 
	{
		return tempoPassado;
	}
	
	public int getFPS() 
	{
		return FRAME_MAXIMO;
	}
}
