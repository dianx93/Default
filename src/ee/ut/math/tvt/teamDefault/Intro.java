package ee.ut.math.tvt.teamDefault;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Intro extends Application {

	private static final Logger log = Logger.getLogger(Intro.class);

	public void start(Stage primaryStage) {
		try {
			log.setLevel(Level.INFO);
			log.addAppender(new FileAppender(new PatternLayout(
					"%d %-5p [%c{1}] %m%n"), "etc\\Team Default.log"));
			Group root = new Group();
			Scene scene = IntroUI.createIntroScene(primaryStage, root);
			primaryStage.setScene(scene);
			primaryStage.show();
			log.info("Intro window opened");
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
