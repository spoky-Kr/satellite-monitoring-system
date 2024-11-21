package application;

public class Sputnik {
private String title;
private Integer valX,valY,valZ;
static int count=0;
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public Integer getValX() {
	return valX;
}
public void setValX(Integer valX) {
	this.valX = valX;
}
public Integer getValY() {
	return valY;
}
public void setValY(Integer valY) {
	this.valY = valY;
}
public Integer getValZ() {
	return valZ;
}
public void setValZ(Integer valZ) {
	this.valZ = valZ;
}
public Sputnik(String title, Integer valX, Integer valY, Integer valZ) {
	super();
	this.title = title;
	this.valX = valX;
	this.valY = valY;
	this.valZ = valZ;
	count++;
}

void displayInfo(){
    System.out.printf("Title: %s \tValX: %d\n", title, valX);
}


}
