package application;
	

import java.util.List;

import dao.ClienteDAO;
import dao.ClienteJBDC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.ClienteModel;


public class Main extends Application {
	private static Parent mainScreen;
	@Override
	public void start(Stage primaryStage) {
		try {
			ClienteDAO clienteDao = new ClienteJBDC();
			ClienteModel cliente = clienteDao.findById(1);
			System.out.println(cliente.toString());
			cliente.setNome("Lucas");
			if(clienteDao.update(cliente)) {
				cliente = clienteDao.findById(1);
				System.out.println(cliente.toString());
			}
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
