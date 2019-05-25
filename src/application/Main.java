package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	private static Parent mainScreen;
	@Override
	public void start(Stage primaryStage) {
		try {
			mainScreen = FXMLLoader.load(getClass().getResource("/gui/MainView.fxml"));
			Scene mainScene = new Scene(mainScreen);
			primaryStage.setScene(mainScene);
			primaryStage.show();
		} catch(Exception	 e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
