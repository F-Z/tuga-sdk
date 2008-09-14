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

package org.tuga.middleware.file;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;

import org.tuga.middleware.exception.TuGAInfoException;
import org.tuga.middleware.graphic.imagem.Surface;
import org.tuga.middleware.graphic.imagem.SurfaceColorKey;
import org.tuga.middleware.graphic.imagem.tipo.SurfaceBufferedImage;
import org.tuga.middleware.graphic.imagem.tipo.SurfaceVolatileImage;


public class LoadImage 
{

	static private boolean colorKey = false;
	static private boolean buffered = true;
	

	static public void setColorKey(boolean colorKey)
	{
		LoadImage.colorKey = colorKey;
	}

	static public void setBuffered(boolean buffered)
	{
		LoadImage.buffered = buffered;
	}

	static public Image carregar(String arquivo, int width, int height) throws TuGAInfoException
	{
		Image imagem = null;

		try {
			imagem = Surface.carregar(arquivo);
			
			System.out.println(" [Ok]");
			
	        if (colorKey){
		        imagem = SurfaceColorKey.converter(imagem,new Color(255,0,255));
	        }
		        
	        if (buffered){
				BufferedImage bImagem = SurfaceBufferedImage.converter(imagem, width, height);
				imagem.flush();
				return bImagem;
			} else {
				VolatileImage vImagem = SurfaceVolatileImage.converter(imagem, width, height);
				imagem.flush();			
				return vImagem;			
			}
		} catch (Exception e) {
			System.out.println(" [ERRO]");
			throw new TuGAInfoException(e);
		}

	}
}
