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
package org.tuga.middleware.graphic.imagem;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;

public class SurfaceColorKey extends RGBImageFilter
{
	protected int substituirCor;

	public void setCor(Color corTransparente)
	{
		substituirCor = corTransparente.getRGB() | 0xFF000000;
	}
	
    final public int filterRGB(int x, int y, int rgb)
    {
    	if (( rgb | 0xFF000000 ) == substituirCor){
    		return 0x00FFFFFF & rgb;
    	} else {
    		return rgb;
    	}
    }
    
	static public Image converter(Image imagem,Color corTransparente)
	{
		SurfaceColorKey corFiltrada  = new SurfaceColorKey();
		corFiltrada.setCor(corTransparente);
		
	    ImageProducer imageProducer = new FilteredImageSource(imagem.getSource(), corFiltrada);
	    Image imagemTransparente = Toolkit.getDefaultToolkit().createImage(imageProducer);
	    
	    imagem.flush();
	    imagem = null;
	    
	    return imagemTransparente;
	}
}
