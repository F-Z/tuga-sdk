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

package org.tuga.middleware.base;



public class Rect
{
	public int x;
	public int y;
	public int w;
	public int h;

	public Rect()
	{
        x = 0;
        y = 0;
        w = 0;
        h = 0;
	}

	public Rect(int x, int y, int w, int h)
    {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
	
	public Rect(Rect rect)
	{
        x = rect.x;
        y = rect.y;
        w = rect.w;
        h = rect.h;	    
	}

	public Rect clonar()
	{
	    return new Rect(this);
	}
	
    public String toString() 
	{
		return "Rect x("+x+") y("+y+") w("+w+") h("+h+")";
	}
}
