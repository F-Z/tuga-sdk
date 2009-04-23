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

import java.awt.Image;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.net.URL;

public class Surface
{
	static public Image carregar(String arquivo) throws FileNotFoundException
	{	
		Image imagem = null;
			
		//Verificar se faz parte de distribuição padrão da sun
        ClassLoader classLoader = com.sun.org.apache.bcel.internal.util.ClassLoader.getSystemClassLoader();

        URL res = classLoader.getResource(arquivo);

        System.out.println("");  

        System.out.print("[LOAD] Image:"+arquivo);          
        if (res!=null){
            System.out.print(" "+res.getFile());  
            
            imagem = Toolkit.getDefaultToolkit().getImage(res);
        } else {
            throw new FileNotFoundException("File Not Exist: "+arquivo);
        }

		return imagem;

	}
}
