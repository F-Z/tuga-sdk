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

package org.tuga.framework.gbf.kernel.graphic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.tuga.framework.base.InterfaceManager;



public class ImageBufferManager extends InterfaceManager
{
	private static ImageBufferManager instance;
	private HashMap table;	

	private ImageBufferManager()
	{
    	System.out.print("[_GBF] ImageBufferManager");		
		table = new HashMap();
		System.out.println(" ready!");
	}
	
	public static ImageBufferManager getInstance()
	{
		if (instance==null){
			instance = new ImageBufferManager();
		}
		return instance;
	}

    // Retorna GraphicSystemImageBufferManager para manipulação
    public ImageBuffer getImageBuffer(String nome)
    {
		if (table.containsKey(nome)) {		
			return (ImageBuffer) table.get(nome);
		} else {
			System.out.println("ImageBufferManager.getImageBuffer("+nome+"):Falha");
			return null;
		}
    }

    // Carregar ImageBuffer para o ImageBufferManager 
    public boolean carregar(String nome, String arquivo, int width, int height)
    {
    	boolean sucesso = false;
    	
    	ImageBuffer surface = new ImageBuffer();

		sucesso = surface.carregar(getPathBase()+arquivo,width,height);
		
		if (sucesso){
			table.put(nome,surface);
		}
		
		return sucesso;
    }
   
    public void remover(String nome)
    {
        System.out.print("[DELE] Image: '"+nome+"'");
		if (table.containsKey(nome)) {		
			ImageBuffer surface = (ImageBuffer) table.remove(nome);
			surface.finalizar();
			surface = null; 		
            System.out.println(" [Ok]");
    	} else {
    		System.out.println("[ERRO]");
    	}
    }
    
    public void finalizar()
    {
    	System.out.println("[_GBF]-> ImageBufferManager - size:"+table.size());
    	Set e = table.keySet();

    	while (!e.isEmpty()){
    	    Iterator i = e.iterator();
    	    String nome = i.next().toString();
   	        remover(nome);
   	        
            e = table.keySet();
    	}

    	table.clear();
        System.out.println("[_GBF]<- ImageBufferManager");
    }
}