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

import org.tuga.framework.gbf.imagem.layer.FrameLayer;
import org.tuga.framework.gbf.imagem.sprite.SpriteItem;
import org.tuga.framework.gbf.imagem.sprite.SpritePersonagem;
import org.tuga.framework.gbf.kernel.graphic.ImageBuffer;


public class SpriteFactory
{

	private ImageBuffer imageBuffer;

	//Construtor
	public SpriteFactory(ImageBuffer imageBuffer) 
	{
	    this.imageBuffer=imageBuffer;
	}	

	public SpritePersonagem  criarSpritePersonagem(int left, int top, int largura, int altura, int quantitadeQuadro, int repeticaoQuadro) 
	{
		SpritePersonagem sprite = new SpritePersonagem();
		sprite.criar(left,top,largura,altura,quantitadeQuadro,repeticaoQuadro,imageBuffer);

		return sprite;
	}
	
	public SpriteItem criarSpriteItem(int left, int top, int largura, int altura, int quantitadeQuadro, int repeticaoQuadro) 
	{
	    SpriteItem sprite = new SpriteItem();
	    sprite.criar(left,top,largura,altura,quantitadeQuadro,repeticaoQuadro,imageBuffer);

	    return sprite;
	}

    public FrameLayer criarFrameLayer(int left, int top, int largura, int altura) 
    {
        FrameLayer sprite =new FrameLayer();
        sprite.criar(left,top,largura,altura,imageBuffer);

        return sprite;
    }

}
