import java.sql.ResultSet;
import java.sql.SQLException;


public class Column {
	private String _column_name;
	private int _data_type;
	private String _type_name;
	private int _nullable;
	private String _column_def;
	private int _ordinal_position;
	private String _is_autoincrement;
	
	// getters
	
	public String getColumnName() {
		return this._column_name;
	}
	
	public int getDataType() {
		return this._data_type;
	}
	
	public String getTypeName() {
		return this._type_name;
	}
	
	public int getNullable() {
		return this._nullable;
	}
	
	public String getColumnDef() {
		return this._column_def;
	}
	
	public int getOrdinalPosition() {
		return this._ordinal_position;
	}

	public String getIsAutoincrement() {
		return this._is_autoincrement;
	}

	// setters
	
	public void setColumnName(String _column_name) {
		this._column_name = _column_name;
	}
	
	public void setDataType(int _data_type) {
		this._data_type = _data_type;
	}
	
	public void setTypeName(String _type_name) {
		this._type_name = _type_name;
	}
	
	public void setNullable(int _nullable) {
		this._nullable = _nullable;
	}
	
	public void setColumnDef(String _column_def) {
		this._column_def = _column_def;
	}
	
	public void setOrdinalPosition(int _ordinal_position) {
		this._ordinal_position = _ordinal_position;
	}

	public void setIsAutoincrement(String _is_autoincrement) {
		this._is_autoincrement = _is_autoincrement;
	}
	
	public static Column fromRS(ResultSet rs) throws SQLException {
		Column c = new Column();
		
		c.setColumnName(rs.getString(4));
		c.setDataType(rs.getInt(5));
		c.setTypeName(rs.getString(6));
		c.setNullable(rs.getInt(11));
		c.setColumnDef(rs.getString(13));
		c.setOrdinalPosition(rs.getInt(17));
		c.setIsAutoincrement(rs.getString(23));
		
		return c;
	}
	
}
