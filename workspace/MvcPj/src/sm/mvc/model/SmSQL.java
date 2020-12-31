package sm.mvc.model;

class SmSQL {
	static final String LIST = "select * from BOARD order by SEQ desc";
	static final String CONTENT = "select * from BOARD where SEQ=?";
	static final String DELETE = "delete from BOARD where SEQ=?";
	static final String INSERT = "insert into BOARD values(BOARD_SEQ.nextval, ?, ?, ?, ?, SYSDATE)";
	static final String UPDATE = "update BOARD set WRITER=?, EMAIL=?, SUBJECT=?, CONTENT=? where SEQ=?";
}
