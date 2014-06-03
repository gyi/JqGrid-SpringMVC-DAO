package base;

import java.io.StringWriter;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IDBO {
	/**
	 * ������ݿ�
	 * @return 
	 */
//	public Connection getConnection();
	
	/**
	 * ����SQL���,ֻȡ���ü�¼���ĵ�һ�����
	 *
	 * @param sql
	 * @param useBeanName �Ƿ�ʹ��JAVA BEAN�������ʽ�����ֶ���
	 * @param params
	 * @return
	 * @throws Exception 
	 */
	public Map<String,Object> readFirstRecord(String format, String sql,Object params[]) throws Exception;
	
	/**
	 * 判断一条记录是否存在
	 * 
	 * @param sql 查询记录的SQL语句
	 * @param object 参数集合，需要跟SQL中"？？"，所对应
	 * @throws Exception 
	 */
	public boolean isRecordExists(String sql,Object[] params) throws Exception ;
	
	/**
	 * 根据传入的SQL语句,取得该查询语句所查询出的记录个数
	 *
	 * @param sql
	 * @param params
	 * @return
	 * @throws Exception 
	 */
	public long getTotalCount(String sql,Object[] params) throws Exception ;
	
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
	 * @param format 
	 * @param params	�����б�
	 * @param useBeanName �Ƿ�ʹ��JAVA BEAN�������ʽ�����ֶ���
	 * @return Map����ļ���List�������
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public List executeQuery(String format, String sql, Object params[]) throws Exception;
	
	/**
	 * �ر�ResultSet 
	 *
	 * @author 
	 * @param rs
	 * @throws Exception 
	 */
	public void closeResultSet(ResultSet rs) throws Exception;
	/**
	 * �ر�PreparedStatement
	 * 
	 * @param stat
	 * @throws Exception 
	 */
	public void closePreparedStatement(PreparedStatement stat) throws Exception;
	
	/**
	 * �ر�Connection 
	 * 
	 * @param con
	 * @throws Exception 
	 */
	public void closeConnection(Connection con) throws Exception;
	
	/**
	 * ȡ�����е���һ��ֵ
	 * 
	 * @param seqName
	 * @return
	 * @throws Exception 
	 */
	Long getSequenceNextVal(String seqName) throws Exception;
	
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
	int executeUpdate(String sql, Object[] params) throws Exception;
}
