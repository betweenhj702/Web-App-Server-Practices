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
		//ȸ���� �ƴ� ���
		if(member == null) return null;
		//ȸ��Ȯ��, ����� Ʋ�� ���
		if(!member.getPwd().equals(pwd)){
			member.setSeq(-1);
			member.setName(null);
			member.setPhone(null);
			member.setPwd(null);
			member.setFlag(false);
			return member;
		}
		//������� ����
		member.setPwd(null); //����� ������ ������!!! ���ȼ�
		member.setFlag(true);
		return member;
	}
}