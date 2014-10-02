package ee.ut.math.tvt.teamDefault;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Intro extends Application {
	
	public void start(Stage primaryStage) {
		try {
			Group root = new Group();
			Scene scene = IntroUI.createIntroScene(primaryStage, root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
