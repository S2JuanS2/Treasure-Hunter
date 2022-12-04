package treasureHunter.treasureHunterApp;

import java.util.HashMap;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.media.AudioClip;

public class Resources {

	public static final String PAUSA = "PAUSA";
	public static final String SOUND_SAVE = "soundSave";
	public static final String SOUND_CHAIN = "soundChain";
	public static final String SOUND_BUY = "soundBuy";
	public static final String SOUND_AMBIENCE = "ambiencePlay";
	public static final String SOUND_AMBIENCE_MENU = "soundAmbience";
	public static final String SOUND_CLICK = "soundClick";
	public static final String SOUND_NO_FUEL = "soundNoFuel";
	public static final String SOUND_DEFEAT = "soundDefeat";
	public static final String SOUND_WIN = "soundWin";
	public static final String SOUND_COLLECT = "soundCollect";
	public static final String SOUND_COLLISION = "soundCollision";
	
	public static final String FONDO = "fondo";
	public static final String FONDO_MENU = "fondoMenu";
	public static final String LOGO = "logo";
	public static final String HAND_HOVER = "handHover";
	public static final String HAND = "hand";
	public static final String ICON_CHEST = "cofre";
	public static final String PARCHMENT = "pergamino";
	public static final String DEFEAT = "derrota";
	public static final String VICTORY = "victoria";
	
	public static final String BACKGROUND_GAS = "gas";
	public static final String BACKGROUND_SAVE = "save";
	public static final String BACKGROUND_IMPROVE_HOOK = "improveHook";
	public static final String BACKGROUND_IMPROVE_ENGINE = "improveEngine";
	public static final String BACKGROUND_PAUSE = "pause";
	public static final String BACKGROUND_MUSIC_PAUSE = "musicPause";
	public static final String BACKGROUND_BACK = "back";
	public static final String BACKGROUND_PLAY = "play";
	public static final String BACKGROUND_GO_DOWN = "goDown";
	
	public static final String BACKGROUND_NEW_GAME = "newGame";
	public static final String BACKGROUND_CONTINUE_GAME = "continueGame";
	public static final String BACKGROUND_EXIT = "exit";
	public static final String BACKGROUND_HELP = "help";
	public static final String BACKGROUND_ABOUT = "about";
	public static final String BACKGROUND_CONTINUE = "continue";
	public static final String BACKGROUND_ABOUT_TEXT = "aboutText";
	public static final String BACKGROUND_HELP_TEXT = "helpText";
	public static final String BACKGROUND_PARCHMENT = "pergaminoName";
	public static final String BACKGROUND_PEN = "pluma";


	
	public HashMap<String, Image> images;
	public HashMap<String, AudioClip> sounds;
	public HashMap<String, Background> background;
	
	Resources(){
		
		images = new HashMap<String,Image>();
		sounds = new HashMap<String,AudioClip>();
		background = new HashMap<String, Background>();
		loadImages();
		loadBackground();
		loadSounds();
		
	}
	
	public HashMap<String, Image> getImages() {
		return images;
	}

	public HashMap<String, AudioClip> getSounds() {
		return sounds;
	}

	public HashMap<String, Background> getBackground() {
		return background;
	}
	
	public void loadBackground() {
			
			background.put(BACKGROUND_GAS, new Background(new BackgroundImage(images.get("gas"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
			
			background.put(BACKGROUND_SAVE, new Background(new BackgroundImage(images.get("save"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
	
			background.put(BACKGROUND_IMPROVE_HOOK, new Background(new BackgroundImage(images.get("cadena"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
	
			background.put(BACKGROUND_IMPROVE_ENGINE, new Background(new BackgroundImage(images.get("llave"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
	
			background.put(BACKGROUND_MUSIC_PAUSE, new Background(new BackgroundImage(images.get("musicPause"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
	
			background.put(BACKGROUND_PAUSE, new Background(new BackgroundImage(images.get("pause"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
	
			background.put(BACKGROUND_PLAY, new Background(new BackgroundImage(images.get("play"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
	
			background.put(BACKGROUND_BACK, new Background(new BackgroundImage(images.get("back"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
	
			background.put(BACKGROUND_GO_DOWN, new Background(new BackgroundImage(images.get("goDown"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
	
			background.put(BACKGROUND_NEW_GAME, new Background(new BackgroundImage(images.get("nuevaPartida"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
	
			background.put(BACKGROUND_CONTINUE_GAME, new Background(new BackgroundImage(images.get("continuarPartida"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
	
			background.put(BACKGROUND_EXIT, new Background(new BackgroundImage(images.get("salir"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
	
			background.put(BACKGROUND_HELP, new Background(new BackgroundImage(images.get("help"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
	
			background.put(BACKGROUND_ABOUT, new Background(new BackgroundImage(images.get("about"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
	
			background.put(BACKGROUND_CONTINUE, new Background(new BackgroundImage(images.get("accept"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
	
			background.put(BACKGROUND_ABOUT_TEXT, new Background(new BackgroundImage(images.get("aboutText"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
	
			background.put(BACKGROUND_HELP_TEXT, new Background(new BackgroundImage(images.get("helpText"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
			
			background.put(BACKGROUND_PARCHMENT, new Background(new BackgroundImage(images.get("pergaminoName"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
	
		}
		
		public void loadImages() {
		
			images.put(FONDO,new Image(getClass().getResource("/res/fondo.png").toExternalForm()));
			images.put(FONDO_MENU, new Image(getClass().getResource("/res/fondoMenu.png").toExternalForm()));
			images.put(LOGO, new Image(getClass().getResource("/res/logo.png").toExternalForm()));
			images.put(HAND, new Image(getClass().getResource("/res/mano.png").toExternalForm()));
			images.put(HAND_HOVER, new Image(getClass().getResource("/res/manoHover.png").toExternalForm()));
			images.put(ICON_CHEST, new Image(getClass().getResource("/res/cofre.png").toExternalForm()));
			images.put(PARCHMENT, new Image(getClass().getResource("/res/pergamino.png").toExternalForm()));
			images.put(VICTORY, new Image(getClass().getResource("/res/victoria.png").toExternalForm()));
			images.put(DEFEAT, new Image(getClass().getResource("/res/derrota.png").toExternalForm()));
			images.put("nuevaPartida", new Image(getClass().getResource("/res/nuevaPartida.png").toExternalForm()));
			images.put("continuarPartida", new Image(getClass().getResource("/res/continuarPartida.png").toExternalForm()));
			images.put("accept", new Image(getClass().getResource("/res/accept.png").toExternalForm()));
			images.put("help", new Image(getClass().getResource("/res/ayuda.png").toExternalForm()));
			images.put("helpText", new Image(getClass().getResource("/res/ayudaText.png").toExternalForm()));
			images.put("about", new Image(getClass().getResource("/res/about.png").toExternalForm()));
			images.put("aboutText", new Image(getClass().getResource("/res/aboutText.png").toExternalForm()));
			images.put("back", new Image(getClass().getResource("/res/volver.png").toExternalForm()));
			images.put("play", new Image(getClass().getResource("/res/play.png").toExternalForm()));
			images.put("pause", new Image(getClass().getResource("/res/pause.png").toExternalForm()));
			images.put("musicPause", new Image(getClass().getResource("/res/musicPause.png").toExternalForm()));
			images.put("pergaminoName", new Image(getClass().getResource("/res/pergaminoName.png").toExternalForm()));
			images.put("salir", new Image(getClass().getResource("/res/salir.png").toExternalForm()));
			images.put("save", new Image(getClass().getResource("/res/save.png").toExternalForm()));
			images.put("goDown", new Image(getClass().getResource("/res/goDown.png").toExternalForm()));
			images.put("gas", new Image(getClass().getResource("/res/gasolina.png").toExternalForm()));
			images.put("cadena", new Image(getClass().getResource("/res/cadena.png").toExternalForm()));
			images.put("pluma", new Image(getClass().getResource("/res/pluma.png").toExternalForm()));
			images.put("llave", new Image(getClass().getResource("/res/llave.png").toExternalForm()));
			images.put("hook", new Image(getClass().getResource("/res/hook.png").toExternalForm()));
			images.put("hook2", new Image(getClass().getResource("/res/hook2.png").toExternalForm()));
			images.put("hook3", new Image(getClass().getResource("/res/hook3.png").toExternalForm()));
			images.put("oro", new Image(getClass().getResource("/res/oro.png").toExternalForm()));
			images.put("diamond", new Image(getClass().getResource("/res/diamante.png").toExternalForm()));
			images.put("iron", new Image(getClass().getResource("/res/hierro.png").toExternalForm()));
			images.put("calavera", new Image(getClass().getResource("/res/calavera.png").toExternalForm()));
			images.put("ruby", new Image(getClass().getResource("/res/ruby.png").toExternalForm()));
			images.put("moneda", new Image(getClass().getResource("/res/moneda.png").toExternalForm()));
			images.put("planta", new Image(getClass().getResource("/res/planta.png").toExternalForm()));
			images.put("saco", new Image(getClass().getResource("/res/saco.png").toExternalForm()));
			images.put("piedra", new Image(getClass().getResource("/res/piedra.png").toExternalForm()));
			images.put("caldero", new Image(getClass().getResource("/res/caldero.png").toExternalForm()));
			
		}
		
		public void loadSounds() {		
			sounds.put(SOUND_CLICK, new AudioClip(getClass().getResource("/res/clickMenu.wav").toExternalForm()));
			sounds.put(SOUND_WIN, new AudioClip (getClass().getResource("/res/win.wav").toExternalForm()));
			sounds.put(SOUND_DEFEAT, new AudioClip (getClass().getResource("/res/loser.wav").toExternalForm()));
			sounds.put(SOUND_BUY, new AudioClip(getClass().getResource("/res/buy.wav").toExternalForm()));
			sounds.put(SOUND_NO_FUEL, new AudioClip(getClass().getResource("/res/noFuel.wav").toExternalForm()));
			sounds.put(SOUND_CHAIN, new AudioClip(getClass().getResource("/res/cadena.wav").toExternalForm()));
			sounds.put(SOUND_AMBIENCE_MENU, new AudioClip(getClass().getResource("/res/ambience.wav").toExternalForm()));
			sounds.put(SOUND_COLLISION, new AudioClip(getClass().getResource("/res/collision.wav").toExternalForm()));
			sounds.put(SOUND_COLLECT, new AudioClip(getClass().getResource("/res/collect.wav").toExternalForm()));
			sounds.put(SOUND_SAVE, new AudioClip(getClass().getResource("/res/save.wav").toExternalForm()));
			sounds.put(SOUND_AMBIENCE, new AudioClip(getClass().getResource("/res/ambiencePlay.wav").toExternalForm()));
		}
		
	}
