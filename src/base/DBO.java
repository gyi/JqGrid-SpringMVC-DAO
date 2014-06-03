package base;

import java.io.InputStream;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;
//import javax.sql.DataSource;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service("dbo")
@SuppressWarnings("unchecked")
//extends JdbcDaoSupport 
public class DBO extends JdbcDaoSupport implements IDBO{
	@Resource(name="dataSource")
	private DataSource dataSource;
	
	@PostConstruct
	void init(){
		super.setDataSource(this.dataSource);
	}
	
	/**
	 * ������ݿ�
	 * @return 
	 */
	public Connection getConnections() {
		// new DBConnection("oracle", "192.168.17.108��, 3306, "test", "test", "lxd", "123456��);
		String dbType = "mysql";
		String dbIp = "localhost";
		String dbPort = "3306";
		String dbInstance = "mysql";
		String userName = "root";
		String password = "323847";
		String driverClass = null;
		String url = null;
		if (dbType.equalsIgnoreCase("mysql")) {
			driverClass = "com.mysql.jdbc.Driver";
			url = "jdbc:mysql://" + dbIp + ":" + dbPort + "/" + dbInstance;
		} else if (dbType.equalsIgnoreCase("oracle")) {
			driverClass = "oracle.jdbc.driver.OracleDriver";
			url = "jdbc:oracle:thin:@" + dbIp + ":" + dbPort + ":" + dbInstance;
		} else if (dbType.equalsIgnoreCase("post")) {
			driverClass = "org.postgresql.Driver";
			url = "jdbc:postgresql://" + dbIp + ":" + dbPort + "/" + dbInstance;
		} else if (dbType.equalsIgnoreCase("post")) {
			driverClass = "org.postgresql.Driver";
			url = "jdbc:postgresql://" + dbIp + ":" + dbPort + "/" + dbInstance;
		}
		Connection conn = null;
		try {
			Class.forName(driverClass);
			conn = DriverManager.getConnection(url, userName, password);
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		return conn;
	}
	
	/**
	 * ����SQL���,ֻȡ���ü�¼���ĵ�һ�����
	 *
	 * @param sql
	 * @param useBeanName �Ƿ�ʹ��JAVA BEAN�������ʽ�����ֶ���
	 * @param params
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings({ "unchecked", "unchecked" })
	public Map<String,Object> readFirstRecord(String format, String csql,Object params[]) throws Exception{
		try{
			String sql = new StringBuffer("SELECT * FROM (").append(csql).append(") as Just_One LIMIT 0,1").toString();
			List<Map<String,Object>> list = this.executeQuery(format, sql, params);
			
			if(list != null && list.size()>0){
				return (Map<String,Object>)list.get(0);
			}
			return new HashMap<String,Object>();
		}catch(Exception e){
			throw e;
		}
	}
	
	/**
	 * �Ƿ�ΪString ���� jdk 1.5
	 * @param inValue
	 * @return
	 * @see setParameterValueInternal
	 */
	private boolean isStringValue(Object inValue) {
		return (inValue instanceof CharSequence || inValue instanceof StringWriter);
	}
	
	/**
	 * �Ƿ�Ϊ��������
	 * @param inValue
	 * @return
	 * @see setParameterValueInternal
	 */
	private boolean isDateValue(Object inValue) {
		return (inValue instanceof java.util.Date && !(inValue instanceof java.sql.Date ||
				inValue instanceof java.sql.Time || inValue instanceof java.sql.Timestamp));
	}
	
	/**
	 * �Ѳ������PreparedStatement�����ָ��λ����
	 * 
	 * @param ps
	 * @param paramIndex
	 * @param inValue
	 * @throws SQLException
	 */
	private void setParameterValueInternal(PreparedStatement ps, int paramIndex,  Object inValue) throws SQLException {
		if (inValue == null) {
			boolean useSetObject = false;
			try {
				useSetObject = (ps.getConnection().getMetaData().getDatabaseProductName().indexOf("Informix") != -1);
			}
			catch (Throwable ex) {
			}
			if (useSetObject) {
				ps.setObject(paramIndex, null);
			}
			else {
				ps.setNull(paramIndex, Types.NULL);
			}
		}

		else {//inValue != null
			if (isStringValue(inValue)) {
				ps.setString(paramIndex, inValue.toString());
			}
			else if (isDateValue(inValue)) {
				ps.setTimestamp(paramIndex, new java.sql.Timestamp(((java.util.Date) inValue).getTime()));
			}
			else if (inValue instanceof Calendar) {
				Calendar cal = (Calendar) inValue;
				ps.setTimestamp(paramIndex, new java.sql.Timestamp(cal.getTime().getTime()));
			}
			else {
				ps.setObject(paramIndex, inValue);
			}
		}
	}
	
	/**
	 * �󶨲���PreparedStatement������
	 *
	 * @author 
	 * @param stat
	 * @param params
	 * @throws Exception 
	 */
	private void setPreparedValue(PreparedStatement stat,Object[] params) throws Exception{
		try{
			if(params != null && params.length >0){	
				if(!(params.length==1 && "".equals(String.valueOf(params[0])) )){
					for(int i=0;i<params.length;i++){
						this.setParameterValueInternal(stat, i+1, params[i]);
					}
				}
			}
		}catch(SQLException e){
			throw e;
		}catch(Exception e){
			throw e;
		}
	}
	
	/**
	 * ��ȡJDBC��¼��������
	 * 
	 * @param rs JDBC��¼��
	 * @param useBeanName �Ƿ�ʹ��JAVA BEAN�������ʽ�����ֶ���
	 *        JAVA BEAN�������ʽΪ��townName
	 * @return ��¼�������е��ֶ���
	 * @throws Exception 
	 * @see executeQuery(String sql,Object params[], boolean useBeanName);
	 */
	private String[] getColumnNames(ResultSet rs, boolean useBeanName) throws Exception{
		try{
			//�õ����(rs)�Ľṹ��Ϣ�������ֶ����ֶ����
			ResultSetMetaData meta = rs.getMetaData();
			//���Է��ر���
			int colCount = meta.getColumnCount();
			String[] colNames = new String[colCount];

			if(useBeanName){
				for(int i=0; i<colCount; i++){
					String col = meta.getColumnName(i+1);
					colNames[i] = col;
				}
			}else{
				for(int i=0; i<colCount; i++){
					String col = meta.getColumnName(i+1);
					colNames[i] = col;
				}
			}
			
			return colNames;
		}catch(SQLException e){
			throw e;
		}catch(Exception e){
			throw e;
		}		
	}
	
	/**
	 * ��ݴ����SQL��估�����б�,��ѯ����¼����LIST
	 * 
	 * ���裺
	 * 1.�������
	 * 2.���SQL����ѯ����¼��
	 * 3.��ȡ��¼��д�뵽LIST��
	 * 
	 * ע:ע�����е�����ת��Ϊ��׼��POJO�еı�������ʽ
	 * @author 
	 * @param sql 		��ѯ���
	 * @param params	�����б�
	 * @param useBeanName �Ƿ�ʹ��JAVA BEAN�������ʽ�����ֶ���
	 * @return Map����ļ���List�������
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public List executeQuery(String format, String sql,Object params[]) throws Exception{
		List<Object> results = new ArrayList<Object>();
		Connection con         = null;
		PreparedStatement stat = null;
		ResultSet rs           = null;
		try{
			con = this.getConnections();
			stat = con.prepareStatement(sql);
			if(sql.indexOf("?") >= 0){
				this.setPreparedValue(stat, params);
			}
			rs = stat.executeQuery();
			String[] colNames = getColumnNames(rs, true);
			for(int i=1; rs.next(); i++){
				if(format.equals("1")){
					results.add(mapRow(rs, colNames));
				}
				else if(format.equals("2")){
					results.add(mapRow(rs, colNames,i));
				}
				
			}
			return results;
		}catch(Exception e){
			throw e;
		}finally{
			this.closeResultSet(rs);
			this.closePreparedStatement(stat);
			this.closeConnection(con);
		}
	}
	
	/**
	 * 根据传入的SQL语句,取得该查询语句所查询出的记录个数
	 *
	 * @param sql
	 * @param params
	 * @return
	 * @throws Exception 
	 */
	public long getTotalCount(String sql,Object[] params) throws Exception{
		try{
			String TotalSql = new StringBuffer().append("SELECT COUNT(*) count FROM (").append(sql).append(") AS TABLE_ALIAS_ ").toString();
			Map map = this.readFirstRecord("1",TotalSql, params);
			Object o =  this.readFirstRecord("1",TotalSql, params).get("count");
			long l = (Long) this.readFirstRecord("1",TotalSql, params).get("count");
			//String str =  (String) this.readFirstRecord(TotalSql, params).get("count");
			
			return (Long) this.readFirstRecord("1",TotalSql, params).get("count");
			//return this.getJdbcTemplate().queryForLong(Util.createSizeSql(sql), params);//改
		}catch(Exception e){
			throw new Exception(e);
		}
	}
	
	/**
	 * 判断一条记录是否存在
	 * 
	 * @param sql 查询记录的SQL语句
	 * @param object 参数集合，需要跟SQL中"？？"，所对应
	 * @throws Exception 
	 */
	public boolean isRecordExists(String sql,Object[] params) throws Exception{
		try{
			return getTotalCount(sql,params) > 0 ? true:false;
		}catch(Exception e){
			throw new Exception(e);
		}
	}
	
	/**
	 * ��ȡ����е� (index) ����ֵ
	 * @param rs JDBC��¼��
	 * @param index �����
	 * @return
	 * @throws SQLException
	 */
	private Object getResultSetValue(ResultSet rs, int index) throws SQLException {
		Object obj = rs.getObject(index);
		if (obj instanceof Blob) {
			return rs.getBytes(index);
		}else if (obj instanceof Clob) {
			return rs.getString(index);
		}else if (obj != null && obj.getClass().getName().startsWith("oracle.sql.TIMESTAMP")) {
			return rs.getTimestamp(index);
		}else if (obj != null && obj.getClass().getName().startsWith("oracle.sql.DATE")) {
			String metaDataClassName = rs.getMetaData().getColumnClassName(index);
			if ("java.sql.Timestamp".equals(metaDataClassName) ||
					"oracle.sql.TIMESTAMP".equals(metaDataClassName)) {
				return rs.getTimestamp(index);
			}else {
				return rs.getDate(index);
			}
		}else if (obj != null && obj instanceof java.sql.Date) {
			if ("java.sql.Timestamp".equals(rs.getMetaData().getColumnClassName(index))) {
				return rs.getTimestamp(index);
			}
		}else if (obj != null && obj.getClass().getName().equals("java.math.BigDecimal")) {
			return String.valueOf(obj);
		}
		return obj;
	}
	
	/**
	 * ��ȡһ����¼�Ľ��
	 * @param rs JDBC��¼��
	 * @param useBeanName �Ƿ�ʹ��JAVA BEAN�������ʽ�����ֶ���
	 *        JAVA BEAN�������ʽΪ��townName
	 * @return Object
	 * @throws SQLException
	 */
	private Map mapRow(ResultSet rs, String[] colNames) throws SQLException {
		Map<String, Object> mapOfColValues = new HashMap<String, Object>(colNames.length);
		List<Object> listOfColValues = new ArrayList<Object>();
		for (int i = 1; i <= colNames.length; i++) {
			String key = colNames[i-1];
			Object obj = getResultSetValue(rs, i);
			mapOfColValues.put(key, obj);
		}
		return mapOfColValues;
	}
	
	
	/**
	 * ��ȡһ����¼�Ľ��
	 * @param rs JDBC��¼��
	 * @param useBeanName �Ƿ�ʹ��JAVA BEAN�������ʽ�����ֶ���
	 *        JAVA BEAN�������ʽΪ��townName
	 * @return Object
	 * @throws SQLException
	 */
	private Object mapRow(ResultSet rs, String[] colNames, int index) throws SQLException {
		Map<String, Object> mapOfColValues = new HashMap<String, Object>(colNames.length);
		List<Object> listOfColValues = new ArrayList<Object>(colNames.length);
		
		for (int i = 1; i <= colNames.length; i++) {
			//String key = colNames[i-1];
			Object obj = getResultSetValue(rs, i);
			listOfColValues.add(i-1, obj);
		}
		mapOfColValues.put("id", index);
		mapOfColValues.put("cell", listOfColValues);
		return mapOfColValues;
	}
	
	/**
	 * �ر�ResultSet 
	 *
	 * @author 
	 * @param rs
	 * @throws Exception 
	 */
	public void closeResultSet(ResultSet rs) throws Exception{
		try{
			if(rs != null){
				rs.close();
			}
			rs = null;
		}catch(SQLException e){
			throw e;
		}catch(Exception e){
			throw e;
		}
	}
	/**
	 * �ر�PreparedStatement
	 * 
	 * @param stat
	 * @throws Exception 
	 */
	public void closePreparedStatement(PreparedStatement stat) throws Exception{
		try{
			if(stat != null){
				stat.close();
			}
			stat = null;
		}catch(SQLException e){
			throw e;
		}catch(Exception e){
			throw e;
		}
	}
	
	/**
	 * �ر�Connection 
	 * 
	 * @param con
	 * @throws Exception 
	 */
	public void closeConnection(Connection con) throws Exception{
		try{
			//���� spring ����
			if(con != null){
				con.close();
			}
			con = null;
		}catch(Exception e){
			throw e;
		}
	}
	
	/**
	 * ȡ�����е���һ��ֵ
	 * 
	 * @param seqName
	 * @return
	 * @throws Exception 
	 */
	public Long getSequenceNextVal(String seqName) throws Exception{
		try{
			String sql = new StringBuffer("SELECT ").append(seqName.toUpperCase()).append(".NEXTVAL FROM DUAL").toString();
			//return (long) 0;
			return this.getJdbcTemplate().queryForLong(sql);
		}catch(Exception e){
			throw e;
		}
	}
	
	/**
	 * ʹ��SQL��������ݿ�Ĳ���,֧��insert update delete
	 * ����д���
	 * 
	 * ʹ��SQL��������ݿ�Ĳ���,���ֶε�д�룬(���ֶε��������Ϊblob)
	 * sql��䣺select blob_Name from table_name where key = ?
	 * 
	 * @param sql
	 * @param blobColumn
	 * @param blobValue
	 * @return
	 * @throws Exception 
	 * @throws SQLException 
	 */
	public int executeUpdate(String sql, Object[] params) throws Exception{
		Connection con         = null;
		PreparedStatement stat = null;
		try{
			con = this.getConnections();
			stat = con.prepareStatement(sql);
			if(params != null && params.length >0){	
				if(!(params.length==1 && "".equals(String.valueOf((params[0]))))){
					for(int i=0;i<params.length;i++){
						this.setPreStatParams(stat, i+1, params[i]);
					}
				}
			}
			return stat.executeUpdate();
		}catch(Exception e){
			throw e;
		}finally{
		
			this.closePreparedStatement(stat);
			this.closeConnection(con);
		}
	}
	
	/**
	 * ��ݱ��������Զ����ò�ͬ��PreparedStatement��setֵ����,������д��
	 * 
	 * @param stat			PreparedStatement����
	 * @param typeName		����������
	 * @param index			����ֵ
	 * @param val			����ֵ
	 * @throws Exception 
	 */
	@SuppressWarnings("unused")
	private void setPreStatParams(PreparedStatement stat, int index, Object val) throws Exception{
		try{
			if(val == null){
				boolean useSetObject = false;
				try {
					useSetObject = (stat.getConnection().getMetaData().getDatabaseProductName().indexOf("Informix") != -1);
				}catch (Throwable ex) {
				}
				
				if (useSetObject) {
					stat.setObject(index, null);
				}else {
					stat.setNull(index, Types.NULL);
				}
			}else{
				if(String.class.equals(val.getClass())){
					if(!"".equals(val.toString().trim())){
						stat.setString(index, String.valueOf(val).trim());
					}else{
						stat.setNull(index, Types.NULL);
					}
					
				}else if(Integer.class.equals(val.getClass())){
					stat.setInt(index, (Integer)val);
					
				}else if(Long.class.equals(val.getClass())){
					stat.setLong(index, (Long)val);
					
				}else if(Short.class.equals(val.getClass())){
					stat.setShort(index, (Short)val);
					
				}else if(Boolean.class.equals(val.getClass())){
					stat.setBoolean(index, (Boolean)val);
					
				}else if(Float.class.equals(val.getClass())){
					stat.setFloat(index,(Float)val);
					
				}else if(Double.class.equals(val.getClass())){
					stat.setDouble(index, (Double)val);
					
				}else if(Byte.class.equals(val.getClass())){
					stat.setByte(index, (Byte)val);
					
				}else if(BigDecimal.class.equals(val.getClass())){
					stat.setBigDecimal(index, (BigDecimal) val);
					
				}else if(val instanceof java.util.Date) {
					if (val instanceof java.sql.Time) {
						stat.setTime(index, (java.sql.Time) val);
					}
					else {
						stat.setTime(index, new java.sql.Time(((java.util.Date) val).getTime()));
					}
					
				}else if (val instanceof Calendar) {
						Calendar cal = (Calendar) val;
						stat.setTime(index, new java.sql.Time(cal.getTime().getTime()), cal);
						
				}else {
					stat.setObject(index, val, Types.TIME);
					
				}
			}
		}catch(SQLException e){
			throw e;
		}catch(Exception e){
			throw e;
		}
	}
	

}
