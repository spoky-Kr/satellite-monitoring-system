package application;


import java.io.IOException;
import java.util.Timer;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.AmbientLight;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;





public class Main extends Application {
	private static final float HEIGHT = 800;
	private static final float WIDTH = 1400;
	
	private double anchorX, anchorY;
	private double anchorAngleX=0;
	private double anchorAngleY=0;
	private final DoubleProperty angleX = new SimpleDoubleProperty(0);
	private final DoubleProperty angleY = new SimpleDoubleProperty(0);
	private final Sphere sphere=new Sphere(100);
	private final TilePane tilePane = new TilePane();
	private final ToggleGroup tg = new ToggleGroup();
	private final RadioButton r = new RadioButton(" Реальная скорость");
	private final RadioButton r1 = new RadioButton(" 500x Ускорение");
	private final RadioButton r2 = new RadioButton(" 1000x Ускорение");
	private final RadioButton r3 = new RadioButton(" 5000x Ускорение");
	private final String timeInterval[]= {"10.06.23  00:00:00 MSK","11.06.23  00:00:00 MSK",
			 "12.06.23  00:00:00 MSK","13.06.23  00:00:00 MSK",
			 "14.06.23  00:00:00 MSK","15.06.23  00:00:00 MSK"};
	private final ComboBox<String> boxTime=new ComboBox<String>(FXCollections.observableArrayList(timeInterval));
	private final TilePane tilePaneTime = new TilePane(boxTime);
	private String sel="";
	private final Timer tt=new Timer();


	
	@Override
	public void start(Stage primaryStage) {
		boxTime.setPrefHeight(10);
		boxTime.setPrefWidth(300);
		
		
		
		
		Label exp = new Label(" Реальная скорость");
		Label exp1 = new Label(" 500x Ускорение");
		Label exp2 = new Label(" 1000x Ускорение");
		Label exp3 = new Label(" 5000x Ускорение");
		exp1.getStyleClass().add("explanation");
		exp2.getStyleClass().add("explanation");
		exp3.getStyleClass().add("explanation");
		exp.getStyleClass().add("explanation");
		r.setGraphic(exp);
		r1.setGraphic(exp1);
		r2.setGraphic(exp2);
		r3.setGraphic(exp3);
		

		r.setToggleGroup(tg);
		r1.setToggleGroup(tg);
		r2.setToggleGroup(tg);
		r3.setToggleGroup(tg);
		
		tilePane.getChildren().add(r);
		tilePane.getChildren().add(r1);
		tilePane.getChildren().add(r2);
		tilePane.getChildren().add(r3);
		//tilePane.setLayoutX(-700);
		//tilePane.setLayoutY(-500);
		tilePane.setTranslateX(-1100);
		tilePane.setTranslateY(-600);
		tilePane.setTranslateZ(500);
		
		tilePaneTime.setTranslateX(+800);
		tilePaneTime.setTranslateY(-600);
		tilePaneTime.setTranslateZ(500);
		
		 Camera camera = new PerspectiveCamera(true);
		 camera.setNearClip(1);
		 camera.setFarClip(20000);
		 camera.translateZProperty().set(-2000);
		
		 
		 
		
		 
		 
		 SmartGroup world = new SmartGroup();
		 world.getChildren().add(prepareEarth());
		 //world.getChildren().add(new AmbientLight());
		 world.getChildren().add(prepareSputnikY("1", 300, 0, 0));
		// world.getChildren().add(prepareSputnikY("Второй", -303, 10, 10));
		// world.getChildren().add(prepareSputnikY("Третий", 0, -10, 308));
		 world.getChildren().add(prepareSputnikX("2", 0, 0, 300));
		 world.getChildren().add(prepareSputnikX("3", 10, 0, -304));
		 world.getChildren().add(prepareSputnikX("4", -10, 308, 0));
		 world.getChildren().add(prepareSputnikZ("5", 1, 300, 0));
		 world.getChildren().add(prepareSputnikZ("6", 1, -303, 10));
		 world.getChildren().add(prepareSputnikZ("7", 308, 0, -10));

		 Group root = new Group();
		 root.getChildren().add(prepareImageView());
		 root.getChildren().add(world);
		 root.getChildren().add(tilePane);
		 root.getChildren().add(tilePaneTime);
		 root.getChildren().add(new AmbientLight());
		
		
		 
		 Scene scene = new Scene(root, WIDTH, HEIGHT, true);
		 scene.setFill(Color.SILVER);
		 scene.setCamera(camera);
		 scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		 initMouseControl(world, scene, primaryStage);
		 
		primaryStage.setTitle("Земля v1.0");
		primaryStage.setScene(scene);
		//primaryStage.setOpacity(0.1);
		primaryStage.show();
		
		prepareAnimation();
	}
	
	
	private Node prepareLigthSource() {
		PointLight pointLight = new PointLight();
	//	pointLight.getTransforms().add(new Translate(0,0,500));
		return pointLight;
	}


	private void prepareAnimation() {
		AnimationTimer timer = new AnimationTimer() {
	
			public void handle(long t) {
				
				switch(SelectRadioButton()) {
				case " Реальная скорость": 
					sphere.rotateProperty().set(sphere.getRotate()-0.00007);
				break;
				
				case " 500x Ускорение":
					sphere.rotateProperty().set(sphere.getRotate()-0.035);
				break;
				
				case " 1000x Ускорение":
					sphere.rotateProperty().set(sphere.getRotate()-0.07);
				break;
				
				case " 5000x Ускорение":
					sphere.rotateProperty().set(sphere.getRotate()-0.35);
				break;
				
					
				}

			}
			
			
		};
		timer.start();
		
	}
	
	private String SelectRadioButton() {
		
		tg.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            
			@Override
           public void changed(ObservableValue<? extends Toggle> changed, Toggle oldValue, Toggle newValue){
                
               // получаем выбранный элемент RadioButton
               RadioButton selectedBtn = (RadioButton) newValue;
               sel=selectedBtn.getText();
               
           }
       });
		return sel;
	}
	private Node prepareSputnikX(String title, Integer valX, Integer valY, Integer valZ) {
		Sputnik s = new Sputnik(title, valX, valY, valZ);
		//s.displayInfo();
		Sphere sputnik=new Sphere(5);
		sputnik.getTransforms().add(new Translate(valX, valY, valZ));
		PhongMaterial materialSputnik = new PhongMaterial();
		materialSputnik.setSelfIlluminationMap(new Image(getClass().getResourceAsStream("/resourses/white.jpg")));
		sputnik.setMaterial(materialSputnik);
		
		sputnik.setOnMouseClicked(event->{ LinkWindow(title); });
		AnimationTimer timer = new AnimationTimer() {
		
			@Override
			public void handle(long arg0) {
				sputnik.setRotationAxis(Rotate.X_AXIS);
				SputnikMoving(sputnik);
				
			}
			
			
		};
		
		timer.start();
		
		
		
		return sputnik;
		
	}
	
	void LinkWindow(String title) {
		switch(title) {
		case "1": 
			downloadWindow("/WorkWindows/infoSputnik.fxml");
		break;
		case "2": 
			downloadWindow("/WorkWindows/infoSputnik2.fxml");
		break;
		case "3": 
			downloadWindow("/WorkWindows/infoSputnik3.fxml");
		break;
		case "4": 
			downloadWindow("/WorkWindows/infoSputnik4.fxml");
		break;
		case "5": 
			downloadWindow("/WorkWindows/infoSputnik5.fxml");
		break;
		case "6": 
			downloadWindow("/WorkWindows/infoSputnik6.fxml");
		break;
		case "7": 
			downloadWindow("/WorkWindows/infoSputnik7.fxml");
		break;
		}
	}
    void downloadWindow(String WayWindow) {
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(getClass().getResource(WayWindow));
	    try {
		    loader.load();
	    } catch (IOException e) {
		    e.printStackTrace();
	    }
	    Parent root = loader.getRoot();
	    Stage stage = new Stage();
	    stage.setTitle("Информация о спутнике");
	    stage.setScene(new Scene(root,500,432));
        
	    stage.show();
    }
    
    void SputnikMoving(Sphere sputnik) {
		switch(SelectRadioButton()) {
		case " Реальная скорость": 
			sputnik.rotateProperty().set(sputnik.getRotate()+0.000147);
		break;
		
		case " 500x Ускорение":
			sputnik.rotateProperty().set(sputnik.getRotate()+0.0735);
		break;
		
		case " 1000x Ускорение":
			sputnik.rotateProperty().set(sputnik.getRotate()+0.147);
		break;
		
		case " 5000x Ускорение":
			sputnik.rotateProperty().set(sputnik.getRotate()+0.735);
		break;
		
			
		}
    }

	private Node prepareSputnikY(String title, Integer valX, Integer valY, Integer valZ) {
		Sputnik s = new Sputnik(title, valX, valY, valZ);
		s.displayInfo();
		Sphere sputnik=new Sphere(5);
		sputnik.getTransforms().add(new Translate(valX, valY, valZ));
		PhongMaterial materialSputnik = new PhongMaterial();
		materialSputnik.setSelfIlluminationMap(new Image(getClass().getResourceAsStream("/resourses/white.jpg")));
		sputnik.setMaterial(materialSputnik);
		sputnik.setOnMouseClicked(event->{ LinkWindow(title); });
		AnimationTimer timer = new AnimationTimer() {

			@Override
			public void handle(long arg0) {
				sputnik.setRotationAxis(Rotate.Y_AXIS);
				SputnikMoving(sputnik);
			
			}
			
			
		};
		
		timer.start();
		
		
		return sputnik;
		
	}
	
	private Node prepareSputnikZ(String title, Integer valX, Integer valY, Integer valZ) {
		Sputnik s = new Sputnik(title, valX, valY, valZ);
		s.displayInfo();
		Sphere sputnik=new Sphere(5);
		sputnik.getTransforms().add(new Translate(valX, valY, valZ));
		PhongMaterial materialSputnik = new PhongMaterial();
		materialSputnik.setDiffuseMap(new Image(getClass().getResourceAsStream("/resourses/white.jpg")));
		sputnik.setMaterial(materialSputnik);
		sputnik.setOnMouseClicked(event->{ LinkWindow(title); });
		AnimationTimer timer = new AnimationTimer() {

			@Override
			public void handle(long arg0) {
				sputnik.setRotationAxis(Rotate.Z_AXIS);
				SputnikMoving(sputnik);

			}
			
			
		};
		
		timer.start();
		
		
		return sputnik;
		
	}
	
	private ImageView prepareImageView() {
		Image image = new Image(Main.class.getResourceAsStream("/resourses/galaktika.jpg"));
		ImageView imageView = new ImageView(image);
		imageView.setPreserveRatio(true);
		imageView.getTransforms().add(new Translate(-image.getWidth()/2, -image.getHeight()/2,500));
		return imageView;
	}


	private Node prepareEarth() {
		PhongMaterial earthMaterial = new PhongMaterial();
		earthMaterial.setDiffuseMap(new Image(getClass().getResourceAsStream("/resourses/Earth.jpg")));
		earthMaterial.setSelfIlluminationMap(new Image(getClass().getResourceAsStream("/resourses/EarthLight2.jpg")));
		earthMaterial.setSpecularMap(new Image(getClass().getResourceAsStream("/resourses/EarthNeg.jpg")));
		sphere.setRotationAxis(Rotate.Y_AXIS);
		sphere.setMaterial(earthMaterial);
		return sphere;
	}


	class SmartGroup extends Group{
		Rotate r;
		Transform t = new Rotate();
		
		void rotateByX(int ang) {
			r = new Rotate(ang, Rotate.X_AXIS);
			t = t.createConcatenation(r);
			this.getTransforms().clear();
			this.getTransforms().addAll(t);
		}
		
		void rotateByY(int ang) {
			r = new Rotate(ang, Rotate.Y_AXIS);
			t = t.createConcatenation(r);
			this.getTransforms().clear();
			this.getTransforms().addAll(t);
		}
	}
	
	private void initMouseControl(SmartGroup group, Scene scene, Stage stage) {
		Rotate xRotate;
		Rotate yRotate;
		group.getTransforms().addAll(
				xRotate=new Rotate(0, Rotate.X_AXIS),
				yRotate=new Rotate(0, Rotate.Y_AXIS));
		
		xRotate.angleProperty().bind(angleX);
		yRotate.angleProperty().bind(angleY);
		
		scene.setOnMousePressed(event->{
			anchorX = event.getSceneX();
			anchorY = event.getSceneY();
			anchorAngleX=angleX.get();
			anchorAngleY=angleY.get();
		});
		
		scene.setOnMouseDragged(event->{
			angleX.set(anchorAngleX - (anchorY - event.getSceneY()));
			angleY.set(anchorAngleY + anchorX - event.getSceneX());
		});
		
		stage.addEventHandler(ScrollEvent.SCROLL, event->{
			double movement=event.getDeltaY();
			group.translateZProperty().set(group.getTranslateZ() + movement);
		});
		}
	
	public static void main(String[] args) {launch(args);}
}
