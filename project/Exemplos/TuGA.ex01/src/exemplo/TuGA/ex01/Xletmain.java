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

package exemplo.TuGA.ex01;
import java.awt.BorderLayout;
import java.awt.Container;
import java.io.File;

import javax.tv.graphics.TVContainer;
import javax.tv.xlet.Xlet;
import javax.tv.xlet.XletContext;
import javax.tv.xlet.XletStateChangeException;

import org.tuga.framework.gam.GameAbstractPC;
import org.tuga.middleware.exception.GraphicException;

import exemplo.TuGA.ex01.game.Game;






/**
 * Xlet
 * 
 * @author David de Almeida Ferreira
 * http://davidferreira-fz.blogspot.com
 * davidferreira.fz@gmail.com
 *
 */
public class Xletmain implements Xlet
{
	private XletContext thisContext;
	private GameAbstractPC game; 

	
	public void initXlet(XletContext ctx) throws XletStateChangeException 
	{
		thisContext = ctx;

		Container rootContainer = TVContainer.getRootContainer(thisContext);

		rootContainer.setLayout(new BorderLayout());

		rootContainer.setIgnoreRepaint(true);

		game = new Game(rootContainer);
		game.setPath(new File("").getAbsolutePath());
	}
       
	public void startXlet() throws XletStateChangeException
	{
		try {
		    game.inicializar();
			game.executar();
		} catch (GraphicException e) {
			e.printStackTrace();
		} finally {
            game.finalizar();
		}
		
	}

	public void pauseXlet() 
	{

	}

	public void destroyXlet(boolean unconditional) throws XletStateChangeException 
	{
		thisContext.notifyDestroyed();		
	}

}
