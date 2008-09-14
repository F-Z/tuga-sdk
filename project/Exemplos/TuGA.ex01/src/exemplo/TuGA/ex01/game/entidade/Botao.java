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

package exemplo.TuGA.ex01.game.entidade;
import java.awt.Color;
import java.awt.Graphics2D;

import org.tuga.middleware.input.Action;



/**
 * @author David de Almeida Ferreira
 * http://davidferreira-fz.blogspot.com
 * davidferreira.fz@gmail.com
 *
 */
public class Botao
{

	private int x;
	private int y;
	private Color[] cor;
	private boolean[] acionado; 
	
		
	public Botao(int x, int y)
	{
		this.x = x - 90;
		this.y = y;
		
		cor = new Color[4];
		
		cor[0] = Color.red;
		cor[1] = Color.green;
		cor[2] = Color.yellow;
		cor[3] = Color.blue;
		
		acionado = new boolean[4];
	}
	
    
	public void acao(Action action)
	{
		if (action.isBotaoA()){
			acionado[0] = true;
		} else {
			acionado[0] = false;
		}
		
		if (action.isBotaoB()){
			acionado[1] = true;
		} else {
			acionado[1] = false;
		}
		
		if (action.isBotaoC()){
			acionado[2] = true;
		} else {
			acionado[2] = false;
		}
		
		if (action.isBotaoD()){
			acionado[3] = true;
		} else {
			acionado[3] = false;
		}	
	}
	
    public void desenhar(Graphics2D graphics)
    {      

		graphics.setColor(Color.black);
		
		graphics.fillRect(x-10, y-10, 180, 40);
    	for (int i=0; i<4; i++){
    		graphics.setColor(cor[i]);
    		graphics.fillRect(x+(i*40), y, 40, 20);
    		if (acionado[i]){
    			graphics.setColor(Color.black);        
    			graphics.fillRect(x+(i*40)+10, y+10, 20, 6);
    		}
    	}
    }

    
}
