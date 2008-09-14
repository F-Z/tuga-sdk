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

package org.tuga.framework.gbf.imagem.layer;

import org.tuga.framework.base.Dimensao;

public class LayerMundo
{
    private Dimensao areaVisivel;

    private Dimensao quantidadeTiles;

    private Dimensao tile;

    /**
     * Construtor com visibilidade default
     */
    LayerMundo()
    {
        areaVisivel =  new Dimensao();
        quantidadeTiles = new Dimensao();
        tile = new Dimensao();
    }


    public Dimensao getPixelTile() 
    {
        return tile.clonar();
    }
    
    public int getPixelTileHorizontal() 
    {
        return tile.w;
    }
    
    public int getPixelTileVertical() 
    {
        return tile.h;
    }
    
    public Dimensao getPixelVisivel() 
    {
        return areaVisivel.clonar();
    }
    
    public int getPixelVisivelHorizontal() 
    {
        return areaVisivel.w;
    }
    
    public int getPixelVisivelVertical() 
    {
        return areaVisivel.h;
    }
    
    public Dimensao getTiles() 
    {
        return quantidadeTiles.clonar();
    }
    
    public int getTilesHorizontal() 
    {
        return quantidadeTiles.w;
    }
    
    public int getTilesVertical() 
    {
        return quantidadeTiles.h;
    }
    
    public void setPixelTile(int largura, int altura) 
    {
        tile.w=largura;
        tile.h=altura;
    }
    
    public void setPixelVisivel(int largura, int altura) 
    {
        areaVisivel.w=largura;
        areaVisivel.h=altura;
    }
    
    public void setTiles(int largura, int altura) 
    {
        quantidadeTiles.w=largura;
        quantidadeTiles.h=altura;
    }    
}
