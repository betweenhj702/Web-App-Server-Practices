package login.mvc.model;

import mvc.domain.Member;

public class LoginService {
	private LoginDAO dao;
	private static final LoginService instance = new LoginService();
	LoginService(){
		dao = new LoginDAO();
	}
	public static LoginService getInstance() {
		return instance;
	}
	
	public Member checkS(String email, String pwd) {
		Member member = dao.check(email);
		//회원이 아닌 경우
		if(member == null) return null;
		//회원확인, 비번이 틀린 경우
		if(!member.getPwd().equals(pwd)){
			member.setSeq(-1);
			member.setName(null);
			member.setPhone(null);
			member.setPwd(null);
			member.setFlag(false);
			return member;
		}
		//비번까지 맞음
		member.setPwd(null); //비번은 지워서 보내자!!! 보안성
		member.setFlag(true);
		return member;
	}
}
