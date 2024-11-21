module Diplom {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires javafx.graphics;
	requires javafx.base;
	requires java.desktop;
	
	opens application to javafx.graphics, javafx.fxml, javafx.base;
}
