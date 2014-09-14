import java.sql.ResultSet;
import java.sql.SQLException;


public class Column {
	private String columnName;
	private int dataType;
	private String typeName;
	private int nullable;
	private String columnDef;
	private int ordinalPosition;
	private String isAutoincrement;
	private String memberName;
	private String baseType;
	
	// getters
	
	public String getColumnName() {
		return this.columnName;
	}
	
	public int getDataType() {
		return this.dataType;
	}
	
	public String getTypeName() {
		return this.typeName;
	}
	
	public int getNullable() {
		return this.nullable;
	}
	
	public String getColumnDef() {
		return this.columnDef;
	}
	
	public int getOrdinalPosition() {
		return this.ordinalPosition;
	}

	public String getIsAutoincrement() {
		return this.isAutoincrement;
	}

	public String getMemberName() {
		return memberName;
	}

	public String getBaseType() {
		return baseType;
	}

	// setters
	
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	
	public void setDataType(int dataType) {
		this.dataType = dataType;
	}
	
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	public void setNullable(int nullable) {
		this.nullable = nullable;
	}
	
	public void setColumnDef(String columnDef) {
		this.columnDef = columnDef;
	}
	
	public void setOrdinalPosition(int ordinalPosition) {
		this.ordinalPosition = ordinalPosition;
	}

	public void setIsAutoincrement(String isAutoincrement) {
		this.isAutoincrement = isAutoincrement;
	}
	
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public void setBaseType(String baseType) {
		this.baseType = baseType;
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
		
		c.setMemberName(Util.toJavaFieldName(c.getColumnName()));
		
		c.setBaseType(c.getTypeName().split("\\(")[0]);		
		
		return c;
	}
	
	@Override
	public String toString() {
		return "Column [_column_name=" + columnName + ", _data_type="
				+ dataType + ", _type_name=" + typeName + ", _nullable="
				+ nullable + ", _column_def=" + columnDef
				+ ", _ordinal_position=" + ordinalPosition
				+ ", _is_autoincrement=" + isAutoincrement + "]";
	}

	
}
