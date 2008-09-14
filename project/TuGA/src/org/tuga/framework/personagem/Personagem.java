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

package org.tuga.framework.personagem;

import java.util.HashMap;

import org.tuga.framework.base.Dimensao;
import org.tuga.framework.base.Ponto;
import org.tuga.framework.base.TypeDelay;
import org.tuga.framework.gbf.imagem.sprite.Sprite;
import org.tuga.framework.gbf.imagem.sprite.SpritePersonagem;
import org.tuga.middleware.input.Action;



public abstract class Personagem
{
	protected boolean ativo;

	protected boolean vivo;

	protected int ID;

	private HashMap sprites;		

    protected TypeDelay delay;

	protected Ponto posicao;
	
    private static  int IDcontagem;

	//private Dimensao dimensao;
	

	//Construtor 
	protected Personagem() 
	{
		sprites  = new HashMap();
	    vivo     = true;
	    ativo    = true;
	    delay    = new TypeDelay();
	    posicao  = new Ponto();
	  //  dimensao = new Dimensao();
	    
	    
	    IDcontagem++;
	    ID=IDcontagem;
	}
	
	public void desenhar() 
	{
		Sprite sprite = (Sprite) sprites.get("principal");
		sprite.desenhar(posicao.x,posicao.y);
	}
	
	public void setAtivo(boolean valor) 
	{
	    ativo=valor;
	}
	
	public void setVivo(boolean valor) 
	{
	    vivo=valor;
	}
	
	public boolean isAtivo() 
	{
	    return ativo;
	}
	
	public boolean isVivo() 
	{
	    return vivo;
	}
	
	public boolean isColisao(Personagem personagem) 
	{
		Sprite sprite      = (Sprite) sprites.get("principal");
		Sprite spriteOutro = (Sprite) personagem.sprites.get("principal");
	
		return sprite.isColisao(spriteOutro);
	}
	
	public void setPosicao(int x, int y) 
	{
	    posicao.x=x;
	    posicao.y=y;

	    Sprite sprite = (Sprite) sprites.get("principal");
	    
	    if (sprite!=null){
	        sprite.setPosicao(posicao.x,posicao.y);
	    }
	}
	
	public void setPosicao(Ponto ponto) 
	{
	    setPosicao(ponto.x,ponto.y);
	}
	
	public Ponto getPosicao() 
	{
	    return posicao;
	}
	
	//Adiciona o sprite principal do personagem
	public boolean adicionarSpritePrincipal(SpritePersonagem sprite) 
	{
	    return adicionarSprite(sprite,"principal");
	}
	
	//Adiciona o sprites extras do personagem
	public boolean adicionarSprite(SpritePersonagem sprite, String nome) 
	{
	    if (sprite!=null){
	        sprite.animacao.setAutomatico(true);
	        sprites.put(nome,sprite);
	        
	        return true;
	    } else {
	        return false;
	    }
	}
	
	public Dimensao getDimensao() 
	{
		Sprite sprite = (SpritePersonagem) sprites.get("principal");
		return sprite.getTamanho();
	}
	
	public SpritePersonagem getSpritePrincipal() 
	{
	    return (SpritePersonagem) sprites.get("principal");
	}
	
	public SpritePersonagem getSprite(String nome) 
	{
	    return (SpritePersonagem) sprites.get(nome);
	}
	
    public abstract void acao(final Action action);

}
