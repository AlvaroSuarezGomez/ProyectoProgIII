package com.monsterfantasy.game.battle;



import java.io.Serializable;
import java.util.ArrayList;

import com.monsterfantasy.game.Monsterfantasy;

public class Enemigo extends Personaje implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Puntos de espiritu para realizar ataques especiales 
	 */
	protected int espiritu; 
	
	private ArrayList<AtaqueEspecial> ataques;
	
	


	/**
	 * Puntos de experiencia que obtiene el heroe al derrotar al enemigo
	 * 
	 */
	protected int exprecompensa;

	/**
	 *  Nombre del enemigo
	 */
	protected String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return Puntos de experiencia que obtiene el heroe al derrotar al enemigo
	 */
	public int getExprecompensa() {
		return exprecompensa;
	}

	/**
	 * Modifica los Puntos de experiencia que obtiene el heroe al derrotar al
	 * enemigo
	 * 
	 * @param exprecompensa Puntos de experiencia que obtiene el heroe al derrotar
	 *                      al enemigo a asignar
	 */
	public void setExprecompensa(int exprecompensa) {
		this.exprecompensa = exprecompensa;
	}

	/**
	 * Constructor
	 * 
	 * @param pv              Puntos de vida actuales
	 * @param pvmax           Puntos de vida maximos
	 * @param ataque          Puntos de ataque
	 * @param defensa         puntos de defensa
	 * @param posicionguardia Booleano que determina si se encuentra el luchador en
	 *                        posicion de defensa
	 * @param exprecompensa   Puntos de experiencia que obtiene el heroe al derrotar
	 *                        al enemigo
	 */
	public Enemigo(int pv, int pvmax, int ataque, int defensa, boolean posicionguardia, int exprecompensa,
			String nombre, int espiritu) {
		super(pv, pvmax, ataque, defensa, posicionguardia, espiritu);
		this.exprecompensa = exprecompensa;
		this.nombre = nombre;
		
		this.setAtaques(new ArrayList<AtaqueEspecial>());


	}

	/**
	 * El enemigo realiza un ataque sobre el heroe
	 */
	public void ataque(Personaje p) {
		Heroe heroe = (Heroe) p;
		int danyo;
		if (heroe.posicionguardia == false) {
			danyo = this.ataque - heroe.defensa;
			if (danyo <= 0) {
				danyo = 1;
				heroe.setPv(heroe.getPv() - danyo);
			}

			else {
				heroe.setPv(heroe.getPv() - danyo);
			}
		} else {
			danyo = this.ataque - (heroe.defensa * 2);
			if (danyo <= 0) {
				danyo = 1;
				heroe.setPv(heroe.getPv() - danyo);
				heroe.setPosicionguardia(false);
			}

			else {
				heroe.setPv(heroe.getPv() - danyo);
			}
		}

	}
	
	
	/**
	 * @param p Personaje al que se ataca
	 * @param at ataque especial que se realiza
	 */
	public void ataqueespecial(Personaje p, AtaqueEspecial at) {
		Heroe heroe = (Heroe) p;
		int danyo;
		if (heroe.posicionguardia == false) {
			danyo = this.ataque + at.getPotencia() - heroe.defensa;
			if (danyo <= 0) {
				danyo = 1;
				heroe.setPv(heroe.getPv() - danyo);
			}

			else {
				heroe.setPv(heroe.getPv() - danyo);
			}
		} else {
			danyo = this.ataque + at.getPotencia() - heroe.defensa * 2;
			if (danyo <= 0) {
				danyo = 1;
				heroe.setPv(heroe.getPv() - danyo);
				heroe.setPosicionguardia(false);
			} else {
				heroe.setPv(heroe.getPv() - danyo);
			}

		}

	}

	/**
	 * El enemigo adopta una posicion de defensa y se le suman dos puntos de espiritu
	 */
	public void guardia() {
		this.posicionguardia = true;
		this.espiritu = this.espiritu + 2;

	}
	
	public ArrayList<AtaqueEspecial> getAtaquesEnemigos(Enemigo enemigo,int contador,ArrayList<AtaqueEspecial> ataques){
	    if(contador<(BaseDeDatos.getAtaques().size())){
	        int ataque = (int) Math.random()*enemigo.getAtaques().size();
	        ataques.add(enemigo.getAtaques().get(ataque));
	        return getAtaquesEnemigos(enemigo,contador+1,ataques);
	    }else{
	        return ataques;
	    }
	}
	
	public void elegirAccion(Enemigo enemigo, Personaje personaje, int actionIndex, ArrayList<AtaqueEspecial> ataques, Monsterfantasy game) {
        Personaje personajePrueba = personaje;
        Enemigo enemigoPrueba = enemigo;
        accion(enemigoPrueba, personajePrueba, actionIndex, ataques, game);
        
        String text = "";

        if (personajePrueba.getPv() <= 0) {
           accion(enemigo, personaje, actionIndex, ataques, game);
        }

        else if (enemigoPrueba.getPv() <= 0) {
           accion(enemigoPrueba, personajePrueba, actionIndex + 1, ataques, game);
        }

        else {
           accion(enemigoPrueba, personajePrueba, actionIndex + 1, ataques, game);
        }

    }

    public void accion(Enemigo enemigo, Personaje personaje, int actionIndex, ArrayList<AtaqueEspecial> ataques, Monsterfantasy game) {
        
    	String texto = "";
    	
    	if (actionIndex == 0) {
            texto = enemigo.getNombre() + " ha atacado a " + game.getPartida().getNombre();
            ataque(personaje);
            enemigo.setEspiritu(enemigo.getEspiritu()+1);
        }

        if (actionIndex == 1) {
            guardia();
            texto = enemigo.getNombre() + " ha usado la guardia ";
        }

        for (AtaqueEspecial atEsp : ataques) {
            if (atEsp.getEspiritu() <= enemigo.getEspiritu()) {
                ataqueespecial(personaje, atEsp);
                enemigo.setEspiritu(enemigo.getEspiritu()-atEsp.getEspiritu());
                texto = enemigo.getNombre() + " ha usado " + atEsp.getNombre();
        }
        }
    }	

	public ArrayList<AtaqueEspecial> getAtaques() {
		return ataques;
	}

	public void setAtaques(ArrayList<AtaqueEspecial> ataques) {
		this.ataques = ataques;
	}

	



}









