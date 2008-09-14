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

package org.tuga.framework.gam;
import java.awt.Container;
import java.awt.Graphics2D;

import org.tuga.framework.gbf.kernel.fps.FPS;
import org.tuga.framework.gbf.kernel.fps.FPSFactory;
import org.tuga.framework.gbf.kernel.graphic.ImageBufferManager;
import org.tuga.middleware.exception.GraphicException;
import org.tuga.middleware.input.Action;
import org.tuga.middleware.input.EventManager;



/**
 * 
 * @author David de Almeida Ferreira
 * http://davidferreira-fz.blogspot.com
 * davidferreira.fz@gmail.com
 */
abstract public class GameAbstractMobile extends GameAbstract
{
	private FPS fps;
	
	private boolean executando;
	
	protected ImageBufferManager imageBufferManager;
	
	
	
	public GameAbstractMobile(Container container)
	{    
	    super(container);

	    FPSFactory factory = new FPSFactory();
		factory.setFPS(FPSFactory.FIXO);
		fps  = factory.getFPS();
		executando = true;
	}
	
	
	public void executar() throws GraphicException 
	{
	    executando = hookInicializar();

		System.out.println("=== iTVGameBR - Jogo em Execução ===");
		
		while (executando) {
			
			hookAcao(EventManager.getInstance().action());
			
	        hookDesenhar(screen.getScreen());
			
			screen.flip();

			fps.atualizar();
		}
		
		hookFinalizar();
	}
		
	final private boolean hookInicializar()
	{
        imageBufferManager = ImageBufferManager.getInstance();
        
        System.out.println("=== iTVGameBR - Iniciando Jogo ===");    
        //SurfaceConverter.setScreen(screen);
        fps.iniciar();
        return inicializarGame();
	}
	
	final private void hookAcao(Action action)
	{
		acaoInterna(action);
		
		executarGame(action);
	}

	
    final private void hookDesenhar(Graphics2D graphics)
    {
        desenharGame(graphics);
        
        fps.desenhar(graphics);
    }
	final private void hookFinalizar()
	{	
        System.out.println("=== iTVGameBR - Saindo do Jogo ===");   
        
		imageBufferManager.finalizar();
		
		finalizarGame();
	}
		

    final private void acaoInterna(Action action)
    {
        if ((action.isKeyPressed(Action.VK_CHANNEL_UP))||
            (action.isKeyPressed(Action.VK_CHANNEL_DOWN))){
            executando = false;
        }
    }

    abstract protected boolean inicializarGame();    
    abstract protected void executarGame(Action action);
    abstract protected void desenharGame(Graphics2D graphics);    
	abstract protected void finalizarGame();
	

}
