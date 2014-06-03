/**
 *是否是ie
 */
var _isIE = false;
(function(){
	var userAgent=window.navigator.userAgent.toLowerCase();
	if(typeof(window.oper)!='object'&&userAgent.indexOf('msie')>0){
		_isIE = true;
	}
})();
var _isIE6 = false;
document.write("<!--[if lte IE 6]><script>_isIE6=true;</script><![endif]-->");
//兼容火狐的insertAdjacentElement方法
if(!_isIE){
	HTMLElement.prototype.insertAdjacentElement=function(where,parsedNode){
		switch(where){
			case "beforeBegin":
				this.parentNode.insertBefore(parsedNode,this);
				break;
			case "afterBegin":
				this.insertBefore(parsedNode,this.firstChild);
				break;
			case "beforeEnd":
				this.appendChild(parsedNode);
				break;
			case "afterEnd":
				if(this.nextSibling)
				this.parentNode.insertBefore(parsedNode,this.nextSibling);
				else
				this.parentNode.appendChild(parsedNode);
				break;
		}
	}
}