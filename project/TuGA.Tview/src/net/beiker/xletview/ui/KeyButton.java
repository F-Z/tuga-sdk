/*
 TuGA Tview is free software; you can redistribute it and/or modify        
 it under the terms of the GNU  General Public License (GPL) as
 published by the Free Software Foundation; version 2 of the
 License.
--------------------------------------------------------------------
 This file is a modification to TuGA Tview.
 
 +Note: Adding emulation of KeyEvent based on the click of a mouse

 Visit: http://tuga-sdk.googlecode.com

--------------------------------------------------------------------
TuGA Tview - Copyright (C) 2008 David Ferreira <DukItan Software>                    
 XleTView   - Copyright (C) 2003 Martin Sveden 
*/


package net.beiker.xletview.ui;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;


/**
* A Button that fires key events.
*/
public class KeyButton extends Container implements MouseListener{

    private Img image;
    private KeyListener keyListener;
	private Component listenerComponent;
    private int keyCode;

    private int width;
    private int height;

    public KeyButton(URL imgUrl, int keyCode){
        image = new Img(imgUrl);
        //setSize(getPrefferedSize());
        setSize(image.getSize());
        add(image);
        this.keyCode = keyCode;
        addMouseListener(this);
    }
    
    public KeyButton(URL imgUrl, int width, int height, int keyCode){
    	image = new Img(imgUrl, width, height);
    	//setSize(getPrefferedSize());
    	setSize(image.getSize());
    	add(image);
    	this.keyCode = keyCode;
    	addMouseListener(this);
    }

    public void setNormal(){
        image.setLocation(0,0);
    }

    public void setOn(){
        image.setLocation(1,1);
    }

    public Dimension getPrefferedSize(){
        //return new Dimension(image.getWidth(), image.getHeight());
    	return new Dimension(getWidth(), getHeight());
    }

    public Dimension getMinimumSize(){
        return getPrefferedSize();
    }

    public Dimension getMaximumSize(){
        return getPrefferedSize();
    }

    public Dimension minimumSize(){
        return getPrefferedSize();
    }

    public Dimension preferredSize(){
        return getPrefferedSize();
    }

    public void addKeyListener(KeyListener keyListener){
        this.keyListener = AWTEventMulticaster.add(this.keyListener, keyListener);
    }
    
    public void setListenerComponent(Component c){
		listenerComponent = c;
    }

    private void fireKeyEvent(int eventType){
        if(keyListener != null){
            
            //KeyEvent keyEvent = new KeyEvent(this, keyCode, 0L, 0, keyCode);
            KeyEvent keyEvent = null;
            switch(eventType){
                case KeyEvent.KEY_PRESSED:
                    keyEvent = new KeyEvent(this, eventType, 0L, 0, keyCode);                    
                    keyListener.keyPressed(keyEvent);
                break;                
                case KeyEvent.KEY_RELEASED:
                    keyEvent = new KeyEvent(this, eventType, 0L, 0, keyCode);
                    keyListener.keyReleased(keyEvent);
                break;
                /*
                case KeyEvent.KEY_TYPED:
                    keyEvent = new KeyEvent(this, eventType, 0L, 0, KeyEvent.VK_UNDEFINED);
                    keyListener.keyTyped(keyEvent);
                break;
                */
            }
        }
    }

    // implementing MouseListener -->
    public void mouseClicked(MouseEvent e){
    }

    public void mousePressed(MouseEvent e)
    {
        setOn();
        
        fireKeyEvent(KeyEvent.KEY_PRESSED);
/*
 * TODO: TuGA - Adicionando emulação de KeyEvent por meio de ações do mouse
 * Por: David Ferreira <davidferreira.fz@gmail.com>
 */
    	AWTEvent event = e;
		KeyEvent ke = new KeyEvent(this,KeyEvent.KEY_PRESSED,0,0,keyCode,'0');
		EventQueue eq = new EventQueue();
		eq.postEvent(ke);               
    }

    public void mouseReleased(MouseEvent e)
    {
        setNormal();

        fireKeyEvent(KeyEvent.KEY_RELEASED);        
/*
 * TODO: TuGA - Adicionando emulação de KeyEvent por meio de ações do mouse
 * Por: David Ferreira <davidferreira.fz@gmail.com>
 */
    	AWTEvent event = e;
		KeyEvent ke = new KeyEvent(this,KeyEvent.KEY_RELEASED,0,0,keyCode,'0');
		EventQueue eq = new EventQueue();
		eq.postEvent(ke);       
    }

    public void mouseEntered(MouseEvent e){}

    public void mouseExited(MouseEvent e){}


    // implementing MouseListener //
}
