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

public class EnemigoTest {
	
	private Heroe h;
	private static Enemigo e;
	private AtaqueEspecial a;
	private static ArrayList<AtaqueEspecial> listaataques;

	
	@Before             
	public void setUp() {
	
		
		 h = new Heroe(200, 200, 100, 30,  500, 320, 1,
			false, 5);
		 
		 e = new Enemigo(300, 300, 100, 50, false, 300,
			"Enemigo", 4);
		 
		 a = new AtaqueEspecial("Placaje" , 20 , 1);
		 
		
		 
		 listaataques = new ArrayList<AtaqueEspecial>();
		 listaataques.add(a);
		 
	
		
	
	}
	

	@Test
	public void testAtaque() {
		
		
		e.ataque(h);
		assertEquals(130, h.getPv());		//el ataque deberia dejarle con 130 de vida al heroe
		h.setPosicionguardia(true);
		e.ataque(h);
		assertEquals(90, h.getPv());		////el ataque deberia dejarle con 90 de vida al heroe al activar la guardia tras el primer golpe
		
		
		h.setPosicionguardia(false);			//probamos todas las ramas del metodo
		e.setAtaque(0);
		e.ataque(h);
		assertEquals(89, h.getPv());			
		h.setPosicionguardia(true);
		e.ataque(h);
		assertEquals(88, h.getPv());		
	}

	@Test
	public void testGuardia() {
		e.guardia();
		assertTrue(e.isPosicionguardia());
	}

	@Test
	public void testGetNombre() {
		assertEquals("Enemigo" , e.getNombre());
	}

	@Test
	public void testSetNombre() {
		e.setNombre("bicho malo");
		assertEquals("bicho malo", e.getNombre());
	}

	@Test
	public void testGetExprecompensa() {
		assertEquals(300, e.getExprecompensa());
	}

	@Test
	public void testSetExprecompensa() {
		e.setExprecompensa(500);
		assertEquals(500, e.getExprecompensa());
	}

	

	@Test
	public void testAtaqueespecial() {
		e.ataqueespecial(h, a);
		assertEquals(110, h.getPv());		//el ataque especial deberia dejarle con 100 de vida al heroe
		h.setPosicionguardia(true);
		e.ataqueespecial(h, a);
		assertEquals(50, h.getPv());		////el ataque especial deberia dejarle con 50 de vida al heroe al activar la guardia tras el primer golpe 
		
		
		h.setPosicionguardia(false);			//probamos todas las ramas del metodo
		e.setAtaque(0);
		e.ataqueespecial(h, a);
		assertEquals(49, h.getPv());				
		h.setPosicionguardia(true);
		e.ataqueespecial(h, a);
		assertEquals(48, h.getPv());			
	}
	
	@Test
	public void getAtaquesEnemigosTest() {
		ArrayList<AtaqueEspecial> lista = new ArrayList<AtaqueEspecial>();
		
		
	}
	
	public static void main(String[] args) {
		ArrayList<AtaqueEspecial> lista = new ArrayList<AtaqueEspecial>();
		System.out.println(e.getAtaquesEnemigos(e,4,listaataques).size()); 
	}

}
