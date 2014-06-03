var onSaveClick = function(){
	var actionUrl = "index.do?method=doAdd";
	var form = $('#form').serialize();
	alert(form);
	actionUrl += "&" + form;
	// 从Server读取对应ID的JSON数据  
    $.ajax( {  
    	type:"post",
        url : actionUrl,  
        //data : {"form":form}, 
        dataType : "json",  
        cache : false,  
        success : function(data) {  
            // 如果读取结果成功，则将信息载入到对话框中 
        	if(data.success == "ok"){
        		alert("成功");
        		self.close();
			}
			else{
				alert('失败');
			}
        }  
    });  	
}