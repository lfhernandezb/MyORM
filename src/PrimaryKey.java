import java.sql.ResultSet;
import java.sql.SQLException;


public class PrimaryKey {
	private String _column_name;
	private short _key_seq;
	private String _pk_name;
	
	// getters
	
	public String getColumnName() {
		return this._column_name;
	}
	
	public short getKeySeq() {
		return this._key_seq;
	}
	
	public String getPkName() {
		return this._pk_name;
	}
	
	// setters
	
	public void setColumnName(String _column_name) {
		this._column_name = _column_name;
	}
	
	public void setKeySeq(short _key_seq) {
		this._key_seq = _key_seq;
	}
	
	public void setPkName(String _pk_name) {
		this._pk_name = _pk_name;
	}
		
	public static PrimaryKey fromRS(ResultSet rs) throws SQLException {
		PrimaryKey pk = new PrimaryKey();
		
		pk.setColumnName(rs.getString(4));
		pk.setKeySeq(rs.getShort(5));
		pk.setPkName(rs.getString(6));
		
		return pk;
	}
	
}
