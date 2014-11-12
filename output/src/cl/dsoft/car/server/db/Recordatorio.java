/**
 * 
 */
package cl.dsoft.car.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.AbstractMap;
import java.util.ArrayList;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import cl.dsoft.UnsupportedParameterException;

/**
 * @author petete-ntbk
 *
 */
public class Recordatorio {
    protected Long _idRecordatorio;
    protected String _titulo;
    protected String _fechaModificacion;
    protected String _fecha;
    protected Long _idUsuario;
    protected Long _idVehiculo;
    protected Boolean _borrado;
    protected String _descripcion;
    protected Boolean _recordarKm;
    protected Integer _km;
    protected Boolean _recordarFecha;

    private final static String _str_sql = 
        "    SELECT" +
        "    re.id_recordatorio AS id_recordatorio," +
        "    re.titulo AS titulo," +
        "    DATE_FORMAT(re.fecha_modificacion, '%Y-%m-%d %H:%i:%s') AS fecha_modificacion," +
        "    DATE_FORMAT(re.fecha, '%Y-%m-%d %H:%i:%s') AS fecha," +
        "    re.id_usuario AS id_usuario," +
        "    re.id_vehiculo AS id_vehiculo," +
        "    0+re.borrado AS borrado," +
        "    re.descripcion AS descripcion," +
        "    0+re.recordar_km AS recordar_km," +
        "    re.km AS km," +
        "    0+re.recordar_fecha AS recordar_fecha" +
        "    FROM recordatorio re";

    public Recordatorio() {
        _idRecordatorio = null;
        _titulo = null;
        _fechaModificacion = null;
        _fecha = null;
        _idUsuario = null;
        _idVehiculo = null;
        _borrado = null;
        _descripcion = null;
        _recordarKm = null;
        _km = null;
        _recordarFecha = null;

    }
    /**
     * @return the _idRecordatorio
     */
    public Long getIdRecordatorio() {
        return _idRecordatorio;
    }
    /**
     * @return the _titulo
     */
    public String getTitulo() {
        return _titulo;
    }
    /**
     * @return the _fechaModificacion
     */
    public String getFechaModificacion() {
        return _fechaModificacion;
    }
    /**
     * @return the _fecha
     */
    public String getFecha() {
        return _fecha;
    }
    /**
     * @return the _idUsuario
     */
    public Long getIdUsuario() {
        return _idUsuario;
    }
    /**
     * @return the _idVehiculo
     */
    public Long getIdVehiculo() {
        return _idVehiculo;
    }
    /**
     * @return the _borrado
     */
    public Boolean getBorrado() {
        return _borrado;
    }
    /**
     * @return the _descripcion
     */
    public String getDescripcion() {
        return _descripcion;
    }
    /**
     * @return the _recordarKm
     */
    public Boolean getRecordarKm() {
        return _recordarKm;
    }
    /**
     * @return the _km
     */
    public Integer getKm() {
        return _km;
    }
    /**
     * @return the _recordarFecha
     */
    public Boolean getRecordarFecha() {
        return _recordarFecha;
    }
    /**
     * @param _idRecordatorio the _idRecordatorio to set
     */
    public void setIdRecordatorio(Long _idRecordatorio) {
        this._idRecordatorio = _idRecordatorio;
    }
    /**
     * @param _titulo the _titulo to set
     */
    public void setTitulo(String _titulo) {
        this._titulo = _titulo;
    }
    /**
     * @param _fechaModificacion the _fechaModificacion to set
     */
    public void setFechaModificacion(String _fechaModificacion) {
        this._fechaModificacion = _fechaModificacion;
    }
    /**
     * @param _fecha the _fecha to set
     */
    public void setFecha(String _fecha) {
        this._fecha = _fecha;
    }
    /**
     * @param _idUsuario the _idUsuario to set
     */
    public void setIdUsuario(Long _idUsuario) {
        this._idUsuario = _idUsuario;
    }
    /**
     * @param _idVehiculo the _idVehiculo to set
     */
    public void setIdVehiculo(Long _idVehiculo) {
        this._idVehiculo = _idVehiculo;
    }
    /**
     * @param _borrado the _borrado to set
     */
    public void setBorrado(Boolean _borrado) {
        this._borrado = _borrado;
    }
    /**
     * @param _descripcion the _descripcion to set
     */
    public void setDescripcion(String _descripcion) {
        this._descripcion = _descripcion;
    }
    /**
     * @param _recordarKm the _recordarKm to set
     */
    public void setRecordarKm(Boolean _recordarKm) {
        this._recordarKm = _recordarKm;
    }
    /**
     * @param _km the _km to set
     */
    public void setKm(Integer _km) {
        this._km = _km;
    }
    /**
     * @param _recordarFecha the _recordarFecha to set
     */
    public void setRecordarFecha(Boolean _recordarFecha) {
        this._recordarFecha = _recordarFecha;
    }

    public static Recordatorio fromRS(ResultSet p_rs) throws SQLException {
        Recordatorio ret = new Recordatorio();

        ret.setIdRecordatorio(p_rs.getLong("id_recordatorio"));
        ret.setTitulo(p_rs.getString("titulo"));
        ret.setFechaModificacion(p_rs.getString("fecha_modificacion"));
        ret.setFecha(p_rs.getString("fecha"));
        ret.setIdUsuario(p_rs.getLong("id_usuario"));
        ret.setIdVehiculo(p_rs.getLong("id_vehiculo"));
        ret.setBorrado(p_rs.getBoolean("borrado"));
        ret.setDescripcion(p_rs.getString("descripcion"));
        ret.setRecordarKm(p_rs.getBoolean("recordar_km"));
        ret.setKm(p_rs.getInt("km"));
        ret.setRecordarFecha(p_rs.getBoolean("recordar_fecha"));

        return ret;
    }

    public static Recordatorio getByParameter(Connection p_conn, String p_key, String p_value) throws SQLException {
        Recordatorio ret = null;
        
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

    
    public static ArrayList<Recordatorio> seek(Connection p_conn, ArrayList<AbstractMap.SimpleEntry<String, String>> p_parameters, String p_order, String p_direction, int p_offset, int p_limit) throws UnsupportedParameterException, SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        String str_sql;
        ArrayList<Recordatorio> ret;
        
        str_sql = "";
        
        try {
            ArrayList<String> array_clauses = new ArrayList<String>();
            
            ret = new ArrayList<Recordatorio>();
            
            str_sql = _str_sql;
            
            for (AbstractMap.SimpleEntry<String, String> p : p_parameters) {
                if (p.getKey().equals("id_recordatorio")) {
                    array_clauses.add("re.id_recordatorio = " + p.getValue());
                }
                else if (p.getKey().equals("id_usuario")) {
                    array_clauses.add("re.id_usuario = " + p.getValue());
                }
                else if (p.getKey().equals("id_usuario")) {
                    array_clauses.add("re.id_usuario = " + p.getValue());
                }
                else if (p.getKey().equals("id_vehiculo")) {
                    array_clauses.add("re.id_vehiculo = " + p.getValue());
                }
                else if (p.getKey().equals("mas reciente")) {
                    array_clauses.add("re.fecha_modificacion > STR_TO_DATE('" + p.getValue() + "', '%Y-%m-%d %H:%i:%s')");
                }
                else if (p.getKey().equals("no borrado")) {
                    array_clauses.add("re.borrado = 0");
                }
                else if (p.getKey().equals("borrado")) {
                    array_clauses.add("re.borrado = 1");
                }
                else {
                    throw new UnsupportedParameterException("Parametro no soportado: " + p.getKey());
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
        catch (UnsupportedParameterException ex) {
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
            "    UPDATE recordatorio" +
            "    SET" +
            "    titulo = " + (_titulo != null ? "'" + _titulo + "'" : "null") + "," +
            "    fecha_modificacion = " + (_fechaModificacion != null ? "STR_TO_DATE('" + _fechaModificacion + "', '%Y-%m-%d %H:%i:%s')" : "null") + "," +
            "    fecha = " + (_fecha != null ? "STR_TO_DATE('" + _fecha + "', '%Y-%m-%d %H:%i:%s')" : "null") + "," +
            "    borrado = " + (_borrado != null ? "b'" + (_borrado ? 1 : 0) + "'" : "null") + "," +
            "    descripcion = " + (_descripcion != null ? "'" + _descripcion + "'" : "null") + "," +
            "    recordar_km = " + (_recordarKm != null ? "b'" + (_recordarKm ? 1 : 0) + "'" : "null") + "," +
            "    km = " + (_km != null ? _km : "null") + "," +
            "    recordar_fecha = " + (_recordarFecha != null ? "b'" + (_recordarFecha ? 1 : 0) + "'" : "null") +
            "    WHERE" +
            "    id_recordatorio = " + Long.toString(this._idRecordatorio) + " AND" +
            "    id_usuario = " + Long.toString(this._idUsuario);

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
            "    INSERT INTO recordatorio" +
            "    (" +
            "    id_recordatorio, " +
            "    titulo, " +
            "    fecha, " +
            "    id_usuario, " +
            "    id_vehiculo, " +
            "    descripcion, " +
            "    recordar_km, " +
            "    km, " +
            "    recordar_fecha)" +
            "    VALUES" +
            "    (" +
            "    " + (_idRecordatorio != null ? "'" + _idRecordatorio + "'" : "null") + "," +
            "    " + (_titulo != null ? "'" + _titulo + "'" : "null") + "," +
            "    " + (_fecha != null ? "STR_TO_DATE('" + _fecha + "', '%Y-%m-%d %H:%i:%s')" : "null") + "," +
            "    " + (_idUsuario != null ? "'" + _idUsuario + "'" : "null") + "," +
            "    " + (_idVehiculo != null ? "'" + _idVehiculo + "'" : "null") + "," +
            "    " + (_descripcion != null ? "'" + _descripcion + "'" : "null") + "," +
            "    " + (_recordarKm != null ? "b'" + (_recordarKm ? 1 : 0) + "'" : "null") + "," +
            "    " + (_km != null ? "'" + _km + "'" : "null") + "," +
            "    " + (_recordarFecha != null ? "b'" + (_recordarFecha ? 1 : 0) + "'" : "null") +
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
            "    DELETE FROM recordatorio" +
            "    WHERE" +
            "    id_recordatorio = " + Long.toString(this._idRecordatorio) + " AND" +
            "    id_usuario = " + Long.toString(this._idUsuario);

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
        Recordatorio obj = null;
        
        String str_sql = _str_sql +
            "    WHERE" +
            "    id_recordatorio = " + Long.toString(this._idRecordatorio) + " AND" +
            "    id_usuario = " + Long.toString(this._idUsuario) +
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

                _titulo = obj.getTitulo();
                _fechaModificacion = obj.getFechaModificacion();
                _fecha = obj.getFecha();
                _idVehiculo = obj.getIdVehiculo();
                _borrado = obj.getBorrado();
                _descripcion = obj.getDescripcion();
                _recordarKm = obj.getRecordarKm();
                _km = obj.getKm();
                _recordarFecha = obj.getRecordarFecha();
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
            "    id_recordatorio = " + Long.toString(this._idRecordatorio) + " AND" +
            "    id_usuario = " + Long.toString(this._idUsuario) +
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
        return "Recordatorio [" +
	           "    _idRecordatorio = " + (_idRecordatorio != null ? _idRecordatorio : "null") + "," +
	           "    _titulo = " + (_titulo != null ? "'" + _titulo + "'" : "null") + "," +
	           "    _fecha_modificacion = " + (_fechaModificacion != null ? "STR_TO_DATE(" + _fechaModificacion + ", '%Y-%m-%d %H:%i:%s')" : "null") + "," +
	           "    _fecha = " + (_fecha != null ? "STR_TO_DATE(" + _fecha + ", '%Y-%m-%d %H:%i:%s')" : "null") + "," +
	           "    _idUsuario = " + (_idUsuario != null ? _idUsuario : "null") + "," +
	           "    _idVehiculo = " + (_idVehiculo != null ? _idVehiculo : "null") + "," +
	           "    _borrado = " + (_borrado != null ? "b'" + _borrado : "null") + "," +
	           "    _descripcion = " + (_descripcion != null ? "'" + _descripcion + "'" : "null") + "," +
	           "    _recordar_km = " + (_recordarKm != null ? "b'" + _recordarKm : "null") + "," +
	           "    _km = " + (_km != null ? _km : "null") + "," +
	           "    _recordar_fecha = " + (_recordarFecha != null ? "b'" + _recordarFecha : "null") +
			   "]";
    }


    public String toJSON() {
        return "Recordatorio : {" +
	           "    \"_idRecordatorio\" : " + (_idRecordatorio != null ? _idRecordatorio : "null") + "," +
	           "    \"_titulo\" : " + (_titulo != null ? "\"" + _titulo + "\"" : "null") + "," +
	           "    \"_fecha_modificacion\" : " + (_fechaModificacion != null ? "\"" + _fechaModificacion + "\"" : "null") + "," +
	           "    \"_fecha\" : " + (_fecha != null ? "\"" + _fecha + "\"" : "null") + "," +
	           "    \"_idUsuario\" : " + (_idUsuario != null ? _idUsuario : "null") + "," +
	           "    \"_idVehiculo\" : " + (_idVehiculo != null ? _idVehiculo : "null") + "," +
	           "    \"_borrado\" : " + (_borrado != null ? "b'" + _borrado : "null") + "," +
	           "    \"_descripcion\" : " + (_descripcion != null ? "\"" + _descripcion + "\"" : "null") + "," +
	           "    \"_recordar_km\" : " + (_recordarKm != null ? "b'" + _recordarKm : "null") + "," +
	           "    \"_km\" : " + (_km != null ? _km : "null") + "," +
	           "    \"_recordar_fecha\" : " + (_recordarFecha != null ? "b'" + _recordarFecha : "null") +
			   "}";
    }


    public String toXML() {
        return "<Recordatorio>" +
	           "    <idRecordatorio" + (_idRecordatorio != null ? ">" + _idRecordatorio + "</idRecordatorio>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <titulo" + (_titulo != null ? ">" + _titulo + "</titulo>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <fechaModificacion" + (_fechaModificacion != null ? ">" + _fechaModificacion + "</fechaModificacion>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <fecha" + (_fecha != null ? ">" + _fecha + "</fecha>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <idUsuario" + (_idUsuario != null ? ">" + _idUsuario + "</idUsuario>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <idVehiculo" + (_idVehiculo != null ? ">" + _idVehiculo + "</idVehiculo>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <borrado" + (_borrado != null ? ">" + _borrado + "</borrado>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <descripcion" + (_descripcion != null ? ">" + _descripcion + "</descripcion>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <recordarKm" + (_recordarKm != null ? ">" + _recordarKm + "</recordarKm>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <km" + (_km != null ? ">" + _km + "</km>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <recordarFecha" + (_recordarFecha != null ? ">" + _recordarFecha + "</recordarFecha>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
			   "</Recordatorio>";
    }


    public static Recordatorio fromXMLNode(Node xmlNode) {
        Recordatorio ret = new Recordatorio();

        Element element = (Element) xmlNode;

        ret.setIdRecordatorio(Long.decode(element.getElementsByTagName("id_recordatorio").item(0).getTextContent()));
        ret.setTitulo(element.getElementsByTagName("titulo").item(0).getTextContent());
        ret.setFechaModificacion(element.getElementsByTagName("fecha_modificacion").item(0).getTextContent());
        ret.setFecha(element.getElementsByTagName("fecha").item(0).getTextContent());
        ret.setIdUsuario(Long.decode(element.getElementsByTagName("id_usuario").item(0).getTextContent()));
        ret.setIdVehiculo(Long.decode(element.getElementsByTagName("id_vehiculo").item(0).getTextContent()));
        ret.setBorrado(Boolean.valueOf(element.getElementsByTagName("borrado").item(0).getTextContent()));
        ret.setDescripcion(element.getElementsByTagName("descripcion").item(0).getTextContent());
        ret.setRecordarKm(Boolean.valueOf(element.getElementsByTagName("recordar_km").item(0).getTextContent()));
        ret.setKm(Integer.decode(element.getElementsByTagName("km").item(0).getTextContent()));
        ret.setRecordarFecha(Boolean.valueOf(element.getElementsByTagName("recordar_fecha").item(0).getTextContent()));

        return ret;
    }
}
