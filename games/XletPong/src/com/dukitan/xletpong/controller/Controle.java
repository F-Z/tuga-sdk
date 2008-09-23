package com.dukitan.xletpong.controller;

import org.tuga.framework.gbf.imagem.layer.FrameLayer;
import org.tuga.framework.gbf.imagem.layer.LayerManager;
import org.tuga.framework.gbf.kernel.write.WriteSystemManager;
import org.tuga.middleware.input.Action;

import com.dukitan.xletpong.entity.Bola;
import com.dukitan.xletpong.entity.Objeto;
import com.dukitan.xletpong.entity.raquete.CPU;
import com.dukitan.xletpong.entity.raquete.Jogador;
import com.dukitan.xletpong.entity.raquete.Raquete;




public class Controle
{
    private Placar placar;

    private Bola bola;

    private Raquete raqueteJogador;

    private CPU raqueteCPU;

    private FrameLayer cenario;

    private WriteSystemManager wsManager;

    //Diferença para o fim da partida
    final private static int DIFERENCA_FIM_PARTIDA = 5;
    //Diferença para determinar fim da prorrogação
    final private static int DIFERENCA_FIM_PRORROGACAO = 2;
    //Limite para contar a prorrogação
    final private static int LIMITE_PARA_PRORROGACAO = 10;
    //Diferença de sets para determinar o ganhador
    final private static int DIFERENCA_SET_VITORIA = 2;
    
    public Controle()
    {
        cenario    = LayerManager.getInstance().getFrameLayer("background");
        wsManager  = WriteSystemManager.getInstance();
        
        raqueteJogador = new Jogador();
        raqueteCPU     = new CPU();
        bola           = new Bola();
        placar         = new Placar();
        
        raqueteJogador.setLado(Raquete.LADO_DIREITO);
        raqueteCPU.setLado(Raquete.LADO_ESQUERDO);
    }

    public void iniciar()
    {
        Objeto.setArea(cenario.getArea());

        bola.iniciar();
        Raquete.setBola(bola);

        raqueteJogador.preparar();
        raqueteCPU.preparar();

        placar.iniciar();
    }

    public void iniciarSet()
    {
        placar.novaPartida();
    }

    public void prepararSet()
    {
        bola.continuar();
        raqueteJogador.iniciar();
        raqueteCPU.iniciar();
    }

    public void executar(Action action)
    {
        bola.acao(null);
        raqueteJogador.acao(action);
        raqueteCPU.acao(null);

        bola.isColisao(raqueteJogador);
        bola.isColisao(raqueteCPU);

        juiz();

        display();
        
        if (action.isBotaoD()){
            ativarDemo(true);
        } else if (action.isBotaoC()){
            ativarDemo(false);            
        }
    }

    public boolean isGameOver()
    {
        boolean terminou = false;

        if (placar.getVitoriaCPU()>=placar.getVitoriaJogador()+DIFERENCA_SET_VITORIA){
            terminou = true;
        }

        return terminou;
    }

    public boolean isFinalizado()
    {
        boolean terminou = false;

        if (placar.getVitoriaJogador()>=placar.getVitoriaCPU()+DIFERENCA_SET_VITORIA){
            terminou = true;
        }

        return terminou;
    }

    //Verifica se o set foi finalizado
    public boolean isSetFinalizado()
    {
        boolean finalizado = false;

        int diferenca=placar.getCPU()-placar.getJogador();

        if (((diferenca>=DIFERENCA_FIM_PARTIDA)||(diferenca<=-DIFERENCA_FIM_PARTIDA)) ||
           (((placar.getCPU()>LIMITE_PARA_PRORROGACAO)||(placar.getJogador()>LIMITE_PARA_PRORROGACAO))
              &&(diferenca>=DIFERENCA_FIM_PRORROGACAO))){
                finalizado = true;
                placar.novaPartida();
        }

        return finalizado;
    }

    public int getNumeroSet()
    {
        return placar.getVitoriaCPU()+placar.getVitoriaJogador();
    }

    public void display()
    {
        cenario.desenhar();
        //wsManager->escrever("texto" ,20,10,"%02d",placar.getVitoriaCPU());
        int left = cenario.getArea().left;
        
        wsManager.escrever("texto",left+285,10,placar.getCPU()+" X "+placar.getJogador());
        wsManager.escrever("texto",left+24, 10,""+placar.getVitoriaCPU());
        wsManager.escrever("texto",left+600,10,""+placar.getVitoriaJogador());
        
        bola.desenhar();
        raqueteJogador.desenhar();
        raqueteCPU.desenhar();
    }

    protected void juiz()
    {       
        if (bola.getPosicao().x>=(cenario.getArea().right)){
            System.out.println("Bola("+bola.getPosicao()+") >= "+(cenario.getArea().left+cenario.getArea().right));
            placar.pontuarCPU();
            raqueteCPU.preparar();
            raqueteJogador.preparar();

            bola.iniciar(raqueteCPU.saque());
        } else  if (bola.getPosicao().x+bola.getDimensao().w<=cenario.getArea().left){            
            placar.pontuarJogador();
            raqueteJogador.preparar();
            raqueteCPU.preparar();

            bola.iniciar(raqueteJogador.saque());           
        }
    }

    protected void setFinalizado()
    {
        
    }


    //Ativar demonstração do jogo
    private void ativarDemo(boolean ativo)
    {
        if (ativo){
            if (raqueteJogador!=null){
                raqueteJogador = null;
            }
            raqueteJogador = new CPU();
            raqueteJogador.setLado(Raquete.LADO_DIREITO);
        } else {
            if (raqueteJogador!=null){
                raqueteJogador = null;
            }
            raqueteJogador = new Jogador();
            raqueteJogador.setLado(Raquete.LADO_DIREITO);
        }
    }

}
