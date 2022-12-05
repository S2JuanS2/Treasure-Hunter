package treasureHunter.treasureHunterApp;

import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Interactable {

	public static final int MAX_LENGTH = 8;
	public static final int BTN_ACTIONS_X = 20;
	public static final int BTN_ACTIONS_Y = 28;
	public static final int BTN_SIZE = 32;
	public static final int BTN_SETTINGS_X = 600;
	
	private Button btnBuyFuel;
	private Button btnBuyImproveHook;
	private Button btnBuyImprovePower;
	private Button btnSave;
	private Button btnPause;
	private Button btnBack;
	private Button btnBackGame;
	private Button btnMusicPause;
	private Button btnNewGame;
	private Button btnContinueGame;
	private Button btnExitGame;
	private Button btnHelp;
	private Button btnAbout;
	private Button btnContinue; 
	private Button btnPlayGame;
	private Button btnGoDown;
	private Button btnStart;
	private Button btnFinish;
	private Label lbHelp;
	private Label lbAbout;
	private Label lbPause;
	private Label lbError;
	private TextField name;
	
	public Interactable() {
		
		btnNewGame = new Button();
		btnContinueGame = new Button();
		btnExitGame = new Button();
		btnHelp = new Button();
		btnAbout = new Button();
		btnContinue = new Button();
		btnStart = new Button();
		btnFinish = new Button();
		
		lbAbout = new Label();
		lbHelp = new Label();
		lbError = new Label();
		name = new TextField();
		
		btnBuyFuel = new Button();
		btnSave = new Button();
		btnBuyImproveHook = new Button();
		btnBuyImprovePower = new Button();
		btnPause = new Button();
		btnBack = new Button();
		btnBackGame = new Button();
		btnMusicPause = new Button();
		btnPlayGame = new Button();
		btnGoDown = new Button();
		lbPause = new Label(Resources.PAUSA);
	}
	
	public String getTextField() {
		return(name.getText());
	}
	public Button getBtnBuyFuel() {
		return btnBuyFuel;
	}
	public Button getBtnBuyImproveHook() {
		return btnBuyImproveHook;
	}
	public Button getBtnBuyImprovePower() {
		return btnBuyImprovePower;
	}
	public Button getBtnSave() {
		return btnSave;
	}
	public Button getBtnPause() {
		return btnPause;
	}
	public Button getBtnGoDown() {
		return btnGoDown;
	}
	public Button getBtnBack() {
		return btnBack;
	}
	public Button getBtnBackGame() {
		return btnBackGame;
	}
	public Button getBtnMusicPause() {
		return btnMusicPause;
	}
	public Button getBtnNewGame() {
		return btnNewGame;
	}
	public Button getBtnContinueGame() {
		return btnContinueGame;
	}
	public Button getBtnExitGame() {
		return btnExitGame;
	}
	public Button getBtnHelp() {
		return btnHelp;
	}
	public Button getBtnAbout() {
		return btnAbout;
	}
	public Button getBtnContinue() {
		return btnContinue;
	}
	public Button getBtnPlayGame() {
		return btnPlayGame;
	}
	public Button getBtnStart() {
		return btnStart;
	}
	public Button getBtnFinish() {
		return btnFinish;
	}
	public Label getLbHelp() {
		return lbHelp;
	}
	public Label getLbAbout() {
		return lbAbout;
	}
	public Label getLbPause() {
		return lbPause;
	}
	public Label getLbError() {
		return lbError;
	}
	public TextField getName() {
		return name;
	}
	
	/*
	 * DEVUELVE TRUE SI EL NOMBRE DADO POR EL USUARIO ESTÁ VACÍO O TIENE
	 * MAS CARACTERES DE LO PERMITIDO
	 */
	public boolean errorTextField() {
		return (!(name.getLength() <= MAX_LENGTH && getTextField() != ""));
	}
	
	/*
	 * APLICA ESTILO A LOS BOTONES DEL JUEGO
	 */
	public void generateGameButtonStyle(Resources resources) {
		
		btnBuyFuel.setLayoutX(BTN_ACTIONS_X);
		btnBuyFuel.setLayoutY(BTN_ACTIONS_Y);
		btnBuyFuel.setPrefSize(BTN_SIZE-5, BTN_SIZE);
		btnBuyFuel.setBackground(resources.getBackground().get(Resources.BACKGROUND_GAS));
		btnBuyFuel.setCursor(new ImageCursor(resources.getImages().get(Resources.HAND_HOVER)));
				
		btnSave.setLayoutX(BTN_ACTIONS_X+40);
		btnSave.setLayoutY(BTN_ACTIONS_Y);
		btnSave.setPrefSize(BTN_SIZE-4, BTN_SIZE);
		btnSave.setBackground(resources.getBackground().get(Resources.BACKGROUND_SAVE));
		btnSave.setCursor(new ImageCursor(resources.getImages().get(Resources.HAND_HOVER)));
		
		btnBuyImproveHook.setLayoutX(BTN_ACTIONS_X+80);
		btnBuyImproveHook.setLayoutY(BTN_ACTIONS_Y);
		btnBuyImproveHook.setPrefSize(BTN_SIZE, BTN_SIZE);
		btnBuyImproveHook.setBackground(resources.getBackground().get(Resources.BACKGROUND_IMPROVE_HOOK));
		btnBuyImproveHook.setCursor(new ImageCursor(resources.getImages().get(Resources.HAND_HOVER)));
		
		btnBuyImprovePower.setLayoutX(BTN_ACTIONS_X+120);
		btnBuyImprovePower.setLayoutY(BTN_ACTIONS_Y);
		btnBuyImprovePower.setPrefSize(BTN_SIZE, BTN_SIZE);
		btnBuyImprovePower.setBackground(resources.getBackground().get(Resources.BACKGROUND_IMPROVE_ENGINE));
		btnBuyImprovePower.setCursor(new ImageCursor(resources.getImages().get(Resources.HAND_HOVER)));
		
		btnPause.setLayoutX(573);
		btnPause.setLayoutY(0);
		btnPause.setPrefSize(BTN_SIZE, BTN_SIZE);
		btnPause.setScaleX(0.7);
		btnPause.setScaleY(0.7);
		btnPause.setBackground(resources.getBackground().get(Resources.BACKGROUND_PAUSE));
		btnPause.setCursor(new ImageCursor(resources.getImages().get(Resources.HAND_HOVER)));
		
		btnMusicPause.setLayoutX(BTN_SETTINGS_X);
		btnMusicPause.setLayoutY(0);
		btnMusicPause.setPrefSize(BTN_SIZE, BTN_SIZE);
		btnMusicPause.setScaleX(0.6);
		btnMusicPause.setScaleY(0.6);
		btnMusicPause.setBackground(resources.getBackground().get(Resources.BACKGROUND_MUSIC_PAUSE));
		btnMusicPause.setCursor(new ImageCursor(resources.getImages().get(Resources.HAND_HOVER)));
		
		btnBackGame.setLayoutX(BTN_SETTINGS_X);
		btnBackGame.setLayoutY(35);
		btnBackGame.setScaleX(0.8);
		btnBackGame.setScaleY(0.8);
		btnBackGame.setPrefSize(BTN_SIZE, BTN_SIZE);
		btnBackGame.setBackground(resources.getBackground().get(Resources.BACKGROUND_BACK));
		btnBackGame.setCursor(new ImageCursor(resources.getImages().get(Resources.HAND_HOVER)));
		
		btnPlayGame.setLayoutX(270);
		btnPlayGame.setLayoutY(20);
		btnPlayGame.setPrefSize(120,50);
		btnPlayGame.setScaleX(0.8);
		btnPlayGame.setScaleY(0.8);
		btnPlayGame.setBackground(resources.getBackground().get(Resources.BACKGROUND_PLAY));
		btnPlayGame.setCursor(new ImageCursor(resources.getImages().get(Resources.HAND_HOVER)));
		
		btnGoDown.setLayoutX(200);
		btnGoDown.setLayoutY(28);
		btnGoDown.setPrefSize(BTN_SIZE,BTN_SIZE);
		btnGoDown.setBackground(resources.getBackground().get(Resources.BACKGROUND_GO_DOWN));
		btnGoDown.setCursor(new ImageCursor(resources.getImages().get(Resources.HAND_HOVER)));
		
		lbPause.setLayoutX(320);
		lbPause.setLayoutY(90);
		lbPause.setTextFill(Color.WHITE);
		
	}
	
	/*
	 * APLICA ESTILO A LOS BOTONES DEL MENU
	 */
	public void generateMenuButtonStyle(Resources resources) {
		
		btnNewGame.setPrefSize(155, 22);
		btnNewGame.setBackground(resources.getBackground().get(Resources.BACKGROUND_NEW_GAME));
		btnNewGame.setCursor(new ImageCursor(resources.getImages().get(Resources.HAND_HOVER)));
		btnNewGame.setLayoutX(240);
		btnNewGame.setLayoutY(260);
		
		btnContinueGame.setPrefSize(190, 22);
		btnContinueGame.setBackground(resources.getBackground().get(Resources.BACKGROUND_CONTINUE_GAME));
		btnContinueGame.setCursor(new ImageCursor(resources.getImages().get(Resources.HAND_HOVER)));
		btnContinueGame.setLayoutX(240);
		btnContinueGame.setLayoutY(300);
		
		btnExitGame.setPrefSize(60, 22);
		btnExitGame.setBackground(resources.getBackground().get(Resources.BACKGROUND_EXIT));
		btnExitGame.setCursor(new ImageCursor(resources.getImages().get(Resources.HAND_HOVER)));
		btnExitGame.setLayoutX(240);
		btnExitGame.setLayoutY(420);
		
		btnHelp.setPrefSize(75, 22);
		btnHelp.setBackground(resources.getBackground().get(Resources.BACKGROUND_HELP));
		btnHelp.setCursor(new ImageCursor(resources.getImages().get(Resources.HAND_HOVER)));
		btnHelp.setLayoutX(240);
		btnHelp.setLayoutY(340);
		
		btnAbout.setPrefSize(100, 22);
		btnAbout.setBackground(resources.getBackground().get(Resources.BACKGROUND_ABOUT));
		btnAbout.setCursor(new ImageCursor(resources.getImages().get(Resources.HAND_HOVER)));
		btnAbout.setLayoutX(240);
		btnAbout.setLayoutY(380);
		
		btnContinue.setPrefSize(BTN_SIZE,BTN_SIZE);
		btnContinue.setLayoutX(520);
		btnContinue.setLayoutY(410);
		btnContinue.setBackground(resources.getBackground().get(Resources.BACKGROUND_CONTINUE));
		btnContinue.setCursor(new ImageCursor(resources.getImages().get(Resources.HAND_HOVER))); //general
		
		btnFinish.setPrefSize(BTN_SIZE,BTN_SIZE);
		btnFinish.setLayoutX(420);
		btnFinish.setLayoutY(340);
		btnFinish.setBackground(resources.getBackground().get(Resources.BACKGROUND_CONTINUE));
		btnFinish.setCursor(new ImageCursor(resources.getImages().get(Resources.HAND_HOVER)));
		
		btnStart.setLayoutX(520);
		btnStart.setLayoutY(410);
		btnStart.setPrefSize(BTN_SIZE,BTN_SIZE);
		btnStart.setBackground(resources.getBackground().get(Resources.BACKGROUND_CONTINUE));
		btnStart.setCursor(new ImageCursor(resources.getImages().get(Resources.HAND_HOVER)));
		
		btnBack.setLayoutX(562);
		btnBack.setLayoutY(410);
		btnBack.setPrefSize(BTN_SIZE,BTN_SIZE);
		btnBack.setBackground(resources.getBackground().get(Resources.BACKGROUND_BACK));
		btnBack.setCursor(new ImageCursor(resources.getImages().get(Resources.HAND_HOVER)));
		
		lbAbout.setBackground(resources.getBackground().get(Resources.BACKGROUND_ABOUT_TEXT));
		lbAbout.setPrefSize(350, 300);
		lbAbout.setLayoutX(150);
		lbAbout.setLayoutY(300);
		
		lbHelp.setBackground(resources.getBackground().get(Resources.BACKGROUND_HELP_TEXT));
		lbHelp.setPrefSize(640, 115);
		lbHelp.setLayoutX(0);
		lbHelp.setLayoutY(300);
		
		lbError.setLayoutX(135);
		lbError.setLayoutY(420);
		lbError.setFont(Font.font(14));
		lbError.setTextFill(Color.DARKRED);
		
		name.setBackground(resources.getBackground().get(Resources.BACKGROUND_PARCHMENT));
		name.setPrefSize(300,140);
		name.setLayoutX(150);
		name.setLayoutY(280);
		name.setFont(new Font(28));
		name.setAlignment(Pos.CENTER);
		name.setCursor(new ImageCursor(resources.getImages().get(Resources.BACKGROUND_PEN)));

	}
	
}
