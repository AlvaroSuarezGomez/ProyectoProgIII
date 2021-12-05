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

public class PocionesTest {

	
	
	private Heroe h;
	
	private Pociones p;
	private ArrayList<Pociones> listapociones;

	@Before             
	public void setUp() {
	
		
		 p = new Pociones("Pocion 100" , 200 , 100);
		 h = new Heroe(300, 300, 100, 50,  500, 320, 1,
			false, 5);
		
		 
		 listapociones = new ArrayList<Pociones>();
		 listapociones.add(p);
	
		 
		 
		
		
	
	}
	
	
	@Test
	public void testConsumir() {
		p.consumir(h);
		assertEquals(300, h.getPv());   //al tener la salud al maximo, la pocion no le aumenta los pv
		h.setPv(50);
		p.consumir(h);					//bajamos la salud del heroe a 50 pv
		assertEquals(150, h.getPv());  // ahora le suma 100 pv 
	}

	@Test
	public void testGetNombre() {
	
		assertEquals("Pocion 100" , p.getNombre());
	}

	@Test
	public void testSetNombre() {
		p.setNombre("pocion de 100");
		assertEquals("pocion de 100" , p.getNombre());
	}

	@Test
	public void testGetPrecio() {
		assertEquals(200 , p.getPrecio());
	}

	@Test
	public void testSetPrecio() {
		p.setPrecio(300);
		assertEquals(300 , p.getPrecio());
	}



}
