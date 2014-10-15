package ee.ut.math.tvt.teamDefault;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class IntroUI {

	public static Scene createIntroScene(Stage stage, Group root) throws FileNotFoundException {
		Scene scene = new Scene(root, 340, 505);
		VBox infoBox = new VBox();
		infoBox.setPadding(new Insets(20));
		
		Properties prop = new Properties();
		InputStream input = null;
	 
		try {
			input = new FileInputStream("res\\application.properties");
			prop.load(input);
			infoBox.getChildren().add(new Label(prop.getProperty("Team.Name")));
			infoBox.getChildren().add(new Label());
			infoBox.getChildren().add(new Label("Team Leader"));
			infoBox.getChildren().add(new Label(prop.getProperty("Team.Leader")));
			infoBox.getChildren().add(new Label(prop.getProperty("Team.Leader.Mail")));
			infoBox.getChildren().add(new Label());
			infoBox.getChildren().add(new Label("Team"));
			infoBox.getChildren().add(new Label(prop.getProperty("Team.Member1")));
			infoBox.getChildren().add(new Label(prop.getProperty("Team.Member2")));
			
			input = new FileInputStream("res\\version.properties");
			prop.load(input);
			infoBox.getChildren().add(new Label());
			infoBox.getChildren().add(new Label("Version: "+prop.getProperty("build.number")));
			
			input = new FileInputStream("res\\application.properties");
			infoBox.getChildren().add(new ImageView(new Image(new FileInputStream(prop.getProperty("Team.Logo")))));
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		root.getChildren().add(infoBox);
		return scene;
	}

}
