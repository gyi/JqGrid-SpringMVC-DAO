var Util = new _Util();
function _Util(){
	this.limitNum = 14;
	
	/**
	*��ģ̬����
	* �����������ͬ��ģ̬���嵯���Ĵ�СҲ�в��
	* explorerVersion��������ʱ���ø߶ȿ��ʱ��������汾������ʱ������汾 Ĭ�ϣ�6 
	* isChangeForBrowser �Ƿ������������С Ĭ�� ����true
	* scrolls �Ƿ񼤻������ Ĭ�� �ر�no
	*@param url ҳ���ַ
	*@param width ���ڿ�� 
	*@param height ���ڸ߶�
	*/
	this.openModalDialog = function(url, width, height, arguments,explorerVersion,isChangeForBrowser,scrolls){
		if(isChangeForBrowser==null)isChangeForBrowser = true;
		if(explorerVersion==null)explorerVersion = 6;
		if(isChangeForBrowser){
			if(!_isIE6){
				if(explorerVersion == 6){
					width = width - 6;
					height = height - 54;
				}
			}else if(_isIE){
				if(explorerVersion == 7){
					width = width + 6;
					height = height + 54;
				}
			}
		}
	    if(scrolls!='no'&&scrolls!='false'){
	        scrolls = 'yes';
	    }else{
	        scrolls = 'no';
	    }
		var features = "scroll:"+scrolls+";status:no;dialogWidth:" + width + "px;dialogHeight:" + height + "px;help:no";
		var returnValue = showModalDialog(url, arguments, features); 
		_pageModalDialog = null;
		return returnValue;
	};
}