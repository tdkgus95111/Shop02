package dbpkg;
//me.custno, me.custname, me.grade, sum(mo.price) as total 
public class PriceVO {
	private String custno; 
	private String custname;
	private String grade;
	private String total;
	
	public String getCustno() {
		return custno;
	}
	public void setCustno(String custno) {
		this.custno = custno;
	}
	public String getCustname() {
		return custname;
	}
	public void setCustname(String custname) {
		this.custname = custname;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
}
