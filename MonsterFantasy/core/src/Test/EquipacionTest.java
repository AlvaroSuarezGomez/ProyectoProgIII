package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.monsterfantasy.game.battle.AtaqueEspecial;
import com.monsterfantasy.game.battle.Enemigo;
import com.monsterfantasy.game.battle.Equipacion;
import com.monsterfantasy.game.battle.Heroe;
import com.monsterfantasy.game.battle.Pociones;

public class EquipacionTest {

	
	private Heroe h;
	
	private Equipacion equip;
	private ArrayList<Equipacion> listaequipacion;
	
	@Before             
	public void setUp() {
		
		equip = new Equipacion(20, "Armadura" , 300);
	
		
		 h = new Heroe(300, 300, 100, 50,  500, 320, 1,
			false, 5);
		 
		 
		 listaequipacion = new ArrayList<Equipacion>();
		 listaequipacion.add(equip);
		 
		 
		
		
	
	}
	
	@Test
	public void testEquipar() {
		equip.equipar(h);
		assertEquals(50 + 20 , h.getDefensa());   //le suma los puntos de defensa de la armadura al heroe
	}

	@Test
	public void testDesequipar() {
		equip.equipar(h);  // 50 + 20
		equip.desequipar(h);
		assertEquals(50 + 20 - 20, h.getDefensa());  //le resta los puntos de defensa de la armadura al heroe
	}

}
