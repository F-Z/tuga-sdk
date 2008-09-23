package com.dukitan.xletpong.entity.raquete;

import java.util.Random;

import org.tuga.framework.base.Area;
import org.tuga.framework.base.Ponto;
import org.tuga.framework.gbf.imagem.SpriteFactory;
import org.tuga.framework.gbf.kernel.graphic.ImageBuffer;
import org.tuga.framework.gbf.kernel.graphic.ImageBufferManager;
import org.tuga.framework.personagem.Personagem;
import org.tuga.middleware.input.Action;



public class CPU extends Raquete
{
    final static protected int EFEITO_SEM   = 0;
    final static protected int EFEITO_CIMA  = 1;
    final static protected int EFEITO_BAIXO = 2;
    
    final static protected int DECISAO_NADA   = 0;
    final static protected int DECISAO_SUBIR  = 1;
    final static protected int DECISAO_DESCER = 2;
    
    private int raioVisao;

    private int efeito;
    
    
    public CPU()
    {
        ImageBuffer surface = ImageBufferManager.getInstance().getImageBuffer("personagem");
        SpriteFactory factory = new SpriteFactory(surface);
        
        adicionarSpritePrincipal(factory.criarSpritePersonagem(15,21,14,80,1,1));

        iniciarVisao();
    }

    public Ponto saque()
    {
        Ponto saque = new Ponto();
        Random rand = new Random();

        saque.x=posicao.x-getVisaoBola().getDimensao().w;
        saque.y=posicao.y+rand.nextInt(getDimensao().h-getVisaoBola().getDimensao().h);
               
        return saque;
    }

    public boolean isColisao(Personagem personagem)
    {
        boolean retorno = super.isColisao(personagem);

        if (retorno){
            Random rand = new Random();            
            efeito = rand.nextInt(3);
        }

        return retorno;
    };

    //Inicia raquete
    public void iniciar()
    {
        super.iniciar();
        aumentarVisao();
    }

    //Desenha o sprite principal do personagem
    public void desenhar()
    {
        super.desenhar();
/*
        #ifdef DEBUG
            GraphicSystemGFX *gfx = GraphicSystemGFX::getInstance();

            gfx->setColor(255,255,255);
            gfx->circulo(posicao.x+getDimensao().w/2,posicao.y+getDimensao().h/2,raioVisao);

            gfx->setColor(255,0,0);
            gfx->circulo(posicao.x+getDimensao().w/2,posicao.y+getDimensao().h/2,getDimensao().h*1.4);
        #endif
*/
    }

    private void iniciarVisao()
    {
        raioVisao=260;
    }

    private void aumentarVisao()
    {
        raioVisao+=20;

        if (raioVisao>=getAreaTela().bottom){
            raioVisao=getAreaTela().bottom;
        }
    }

    private int pensar(Area visao, Area areaVisaoBola)
    {
        float qx, qy, qr, qe; //para guardar o quadrado de x, y e raio
        int decisao = DECISAO_NADA;
    
        //quadrado da distância em x
        qx = (float) Math.pow(((areaVisaoBola.left + areaVisaoBola.right/2) - (visao.left + visao.right/2)), 2);
        //quadrado da distância em y
        qy = (float) Math.pow((areaVisaoBola.top + areaVisaoBola.bottom/2) - (visao.top  + visao.bottom/2), 2);
        //quadrado da soma dos raios
        qr = (float) Math.pow(raioVisao, 2);
        //quadrado da soma dos raios para efeito
        qe = (float) Math.pow(visao.bottom*1.4, 2);
    
    
        if (qx + qy <= qr){
    
            if (qx + qy <= qe){
                switch (efeito){
                    case EFEITO_CIMA:
                            if (visao.top >= areaVisaoBola.top+areaVisaoBola.bottom){
                                decisao = DECISAO_SUBIR;
                            } else if (visao.top+areaVisaoBola.bottom < areaVisaoBola.top+areaVisaoBola.bottom){
                                decisao = DECISAO_DESCER;
                            }
                        break;
    
                    case EFEITO_BAIXO:
                            if (visao.top+visao.bottom <= areaVisaoBola.top){
                                decisao = DECISAO_DESCER;
                            } else
                            if (visao.top+visao.bottom >= areaVisaoBola.top+areaVisaoBola.bottom){
                                decisao = DECISAO_SUBIR;
                            }
                        break;
    
                    case EFEITO_SEM:
                    default:
                            if (visao.top > areaVisaoBola.top){
                                decisao = DECISAO_SUBIR;
                            } else  if (visao.top+visao.bottom < areaVisaoBola.top+areaVisaoBola.bottom){
                                decisao = DECISAO_DESCER;
                            }
    
                        break;
                }
            } else {
                if (visao.top > areaVisaoBola.top){
                    decisao = DECISAO_SUBIR;
                } else  if (visao.top+visao.bottom < areaVisaoBola.top+areaVisaoBola.bottom){
                    decisao = DECISAO_DESCER;
                }
            }
        }
    
        return decisao;
    }

    public void acao(Action action)
    {
        Area areaVisaoBola = new Area(getVisaoBola().getDimensao(),getVisaoBola().getPosicao());
        Area visao         = new Area(getDimensao(),getPosicao());

        switch(pensar(visao,areaVisaoBola))
        {
            case DECISAO_SUBIR:
                    subir();
                break;
            case DECISAO_DESCER:
                    descer();
                break;
            case DECISAO_NADA:
            default:
                    //nada para fazer
                break;
        }        
    }


}
