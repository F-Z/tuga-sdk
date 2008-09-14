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
package org.tuga.middleware.graphic;
import java.awt.Canvas;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import org.tuga.middleware.base.Rect;


/**
 * 
 * @author David de Almeida Ferreira
 * http://davidferreira-fz.blogspot.com
 * davidferreira.fz@gmail.com
 * 
 */
public class Screen extends Canvas
{
	private static final long serialVersionUID = 1L;
	private BufferStrategy strategy;
	private Graphics2D graphics2D;
	private final Font fonte;

	

	public Screen(final int width, final int height) 
	{
		setVisible(true);
		fonte  = new Font("Tiresias", Font.BOLD, 20);
		System.out.print("@ Screen set("+width+"x"+height+")");
		
		setLocation(0, 0);	
		setSize(width,height);
	}
		
	public void iniciar()
	{	
		setIgnoreRepaint(true);
		
		createBufferStrategy(2);	
		strategy = getBufferStrategy();	

		iniciarGraphic();
		System.out.println(" ready!");		
	}
		
	public Graphics2D getScreen()
	{	
		return graphics2D;
	}
	
	public void flip()
	{
		finalizarGraphic();
		strategy.show();
		iniciarGraphic();
	}
	
	private void iniciarGraphic()
	{
		graphics2D = (Graphics2D) strategy.getDrawGraphics();
		graphics2D.clearRect(0, 0, getWidth(), getHeight());	
		graphics2D.setFont(fonte);
	}
	
	private void finalizarGraphic()
	{
		graphics2D.dispose();
	}
	
	
/**
 * Desenha uma imagem na Surface principal de Vídeo
 * @param imagem imagem a ser desenhada
 * @param area regiao da imagem a ser desenhada
 * @param posicao posicao na surface de video
 */
    public void blitSurface(java.awt.Image imagem, Rect area, final Rect posicao)
    {
//TODO: Fazer clip do que passa da tela
		posicao.w = posicao.x+area.w;
		posicao.h = posicao.y+area.h;
		
 	   	area.w = area.x+area.w;
 	   	area.h = area.y+area.h;
       	   	
		graphics2D.drawImage(imagem, 
				posicao.x, posicao.y, posicao.w, posicao.h, 
				   area.x,    area.y,    area.w,    area.h, 
				null);
		//System.out.println("Screen.blitSurface -> posicaoTela:"+posicao+" areaIMG:"+area);
    }	
}
