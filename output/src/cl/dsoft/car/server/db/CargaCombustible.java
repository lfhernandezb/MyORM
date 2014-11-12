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
public class CargaCombustible {
    protected String _fechaModificacion;
    protected Boolean _estanqueLleno;
    protected String _fecha;
    protected Long _idUsuario;
    protected Long _idVehiculo;
    protected Boolean _borrado;
    protected Integer _latitud;
    protected Integer _costo;
    protected Integer _longitud;
    protected Long _idCargaCombustible;
    protected Integer _km;
    protected Integer _litros;

    private final static String _str_sql = 
        "    SELECT" +
        "    DATE_FORMAT(ca.fecha_modificacion, '%Y-%m-%d %H:%i:%s') AS fecha_modificacion," +
        "    0+ca.estanque_lleno AS estanque_lleno," +
        "    DATE_FORMAT(ca.fecha, '%Y-%m-%d %H:%i:%s') AS fecha," +
        "    ca.id_usuario AS id_usuario," +
        "    ca.id_vehiculo AS id_vehiculo," +
        "    0+ca.borrado AS borrado," +
        "    ca.latitud AS latitud," +
        "    ca.costo AS costo," +
        "    ca.longitud AS longitud," +
        "    ca.id_carga_combustible AS id_carga_combustible," +
        "    ca.km AS km," +
        "    ca.litros AS litros" +
        "    FROM carga_combustible ca";

    public CargaCombustible() {
        _fechaModificacion = null;
        _estanqueLleno = null;
        _fecha = null;
        _idUsuario = null;
        _idVehiculo = null;
        _borrado = null;
        _latitud = null;
        _costo = null;
        _longitud = null;
        _idCargaCombustible = null;
        _km = null;
        _litros = null;

    }
    /**
     * @return the _fechaModificacion
     */
    public String getFechaModificacion() {
        return _fechaModificacion;
    }
    /**
     * @return the _estanqueLleno
     */
    public Boolean getEstanqueLleno() {
        return _estanqueLleno;
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
     * @return the _latitud
     */
    public Integer getLatitud() {
        return _latitud;
    }
    /**
     * @return the _costo
     */
    public Integer getCosto() {
        return _costo;
    }
    /**
     * @return the _longitud
     */
    public Integer getLongitud() {
        return _longitud;
    }
    /**
     * @return the _idCargaCombustible
     */
    public Long getIdCargaCombustible() {
        return _idCargaCombustible;
    }
    /**
     * @return the _km
     */
    public Integer getKm() {
        return _km;
    }
    /**
     * @return the _litros
     */
    public Integer getLitros() {
        return _litros;
    }
    /**
     * @param _fechaModificacion the _fechaModificacion to set
     */
    public void setFechaModificacion(String _fechaModificacion) {
        this._fechaModificacion = _fechaModificacion;
    }
    /**
     * @param _estanqueLleno the _estanqueLleno to set
     */
    public void setEstanqueLleno(Boolean _estanqueLleno) {
        this._estanqueLleno = _estanqueLleno;
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
     * @param _latitud the _latitud to set
     */
    public void setLatitud(Integer _latitud) {
        this._latitud = _latitud;
    }
    /**
     * @param _costo the _costo to set
     */
    public void setCosto(Integer _costo) {
        this._costo = _costo;
    }
    /**
     * @param _longitud the _longitud to set
     */
    public void setLongitud(Integer _longitud) {
        this._longitud = _longitud;
    }
    /**
     * @param _idCargaCombustible the _idCargaCombustible to set
     */
    public void setIdCargaCombustible(Long _idCargaCombustible) {
        this._idCargaCombustible = _idCargaCombustible;
    }
    /**
     * @param _km the _km to set
     */
    public void setKm(Integer _km) {
        this._km = _km;
    }
    /**
     * @param _litros the _litros to set
     */
    public void setLitros(Integer _litros) {
        this._litros = _litros;
    }

    public static CargaCombustible fromRS(ResultSet p_rs) throws SQLException {
        CargaCombustible ret = new CargaCombustible();

        ret.setFechaModificacion(p_rs.getString("fecha_modificacion"));
        ret.setEstanqueLleno(p_rs.getBoolean("estanque_lleno"));
        ret.setFecha(p_rs.getString("fecha"));
        ret.setIdUsuario(p_rs.getLong("id_usuario"));
        ret.setIdVehiculo(p_rs.getLong("id_vehiculo"));
        ret.setBorrado(p_rs.getBoolean("borrado"));
        ret.setLatitud(p_rs.getInt("latitud"));
        ret.setCosto(p_rs.getInt("costo"));
        ret.setLongitud(p_rs.getInt("longitud"));
        ret.setIdCargaCombustible(p_rs.getLong("id_carga_combustible"));
        ret.setKm(p_rs.getInt("km"));
        ret.setLitros(p_rs.getInt("litros"));

        return ret;
    }

    public static CargaCombustible getByParameter(Connection p_conn, String p_key, String p_value) throws SQLException {
        CargaCombustible ret = null;
        
        String str_sql = _str_sql +
            "  WHERE ca." + p_key + " = " + p_value +
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

    
    public static ArrayList<CargaCombustible> seek(Connection p_conn, ArrayList<AbstractMap.SimpleEntry<String, String>> p_parameters, String p_order, String p_direction, int p_offset, int p_limit) throws UnsupportedParameterException, SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        String str_sql;
        ArrayList<CargaCombustible> ret;
        
        str_sql = "";
        
        try {
            ArrayList<String> array_clauses = new ArrayList<String>();
            
            ret = new ArrayList<CargaCombustible>();
            
            str_sql = _str_sql;
            
            for (AbstractMap.SimpleEntry<String, String> p : p_parameters) {
                if (p.getKey().equals("id_usuario")) {
                    array_clauses.add("ca.id_usuario = " + p.getValue());
                }
                else if (p.getKey().equals("id_carga_combustible")) {
                    array_clauses.add("ca.id_carga_combustible = " + p.getValue());
                }
                else if (p.getKey().equals("id_usuario")) {
                    array_clauses.add("ca.id_usuario = " + p.getValue());
                }
                else if (p.getKey().equals("id_vehiculo")) {
                    array_clauses.add("ca.id_vehiculo = " + p.getValue());
                }
                else if (p.getKey().equals("mas reciente")) {
                    array_clauses.add("ca.fecha_modificacion > STR_TO_DATE('" + p.getValue() + "', '%Y-%m-%d %H:%i:%s')");
                }
                else if (p.getKey().equals("no borrado")) {
                    array_clauses.add("ca.borrado = 0");
                }
                else if (p.getKey().equals("borrado")) {
                    array_clauses.add("ca.borrado = 1");
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
            "    UPDATE carga_combustible" +
            "    SET" +
            "    fecha_modificacion = " + (_fechaModificacion != null ? "STR_TO_DATE('" + _fechaModificacion + "', '%Y-%m-%d %H:%i:%s')" : "null") + "," +
            "    estanque_lleno = " + (_estanqueLleno != null ? "b'" + (_estanqueLleno ? 1 : 0) + "'" : "null") + "," +
            "    fecha = " + (_fecha != null ? "STR_TO_DATE('" + _fecha + "', '%Y-%m-%d %H:%i:%s')" : "null") + "," +
            "    borrado = " + (_borrado != null ? "b'" + (_borrado ? 1 : 0) + "'" : "null") + "," +
            "    latitud = " + (_latitud != null ? _latitud : "null") + "," +
            "    costo = " + (_costo != null ? _costo : "null") + "," +
            "    longitud = " + (_longitud != null ? _longitud : "null") + "," +
            "    km = " + (_km != null ? _km : "null") + "," +
            "    litros = " + (_litros != null ? _litros : "null") +
            "    WHERE" +
            "    id_usuario = " + Long.toString(this._idUsuario) + " AND" +
            "    id_carga_combustible = " + Long.toString(this._idCargaCombustible);

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
            "    INSERT INTO carga_combustible" +
            "    (" +
            "    estanque_lleno, " +
            "    fecha, " +
            "    id_usuario, " +
            "    id_vehiculo, " +
            "    latitud, " +
            "    costo, " +
            "    longitud, " +
            "    id_carga_combustible, " +
            "    km, " +
            "    litros)" +
            "    VALUES" +
            "    (" +
            "    " + (_estanqueLleno != null ? "b'" + (_estanqueLleno ? 1 : 0) + "'" : "null") + "," +
            "    " + (_fecha != null ? "STR_TO_DATE('" + _fecha + "', '%Y-%m-%d %H:%i:%s')" : "null") + "," +
            "    " + (_idUsuario != null ? "'" + _idUsuario + "'" : "null") + "," +
            "    " + (_idVehiculo != null ? "'" + _idVehiculo + "'" : "null") + "," +
            "    " + (_latitud != null ? "'" + _latitud + "'" : "null") + "," +
            "    " + (_costo != null ? "'" + _costo + "'" : "null") + "," +
            "    " + (_longitud != null ? "'" + _longitud + "'" : "null") + "," +
            "    " + (_idCargaCombustible != null ? "'" + _idCargaCombustible + "'" : "null") + "," +
            "    " + (_km != null ? "'" + _km + "'" : "null") + "," +
            "    " + (_litros != null ? "'" + _litros + "'" : "null") +
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
            "    DELETE FROM carga_combustible" +
            "    WHERE" +
            "    id_usuario = " + Long.toString(this._idUsuario) + " AND" +
            "    id_carga_combustible = " + Long.toString(this._idCargaCombustible);

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
        CargaCombustible obj = null;
        
        String str_sql = _str_sql +
            "    WHERE" +
            "    id_usuario = " + Long.toString(this._idUsuario) + " AND" +
            "    id_carga_combustible = " + Long.toString(this._idCargaCombustible) +
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

                _fechaModificacion = obj.getFechaModificacion();
                _estanqueLleno = obj.getEstanqueLleno();
                _fecha = obj.getFecha();
                _idVehiculo = obj.getIdVehiculo();
                _borrado = obj.getBorrado();
                _latitud = obj.getLatitud();
                _costo = obj.getCosto();
                _longitud = obj.getLongitud();
                _km = obj.getKm();
                _litros = obj.getLitros();
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
            "    id_usuario = " + Long.toString(this._idUsuario) + " AND" +
            "    id_carga_combustible = " + Long.toString(this._idCargaCombustible) +
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
        return "CargaCombustible [" +
	           "    _fecha_modificacion = " + (_fechaModificacion != null ? "STR_TO_DATE(" + _fechaModificacion + ", '%Y-%m-%d %H:%i:%s')" : "null") + "," +
	           "    _estanque_lleno = " + (_estanqueLleno != null ? "b'" + _estanqueLleno : "null") + "," +
	           "    _fecha = " + (_fecha != null ? "STR_TO_DATE(" + _fecha + ", '%Y-%m-%d %H:%i:%s')" : "null") + "," +
	           "    _idUsuario = " + (_idUsuario != null ? _idUsuario : "null") + "," +
	           "    _idVehiculo = " + (_idVehiculo != null ? _idVehiculo : "null") + "," +
	           "    _borrado = " + (_borrado != null ? "b'" + _borrado : "null") + "," +
	           "    _latitud = " + (_latitud != null ? _latitud : "null") + "," +
	           "    _costo = " + (_costo != null ? _costo : "null") + "," +
	           "    _longitud = " + (_longitud != null ? _longitud : "null") + "," +
	           "    _idCargaCombustible = " + (_idCargaCombustible != null ? _idCargaCombustible : "null") + "," +
	           "    _km = " + (_km != null ? _km : "null") + "," +
	           "    _litros = " + (_litros != null ? _litros : "null") +
			   "]";
    }


    public String toJSON() {
        return "CargaCombustible : {" +
	           "    \"_fecha_modificacion\" : " + (_fechaModificacion != null ? "\"" + _fechaModificacion + "\"" : "null") + "," +
	           "    \"_estanque_lleno\" : " + (_estanqueLleno != null ? "b'" + _estanqueLleno : "null") + "," +
	           "    \"_fecha\" : " + (_fecha != null ? "\"" + _fecha + "\"" : "null") + "," +
	           "    \"_idUsuario\" : " + (_idUsuario != null ? _idUsuario : "null") + "," +
	           "    \"_idVehiculo\" : " + (_idVehiculo != null ? _idVehiculo : "null") + "," +
	           "    \"_borrado\" : " + (_borrado != null ? "b'" + _borrado : "null") + "," +
	           "    \"_latitud\" : " + (_latitud != null ? _latitud : "null") + "," +
	           "    \"_costo\" : " + (_costo != null ? _costo : "null") + "," +
	           "    \"_longitud\" : " + (_longitud != null ? _longitud : "null") + "," +
	           "    \"_idCargaCombustible\" : " + (_idCargaCombustible != null ? _idCargaCombustible : "null") + "," +
	           "    \"_km\" : " + (_km != null ? _km : "null") + "," +
	           "    \"_litros\" : " + (_litros != null ? _litros : "null") +
			   "}";
    }


    public String toXML() {
        return "<CargaCombustible>" +
	           "    <fechaModificacion" + (_fechaModificacion != null ? ">" + _fechaModificacion + "</fechaModificacion>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <estanqueLleno" + (_estanqueLleno != null ? ">" + _estanqueLleno + "</estanqueLleno>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <fecha" + (_fecha != null ? ">" + _fecha + "</fecha>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <idUsuario" + (_idUsuario != null ? ">" + _idUsuario + "</idUsuario>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <idVehiculo" + (_idVehiculo != null ? ">" + _idVehiculo + "</idVehiculo>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <borrado" + (_borrado != null ? ">" + _borrado + "</borrado>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <latitud" + (_latitud != null ? ">" + _latitud + "</latitud>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <costo" + (_costo != null ? ">" + _costo + "</costo>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <longitud" + (_longitud != null ? ">" + _longitud + "</longitud>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <idCargaCombustible" + (_idCargaCombustible != null ? ">" + _idCargaCombustible + "</idCargaCombustible>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <km" + (_km != null ? ">" + _km + "</km>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <litros" + (_litros != null ? ">" + _litros + "</litros>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
			   "</CargaCombustible>";
    }


    public static CargaCombustible fromXMLNode(Node xmlNode) {
        CargaCombustible ret = new CargaCombustible();

        Element element = (Element) xmlNode;

        ret.setFechaModificacion(element.getElementsByTagName("fecha_modificacion").item(0).getTextContent());
        ret.setEstanqueLleno(Boolean.valueOf(element.getElementsByTagName("estanque_lleno").item(0).getTextContent()));
        ret.setFecha(element.getElementsByTagName("fecha").item(0).getTextContent());
        ret.setIdUsuario(Long.decode(element.getElementsByTagName("id_usuario").item(0).getTextContent()));
        ret.setIdVehiculo(Long.decode(element.getElementsByTagName("id_vehiculo").item(0).getTextContent()));
        ret.setBorrado(Boolean.valueOf(element.getElementsByTagName("borrado").item(0).getTextContent()));
        ret.setLatitud(Integer.decode(element.getElementsByTagName("latitud").item(0).getTextContent()));
        ret.setCosto(Integer.decode(element.getElementsByTagName("costo").item(0).getTextContent()));
        ret.setLongitud(Integer.decode(element.getElementsByTagName("longitud").item(0).getTextContent()));
        ret.setIdCargaCombustible(Long.decode(element.getElementsByTagName("id_carga_combustible").item(0).getTextContent()));
        ret.setKm(Integer.decode(element.getElementsByTagName("km").item(0).getTextContent()));
        ret.setLitros(Integer.decode(element.getElementsByTagName("litros").item(0).getTextContent()));

        return ret;
    }
}
