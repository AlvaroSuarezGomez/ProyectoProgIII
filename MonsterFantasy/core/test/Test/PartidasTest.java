package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.monsterfantasy.game.gestionpartidas.Partidas;

public class PartidasTest {

	@Test
	public void testGuardarfichero() {
		
		Partidas.guardarfichero(Partidas.getMapapartidas(), "pruebaguardado.dat");
	}

	@Test
	public void testCargafichero() {
		Partidas.cargafichero("pruebaguardado.dat");  //se carga un fichero existente
		Partidas.cargafichero("noexiste.dat");  //// si no hay fichero, se crea un mapa de partidas vacio
	}

}
