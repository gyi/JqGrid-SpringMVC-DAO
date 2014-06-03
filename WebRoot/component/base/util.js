var Util = new _Util();
function _Util(){
	this.limitNum = 14;
	
	/**
	*打开模态窗口
	* 由于浏览器不同，模态窗体弹出的大小也有差别。
	* explorerVersion开发调试时正好高度宽度时，浏览器版本。开发时浏览器版本 默认：6 
	* isChangeForBrowser 是否开启调整窗体大小 默认 开启true
	* scrolls 是否激活滚动条 默认 关闭no
	*@param url 页面地址
	*@param width 窗口宽度 
	*@param height 窗口高度
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