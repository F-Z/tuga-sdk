package com.dukitan.xletpong.entity;

import org.tuga.framework.base.Area;
import org.tuga.framework.gbf.kernel.sound.SoundSystem;
import org.tuga.framework.personagem.Personagem;


public abstract class Objeto extends Personagem
{
	static protected SoundSystem soundSystem;
	static final private int FOLGA = 4;
	
    private static Area areaTela;


	public Objeto()
	{
		if (soundSystem==null){
			soundSystem = SoundSystem.getInstance();
		}
	}

    public static void setArea(Area area)
    {
        areaTela=area;

        areaTela.top=areaTela.top+FOLGA;
        areaTela.bottom=areaTela.bottom-FOLGA;
    }

    protected  Area getAreaTela()
    {
        return areaTela;
    }

}
