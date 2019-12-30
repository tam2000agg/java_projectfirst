package table2;

public class STUDENTBEAM
{
float cvary;
String name;
float bvary;
String dvary;
public STUDENTBEAM()
{}
public STUDENTBEAM(float cvary, String name, float bvary, String dvary) {
	super();
	this.cvary = cvary;
	this.name = name;
	this.bvary = bvary;
	this.dvary = dvary;
}
public float getCvary() {
	return cvary;
}
public void setCvary(float cvary) {
	this.cvary = cvary;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public float getBvary() {
	return bvary;
}
public void setBvary(float bvary) {
	this.bvary = bvary;
}
public String getDvary() {
	return dvary;
}
public void setDvary(String dvary) {
	this.dvary = dvary;
}


}
