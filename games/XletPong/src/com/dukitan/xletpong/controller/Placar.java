package com.dukitan.xletpong.controller;

import org.tuga.framework.gbf.kernel.sound.SoundSystem;



public class Placar
{
    private int jogador;
    private int cpu;
    private int partidaJogador;
    private int partidaCPU;

    static protected SoundSystem soundSystem;    
    
    public Placar()
    {
        iniciar();
        if (soundSystem==null){
            soundSystem = SoundSystem.getInstance();
        }
    }

    public void iniciar()
    {
        jogador=0;
        cpu=0;
        partidaJogador=0;
        partidaCPU=0;
    }

    public void novaPartida()
    {
        if (jogador>cpu){
            vitoriaJogador();
        } else if (jogador<cpu){
            vitoriaCPU();
        }
        jogador=0;
        cpu=0;
    }

    public void pontuarJogador()
    {
        //soundSystem->fxManager->playPanEffect("ponto",640);
        jogador++;
    }

    public void pontuarCPU()
    {
        //soundSystem->fxManager->playPanEffect("ponto",0);
        cpu++;
    }

    public int getJogador()
    {
        return jogador;
    }

    public int getCPU()
    {
        return cpu;
    }

    public void vitoriaJogador()
    {
        partidaJogador++;
    }

    public void vitoriaCPU()
    {
        partidaCPU++;
    }

    public int getVitoriaJogador()
    {
        return partidaJogador;
    }

    public int getVitoriaCPU()
    {
        return partidaCPU;
    }
}
