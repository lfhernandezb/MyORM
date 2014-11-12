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
public class Log {
    protected Long _idTipoVehiculo;
    protected String _fecha;
    protected Long _vehiculoIdUsuario;
    protected Long _idVehiculo;
    protected Double _latitud;
    protected Long _idLog;
    protected Long _idModelo;
    protected Double _longitud;
    protected String _accion;
    protected Integer _km;
    protected Long _idMarca;
    protected Long _vehiculoIdVehiculo;

    private final static String _str_sql = 
        "    SELECT" +
        "    lo.id_tipo_vehiculo AS id_tipo_vehiculo," +
        "    DATE_FORMAT(lo.fecha, '%Y-%m-%d %H:%i:%s') AS fecha," +
        "    lo.vehiculo_id_usuario AS vehiculo_id_usuario," +
        "    lo.id_vehiculo AS id_vehiculo," +
        "    lo.latitud AS latitud," +
        "    lo.id_log AS id_log," +
        "    lo.id_modelo AS id_modelo," +
        "    lo.longitud AS longitud," +
        "    lo.accion AS accion," +
        "    lo.km AS km," +
        "    lo.id_marca AS id_marca," +
        "    lo.vehiculo_id_vehiculo AS vehiculo_id_vehiculo" +
        "    FROM log lo";

    public Log() {
        _idTipoVehiculo = null;
        _fecha = null;
        _vehiculoIdUsuario = null;
        _idVehiculo = null;
        _latitud = null;
        _idLog = null;
        _idModelo = null;
        _longitud = null;
        _accion = null;
        _km = null;
        _idMarca = null;
        _vehiculoIdVehiculo = null;

    }
    /**
     * @return the _idTipoVehiculo
     */
    public Long getIdTipoVehiculo() {
        return _idTipoVehiculo;
    }
    /**
     * @return the _fecha
     */
    public String getFecha() {
        return _fecha;
    }
    /**
     * @return the _vehiculoIdUsuario
     */
    public Long getVehiculoIdUsuario() {
        return _vehiculoIdUsuario;
    }
    /**
     * @return the _idVehiculo
     */
    public Long getIdVehiculo() {
        return _idVehiculo;
    }
    /**
     * @return the _latitud
     */
    public Double getLatitud() {
        return _latitud;
    }
    /**
     * @return the _idLog
     */
    public Long getIdLog() {
        return _idLog;
    }
    /**
     * @return the _idModelo
     */
    public Long getIdModelo() {
        return _idModelo;
    }
    /**
     * @return the _longitud
     */
    public Double getLongitud() {
        return _longitud;
    }
    /**
     * @return the _accion
     */
    public String getAccion() {
        return _accion;
    }
    /**
     * @return the _km
     */
    public Integer getKm() {
        return _km;
    }
    /**
     * @return the _idMarca
     */
    public Long getIdMarca() {
        return _idMarca;
    }
    /**
     * @return the _vehiculoIdVehiculo
     */
    public Long getVehiculoIdVehiculo() {
        return _vehiculoIdVehiculo;
    }
    /**
     * @param _idTipoVehiculo the _idTipoVehiculo to set
     */
    public void setIdTipoVehiculo(Long _idTipoVehiculo) {
        this._idTipoVehiculo = _idTipoVehiculo;
    }
    /**
     * @param _fecha the _fecha to set
     */
    public void setFecha(String _fecha) {
        this._fecha = _fecha;
    }
    /**
     * @param _vehiculoIdUsuario the _vehiculoIdUsuario to set
     */
    public void setVehiculoIdUsuario(Long _vehiculoIdUsuario) {
        this._vehiculoIdUsuario = _vehiculoIdUsuario;
    }
    /**
     * @param _idVehiculo the _idVehiculo to set
     */
    public void setIdVehiculo(Long _idVehiculo) {
        this._idVehiculo = _idVehiculo;
    }
    /**
     * @param _latitud the _latitud to set
     */
    public void setLatitud(Double _latitud) {
        this._latitud = _latitud;
    }
    /**
     * @param _idLog the _idLog to set
     */
    public void setIdLog(Long _idLog) {
        this._idLog = _idLog;
    }
    /**
     * @param _idModelo the _idModelo to set
     */
    public void setIdModelo(Long _idModelo) {
        this._idModelo = _idModelo;
    }
    /**
     * @param _longitud the _longitud to set
     */
    public void setLongitud(Double _longitud) {
        this._longitud = _longitud;
    }
    /**
     * @param _accion the _accion to set
     */
    public void setAccion(String _accion) {
        this._accion = _accion;
    }
    /**
     * @param _km the _km to set
     */
    public void setKm(Integer _km) {
        this._km = _km;
    }
    /**
     * @param _idMarca the _idMarca to set
     */
    public void setIdMarca(Long _idMarca) {
        this._idMarca = _idMarca;
    }
    /**
     * @param _vehiculoIdVehiculo the _vehiculoIdVehiculo to set
     */
    public void setVehiculoIdVehiculo(Long _vehiculoIdVehiculo) {
        this._vehiculoIdVehiculo = _vehiculoIdVehiculo;
    }

    public static Log fromRS(ResultSet p_rs) throws SQLException {
        Log ret = new Log();

        ret.setIdTipoVehiculo(p_rs.getLong("id_tipo_vehiculo"));
        ret.setFecha(p_rs.getString("fecha"));
        ret.setVehiculoIdUsuario(p_rs.getLong("vehiculo_id_usuario"));
        ret.setIdVehiculo(p_rs.getLong("id_vehiculo"));
        ret.setLatitud(p_rs.getDouble("latitud"));
        ret.setIdLog(p_rs.getLong("id_log"));
        ret.setIdModelo(p_rs.getLong("id_modelo"));
        ret.setLongitud(p_rs.getDouble("longitud"));
        ret.setAccion(p_rs.getString("accion"));
        ret.setKm(p_rs.getInt("km"));
        ret.setIdMarca(p_rs.getLong("id_marca"));
        ret.setVehiculoIdVehiculo(p_rs.getLong("vehiculo_id_vehiculo"));

        return ret;
    }

    public static Log getByParameter(Connection p_conn, String p_key, String p_value) throws SQLException {
        Log ret = null;
        
        String str_sql = _str_sql +
            "  WHERE lo." + p_key + " = " + p_value +
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

    
    public static ArrayList<Log> seek(Connection p_conn, ArrayList<AbstractMap.SimpleEntry<String, String>> p_parameters, String p_order, String p_direction, int p_offset, int p_limit) throws UnsupportedParameterException, SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        String str_sql;
        ArrayList<Log> ret;
        
        str_sql = "";
        
        try {
            ArrayList<String> array_clauses = new ArrayList<String>();
            
            ret = new ArrayList<Log>();
            
            str_sql = _str_sql;
            
            for (AbstractMap.SimpleEntry<String, String> p : p_parameters) {
                if (p.getKey().equals("vehiculo_id_usuario")) {
                    array_clauses.add("lo.vehiculo_id_usuario = " + p.getValue());
                }
                else if (p.getKey().equals("id_log")) {
                    array_clauses.add("lo.id_log = " + p.getValue());
                }
                else if (p.getKey().equals("vehiculo_id_usuario")) {
                    array_clauses.add("lo.vehiculo_id_usuario = " + p.getValue());
                }
                else if (p.getKey().equals("vehiculo_id_vehiculo")) {
                    array_clauses.add("lo.vehiculo_id_vehiculo = " + p.getValue());
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
            "    UPDATE log" +
            "    SET" +
            "    id_tipo_vehiculo = " + (_idTipoVehiculo != null ? _idTipoVehiculo : "null") + "," +
            "    fecha = " + (_fecha != null ? "STR_TO_DATE('" + _fecha + "', '%Y-%m-%d %H:%i:%s')" : "null") + "," +
            "    id_vehiculo = " + (_idVehiculo != null ? _idVehiculo : "null") + "," +
            "    latitud = " + (_latitud != null ? _latitud : "null") + "," +
            "    id_modelo = " + (_idModelo != null ? _idModelo : "null") + "," +
            "    longitud = " + (_longitud != null ? _longitud : "null") + "," +
            "    accion = " + (_accion != null ? "'" + _accion + "'" : "null") + "," +
            "    km = " + (_km != null ? _km : "null") + "," +
            "    id_marca = " + (_idMarca != null ? _idMarca : "null") +
            "    WHERE" +
            "    vehiculo_id_usuario = " + Long.toString(this._vehiculoIdUsuario) + " AND" +
            "    id_log = " + Long.toString(this._idLog);

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
            "    INSERT INTO log" +
            "    (" +
            "    id_tipo_vehiculo, " +
            "    vehiculo_id_usuario, " +
            "    id_vehiculo, " +
            "    latitud, " +
            "    id_log, " +
            "    id_modelo, " +
            "    longitud, " +
            "    accion, " +
            "    km, " +
            "    id_marca, " +
            "    vehiculo_id_vehiculo)" +
            "    VALUES" +
            "    (" +
            "    " + (_idTipoVehiculo != null ? "'" + _idTipoVehiculo + "'" : "null") + "," +
            "    " + (_vehiculoIdUsuario != null ? "'" + _vehiculoIdUsuario + "'" : "null") + "," +
            "    " + (_idVehiculo != null ? "'" + _idVehiculo + "'" : "null") + "," +
            "    " + (_latitud != null ? "'" + _latitud + "'" : "null") + "," +
            "    " + (_idLog != null ? "'" + _idLog + "'" : "null") + "," +
            "    " + (_idModelo != null ? "'" + _idModelo + "'" : "null") + "," +
            "    " + (_longitud != null ? "'" + _longitud + "'" : "null") + "," +
            "    " + (_accion != null ? "'" + _accion + "'" : "null") + "," +
            "    " + (_km != null ? "'" + _km + "'" : "null") + "," +
            "    " + (_idMarca != null ? "'" + _idMarca + "'" : "null") + "," +
            "    " + (_vehiculoIdVehiculo != null ? "'" + _vehiculoIdVehiculo + "'" : "null") +
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
            "    DELETE FROM log" +
            "    WHERE" +
            "    vehiculo_id_usuario = " + Long.toString(this._vehiculoIdUsuario) + " AND" +
            "    id_log = " + Long.toString(this._idLog);

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
        Log obj = null;
        
        String str_sql = _str_sql +
            "    WHERE" +
            "    vehiculo_id_usuario = " + Long.toString(this._vehiculoIdUsuario) + " AND" +
            "    id_log = " + Long.toString(this._idLog) +
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

                _idTipoVehiculo = obj.getIdTipoVehiculo();
                _fecha = obj.getFecha();
                _idVehiculo = obj.getIdVehiculo();
                _latitud = obj.getLatitud();
                _idModelo = obj.getIdModelo();
                _longitud = obj.getLongitud();
                _accion = obj.getAccion();
                _km = obj.getKm();
                _idMarca = obj.getIdMarca();
                _vehiculoIdVehiculo = obj.getVehiculoIdVehiculo();
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
            "    vehiculo_id_usuario = " + Long.toString(this._vehiculoIdUsuario) + " AND" +
            "    id_log = " + Long.toString(this._idLog) +
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
        return "Log [" +
	           "    _idTipoVehiculo = " + (_idTipoVehiculo != null ? _idTipoVehiculo : "null") + "," +
	           "    _fecha = " + (_fecha != null ? "STR_TO_DATE(" + _fecha + ", '%Y-%m-%d %H:%i:%s')" : "null") + "," +
	           "    _vehiculoIdUsuario = " + (_vehiculoIdUsuario != null ? _vehiculoIdUsuario : "null") + "," +
	           "    _idVehiculo = " + (_idVehiculo != null ? _idVehiculo : "null") + "," +
	           "    _latitud = " + (_latitud != null ? _latitud : "null") + "," +
	           "    _idLog = " + (_idLog != null ? _idLog : "null") + "," +
	           "    _idModelo = " + (_idModelo != null ? _idModelo : "null") + "," +
	           "    _longitud = " + (_longitud != null ? _longitud : "null") + "," +
	           "    _accion = " + (_accion != null ? "'" + _accion + "'" : "null") + "," +
	           "    _km = " + (_km != null ? _km : "null") + "," +
	           "    _idMarca = " + (_idMarca != null ? _idMarca : "null") + "," +
	           "    _vehiculoIdVehiculo = " + (_vehiculoIdVehiculo != null ? _vehiculoIdVehiculo : "null") +
			   "]";
    }


    public String toJSON() {
        return "Log : {" +
	           "    \"_idTipoVehiculo\" : " + (_idTipoVehiculo != null ? _idTipoVehiculo : "null") + "," +
	           "    \"_fecha\" : " + (_fecha != null ? "\"" + _fecha + "\"" : "null") + "," +
	           "    \"_vehiculoIdUsuario\" : " + (_vehiculoIdUsuario != null ? _vehiculoIdUsuario : "null") + "," +
	           "    \"_idVehiculo\" : " + (_idVehiculo != null ? _idVehiculo : "null") + "," +
	           "    \"_latitud\" : " + (_latitud != null ? _latitud : "null") + "," +
	           "    \"_idLog\" : " + (_idLog != null ? _idLog : "null") + "," +
	           "    \"_idModelo\" : " + (_idModelo != null ? _idModelo : "null") + "," +
	           "    \"_longitud\" : " + (_longitud != null ? _longitud : "null") + "," +
	           "    \"_accion\" : " + (_accion != null ? "\"" + _accion + "\"" : "null") + "," +
	           "    \"_km\" : " + (_km != null ? _km : "null") + "," +
	           "    \"_idMarca\" : " + (_idMarca != null ? _idMarca : "null") + "," +
	           "    \"_vehiculoIdVehiculo\" : " + (_vehiculoIdVehiculo != null ? _vehiculoIdVehiculo : "null") +
			   "}";
    }


    public String toXML() {
        return "<Log>" +
	           "    <idTipoVehiculo" + (_idTipoVehiculo != null ? ">" + _idTipoVehiculo + "</idTipoVehiculo>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <fecha" + (_fecha != null ? ">" + _fecha + "</fecha>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <vehiculoIdUsuario" + (_vehiculoIdUsuario != null ? ">" + _vehiculoIdUsuario + "</vehiculoIdUsuario>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <idVehiculo" + (_idVehiculo != null ? ">" + _idVehiculo + "</idVehiculo>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <latitud" + (_latitud != null ? ">" + _latitud + "</latitud>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <idLog" + (_idLog != null ? ">" + _idLog + "</idLog>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <idModelo" + (_idModelo != null ? ">" + _idModelo + "</idModelo>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <longitud" + (_longitud != null ? ">" + _longitud + "</longitud>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <accion" + (_accion != null ? ">" + _accion + "</accion>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <km" + (_km != null ? ">" + _km + "</km>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <idMarca" + (_idMarca != null ? ">" + _idMarca + "</idMarca>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <vehiculoIdVehiculo" + (_vehiculoIdVehiculo != null ? ">" + _vehiculoIdVehiculo + "</vehiculoIdVehiculo>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
			   "</Log>";
    }


    public static Log fromXMLNode(Node xmlNode) {
        Log ret = new Log();

        Element element = (Element) xmlNode;

        ret.setIdTipoVehiculo(Long.decode(element.getElementsByTagName("id_tipo_vehiculo").item(0).getTextContent()));
        ret.setFecha(element.getElementsByTagName("fecha").item(0).getTextContent());
        ret.setVehiculoIdUsuario(Long.decode(element.getElementsByTagName("vehiculo_id_usuario").item(0).getTextContent()));
        ret.setIdVehiculo(Long.decode(element.getElementsByTagName("id_vehiculo").item(0).getTextContent()));
        ret.setLatitud(Double.valueOf(element.getElementsByTagName("latitud").item(0).getTextContent()));
        ret.setIdLog(Long.decode(element.getElementsByTagName("id_log").item(0).getTextContent()));
        ret.setIdModelo(Long.decode(element.getElementsByTagName("id_modelo").item(0).getTextContent()));
        ret.setLongitud(Double.valueOf(element.getElementsByTagName("longitud").item(0).getTextContent()));
        ret.setAccion(element.getElementsByTagName("accion").item(0).getTextContent());
        ret.setKm(Integer.decode(element.getElementsByTagName("km").item(0).getTextContent()));
        ret.setIdMarca(Long.decode(element.getElementsByTagName("id_marca").item(0).getTextContent()));
        ret.setVehiculoIdVehiculo(Long.decode(element.getElementsByTagName("vehiculo_id_vehiculo").item(0).getTextContent()));

        return ret;
    }
}
