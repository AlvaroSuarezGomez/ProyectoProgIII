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



public class HeroeTest {

	
	private Heroe h;
	private Enemigo e;
	private AtaqueEspecial a;
	private ArrayList<AtaqueEspecial> listaataques;
	private Pociones p;
	private ArrayList<Pociones> listapociones;
	private Equipacion equip;
	private ArrayList<Equipacion> listaequipacion;
	
	@Before             
	public void setUp() {
	
		
		 h = new Heroe(300, 300, 100, 50,  500, 320, 1,
			false, 5);
		 
		 e = new Enemigo(200, 200, 100, 30, false, 300,
			"Enemigo", 4);
		 
		 a = new AtaqueEspecial("Placaje" , 20 , 1);
		 
		 equip = new Equipacion(20, "armadura" , 30);
		 
		 listaataques = new ArrayList<AtaqueEspecial>();
		 listaataques.add(a);
		 
		 listapociones = new ArrayList<Pociones>();
		 listapociones.add(p);
		 
		 listaequipacion = new ArrayList<Equipacion>();
		 listaequipacion.add(equip);
		 
		 
		
		
	
	}
	
	
	
	@Test
	public void testGetEspiritu() {
		assertEquals(5, h.getEspiritu());
	}

	@Test
	public void testSetEspiritu() {
		h.setEspiritu(6);                               //probamos que se han establecido bien los puntos de espíritu
		assertEquals(6, h.getEspiritu());
	}

	@Test
	public void testAtaque() {
		h.ataque(e);
		assertEquals(130, e.getPv());		//el ataque deberia dejarle con 130 de vida al enemigo
		e.setPosicionguardia(true);
		h.ataque(e);
		assertEquals(90, e.getPv());		////el ataque deberia dejarle con 90 de vida al enemigo al activar la guardia tras el primer golpe
		
		
		e.setPosicionguardia(false);			//probamos todas las ramas del metodo
		h.setAtaque(0);
		h.ataque(e);
		assertEquals(89, e.getPv());			
		e.setPosicionguardia(true);
		h.ataque(e);
		assertEquals(88, e.getPv());			
		
	}

	@Test
	public void testGuardia() {
		
		h.guardia();
		assertTrue(h.isPosicionguardia());  //ponemos al heroe en posicion de guardia y comprobamos que ha funcionado
	}

	@Test
	public void testGetAtaques() {
		h.setAtaques(listaataques);
		assertEquals(listaataques, h.getAtaques());
	}

	@Test
	public void testSetAtaques() {
		h.setAtaques(listaataques);
		assertEquals(listaataques, h.getAtaques());
	}

	@Test
	public void testGetExp() {
		assertEquals(320, h.getExp());
	}

	@Test
	public void testSetExp() {
		h.setExp(400);
		assertEquals(400, h.getExp());
	}

	@Test
	public void testGetNv() {
		assertEquals(1, h.getNv());
	}

	@Test
	public void testSetNv() {
		h.setNv(2);
		assertEquals(2, h.getNv());
	}

	@Test
	public void testGetDinero() {
		assertEquals(500,h.getDinero());
	}

	@Test
	public void testSetDinero() {
		h.setDinero(600);
		assertEquals(600, h.getDinero());
	}

	@Test
	public void testAtaqueespecial() {
		h.ataqueespecial(e, a);
		assertEquals(110, e.getPv());		//el ataque especial deberia dejarle con 100 de vida al enemigo
		e.setPosicionguardia(true);
		h.ataqueespecial(e, a);
		assertEquals(50, e.getPv());		////el ataque especial deberia dejarle con 50 de vida al enemigo al activar la guardia tras el primer golpe 
		
		
		e.setPosicionguardia(false);			//probamos todas las ramas del metodo
		h.setAtaque(0);
		h.ataqueespecial(e, a);
		assertEquals(49, e.getPv());				
		e.setPosicionguardia(true);
		h.ataqueespecial(e, a);
		assertEquals(48, e.getPv());			
	}

	@Test
	public void testGetPociones() {
		h.setPociones(listapociones);
		assertEquals(listapociones, h.getPociones());
	}

	@Test
	public void testSetPociones() {
		h.setPociones(listapociones);
		assertEquals(listapociones, h.getPociones());
	}

	@Test
	public void testGetEquipacion() {
		h.setEquipacion(listaequipacion);
		assertEquals(listaequipacion, h.getEquipacion());
	}

	@Test
	public void testSetEquipacion() {
		h.setEquipacion(listaequipacion);
		assertEquals(listaequipacion, h.getEquipacion());
	}

	@Test
	public void testIsPosicionguardia() {
		assertFalse(h.isPosicionguardia());
	}

	@Test
	public void testSetPosicionguardia() {
		h.setPosicionguardia(true);
		assertTrue(h.isPosicionguardia());
	}

	@Test
	public void testGetPv() {
		assertEquals(300, h.getPv());
	}

	@Test
	public void testSetPv() {
		h.setPv(400);
		assertEquals(400, h.getPv());
	}

	@Test
	public void testGetPvmax() {
		assertEquals(300, h.getPvmax());
	}

	@Test
	public void testSetPvmax() {
		h.setPvmax(400);
		assertEquals(400, h.getPvmax());
	}

	@Test
	public void testGetAtaque() {
		assertEquals(100, h.getAtaque());
	}

	@Test
	public void testSetAtaque() {
		h.setAtaque(400);
		assertEquals(400, h.getAtaque());
	}

	@Test
	public void testGetDefensa() {
		assertEquals(50, h.getDefensa());
	}

	@Test
	public void testSetDefensa() {
		h.setDefensa(60);
		assertEquals(60, h.getDefensa());
	}

	

}
