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

import org.tuga.middleware.base.Rect;

/**
 * 
 * @author david
 */
public class Dimensao
{
    public int w;
    public int h;
    
    public Dimensao()
    {
        w = 0;
        h = 0;
    }
    
	public Dimensao(int w, int h)
    {
        this.w = w;
        this.h = h;
    }
	
	public Dimensao(Rect rect)
	{
	    w=rect.w;
	    h=rect.h;
	}

    public Dimensao(Dimensao dimensao)
    {
        this.w = dimensao.w;
        this.h = dimensao.h;
    }
    
	public Dimensao clonar()
	{
	    return new Dimensao(this);
	}
	
    public String toString() 
	{
		return "Dimensao w("+w+") h("+h+")";
	}
}
