package table;

public class Studentbean
{

	String name;
	String mob;
	String add;
	float cq;
	float bq;
	float cp;
	float bp;
	String dos;

	int status;
	String image;

	public Studentbean()
	{

	}

	public Studentbean(String name, String mob, String add, float cq, float bq, float cp, float bp, String dos,
			 int status, String image)
	{
		super();
		this.name = name;
		this.mob = mob;
		this.add = add;
		this.cq = cq;
		this.bq = bq;
		this.cp = cp;
		this.bp = bp;
		this.dos = dos;

		this.status = status;
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMob() {
		return mob;
	}

	public void setMob(String mob) {
		this.mob = mob;
	}

	public String getAdd() {
		return add;
	}

	public void setAdd(String add) {
		this.add = add;
	}

	public float getCq() {
		return cq;
	}

	public void setCq(float cq) {
		this.cq = cq;
	}

	public float getBq() {
		return bq;
	}

	public void setBq(float bq) {
		this.bq = bq;
	}

	public float getCp() {
		return cp;
	}

	public void setCp(float cp) {
		this.cp = cp;
	}

	public float getBp() {
		return bp;
	}

	public void setBp(float bp) {
		this.bp = bp;
	}

	public String getDos() {
		return dos;
	}

	public void setDos(String dos) {
		this.dos = dos;
	}



	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}




}
