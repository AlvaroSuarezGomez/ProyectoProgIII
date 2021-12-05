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

public class AtaqueEspecialTest {



	private AtaqueEspecial a;


	
	@Before             
	public void setUp() {
	
	
		 
	
		 a = new AtaqueEspecial("Placaje" , 20 , 1);
		 
	
	}
	
	
	
	


	@Test
	public void testGetNombre() {
		assertEquals("Placaje", a.getNombre());
		
		
	}

	@Test
	public void testSetNombre() {
		a.setNombre("Mordisco");
		assertEquals("Mordisco" , a.getNombre());
		
	}

	@Test
	public void testGetPotencia() {
		assertEquals(20, a.getPotencia());
		
	}

	@Test
	public void testSetPotencia() {
		a.setPotencia(40);
		assertEquals(40, a.getPotencia());
		
	}

	@Test
	public void testGetEspiritu() {
		assertEquals(1, a.getEspiritu());
	}

	@Test
	public void testSetEspiritu() {
		a.setEspiritu(2);
		assertEquals(2, a.getEspiritu());
	}

}
