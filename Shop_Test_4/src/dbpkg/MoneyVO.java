package dbpkg;
//custno      number(6)   not null,
//salenol     number(8)   primary key not null,
//pcost       number(8),
//amount      number(4),
//price       number(8),
//pcode       varchar2(4),
//sdate       date,
public class MoneyVO {
	private String custno; 
	private String salenol;
	private String pcost;
	private String amount;
	private String price;
	private String pcode;
	private String sdate;
	
	public String getCustno() {
		return custno;
	}
	public void setCustno(String custno) {
		this.custno = custno;
	}
	public String getSalenol() {
		return salenol;
	}
	public void setSalenol(String salenol) {
		this.salenol = salenol;
	}
	public String getPcost() {
		return pcost;
	}
	public void setPcost(String pcost) {
		this.pcost = pcost;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getPcode() {
		return pcode;
	}
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
}
