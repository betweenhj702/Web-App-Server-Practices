package sm.mvc.model;

import java.util.ArrayList;

import mvc.domain.Board;

public class SmService {

	private SmDAO dao;
	private static final SmService instance = new SmService();
	private SmService(){
		dao = new SmDAO();
	}
	public static SmService getInstance(){
		return instance; 
	}

	public ArrayList<Board> listS(){
		return dao.list();
	}
	
	public Board selectConS(int seq){
		return dao.selectCon(seq);
	}
	
	public int deleteS(int seq){
		return dao.delete(seq);
	}

	public int insertS(Board dto){
		return dao.insert(dto);
	}

	public Board selUpConS(int seq){
		return dao.selUpCon(seq);
	}

	public void updateS(Board dto){
		dao.update(dto);
		dao.selectCon(dto.getSeq());
	}
}
