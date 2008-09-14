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

import org.tuga.middleware.base.Rect;




/**
 *
 * @author David de Almeida Ferreira
 * http://davidferreira-fz.blogspot.com
 * davidferreira.fz@gmail.com
 */
public class Cenario
{

	private Rect area;
	private Color cor;

	public Cenario(Rect area)
	{
		this.area=area;
		cor=Color.YELLOW;
	} 
	
	public void setColor(Color cor)
	{
		this.cor=cor;
	}
	
	public void desenhar(Graphics2D graphics)
	{
        graphics.setColor(cor);
        graphics.fillRect(area.x, area.y, area.w, area.h);
	}

}
