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

package org.tuga.framework.gbf.imagem.sprite;


public class SpriteItem extends Sprite
{
	//Construtor 
	public SpriteItem() 
	{
	    animacao.setAutomatico(false);
	}

	public void desenhar(int x, int y) 
	{
	    animacao.ajustarCorte(0,tamanho.w);

	    super.desenhar(x,y);
	}
	
	//Informa o quadro de animação
	public void setFrame(int quadro) 
	{
	    animacao.setFrame(quadro);
	}
}
