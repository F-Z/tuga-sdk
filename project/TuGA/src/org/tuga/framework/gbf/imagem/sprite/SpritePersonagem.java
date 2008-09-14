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





public class SpritePersonagem extends Sprite
{
	public class Direcao
	{
		final public static int DR_CIMA = 0;
		final public static int DR_DIREITA =1;
		final public static int DR_BAIXO =2;
		final public static int DR_ESQUERDA =3;
		final public static int DR_CIMADIREITA =4;
		final public static int DR_BAIXODIREITA=5;
		final public static int DR_BAIXOESQUERDA=6;
		final public static int DR_CIMAESQUERDA=7;
	}	
	// Utilizar constantes definidas em SpriteDirecao
    protected int direcao;
    
    protected int qtdDirecao;

    public void setDirecao(int DIR) 
    {
        switch (qtdDirecao){
            case 8:
            case 4:
                    direcao=DIR;
                break;
            case 3:
                    switch (DIR){
                        case Direcao.DR_ESQUERDA:
                                direcao=Direcao.DR_BAIXO;
                            break;
                        case Direcao.DR_BAIXO:
                            break;
                        default:
                                direcao=DIR;
                            break;
                    }
                break;
            case 2:
                    switch (DIR){
                        case Direcao.DR_ESQUERDA:
                        case Direcao.DR_CIMA:
                                direcao=Direcao.DR_CIMA;
                            break;
                        case Direcao.DR_BAIXO:
                        case Direcao.DR_DIREITA:
                                direcao=Direcao.DR_DIREITA;
                            break;
                        default:
                                //Sem modificação da direção. Opção 'default' apenas
                                //para respeitar a recomendação do compilador
                            break;
                }
                break;
        }

        animacao.processarManual();
    }
    
    public void setQtdDirecoes(int QTD)
    {
        qtdDirecao=QTD;
    }
    
    public void desenhar(int x, int y) 
    {
        animacao.ajustarCorte(direcao,tamanho.w);

        super.desenhar(x,y);
    }
    
    //Retorna a quantidade de direções que o personagem possui 
    public int getQtdDirecoes() 
    {
        return qtdDirecao;
    }
    
    public int getDirecao() 
    {
        return direcao;
    }
    
    //Construtor
    public SpritePersonagem() 
    {
        direcao=Direcao.DR_CIMA;
        qtdDirecao=4;
    }

}
