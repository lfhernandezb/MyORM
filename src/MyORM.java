import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.text.WordUtils;
import org.apache.log4j.Logger;
import org.ini4j.Wini;

/**
 * 
 */

/**
 * @author Owner
 *
 */
public class MyORM {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		if (args.length != 3) {
			System.err
					.println("Utilice: java ShopInvoice <template.xml> <certDigital.p12> <password>");
			System.exit(-1);
		*/
		Logger logger = Logger.getLogger(MyORM.class.getName());

		String config_file_name;
		java.sql.Connection conn;
		Wini ini;
		DatabaseMetaData databaseMetaData;
		ResultSet rs;
		String    catalog;
		String    schemaPattern;
		String    tableNamePattern;
		String[]  types;
		
		Map<String, Column> mapColumns;
		Map<String, PrimaryKey> mapPrimaryKeys;
		Map<String, ForeignKey> mapForeignKeys;
		Map<String, String> mapJavaTypes;
		Map<String, String> mapFunctionTypes;
		
		ResultSet rsColumns;
		ResultSet rsPrimaryKeys;
		ResultSet rsIndexInfo;
		ResultSet rsImportedKeys;

		conn = null;

		try {
			
        	// leo archivo de configuracion
        	
        	ini = new Wini();
        	
        	config_file_name = System.getProperty("config_file");
        	
        	File f = new File(config_file_name);
        	
        	if (!f.exists()) {
        		throw new Exception("Config file does not exists");
        	}
        	
        	ini.load(new File(config_file_name));
        	
        	// abro conexion a la BD
        	Class.forName("com.mysql.jdbc.Driver");
        	conn = DriverManager.getConnection("jdbc:mysql://" + ini.get("DB", "host") + "/" + ini.get("DB", "database"), 
        			ini.get("DB", "user"), ini.get("DB", "password"));
        	
        	// conn.setAutoCommit(false);
        	
        	logger.debug("conn: " + conn);

        	// obtengo las tablas del modelo
        	

        	
        	databaseMetaData = conn.getMetaData();
        	
    		catalog          = null;
    		schemaPattern    = null;
    		tableNamePattern = null;
    		types            = null;
    		
    		mapColumns = new HashMap<String, Column>();
    		mapPrimaryKeys = new HashMap<String, PrimaryKey>();
    		mapForeignKeys = new HashMap<String, ForeignKey>();
    		mapJavaTypes = new HashMap<String, String>();
    		mapFunctionTypes = new HashMap<String, String>();
    		
    		mapJavaTypes.put("BIGINT", "Long");
    		mapJavaTypes.put("INT", "Integer");
    		mapJavaTypes.put("SMALLINT", "Short");
    		mapJavaTypes.put("TINYINT", "Byte");
    		mapJavaTypes.put("CHAR", "String");
    		mapJavaTypes.put("VARCHAR", "String");
    		mapJavaTypes.put("LONGVARCHAR", "String");
    		mapJavaTypes.put("TEXT", "String");
    		mapJavaTypes.put("DATE", "String");
    		mapJavaTypes.put("DATETIME", "String");
    		mapJavaTypes.put("TIMESTAMP", "String");
    		mapJavaTypes.put("BIT", "Boolean");
    		mapJavaTypes.put("BOOLEAN", "Boolean");
    		mapJavaTypes.put("DECIMAL", "Decimal");
    		mapJavaTypes.put("DOUBLE", "Double");
    		mapJavaTypes.put("FLOAT", "Float");
    		
    		// para RecordSet.get<type>()
    		mapFunctionTypes.put("BIGINT", "Long");
    		mapFunctionTypes.put("INT", "Int");
    		mapFunctionTypes.put("SMALLINT", "Short");
    		mapFunctionTypes.put("TINYINT", "Byte");
    		mapFunctionTypes.put("CHAR", "String");
    		mapFunctionTypes.put("VARCHAR", "String");
    		mapFunctionTypes.put("LONGVARCHAR", "String");
    		mapFunctionTypes.put("TEXT", "String");
    		mapFunctionTypes.put("DATE", "String");
    		mapFunctionTypes.put("DATETIME", "String");
    		mapFunctionTypes.put("TIMESTAMP", "String");
    		mapFunctionTypes.put("BIT", "Boolean");
    		mapFunctionTypes.put("BOOLEAN", "Boolean");
    		mapFunctionTypes.put("DECIMAL", "Decimal");
    		mapFunctionTypes.put("DOUBLE", "Double");
    		mapFunctionTypes.put("FLOAT", "Float");
        	
        	rs = databaseMetaData.getTables(catalog, schemaPattern, tableNamePattern, types );

        	while(rs.next()) {
        		
        		mapColumns.clear();
        		mapPrimaryKeys.clear();
        		mapForeignKeys.clear();
        		
        	    String tableName = rs.getString(3);
        	    
        	    logger.debug("table: " + tableName);
        	    
        	    tableNamePattern = tableName;
        	    
        	    rsColumns = databaseMetaData.getColumns(null, null, tableNamePattern, null);

        	    rsPrimaryKeys = databaseMetaData.getPrimaryKeys(null, null, tableNamePattern);

        	    //rsIndexInfo = databaseMetaData.getIndexInfo(null, null, tableNamePattern, false, false);
        	    rsImportedKeys = databaseMetaData.getImportedKeys(null, null, tableNamePattern);
        	    
        	    logger.debug("columns:");
        	    
        	    while(rsColumns.next()){
        	        String columnName = rsColumns.getString(4);
        	        
        	        /*
        	        if (tableName.equals("vehiculo")) {
        	        	logger.debug(Column.fromRS(rsColumns).toString());
        	        }
        	        */
        	        
        	        mapColumns.put(columnName, Column.fromRS(rsColumns));
        	    }
        	    
        	    while(rsPrimaryKeys.next()){
        	    	String columnName = rsPrimaryKeys.getString(4);
        	    	
        	    	mapPrimaryKeys.put(columnName, PrimaryKey.fromRS(rsPrimaryKeys));
        	    }
        	    
        	    while(rsImportedKeys.next()){
        	    	String columnName = rsImportedKeys.getString(8);
        	    	
        	    	mapForeignKeys.put(columnName, ForeignKey.fromRS(rsImportedKeys));
        	    }

        	    String className = toJavaClassName(tableName);
        	    /*
        	    int pos = tableName.indexOf("_");
        	    
        	    if (pos != -1) {
        	    	//logger.debug("found _!");
        	    	className = tableName.substring(0, 1).toUpperCase() + tableName.substring(1, pos) + tableName.substring(pos + 1).substring(0, 1).toUpperCase() + tableName.substring(pos + 2);
        	    }
        	    else {
        	    	className = tableName.substring(0, 1).toUpperCase() + tableName.substring(1);
        	    }
        	    */
        	    
        	    String tableShortAlias = tableName.substring(0, 2);
        	    
        	    String output = 
	        	    "/**\n" +
	           	    " * \n" +
	                " */\n" +
	                "package " + System.getProperty("package_name") + ";\n" +
	           	    "\n" +
					"import java.sql.Connection;\n" +
					"import java.sql.ResultSet;\n" +
					"import java.sql.SQLException;\n" +
					"import java.sql.Statement;\n" +
					"import java.util.AbstractMap;\n" +
					"import java.util.ArrayList;\n" +
					"import org.w3c.dom.Element;\n" +
					"import org.w3c.dom.Node;\n" +
					"\n" +
					"/**\n" +
					" * @author petete-ntbk\n" +
					" *\n" +
					" */\n" +
					"public class " + className + " {\n";
        	    
        	    // declaraciones
        	    
        	    for (Map.Entry<String, Column> entry : mapColumns.entrySet()) {
        	    	
        	        String columnName = entry.getKey();
        	        Column column = entry.getValue();
        	        
        	        String memberName = toJavaFieldName(columnName);
        	        
        	        output += "    protected " + mapJavaTypes.get(column.getBaseType()) + " _";

        	        if (mapPrimaryKeys.size() == 1 && mapPrimaryKeys.containsKey(columnName)) {
        	        	output += "id";
        	        }
        	        else {
        	        	output += memberName;
        	        }
        	        
        	        output += ";\n";
        	    }
        	    
        	    // sentencia SELECT
        	    
    	        output += 
    	        	"\n" +
    	        	"    private final static String _str_sql = \n" +
		            "        \"    SELECT\" +";
    	        
    	        Boolean bFirst = false;
    	        
        	    for (Map.Entry<String, Column> entry : mapColumns.entrySet()) {
        	    	
        	        String columnName = entry.getKey();
        	        Column column = entry.getValue();
        	        
        	        
        	        if (tableName.equals("usuario")) {
        	        	logger.debug("column: " + columnName + " data type: " + column.getDataType());
        	        }
        	        
        	        if (!bFirst) {
        	        	bFirst = true;
        	        }
        	        else {
        	        	output += ",\" +";
        	        }

        	        output += "\n        \"    ";
        	        
        	        switch(column.getBaseType()) {
	    	        	case "BIGINT":
	    	        	case "INT":
	    	        	case "SMALLINT":
	    	        	case "TINYINT":
	    	        	case "CHAR":
	    	        	case "VARCHAR":
	    	        	case "LONGVARCHAR":
	    	        	case "TEXT":
	    	        	case "DECIMAL":
	    	        	case "DOUBLE":
	    	        	case "FLOAT":
	    	        		output += tableShortAlias + "." + columnName;
	    	        		break;
	    	        	case "DATE":
	    	        	case "DATETIME":
	    	        	case "TIMESTAMP":
	    	        		output += "DATE_FORMAT(" + tableShortAlias + "." + columnName + ", '%Y-%m-%d %H:%i:%s')";
	    	        		break;
	    	        	case "BIT":
	    	        		output += "0+" + tableShortAlias + "." + columnName;
	    	        		break;
	    	        	default:
	    	        		throw new Exception("Tipo no soportado: " + column.getBaseType() + " columna: " + columnName);
        	        } // end switch
        	        
        	        output += " AS ";
        	        
        	        if (mapPrimaryKeys.size() == 1 && mapPrimaryKeys.containsKey(columnName)) {
        	        	output += "id";
        	        }
        	        else {
        	        	output += columnName;
        	        }
        	        
        	    }
        	    
        	    output +=
       	    		"\" +\n        \"    FROM " + tableName + " " + tableShortAlias + "\";\n";
        	    
        	    // fin SELECT
        	    
        	    // constructor
        	    
    	        output +=
    	        	"\n" +
    	        	"    public " + className + "() {\n";
    	        
        	    for (Map.Entry<String, Column> entry : mapColumns.entrySet()) {
        	    	
        	        String columnName = entry.getKey();
        	        //Column column = entry.getValue();
        	        String memberName = toJavaFieldName(columnName);
        	        
        	        output += "        _";
        	        
        	        if (mapPrimaryKeys.size() == 1 && mapPrimaryKeys.containsKey(columnName)) {
        	        	output += "id";
        	        }
        	        else {
        	        	output += memberName;
        	        }
        	        
        	        output += " = null;\n";
        	    }

        	    output +=
    	        		"\n    }\n";

				// fin constructor
        	    
        	    // getters
        	    
        	    for (Map.Entry<String, Column> entry : mapColumns.entrySet()) {
        	    	
        	        String columnName = entry.getKey();
        	        Column column = entry.getValue();
        	        
        	        String memberName = toJavaFieldName(columnName);
        	        
        	        output +=
        	        	"    /**\n" +
        	        	"     * @return the _";

        	        if (mapPrimaryKeys.size() == 1 && mapPrimaryKeys.containsKey(columnName)) {
        	        	output += "id";
        	        }
        	        else {
        	        	output += memberName;
        	        }
        	        
        	        output +=
        	            "\n" +
        	        	"     */\n" +
        	        	"    public " + mapJavaTypes.get(column.getBaseType()) + " get";
        	        
        	        if (mapPrimaryKeys.size() == 1 && mapPrimaryKeys.containsKey(columnName)) {
        	        	output += "Id";
        	        }
        	        else {
        	        	output += WordUtils.capitalize(memberName);
        	        }
        	        
        	        output +=
        	            "() {\n" +
        	        	"        return _";
        	        
        	        if (mapPrimaryKeys.size() == 1 && mapPrimaryKeys.containsKey(columnName)) {
        	        	output += "id";
        	        }
        	        else {
        	        	output += memberName;
        	        }
        	        
        	        output +=
        	            ";\n" +
        	        	"    }\n";
        	        
        	    }

        	    // fin getters
        	    
        	    // setters
        	    
        	    for (Map.Entry<String, Column> entry : mapColumns.entrySet()) {
        	    	
        	        String columnName = entry.getKey();
        	        Column column = entry.getValue();
        	        
        	        String memberName = toJavaFieldName(columnName);
        	        
        	        output +=
            	        	"    /**\n" +
            	    	        	"     * @param _";

        	        if (mapPrimaryKeys.size() == 1 && mapPrimaryKeys.containsKey(columnName)) {
        	        	output += "id";
        	        }
        	        else {
        	        	output += memberName;
        	        }
        	        
        	        output +=
        	            " the _";
        	        
        	        if (mapPrimaryKeys.size() == 1 && mapPrimaryKeys.containsKey(columnName)) {
        	        	output += "id";
        	        }
        	        else {
        	        	output += memberName;
        	        }
        	        
        	        output +=
        	            " to set\n" +
	    	        	"     */\n" +
	    	        	"    public void set";
        	        
        	        if (mapPrimaryKeys.size() == 1 && mapPrimaryKeys.containsKey(columnName)) {
        	        	output += "Id";
        	        }
        	        else {
        	        	output += WordUtils.capitalize(memberName);
        	        }
        	        
        	        output +=
        	            "(" + mapJavaTypes.get(column.getBaseType()) + " _";
        	        
        	        if (mapPrimaryKeys.size() == 1 && mapPrimaryKeys.containsKey(columnName)) {
        	        	output += "id";
        	        }
        	        else {
        	        	output += memberName;
        	        }
        	        
        	        output +=
        	            ") {\n" +
    	        	"        this._";

        	        if (mapPrimaryKeys.size() == 1 && mapPrimaryKeys.containsKey(columnName)) {
        	        	output += "id";
        	        }
        	        else {
        	        	output += memberName;
        	        }
        	        
        	        output +=
        	            " = _";
        	    
        	        if (mapPrimaryKeys.size() == 1 && mapPrimaryKeys.containsKey(columnName)) {
        	        	output += "id";
        	        }
        	        else {
        	        	output += memberName;
        	        }
        	        
        	        output +=
        	            ";\n" +
    	        	    "    }\n";
        	           	    
        	    }

        	    // fin setters
        	    
        	    // fromRS
        	    
        	    output +=
        	    	"\n" +
					"    public static " + className + " fromRS(ResultSet p_rs) throws SQLException {\n" +
		            "        " + className + " ret = new " + className + "();\n\n";		

        	    for (Map.Entry<String, Column> entry : mapColumns.entrySet()) {
        	    	
        	        String columnName = entry.getKey();
        	        Column column = entry.getValue();
        	        
        	        String memberName = toJavaFieldName(columnName);
        	        
        	        output += 
        	        	"        ret.set"; 
        	        
        	        if (mapPrimaryKeys.size() == 1 && mapPrimaryKeys.containsKey(columnName)) {
        	        	output += "Id";
        	        }
        	        else {
        	        	output += WordUtils.capitalize(memberName);
        	        }
        	        
        	        output += "(p_rs.get" + mapFunctionTypes.get(column.getBaseType()) + "(\"";
        	        
        	        if (mapPrimaryKeys.size() == 1 && mapPrimaryKeys.containsKey(columnName)) {
        	        	output += "id";
        	        }
        	        else {
        	        	output += columnName;
        	        }

        	        output += 
        	        	"\"));\n";

        	    }
        	    
        	    output +=
                    "\n" +			
		            "        return ret;\n" +
		            "    }\n";

        	           	    
        	    // fin fromRS
        	    
        	    // getByParameter
        	    
    	        output +=
        	        	"\n" +
        	            "    public static " + className + " getByParameter(Connection p_conn, String p_key, String p_value) throws SQLException {\n" +
        	            "        " + className + " ret = null;\n" +
        	            "        \n" +
        	            "        String str_sql = _str_sql +\n" +
        	            "            \"  WHERE " + tableShortAlias + ".\" + p_key + \" = \" + p_value +\n" +
        	            "            \"  LIMIT 0, 1\";\n" +
        	            "        \n" +
        	            "        //System.out.println(str_sql);\n" +
        	            "        \n" +
        	            "        // assume that conn is an already created JDBC connection (see previous examples)\n" +
        	            "        Statement stmt = null;\n" +
        	            "        ResultSet rs = null;\n" +
        	            "        \n" +
        	            "        try {\n" +
        	            "            stmt = p_conn.createStatement();\n" +
        	            "            //System.out.println(\"stmt = p_conn.createStatement() ok\");\n" +
        	            "            rs = stmt.executeQuery(str_sql);\n" +
        	            "            //System.out.println(\"rs = stmt.executeQuery(str_sql) ok\");\n" +
        	            "\n" +
        	            "            // Now do something with the ResultSet ....\n" +
        	            "            \n" +
        	            "            if (rs.next()) {\n" +
        	            "                //System.out.println(\"rs.next() ok\");\n" +
        	            "                ret = fromRS(rs);\n" +
        	            "                //System.out.println(\"fromRS(rs) ok\");\n" +
        	            "            }\n" +
        	            "        }\n" +
        	            "        catch (SQLException ex){\n" +
        	            "            // handle any errors\n" +
        	            "            System.out.println(\"SQLException: \" + ex.getMessage() + \" sentencia: \" + str_sql);\n" +
        	            "            System.out.println(\"SQLState: \" + ex.getSQLState());\n" +
        	            "            System.out.println(\"VendorError: \" + ex.getErrorCode());\n" +
        	            "            \n" +
        	            "            throw ex;\n" +
        	            "        }\n" +
        	            "        finally {\n" +
        	            "            // it is a good idea to release\n" +
        	            "            // resources in a finally{} block\n" +
        	            "            // in reverse-order of their creation\n" +
        	            "            // if they are no-longer needed\n" +
        	            "            if (rs != null) {\n" +
        	            "                try {\n" +
        	            "                    rs.close();\n" +
        	            "                } catch (SQLException sqlEx) { \n" +
        	            "                    \n" +
        	            "                } // ignore\n" +
        	            "                rs = null;\n" +
        	            "            }\n" +
        	            "            if (stmt != null) {\n" +
        	            "                try {\n" +
        	            "                    stmt.close();\n" +
        	            "                } catch (SQLException sqlEx) {\n" +
        	            "                    \n" +
        	            "                } // ignore\n" +
        	            "                stmt = null;\n" +
        	            "            }\n" +
        	            "        }        \n" +
        	            "        \n" +
        	            "        return ret;        \n" +
        	            "    }\n" +
        	            "\n";

    	        // fin getByParameter
    	        
    	        // getById
    	        
    	        if (mapPrimaryKeys.size() == 1) {
        	        output +=
        	        	"    public static " + className + " getById(Connection p_conn, String p_id) throws Exception {\n" +
        	        	"        return getByParameter(p_conn, \"id_" + tableName + "\", p_id);\n" +
        	        	"    }\n";
    	        }

    	        // fin getById
    	        
    	        // seek
        	    
    	        output +=
        	        	"    \n" +
        	        	"    public static ArrayList<" + className + "> seek(Connection p_conn, ArrayList<AbstractMap.SimpleEntry<String, String>> p_parameters, String p_order, String p_direction, int p_offset, int p_limit) throws Exception {\n" +
        	        	"        Statement stmt = null;\n" +
        	        	"        ResultSet rs = null;\n" +
        	        	"        String str_sql;\n" +
        	        	"        ArrayList<" + className + "> ret;\n" +
        	        	"        \n" +
        	        	"        str_sql = \"\";\n" +
        	        	"        \n" +
        	        	"        try {\n" +
        	        	"            ArrayList<String> array_clauses = new ArrayList<String>();\n" +
        	        	"            \n" +
        	        	"            ret = new ArrayList<" + className + ">();\n" +
        	        	"            \n" +
        	        	"            str_sql = _str_sql;\n" +
        	        	"            \n" +
        	        	"            for (AbstractMap.SimpleEntry<String, String> p : p_parameters) {\n";
    	        
    	        bFirst = false;
    	        
        	    for (Map.Entry<String, PrimaryKey> entry : mapPrimaryKeys.entrySet()) {
        	    	
        	        String columnName = entry.getKey();
        	        PrimaryKey pk = entry.getValue();
        	        
        	        output += "                ";
        	        
        	        if (!bFirst) {
        	        	bFirst = true;
        	        }
        	        else {
        	        	output += "else ";
        	        }
        	        
        	        output += 
        	        	"if (p.getKey().equals(\"" + pk.getColumnName() + "\")) {\n" +
                	    "                    array_clauses.add(\"" + tableShortAlias + "." + pk.getColumnName() + " = \" + p.getValue());\n" +
                	    "                }\n";

        	    }
    	        
        	    for (Map.Entry<String, ForeignKey> entry : mapForeignKeys.entrySet()) {
        	    	
        	        String columnName = entry.getKey();
        	        ForeignKey fk = entry.getValue();
        	        
        	        output += "                ";
        	        
        	        if (!bFirst) {
        	        	bFirst = true;
        	        }
        	        else {
        	        	output += "else ";
        	        }
        	        
        	        output += 
            	        	"if (p.getKey().equals(\"" + fk.getColumnName() + "\")) {\n" +
                    	    "                    array_clauses.add(\"" + tableShortAlias + "." + fk.getColumnName() + " = \" + p.getValue());\n" +
                    	    "                }\n";

        	    }
    	        
        	    if (mapColumns.containsKey("fecha_modificacion")) {
        	    	
        	        output += "                ";
        	        
        	        if (!bFirst) {
        	        	bFirst = true;
        	        }
        	        else {
        	        	output += "else ";
        	        }
        	        
        	        output += 
            	        	"if (p.getKey().equals(\"mas reciente\")) {\n" +
                    	    "                    array_clauses.add(\"" + tableShortAlias + ".fecha_modificacion > STR_TO_DATE(\" + p.getValue() + \", '%Y-%m-%d %H:%i:%s')\");\n" +
                    	    "                }\n";

        	    }

        	    if (mapColumns.containsKey("borrado")) {
        	    	
        	        output += "                ";
        	        
        	        if (!bFirst) {
        	        	bFirst = true;
        	        }
        	        else {
        	        	output += "else ";
        	        }
        	        
        	        output += 
            	        	"if (p.getKey().equals(\"no borrado\")) {\n" +
                    	    "                    array_clauses.add(\"" + tableShortAlias + ".borrado = 0\");\n" +
                    	    "                }\n";

        	        output += "                ";
        	        
        	        output += "else ";
        	        
        	        output += 
            	        	"if (p.getKey().equals(\"borrado\")) {\n" +
                    	    "                    array_clauses.add(\"" + tableShortAlias + ".borrado = 1\");\n" +
                    	    "                }\n";
        	    }

        	    output +=
    	        	"                else {\n" +
    	        	"                    throw new Exception(\"Parametro no soportado: \" + p.getKey());\n" +
        	        "                }\n" +
    	        	"            }\n" +
    	        	"                                \n" +
    	        	"            boolean bFirstTime = false;\n" +
    	        	"            \n" +
    	        	"            for(String clause : array_clauses) {\n" +
    	        	"                if (!bFirstTime) {\n" +
    	        	"                     bFirstTime = true;\n" +
    	        	"                     str_sql += \" WHERE \";\n" +
    	        	"                }\n" +
    	        	"                else {\n" +
    	        	"                     str_sql += \" AND \";\n" +
    	        	"                }\n" +
    	        	"                str_sql += clause;\n" +
    	        	"            }\n" +
    	        	"            \n" +
    	        	"            if (p_order != null && p_direction != null) {\n" +
    	        	"                str_sql += \" ORDER BY \" + p_order + \" \" + p_direction;\n" +
    	        	"            }\n" +
    	        	"            \n" +
    	        	"            if (p_offset != -1 && p_limit != -1) {\n" +
    	        	"                str_sql += \"  LIMIT \" +  Integer.toString(p_offset) + \", \" + Integer.toString(p_limit);\n" +
    	        	"            }\n" +
    	        	"            \n" +
    	        	"            //echo \"<br>\" . str_sql . \"<br>\";\n" +
    	        	"        \n" +
    	        	"            stmt = p_conn.createStatement();\n" +
    	        	"            \n" +
    	        	"            rs = stmt.executeQuery(str_sql);\n" +
    	        	"            \n" +
    	        	"            while (rs.next()) {\n" +
    	        	"                ret.add(fromRS(rs));\n" +
    	        	"            }\n" +
    	        	"            /*\n" +
    	        	"            if (ret.size() == 0) {\n" +
    	        	"                ret = null;\n" +
    	        	"            }\n" +
    	        	"            */\n" +
    	        	"        }\n" +
    	        	"        catch (SQLException ex){\n" +
    	        	"            // handle any errors\n" +
    	        	"            System.out.println(\"SQLException: \" + ex.getMessage() + \" sentencia: \" + str_sql);\n" +
    	        	"            System.out.println(\"SQLState: \" + ex.getSQLState());\n" +
    	        	"            System.out.println(\"VendorError: \" + ex.getErrorCode());\n" +
    	        	"            \n" +
    	        	"            throw ex;\n" +
    	        	"        }\n" +
    	        	"        catch (Exception ex) {\n" +
    	        	"            throw ex;\n" +
    	        	"        }\n" +
    	        	"        finally {\n" +
    	        	"            // it is a good idea to release\n" +
    	        	"            // resources in a finally{} block\n" +
    	        	"            // in reverse-order of their creation\n" +
    	        	"            // if they are no-longer needed\n" +
    	        	"            if (rs != null) {\n" +
    	        	"                try {\n" +
    	        	"                    rs.close();\n" +
    	        	"                } catch (SQLException sqlEx) { \n" +
    	        	"                    \n" +
    	        	"                } // ignore\n" +
    	        	"                rs = null;\n" +
    	        	"            }\n" +
    	        	"            if (stmt != null) {\n" +
    	        	"                try {\n" +
    	        	"                    stmt.close();\n" +
    	        	"                } catch (SQLException sqlEx) {\n" +
    	        	"                    \n" +
    	        	"                } // ignore\n" +
    	        	"                stmt = null;\n" +
    	        	"            }\n" +
    	        	"        }        \n" +
    	        	"\n" +
    	        	"        return ret;\n" +
    	        	"    }\n" +
    	        	"\n";
        	    
        	    // fin seek
        	    
        	    // update
        	        
        	    output +=
    	        	"    public int update(Connection p_conn) throws SQLException {\n" +
    	        	"\n" +
    	        	"        int ret = -1;\n" +
    	        	"        Statement stmt = null;\n" +
    	        	"\n" +
    	        	"        String str_sql =\n" +
    	        	"            \"    UPDATE " + tableName + "\" +\n" +
    	        	"            \"    SET\" +";
        	        
       	        bFirst =  false;
        	        
        	    for (Map.Entry<String, Column> entry : mapColumns.entrySet()) {
        	    	
        	        String columnName = entry.getKey();
        	        Column column = entry.getValue();
        	        
        	        String memberName = toJavaFieldName(columnName);
        	        
        	        // no se actualizan las llaves primarias
        	        if (mapPrimaryKeys.containsKey(columnName)) {
        	        	continue;
        	        }
        	        
        	        // no se actualizan las llaves foraneas
        	        if (mapForeignKeys.containsKey(columnName)) {
        	        	continue;
        	        }

        	        if (!bFirst) {
        	        	bFirst = true;
        	        }
        	        else {
        	        	output += " + \",\" +";
        	        }
        	        
        	        output += "\n            \"    ";
        	        
        	        switch(column.getBaseType()) {
	    	        	case "BIGINT":
	    	        	case "INT":
	    	        	case "SMALLINT":
	    	        	case "TINYINT":
	    	        	case "DECIMAL":
	    	        	case "DOUBLE":
	    	        	case "FLOAT":
	    	        		output += columnName + " = \" + (_" + memberName + " != null ? _" + memberName + " : \"null\")";
	    	        		break;
	    	        	case "CHAR":
	    	        	case "VARCHAR":
	    	        	case "LONGVARCHAR":
	    	        	case "TEXT":
	    	        		output += columnName + " = \" + (_" + memberName + " != null ? \"'\" + _" + memberName + " + \"'\" : \"null\")";	    	        		break;
	    	        	case "DATE":
	    	        	case "DATETIME":
	    	        	case "TIMESTAMP":
	    	        		output += columnName + " = \" + (_" + memberName + " != null ? \"STR_TO_DATE(\" + _" + memberName + " + \", '%Y-%m-%d %H:%i:%s')\" : \"null\")";
	    	        		break;
	    	        	case "BIT":
	    	        		output += columnName + " = \" + (_" + memberName + " != null ? \"b'\" + _" + memberName + " : \"null\")";
	    	        		break;
	    	        	default:
	    	        		throw new Exception("Tipo no soportado: " + column.getBaseType() + " columna: " + memberName);
        	        } // end switch
        	        
        	    }

        	    output +=
        	    		" +\n            \"    WHERE\" +\n";
        	    
    	        output += buildWhereSentence(
    	        		mapColumns,
    	        		mapPrimaryKeys,
    	        		mapJavaTypes
    	        	);
    	                	    
        	    output +=
        	    	";\n" +
    	        	"\n" +
    	        	"        try {\n" +
    	        	"            stmt = p_conn.createStatement();\n" +
    	        	"            \n" +
    	        	"            ret = stmt.executeUpdate(str_sql);\n" +
    	        	"            /*\n" +
    	        	"            if (stmt.executeUpdate(str_sql) < 1) {\n" +
    	        	"                throw new Exception(\"No hubo filas afectadas\");\n" +
    	        	"            }\n" +
    	        	"            */\n" +
    	        	"            \n" +
    	        	"        }\n" +
    	        	"        catch (SQLException ex){\n" +
    	        	"            // handle any errors\n" +
    	        	"            System.out.println(\"SQLException: \" + ex.getMessage() + \" sentencia: \" + str_sql);\n" +
    	        	"            System.out.println(\"SQLState: \" + ex.getSQLState());\n" +
    	        	"            System.out.println(\"VendorError: \" + ex.getErrorCode());\n" +
    	        	"            \n" +
    	        	"            throw ex;\n" +
    	        	"        }\n" +
    	        	"        finally {\n" +
    	        	"            // it is a good idea to release\n" +
    	        	"            // resources in a finally{} block\n" +
    	        	"            // in reverse-order of their creation\n" +
    	        	"            // if they are no-longer needed\n" +
    	        	"            if (stmt != null) {\n" +
    	        	"                try {\n" +
    	        	"                    stmt.close();\n" +
    	        	"                } catch (SQLException sqlEx) {\n" +
    	        	"                    \n" +
    	        	"                } // ignore\n" +
    	        	"                stmt = null;\n" +
    	        	"            }\n" +
    	        	"        }\n" +
    	        	"        \n" +
    	        	"        return ret;\n" +
    	        	"    }\n" +
    	        	"    \n";
        	    
        	    // fin update
    	        
        	    // insert
    	        	
        	    output +=
    	        	"    public int insert(Connection p_conn) throws SQLException {\n" +
    	        	"        \n" +
    	        	"        int ret = -1;\n" +
    	        	"        Statement stmt = null;\n" +
    	        	"        ResultSet rs = null;\n" +
    	        	"\n" +
    	        	"        String str_sql =\n" +
    	        	"            \"    INSERT INTO " + tableName + "\" +\n" +
    	        	"            \"    (\" +\n";
        	    
       	        bFirst =  false;
    	        
        	    for (Map.Entry<String, Column> entry : mapColumns.entrySet()) {
        	    	
        	        String columnName = entry.getKey();
        	        Column column = entry.getValue();
        	        
        	        String memberName = toJavaFieldName(columnName);
        	        
        	        // no se insertan las llaves primarias autoincrementales
        	        if (mapPrimaryKeys.containsKey(columnName) && column.getIsAutoincrement() == "YES") {
        	        	continue;
        	        }
        	        
        	        // no se insertan columnas con valor por defecto
        	        if (column.getColumnDef() != null) {
        	        	continue;
        	        }

        	        if (!bFirst) {
        	        	bFirst = true;
        	        }
        	        else {
        	        	output += ", \" +\n";
        	        }
        	        
        	        output += "            \"    " + columnName;
        	        
        	    }
        	    
        	    output +=
    	        	")\" +\n" +
    	        	"            \"    VALUES\" +\n" +
    	        	"            \"    (\" +\n";

       	        bFirst =  false;
    	        
        	    for (Map.Entry<String, Column> entry : mapColumns.entrySet()) {
        	    	
        	        String columnName = entry.getKey();
        	        Column column = entry.getValue();
        	        
        	        String memberName = toJavaFieldName(columnName);
        	        
        	        // no se insertan las llaves primarias autoincrementales
        	        if (mapPrimaryKeys.containsKey(columnName) && column.getIsAutoincrement() == "YES") {
        	        	continue;
        	        }
        	        
        	        // no se insertan columnas con valor por defecto
        	        if (column.getColumnDef() != null) {
        	        	continue;
        	        }

        	        if (!bFirst) {
        	        	bFirst = true;
        	        }
        	        else {
        	        	output += " + \",\" +\n";
        	        }
        	        
        	        output += "            \"    ";
        	        
        	        switch(column.getBaseType()) {
	    	        	case "BIGINT":
	    	        	case "INT":
	    	        	case "SMALLINT":
	    	        	case "TINYINT":
	    	        	case "DECIMAL":
	    	        	case "DOUBLE":
	    	        	case "FLOAT":
	    	        		output += "\" + (_";
	    	        		
	            	        if (mapPrimaryKeys.size() == 1 && mapPrimaryKeys.containsKey(columnName)) {
	            	        	output += "id";
	            	        }
	            	        else {
	            	        	output += memberName;
	            	        }
	            	        
	            	        output += " != null ? \"'\" + _";

	            	        if (mapPrimaryKeys.size() == 1 && mapPrimaryKeys.containsKey(columnName)) {
	            	        	output += "id";
	            	        }
	            	        else {
	            	        	output += memberName;
	            	        }
	            	        
	            	        output += " + \"'\" : \"null\")";
	            	        
	    	        		break;
	    	        	case "CHAR":
	    	        	case "VARCHAR":
	    	        	case "LONGVARCHAR":
	    	        	case "TEXT":
	    	        		output += "\" + (_" + memberName + " != null ? \"'\" + _" + memberName + " + \"'\" : \"null\")";
	    	        		break;
	    	        	case "DATE":
	    	        	case "DATETIME":
	    	        	case "TIMESTAMP":
	    	        		output += "\" + (_" + memberName + " != null ? \"STR_TO_DATE(\" + _" + memberName + " + \", '%Y-%m-%d %H:%i:%s')\" : \"null\")";
	    	        		break;
	    	        	case "BIT":
	    	        		output += "\" + (_" + memberName + " != null ? \"b'\" + (_" + memberName + " ? 1 : 0) + \"'\" : \"null\")";
	    	        		break;
	    	        	default:
	    	        		throw new Exception("Tipo no soportado: " + column.getBaseType() + " columna: " + columnName);
        	        } // end switch
        	        
        	    }
        	    
        	    output +=
    	        	" +\n            \"    )\";\n" +
    	        	"        \n" +
    	        	"        try {\n" +
    	        	"            stmt = p_conn.createStatement();\n" +
    	        	"\n" +
    	        	"            ret = stmt.executeUpdate(str_sql";
        	    
        	    Boolean bFlag = false; 
        	    
    	        if (mapPrimaryKeys.size() == 1) {
    	        	
    	        	for (Map.Entry<String, PrimaryKey> entry : mapPrimaryKeys.entrySet()) {
    	        		if (mapColumns.get(entry.getKey()).getIsAutoincrement() == "YES") {
    	        			bFlag = true;
    	        		}
    	        	}
        	        
    	        }
    	        
    	        if (bFlag) {
    	        	
            	    output +=
            	        ", Statement.RETURN_GENERATED_KEYS);\n" +
        	        	"\n" +
        	        	"            rs = stmt.getGeneratedKeys();\n" +
        	        	"\n" +
        	        	"            if (rs.next()) {\n" +
        	        	"                _id = rs.getInt(1);\n" +
        	        	"            } else {\n" +
        	        	"                // throw an exception from here\n" +
        	        	"                // throw new Exception(\"Error al obtener id\");\n" +
        	        	"            }\n" +
        	        	"\n" +
        	        	"            rs.close();\n" +
        	        	"            rs = null;\n" +
        	        	"            //System.out.println(\"Key returned from getGeneratedKeys():\" + _id.toString());\n";
            	    
    	        }
    	        else {
    	        	
            	    output +=
            	        ");\n";
    	        }

    	        output +=
    	        	"\n" +
    	        	"            load(p_conn);\n" +
    	        	"\n" +
    	        	"        }\n" +
    	        	"        catch (SQLException ex){\n" +
    	        	"            // handle any errors\n" +
    	        	"            System.out.println(\"SQLException: \" + ex.getMessage() + \" sentencia: \" + str_sql);\n" +
    	        	"            System.out.println(\"SQLState: \" + ex.getSQLState());\n" +
    	        	"            System.out.println(\"VendorError: \" + ex.getErrorCode());\n" +
    	        	"            \n" +
    	        	"            throw ex;\n" +
    	        	"        }\n" +
    	        	"        finally {\n" +
    	        	"            // it is a good idea to release\n" +
    	        	"            // resources in a finally{} block\n" +
    	        	"            // in reverse-order of their creation\n" +
    	        	"            // if they are no-longer needed\n" +
    	        	"            if (rs != null) {\n" +
    	        	"                try {\n" +
    	        	"                    rs.close();\n" +
    	        	"                } catch (SQLException sqlEx) { \n" +
    	        	"                    \n" +
    	        	"                } // ignore\n" +
    	        	"                rs = null;\n" +
    	        	"            }\n" +
    	        	"            if (stmt != null) {\n" +
    	        	"                try {\n" +
    	        	"                    stmt.close();\n" +
    	        	"                } catch (SQLException sqlEx) {\n" +
    	        	"                    \n" +
    	        	"                } // ignore\n" +
    	        	"                stmt = null;\n" +
    	        	"            }\n" +
    	        	"        }\n" +
    	        	"        \n" +
    	        	"        return ret;\n" +
    	        	"    }\n" +
    	        	"\n";
    	        
    	        // fin insert
    	        
    	        // delete
    	        
    	        output +=
    	        	"    public int delete(Connection p_conn) throws SQLException {\n" +
    	        	"\n" +
    	        	"        int ret = -1;\n" +
    	        	"        Statement stmt = null;\n" +
    	        	"\n" +
    	        	"        String str_sql =\n" +
    	        	"            \"    DELETE FROM " + tableName + "\" +\n" +
    	        	"            \"    WHERE\" +\n";
    	        
    	        output += buildWhereSentence(
    	        		mapColumns,
    	        		mapPrimaryKeys,
    	        		mapJavaTypes
    	        	);
    	        
    	        output +=
    	        	";\n" +
    	        	"\n" +
    	        	"        try {\n" +
    	        	"            stmt = p_conn.createStatement();\n" +
    	        	"            \n" +
    	        	"            ret = stmt.executeUpdate(str_sql);\n" +
    	        	"        }\n" +
    	        	"        catch (SQLException ex){\n" +
    	        	"            // handle any errors\n" +
    	        	"            System.out.println(\"SQLException: \" + ex.getMessage() + \" sentencia: \" + str_sql);\n" +
    	        	"            System.out.println(\"SQLState: \" + ex.getSQLState());\n" +
    	        	"            System.out.println(\"VendorError: \" + ex.getErrorCode());\n" +
    	        	"            \n" +
    	        	"            throw ex;\n" +
    	        	"        }\n" +
    	        	"        finally {\n" +
    	        	"            // it is a good idea to release\n" +
    	        	"            // resources in a finally{} block\n" +
    	        	"            // in reverse-order of their creation\n" +
    	        	"            // if they are no-longer needed\n" +
    	        	"            if (stmt != null) {\n" +
    	        	"                try {\n" +
    	        	"                    stmt.close();\n" +
    	        	"                } catch (SQLException sqlEx) {\n" +
    	        	"                    \n" +
    	        	"                } // ignore\n" +
    	        	"                stmt = null;\n" +
    	        	"            }\n" +
    	        	"        }\n" +
    	        	"        \n" +
    	        	"        return ret;\n" +
    	        	"    }\n";
    	        
    	       // fin delete 
    	        
    	        

    	        
    	        
        	    // load
        	    
    	        output +=
    	        	"\n" +
    	            "    public void load(Connection p_conn) throws SQLException {\n" +
    	            "        " + className + " obj = null;\n" +
    	            "        \n" +
    	            "        String str_sql = _str_sql +\n" +
    	            "            \"    WHERE\" +\n";
    	        
    	        output += buildWhereSentence(mapColumns, mapPrimaryKeys, mapJavaTypes);
    	        
    	        output += 
    	        	" +\n" +
    	            "            \"    LIMIT 0, 1\";\n" +
    	            "        \n" +
    	            "        //System.out.println(str_sql);\n" +
    	            "        \n" +
    	            "        // assume that conn is an already created JDBC connection (see previous examples)\n" +
    	            "        Statement stmt = null;\n" +
    	            "        ResultSet rs = null;\n" +
    	            "        \n" +
    	            "        try {\n" +
    	            "            stmt = p_conn.createStatement();\n" +
    	            "            //System.out.println(\"stmt = p_conn.createStatement() ok\");\n" +
    	            "            rs = stmt.executeQuery(str_sql);\n" +
    	            "            //System.out.println(\"rs = stmt.executeQuery(str_sql) ok\");\n" +
    	            "\n" +
    	            "            // Now do something with the ResultSet ....\n" +
    	            "            \n" +
    	            "            if (rs.next()) {\n" +
    	            "                //System.out.println(\"rs.next() ok\");\n" +
    	            "                obj = fromRS(rs);\n" +
    	            "                //System.out.println(\"fromRS(rs) ok\");\n\n";
    	        
        	    for (Map.Entry<String, Column> entry : mapColumns.entrySet()) {
        	    	
        	        String columnName = entry.getKey();
        	        Column column = entry.getValue();
        	        
        	        String memberName = toJavaFieldName(columnName);
        	        
        	        // no se cargan las llaves primarias
        	        if (mapPrimaryKeys.containsKey(columnName)) {
        	        	continue;
        	        }
        	        
        	        output += 
        	        	"                _"; 
        	        
        	        if (mapPrimaryKeys.size() == 1 && mapPrimaryKeys.containsKey(columnName)) {
        	        	output += "id";
        	        }
        	        else {
        	        	output += memberName;
        	        }
        	        
        	        output += " = obj.get";
        	        
        	        if (mapPrimaryKeys.size() == 1 && mapPrimaryKeys.containsKey(columnName)) {
        	        	output += "Id";
        	        }
        	        else {
        	        	output += WordUtils.capitalize(memberName);
        	        }

        	        output += 
        	        	"();\n";

        	    }
        	    
    	        output +=
    	            "            }\n" +
    	            "        }\n" +
    	            "        catch (SQLException ex){\n" +
    	            "            // handle any errors\n" +
    	            "            System.out.println(\"SQLException: \" + ex.getMessage() + \" sentencia: \" + str_sql);\n" +
    	            "            System.out.println(\"SQLState: \" + ex.getSQLState());\n" +
    	            "            System.out.println(\"VendorError: \" + ex.getErrorCode());\n" +
    	            "            \n" +
    	            "            throw ex;\n" +
    	            "        }\n" +
    	            "        finally {\n" +
    	            "            // it is a good idea to release\n" +
    	            "            // resources in a finally{} block\n" +
    	            "            // in reverse-order of their creation\n" +
    	            "            // if they are no-longer needed\n" +
    	            "            if (rs != null) {\n" +
    	            "                try {\n" +
    	            "                    rs.close();\n" +
    	            "                } catch (SQLException sqlEx) { \n" +
    	            "                    \n" +
    	            "                } // ignore\n" +
    	            "                rs = null;\n" +
    	            "            }\n" +
    	            "            if (stmt != null) {\n" +
    	            "                try {\n" +
    	            "                    stmt.close();\n" +
    	            "                } catch (SQLException sqlEx) {\n" +
    	            "                    \n" +
    	            "                } // ignore\n" +
    	            "                stmt = null;\n" +
    	            "            }\n" +
    	            "        }        \n" +
    	            "        \n" +
    	            "    }\n";

    	        // fin load

        	    // save
        	    
    	        output +=
    	        	"\n" +
    	            "    public void save(Connection p_conn) throws SQLException {\n" +
    	            "        \n" +
    	            "        String str_sql = _str_sql +\n" +
    	            "            \"    WHERE\" +\n";
    	        
    	        output += buildWhereSentence(mapColumns, mapPrimaryKeys, mapJavaTypes);
    	        
    	        output += 
    	        	" +\n" +
    	            "            \"    LIMIT 0, 1\";\n" +
    	            "        \n" +
    	            "        //System.out.println(str_sql);\n" +
    	            "        \n" +
    	            "        // assume that conn is an already created JDBC connection (see previous examples)\n" +
    	            "        Statement stmt = null;\n" +
    	            "        ResultSet rs = null;\n" +
    	            "        Boolean exists = false;\n" +
    	            "        \n" +
    	            "        try {\n" +
    	            "            stmt = p_conn.createStatement();\n" +
    	            "            //System.out.println(\"stmt = p_conn.createStatement() ok\");\n" +
    	            "            rs = stmt.executeQuery(str_sql);\n" +
    	            "            //System.out.println(\"rs = stmt.executeQuery(str_sql) ok\");\n" +
    	            "\n" +
    	            "            // Now do something with the ResultSet ....\n" +
    	            "\n" +
    	            "            if (rs.next()) {\n" +
    	            "                // registro existe\n" +
    	            "                exists = true;\n" +
    	            "            }\n" +
    	            "\n" +
    	            "            rs.close();" +
    	            "\n" +
    	            "            stmt.close();\n" +
    	            "\n" +
    	            "            if (exists) {\n" +
    	            "            	// update\n" +
    	            "            	update(p_conn);\n" +
    	            "            }\n" +
    	            "            else {\n" +
    	            "            	// insert\n" +
    	            "            	insert(p_conn);\n" +
    	            "            }\n" +
    	            "        }\n" +
    	            "        catch (SQLException ex){\n" +
    	            "            // handle any errors\n" +
    	            "            System.out.println(\"SQLException: \" + ex.getMessage() + \" sentencia: \" + str_sql);\n" +
    	            "            System.out.println(\"SQLState: \" + ex.getSQLState());\n" +
    	            "            System.out.println(\"VendorError: \" + ex.getErrorCode());\n" +
    	            "            \n" +
    	            "            throw ex;\n" +
    	            "        }\n" +
    	            "        finally {\n" +
    	            "            // it is a good idea to release\n" +
    	            "            // resources in a finally{} block\n" +
    	            "            // in reverse-order of their creation\n" +
    	            "            // if they are no-longer needed\n" +
    	            "            if (rs != null) {\n" +
    	            "                try {\n" +
    	            "                    rs.close();\n" +
    	            "                } catch (SQLException sqlEx) { \n" +
    	            "                    \n" +
    	            "                } // ignore\n" +
    	            "                rs = null;\n" +
    	            "            }\n" +
    	            "            if (stmt != null) {\n" +
    	            "                try {\n" +
    	            "                    stmt.close();\n" +
    	            "                } catch (SQLException sqlEx) {\n" +
    	            "                    \n" +
    	            "                } // ignore\n" +
    	            "                stmt = null;\n" +
    	            "            }\n" +
    	            "        }        \n" +
    	            "        \n" +
    	            "    }\n";

    	        // fin save

    	        // toString
        	    
    	        output +=
    	        	"\n" +
    	            "    @Override\n" +
    	            "    public String toString() {\n" +
    	            "        return \"" + className + " [\" +";
    	        
    	        bFirst = false;
    	        
        	    for (Map.Entry<String, Column> entry : mapColumns.entrySet()) {
        	    	
        	        String columnName = entry.getKey();
        	        Column column = entry.getValue();
        	        
        	        String memberName = toJavaFieldName(columnName);
        	        
        	        if (!bFirst) {
        	        	bFirst = true;
        	        }
        	        else {
        	        	output += " + \",\" +";
        	        }
        	        
        	        output += "\n	           \"    _";
        	        
        	        switch(column.getBaseType()) {
	    	        	case "BIGINT":
	    	        	case "INT":
	    	        	case "SMALLINT":
	    	        	case "TINYINT":
	    	        	case "DECIMAL":
	    	        	case "DOUBLE":
	    	        	case "FLOAT":

	            	        if (mapPrimaryKeys.size() == 1 && mapPrimaryKeys.containsKey(columnName)) {
	            	        	output += "id";
	            	        }
	            	        else {
	            	        	output += memberName;
	            	        }
	            	        
	            	        output += " = \" + (_";
	            	        
	            	        if (mapPrimaryKeys.size() == 1 && mapPrimaryKeys.containsKey(columnName)) {
	            	        	output += "id";
	            	        }
	            	        else {
	            	        	output += memberName;
	            	        }
	            	        
	            	        output += " != null ? _";
	            	        
	            	        if (mapPrimaryKeys.size() == 1 && mapPrimaryKeys.containsKey(columnName)) {
	            	        	output += "id";
	            	        }
	            	        else {
	            	        	output += memberName;
	            	        }
	            	        
	            	        output += " : \"null\")";
	            	        
	    	        		break;
	    	        	case "CHAR":
	    	        	case "VARCHAR":
	    	        	case "LONGVARCHAR":
	    	        	case "TEXT":
	    	        		output += columnName + " = \" + (_" + memberName + " != null ? \"'\" + _" + memberName + " + \"'\" : \"null\")";	    	        		break;
	    	        	case "DATE":
	    	        	case "DATETIME":
	    	        	case "TIMESTAMP":
	    	        		output += columnName + " = \" + (_" + memberName + " != null ? \"STR_TO_DATE(\" + _" + memberName + " + \", '%Y-%m-%d %H:%i:%s')\" : \"null\")";
	    	        		break;
	    	        	case "BIT":
	    	        		output += columnName + " = \" + (_" + memberName + " != null ? \"b'\" + _" + memberName + " : \"null\")";
	    	        		break;
	    	        	default:
	    	        		throw new Exception("Tipo no soportado: " + column.getBaseType() + " columna: " + columnName);
        	        } // end switch
        	        
        	    }
    	        
    	        output +=
    	        	" +" +
    	            "\n			   \"]\";" +
    	            "\n" +
    	            "    }\n" +
    	            "\n";

    	        // fin toString
    	        
        	    // toJSON
        	    
    	        output +=
        	        	"\n" +
        	            "    public String toJSON() {\n" +
        	            "        return \"" + className + " : {\" +";
        	        
    	        bFirst = false;
    	        
        	    for (Map.Entry<String, Column> entry : mapColumns.entrySet()) {
        	    	
        	        String columnName = entry.getKey();
        	        Column column = entry.getValue();
        	        
        	        String memberName = toJavaFieldName(columnName);
        	        
        	        if (!bFirst) {
        	        	bFirst = true;
        	        }
        	        else {
        	        	output += " + \",\" +";
        	        }
        	        
        	        output += "\n	           \"    \\\"_";
        	        
        	        switch(column.getBaseType()) {
	    	        	case "BIGINT":
	    	        	case "INT":
	    	        	case "SMALLINT":
	    	        	case "TINYINT":
	    	        	case "DECIMAL":
	    	        	case "DOUBLE":
	    	        	case "FLOAT":

	            	        if (mapPrimaryKeys.size() == 1 && mapPrimaryKeys.containsKey(columnName)) {
	            	        	output += "id";
	            	        }
	            	        else {
	            	        	output += memberName;
	            	        }
	            	        
	            	        output += "\\\" : \" + (_";
	            	        
	            	        if (mapPrimaryKeys.size() == 1 && mapPrimaryKeys.containsKey(columnName)) {
	            	        	output += "id";
	            	        }
	            	        else {
	            	        	output += memberName;
	            	        }
	            	        
	            	        output += " != null ? _";
	            	        
	            	        if (mapPrimaryKeys.size() == 1 && mapPrimaryKeys.containsKey(columnName)) {
	            	        	output += "id";
	            	        }
	            	        else {
	            	        	output += memberName;
	            	        }
	            	        
	            	        output += " : \"null\")";
	            	        
	    	        		break;
	    	        	case "CHAR":
	    	        	case "VARCHAR":
	    	        	case "LONGVARCHAR":
	    	        	case "TEXT":
	    	        	case "DATE":
	    	        	case "DATETIME":
	    	        	case "TIMESTAMP":
	    	        		output += columnName + "\\\" : \" + (_" + memberName + " != null ? \"\\\"\" + _" + memberName + " + \"\\\"\" : \"null\")";	    	        		break;
	    	        	case "BIT":
	    	        		output += columnName + "\\\" : \" + (_" + memberName + " != null ? \"b'\" + _" + memberName + " : \"null\")";
	    	        		break;
	    	        	default:
	    	        		throw new Exception("Tipo no soportado: " + column.getBaseType() + " columna: " + columnName);
        	        } // end switch
        	        
        	    }
    	        
    	        output +=
    	        	" +" +
    	            "\n			   \"}\";" +
    	            "\n" +
    	            "    }\n" +
    	            "\n";

    	        // fin toJSON
    	        
        	    // toXML
        	    
    	        output +=
    	        	"\n" +
    	            "    public String toXML() {\n" +
    	            "        return \"<" + className + ">\" +";
    	        
        	    for (Map.Entry<String, Column> entry : mapColumns.entrySet()) {
        	    	
        	        String columnName = entry.getKey();
        	        Column column = entry.getValue();
        	        
        	        String memberName = toJavaFieldName(columnName);
        	                	        
        	        output += "\n	           \"    <";
        	        
        	        if (mapPrimaryKeys.size() == 1 && mapPrimaryKeys.containsKey(columnName)) {
        	        	output += "id";
        	        }
        	        else {
        	        	output += memberName;
        	        }
        	        
        	        output += "\" + (_";
        	        
        	        if (mapPrimaryKeys.size() == 1 && mapPrimaryKeys.containsKey(columnName)) {
        	        	output += "id";
        	        }
        	        else {
        	        	output += memberName;
        	        }
        	        
        	        output += " != null ? \">\" + _";
        	        
        	        if (mapPrimaryKeys.size() == 1 && mapPrimaryKeys.containsKey(columnName)) {
        	        	output += "id";
        	        }
        	        else {
        	        	output += memberName;
        	        }
        	        
        	        output += " + \"</";
        	        		
        	        if (mapPrimaryKeys.size() == 1 && mapPrimaryKeys.containsKey(columnName)) {
        	        	output += "id";
        	        }
        	        else {
        	        	output += memberName;
        	        }
        	        		
        	        output += ">\" : \" xsi:nil=\\\"true\\\" xmlns:xsi=\\\"http://www.w3.org/2001/XMLSchema-instance\\\"/>\")" + " +";
	            	        
        	        
        	    }
    	        
    	        output +=
    	            "\n			   \"</" + className + ">\";" +
    	            "\n" +
    	            "    }\n" +
    	            "\n";

    	        // fin toXML
    	        
        	    // fromXMLNode
        	    
        	    output +=
        	    	"\n" +
					"    public static " + className + " fromXMLNode(Node xmlNode) {\n" +
		            "        " + className + " ret = new " + className + "();\n\n" +
					"        Element element = (Element) xmlNode;\n\n";

        	    for (Map.Entry<String, Column> entry : mapColumns.entrySet()) {
        	    	
        	        String columnName = entry.getKey();
        	        Column column = entry.getValue();
        	        
        	        logger.debug(column.toString());
        	        
        	        String memberName = toJavaFieldName(columnName);
        	        
        	        output += 
        	        	"        ret.set"; 
        	        
        	        if (mapPrimaryKeys.size() == 1 && mapPrimaryKeys.containsKey(columnName)) {
        	        	output += "Id";
        	        }
        	        else {
        	        	output += WordUtils.capitalize(memberName);
        	        }
        	        
        	        output += "(";
        	        
        	        if (mapFunctionTypes.get(column.getBaseType()).equals("String") ) {
        	        	output += "element.getElementsByTagName(\"" + columnName + "\").item(0).getTextContent()";
        	        }
        	        else if (mapFunctionTypes.get(column.getBaseType()).equals("Boolean") || mapFunctionTypes.get(column.getBaseType()).equals("Double")) {
        	        	output += mapJavaTypes.get(column.getBaseType()) + ".valueOf(element.getElementsByTagName(\"" + columnName + "\").item(0).getTextContent())";
        	        }
        	        else {
        	        	output += mapJavaTypes.get(column.getBaseType()) + ".decode(element.getElementsByTagName(\"" + columnName + "\").item(0).getTextContent())";
        	        }

        	        output += 
        	        	");\n";

        	    }
        	    
        	    output +=
                    "\n" +			
		            "        return ret;\n" +
		            "    }\n";

        	           	    
        	    // fin fromXMLNode
    	        
    	        // fin clase
    	        
    	        output +=  
    	        	"}\n";    	        	

    	        
    	        //System.out.println(output);
    	        
    	        writeToFile(System.getProperty("output_dir") + "/" + className + ".java", output);
    	        
    	        
        	} // end while rs (tabla)

        	
        	
    		conn.close();
    		
        	

        }
		catch (SQLException ex) {
			// TODO Auto-generated catch block
			
        	logger.debug("SQLException: " + ex.getMessage());
        	logger.debug("SQLState: " + ex.getSQLState());
        	logger.debug("VendorError: " + ex.getErrorCode());
			ex.printStackTrace();
			
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} 
        catch (Exception ex) {
        	logger.debug("Exception: " + ex.getMessage());
        	ex.printStackTrace();
        }
	}

	private static String buildWhereSentence(
		Map<String, Column> p_mapColumns,
		Map<String, PrimaryKey> p_mapPrimaryKeys,
		Map<String, String> p_mapJavaTypes
	) 
	{
		String res = "";
		Boolean bFirst = false;
		
	    for (Map.Entry<String, PrimaryKey> entry : p_mapPrimaryKeys.entrySet()) {
	    	
	        String columnName = entry.getKey();
	        PrimaryKey pk = entry.getValue();
	        
	        String memberName = toJavaFieldName(columnName);
	        
	        if (!bFirst) {
	        	bFirst = true;
	        }
	        else {
	        	res += " + \" AND\" +\n";
	        }

	        res += "            \"    " + columnName + " = \" + " + p_mapJavaTypes.get(p_mapColumns.get(columnName).getBaseType()) + ".toString(this._";
	        
	        if (p_mapPrimaryKeys.size() == 1) {
	        	res += "id";
	        }
	        else {
	        	res += memberName;
	        }
	        
	        res += ")";

	    }
		
	    return res;
	}
	
	private static void writeToFile(String p_fileName, String p_content) throws IOException {
		 
		File file = new File(p_fileName);

		// if file doesnt exists, then create it
		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(p_content);
		bw.close();

		System.out.println("Done");
	}
	
	private static String toJavaFieldName(String name) { // "MY_COLUMN"
	    String name0 = name.replace("_", " "); // to "MY COLUMN"
	    name0 = WordUtils.capitalizeFully(name0); // to "My Column"
	    name0 = name0.replace(" ", ""); // to "MyColumn"
	    name0 = WordUtils.uncapitalize(name0); // to "myColumn"
	    return name0;
	}

	private static String toJavaClassName(String name) { // "MY_TABLE"
	    String name0 = name.replace("_", " "); // to "MY TABLE"
	    name0 = WordUtils.capitalizeFully(name0); // to "My Table"
	    name0 = name0.replace(" ", ""); // to "MyTable"
	    return name0;
	}

}
