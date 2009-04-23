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

package org.tuga.framework.gbf.kernel.write;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.tuga.framework.base.InterfaceManager;
import org.tuga.middleware.exception.TuGAInfoException;


public class WriteSystemManager extends InterfaceManager
{
    private static WriteSystemManager instance;
    private HashMap table;      
    
    //Constante para representar a fonte padrão do GBF
    static public final String defaultFont = "default";
        
    private WriteSystemManager()
    {
    	System.out.print("[_GBF] WriteSystemManager");	       
        table = new HashMap();
		System.out.println(" ready!");
    }
    
    public static WriteSystemManager getInstance()
    {
        if (instance==null){
            instance = new WriteSystemManager();
        }
        return instance;
    }
    
    //Retorna uma fonte para manipulação direta
    //Obs.: Ideal para casos em que se deseja manipulações avançadas
    public WriteSystemBitmap getFonte(String nome)
    {
        if (table.containsKey(nome)) {      
            return (WriteSystemBitmap) table.get(nome);
        } else {
            System.out.println("WriteSystemManager.getFonte("+nome+"):Falha");
            return (WriteSystemBitmap) table.get(defaultFont);
        }        
    }
    
    //Carrega e adiciona uma fonte (WriteSystemBitmap)
    public boolean carregar(String nome,String arquivo, int width, int height)
    {
        boolean carregou = false;
        WriteSystemBitmap fonte = new WriteSystemBitmap();

        try {
            if (fonte.carregar(arquivo,width,height)){
                table.put(nome,fonte);
                carregou=true;
            }
        } catch (TuGAInfoException e) {
			e.printStackTrace();
		}
        
        return carregou;
    }

    //Escreve um texto na tela
    public void escrever(String fonte, int x, int y, String texto)
    {
        getFonte(fonte).escrever(texto,x,y);
    }
    
    //Retorna em Pixel o tamanho total da linha
    public int getLarguraLinha(String nome, String palavra)
    {
        if (table.containsKey(nome)){
            return getFonte(nome).getLarguraLinha(palavra);
        } else {
            return 0;
        }
    }
    
    //Remove uma fonte (WriteSystemFontBitmap)
    public void remover(String nome)
    {
        System.out.print("[DELE] Font: '"+nome+"'");
        if (table.containsKey(nome)) {      
            WriteSystemBitmap imagem = (WriteSystemBitmap) table.remove(nome);
            imagem.finalizar();
            imagem = null;  
            System.out.println(" [Ok]");
        } else {
            System.out.println("[ERRO]");
        }
    }   
    
    public void finalizar()
    {   	
    	System.out.println("[_GBF]-> WriteSystemManager - size:"+table.size());
        Set e = table.keySet();

        while (!e.isEmpty()){
            Iterator i = e.iterator();
            String nome = i.next().toString();
            remover(nome);
            
            e = table.keySet();
        }

        table.clear();
        System.out.println("[_GBF]<- WriteSystemManager");
    }
}
