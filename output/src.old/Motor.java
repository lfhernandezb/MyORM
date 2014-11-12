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
public class Motor {
    protected String _configuracion;
    protected Byte _valvulas;
    protected Integer _torque;
    protected Byte _cilindros;
    protected String _razonCompresion;
    protected String _codigoFabricante;
    protected String _codigo;
    protected Integer _desplazamiento;
    protected Long _idModeloAnio;
    protected String _tamanio;
    protected String _tipo;
    protected Long _id;
    protected Integer _potencia;
    protected String _tipoCompresor;
    protected String _tipoCombustible;

    private final static String _str_sql = 
        "    SELECT" +
        "    mo.configuracion AS configuracion," +
        "    mo.valvulas AS valvulas," +
        "    mo.torque AS torque," +
        "    mo.cilindros AS cilindros," +
        "    mo.razon_compresion AS razon_compresion," +
        "    mo.codigo_fabricante AS codigo_fabricante," +
        "    mo.codigo AS codigo," +
        "    mo.desplazamiento AS desplazamiento," +
        "    mo.id_modelo_anio AS id_modelo_anio," +
        "    mo.tamanio AS tamanio," +
        "    mo.tipo AS tipo," +
        "    mo.id_motor AS id," +
        "    mo.potencia AS potencia," +
        "    mo.tipo_compresor AS tipo_compresor," +
        "    mo.tipo_combustible AS tipo_combustible" +
        "    FROM motor mo";

    public Motor() {
        _configuracion = null;
        _valvulas = null;
        _torque = null;
        _cilindros = null;
        _razonCompresion = null;
        _codigoFabricante = null;
        _codigo = null;
        _desplazamiento = null;
        _idModeloAnio = null;
        _tamanio = null;
        _tipo = null;
        _id = null;
        _potencia = null;
        _tipoCompresor = null;
        _tipoCombustible = null;

    }
    /**
     * @return the _configuracion
     */
    public String getConfiguracion() {
        return _configuracion;
    }
    /**
     * @return the _valvulas
     */
    public Byte getValvulas() {
        return _valvulas;
    }
    /**
     * @return the _torque
     */
    public Integer getTorque() {
        return _torque;
    }
    /**
     * @return the _cilindros
     */
    public Byte getCilindros() {
        return _cilindros;
    }
    /**
     * @return the _razonCompresion
     */
    public String getRazonCompresion() {
        return _razonCompresion;
    }
    /**
     * @return the _codigoFabricante
     */
    public String getCodigoFabricante() {
        return _codigoFabricante;
    }
    /**
     * @return the _codigo
     */
    public String getCodigo() {
        return _codigo;
    }
    /**
     * @return the _desplazamiento
     */
    public Integer getDesplazamiento() {
        return _desplazamiento;
    }
    /**
     * @return the _idModeloAnio
     */
    public Long getIdModeloAnio() {
        return _idModeloAnio;
    }
    /**
     * @return the _tamanio
     */
    public String getTamanio() {
        return _tamanio;
    }
    /**
     * @return the _tipo
     */
    public String getTipo() {
        return _tipo;
    }
    /**
     * @return the _id
     */
    public Long getId() {
        return _id;
    }
    /**
     * @return the _potencia
     */
    public Integer getPotencia() {
        return _potencia;
    }
    /**
     * @return the _tipoCompresor
     */
    public String getTipoCompresor() {
        return _tipoCompresor;
    }
    /**
     * @return the _tipoCombustible
     */
    public String getTipoCombustible() {
        return _tipoCombustible;
    }
    /**
     * @param _configuracion the _configuracion to set
     */
    public void setConfiguracion(String _configuracion) {
        this._configuracion = _configuracion;
    }
    /**
     * @param _valvulas the _valvulas to set
     */
    public void setValvulas(Byte _valvulas) {
        this._valvulas = _valvulas;
    }
    /**
     * @param _torque the _torque to set
     */
    public void setTorque(Integer _torque) {
        this._torque = _torque;
    }
    /**
     * @param _cilindros the _cilindros to set
     */
    public void setCilindros(Byte _cilindros) {
        this._cilindros = _cilindros;
    }
    /**
     * @param _razonCompresion the _razonCompresion to set
     */
    public void setRazonCompresion(String _razonCompresion) {
        this._razonCompresion = _razonCompresion;
    }
    /**
     * @param _codigoFabricante the _codigoFabricante to set
     */
    public void setCodigoFabricante(String _codigoFabricante) {
        this._codigoFabricante = _codigoFabricante;
    }
    /**
     * @param _codigo the _codigo to set
     */
    public void setCodigo(String _codigo) {
        this._codigo = _codigo;
    }
    /**
     * @param _desplazamiento the _desplazamiento to set
     */
    public void setDesplazamiento(Integer _desplazamiento) {
        this._desplazamiento = _desplazamiento;
    }
    /**
     * @param _idModeloAnio the _idModeloAnio to set
     */
    public void setIdModeloAnio(Long _idModeloAnio) {
        this._idModeloAnio = _idModeloAnio;
    }
    /**
     * @param _tamanio the _tamanio to set
     */
    public void setTamanio(String _tamanio) {
        this._tamanio = _tamanio;
    }
    /**
     * @param _tipo the _tipo to set
     */
    public void setTipo(String _tipo) {
        this._tipo = _tipo;
    }
    /**
     * @param _id the _id to set
     */
    public void setId(Long _id) {
        this._id = _id;
    }
    /**
     * @param _potencia the _potencia to set
     */
    public void setPotencia(Integer _potencia) {
        this._potencia = _potencia;
    }
    /**
     * @param _tipoCompresor the _tipoCompresor to set
     */
    public void setTipoCompresor(String _tipoCompresor) {
        this._tipoCompresor = _tipoCompresor;
    }
    /**
     * @param _tipoCombustible the _tipoCombustible to set
     */
    public void setTipoCombustible(String _tipoCombustible) {
        this._tipoCombustible = _tipoCombustible;
    }

    public static Motor fromRS(ResultSet p_rs) throws SQLException {
        Motor ret = new Motor();

        ret.setConfiguracion(p_rs.getString("configuracion"));
        ret.setValvulas(p_rs.getByte("valvulas"));
        ret.setTorque(p_rs.getInt("torque"));
        ret.setCilindros(p_rs.getByte("cilindros"));
        ret.setRazonCompresion(p_rs.getString("razon_compresion"));
        ret.setCodigoFabricante(p_rs.getString("codigo_fabricante"));
        ret.setCodigo(p_rs.getString("codigo"));
        ret.setDesplazamiento(p_rs.getInt("desplazamiento"));
        ret.setIdModeloAnio(p_rs.getLong("id_modelo_anio"));
        ret.setTamanio(p_rs.getString("tamanio"));
        ret.setTipo(p_rs.getString("tipo"));
        ret.setId(p_rs.getLong("id"));
        ret.setPotencia(p_rs.getInt("potencia"));
        ret.setTipoCompresor(p_rs.getString("tipo_compresor"));
        ret.setTipoCombustible(p_rs.getString("tipo_combustible"));

        return ret;
    }

    public static Motor getByParameter(Connection p_conn, String p_key, String p_value) throws SQLException {
        Motor ret = null;
        
        String str_sql = _str_sql +
            "  WHERE mo." + p_key + " = " + p_value +
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

    public static Motor getById(Connection p_conn, String p_id) throws Exception {
        return getByParameter(p_conn, "id_motor", p_id);
    }
    
    public static ArrayList<Motor> seek(Connection p_conn, ArrayList<AbstractMap.SimpleEntry<String, String>> p_parameters, String p_order, String p_direction, int p_offset, int p_limit) throws Exception {
        Statement stmt = null;
        ResultSet rs = null;
        String str_sql;
        ArrayList<Motor> ret;
        
        str_sql = "";
        
        try {
            ArrayList<String> array_clauses = new ArrayList<String>();
            
            ret = new ArrayList<Motor>();
            
            str_sql = _str_sql;
            
            for (AbstractMap.SimpleEntry<String, String> p : p_parameters) {
                if (p.getKey().equals("id_motor")) {
                    array_clauses.add("mo.id_motor = " + p.getValue());
                }
                else if (p.getKey().equals("id_modelo_anio")) {
                    array_clauses.add("mo.id_modelo_anio = " + p.getValue());
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
            "    UPDATE motor" +
            "    SET" +
            "    configuracion = " + (_configuracion != null ? "'" + _configuracion + "'" : "null") + "," +
            "    valvulas = " + (_valvulas != null ? _valvulas : "null") + "," +
            "    torque = " + (_torque != null ? _torque : "null") + "," +
            "    cilindros = " + (_cilindros != null ? _cilindros : "null") + "," +
            "    razon_compresion = " + (_razonCompresion != null ? "'" + _razonCompresion + "'" : "null") + "," +
            "    codigo_fabricante = " + (_codigoFabricante != null ? "'" + _codigoFabricante + "'" : "null") + "," +
            "    codigo = " + (_codigo != null ? "'" + _codigo + "'" : "null") + "," +
            "    desplazamiento = " + (_desplazamiento != null ? _desplazamiento : "null") + "," +
            "    tamanio = " + (_tamanio != null ? "'" + _tamanio + "'" : "null") + "," +
            "    tipo = " + (_tipo != null ? "'" + _tipo + "'" : "null") + "," +
            "    potencia = " + (_potencia != null ? _potencia : "null") + "," +
            "    tipo_compresor = " + (_tipoCompresor != null ? "'" + _tipoCompresor + "'" : "null") + "," +
            "    tipo_combustible = " + (_tipoCombustible != null ? "'" + _tipoCombustible + "'" : "null") +
            "    WHERE" +
            "    id_motor = " + Long.toString(this._id);

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
            "    INSERT INTO motor" +
            "    (" +
            "    configuracion, " +
            "    valvulas, " +
            "    torque, " +
            "    cilindros, " +
            "    razon_compresion, " +
            "    codigo_fabricante, " +
            "    codigo, " +
            "    desplazamiento, " +
            "    id_modelo_anio, " +
            "    tamanio, " +
            "    tipo, " +
            "    id_motor, " +
            "    potencia, " +
            "    tipo_compresor, " +
            "    tipo_combustible)" +
            "    VALUES" +
            "    (" +
            "    " + (_configuracion != null ? "'" + _configuracion + "'" : "null") + "," +
            "    " + (_valvulas != null ? "'" + _valvulas + "'" : "null") + "," +
            "    " + (_torque != null ? "'" + _torque + "'" : "null") + "," +
            "    " + (_cilindros != null ? "'" + _cilindros + "'" : "null") + "," +
            "    " + (_razonCompresion != null ? "'" + _razonCompresion + "'" : "null") + "," +
            "    " + (_codigoFabricante != null ? "'" + _codigoFabricante + "'" : "null") + "," +
            "    " + (_codigo != null ? "'" + _codigo + "'" : "null") + "," +
            "    " + (_desplazamiento != null ? "'" + _desplazamiento + "'" : "null") + "," +
            "    " + (_idModeloAnio != null ? "'" + _idModeloAnio + "'" : "null") + "," +
            "    " + (_tamanio != null ? "'" + _tamanio + "'" : "null") + "," +
            "    " + (_tipo != null ? "'" + _tipo + "'" : "null") + "," +
            "    " + (_id != null ? "'" + _id + "'" : "null") + "," +
            "    " + (_potencia != null ? "'" + _potencia + "'" : "null") + "," +
            "    " + (_tipoCompresor != null ? "'" + _tipoCompresor + "'" : "null") + "," +
            "    " + (_tipoCombustible != null ? "'" + _tipoCombustible + "'" : "null") +
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
            "    DELETE FROM motor" +
            "    WHERE" +
            "    id_motor = " + Long.toString(this._id);

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
        Motor obj = null;
        
        String str_sql = _str_sql +
            "    WHERE" +
            "    id_motor = " + Long.toString(this._id) +
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

                _configuracion = obj.getConfiguracion();
                _valvulas = obj.getValvulas();
                _torque = obj.getTorque();
                _cilindros = obj.getCilindros();
                _razonCompresion = obj.getRazonCompresion();
                _codigoFabricante = obj.getCodigoFabricante();
                _codigo = obj.getCodigo();
                _desplazamiento = obj.getDesplazamiento();
                _idModeloAnio = obj.getIdModeloAnio();
                _tamanio = obj.getTamanio();
                _tipo = obj.getTipo();
                _potencia = obj.getPotencia();
                _tipoCompresor = obj.getTipoCompresor();
                _tipoCombustible = obj.getTipoCombustible();
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
            "    id_motor = " + Long.toString(this._id) +
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
        return "Motor [" +
	           "    _configuracion = " + (_configuracion != null ? "'" + _configuracion + "'" : "null") + "," +
	           "    _valvulas = " + (_valvulas != null ? _valvulas : "null") + "," +
	           "    _torque = " + (_torque != null ? _torque : "null") + "," +
	           "    _cilindros = " + (_cilindros != null ? _cilindros : "null") + "," +
	           "    _razon_compresion = " + (_razonCompresion != null ? "'" + _razonCompresion + "'" : "null") + "," +
	           "    _codigo_fabricante = " + (_codigoFabricante != null ? "'" + _codigoFabricante + "'" : "null") + "," +
	           "    _codigo = " + (_codigo != null ? "'" + _codigo + "'" : "null") + "," +
	           "    _desplazamiento = " + (_desplazamiento != null ? _desplazamiento : "null") + "," +
	           "    _idModeloAnio = " + (_idModeloAnio != null ? _idModeloAnio : "null") + "," +
	           "    _tamanio = " + (_tamanio != null ? "'" + _tamanio + "'" : "null") + "," +
	           "    _tipo = " + (_tipo != null ? "'" + _tipo + "'" : "null") + "," +
	           "    _id = " + (_id != null ? _id : "null") + "," +
	           "    _potencia = " + (_potencia != null ? _potencia : "null") + "," +
	           "    _tipo_compresor = " + (_tipoCompresor != null ? "'" + _tipoCompresor + "'" : "null") + "," +
	           "    _tipo_combustible = " + (_tipoCombustible != null ? "'" + _tipoCombustible + "'" : "null") +
			   "]";
    }


    public String toJSON() {
        return "Motor : {" +
	           "    \"_configuracion\" : " + (_configuracion != null ? "\"" + _configuracion + "\"" : "null") + "," +
	           "    \"_valvulas\" : " + (_valvulas != null ? _valvulas : "null") + "," +
	           "    \"_torque\" : " + (_torque != null ? _torque : "null") + "," +
	           "    \"_cilindros\" : " + (_cilindros != null ? _cilindros : "null") + "," +
	           "    \"_razon_compresion\" : " + (_razonCompresion != null ? "\"" + _razonCompresion + "\"" : "null") + "," +
	           "    \"_codigo_fabricante\" : " + (_codigoFabricante != null ? "\"" + _codigoFabricante + "\"" : "null") + "," +
	           "    \"_codigo\" : " + (_codigo != null ? "\"" + _codigo + "\"" : "null") + "," +
	           "    \"_desplazamiento\" : " + (_desplazamiento != null ? _desplazamiento : "null") + "," +
	           "    \"_idModeloAnio\" : " + (_idModeloAnio != null ? _idModeloAnio : "null") + "," +
	           "    \"_tamanio\" : " + (_tamanio != null ? "\"" + _tamanio + "\"" : "null") + "," +
	           "    \"_tipo\" : " + (_tipo != null ? "\"" + _tipo + "\"" : "null") + "," +
	           "    \"_id\" : " + (_id != null ? _id : "null") + "," +
	           "    \"_potencia\" : " + (_potencia != null ? _potencia : "null") + "," +
	           "    \"_tipo_compresor\" : " + (_tipoCompresor != null ? "\"" + _tipoCompresor + "\"" : "null") + "," +
	           "    \"_tipo_combustible\" : " + (_tipoCombustible != null ? "\"" + _tipoCombustible + "\"" : "null") +
			   "}";
    }


    public String toXML() {
        return "<Motor>" +
	           "    <configuracion" + (_configuracion != null ? ">" + _configuracion + "</configuracion>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <valvulas" + (_valvulas != null ? ">" + _valvulas + "</valvulas>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <torque" + (_torque != null ? ">" + _torque + "</torque>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <cilindros" + (_cilindros != null ? ">" + _cilindros + "</cilindros>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <razonCompresion" + (_razonCompresion != null ? ">" + _razonCompresion + "</razonCompresion>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <codigoFabricante" + (_codigoFabricante != null ? ">" + _codigoFabricante + "</codigoFabricante>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <codigo" + (_codigo != null ? ">" + _codigo + "</codigo>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <desplazamiento" + (_desplazamiento != null ? ">" + _desplazamiento + "</desplazamiento>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <idModeloAnio" + (_idModeloAnio != null ? ">" + _idModeloAnio + "</idModeloAnio>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <tamanio" + (_tamanio != null ? ">" + _tamanio + "</tamanio>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <tipo" + (_tipo != null ? ">" + _tipo + "</tipo>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <id" + (_id != null ? ">" + _id + "</id>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <potencia" + (_potencia != null ? ">" + _potencia + "</potencia>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <tipoCompresor" + (_tipoCompresor != null ? ">" + _tipoCompresor + "</tipoCompresor>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
	           "    <tipoCombustible" + (_tipoCombustible != null ? ">" + _tipoCombustible + "</tipoCombustible>" : " xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>") +
			   "</Motor>";
    }


    public static Motor fromXMLNode(Node xmlNode) {
        Motor ret = new Motor();

        Element element = (Element) xmlNode;

        ret.setConfiguracion(element.getElementsByTagName("configuracion").item(0).getTextContent());
        ret.setValvulas(Byte.decode(element.getElementsByTagName("valvulas").item(0).getTextContent()));
        ret.setTorque(Integer.decode(element.getElementsByTagName("torque").item(0).getTextContent()));
        ret.setCilindros(Byte.decode(element.getElementsByTagName("cilindros").item(0).getTextContent()));
        ret.setRazonCompresion(element.getElementsByTagName("razon_compresion").item(0).getTextContent());
        ret.setCodigoFabricante(element.getElementsByTagName("codigo_fabricante").item(0).getTextContent());
        ret.setCodigo(element.getElementsByTagName("codigo").item(0).getTextContent());
        ret.setDesplazamiento(Integer.decode(element.getElementsByTagName("desplazamiento").item(0).getTextContent()));
        ret.setIdModeloAnio(Long.decode(element.getElementsByTagName("id_modelo_anio").item(0).getTextContent()));
        ret.setTamanio(element.getElementsByTagName("tamanio").item(0).getTextContent());
        ret.setTipo(element.getElementsByTagName("tipo").item(0).getTextContent());
        ret.setId(Long.decode(element.getElementsByTagName("id_motor").item(0).getTextContent()));
        ret.setPotencia(Integer.decode(element.getElementsByTagName("potencia").item(0).getTextContent()));
        ret.setTipoCompresor(element.getElementsByTagName("tipo_compresor").item(0).getTextContent());
        ret.setTipoCombustible(element.getElementsByTagName("tipo_combustible").item(0).getTextContent());

        return ret;
    }
}
