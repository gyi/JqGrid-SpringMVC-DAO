package base;

import java.util.Map;

import javax.annotation.Resource;

/**
 * �?��业务BO都必须继承的父类,用于注入�?��必要的操作类		各模块都要继�?
 * 
 * @version  1.0  Mar 16, 2009
 * 
 * 版权�?��(C) 辽宁申瑞软件科技有限公司
 * copyright(C) LiaoNing Sunray Software Technology co., Ltd
 * 
 * history:
 *
 */
public class ServiceSupport {

	
	
	@Resource(name="dbo")
	protected IDBO dbo;
	public void setDbo(IDBO dbo) {
		this.dbo = dbo;
	}

}
