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

package org.tuga.middleware.graphic.imagem.tipo;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.VolatileImage;

import org.tuga.middleware.exception.GraphicException;


public class SurfaceVolatileImage extends SurfaceConverter
{
	static public VolatileImage converter(Image img, int width, int height) throws GraphicException
	{
		VolatileImage vi = null;		
		Graphics2D gc = screen.getScreen();

		if (gc!=null){
            vi = gc.getDeviceConfiguration().createCompatibleVolatileImage(width,height);
			
			if (vi!=null){
				Graphics2D gcvi = (Graphics2D) vi.createGraphics();
				
				if (gcvi!=null) {
	        		boolean ok = false;
			    	System.out.print("[PROC] VolatileImage working ");	  
	        		
	        		do {
	        			ok = gcvi.drawImage(img, 0, 0, null);
	                    System.out.print(".");
	        		} while (!ok);
	        		gcvi.dispose();
	        		
	        		if (!ok){
		    			throw new GraphicException("Falha na conversão para 'VolatileImage'");
	        		}
	        	} else {
					throw new GraphicException("Falha na criação de 'Graphics'");        		
	        	}
			} else {
				throw new GraphicException("Falha na criação de 'VolatileImage'");
			}
			gc.dispose();
		} else {
			throw new GraphicException("Falha no acesso a 'Graphics2D'");
		}
	    
		System.out.println(" ready!");
		
		return vi;
	}
}
