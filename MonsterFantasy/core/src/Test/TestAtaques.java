package Test;

import org.junit.Test;

import com.monsterfantasy.game.battle.AtaqueEspecial;
import com.monsterfantasy.game.battle.Enemigo;
import com.monsterfantasy.game.battle.Heroe;
import com.monsterfantasy.game.battle.Personaje;

public class TestAtaques {
	
	static Enemigo enemigo = new Enemigo(50,100,10,5,true,10,"Xabi",10);
	static Heroe heroe = new Heroe(100,200,20,40,100,40,5,true,40);
	static AtaqueEspecial ataque = new AtaqueEspecial ("hola",2,3);
	
	@Test
	public void main() {
		heroe.ataque(enemigo);
		heroe.ataqueespecial(enemigo, ataque);
		enemigo.ataque(heroe);
		enemigo.ataqueespecial(heroe, ataque);
	}
	
}
