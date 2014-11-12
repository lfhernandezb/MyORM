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
public class Usuario {
    protected String _nombre;
    protected String _fechaModificacion;
    protected String _fechaVencimientoLicencia;
    protected Long _id;
    protected Boolean _hombre;
    protected Long _idComuna;
    protected Boolean _borrado;
    protected String _telefono;
    protected String _correo;
    protected String _fechaNacimiento;

    private final static String _str_sql = 
        "    SELECT" +
        "    us.nombre AS nombre," +
        "    DATE_FORMAT(us.fecha_modificacion, '%Y-%m-%d %H:%i:%s') AS fecha_modificacion," +
        "    DATE_FORMAT(us.fecha_vencimiento_licencia, '%Y-%m-%d %H:%i:%s') AS fecha_vencimiento_licencia," +
        "    us.id_usuario AS id," +
        "    0+us.hombre AS hombre," +
        "    us.id_comuna AS id_comuna," +
        "    0+us.borrado AS borrado," +
        "    us.telefono AS telefono," +
        "    us.correo AS correo," +
        "    DATE_FORMAT(us.fecha_nacimiento, '%Y-%m-%d %H:%i:%s') AS fecha_nacimiento" +
        "    FROM usuario us";

    public Usuario() {
        _nombre = null;
        _fechaModificacion = null;
        _fechaVencimientoLicencia = null;
        _id = null;
        _hombre = null;
        _idComuna = null;
        _borrado = null;
        _telefono = null;
        _correo = null;
        _fechaNacimiento = null;

    }
    /**
     * @return the _nombre
     */
    public String getNombre() {
        return _nombre;
    }
    /**
     * @return the _fechaModificacion
     */
    public String getFechaModificacion() {
        return _fechaModificacion;
    }
    /**
     * @return the _fechaVencimientoLicencia
     */
    public String getFechaVencimientoLicencia() {
        return _fechaVencimientoLicencia;
    }
    /**
     * @return the _id
     */
    public Long getId() {
        return _id;
    }
    /**
     * @return the _hombre
     */
    public Boolean getHombre() {
        return _hombre;
    }
    /**
     * @return the _idComuna
     */
    public Long getIdComuna() {
        return _idComuna;
    }
    /**
     * @return the _borrado
     */
    public Boolean getBorrado() {
        return _borrado;
    }
    /**
     * @return the _telefono
     */
    public String getTelefono() {
        return _telefono;
    }
    /**
     * @return the _correo
     */
    public String getCorreo() {
        return _correo;
    }
    /**
     * @return the _fechaNacimiento
     */
    public String getFechaNacimiento() {
        return _fechaNacimiento;
    }
    /**
     * @param _nombre the _nombre to set
     */
    public void setNombre(String _nombre) {
        this._nombre = _nombre;
    }
    /**
     * @param _fechaModificacion the _fechaModificacion to set
     */
    public void setFechaModificacion(String _fechaModificacion) {
        this._fechaModificacion = _fechaModificacion;
    }
    /**
     * @param _fechaVencimientoLicencia the _fechaVencimientoLicencia to set
     */
    public void setFechaVencimientoLicencia(String _fechaVencimientoLicencia) {
        this._fechaVencimientoLicencia = _fechaVencimientoLicencia;
    }
    /**
     * @param _id the _id to set
     */
    public void setId(Long _id) {
        this._id = _id;
    }
    /**
     * @param _hombre the _hombre to set
     */
    public void setHombre(Boolean _hombre) {
        this._hombre = _hombre;
    }
    /**
     * @param _idComuna the _idComuna to set
     */
    public void setIdComuna(Long _idComuna) {
        this._idComuna = _idComuna;
    }
    /**
     * @param _borrado the _borrado to set
     */
    public void setBorrado(Boolean _borrado) {
        this._borrado = _borrado;
    }
    /**
     * @param _telefono the _telefono to set
     */
    public void setTelefono(String _telefono) {
        this._telefono = _telefono;
    }
    /**
     * @param _correo the _correo to set
     */
    public void setCorreo(String _correo) {
        this._correo = _correo;
    }
    /**
     * @param _fechaNacimiento the _fechaNacimiento to set
     */
    public void setFechaNacimiento(String _fechaNacimiento) {
        this._fechaNacimiento = _fechaNacimiento;
    }

    public static Usuario fromRS(ResultSet p_rs) throws SQLException {
        Usuario ret = new Usuario();

        ret.setNombre(p_rs.getString("nombre"));
        ret.setFechaModificacion(p_rs.getString("fecha_modificacion"));
        ret.setFechaVencimientoLicencia(p_rs.getString("fecha_vencimiento_licencia"));
        ret.setId(p_rs.getLong("id"));
        ret.setHombre(p_rs.getBoolean("hombre"));
        ret.setIdComuna(p_rs.getLong("id_comuna"));
        ret.setBorrado(p_rs.getBoolean("borrado"));
        ret.setTelefono(p_rs.getString("telefono"));
        ret.setCorreo(p_rs.getString("correo"));
        ret.setFechaNacimiento(p_rs.getString("fecha_nacimiento"));

        return ret;
    }

    public static Usuario getByParameter(Connection p_conn, String p_key, String p_value) throws SQLException {
        Usuario ret = null;
        
        String str_sql = _str_sql +
            "  WHERE us." + p_key + " = " + p_value +
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

    public static Usuario getById(Connection p_conn, String p_id) throws Exception {
        return getByParameter(p_conn, "id_usuario", p_id);
    }
    
    public static ArrayList<Usuario> seek(Connection p_conn, ArrayList<AbstractMap.SimpleEntry<String, String>> p_parameters, String p_order, String p_direction, int p_offset, int p_limit) throws Exception {
        Statement stmt = null;
        ResultSet rs = null;
        String str_sql;
        ArrayList<Usuario> ret;
        
        str_sql = "";
        
        try {
            ArrayList<String> array_clauses = new ArrayList<String>();
            
            ret = new ArrayList<Usuario>();
            
            str_sql = _str_sql;
            
            for (AbstractMap.SimpleEntry<String, String> p : p_parameters) {
                if (p.getKey().equals("id_usuario")) {
                    array_clauses.add("us.id_usuario = " + p.getValue());
                }
                else if (p.getKey().equals("id_comuna")) {
                    array_clauses.add("us.id_comuna = " + p.getValue());
                }
                else if (p.getKey().equals("mas reciente")) {
                    array_clauses.add("us.fecha_modificacion > STR_TO_DATE(" + p.getValue() + ", '%Y-%m-%d %H:%i:%s')");
                }
                else if (p.getKey().equals("no borrado")) {
                    array_clauses.add("us.borrado = 0");
                }
                else if (p.getKey().equals("borrado")) {
                    array_clauses.add("us.borrado = 1");
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
            "    UPDATE usuario" +
            "    SET" +
            "    nombre = " + (_nombre != null ? "'" + _nombre + "'" : "null") + "," +
            "    fecha_modificacion = " + (_fechaModificacion != null ? "STR_TO_DATE(" + _fechaModificacion + ", '%Y-%m-%d %H:%i:%s')" : "null") + "," +
            "    fecha_vencimiento_licencia = " + (_fechaVencimientoLicencia != null ? "STR_TO_DATE(" + _fechaVencimientoLicencia + ", '%Y-%m-%d %H:%i:%s')" : "null") + "," +
            "    hombre = " + (_hombre != null ? "b'" + _hombre : "null") + "," +
            "    borrado = " + (_borrado != null ? "b'" + _borrado : "null") + "," +
            "    telefono = " + (_telefono != null ? "'" + _telefono + "'" : "null") + "," +
            "    correo = " + (_correo != null ? "'" + _correo + "'" : "null") + "," +
            "    fecha_nacimiento = " + (_fechaNacimiento != null ? "STR_TO_DATE(" + _fechaNacimiento + ", '%Y-%m-%d %H:%i:%s')" : "null") +
            "    WHERE" +
            "    id_usuario = " + Long.toString(this._id);

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
            "    INSERT INTO usuario" +
            "    (" +
            "    nombre, " +
            "    fecha_vencimiento_licencia, " +
            "    id_usuario, " +
            "    hombre, " +
            "    id_comuna, " +
            "    telefono, " +
            "    correo, " +
            "    fecha_nacimiento)" +
            "    VALUES" +
            "    (" +
            "    " + (_nombre != null ? "'" + _nombre + "'" : "null") + "," +
            "    " + (_fechaVencimientoLicencia != null ? "STR_TO_DATE(" + _fechaVencimientoLicencia + ", '%Y-%m-%d %H:%i:%s')" : "null") + "," +
            "    " + (_id != null ? "'" + _id + "'" : "null") + "," +
            "    " + (_hombre != null ? "b'" + (_hombre ? 1 : 0) + "'" : "null") + "," +
            "    " + (_idComuna != null ? "'" + _idComuna + "'" : "null") + "," +
            "    " + (_telefono != null ? "'" + _telefono + "'" : "null") + "," +
            "    " + (_correo != null ? "'" + _correo + "'" : "null") + "," +
            "    " + (_fechaNacimiento != null ? "STR_TO_DATE(" + _fechaNacimiento + ", '%Y-%m-%d %H:%i:%s')" : "null") +
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
            "    DELETE FROM usuario" +
            "    WHERE" +
            "    id_usuario = " + Long.toString(this._id);

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
        Usuario obj = null;
        
        String str_sql = _str_sql +
            "    WHERE" +
            "    id_usuario = " + Long.toString(this._id) +
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

                _nombre = obj.getNombre();
                _fechaModificacion = obj.getFechaModificacion();
                _fechaVencimientoLicencia = obj.getFechaVencimientoLicencia();
                _hombre = obj.getHombre();
                _idComuna = obj.getIdComuna();
                _borrado = obj.getBorrado();
                _telefono = obj.getTelefono();
                _correo = obj.getCorreo();
                _fechaNacimiento = obj.getFechaNacimiento();
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
            "    id_usuario = " + Long.toString(this._id) +
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
        return "Usuario [" +
	           "    _nombre = " + (_nombre != null ? "'" + _nombre + "'" : "null") + "," +
	           "    _fecha_modificacion = " + (_fechaModificacion != null ? "STR_TO_DATE(" + _fechaModificacion + ", '%Y-%m-%d %H:%i:%s')" : "null") + "," +
	           "    _fecha_vencimiento_licencia = " + (_fechaVencimientoLicencia != null ? "STR_TO_DATE(" + _fechaVencimientoLicencia + ", '%Y-%m-%d %H:%i:%s')" : "null") + "," +
	           "    _id = " + (_id != null ? _id : "null") + "," +
	           "    _hombre = " + (_hombre != null ? "b'" + _hombre : "null") + "," +
	           "    _idComuna = " + (_idComuna != null ? _idComuna : "null") + "," +
	           "    _borrado = " + (_borrado != null ? "b'" + _borrado : "null") + "," +
	           "    _telefono = " + (_telefono != null ? "'" + _telefono + "'" : "null") + "," +
	           "    _correo = " + (_correo != null ? "'" + _correo + "'" : "null") + "," +
	           "    _fecha_nacimiento = " + (_fechaNacimiento != null ? "STR_TO_DATE(" + _fechaNacimiento + ", '%Y-%m-%d %H:%i:%s')" : "null") +
			   "]";
    }


    public String toJSON() {
        return "Usuario : {" +
	           "    \"_nombre\" : " + (_nombre != null ? "\"" + _nombre + "\"" : "null") + "," +
	           "    \"_fecha_modificacion\" : " + (_fechaModificacion != null ? "\"" + _fechaModificacion + "\"" : "null") + "," +
	           "    \"_fecha_vencimiento_licencia\" : " + (_fechaVencimientoLicencia != null ? "\"" + _fechaVencimientoLicencia + "\"" : "null") + "," +
	           "    \"_id\" : " + (_id != null ? _id : "null") + "," +
	           "    \"_hombre\" : " + (_hombre != null ? "b'" + _hombre : "null") + "," +
	           "    \"_idComuna\" : " + (_idComuna != null ? _idComuna : "null") + "," +
	           "    \"_borrado\" : " + (_borrado != null ? "b'" + _borrado : "null") + "," +
	           "    \"_telefono\" : " + (_telefono != null ? "\"" + _telefono + "\"" : "null") + "," +
	           "    \"_correo\" : " + (_correo != null ? "\"" + _correo + "\"" : "null") + "," +
	           "    \"_fecha_nacimiento\" : " + (_fechaNacimiento != null ? "\"" + _fechaNacimiento + "\"" : "null") +
			   "}";
    }


    public String toXML() {
        return "<Usuario>" +
	           "    <nombre" + (_nombre != null ? ">" + _nombre + "</nombre>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <fechaModificacion" + (_fechaModificacion != null ? ">" + _fechaModificacion + "</fechaModificacion>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <fechaVencimientoLicencia" + (_fechaVencimientoLicencia != null ? ">" + _fechaVencimientoLicencia + "</fechaVencimientoLicencia>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <id" + (_id != null ? ">" + _id + "</id>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <hombre" + (_hombre != null ? ">" + _hombre + "</hombre>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <idComuna" + (_idComuna != null ? ">" + _idComuna + "</idComuna>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <borrado" + (_borrado != null ? ">" + _borrado + "</borrado>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <telefono" + (_telefono != null ? ">" + _telefono + "</telefono>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <correo" + (_correo != null ? ">" + _correo + "</correo>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <fechaNacimiento" + (_fechaNacimiento != null ? ">" + _fechaNacimiento + "</fechaNacimiento>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
			   "</Usuario>";
    }


    public static Usuario fromXMLNode(Node xmlNode) {
        Usuario ret = new Usuario();

        Element element = (Element) xmlNode;

        ret.setNombre(element.getElementsByTagName("nombre").item(0).getTextContent());
        ret.setFechaModificacion(element.getElementsByTagName("fecha_modificacion").item(0).getTextContent());
        ret.setFechaVencimientoLicencia(element.getElementsByTagName("fecha_vencimiento_licencia").item(0).getTextContent());
        ret.setId(Long.decode(element.getElementsByTagName("id_usuario").item(0).getTextContent()));
        ret.setHombre(Boolean.valueOf(element.getElementsByTagName("hombre").item(0).getTextContent()));
        ret.setIdComuna(Long.decode(element.getElementsByTagName("id_comuna").item(0).getTextContent()));
        ret.setBorrado(Boolean.valueOf(element.getElementsByTagName("borrado").item(0).getTextContent()));
        ret.setTelefono(element.getElementsByTagName("telefono").item(0).getTextContent());
        ret.setCorreo(element.getElementsByTagName("correo").item(0).getTextContent());
        ret.setFechaNacimiento(element.getElementsByTagName("fecha_nacimiento").item(0).getTextContent());

        return ret;
    }
}
