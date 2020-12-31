package addr.mvc.model;

import java.util.ArrayList;

import mvc.domain.Address;

public class AddrService { //SingleTon Object Model 
	private AddrDAO dao;
	private static final AddrService instance = new AddrService();
	private AddrService(){
		dao = new AddrDAO();
	}
	public static AddrService getInstance() {
		return instance;
	}
	
	public ArrayList<Address> listS(){
		return dao.list();
	}
	public void insertS(Address dto){
		dao.insert(dto);
	}
	public boolean delS(int seq){
		return dao.del(seq);
	}
	public void delAllS(){
		dao.delAll();
	}
}
