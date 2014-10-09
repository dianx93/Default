package ee.ut.math.tvt.teamDefault;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
		Scene scene = new Scene(root, 300, 400);
		VBox infoBox = new VBox();
		infoBox.setPadding(new Insets(20));
		Scanner fromIntroText = new Scanner(new File("lib\\intro.txt")); //need peaks olema properties failis, mitte txt
		for (int i = 0; i <9 ; i++) {
			infoBox.getChildren().add(new Label(fromIntroText.nextLine()));
		}
		fromIntroText.close();
		infoBox.getChildren().add(new ImageView(new Image(new FileInputStream("lib\\default.gif"))));
		root.getChildren().add(infoBox);
		//version number
		return scene;
	}

}
