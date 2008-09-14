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

package org.tuga.framework.gbf.imagem.layer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.tuga.framework.base.InterfaceManager;


public class LayerManager extends InterfaceManager
{
    protected static LayerManager instance;
    
    private HashMap table;  
    
    /**
     * Construtor
     */
    protected LayerManager()
    {
        System.out.println("* FrameLayerManager *");       
        table = new HashMap();
    }

    /**
     * Retorna uma instancia de FrameLayerManager
     * @return
     */
    public static LayerManager getInstance()
    {
        if (instance==null){
            instance = new LayerManager();
        }
        return instance;
    }

    /**
     * Retorna FrameLayer para manipulação
     * @param nome
     * @return
     */ 
    public FrameLayer getFrameLayer(String nome)
    {
        if (table.containsKey(nome)){
            return (FrameLayer) table.get(nome);
        } else {
            System.out.println("FrameLayer não encontrado: "+nome+" [Falhou]");
            return null;
        }
    }

    /**
     * Adiciona FrameLayer no FrameLayerManager 
     * @param nome
     * @param frameLayer
     */
    public void adicionar(String nome, FrameLayer frameLayer)
    {
        if ((nome!=null)&&(!nome.equalsIgnoreCase(""))&&(frameLayer!=null)){
            table.put(nome,frameLayer);
        } else {
            System.out.println("Adicionando FrameLayer "+nome+" [Falhou]");
        }
    }

    public void finalizar()
    {
        System.out.println("# FrameLayerManager.finalizar()");
        Set e = table.keySet();

        while (!e.isEmpty()){
            Iterator i = e.iterator();
            String nome = i.next().toString();
            remover(nome);
            
            e = table.keySet();
        }

        table.clear();
    }
    
    public void remover(String nome)
    {
        if (table.containsKey(nome)) {      
            //FrameLayer frame = (FrameLayer) table.remove(nome);
            //frame.finalizar();
           // frame = null;         
        } else {
            System.out.println("FrameLayerManager.remover("+nome+"): Falhou!");
        } 
    }
    
    
}
