package base;

import java.util.Map;

import javax.annotation.Resource;

/**
 * æ?ä¸å¡BOé½å¿é¡»ç»§æ¿çç¶ç±»,ç¨äºæ³¨å¥ä¸?ºå¿è¦çæä½ç±»		åæ¨¡åé½è¦ç»§æ?
 * 
 * @version  1.0  Mar 16, 2009
 * 
 * çææ?(C) è¾½å®ç³çè½¯ä»¶ç§ææéå¬å¸
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
