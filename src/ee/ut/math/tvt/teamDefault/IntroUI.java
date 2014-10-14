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
			input = new FileInputStream("application.properties");
			prop.load(input);
			infoBox.getChildren().add(new Label(prop.getProperty("teamName")));
			infoBox.getChildren().add(new Label());
			infoBox.getChildren().add(new Label("Team Leader"));
			infoBox.getChildren().add(new Label(prop.getProperty("TeamLeader")));
			infoBox.getChildren().add(new Label(prop.getProperty("TeamLeaderMail")));
			infoBox.getChildren().add(new Label());
			infoBox.getChildren().add(new Label("Team"));
			infoBox.getChildren().add(new Label(prop.getProperty("TeamMember1")));
			infoBox.getChildren().add(new Label(prop.getProperty("TeamMember2")));
			
			input = new FileInputStream("version.properties");
			prop.load(input);
			infoBox.getChildren().add(new Label());
			infoBox.getChildren().add(new Label("Version: "+prop.getProperty("build.number")));
			
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
		
		infoBox.getChildren().add(new ImageView(new Image(new FileInputStream("lib\\default.gif"))));
		root.getChildren().add(infoBox);
		return scene;
	}

}
