package Test;

import java.util.ArrayList;

import org.junit.Test;

import com.monsterfantasy.game.battle.AtaqueEspecial;
import com.monsterfantasy.game.battle.Enemigo;
import com.monsterfantasy.game.battle.Equipacion;
import com.monsterfantasy.game.battle.Heroe;
import com.monsterfantasy.game.battle.Personaje;
import com.monsterfantasy.game.battle.Pociones;

public class TestAtaques {
	
	ArrayList<Equipacion> equipacion = new ArrayList<Equipacion>();
	ArrayList<Pociones> pociones = new ArrayList<Pociones>();
	static Enemigo enemigo = new Enemigo(50,100,10,5,true,10,"Xabi",10);
	static Heroe heroe = new Heroe(100,200,20,40,100,40,5,true,40);
	static AtaqueEspecial ataque = new AtaqueEspecial ("hola",2,3);
	static Equipacion escudo = new Equipacion(20,"escudo de Zeus",30);
	static Pociones pocion1= new Pociones ("Curacion",20,30);
	
	
	
	@Test
	public void main() {
		heroe.setEquipacion(equipacion);
		equipacion.add(escudo);
		pociones.add(pocion1);
		heroe.ataque(enemigo);
		heroe.ataqueespecial(enemigo, ataque);
		enemigo.ataque(heroe);
		enemigo.ataqueespecial(heroe, ataque);
		escudo.equipar(heroe);
		escudo.desequipar(heroe);
		pocion1.consumir(heroe);
	}
}
