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

package org.tuga.framework.gbf.imagem;

import org.tuga.framework.gbf.imagem.sprite.Animacao;
import org.tuga.framework.gbf.kernel.graphic.ImageBuffer;
import org.tuga.middleware.base.Rect;


abstract public class SpriteInterface
{
    protected ImageBuffer imageBuffer;
    protected Rect posicao;
    protected Rect tamanho;
    public Animacao animacao;
    
	//Construtor
	protected SpriteInterface()
	{
		posicao  = new Rect();
		tamanho  = new Rect();
		animacao = new Animacao();
	}
   
	public void criar(int left, int top, int largura, int altura, ImageBuffer imageBuffer) 
	{
	    tamanho.x = left;
	    tamanho.y = top;
		tamanho.w = largura;
	    tamanho.h = altura;

	    this.imageBuffer = imageBuffer;
	    
	    System.out.println("SpriteInterface.criar()->tamanho:"+tamanho);
	    animacao.setDimensaoFrame(tamanho.clonar());
	}	
}
