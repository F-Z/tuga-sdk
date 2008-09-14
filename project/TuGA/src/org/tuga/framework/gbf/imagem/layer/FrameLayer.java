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

import java.util.Random;

import org.tuga.framework.base.Area;
import org.tuga.framework.base.Dimensao;
import org.tuga.framework.base.Ponto;
import org.tuga.framework.gbf.imagem.SpriteInterface;
import org.tuga.middleware.base.Rect;


public class FrameLayer extends SpriteInterface
{   
    protected int[] mapa;

    protected LayerMundo mundo;

    protected Area screen_dimensao;    
    
    public LayerCamera camera;

    //Construtor
    public FrameLayer()
    {
        screen_dimensao = new Area();
        
        mundo  = new LayerMundo();
        camera = new LayerCamera();
    }

    //Retorna a area do layer relacionado com o ponto de desenho (x e y) e  o tamanho interno (w e h)
    public Area getArea()
    {
        return screen_dimensao.clonar();
    }

    //Distancia restante para finalizar Scrolling Vertical
    public int getDistanciaScrollVertical(){ return 0; }

    //Porcentagem percorrida do Scroll Horizontal
    public int getPorcentagemScrollHorizontal(){ return 0; }

    //Porcentagem percorrida do Scroll Vertical
    public int getPorcentagemScrollVertical(){ return 0; }

    //Distancia total do Scrolling Vertical
    public int getTotalScrollVertical(){ return 0; }

    //Inicializa tiles com valores do arquivo
    public void iniciarArquivo(String arquivo){}

    //Iniciar preenchendo apenas com o quadro informado
    public void iniciarCom(int quadro)
    {
        Dimensao tilesMundo   = mundo.getTiles();
        int total = tilesMundo.w*tilesMundo.h;

        for (int i=0; i<total; i++){
            mapa[i]=quadro;
        }

        camera.setMundo(mundo);
    }

    //Iniciar ordenado até o quadro informado
    public void iniciarOrdenado(int quadroMaximo)
    {
        Dimensao tilesMundo   = mundo.getTiles();
        int total = tilesMundo.w*tilesMundo.h;
        int contador=0;

        for (int i=0; i<total; i++){
            mapa[i]=contador;
            contador++;
            if (contador>quadroMaximo){
                contador=0;
            }
        }

        camera.setMundo(mundo);
    }

    //Inicializa tiles de forma aleatória
    public void iniciarRandomico(int range)
    {
        System.out.println("FrameLayer.iniciarRandomico()");
        Dimensao tilesMundo = mundo.getTiles();
        int total = tilesMundo.w*tilesMundo.h;

        Random rand = new Random();
                
        for (int i=0; i<total; i++){
            mapa[i]=rand.nextInt(range);
            System.out.println("mapa["+i+"]="+mapa[i]);
        }

        camera.setMundo(mundo);
    }

    //Informa o posicionamento da area de desenho e as suas dimensões internas
    public void setFrame(int left, int top, int largura, int altura)
    {
        screen_dimensao.top    = top;
        screen_dimensao.left   = left;
        screen_dimensao.bottom = top  + altura;
        screen_dimensao.right  = left + largura;

        mundo.setPixelVisivel(largura,altura);
    }

    //Informa o tamanho do mundo em tiles horizontais e verticais
    public void setTiles(int largura, int altura)
    {
        mundo.setTiles(largura,altura);

        mapa = new int[largura*altura];
    }

    //Informa o tamanho em pixels dos tiles usados no layer
    public void setPixelTile(int largura, int altura)
    {
        mundo.setPixelTile(largura,altura);
        
    }

    //Desenha a grade de tiles do mapa
    public void showGrade(){}
    
    //Desenha o mapa
    public void desenhar()
    {
        int i,IMG,bloco_y,bloco_x;
        int offset_x, offset_y;
        int l_max, c_max;
        //Rect corte=tamanho.clonar();
        Rect corte = new Rect();

        Ponto ponto           = camera.getPosicao();
        Dimensao pixelTile    = mundo.getPixelTile();
        Dimensao pixelVisivel = mundo.getPixelVisivel();
        Dimensao tilesMundo   = mundo.getTiles();

        // Tamanho do Bloco
        bloco_y  = ponto.y / pixelTile.h;
        bloco_x  = ponto.x / pixelTile.w;

        // Calcula o Smooth
        offset_y = ponto.y & (pixelTile.h - 1);
        offset_x = ponto.x & (pixelTile.w - 1);


        // Linhas que cabem na tela
        l_max    = (pixelVisivel.h/pixelTile.h);
        if ((pixelVisivel.h % pixelTile.h>0)||(offset_y>0)){
            l_max++;
        }
        // Checa se a linha não ultrapassa o total
        if (l_max>tilesMundo.h){   l_max=tilesMundo.h;   }

        // Colunas que cabem na tela
        c_max    = (pixelVisivel.w/pixelTile.w);
        if ((pixelVisivel.w % pixelTile.w>0)||(offset_x>0)){
            c_max++;
        }
        // Checa se a coluna não ultrapassa o total
        if (c_max>tilesMundo.w){   c_max=tilesMundo.w;   }

        // Calcula a coordenada inicial de Y
        posicao.y=screen_dimensao.top - offset_y;
               
        for (int l=0; l<l_max; l++){
            // Transforma linha em coordenada do vetor
            i=((l + bloco_y) * tilesMundo.w) + bloco_x;
            // Verifica e corta a imagem da primeira linha

            if (posicao.y<=screen_dimensao.top){
                posicao.y=screen_dimensao.top;
                corte.y=tamanho.y + offset_y;
                corte.h=tamanho.h - offset_y;
                //corte.h=corte.y+pixelTile.h - offset_y;
            // Verifica e corta a imagem da última linha
            } else if (posicao.y+pixelTile.h>screen_dimensao.bottom){
                corte.h-=(posicao.y+pixelTile.h)-(screen_dimensao.bottom);
            }

            // Calcula a coordenada inicial de X
            posicao.x=screen_dimensao.left - offset_x;
            for (int c=0; c<c_max; c++){
                // Transforma coluna em coordenada do vetor
                IMG=(i)+(c);
                // Verifica e corta a imagem da primeira coluna
                if (posicao.x<=screen_dimensao.left){
                    posicao.x=screen_dimensao.left;
                    corte.x=tamanho.x + offset_x;
                    corte.w=tamanho.w - offset_x;
                    //corte.w=corte.x+pixelTile.w - offset_x;
                // Verifica e corta a imagem da última linha
                } else if (posicao.x+pixelTile.w>screen_dimensao.right){
                    corte.w-=(posicao.x+pixelTile.w)-(screen_dimensao.right);
                }

                // Desenha a imagem na tela efetuando corte se necessário

                //System.out.println("mapa["+IMG+"]:"+mapa[IMG]);
                //System.out.println("posicao:"+posicao);
                //System.out.println("tamanho:"+tamanho);
//                System.out.println("mapa["+IMG+"]:"+mapa[IMG]);
                //System.out.println("corte:"+corte);

                imageBuffer.desenhar(posicao,tamanho,mapa[IMG],corte);
                
                // Move x para a próxima posição                
                posicao.x+=corte.w;
                // Restaura informações de corte para coluna
                corte.w=tamanho.w;
                //corte.w=pixelTile.w;//tamanho.w;
                corte.x=tamanho.x;
            }
            // Move y para a próxima posição
            posicao.y+=corte.h;
            // Restaura informações de corte para coluna
            corte.y=tamanho.y;
            corte.h=tamanho.h;
            //corte.h=pixelTile.h;//tamanho.h;
        }        
    }    
}
