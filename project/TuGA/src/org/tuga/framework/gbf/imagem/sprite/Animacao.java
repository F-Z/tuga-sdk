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

import org.tuga.middleware.base.Rect;


class Frame
{
    public int atual;
    public int total;
    
    public Frame()
    {
        atual = 0;
        total = 0;
    }
}

public class Animacao
{
    protected boolean automatico;
    protected Rect areaCorte;
    protected Frame frame;
    protected Frame repeticao;
    
    //Construtor
    public Animacao()
    {
        automatico = false;

        frame     = new Frame();
        repeticao = new Frame();
    }

    //Ajusta a area de corte do sprite - posicionamento nos frames
    public void ajustarCorte(int direcao, int largura)
    {
        areaCorte.x = (direcao) * (largura * frame.total);
    }

    //Informa a quantidade de quadros e a taxa de repetição
    public void config(int quantidade, int taxaRepeticao)
    {
        frame.total     = quantidade;
        repeticao.total = taxaRepeticao;
    }

    //Informa se animação está no fim - último frame
    public boolean isFim()
    {
        if (frame.atual==frame.total-1){
            return true;
        } else {
            return false;
        }
    }

    //Informa se animação está no inicio - primeiro frame
    public boolean isInicio()
    {
        if (frame.atual==0){
            return true;
        } else {
            return false;
        }
    }

    //Retorna a dimensão do quadro
    public Rect getDimensaoFrame()
    {
        return areaCorte;
    }

    public int processar()
    {
        if (automatico){
            animar();
        }

        return frame.atual;
    }

    //Anima o sprite de forma manual, toda chamada a esse metodo anima o personagem 
    public void processarManual()
    {
        animar();
    }

    //Define se a animação é automática ou manual 
    public void setAutomatico(boolean automatico)
    {
        this.automatico=automatico;
    }

    //Define a dimensão do quadro
    public void setDimensaoFrame(final Rect area)
    {
        areaCorte=area;
    }

    //Coloca a animação no primeiro frame
    public void setInicio()
    {
        repeticao.atual = 0;
        frame.atual     = 0;
    }

    //Informa o quadro a ser usado na animação
    public void setFrame(int quadro)
    {
        if (quadro>=0){
            if (quadro<frame.total){
                frame.atual=quadro; 
            } else {
                frame.atual=frame.total-1;
            }
        }
    }
        
    //Anima o Sprite de forma automática         
    private int animar()
    {
        repeticao.atual++;

        if (repeticao.atual>repeticao.total){
            frame.atual++;
            if (frame.atual>=frame.total){
                frame.atual = 0;
            }
            repeticao.atual = 0;
        }

        return frame.atual;
    }
}
