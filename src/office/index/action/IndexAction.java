package office.index.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import office.index.bo.IIndexBo;
import office.pojo.HelpCategory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import base.Util;

//import eit.day.engineering.bo.IEngineeringBo;
//import eit.pojo.Engineering;

@Controller
@RequestMapping(value = "/index.do")
@SuppressWarnings("unchecked")
public class IndexAction {

	@Resource(name = "indexBo")
	IIndexBo indexBo = null;
	/**
	 * ��ȡ�б����
	 * 
	 * @param request
	 * @param response
	 * 
	 */
	@RequestMapping(params = "method=getPageList")
	public void getPageList(HttpServletRequest request,HttpServletResponse response) {
		// �ռ����
		try {
			 Map page = Util.fetchRequestParameters(request);

			response.setCharacterEncoding("utf-8");
			
			Map pageMap = indexBo.getPageList(page);

			// ����json��
			JSONObject jsonObj = Util.json(pageMap);
			System.out.println(jsonObj.toString());
			response.getWriter().print(jsonObj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * ��ʾ���ҳ�� �������
	 * 
	 * @param request HTTP�������
	 * @param response HTTP��Ӧ����
	 * @return jsp/systemMaintenance/limit/showAddLimit.jsp
	 */
	@RequestMapping(params = "method=showAddView")
	public String showAddView(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//��õ�ǰվ�����
		return "showAddOperator";
	}
	
	/**
	 * ��Ӳ���Ա
	 * 
	 * @param request HTTP�������
	 * @param response HTTP��Ӧ����
	 * @throws IOException 
	 * 
	 */
	@RequestMapping(params = "method=doAdd")
	public void doAdd(HttpServletRequest request,HttpServletResponse response) throws IOException{
		JSONObject json = new JSONObject();
		HelpCategory helpCategory = new HelpCategory();
		Util.putRequestToPojo(request, helpCategory);
		try{
			long newOperatorId=indexBo.doAdd(helpCategory);
			json.put("success","ok");
			
		}catch(Exception e){
			json.put("success","false");
			e.printStackTrace();
			
		}
		json.write(response.getWriter());
		
	}
	
	/**
	 * ��ʾ���ҳ�� �������
	 * 
	 * @param request HTTP�������
	 * @param response HTTP��Ӧ����
	 * @return jsp/systemMaintenance/limit/showAddLimit.jsp
	 */
	@RequestMapping(params = "method=showUpdateView")
	public String showUpdateView(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//��õ�ǰվ�����
		//角色id
		//long id  = ServletRequestUtils.getLongParameter(request, "id",  0);
		
		long id = Long.valueOf(request.getParameter("id"));
		
		//获得角色信息
		request.setAttribute("helpCategory", indexBo.getIndexById(id));
		//return "showModifyOperator";showAddOperator
		return "showModifyOperator";
		
	}
	
	/**
	 * ��Ӳ���Ա
	 * 
	 * @param request HTTP�������
	 * @param response HTTP��Ӧ����
	 * @throws IOException 
	 * 
	 */
	@RequestMapping(params = "method=doModify")
	public void doModify(HttpServletRequest request,HttpServletResponse response) throws IOException{
		JSONObject json = new JSONObject();
		HelpCategory helpCategory = new HelpCategory();
		Util.putRequestToPojo(request, helpCategory);
		long id = Long.valueOf(helpCategory.getHelpCategoryId());
		try{
			//验证数据是否存在
			if(!indexBo.isExistIndex(json,id)){
				json.put("success","false");
				json.write(response.getWriter());
				//json.write(response);
				return;
			}
			
			//获得用户信息
			/*
			HashMap indexMap=(HashMap)indexBo.getIndexById(id);
			String oldName=indexMap.get("name");
     		*/
			
			indexBo.doModify(helpCategory);
			json.put("success","ok");
			
		}catch(Exception e){
			json.put("success","false");
			e.printStackTrace();
			
		}
		json.write(response.getWriter());
		
	}
	
	/**
	 * 删除用户
	 * 
	 * @param request
	 * @param response void
	 * 
	 * history:
	 * @throws IOException 
	 * 
	 */
	@RequestMapping(params="method=doDel")
	public void doDel(HttpServletRequest request, HttpServletResponse response) throws IOException{
		JSONObject json = new JSONObject();
		long id = Long.valueOf(request.getParameter("id"));
		//String name = ServletRequestUtils.getStringParameter(request, "name", "");
		try{
			indexBo.doDel(id); 
			//json.setSuccessType(this.messageContext.getMessage("success-0003", new Object[]{getModel()}));
			json.put("success","ok");
			//this.deleteSuccessSysLog("用户【"+name+"】删除成功", request);
		}catch (Exception e) {
			//log.error(e);
			e.printStackTrace();
			//json.setErrorType(this.messageContext.getMessage("error-0003", new Object[]{getModel()}));
			json.put("success","false");
			//this.deleteFailureSysLog("用户【"+name+"】删除失败", request);
		}
		json.write(response.getWriter());
	}
}
