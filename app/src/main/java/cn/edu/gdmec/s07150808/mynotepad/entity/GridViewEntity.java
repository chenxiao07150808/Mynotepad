package cn.edu.gdmec.s07150808.mynotepad.entity;

public class GridViewEntity {
	

	private String accountName;
	private String bill;
	private String billType;
	private String billState;
	private int img;
	private int id;
	private String time;
	private Integer[] imgs;
	private String[] types;
	
	public GridViewEntity(Integer[] imgs, String[] types) {
		super();
		this.imgs = imgs;
		this.types = types;
	}
	public GridViewEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getBill() {
		return bill;
	}
	public void setBill(String bill) {
		this.bill = bill;
	}
	public String getBillType() {
		return billType;
	}
	public void setBillType(String billType) {
		this.billType = billType;
	}
	public String getBillState() {
		return billState;
	}
	public void setBillState(String billState) {
		this.billState = billState;
	}
	public int getImg() {
		return img;
	}
	public void setImg(int img) {
		this.img = img;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Integer[] getImgs() {
		return imgs;
	}
	public void setImgs(Integer[] imgs) {
		this.imgs = imgs;
	}
	public String[] getTypes() {
		return types;
	}
	public void setTypes(String[] types) {
		this.types = types;
	}



	

	
}
