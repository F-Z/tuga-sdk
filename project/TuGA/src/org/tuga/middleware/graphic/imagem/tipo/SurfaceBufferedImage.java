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

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

import org.tuga.middleware.exception.GraphicException;


public class SurfaceBufferedImage extends SurfaceConverter
{
/**
 * 
 * @param img
 * @param width
 * @param height
 * @return
 * @throws GraphicException
 */
	static public BufferedImage converter(Image img, int width, int height) throws GraphicException
	{
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	
	    GraphicsDevice gs = ge.getDefaultScreenDevice();
	
	    GraphicsConfiguration gc = gs.getDefaultConfiguration();
	
	    BufferedImage bufferedImage = gc.createCompatibleImage(width, height, Transparency.BITMASK);
	
	    if ((img!=null)&&(bufferedImage!=null)){
	    	Graphics g = bufferedImage.createGraphics();
	    	
	    	if (g!=null){
	    		boolean ok = false;
		    	System.out.print("[PROC] BufferedImage working ");	   
		    	
	    		do {
	    			ok = g.drawImage(img, 0, 0, null);
	    			System.out.print(".");
	    		} while (!ok);
	    		g.dispose();
	    		
	    		if (!ok){
	    			throw new GraphicException("Falha na conversão para 'BufferedImage'");
	    		}
	    		
				g.dispose();        		
	    	} else {
				throw new GraphicException("Falha na criação de 'Graphics'");        		
	    	}
	    } else {
			throw new GraphicException("Falha na criação de 'BufferedImage'");
	    }
	    
	    img.flush();
	    img = null;
	    
	    System.out.println(" ready!");
	    
		return bufferedImage;
	}

	/**
	 * 
	 * @param img
	 * @return
	 * @deprecated
	 */
	static public BufferedImage converter1(Image img, int width, int height)
	{
		System.out.println("loadBufferedImage-image:"+img.getWidth(null));
	
		BufferedImage bufferedImage = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
		bufferedImage.createGraphics().drawImage(img, 0, 0, null);
						
		return bufferedImage;
	}
}
