package com.dukitan.xletpong.entity.raquete;

import java.util.Random;

import org.tuga.framework.base.Ponto;
import org.tuga.framework.gbf.imagem.SpriteFactory;
import org.tuga.framework.gbf.kernel.graphic.ImageBuffer;
import org.tuga.framework.gbf.kernel.graphic.ImageBufferManager;
import org.tuga.middleware.input.Action;



public class Jogador extends Raquete
{

    public Jogador() 
    {
        ImageBuffer surface = ImageBufferManager.getInstance().getImageBuffer("personagem");
        SpriteFactory factory = new SpriteFactory(surface);

        adicionarSpritePrincipal(factory.criarSpritePersonagem(0,21,14,80,1,1));
    }
    
    public Ponto saque()
    {
        Ponto saque = new Ponto();
        Random rand = new Random();
        
        saque.x=posicao.x-getVisaoBola().getDimensao().w;
        saque.y=posicao.y+rand.nextInt(getDimensao().h-getVisaoBola().getDimensao().h);
        
        return saque;
    }

    public void acao(final Action action)
    {
        if (action.isCima()){
            subir();
        } else if (action.isBaixo()){
            descer();
        }
    }
}
