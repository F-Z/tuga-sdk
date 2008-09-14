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

package org.tuga.framework.base;


public abstract class InterfaceManager
{   
	
	private static String  PATH_BASE= "";
	
	
	public static void setPathBase(String pathBase)
	{
		PATH_BASE = pathBase;
	}
	
	protected String getPathBase()
	{
		return PATH_BASE;
	}
	
    /**
     * 
     * @param nome
     */
    public abstract void remover(String nome);
    
    /**
     * 
     */
    public abstract void finalizar();
}
