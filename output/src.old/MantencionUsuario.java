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
public class MantencionUsuario {
    protected String _nombre;
    protected String _fechaModificacion;
    protected Long _idUsuario;
    protected Long _idVehiculo;
    protected Boolean _borrado;
    protected Integer _kmentremantenciones;
    protected String _descripcion;
    protected Long _idMantencionUsuario;
    protected Integer _diasentremantenciones;
    protected String _url;
    protected Boolean _dependekm;
    protected String _beneficios;

    private final static String _str_sql = 
        "    SELECT" +
        "    ma.nombre AS nombre," +
        "    DATE_FORMAT(ma.fecha_modificacion, '%Y-%m-%d %H:%i:%s') AS fecha_modificacion," +
        "    ma.id_usuario AS id_usuario," +
        "    ma.id_vehiculo AS id_vehiculo," +
        "    0+ma.borrado AS borrado," +
        "    ma.KmEntreMantenciones AS KmEntreMantenciones," +
        "    ma.descripcion AS descripcion," +
        "    ma.id_mantencion_usuario AS id_mantencion_usuario," +
        "    ma.DiasEntreMantenciones AS DiasEntreMantenciones," +
        "    ma.URL AS URL," +
        "    0+ma.DependeKm AS DependeKm," +
        "    ma.beneficios AS beneficios" +
        "    FROM mantencion_usuario ma";

    public MantencionUsuario() {
        _nombre = null;
        _fechaModificacion = null;
        _idUsuario = null;
        _idVehiculo = null;
        _borrado = null;
        _kmentremantenciones = null;
        _descripcion = null;
        _idMantencionUsuario = null;
        _diasentremantenciones = null;
        _url = null;
        _dependekm = null;
        _beneficios = null;

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
     * @return the _kmentremantenciones
     */
    public Integer getKmentremantenciones() {
        return _kmentremantenciones;
    }
    /**
     * @return the _descripcion
     */
    public String getDescripcion() {
        return _descripcion;
    }
    /**
     * @return the _idMantencionUsuario
     */
    public Long getIdMantencionUsuario() {
        return _idMantencionUsuario;
    }
    /**
     * @return the _diasentremantenciones
     */
    public Integer getDiasentremantenciones() {
        return _diasentremantenciones;
    }
    /**
     * @return the _url
     */
    public String getUrl() {
        return _url;
    }
    /**
     * @return the _dependekm
     */
    public Boolean getDependekm() {
        return _dependekm;
    }
    /**
     * @return the _beneficios
     */
    public String getBeneficios() {
        return _beneficios;
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
     * @param _kmentremantenciones the _kmentremantenciones to set
     */
    public void setKmentremantenciones(Integer _kmentremantenciones) {
        this._kmentremantenciones = _kmentremantenciones;
    }
    /**
     * @param _descripcion the _descripcion to set
     */
    public void setDescripcion(String _descripcion) {
        this._descripcion = _descripcion;
    }
    /**
     * @param _idMantencionUsuario the _idMantencionUsuario to set
     */
    public void setIdMantencionUsuario(Long _idMantencionUsuario) {
        this._idMantencionUsuario = _idMantencionUsuario;
    }
    /**
     * @param _diasentremantenciones the _diasentremantenciones to set
     */
    public void setDiasentremantenciones(Integer _diasentremantenciones) {
        this._diasentremantenciones = _diasentremantenciones;
    }
    /**
     * @param _url the _url to set
     */
    public void setUrl(String _url) {
        this._url = _url;
    }
    /**
     * @param _dependekm the _dependekm to set
     */
    public void setDependekm(Boolean _dependekm) {
        this._dependekm = _dependekm;
    }
    /**
     * @param _beneficios the _beneficios to set
     */
    public void setBeneficios(String _beneficios) {
        this._beneficios = _beneficios;
    }

    public static MantencionUsuario fromRS(ResultSet p_rs) throws SQLException {
        MantencionUsuario ret = new MantencionUsuario();

        ret.setNombre(p_rs.getString("nombre"));
        ret.setFechaModificacion(p_rs.getString("fecha_modificacion"));
        ret.setIdUsuario(p_rs.getLong("id_usuario"));
        ret.setIdVehiculo(p_rs.getLong("id_vehiculo"));
        ret.setBorrado(p_rs.getBoolean("borrado"));
        ret.setKmentremantenciones(p_rs.getInt("KmEntreMantenciones"));
        ret.setDescripcion(p_rs.getString("descripcion"));
        ret.setIdMantencionUsuario(p_rs.getLong("id_mantencion_usuario"));
        ret.setDiasentremantenciones(p_rs.getInt("DiasEntreMantenciones"));
        ret.setUrl(p_rs.getString("URL"));
        ret.setDependekm(p_rs.getBoolean("DependeKm"));
        ret.setBeneficios(p_rs.getString("beneficios"));

        return ret;
    }

    public static MantencionUsuario getByParameter(Connection p_conn, String p_key, String p_value) throws SQLException {
        MantencionUsuario ret = null;
        
        String str_sql = _str_sql +
            "  WHERE ma." + p_key + " = " + p_value +
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

    
    public static ArrayList<MantencionUsuario> seek(Connection p_conn, ArrayList<AbstractMap.SimpleEntry<String, String>> p_parameters, String p_order, String p_direction, int p_offset, int p_limit) throws Exception {
        Statement stmt = null;
        ResultSet rs = null;
        String str_sql;
        ArrayList<MantencionUsuario> ret;
        
        str_sql = "";
        
        try {
            ArrayList<String> array_clauses = new ArrayList<String>();
            
            ret = new ArrayList<MantencionUsuario>();
            
            str_sql = _str_sql;
            
            for (AbstractMap.SimpleEntry<String, String> p : p_parameters) {
                if (p.getKey().equals("id_usuario")) {
                    array_clauses.add("ma.id_usuario = " + p.getValue());
                }
                else if (p.getKey().equals("id_mantencion_usuario")) {
                    array_clauses.add("ma.id_mantencion_usuario = " + p.getValue());
                }
                else if (p.getKey().equals("id_usuario")) {
                    array_clauses.add("ma.id_usuario = " + p.getValue());
                }
                else if (p.getKey().equals("id_vehiculo")) {
                    array_clauses.add("ma.id_vehiculo = " + p.getValue());
                }
                else if (p.getKey().equals("mas reciente")) {
                    array_clauses.add("ma.fecha_modificacion > STR_TO_DATE(" + p.getValue() + ", '%Y-%m-%d %H:%i:%s')");
                }
                else if (p.getKey().equals("no borrado")) {
                    array_clauses.add("ma.borrado = 0");
                }
                else if (p.getKey().equals("borrado")) {
                    array_clauses.add("ma.borrado = 1");
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
            "    UPDATE mantencion_usuario" +
            "    SET" +
            "    nombre = " + (_nombre != null ? "'" + _nombre + "'" : "null") + "," +
            "    fecha_modificacion = " + (_fechaModificacion != null ? "STR_TO_DATE(" + _fechaModificacion + ", '%Y-%m-%d %H:%i:%s')" : "null") + "," +
            "    borrado = " + (_borrado != null ? "b'" + _borrado : "null") + "," +
            "    KmEntreMantenciones = " + (_kmentremantenciones != null ? _kmentremantenciones : "null") + "," +
            "    descripcion = " + (_descripcion != null ? "'" + _descripcion + "'" : "null") + "," +
            "    DiasEntreMantenciones = " + (_diasentremantenciones != null ? _diasentremantenciones : "null") + "," +
            "    URL = " + (_url != null ? "'" + _url + "'" : "null") + "," +
            "    DependeKm = " + (_dependekm != null ? "b'" + _dependekm : "null") + "," +
            "    beneficios = " + (_beneficios != null ? "'" + _beneficios + "'" : "null") +
            "    WHERE" +
            "    id_usuario = " + Long.toString(this._idUsuario) + " AND" +
            "    id_mantencion_usuario = " + Long.toString(this._idMantencionUsuario);

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
            "    INSERT INTO mantencion_usuario" +
            "    (" +
            "    nombre, " +
            "    id_usuario, " +
            "    id_vehiculo, " +
            "    KmEntreMantenciones, " +
            "    descripcion, " +
            "    id_mantencion_usuario, " +
            "    DiasEntreMantenciones, " +
            "    URL, " +
            "    DependeKm, " +
            "    beneficios)" +
            "    VALUES" +
            "    (" +
            "    " + (_nombre != null ? "'" + _nombre + "'" : "null") + "," +
            "    " + (_idUsuario != null ? "'" + _idUsuario + "'" : "null") + "," +
            "    " + (_idVehiculo != null ? "'" + _idVehiculo + "'" : "null") + "," +
            "    " + (_kmentremantenciones != null ? "'" + _kmentremantenciones + "'" : "null") + "," +
            "    " + (_descripcion != null ? "'" + _descripcion + "'" : "null") + "," +
            "    " + (_idMantencionUsuario != null ? "'" + _idMantencionUsuario + "'" : "null") + "," +
            "    " + (_diasentremantenciones != null ? "'" + _diasentremantenciones + "'" : "null") + "," +
            "    " + (_url != null ? "'" + _url + "'" : "null") + "," +
            "    " + (_dependekm != null ? "b'" + (_dependekm ? 1 : 0) + "'" : "null") + "," +
            "    " + (_beneficios != null ? "'" + _beneficios + "'" : "null") +
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
            "    DELETE FROM mantencion_usuario" +
            "    WHERE" +
            "    id_usuario = " + Long.toString(this._idUsuario) + " AND" +
            "    id_mantencion_usuario = " + Long.toString(this._idMantencionUsuario);

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
        MantencionUsuario obj = null;
        
        String str_sql = _str_sql +
            "    WHERE" +
            "    id_usuario = " + Long.toString(this._idUsuario) + " AND" +
            "    id_mantencion_usuario = " + Long.toString(this._idMantencionUsuario) +
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
                _idVehiculo = obj.getIdVehiculo();
                _borrado = obj.getBorrado();
                _kmentremantenciones = obj.getKmentremantenciones();
                _descripcion = obj.getDescripcion();
                _diasentremantenciones = obj.getDiasentremantenciones();
                _url = obj.getUrl();
                _dependekm = obj.getDependekm();
                _beneficios = obj.getBeneficios();
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
            "    id_mantencion_usuario = " + Long.toString(this._idMantencionUsuario) +
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
        return "MantencionUsuario [" +
	           "    _nombre = " + (_nombre != null ? "'" + _nombre + "'" : "null") + "," +
	           "    _fecha_modificacion = " + (_fechaModificacion != null ? "STR_TO_DATE(" + _fechaModificacion + ", '%Y-%m-%d %H:%i:%s')" : "null") + "," +
	           "    _idUsuario = " + (_idUsuario != null ? _idUsuario : "null") + "," +
	           "    _idVehiculo = " + (_idVehiculo != null ? _idVehiculo : "null") + "," +
	           "    _borrado = " + (_borrado != null ? "b'" + _borrado : "null") + "," +
	           "    _kmentremantenciones = " + (_kmentremantenciones != null ? _kmentremantenciones : "null") + "," +
	           "    _descripcion = " + (_descripcion != null ? "'" + _descripcion + "'" : "null") + "," +
	           "    _idMantencionUsuario = " + (_idMantencionUsuario != null ? _idMantencionUsuario : "null") + "," +
	           "    _diasentremantenciones = " + (_diasentremantenciones != null ? _diasentremantenciones : "null") + "," +
	           "    _URL = " + (_url != null ? "'" + _url + "'" : "null") + "," +
	           "    _DependeKm = " + (_dependekm != null ? "b'" + _dependekm : "null") + "," +
	           "    _beneficios = " + (_beneficios != null ? "'" + _beneficios + "'" : "null") +
			   "]";
    }


    public String toJSON() {
        return "MantencionUsuario : {" +
	           "    \"_nombre\" : " + (_nombre != null ? "\"" + _nombre + "\"" : "null") + "," +
	           "    \"_fecha_modificacion\" : " + (_fechaModificacion != null ? "\"" + _fechaModificacion + "\"" : "null") + "," +
	           "    \"_idUsuario\" : " + (_idUsuario != null ? _idUsuario : "null") + "," +
	           "    \"_idVehiculo\" : " + (_idVehiculo != null ? _idVehiculo : "null") + "," +
	           "    \"_borrado\" : " + (_borrado != null ? "b'" + _borrado : "null") + "," +
	           "    \"_kmentremantenciones\" : " + (_kmentremantenciones != null ? _kmentremantenciones : "null") + "," +
	           "    \"_descripcion\" : " + (_descripcion != null ? "\"" + _descripcion + "\"" : "null") + "," +
	           "    \"_idMantencionUsuario\" : " + (_idMantencionUsuario != null ? _idMantencionUsuario : "null") + "," +
	           "    \"_diasentremantenciones\" : " + (_diasentremantenciones != null ? _diasentremantenciones : "null") + "," +
	           "    \"_URL\" : " + (_url != null ? "\"" + _url + "\"" : "null") + "," +
	           "    \"_DependeKm\" : " + (_dependekm != null ? "b'" + _dependekm : "null") + "," +
	           "    \"_beneficios\" : " + (_beneficios != null ? "\"" + _beneficios + "\"" : "null") +
			   "}";
    }


    public String toXML() {
        return "<MantencionUsuario>" +
	           "    <nombre" + (_nombre != null ? ">" + _nombre + "</nombre>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <fechaModificacion" + (_fechaModificacion != null ? ">" + _fechaModificacion + "</fechaModificacion>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <idUsuario" + (_idUsuario != null ? ">" + _idUsuario + "</idUsuario>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <idVehiculo" + (_idVehiculo != null ? ">" + _idVehiculo + "</idVehiculo>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <borrado" + (_borrado != null ? ">" + _borrado + "</borrado>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <kmentremantenciones" + (_kmentremantenciones != null ? ">" + _kmentremantenciones + "</kmentremantenciones>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <descripcion" + (_descripcion != null ? ">" + _descripcion + "</descripcion>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <idMantencionUsuario" + (_idMantencionUsuario != null ? ">" + _idMantencionUsuario + "</idMantencionUsuario>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <diasentremantenciones" + (_diasentremantenciones != null ? ">" + _diasentremantenciones + "</diasentremantenciones>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <url" + (_url != null ? ">" + _url + "</url>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <dependekm" + (_dependekm != null ? ">" + _dependekm + "</dependekm>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <beneficios" + (_beneficios != null ? ">" + _beneficios + "</beneficios>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
			   "</MantencionUsuario>";
    }


    public static MantencionUsuario fromXMLNode(Node xmlNode) {
        MantencionUsuario ret = new MantencionUsuario();

        Element element = (Element) xmlNode;

        ret.setNombre(element.getElementsByTagName("nombre").item(0).getTextContent());
        ret.setFechaModificacion(element.getElementsByTagName("fecha_modificacion").item(0).getTextContent());
        ret.setIdUsuario(Long.decode(element.getElementsByTagName("id_usuario").item(0).getTextContent()));
        ret.setIdVehiculo(Long.decode(element.getElementsByTagName("id_vehiculo").item(0).getTextContent()));
        ret.setBorrado(Boolean.valueOf(element.getElementsByTagName("borrado").item(0).getTextContent()));
        ret.setKmentremantenciones(Integer.decode(element.getElementsByTagName("KmEntreMantenciones").item(0).getTextContent()));
        ret.setDescripcion(element.getElementsByTagName("descripcion").item(0).getTextContent());
        ret.setIdMantencionUsuario(Long.decode(element.getElementsByTagName("id_mantencion_usuario").item(0).getTextContent()));
        ret.setDiasentremantenciones(Integer.decode(element.getElementsByTagName("DiasEntreMantenciones").item(0).getTextContent()));
        ret.setUrl(element.getElementsByTagName("URL").item(0).getTextContent());
        ret.setDependekm(Boolean.valueOf(element.getElementsByTagName("DependeKm").item(0).getTextContent()));
        ret.setBeneficios(element.getElementsByTagName("beneficios").item(0).getTextContent());

        return ret;
    }
}
