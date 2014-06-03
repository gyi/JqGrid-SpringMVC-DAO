package base;

import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import java.lang.Integer;

import java.net.URLDecoder;
import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import office.pojo.HelpCategory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@SuppressWarnings("unchecked")
public class Util {
	
    /**
     * ��HttpServletRequest����ȡȫ��������MAP��ʽ����
     * 
     * @param request HttpServletRequest����
     * @return �����еĲ���
     * @throws UnsupportedEncodingException 
     */
    @SuppressWarnings("unchecked")
	public static Map fetchRequestParameters(HttpServletRequest request) throws UnsupportedEncodingException{
		Map page = new HashMap();
		String curPage = request.getParameter("page");
		String viewRows = request.getParameter("rows");
		String sord = request.getParameter("sord");
		//Map 
		if(sord==null)
			page.put("sord","desc");
		else
			page.put("sord",sord);
		String sidx = request.getParameter("sidx");
		if(sidx==null)
			page.put("sidx","up_date");
		else
			page.put("sidx",sidx);
		
		if(curPage==null)
			curPage = "1";
		if(viewRows==null)
			viewRows="10";
		
		String start = String.valueOf(Integer.parseInt(viewRows)*Integer.parseInt(curPage) - Integer.parseInt(viewRows)); // do not put $limit*($page - 1)

		page.put("start",Integer.parseInt(start));
		page.put("page",Integer.parseInt(curPage));
		page.put("viewRows",Integer.parseInt(viewRows));
		
		return page;
		
    }
    
    /**
     * ��HttpServletRequest����ȡȫ��������MAP��ʽ����
     * 
     * @param request HttpServletRequest����
     * @return �����еĲ���
     * @throws UnsupportedEncodingException 
     */
    @SuppressWarnings("unchecked")
	public static JSONObject json(Map page) throws UnsupportedEncodingException{
    	JSONObject jsonObj = new JSONObject();
		// ���jqGrid��JSON����ݸ�ʽҪ���jsonObj��ֵ
		jsonObj.put("page", String.valueOf(page.get("page"))); // ��ǰҳ
		jsonObj.put("total", String.valueOf(page.get("totalPage"))); // ��ҳ��
		jsonObj.put("records", String.valueOf(page.get("totalRecord"))); // �ܼ�¼��
		//jsonObj.put("viewRows", String.valueOf(page.get("viewRows"))); // �ܼ�¼��
		JSONArray rows = new JSONArray();  
		rows.addAll((List)page.get("rows"));  
		jsonObj.put("rows", rows);
		return jsonObj;
    }

	public static void putRequestToPojo(HttpServletRequest request, HelpCategory helpCategory) {
		// TODO Auto-generated method stub
		String helpCategoryId = request.getParameter("helpCategoryId");
		String name = request.getParameter("name");
		String parentCategoryId = request.getParameter("parentCategoryId");
		String url = request.getParameter("url");
		
		helpCategory.setHelpCategoryId(helpCategoryId);
		helpCategory.setName(name);
		helpCategory.setParentCategoryId(parentCategoryId);
		helpCategory.setUrl(url);
	}
}
