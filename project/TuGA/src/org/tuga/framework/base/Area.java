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


public class Area
{
    public int top;

    public int left;

    public int bottom;

    public int right;
    
    public Area()
    {
        left   = 0;
        top    = 0;
        right  = 0;
        bottom = 0;        
    }
    
    public Area(int left, int top, int right, int bottom)
    {
        this.left   = left;
        this.top    = top;
        this.right  = right;
        this.bottom = bottom;
    }
    
    public Area(Dimensao dimensao, Ponto ponto)
    {
        left   = ponto.x;
        top    = ponto.y;
        right  = dimensao.w;
        bottom = dimensao.h;
    }
    
    public Area(Area area)
    {
        this.left   = area.left;
        this.top    = area.top;
        this.right  = area.right;
        this.bottom = area.bottom;
    }

    public Area clonar()
    {
        return new Area(this);
    }
    
    public String toString()
    {
		return "Area left("+left+") top("+top+") right("+right+") bottom("+bottom+")";
    }
}
