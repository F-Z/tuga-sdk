/*
	TuGA Game API SDK - Module: Examples
	Copyright (C) 2007-2008 David de Almeida Ferreira <DukItan Software>
--------------------------------------------------------------------------
	This software is free software; you can redistribute it and/or
	modify it under the terms of the GNU General Public License (GPL)
	as published by the Free Software Foundation; either
	version 2 of the License.
--------------------------------------------------------------------------	
	http://tuga-sdk.googlecode.com
*/

package exemplo.TuGA.ex01.game;
import java.awt.Color;
import java.awt.Container;
import java.awt.Rectangle;

import org.tuga.framework.gam.GameAbstractPC;
import org.tuga.middleware.base.Rect;
import org.tuga.middleware.input.Action;

import exemplo.TuGA.ex01.game.entidade.Botao;
import exemplo.TuGA.ex01.game.entidade.Cenario;



/**
 * 
 * @author David de Almeida Ferreira
 * http://davidferreira-fz.blogspot.com
 * davidferreira.fz@gmail.com
 *
 */
public class Game extends GameAbstractPC
{
    private Botao botao;
	private Cenario cenario;
	private static final String RESOURCE_TEXTO = "org/tuga/data/kernel/fonte/default.png";

	public Game(Container container)
	{
		super(container);
	}
	
	protected boolean inicializarGame()
	{
		boolean resultado = true;
		
		resultado = writeSystem.carregar("texto",RESOURCE_TEXTO, 128, 272);        

		botao = new Botao(350,250);
        
        Rectangle r = screen.getBounds();
        
        cenario = new Cenario(new Rect(r.x,r.y,r.width,r.height));
        
		return resultado;
	}	

    protected void executarGame(Action action)
    {       
    	cenario.desenhar(screen.getScreen());
        botao.desenhar(screen.getScreen());

        writeSystem.escrever("texto",  10,  10, "TuGA.ex01 - TuGA Game API SDK Example. -  http://tuga-sdk.googlecode.com");
        writeSystem.escrever("texto",  10,  30, "Exemplo de Aplicação Simples Utilizando os botões coloridos.");        
        writeSystem.escrever("texto", 100, 300, "* Para Mudar a cor de fundo acione o botão com a cor desejada.");
 
        
        if (action.isBotaoA()){
        	cenario.setColor(Color.RED);
        } else if (action.isBotaoB()){
        	cenario.setColor(Color.GREEN);        	
        } else if (action.isBotaoC()){
        	cenario.setColor(Color.YELLOW);
        } else if (action.isBotaoD()){
        	cenario.setColor(Color.BLUE);
        }	
 
    }

    protected void finalizarGame()
    {

    }


}
