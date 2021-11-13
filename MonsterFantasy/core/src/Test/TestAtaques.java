package Test;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.monsterfantasy.game.battle.AtaqueEspecial;
import com.monsterfantasy.game.battle.Enemigo;
import com.monsterfantasy.game.battle.Equipacion;
import com.monsterfantasy.game.battle.Heroe;
import com.monsterfantasy.game.battle.Personaje;
import com.monsterfantasy.game.battle.Pociones;

	public class TestAtaques {

	public final static Logger LOGGER = Logger.getLogger("Test");
	ArrayList<Equipacion> equipacion = new ArrayList<Equipacion>();
	ArrayList<Pociones> pociones = new ArrayList<Pociones>();
	static Enemigo enemigo = new Enemigo(50,100,10,5,true,10,"Xabi",10);
	static Heroe heroe = new Heroe(100,200,20,40,100,40,5,true,40);
	static AtaqueEspecial ataque = new AtaqueEspecial ("hola",2,3);
	static Equipacion escudo = new Equipacion(20,"escudo de Zeus",30);
	static Pociones pocion1 = new Pociones("Vida",50,20);
	ArrayList<Equipacion> equipamiento = new ArrayList<Equipacion>();
	static Pociones pocion2= new Pociones ("Curacion",20,30);

//	@BeforeClass
	public static void beforeTest() {
		LOGGER.log(Level.INFO, "ejecutando metodo: beforeClass");
	}

//	@Before
   public void beforeAtaques() {
		LOGGER.log(Level.INFO, "ejecutando metodo: test ataques");
   }

	@Test
	public void testsAtaquesHeroe() {
			enemigo.setPosicionguardia(false);
			heroe.setAtaque(0);
			ataque.setPotencia(0);
			heroe.ataque(enemigo);
			heroe.setAtaque(10);
			heroe.ataque(enemigo);
			enemigo.setPosicionguardia(true);
			heroe.setAtaque(1);
			heroe.ataque(enemigo);
			
			
	}

//	@After
    public void after1() {
		LOGGER.log(Level.INFO, "ejecutado metodo: test ataques");
    }

//	@Before
    public void beforeAtaquesEspeciales() {
		LOGGER.log(Level.INFO, "ejecutando metodo: test ataques especiales");
   }

	@Test
	public void testAtaquesEspeciales() {
		heroe.ataqueespecial(enemigo, ataque);
		enemigo.ataqueespecial(heroe, ataque);
	}
	
//	@After
    public void afterAtaques() {
		LOGGER.log(Level.INFO, "ejecutado metodo : test ataques especiales");
    }

//	@Before
   public void beforeconsumibles() {
    	LOGGER.log(Level.INFO, "ejecutando metodo: consumibles");
   }

	@Test
	public void consumibles(){
		heroe.setEquipacion(equipacion);
		equipacion.add(escudo);
		pociones.add(pocion1);
		escudo.equipar(heroe);
		escudo.desequipar(heroe);
		pocion1.consumir(heroe);
	}
//	@After
    public void afterconsumibles() {
		LOGGER.log(Level.INFO, "ejecutado metodo: consumibles");
    }
	

	
	@Test
	public void huir() {
		heroe.huir();
	}
	
	@Test
	public void guardiaActivada() {
		int a;
		heroe.guardia();
		a= heroe.getEspiritu();
		System.out.println(a);
	}
	
	@Test
	public void listaAtaques() {
		
	}
		@Test
	public void gettersHeroe() {
		heroe.getAtaque();
		heroe.getAtaques();
		heroe.getClass();
		heroe.getDefensa();
		heroe.getDinero();
		heroe.getEquipacion();
		heroe.getEspiritu();
		heroe.getExp();
		heroe.getNv();
		heroe.getPociones();
		heroe.getPv();
		heroe.getPvmax();
		
	}
		
	@Test
	public void setterHeroe() {
		heroe.setAtaque(50);
		heroe.setDefensa(1);
		heroe.setDinero(1);
		heroe.setEspiritu(5);
		heroe.setExp(2);
		heroe.setNv(10);
		heroe.setPosicionguardia(true);
		heroe.setPv(500);
		heroe.setPvmax(1000);
		
	}
	
	@Test
	public void getEnemigo() {
		enemigo.getAtaque();
		enemigo.getClass();
		enemigo.getDefensa();
		enemigo.getEspiritu();
		enemigo.getExprecompensa();
		enemigo.getNombre();
		enemigo.getPv();
		enemigo.getPvmax();
		
	}
	
	@Test
	public void settersEnemigo() {
		enemigo.setAtaque(1);
		enemigo.setDefensa(10);
		enemigo.setEspiritu(50);
		enemigo.setExprecompensa(200);
		enemigo.setNombre("Ines Jacob");
		enemigo.setPosicionguardia(true);
		enemigo.setPv(10);
		enemigo.setPvmax(200);
	}
	
	@Test
	public void defensaEnemigo() {
		enemigo.guardia();
	}
	
	@Test
	public void NombreEnemigo(){
		enemigo.toString();
	}
	
///	@Test
	public void equals() {
		enemigo.equals(heroe);
	}
	

//	@AfterClass
//	public static void afterTest() {
//		LOGGER.log(Level.INFO, "ejecutado los teses de comprobación");
//	}
}
