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

import org.tuga.framework.base.Ponto;

public class LayerCamera
{
    private LayerMundo mundo;
    private Ponto ponto;

    LayerCamera()
    {
        ponto = new Ponto();
        //atributo mundo é passado pela classe FrameLayer
        setTop();        
    }

    //Retorna a Posição Atual da Camera 
    public Ponto getPosicao() 
    {
        return ponto;
    }
    
    //Verifica se a Camera está no limite inferior do mapa 
    public boolean isBottom() 
    {
        if (ponto.y>=(mundo.getTilesVertical() * mundo.getPixelTileVertical() - mundo.getPixelVisivelVertical())){
            return true;
        } else {
            return false;
        }
    }
    //Verifica se a Camera está no limite esquerdo do mapa 
    public boolean isLeft() 
    {
        if (ponto.x<=0){
            return true;
        } else {
            return false;
        }
    }
    //Verifica se a Camera está no limite direito do mapa 
    public boolean isRight() 
    {
        if (ponto.x>=(mundo.getTilesHorizontal() * mundo.getPixelTileHorizontal() - mundo.getPixelVisivelVertical())){
            return true;
        } else {
            return false;
        }
    }
    //Verifica se a Camera está no limite superior do mapa
    public boolean isTop() 
    {
        if (ponto.y<=0){
            return true;
        } else {
            return false;
        }
    }
    //Movimenta camera para Baixo 
    public void runDown(int deslocamento) 
    {
        ponto.y+=deslocamento;
        limiteDown();
    }
    //Movimenta camera para Esquerda 
    public void runLeft(int deslocamento) 
    {
        ponto.x-=deslocamento;
        limiteLeft();
    }
    //Movimenta camera para Direita 
    public void runRight(int deslocamento) 
    {
        ponto.x+=deslocamento;
        limiteRight();
    }
    //Movimenta camera para Cima 
    public void runUp(int deslocamento) 
    {
        ponto.y-=deslocamento;
        limiteUp();
    }
    //Posiciona a Camera no Final do mapa 
    public void setBottom() 
    {
        ponto.x=0;
        ponto.y=(mundo.getTilesVertical() - mundo.getTilesHorizontal()) * (mundo.getPixelTileVertical());
    }
    public void setMundo(LayerMundo mundo) 
    {
        this.mundo=mundo;
    }
    //Posiciona a Camera em um ponto do Mapa 
    public void setPosicao(int X, int Y) 
    {
        ponto.x=X;
        ponto.y=Y;
    }
    //Posiciona a Camera no inicio do mapa 
    public void setTop() 
    {
        ponto.x=0;
        ponto.y=0;
    }
    //Mostra o posicionamento da camera no mapa 
    public void show() 
    {
    /*
        GraphicSystemGFX *gfx = GraphicSystemGFX::getInstance();
        gfx->setColor(0,255,0);
        gfx->circulo(ponto.x,ponto.y,10);
    */
    }
    //Não permite que a camera ultrapasse o limite do mapa pelo lado superior 
    protected void limiteUp() 
    {
        if (ponto.y<=0){
            ponto.y=0;
        }
    }
    //Não permite que a camera ultrapasse o limite do mapa pelo lado inferior 
    protected void limiteDown() 
    {
        int limite = (mundo.getTilesVertical() * mundo.getPixelTileVertical()) - mundo.getPixelVisivelVertical();
        if (ponto.y>=limite){
            ponto.y =limite;
        }
    }
    //Não permite que a camera ultrapasse o limite do mapa pelo lado esquerdo 
    protected void limiteLeft() 
    {
        if (ponto.x<=0){
            ponto.x=0;
        }
    }
    //Não permite que a camera ultrapasse o limite do mapa pelo lado direito 
    protected void limiteRight() 
    {
        int limite = (mundo.getTilesHorizontal() * mundo.getPixelTileHorizontal()) - mundo.getPixelVisivelHorizontal();
        if (ponto.x>=limite){
            ponto.x =limite;
        }
    }
 
}
