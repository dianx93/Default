package ee.ut.math.tvt.teamDefault;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.controller.impl.SalesDomainControllerImpl;
import ee.ut.math.tvt.salessystem.ui.ConsoleUI;
import ee.ut.math.tvt.salessystem.ui.SalesSystemUI;

public class Intro extends Application {

	private static final Logger log = Logger.getLogger(Intro.class);
	private static final String MODE = "console";

	public void start(Stage primaryStage) {

		final SalesDomainController domainController = new SalesDomainControllerImpl();

		try {
			if (getParameters().getUnnamed().size() == 1
					&& getParameters().getUnnamed().get(0).equals(MODE)) {
				log.debug("Mode: " + MODE);

				ConsoleUI cui = new ConsoleUI(domainController);
				cui.run();
			} else {
				Group root = new Group();
				Scene scene = IntroUI.createIntroScene(primaryStage, root);
				primaryStage.setScene(scene);
				primaryStage.show();
				log.info("Intro window opened");
				final SalesSystemUI ui = new SalesSystemUI(domainController);
				ui.setVisible(true);
				primaryStage.close();
				
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
