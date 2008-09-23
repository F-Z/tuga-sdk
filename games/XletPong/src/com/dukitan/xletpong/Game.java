package com.dukitan.xletpong;
import java.awt.Container;

import org.tuga.framework.base.Dimensao;
import org.tuga.framework.base.Ponto;
import org.tuga.framework.gam.GameAbstractPC;
import org.tuga.framework.gbf.imagem.SpriteFactory;
import org.tuga.framework.gbf.imagem.layer.FrameLayer;
import org.tuga.framework.gbf.imagem.layer.LayerManager;
import org.tuga.framework.gbf.kernel.graphic.ImageBuffer;
import org.tuga.framework.gbf.kernel.graphic.ImageBufferManager;
import org.tuga.middleware.input.Action;

import com.dukitan.xletpong.controller.Controle;



/**
 * 
 * @author David de Almeida Ferreira
 * http://davidferreira-fz.blogspot.com
 * davidferreira.fz@gmail.com
 *
 */
public class Game extends GameAbstractPC
{
    private Controle controle;
	
    private static final String FONT_KILLOTON    = "data/fonte/kiloton.png";
    private static final String FONT_KILLOTON_18 = "data/fonte/kiloton_18.png";
    private static final String SPRITES          = "data/imagem/sprites.png";
    private static final String BACKGROUND       = "data/imagem/background.png";
    
	public Game(Container container)
	{
		super(container);	
	}
	
	protected boolean inicializarGame()
	{
		boolean resultado = true;
	    
        resultado=imageBufferManager.carregar("personagem",SPRITES,128,128);
        imageBufferManager.carregar("background",BACKGROUND,640,480);

        writeSystem.carregar("menu" ,FONT_KILLOTON, 416, 432);
        writeSystem.carregar("texto",FONT_KILLOTON_18, 288, 304);        
        
        
        ImageBuffer surface = ImageBufferManager.getInstance().getImageBuffer("background");        

        SpriteFactory spriteFactory = new SpriteFactory(surface);
        FrameLayer background = spriteFactory.criarFrameLayer(0, 0,640,480);
        
        Dimensao d = getDimensao();
        
        Ponto p = new Ponto();
        
        p.x = (d.w - 640) > 0 ? (d.w - 640) / 2 : 0;
        p.y = (d.h - 480) > 0 ? (d.h - 480) / 2 : 0;
        
        background.setFrame(p.x,0,640,480);
        background.setTiles(1,1);
        background.setPixelTile(640,480);
        background.iniciarRandomico(1);
        LayerManager.getInstance().adicionar("background",background);        
        
        controle = new Controle();
        
        controle.iniciar();
        controle.iniciarSet();
        
		return resultado;
	}	

    protected void executarGame(Action action)
    {
        controle.executar(action);
    }

    protected void finalizarGame()
    {

    }


}
