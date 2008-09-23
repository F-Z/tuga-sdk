package com.dukitan.xletpong.entity;

import java.util.Random;

import org.tuga.framework.base.Ponto;
import org.tuga.framework.gbf.imagem.SpriteFactory;
import org.tuga.framework.gbf.imagem.sprite.SpritePersonagem;
import org.tuga.framework.gbf.kernel.graphic.ImageBuffer;
import org.tuga.framework.gbf.kernel.graphic.ImageBufferManager;
import org.tuga.framework.personagem.Personagem;
import org.tuga.middleware.input.Action;



class Velocidade
{
    public float x;
    public float y;
    
    public Velocidade()
    {
        x=0; y=0;
    }
    
    public String toString()
    {
        return "Velocidade x:"+x+" y:"+y;
    }
}

public class Bola extends Objeto
{
    
    private int batidaParede;

    private Velocidade velocidade;

    private Velocidade velocidadeGradativa;
    
    public Bola()
    {
        ImageBuffer surface = ImageBufferManager.getInstance().getImageBuffer("personagem");
        SpriteFactory factory = new SpriteFactory(surface);
        
        adicionarSpritePrincipal(factory.criarSpritePersonagem(0,0,20,20,1,1));
        getSpritePrincipal().animacao.setAutomatico(false);
        getSpritePrincipal().setQtdDirecoes(2);
        
        velocidade          = new Velocidade();
        velocidadeGradativa = new Velocidade();
    }

    public void iniciar()
    {       
        continuar();
    }

    public void iniciar(Ponto saque)
    {
        batidaParede=0;

        Random rand = new Random();
        
        velocidadeGradativa.y=4+rand.nextInt(4);
        velocidadeGradativa.x=4+rand.nextInt(4);
        
        checkarVelocidade();
        
        setPosicao(saque.x,saque.y+getDimensao().h/2);
    }

    public void continuar()
    {
        batidaParede=0;
        
        Random rand = new Random();
        
        velocidadeGradativa.y=4+rand.nextInt(4);
        velocidadeGradativa.x=6+rand.nextInt(4);
        
        checkarVelocidade();
        
        int rRand = rand.nextInt(10);
        
        if (rRand % 2==0){
            velocidade.x = - velocidade.x;
        }

        rRand = rand.nextInt(10);
        
        if (rRand % 2!=0){
            velocidade.y = - velocidade.y;
        }

        
        setPosicao((getAreaTela().right/2)-(getDimensao().w/2),getAreaTela().bottom/2-(getDimensao().h/2));
    }


    public boolean isColisao(Personagem personagem)
    {
        boolean colisao=personagem.isColisao(this);
        if (colisao){
            //soundSystem->fxManager->playPanEffect("raquete",posicao.x);
            //bateu em baixo
            if (posicao.y>=personagem.getPosicao().y+personagem.getDimensao().h-getDimensao().h){
                corrigirEixoX(personagem);
                //veio de baixo?
                if (velocidade.y<0){
                    velocidade.y = - velocidade.y;
                }
            //bateu em cima
            } else if (posicao.y<=personagem.getPosicao().y+getDimensao().h){
                corrigirEixoX(personagem);
                //veio de cima ?
                if (velocidade.y>0){
                    velocidade.y = - velocidade.y;
                }
            //bateu no meio
            } else {
                corrigirEixoX(personagem);
            }
            batidaParede++;
        }

        return colisao;
    }

    public int getVelocidade()
    {
        return (int) velocidade.x;
    }

    //Corrigir a posição da bola após colidir com uma raquete, evitando que a bola seja desenha dentro/após a raquete
    private void corrigirEixoX(Personagem personagem)
    {
        //Colisão do lado direito da tela
        if (getSpritePrincipal().getDirecao()==SpritePersonagem.Direcao.DR_DIREITA){
            if (posicao.x+getDimensao().w>=personagem.getPosicao().x){
                posicao.x=personagem.getPosicao().x-getDimensao().w;
                velocidade.x = - velocidade.x;
            }
        //Colisão do lado esquerdo da tela
        } else {
            if (posicao.x<=personagem.getPosicao().x+personagem.getDimensao().w){
                posicao.x=personagem.getPosicao().x+personagem.getDimensao().w;
                velocidade.x = - velocidade.x;
            }
        }
    }

    private void elevarGrauDificuldade()
    {
        Random rand = new Random();        
        velocidadeGradativa.y+=1+rand.nextInt(3);
        velocidadeGradativa.x+=1+rand.nextInt(2);

        checkarVelocidade();
    }
    
    private void checkarVelocidade()
    {
        if (velocidadeGradativa.y>=getDimensao().w*0.9){
            velocidadeGradativa.y=(float) (getDimensao().w*0.9);
        }
        if (velocidadeGradativa.x>=getDimensao().w*0.9){
           velocidadeGradativa.x=(float) (getDimensao().w*0.9);
        }  
 
        if (velocidade.y>0){
            velocidade.y=velocidadeGradativa.y;
        } else {
            velocidade.y= - velocidadeGradativa.y;
        }

        if (velocidade.x>0){
            velocidade.x=velocidadeGradativa.x;
        } else {
            velocidade.x= - velocidadeGradativa.x;
        }
    }
    
	public void acao(Action action)
	{
	    posicao.x+=(int)velocidade.x;
	    posicao.y+=(int)velocidade.y;

	    if (posicao.y+getDimensao().h>=getAreaTela().bottom){
	        velocidade.y = - velocidade.y;
	        posicao.y=getAreaTela().bottom-getDimensao().h;
	        //soundSystem->fxManager->playPanEffect("ping",posicao.x);
	        batidaParede++;
	    } else if (posicao.y<=getAreaTela().top){
	        velocidade.y = - velocidade.y;
	        posicao.y=getAreaTela().top;
	        //soundSystem->fxManager->playPanEffect("ping",posicao.x);
	        batidaParede++;
	    }

	    if (posicao.x>=getAreaTela().right/2){
	        getSpritePrincipal().setDirecao(SpritePersonagem.Direcao.DR_DIREITA);
	    } else {
	        getSpritePrincipal().setDirecao(SpritePersonagem.Direcao.DR_ESQUERDA);
	    }

	    if (batidaParede>5){
	        elevarGrauDificuldade();
	        batidaParede=0;
	    }
	}

}
