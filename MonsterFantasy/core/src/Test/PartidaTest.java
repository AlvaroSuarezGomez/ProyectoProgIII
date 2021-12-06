package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.monsterfantasy.game.battle.Heroe;
import com.monsterfantasy.game.gestionpartidas.Partida;
import com.monsterfantasy.game.gestionpartidas.Partidas;

public class PartidaTest {
	
	private Partida partida;
	private Partida partida2;
	
	@Before
	public void SetUp() {
		 partida = new Partida("xabi", new Heroe()); //se crea una partida con los valores por defecto de inicio
		 partida2 = new Partida("markel" , new Heroe());
	}

	@Test
	public void testPartidanueva() {
		partida.partidanueva();
		assertTrue( Partidas.getMapapartidas().containsKey("xabi"));  //Comprobamos que la partida creada esta en el mapa de partidas buscando su clave
	}

	@Test
	public void testCargarpartida() {
	
		partida.cargarpartida("markel");  //no existe la partida
		partida.cargarpartida("xabi");    //existe la partida
	}

	@Test
	public void testGuardarpartida() {
		partida.guardarpartida();      //se comprueban los loggers 
		partida2.guardarpartida();
	}

}
