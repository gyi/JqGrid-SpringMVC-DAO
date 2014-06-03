package office.index.bo;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import net.sf.json.JSONObject;

import org.jivesoftware.smack.XMPPException;
import org.springframework.stereotype.Service;

import base.ServiceSupport;
import base.IDBO;

import office.pojo.HelpCategory;

@Service("indexBo")
@SuppressWarnings("unchecked")
public class IndexBoImp  extends ServiceSupport implements IIndexBo {

	@Override
	public Map getPageList(Map page) throws Exception {
		Map responce = new HashMap();
		
		String curPage = String.valueOf(page.get("page"));
		String viewRows = String.valueOf(page.get("viewRows"));
		String start = String.valueOf(page.get("start"));
		String sord = String.valueOf(page.get("sord"));
		String sidx = String.valueOf(page.get("sidx"));
		List<Object> paramList=new ArrayList<Object>(50);
		
		int totalRecord = 0;
		int totalPage = 0;
		
		String sql = "select *" +
				" from help_category as t " +
				" where 1= 1 ";

		String csql = "select count(*) as count" +
				" from help_category as t" +
				" where 1= 1 ";
	
		sql += " order by t."+sidx+" "+sord+ " ";

		sql += " limit "+start+" , "+viewRows+" ";
			
		List resultList = dbo.executeQuery("2", sql.toString(),
				paramList.toArray());
		Map resultMap = dbo.readFirstRecord("1", csql.toString(),
				paramList.toArray());

		totalRecord = Integer.parseInt(String.valueOf(resultMap.get("count")));
		if (totalRecord > 0) {
			totalPage = (int) Math.ceil(totalRecord
					/ Integer.parseInt(viewRows));
		}
		// String totalPage = totalRecord%Integer.parseInt(viewRows)==0 ?
		// String.valueOf(totalRecord/Integer.parseInt(viewRows)) :
		// String.valueOf(totalRecord/Integer.parseInt(viewRows)+1);

		if (Integer.parseInt(curPage) > totalPage) {
			curPage = String.valueOf(totalPage);
		}

		responce.put("page", curPage);
		responce.put("rows", resultList);
		responce.put("totalRecord", totalRecord);
		responce.put("totalPage", String.valueOf(totalPage));
		
		return responce;
		
		//return null;
	}
	
	/**
	 * 添加角色及其权限
	 * 
	 * @param bank
	 * 
	 * history:
	 * 
	 */
	public long doAdd(HelpCategory helpCategory) throws Exception {
		//��Ӳ���Ա��Ϣ
		String sql= "insert into help_category	"+
					"  (help_category_id,		"+
					"   name,                   "+
					"   parent_category_id,     "+
					"   url)                    "+
					"values                     "+
					"  (?,?,?,?)				";
	
		List list = new ArrayList();
		long id=0;
		
		String sqlId = "select max(help_category_id) as max from help_category";
		Map resultMap = dbo.readFirstRecord("1", sqlId.toString(),null);

		id = Long.parseLong(String.valueOf(resultMap.get("max")))+1;
		//id = dbo.getSequenceNextVal("q_help_category_id");
		list.add(id);
		//list.add(helpCategory.getHelpCategoryId());
		list.add(helpCategory.getName());
		list.add(helpCategory.getParentCategoryId());
		list.add(helpCategory.getUrl());

		this.dbo.executeUpdate(sql, list.toArray());

		return id;
	}
	
	/**
	 * 查询一条用户信息
	 * @throws Exception 
	 */
	public Map getIndexById(long id) throws Exception{
		String sql=	" select " +
					"	t.help_category_id, " +
				 	"	t.name, " +
				 	"	t.parent_category_id, " +
				 	"	t.url " +
				 	" from help_category t" +
				 	" where t.help_category_id = ? " ;
		Map result = (HashMap)this.dbo.readFirstRecord("1", sql, new Object[]{id});
		return (HashMap)this.dbo.readFirstRecord("1", sql, new Object[]{id});
	}
	
	/**
	 * 用户信息检查
	 * 
	 * @param id  代理主键
	 * 
	 * @return JSONResult
	 * @throws Exception 
	 */
	public boolean isExistIndex(JSONObject json,long id){
		try{
			String sql = "select * from help_category t where t.help_category_id=? ";
			if (!dbo.isRecordExists(sql, new Object[]{id})) {
				json.put("success","false");
				return false;
			}
						
		}
		catch(Exception e){
			e.printStackTrace();
			return true;
		}
		return true;

	}
	
	/**
	 * 修改用户信息
	 * 
	 * @param map
	 * @return
	 * @throws Exception 
	 * 
	 */
	public void doModify(HelpCategory helpCategory) throws Exception {
		
		//修改SQL语句
		long id = Long.valueOf(helpCategory.getHelpCategoryId());
		List list = new ArrayList();
		
		//修改用户信息
		String sql = " update help_category t set  " +
					" t.name = ?,  " +
					" t.parent_category_id = ?, " +
					" t.url = ?" +
					"where t.help_category_id=?  ";
		
		//list.add(helpCategory.getHelpCategoryId());
		list.add(helpCategory.getName());
		list.add(Long.valueOf(helpCategory.getParentCategoryId()));
		list.add(helpCategory.getUrl());
		list.add(Long.valueOf(id));
		
		this.dbo.executeUpdate(sql, list.toArray());
	}

	/**
	 * 删除用户
	 * 
	 * @param id void
	 * 
	 * history:
	 * @throws Exception 
	 * 
	 */
	public void doDel(long id) throws Exception{	
		String sql = " delete from help_category where help_category_id = ? ";
		dbo.executeUpdate(sql, new Object[]{id});  
	}
	
}
