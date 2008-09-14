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

package org.tuga.framework.gbf.kernel.graphic;

import org.tuga.middleware.base.Rect;
import org.tuga.middleware.exception.TuGAException;
import org.tuga.middleware.graphic.Screen;


public abstract class Image
{
	protected java.awt.Image imagem;
	protected Rect tamanho;
	protected Rect posicao;

	protected static Screen screen;
	protected static double time;
	
	public Image() 
	{
		imagem=null;
		posicao = new Rect();
		tamanho = new Rect();
	}

	public double getTimer() 
	{
	    return time;
	}
	
	public Rect getTamanho() 
	{
	    return tamanho;
	}
	
	public void finalizar()
	{
		imagem.flush();
		imagem = null;
	}
	
    public abstract boolean carregar(String arquivo,int width, int height) throws TuGAException;
    
//friend
	static public void setScreen(Screen screen)
	{
		Image.screen = screen;
	}
//friend
	static public void setTimer(double TEMPO)
	{
	    time=TEMPO;
	}
	
}
