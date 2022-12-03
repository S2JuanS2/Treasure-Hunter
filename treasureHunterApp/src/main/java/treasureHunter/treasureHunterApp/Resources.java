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
			
			background.put("gas", new Background(new BackgroundImage(images.get("gas"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
			
			background.put("save", new Background(new BackgroundImage(images.get("save"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
	
			background.put("improveHook", new Background(new BackgroundImage(images.get("cadena"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
	
			background.put("improveEngine", new Background(new BackgroundImage(images.get("llave"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
	
			background.put("musicPause", new Background(new BackgroundImage(images.get("musicPause"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
	
			background.put("pause", new Background(new BackgroundImage(images.get("pause"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
	
			background.put("play", new Background(new BackgroundImage(images.get("play"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
	
			background.put("back", new Background(new BackgroundImage(images.get("back"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
	
			background.put("goDown", new Background(new BackgroundImage(images.get("goDown"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
	
			background.put("newGame", new Background(new BackgroundImage(images.get("nuevaPartida"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
	
			background.put("continueGame", new Background(new BackgroundImage(images.get("continuarPartida"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
	
			background.put("exit", new Background(new BackgroundImage(images.get("salir"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
	
			background.put("help", new Background(new BackgroundImage(images.get("help"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
	
			background.put("about", new Background(new BackgroundImage(images.get("about"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
	
			background.put("continue", new Background(new BackgroundImage(images.get("accept"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
	
			background.put("aboutText", new Background(new BackgroundImage(images.get("aboutText"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
	
			background.put("helpText", new Background(new BackgroundImage(images.get("helpText"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
			
			background.put("pergaminoName", new Background(new BackgroundImage(images.get("pergaminoName"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
	
		}
		
		public void loadImages() {
		
			images.put("oro", new Image(getClass().getResource("/res/oro.png").toExternalForm()));
			images.put("fondo",new Image(getClass().getResource("/res/fondo.png").toExternalForm()));
			images.put("hook", new Image(getClass().getResource("/res/hook.png").toExternalForm()));
			images.put("hook2", new Image(getClass().getResource("/res/hook2.png").toExternalForm()));
			images.put("hook3", new Image(getClass().getResource("/res/hook3.png").toExternalForm()));
			images.put("diamond", new Image(getClass().getResource("/res/diamante.png").toExternalForm()));
			images.put("iron", new Image(getClass().getResource("/res/hierro.png").toExternalForm()));
			images.put("calavera", new Image(getClass().getResource("/res/calavera.png").toExternalForm()));
			images.put("ruby", new Image(getClass().getResource("/res/ruby.png").toExternalForm()));
			images.put("gas", new Image(getClass().getResource("/res/gasolina.png").toExternalForm()));
			images.put("save", new Image(getClass().getResource("/res/save.png").toExternalForm()));
			images.put("hand", new Image(getClass().getResource("/res/mano.png").toExternalForm()));
			images.put("fondoMenu", new Image(getClass().getResource("/res/fondoMenu.png").toExternalForm()));
			images.put("logo", new Image(getClass().getResource("/res/logo.png").toExternalForm()));
			images.put("nuevaPartida", new Image(getClass().getResource("/res/nuevaPartida.png").toExternalForm()));
			images.put("continuarPartida", new Image(getClass().getResource("/res/continuarPartida.png").toExternalForm()));
			images.put("salir", new Image(getClass().getResource("/res/salir.png").toExternalForm()));
			images.put("handHover", new Image(getClass().getResource("/res/manoHover.png").toExternalForm()));
			images.put("moneda", new Image(getClass().getResource("/res/moneda.png").toExternalForm()));
			images.put("cofre", new Image(getClass().getResource("/res/cofre.png").toExternalForm()));
			images.put("planta", new Image(getClass().getResource("/res/planta.png").toExternalForm()));
			images.put("cadena", new Image(getClass().getResource("/res/cadena.png").toExternalForm()));
			images.put("llave", new Image(getClass().getResource("/res/llave.png").toExternalForm()));
			images.put("saco", new Image(getClass().getResource("/res/saco.png").toExternalForm()));
			images.put("piedra", new Image(getClass().getResource("/res/piedra.png").toExternalForm()));
			images.put("caldero", new Image(getClass().getResource("/res/caldero.png").toExternalForm()));
			images.put("pergamino", new Image(getClass().getResource("/res/pergamino.png").toExternalForm()));
			images.put("victoria", new Image(getClass().getResource("/res/victoria.png").toExternalForm()));
			images.put("derrota", new Image(getClass().getResource("/res/derrota.png").toExternalForm()));
			images.put("accept", new Image(getClass().getResource("/res/accept.png").toExternalForm()));
			images.put("help", new Image(getClass().getResource("/res/ayuda.png").toExternalForm()));
			images.put("about", new Image(getClass().getResource("/res/about.png").toExternalForm()));
			images.put("aboutText", new Image(getClass().getResource("/res/aboutText.png").toExternalForm()));
			images.put("helpText", new Image(getClass().getResource("/res/ayudaText.png").toExternalForm()));
			images.put("back", new Image(getClass().getResource("/res/volver.png").toExternalForm()));
			images.put("pause", new Image(getClass().getResource("/res/pause.png").toExternalForm()));
			images.put("musicPause", new Image(getClass().getResource("/res/musicPause.png").toExternalForm()));
			images.put("pergaminoName", new Image(getClass().getResource("/res/pergaminoName.png").toExternalForm()));
			images.put("play", new Image(getClass().getResource("/res/play.png").toExternalForm()));
			images.put("pluma", new Image(getClass().getResource("/res/pluma.png").toExternalForm()));
			images.put("goDown", new Image(getClass().getResource("/res/goDown.png").toExternalForm()));
			
		}
		
		public void loadSounds() {		
			sounds.put("soundClick", new AudioClip(getClass().getResource("/res/clickMenu.wav").toExternalForm()));
			sounds.put("soundWin", new AudioClip (getClass().getResource("/res/win.wav").toExternalForm()));
			sounds.put("soundDefeat", new AudioClip (getClass().getResource("/res/loser.wav").toExternalForm()));
			sounds.put("soundBuy", new AudioClip(getClass().getResource("/res/buy.wav").toExternalForm()));
			sounds.put("soundNoFuel", new AudioClip(getClass().getResource("/res/noFuel.wav").toExternalForm()));
			sounds.put("soundChain", new AudioClip(getClass().getResource("/res/cadena.wav").toExternalForm()));
			sounds.put("soundAmbience", new AudioClip(getClass().getResource("/res/ambience.wav").toExternalForm()));
			sounds.put("soundCollision", new AudioClip(getClass().getResource("/res/collision.wav").toExternalForm()));
			sounds.put("soundCollect", new AudioClip(getClass().getResource("/res/collect.wav").toExternalForm()));
			sounds.put("soundSave", new AudioClip(getClass().getResource("/res/save.wav").toExternalForm()));
			sounds.put("ambiencePlay", new AudioClip(getClass().getResource("/res/ambiencePlay.wav").toExternalForm()));
		}
		
	}
