package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class infoSputnikController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonExit;

    @FXML
    private TextField fieldApag;

    @FXML
    private TextField fieldNumb;

    @FXML
    private TextField fieldPereg;
    
    @FXML
    private TextField fieldT;

    @FXML
    private TextField fieldPeriod;

    @FXML
    private TextField fieldSpeed;

    @FXML
    private TextField fieldTitle;

    @FXML
    private TextField fieldType;

    @FXML
    private ImageView mainView;

    Connection connection;
    
    @FXML
    void on_click_exit(ActionEvent event) {
    	buttonExit.getScene().getWindow().hide();
    }

    @FXML
    void initialize() throws ClassNotFoundException, SQLException {
    	mainView.setImage(new Image(getClass().getResourceAsStream("/resourses/cosmosSP.png")));
    
		dbConnect connector=new dbConnect();
		connection = connector.getConnection();
		fieldT.setText(connector.getDate("1","select id_sputnik, title_sputnik from sputnik", "title_sputnik"));
		fieldType.setText(connector.getDate("1","select id_sputnik, type_sputnik from sputnik", "type_sputnik"));
		fieldNumb.setText(connector.getDate("1","select id_sputnik, internumb_sputnik from sputnik", "internumb_sputnik"));
		fieldApag.setText(connector.getDate("1","select id_sputnik, apagey from sputnik", "apagey")+" км");
		fieldPereg.setText(connector.getDate("1","select id_sputnik, peregey from sputnik", "peregey")+" км");
		fieldSpeed.setText(connector.getDate("1","select id_sputnik, speed_sputnik from sputnik", "speed_sputnik")+" км/с");
		fieldPeriod.setText(connector.getDate("1","select id_sputnik, period from sputnik", "period")+" мин");

    }

}
