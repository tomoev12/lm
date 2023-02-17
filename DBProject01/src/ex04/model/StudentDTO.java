package ex04.model;

public class StudentDTO {
	
	// Fields
	private int    stid;
	private String stname;
	private String phone;
	private String indate;
	
	//DTO : data transfer object == VO : Value Object 
	//Constructor
	public StudentDTO() {}
	public StudentDTO(int stid, String stname, String phone, String indate) {
		this.stid   = stid;
		this.stname = stname;
		this.phone  = phone;
		this.indate = indate;
	}
	
	// getter/ setter
	public int getStid() {
		return stid;
	}
	public void setStid(int stid) {
		this.stid = stid;
	}
	public String getStname() {
		return stname;
	}
	public void setStname(String stname) {
		this.stname = stname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getIndate() {
		return indate;
	}
	public void setIndate(String indate) {
		this.indate = indate;
	}
	
	// toString
	@Override
	public String toString() {
		return "StudentDTO [stid=" + stid + ", stname=" + stname + ", phone=" + phone + ", indate=" + indate + "]";
	}
	
}
