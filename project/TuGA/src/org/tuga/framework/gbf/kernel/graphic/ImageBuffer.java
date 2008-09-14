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
import org.tuga.middleware.exception.TuGAInfoException;
import org.tuga.middleware.file.LoadImage;


public class ImageBuffer extends Image
{

	public boolean carregar(String arquivo,int width, int height)
	{
		LoadImage.setColorKey(true);
		LoadImage.setBuffered(true);
		
		boolean sucesso = false;
		
		try {
			imagem = LoadImage.carregar(arquivo,width,height);

			if (imagem!=null){
				return true;
			}
		} catch (TuGAInfoException e) {
			System.err.println(e.getMessage());
		}
		
		return sucesso;
		
	}

    //Desenha um sprite simples na tela 
	public void desenhar(int x, int y)
	{
	    Rect rect = getTamanho();
	    
	    Rect posicao = new Rect();

	    posicao.x = x; posicao.y = y;
	    posicao.w = 0; posicao.h = 0;

	    screen.blitSurface(imagem,rect,posicao);
	}

    //Permite desenhar sprites animados  
	public void desenhar(Rect posicao, Rect tamanho, int frame)
	{
	    Rect rect = new Rect();

	    rect.x=(tamanho.x) + (frame * tamanho.w);
	    rect.y=tamanho.y;
	    rect.h=tamanho.h;
	    rect.w=tamanho.w;
/*
	    System.out.println("SurfaceBuffer.desenhar()->rect:"+rect + "" +
	    		"\n\r posicao:"+posicao+ 
	    		"\n\r tamanho:"+tamanho);
*/
	    screen.blitSurface(imagem,rect,posicao);
	}

    //Permite desenhar sprites animados com corte 
	public void desenhar(Rect posicao, Rect tamanho, int frame, Rect corte)
	{
	    Rect rect = new Rect();

	    rect.x=(tamanho.x) + (frame * tamanho.w);
	    rect.y=tamanho.y;
	    rect.h=tamanho.h;
	    rect.w=tamanho.w;

	    if (corte.x>0){ rect.x+= corte.x; }
	    if (corte.y>0){ rect.y = corte.y; }
	    if (corte.w>0){ rect.w = corte.w; }
	    if (corte.h>0){ rect.h = corte.h; }

	    screen.blitSurface(imagem,rect,posicao);
	}	
}
