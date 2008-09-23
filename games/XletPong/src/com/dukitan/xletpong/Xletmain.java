package com.dukitan.xletpong;

import java.awt.BorderLayout;
import java.awt.Container;
import java.io.File;

import javax.tv.graphics.TVContainer;
import javax.tv.xlet.Xlet;
import javax.tv.xlet.XletContext;
import javax.tv.xlet.XletStateChangeException;

import org.tuga.framework.gam.GameAbstractPC;
import org.tuga.middleware.exception.GraphicException;




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
