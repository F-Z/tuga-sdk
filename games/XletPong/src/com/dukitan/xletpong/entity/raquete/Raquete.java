package com.dukitan.xletpong.entity.raquete;

import org.tuga.framework.base.Ponto;
import org.tuga.framework.personagem.Personagem;

import com.dukitan.xletpong.entity.Bola;
import com.dukitan.xletpong.entity.Objeto;




public abstract class Raquete extends Objeto
{
    //Lado esquerdo da Tela
    static public int LADO_ESQUERDO =0;
    //Lado direito da Tela
    static public int LADO_DIREITO  =1;
    
    private int velocidade;

    private static Bola visaoBola;
        
    protected int lado;
    
    public abstract Ponto saque();


    //Construtor
    public Raquete() 
    {
        velocidade = 10;
    }
    //Inicia raquete
    public void iniciar() 
    {
        preparar();
    }
    //Posiciona Raquete
    public void preparar() 
    {
        //Se for do lado direito da tela
        if (lado==LADO_DIREITO){
            setPosicao(getAreaTela().right-getDimensao().w,(getAreaTela().bottom/2)-(getDimensao().h/2));
        //Se for do lado esquerdo da tela
        } else {
            setPosicao(getAreaTela().left,(getAreaTela().bottom/2)-(getDimensao().h/2));
        }
    }
    
    static public void setBola(Bola bola)
    {
        visaoBola=bola;
    }
    
    public void setLado(int valor) 
    {
        lado=valor;
    }
    
    public boolean isColisao(Personagem personagem) 
    {
        boolean retorno = false;
    
        //Se raquete no lado direito da tela
        if (lado==LADO_DIREITO){
            if ((posicao.x + getDimensao().w >= personagem.getPosicao().x) &&
                    (posicao.x <= personagem.getPosicao().x + personagem.getDimensao().w) &&
                    (posicao.y + getDimensao().h >= personagem.getPosicao().y) &&
                    (posicao.y <= personagem.getPosicao().y + personagem.getDimensao().h)){
                retorno = true;
            }
        //Se raquete no lado esquerdo da tela
        } else {
            if ((posicao.x + getDimensao().w >= personagem.getPosicao().x) &&
              (posicao.x <= personagem.getPosicao().x + personagem.getDimensao().w) &&
              (posicao.y + getDimensao().h >= personagem.getPosicao().y) &&
              (posicao.y <= personagem.getPosicao().y + personagem.getDimensao().h)){
                      retorno = true;
            }
        }

        return retorno;
    }
    
    protected int getVelocidade() 
    {
        return velocidade;
    }
    
    protected void setVelocidade(int valor) 
    {
        velocidade=valor;
    }
    
    protected void subir() 
    {
        posicao.y-=getVelocidade();

        if (posicao.y<=getAreaTela().top){
            posicao.y=getAreaTela().top;
        }
    }
    
    protected void descer() 
    {
        posicao.y+=getVelocidade();
        if (posicao.y+getDimensao().h>=getAreaTela().bottom){
            posicao.y=getAreaTela().bottom-getDimensao().h;
        }
    }
  
    protected boolean isBateuParede() 
    {
        boolean bateu = false;

        if ((posicao.y+getDimensao().h>=getAreaTela().bottom)||(posicao.y<=getAreaTela().top)){
            bateu=true;
        }

        return bateu;
    }
    
    protected Bola getVisaoBola() 
    {
        return visaoBola;
    }    
}
