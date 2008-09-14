/*
 TuGA Tview is free software; you can redistribute it and/or modify        
 it under the terms of the GNU  General Public License (GPL) as
 published by the Free Software Foundation; version 2 of the
 License.
--------------------------------------------------------------------
 This file is a modification to TuGA Tview.
 
 +Note: Adding verification of file exist

 Visit: http://tuga-sdk.googlecode.com

--------------------------------------------------------------------
 TuGA Tview - Copyright (C) 2008 David Ferreira <DukItan Software>                   
 XleTView   - Copyright (C) 2003 Martin Sveden 
*/

package xjava.io;


/**
 * 
 * @author Martin Sveden 
 */
public class File extends java.io.File{
//public class File {
    
    private static String virtualRoot = "";
    private String path;

    
    public File(File parent, String child){
    	super(parent, child);
    }
    
    public File(String parent, String child){
    	super(parent, child);
    }
    
    public File(String s){        
        super(s);
        path = s;
        //System.out.println("xjava.io.File - " + s);
    }
    
    public String getAbsolutePath(){
        //return super.getAbsolutePath();
        return virtualRoot + path; 
    }
    
    public static void setVirtualRoot(String root){
        if(!root.endsWith("\\") || !root.endsWith("/")){
            root += java.io.File.separator;
        }
        File.virtualRoot = root;
    }
    
    public static String getVirtualRoot(){
        return File.virtualRoot;
    }

    /*
     * TODO: TuGA - Sobrescrevendo para verificar se existe o arquivo
     * Por: David Ferreira <davidferreira.fz@gmail.com>
     */
    public boolean exists() 
    {
    	java.io.File f = new java.io.File(getAbsolutePath());
    	
    	boolean existe = f.exists();
    	
    	f = null;
    	
    	return existe;    	
    }


}
