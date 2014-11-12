/**
 * 
 */
package cl.dsoft.carws.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.AbstractMap;
import java.util.ArrayList;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * @author petete-ntbk
 *
 */
public class Region {
    protected String _region;
    protected Long _id;
    protected Long _idPais;
    protected String _fechaModificacion;

    private final static String _str_sql = 
        "    SELECT" +
        "    re.region AS region," +
        "    re.id_region AS id," +
        "    re.id_pais AS id_pais," +
        "    DATE_FORMAT(re.fecha_modificacion, '%Y-%m-%d %H:%i:%s') AS fecha_modificacion" +
        "    FROM region re";

    public Region() {
        _region = null;
        _id = null;
        _idPais = null;
        _fechaModificacion = null;

    }
    /**
     * @return the _region
     */
    public String getRegion() {
        return _region;
    }
    /**
     * @return the _id
     */
    public Long getId() {
        return _id;
    }
    /**
     * @return the _idPais
     */
    public Long getIdPais() {
        return _idPais;
    }
    /**
     * @return the _fechaModificacion
     */
    public String getFechaModificacion() {
        return _fechaModificacion;
    }
    /**
     * @param _region the _region to set
     */
    public void setRegion(String _region) {
        this._region = _region;
    }
    /**
     * @param _id the _id to set
     */
    public void setId(Long _id) {
        this._id = _id;
    }
    /**
     * @param _idPais the _idPais to set
     */
    public void setIdPais(Long _idPais) {
        this._idPais = _idPais;
    }
    /**
     * @param _fechaModificacion the _fechaModificacion to set
     */
    public void setFechaModificacion(String _fechaModificacion) {
        this._fechaModificacion = _fechaModificacion;
    }

    public static Region fromRS(ResultSet p_rs) throws SQLException {
        Region ret = new Region();

        ret.setRegion(p_rs.getString("region"));
        ret.setId(p_rs.getLong("id"));
        ret.setIdPais(p_rs.getLong("id_pais"));
        ret.setFechaModificacion(p_rs.getString("fecha_modificacion"));

        return ret;
    }

    public static Region getByParameter(Connection p_conn, String p_key, String p_value) throws SQLException {
        Region ret = null;
        
        String str_sql = _str_sql +
            "  WHERE re." + p_key + " = " + p_value +
            "  LIMIT 0, 1";
        
        //System.out.println(str_sql);
        
        // assume that conn is an already created JDBC connection (see previous examples)
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = p_conn.createStatement();
            //System.out.println("stmt = p_conn.createStatement() ok");
            rs = stmt.executeQuery(str_sql);
            //System.out.println("rs = stmt.executeQuery(str_sql) ok");

            // Now do something with the ResultSet ....
            
            if (rs.next()) {
                //System.out.println("rs.next() ok");
                ret = fromRS(rs);
                //System.out.println("fromRS(rs) ok");
            }
        }
        catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage() + " sentencia: " + str_sql);
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            
            throw ex;
        }
        finally {
            // it is a good idea to release
            // resources in a finally{} block
            // in reverse-order of their creation
            // if they are no-longer needed
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) { 
                    
                } // ignore
                rs = null;
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                    
                } // ignore
                stmt = null;
            }
        }        
        
        return ret;        
    }

    public static Region getById(Connection p_conn, String p_id) throws Exception {
        return getByParameter(p_conn, "id_region", p_id);
    }
    
    public static ArrayList<Region> seek(Connection p_conn, ArrayList<AbstractMap.SimpleEntry<String, String>> p_parameters, String p_order, String p_direction, int p_offset, int p_limit) throws Exception {
        Statement stmt = null;
        ResultSet rs = null;
        String str_sql;
        ArrayList<Region> ret;
        
        str_sql = "";
        
        try {
            ArrayList<String> array_clauses = new ArrayList<String>();
            
            ret = new ArrayList<Region>();
            
            str_sql = _str_sql;
            
            for (AbstractMap.SimpleEntry<String, String> p : p_parameters) {
                if (p.getKey().equals("id_region")) {
                    array_clauses.add("re.id_region = " + p.getValue());
                }
                else if (p.getKey().equals("id_pais")) {
                    array_clauses.add("re.id_pais = " + p.getValue());
                }
                else if (p.getKey().equals("mas reciente")) {
                    array_clauses.add("re.fecha_modificacion > STR_TO_DATE(" + p.getValue() + ", '%Y-%m-%d %H:%i:%s')");
                }
                else {
                    throw new Exception("Parametro no soportado: " + p.getKey());
                }
            }
                                
            boolean bFirstTime = false;
            
            for(String clause : array_clauses) {
                if (!bFirstTime) {
                     bFirstTime = true;
                     str_sql += " WHERE ";
                }
                else {
                     str_sql += " AND ";
                }
                str_sql += clause;
            }
            
            if (p_order != null && p_direction != null) {
                str_sql += " ORDER BY " + p_order + " " + p_direction;
            }
            
            if (p_offset != -1 && p_limit != -1) {
                str_sql += "  LIMIT " +  Integer.toString(p_offset) + ", " + Integer.toString(p_limit);
            }
            
            //echo "<br>" . str_sql . "<br>";
        
            stmt = p_conn.createStatement();
            
            rs = stmt.executeQuery(str_sql);
            
            while (rs.next()) {
                ret.add(fromRS(rs));
            }
            /*
            if (ret.size() == 0) {
                ret = null;
            }
            */
        }
        catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage() + " sentencia: " + str_sql);
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            
            throw ex;
        }
        catch (Exception ex) {
            throw ex;
        }
        finally {
            // it is a good idea to release
            // resources in a finally{} block
            // in reverse-order of their creation
            // if they are no-longer needed
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) { 
                    
                } // ignore
                rs = null;
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                    
                } // ignore
                stmt = null;
            }
        }        

        return ret;
    }

    public int update(Connection p_conn) throws SQLException {

        int ret = -1;
        Statement stmt = null;

        String str_sql =
            "    UPDATE region" +
            "    SET" +
            "    region = " + (_region != null ? "'" + _region + "'" : "null") + "," +
            "    fecha_modificacion = " + (_fechaModificacion != null ? "STR_TO_DATE(" + _fechaModificacion + ", '%Y-%m-%d %H:%i:%s')" : "null") +
            "    WHERE" +
            "    id_region = " + Long.toString(this._id);

        try {
            stmt = p_conn.createStatement();
            
            ret = stmt.executeUpdate(str_sql);
            /*
            if (stmt.executeUpdate(str_sql) < 1) {
                throw new Exception("No hubo filas afectadas");
            }
            */
            
        }
        catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage() + " sentencia: " + str_sql);
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            
            throw ex;
        }
        finally {
            // it is a good idea to release
            // resources in a finally{} block
            // in reverse-order of their creation
            // if they are no-longer needed
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                    
                } // ignore
                stmt = null;
            }
        }
        
        return ret;
    }
    
    public int insert(Connection p_conn) throws SQLException {
        
        int ret = -1;
        Statement stmt = null;
        ResultSet rs = null;

        String str_sql =
            "    INSERT INTO region" +
            "    (" +
            "    region, " +
            "    id_region, " +
            "    id_pais)" +
            "    VALUES" +
            "    (" +
            "    " + (_region != null ? "'" + _region + "'" : "null") + "," +
            "    " + (_id != null ? "'" + _id + "'" : "null") + "," +
            "    " + (_idPais != null ? "'" + _idPais + "'" : "null") +
            "    )";
        
        try {
            stmt = p_conn.createStatement();

            ret = stmt.executeUpdate(str_sql);

            load(p_conn);

        }
        catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage() + " sentencia: " + str_sql);
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            
            throw ex;
        }
        finally {
            // it is a good idea to release
            // resources in a finally{} block
            // in reverse-order of their creation
            // if they are no-longer needed
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) { 
                    
                } // ignore
                rs = null;
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                    
                } // ignore
                stmt = null;
            }
        }
        
        return ret;
    }

    public int delete(Connection p_conn) throws SQLException {

        int ret = -1;
        Statement stmt = null;

        String str_sql =
            "    DELETE FROM region" +
            "    WHERE" +
            "    id_region = " + Long.toString(this._id);

        try {
            stmt = p_conn.createStatement();
            
            ret = stmt.executeUpdate(str_sql);
        }
        catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage() + " sentencia: " + str_sql);
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            
            throw ex;
        }
        finally {
            // it is a good idea to release
            // resources in a finally{} block
            // in reverse-order of their creation
            // if they are no-longer needed
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                    
                } // ignore
                stmt = null;
            }
        }
        
        return ret;
    }

    public void load(Connection p_conn) throws SQLException {
        Region obj = null;
        
        String str_sql = _str_sql +
            "    WHERE" +
            "    id_region = " + Long.toString(this._id) +
            "    LIMIT 0, 1";
        
        //System.out.println(str_sql);
        
        // assume that conn is an already created JDBC connection (see previous examples)
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = p_conn.createStatement();
            //System.out.println("stmt = p_conn.createStatement() ok");
            rs = stmt.executeQuery(str_sql);
            //System.out.println("rs = stmt.executeQuery(str_sql) ok");

            // Now do something with the ResultSet ....
            
            if (rs.next()) {
                //System.out.println("rs.next() ok");
                obj = fromRS(rs);
                //System.out.println("fromRS(rs) ok");

                _region = obj.getRegion();
                _idPais = obj.getIdPais();
                _fechaModificacion = obj.getFechaModificacion();
            }
        }
        catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage() + " sentencia: " + str_sql);
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            
            throw ex;
        }
        finally {
            // it is a good idea to release
            // resources in a finally{} block
            // in reverse-order of their creation
            // if they are no-longer needed
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) { 
                    
                } // ignore
                rs = null;
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                    
                } // ignore
                stmt = null;
            }
        }        
        
    }

    public void save(Connection p_conn) throws SQLException {
        
        String str_sql = _str_sql +
            "    WHERE" +
            "    id_region = " + Long.toString(this._id) +
            "    LIMIT 0, 1";
        
        //System.out.println(str_sql);
        
        // assume that conn is an already created JDBC connection (see previous examples)
        Statement stmt = null;
        ResultSet rs = null;
        Boolean exists = false;
        
        try {
            stmt = p_conn.createStatement();
            //System.out.println("stmt = p_conn.createStatement() ok");
            rs = stmt.executeQuery(str_sql);
            //System.out.println("rs = stmt.executeQuery(str_sql) ok");

            // Now do something with the ResultSet ....

            if (rs.next()) {
                // registro existe
                exists = true;
            }

            rs.close();
            stmt.close();

            if (exists) {
            	// update
            	update(p_conn);
            }
            else {
            	// insert
            	insert(p_conn);
            }
        }
        catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage() + " sentencia: " + str_sql);
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            
            throw ex;
        }
        finally {
            // it is a good idea to release
            // resources in a finally{} block
            // in reverse-order of their creation
            // if they are no-longer needed
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) { 
                    
                } // ignore
                rs = null;
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                    
                } // ignore
                stmt = null;
            }
        }        
        
    }

    @Override
    public String toString() {
        return "Region [" +
	           "    _region = " + (_region != null ? "'" + _region + "'" : "null") + "," +
	           "    _id = " + (_id != null ? _id : "null") + "," +
	           "    _idPais = " + (_idPais != null ? _idPais : "null") + "," +
	           "    _fecha_modificacion = " + (_fechaModificacion != null ? "STR_TO_DATE(" + _fechaModificacion + ", '%Y-%m-%d %H:%i:%s')" : "null") +
			   "]";
    }


    public String toJSON() {
        return "Region : {" +
	           "    \"_region\" : " + (_region != null ? "\"" + _region + "\"" : "null") + "," +
	           "    \"_id\" : " + (_id != null ? _id : "null") + "," +
	           "    \"_idPais\" : " + (_idPais != null ? _idPais : "null") + "," +
	           "    \"_fecha_modificacion\" : " + (_fechaModificacion != null ? "\"" + _fechaModificacion + "\"" : "null") +
			   "}";
    }


    public String toXML() {
        return "<Region>" +
	           "    <region" + (_region != null ? ">" + _region + "</region>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <id" + (_id != null ? ">" + _id + "</id>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <idPais" + (_idPais != null ? ">" + _idPais + "</idPais>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <fechaModificacion" + (_fechaModificacion != null ? ">" + _fechaModificacion + "</fechaModificacion>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
			   "</Region>";
    }


    public static Region fromXMLNode(Node xmlNode) {
        Region ret = new Region();

        Element element = (Element) xmlNode;

        ret.setRegion(element.getElementsByTagName("region").item(0).getTextContent());
        ret.setId(Long.decode(element.getElementsByTagName("id_region").item(0).getTextContent()));
        ret.setIdPais(Long.decode(element.getElementsByTagName("id_pais").item(0).getTextContent()));
        ret.setFechaModificacion(element.getElementsByTagName("fecha_modificacion").item(0).getTextContent());

        return ret;
    }
}
