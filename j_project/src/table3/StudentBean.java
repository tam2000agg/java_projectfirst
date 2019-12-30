package table3;

public class StudentBean {

String name;
String strtdate;
String enddate;
float tqc;
float tqb;
float amn;
int stat;
float pending;
float grandtotal;
public StudentBean ()
{

}
public StudentBean(String name, String strtdate, String enddate, float tqc, float tqb, float amn, int stat,float pending,float grandtotal)
{
	super();
	this.name = name;
	this.strtdate = strtdate;
	this.enddate = enddate;
	this.tqc = tqc;
	this.tqb = tqb;
	this.amn = amn;
	this.stat = stat;
	this.pending=pending;
	this.grandtotal=grandtotal;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getStrtdate() {
	return strtdate;
}
public void setStrtdate(String strtdate) {
	this.strtdate = strtdate;
}
public float getPending() {
	return pending;
}
public void setPending(float pending) {
	this.pending = pending;
}
public float getGrandtotal() {
	return grandtotal;
}
public void setGrandtotal(float grandtotal) {
	this.grandtotal = grandtotal;
}
public String getEnddate() {
	return enddate;
}
public void setEnddate(String enddate) {
	this.enddate = enddate;
}
public float getTqc() {
	return tqc;
}
public void setTqc(float tqc) {
	this.tqc = tqc;
}
public float getTqb() {
	return tqb;
}
public void setTqb(float tqb) {
	this.tqb = tqb;
}
public float getAmn() {
	return amn;
}
public void setAmn(float amn) {
	this.amn = amn;
}
public int getStat() {
	return stat;
}
public void setStat(int stat) {
	this.stat = stat;
}

}
