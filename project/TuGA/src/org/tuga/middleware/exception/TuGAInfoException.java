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

package org.tuga.middleware.exception;

public class TuGAInfoException extends TuGAException
{
	private static final long serialVersionUID = 1L;
	
	public TuGAInfoException(String message)
	{
		super(message);
	}

	public TuGAInfoException(Exception e) {
		super(e);
	}
}
