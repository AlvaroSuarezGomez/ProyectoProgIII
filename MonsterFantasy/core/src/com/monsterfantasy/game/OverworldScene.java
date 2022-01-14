package com.monsterfantasy.game;

import java.io.Serializable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.monsterfantasy.game.battle.Heroe;
import com.monsterfantasy.game.gestionpartidas.Partida;
import com.monsterfantasy.game.gestionpartidas.Partidas;
import com.monsterfantasy.game.overworld.Avatar;
import com.monsterfantasy.game.overworld.Celda;
import com.monsterfantasy.game.overworld.Controller;
import com.monsterfantasy.game.overworld.GestionMapa;
import com.monsterfantasy.game.overworld.LookDirection;
import com.monsterfantasy.game.overworld.Overworld;
import com.monsterfantasy.game.overworld.TipoCelda;

public class OverworldScene extends ScreenAdapter implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4139408601941798510L;
	private SpriteBatch batch;
	private Monsterfantasy game;
	private TextureRegion region;
	private Texture gameMenu;
	private Heroe heroe;
	private Overworld map = new Overworld();
	private Avatar player = new Avatar(0, 0, heroe);
	private Partida partida;
	private boolean isMenuOpened = false;
	private BitmapFont menuFont;
	private int menuIndex = 0;
	private boolean debugMode = false;
	private boolean startBattle = false;
	private Texture menuPointer;
	private MenuOption menu;
	private int equipationIndex = 0;
	private boolean triggerBattleRandomizer = false;
	private boolean triggerShop = false;
	private LookDirection direction = LookDirection.None;
	private boolean move = false;
	
	public OverworldScene(Monsterfantasy game) {
		super();
		this.game = game;
		game.setCam(new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		map.setCeldas(GestionMapa.cargafichero("mapa"));
		map.setTileSet(new Texture("Overworld tileset.png"));
		map.setSuelo(new TextureRegion(getMap().getTileSet(), 16, 0, 16, 16));
		map.setArbol(new TextureRegion(getMap().getTileSet(), 160, 0, 16, 16));
		map.setHierba(new TextureRegion(getMap().getTileSet(), 80, 16, 16, 16));
		map.setShop(new Texture("shop_sprite.png"));
		map.setTienda(new TextureRegion(map.getShop(), 0, 0, 16, 16));
		gameMenu =  new Texture(Gdx.files.internal("GameMenu.png"));
		Controller.overworld = map;
		Controller.setTexture(getPlayer());
		batch = game.getBatch();
		partida = game.getPartida();
		heroe = game.getHeroe();
		menuPointer = new Texture(Gdx.files.internal("BlackPointer.png"));
		menuFont = new BitmapFont(Gdx.files.internal("pokemon-dp-pro.fnt"), Gdx.files.internal("pokemon-dp-pro.png"), false);
		Controller.setOverworldScene(this);
		player.setX(game.getX());
		player.setY(game.getY());
		player.setP_texture_region(new TextureRegion(player.getP_texture(), 0, 0, 64, 64));
		
	}
	
	@Override
	public void render(float dt) {
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		game.getCam().position.set(getPlayer().getX(), getPlayer().getY(), 0);
		batch.setProjectionMatrix(game.getCam().combined);
		batch.begin();
		Controller.player = getPlayer();
		game.getCam().update();
		map.draw(batch, 0);
		batch.draw(player.getP_texture_region(), player.getX(), player.getY(), 64, 64);
		
		if (debugMode) {
			if (Gdx.input.isKeyJustPressed(Keys.Q)) {
			map.getCeldas()[(int) (player.getX()/64)][(int) (player.getY()/64)].setTipo(TipoCelda.Arbol);
			}	else if (Gdx.input.isKeyJustPressed(Keys.P)) {
				map.getCeldas()[(int) (player.getX()/64)][(int) (player.getY()/64)].setTipo(TipoCelda.Hierba);
			}	else if (Gdx.input.isKeyJustPressed(Keys.L)) {
				map.getCeldas()[(int) (player.getX()/64)][(int) (player.getY()/64)].setTipo(TipoCelda.Tienda);
			}
		}
		if (isTriggerBattleRandomizer()) {
			battleRandomizer();
			triggerBattleRandomizer = false;
		}
		
		if (isTriggerShop()) {
			cargarTienda();
		}
		
		
		if (isMenuOpened) {
			batch.draw(gameMenu, getPlayer().getX() + 80, getPlayer().getY() - 100, 320, 400);
			
			if (menu == MenuOption.Main) {
			menuFont.draw(batch, "Dinero: " + heroe.getDinero() + "G", getPlayer().getX() + 100, getPlayer().getY() - 60);
			menuFont.draw(batch, "Nivel: " + heroe.getNv(), getPlayer().getX() + 100, getPlayer().getY() - 30);
			
			menuFont.draw(batch, "Cerrar Menú", getPlayer().getX() + 150, getPlayer().getY() + 250);
			menuFont.draw(batch, "Equipamiento", getPlayer().getX() + 150, getPlayer().getY() + 200);
			menuFont.draw(batch, "Salir del juego", getPlayer().getX() + 150, getPlayer().getY() + 150);
			
			if (menuIndex == 0) {
				batch.draw(menuPointer, getPlayer().getX() + 100, getPlayer().getY() + 230, 16, 16);
				
				if (Gdx.input.isKeyJustPressed(Keys.Z)) {
					isMenuOpened = false;
					menuIndex = 0;
				} else if (Gdx.input.isKeyJustPressed(Keys.S)) {
					menuIndex = 1;
				}
				
			} else if (menuIndex == 1) {
				batch.draw(menuPointer, getPlayer().getX() + 100, getPlayer().getY() + 190, 16, 16);
				
				if ((Gdx.input.isKeyJustPressed(Keys.Z)) && (heroe.getEquipacion().size() > 0)) {
					menu = MenuOption.Equipacion;
					equipationIndex = 0;
					
				} else if (Gdx.input.isKeyJustPressed(Keys.S)) {
					menuIndex = 2;
				} else if (Gdx.input.isKeyJustPressed(Keys.W)) {
					menuIndex = 0;
				}
				
			} else if (menuIndex == 2) {
				batch.draw(menuPointer, getPlayer().getX() + 100, getPlayer().getY() + 130, 16, 16);
				
				if (Gdx.input.isKeyJustPressed(Keys.Z)) {
					game.dispose();
				} else if (Gdx.input.isKeyJustPressed(Keys.W)) {
					menuIndex = 1;
				}
			}	
			
			
			if (Gdx.input.isKeyJustPressed(Keys.X)) {
				isMenuOpened = false;
				menuIndex = 0;
			}
			
		}	else if (menu == MenuOption.Equipacion) {
			
			menuFont.draw(batch, "Selecciona equipación \npara desequipar: \n" + heroe.getEquipacion().get(equipationIndex).getNombre() + "\n" + heroe.getEquipacion().get(equipationIndex).getPuntosdefensa() + " Puntos de defensa", getPlayer().getX() + 100, getPlayer().getY() + 250);
			
			if ((Gdx.input.isKeyJustPressed(Keys.D)) && (equipationIndex < heroe.getEquipacion().size()-1)) {
				equipationIndex += 1;
			}
			
			else if ((Gdx.input.isKeyJustPressed(Keys.A)) && (equipationIndex > 0)) {
				equipationIndex -= 1;
			}
			
			else if (Gdx.input.isKeyJustPressed(Keys.Z)) {
				if (heroe.getEquipacion().size() > 1) {
				heroe.getEquipacion().get(equipationIndex).desequipar(heroe);
				equipationIndex = 0;
				} else {
					menu = MenuOption.Main;
					heroe.getEquipacion().get(equipationIndex).desequipar(heroe);
				}
			}
			
			else if (Gdx.input.isKeyJustPressed(Keys.X)) {
				menu = MenuOption.Main;
			}
		}
			} else {
					Controller.controlSinThread();
					Controller.control(isMove());
		}
		
		if ((Gdx.input.isKeyJustPressed(Keys.C)) && (!isMenuOpened)) {
			isMenuOpened = true;
			menu = MenuOption.Main;
		} else if ((Gdx.input.isKeyJustPressed(Keys.D)) && (!isMenuOpened) && !Controller.isLockedMovement()) {
			setDirection(LookDirection.Right);
			setMove(true);
		} else if ((Gdx.input.isKeyJustPressed(Keys.A)) && (!isMenuOpened) && !Controller.isLockedMovement()) {
			setDirection(LookDirection.Left);
			setMove(true);
		} else if ((Gdx.input.isKeyJustPressed(Keys.W)) && (!isMenuOpened) && !Controller.isLockedMovement()) {
			setDirection(LookDirection.Up);
			setMove(true);
		} else if ((Gdx.input.isKeyJustPressed(Keys.S)) && (!isMenuOpened) && !Controller.isLockedMovement()) {
			setDirection(LookDirection.Down);
			setMove(true);
		} else if ((!Gdx.input.isKeyPressed(Keys.S)) || (!Gdx.input.isKeyPressed(Keys.W)) || (!Gdx.input.isKeyPressed(Keys.D)) || (!Gdx.input.isKeyJustPressed(Keys.A))) {
			setMove(false);
		}
		
		if (startBattle) {
			game.empezarBatalla();
		}
		
		batch.end();
	}
	
	public enum MenuOption {
		Main,
		Equipacion
	}
	
	public void battleRandomizer() {
		int chance = (int) ((Math.random() * (10 - 0)) + 0);
		if (chance > 5) {
			game.empezarBatalla();
		}
	}
	
	public void cargarTienda() {
		game.getScreen().dispose();
		game.setScreen(new ShopScene(game));
	}

	@Override
	public void dispose() {
		game.setX(player.getX());
		game.setY(player.getY());
		GestionMapa.guardarfichero(map.getCeldas(), "mapa");
		partida.guardarpartida();
		map.dispose();
		gameMenu.dispose();
		menuFont.dispose();
		map.getTileSet().dispose();
		map.getShop().dispose();
		menuPointer.dispose();
		super.dispose();
	}

	public Heroe getHeroe() {
		return heroe;
	}

	public void setHeroe(Heroe heroe) {
		this.heroe = heroe;
	}

	public Avatar getPlayer() {
		return player;
	}

	public void setPlayer(Avatar player) {
		this.player = player;
	}

	public TextureRegion getRegion() {
		return region;
	}

	public void setRegion(TextureRegion region) {
		this.region = region;
	}

	public Overworld getMap() {
		return map;
	}

	public void setMap(Overworld map) {
		this.map = map;
	}

	public Monsterfantasy getGame() {
		return game;
	}

	public void setGame(Monsterfantasy game) {
		this.game = game;
	}

	public boolean isTriggerBattleRandomizer() {
		return triggerBattleRandomizer;
	}

	public void setTriggerBattleRandomizer(boolean triggerBattleRandomizer) {
		this.triggerBattleRandomizer = triggerBattleRandomizer;
	}

	public boolean isTriggerShop() {
		return triggerShop;
	}

	public void setTriggerShop(boolean triggerShop) {
		this.triggerShop = triggerShop;
	}

	public LookDirection getDirection() {
		return direction;
	}

	public void setDirection(LookDirection direction) {
		this.direction = direction;
	}

	public boolean isMove() {
		return move;
	}

	public void setMove(boolean move) {
		this.move = move;
	}
}
