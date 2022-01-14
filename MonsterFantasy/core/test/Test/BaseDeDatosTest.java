package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.monsterfantasy.game.battle.BaseDeDatos;

public class BaseDeDatosTest {

	@Before
	public void setUp() {
		BaseDeDatos.abrirConexion("BaseDatos", true);
	}
	
	
	@Test
	public void testGetEnemigos() {
		assertEquals("Zarpitas", BaseDeDatos.getEnemigos().get(0).getNombre());  //El nombre del primer enemigo se ha obtenido correctamente
		assertEquals(5, BaseDeDatos.getEnemigos().size());
		
	}

	@Test
	public void testGetAtaques() {
		assertEquals("Golpeo", BaseDeDatos.getAtaques().get(0).getNombre()); 
		assertEquals(3, BaseDeDatos.getAtaques().size());
	}

	@Test
	public void testGetEquipaciones() {
		assertEquals("Espada", BaseDeDatos.getEquipaciones().get(0).getNombre()); 
		assertEquals(3, BaseDeDatos.getEquipaciones().size());
	}

	@Test
	public void testGetPociones() {
		assertEquals("salud", BaseDeDatos.getPociones().get(0).getNombre()); 
		assertEquals(3, BaseDeDatos.getPociones().size());
	}

}
