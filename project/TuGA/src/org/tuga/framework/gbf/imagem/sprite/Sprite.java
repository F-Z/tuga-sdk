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

import org.tuga.framework.base.Dimensao;
import org.tuga.framework.base.Ponto;
import org.tuga.framework.gbf.imagem.SpriteInterface;
import org.tuga.framework.gbf.kernel.graphic.ImageBuffer;
import org.tuga.middleware.base.Rect;


public class Sprite extends SpriteInterface
{
	//Construtor
	protected Sprite() 
	{
	    animacao.setAutomatico(true);
	}
	
	public void criar(int left, int top, int largura, int altura, int quantitadeQuadro, int repeticaoQuadro, ImageBuffer imageBuffer) 
	{
	    //chama o metodo criar da classe superclasse - SpriteInterface
	    super.criar(left, top, largura, altura, imageBuffer);
	    //configura as informações de animação
	    animacao.config(quantitadeQuadro,repeticaoQuadro);
	}
	
	public void desenhar(int x, int y) 
	{
	    posicao.x=x;
	    posicao.y=y;
	    Rect pontoCorte= new Rect(tamanho);
    
	    pontoCorte.x+=animacao.getDimensaoFrame().x;

//	    System.out.println("Sprite.desenhar()->pontoCorte:"+pontoCorte);
	    imageBuffer.desenhar(posicao,pontoCorte,animacao.processar());    
	}
	
	//Desenha o sprite na tela, com base na última posição informada ou desenhada
	public void desenhar() 
	{
	    desenhar(posicao.x,posicao.y);
	}
	
	//Retorna a dimensão do sprite 'width' e 'height' 
	public Dimensao getTamanho() 
	{
	    Dimensao dimensao = new Dimensao(tamanho);

	    return  dimensao;
	}
	
	//Colisão baseada no tamanho dos Sprites 
	public boolean isColisao(Sprite spriteColisao) 
	{
	    if ((posicao.x + tamanho.w >= spriteColisao.posicao.x)&&
	        (posicao.x <= spriteColisao.posicao.x + spriteColisao.tamanho.w)&&
	        (posicao.y + tamanho.h >= spriteColisao.posicao.y)&&
	        (posicao.y <= spriteColisao.posicao.y + spriteColisao.tamanho.h)){
	            return true;
	    } else {
	        return false;
	    }
	}
	
	//Posiciona o Sprite na tela 
	public void setPosicao(Ponto ponto) 
	{
	    setPosicao(ponto.x,ponto.y);
	}
	
	//Posiciona o Sprite na tela 
	public void setPosicao(int x, int y) 
	{
	    posicao.x=x;
	    posicao.y=y;
	}

	
}
