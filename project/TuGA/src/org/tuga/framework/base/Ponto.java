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
/**
 * 
 * @author david
 */
public class Ponto
{
	public int x;
	public int y;
	
	public Ponto()
	{
	    x=0;
	    y=0;
	}
	
	public Ponto(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

	public Ponto(Ponto ponto)
    {
        this.x = ponto.x;
        this.y = ponto.y;
    }

    public Ponto clonar()
	{
	    return new Ponto(this);
	}
	
    public String toString() 
	{
		return "Ponto: x("+x+") y("+y+")";
	}	
}
