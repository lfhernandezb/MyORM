import java.sql.ResultSet;
import java.sql.SQLException;


public class ForeignKey {
	private String _column_name;
	private short _key_seq;
	private String _fk_name;
	
	// getters
	
	public String getColumnName() {
		return this._column_name;
	}
	
	public short getKeySeq() {
		return this._key_seq;
	}
	
	public String getFkName() {
		return this._fk_name;
	}
	
	// setters
	
	public void setColumnName(String _column_name) {
		this._column_name = _column_name;
	}
	
	public void setKeySeq(short _key_seq) {
		this._key_seq = _key_seq;
	}
	
	public void setFkName(String _pk_name) {
		this._fk_name = _fk_name;
	}
		
	public static ForeignKey fromRS(ResultSet rs) throws SQLException {
		ForeignKey fk = new ForeignKey();
		
		fk.setColumnName(rs.getString(8));
		fk.setKeySeq(rs.getShort(9));
		fk.setFkName(rs.getString(12));
		
		return fk;
	}
	
}
