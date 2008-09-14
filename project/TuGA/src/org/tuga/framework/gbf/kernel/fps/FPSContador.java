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
public class FPSContador extends FPS
{

	private final int INTERVALO_MINIMO = 5;
	private final int INTERVALO_MAXIMO = 50;
	private float iFrameTime =0;
	private long ultimoTempo =0;
	private boolean preventLowdown;
	private int iFrameCount =0;
	private long iTimeAcum = 0;
	private float fFrameTime = 0;

	FPSContador(){}
	
	public void iniciar() 
	{
		iFrameTime  = 0;
		ultimoTempo = 0;
		iFrameCount = 0;
		iTimeAcum   = 0;
		fFrameTime  = 0;
		
		preventLowdown = true;
	}
	
	public void atualizar()
	{
		iFrameTime =0;
		long tempoAtual;
			
		do {
			
			tempoAtual = getTicks();
			
			iFrameTime = (tempoAtual > ultimoTempo) ? tempoAtual - ultimoTempo : 0;
			
			ultimoTempo = (tempoAtual >= ultimoTempo)? ultimoTempo : tempoAtual;
			
		} while(!(iFrameTime >= INTERVALO_MINIMO));
		
		if ((preventLowdown) && (iFrameTime > INTERVALO_MAXIMO)){
			iFrameTime = INTERVALO_MAXIMO;
		}
		
		iTimeAcum += iFrameTime;
		iFrameCount++;
		fFrameTime =  iFrameTime * 0.001f;
		
		if (iTimeAcum >= 1000){
			iFPS = iFrameCount;
			iFrameCount = 0;
			iTimeAcum = 0;
		}
		
		ultimoTempo = tempoAtual;
	}
	
	public float getSegundos() 
	{
		return fFrameTime;
	}
}
