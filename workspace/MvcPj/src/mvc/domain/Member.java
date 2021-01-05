package mvc.domain;

import java.sql.Date;

public class Member {
	private long seq;
	private String name;
	private String email;
	private String pwd;
	private String phone;
	private Date rdate;
	private Date udate;
	private boolean flag;
	
	public Member() {}

	public Member(long seq, String name, String email, String pwd, String phone, Date rdate, Date udate, boolean flag) {
		this.seq = seq;
		this.name = name;
		this.email = email;
		this.pwd = pwd;
		this.phone = phone;
		this.rdate = rdate;
		this.udate = udate;
		this.flag = flag;
	}
	
	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getRdate() {
		return rdate;
	}

	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}

	public Date getUdate() {
		return udate;
	}

	public void setUdate(Date udate) {
		this.udate = udate;
	}
	
}
