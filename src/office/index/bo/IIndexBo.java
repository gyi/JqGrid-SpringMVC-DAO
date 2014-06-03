package office.index.bo;

import java.util.Map;

import net.sf.json.JSONObject;

import office.pojo.HelpCategory;

public interface IIndexBo {
	
	/**
	 * ��ȡ�б����
	 * 
	 * @param map
	 * @return
	 * @throws Exception 
	 */
	Map getPageList(Map map) throws Exception;

	/**
	 * 添加角色及其权限
	 * 
	 * @param bank
	 * 
	 * history:
	 * 
	 */
	long doAdd(HelpCategory helpCategory) throws Exception;
	

	/**
	 * 查询一条角色信息
	 */
	public Map getIndexById(long id) throws Exception;
	
	/**
	 * 角色信息检查
	 * 
	 * @param id  代理主键
	 * 
	 * @return JSONResult
	 */
	public boolean isExistIndex(JSONObject json,long id) throws Exception;
	
	/**
	 * 修改用户信息
	 * 
	 * @param map
	 * @return
	 * @throws Exception 
	 * 
	 */
	public void doModify(HelpCategory helpCategory) throws Exception ;
	
	/**
	 * 删除用户
	 * 
	 * @param id void
	 * 
	 * history:
	 * @throws Exception 
	 * 
	 */
	public void doDel(long id) throws Exception ;
}
