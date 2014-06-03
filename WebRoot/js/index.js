$(function(){
	jQuery("#list").jqGrid(
			{
				url : 'index.do?method=getPageList',
				datatype : "json",
				colNames : [ 'Help Category Id', 'Name', 'Parent Category Id', 'Url' ],
				colModel : [ {name : 'help_category_id', index : 'help_category_id'}, 
				             {name : 'parent_category_id', index : 'parent_category_id'}, 
				             {name : 'name', index : 'name'}, 
				             {name : 'url', index : 'url'}],
				rowNum : 10,
				rowList : [ 10, 20, 30 ],
				pager : '#pager',
				sortname : 'help_category_id',
				viewrecords : true,
				sortorder : "desc",
				caption : "JSON Example",
				multiselect: false
			});
	//jQuery("#list").jqGrid('navGrid', '#pager', {edit : true, add : true, del : true, search: false});
	
    var alertText = "<div>请选择!</div>";  
    
    $("#list").jqGrid("navGrid", "#pager", {  
        addfunc : openDialogAdding,    
        editfunc : openDialogUpdating, 
        delfunc : openDialogDeleting,  
        alerttext : alertText    
    },{},{},{},{     
        caption: "查找",  
        Find: "Let's go!",  
        multipleSearch: true,  
        groupOps: [{ op: "AND", text: "全部" }],  
    },{});   
	
});

var openDialogAdding = function() {  
	var rowid = null;
	//Util.openModalDialog("index.do?method=showAddView",800,480,window);
	window.open ('index.do?method=showAddView');
	$("#list").trigger("reloadGrid");
	//window.open('index.do?method=showAddView','newwindow','height=100,width=400,top=0,left=0,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no, status=no');  
	//loadSelectedRowData(rowid,'add');
}
var openDialogUpdating = function(rowid) {
	alert(rowid);
	var id = SelectedRowData(rowid);
	window.open ('index.do?method=showUpdateView&id='+id);
	$("#list").trigger("reloadGrid");
    //loadSelectedRowData(rowid,'update');
}  
var openDialogDeleting = function(rowid) {
	alert(rowid);
    loadSelectedRowData(rowid,'del');  
    var id = SelectedRowData(rowid);
	var params = {  
	    	"id" : id  
	    };
    var actionUrl = 'index.do?method=showUpdateView';
    refesh(actionUrl, params)
}  

var SelectedRowData = function(selectedRowId){
	if(selectedRowId!=null){
		var rowData = $("#list").getRowData(selectedRowId);
	}
	return rowData.help_category_id;
}


var refesh = function(actionUrl, params){
    $.ajax( {  
        url : actionUrl,  
        data : params,  
        dataType : "json",  
        cache : false,  
        success : function(data) {  
            // 如果读取结果成功，则将信息载入到对话框中 
        	if(data.success == "ok"){
        		alert("操作成功");
            	$("#list").trigger("reloadGrid"); 
			}
			else{
				alert('操作失败');
			}
        	 
        }  
    });
}

/*
var loadSelectedRowData = function(selectedRowId,type) { // (9) 接受选中行的id为参数  
	if(selectedRowId!=null){
		var rowData = $("#list").getRowData(selectedRowId);
		var params = {  
	    	"id" : rowData.help_category_id  
	    };
	}
	
	var actionUrl = "index.do";
	if(type=='add'){
		actionUrl += "?method=showAddView";
	}
	else if(type=='update'){
		actionUrl += "?method=showUpdateView"; 
	}
	else if(type=='del'){
		actionUrl += "?method=doDel"; 
	}
	else{
		alert('error');
	}
	 
    // 从Server读取对应ID的JSON数据  
    $.ajax( {  
        url : actionUrl,  
        data : params,  
        dataType : "json",  
        cache : false,  
        success : function(data) {  
            // 如果读取结果成功，则将信息载入到对话框中 
        	if(data.success == "ok"){
        		alert("操作成功");
            	$("#list").trigger("reloadGrid"); 
			}
			else{
				alert('操作失败');
			}
        	 
        }  
    });  
};*/  