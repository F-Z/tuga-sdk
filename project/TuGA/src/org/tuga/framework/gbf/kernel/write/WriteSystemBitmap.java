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

package org.tuga.framework.gbf.kernel.write;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.tuga.framework.base.Dimensao;
import org.tuga.framework.gbf.kernel.graphic.Image;
import org.tuga.middleware.exception.TuGAInfoException;
import org.tuga.middleware.file.LoadImage;


public class WriteSystemBitmap extends Image
{
    private char largura[];

    private Dimensao dimensaoPadrao;

    private Dimensao dimensaoQuadro;

/**
 * Construtor
 */
    WriteSystemBitmap()
    {
        largura = new char[256];
        dimensaoPadrao = new Dimensao();
        dimensaoQuadro = new Dimensao();
    }
/**
 * Define altura e largura padrão baseada na maior altura e largura utilizada.
 */
    private void checkar()
    {
        int maior=0;
        for (int i=0; i<256; i++){
            if (largura[i]>maior){
                maior=largura[i];
            }
        }

        dimensaoPadrao.w=maior;
        dimensaoPadrao.h=dimensaoQuadro.h;
    }    
/**
 * Configura altura e largura padrão para todas as letras.
 * Obs.: A altura e largura devem ser suficientes para evitar que a letra seja desenhada cortada
 * @param largura
 * @param altura
 */
    public void setDimensao(int largura, int altura)
    {
        for (int i=0;i<256;i++){
            this.largura[i] = (char) largura;
        }

        dimensaoPadrao.w=largura;
        dimensaoPadrao.h=altura;
    }
/**
 * Retorna a largura padrão
 * @return
 */
    public int getLargura()
    {
        return dimensaoPadrao.w;
    }
/**
 * Retorna a altura padrão
 * @return
 */
    public int getAltura()
    {
        return dimensaoPadrao.h; 
    }
/**
 * Retorna a dimensão (altura e largura) padrão
 * @return
 */
    public Dimensao getDimensao()
    {
        return dimensaoPadrao;
    }
/**
 * Desenha o texto na tela
 * @param PALAVRA
 * @param X
 * @param Y
 */
    protected void escrever(String PALAVRA, int X, int Y)
    {
        int i,t=PALAVRA.length();
        int l;
        posicao.x=X;
        posicao.y=Y;

        
        for (i=0; i<t; i++){
            l=PALAVRA.charAt(i);

            tamanho.x=(l%16)*dimensaoQuadro.w; tamanho.w=largura[l];
            tamanho.y=(l/16)*dimensaoQuadro.h; tamanho.h=dimensaoQuadro.h; 
     
            screen.blitSurface(imagem, tamanho, posicao);
            posicao.y=Y;
            posicao.x=posicao.x+largura[l];
        }
    }

/**
 * Retorna em pixel o tamanho da linha
 * @return
 */
    protected int getLarguraLinha(String palavra)
    {
        int tamanhoPixel=0;
        int t=palavra.length();
        char l;

        for (int i=0; i<t; i++){
            l=palavra.charAt(i);
            tamanhoPixel=tamanhoPixel+largura[l];
        }

        return tamanhoPixel;
    }
    //Carrega arquivo de imagem contendo o desenho das letras.
    //Obs.: Se a imagem tiver arquivo de dimensão das letras este será carregado, caso contrario será utilizado um valor default tanto para largura como altura
    public boolean carregar(String arquivo, int width, int height) throws TuGAInfoException
    {
        LoadImage.setColorKey(false);
        LoadImage.setBuffered(true);
        
		imagem = LoadImage.carregar(arquivo,width,height);

        String txt="";

        if (imagem!=null){

            txt=arquivo.substring(0, arquivo.length()-4);
            txt+=".dat";
            
            Reader arquivoMetricas;
            try {
                arquivoMetricas = new FileReader(txt);
                BufferedReader in = null;
                in = new BufferedReader(arquivoMetricas);
                
                try {
                    in.read(largura);
                } finally {
                    try {
                        in.close();
                    } catch (IOException e) {
                    }
                }
            } catch (Exception e) {
                for (int l=0;l<256;l++){
                    largura[l] = (char) (imagem.getWidth(null)/16);
                }
            }

            dimensaoQuadro.w=imagem.getWidth(null)/16;
            dimensaoQuadro.h=imagem.getHeight(null)/16;

            checkar();
            return true;
        } else {
            return false;
        }
    }

}
