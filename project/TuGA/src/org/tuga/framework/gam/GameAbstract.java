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

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Toolkit;

import org.tuga.framework.base.Dimensao;
import org.tuga.framework.base.InterfaceManager;
import org.tuga.framework.gbf.kernel.graphic.Image;
import org.tuga.middleware.Info;
import org.tuga.middleware.exception.GraphicException;
import org.tuga.middleware.graphic.Screen;
import org.tuga.middleware.graphic.imagem.tipo.SurfaceConverter;
import org.tuga.middleware.input.EventManager;


public abstract class GameAbstract
{
    private   Container container;
    protected    Screen screen;
    private      String pathBase;
    
    public GameAbstract(Container container)
    {
    	System.out.println("");
    	System.out.println("============================================================");
        System.out.println("TuGA Game API - TuGA SDK - http://tuga-sdk.googlecode.com");
        System.out.println("====================================================[begin]=");
        
        this.container = container;
      
        
        System.out.print("[MIDD]");
		Toolkit.getDefaultToolkit().addAWTEventListener(EventManager.getInstance(), AWTEvent.KEY_EVENT_MASK);

		System.out.print("[MIDD]");		
        screen = new Screen(container.getWidth(), container.getHeight());
        container.add(screen,BorderLayout.CENTER);		
		screen.iniciar();		
    }
    
    public void setPath(String pathBase)
    {
    	this.pathBase = pathBase;
    	
    	InterfaceManager.setPathBase(pathBase);
    	
    	if (this.pathBase.equalsIgnoreCase("")){
    		//Todo: Só para remover o warning!
    	}
    }

    protected Container getContainer()
    {
        return container;
    }
    
    protected Screen getScreen()
    {
        return screen;
    }
    
    protected Dimensao getDimensao()
    {
        return new Dimensao(container.getWidth(),container.getHeight());
    }
    
    final public void inicializar()
    {
        Info.ambiente();
        
        Image.setScreen(screen);      
        SurfaceConverter.setScreen(screen);
    }
    
    final public void finalizar()
    {
    	EventManager eventManager = EventManager.getInstance();
    	//Liberando estado das teclas
    	eventManager.dispose();
    	
		Toolkit.getDefaultToolkit().removeAWTEventListener(eventManager);
		
        container.removeAll();
        container.setIgnoreRepaint(false);
        
    	System.out.println("============================================================");
        System.out.println("TuGA Game API - TuGA SDK - http://tuga-sdk.googlecode.com");
        System.out.println("======================================================[end]=");

    }
    
    abstract public void executar() throws GraphicException;    
}
