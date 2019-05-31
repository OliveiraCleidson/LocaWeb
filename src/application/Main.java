package application;
	

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.JogoModel;
import model.PlataformaModel;


public class Main extends Application {
	private static Parent mainScreen;
	private static Stage mainWindow;
	@Override
	public void start(Stage primaryStage) {
		try {
			mainScreen = FXMLLoader.load(getClass().getResource("/gui/MainView.fxml"));
			Scene mainScene = new Scene(mainScreen);
			primaryStage.setScene(mainScene);
			primaryStage.setTitle("Locadora de Jogos");
			//PlataformaModel.insertPlataformaBD("XBOX 360");
			//JogoModel.insertJogoBD("FIFA 19");
			mainWindow = primaryStage;
			primaryStage.show();
		} catch(Exception	 e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static Stage getWindow() {
		return mainWindow;
	}
}
