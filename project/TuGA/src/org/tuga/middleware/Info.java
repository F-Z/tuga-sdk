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

package org.tuga.middleware;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

public class Info
{

	static protected String deviceType(int id)
	{
		String tipo = "desconhecido";
		switch(id){
			case GraphicsDevice.TYPE_RASTER_SCREEN:
				tipo="Raster Screen";
				break;
				
			case GraphicsDevice.TYPE_PRINTER:
				tipo="Printer";
				break;
				
			case GraphicsDevice.TYPE_IMAGE_BUFFER:
				tipo="Image Buffer";
				break;
		}
		return tipo;		
	} 


	static public void ambiente()
	{
	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    GraphicsDevice gd = ge.getDefaultScreenDevice();
	    
	    System.out.println("[SYST] Device:"+deviceType(gd.getType()) + " (" +gd.getIDstring()+")");    
	}

}
