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

public class TypeDelay
{
    public float acao;

    public float tiroA;

    public float tiroB;

    public float tiroC;

    public float tiroD;
    
    public TypeDelay()
    {
        this.acao = 0;
        this.tiroA = 0;
        this.tiroB = 0;
        this.tiroC = 0;
        this.tiroD = 0;
    }  
    
    public TypeDelay(TypeDelay typeDelay)
    {
        this.acao  = typeDelay.acao;
        this.tiroA = typeDelay.tiroA;
        this.tiroB = typeDelay.tiroB;
        this.tiroC = typeDelay.tiroC;
        this.tiroD = typeDelay.tiroD;
    }

    public TypeDelay clonar()
    {
        return new TypeDelay(this);
    }
}
