package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.monsterfantasy.game.overworld.GestionMapa;
import com.monsterfantasy.game.overworld.Overworld;

public class GestionMapaTest {

	Overworld overworldTest = new Overworld();
	
	@Test
	public void testGuardarFichero() {
		overworldTest.crearCeldas();
		GestionMapa.guardarfichero(overworldTest.getCeldas(), "pruebamapas.dat");
	}
	
	@Test
	public void testCargarFichero() {
		GestionMapa.cargafichero("pruebamapas.dat");
		GestionMapa.cargafichero("noexiste.dat");
	}

}
