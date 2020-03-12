/**
 * Frontend script file for the todolist-application
 * setup listeners and communicate with the rest-api
 */
var host = window.location.host;
var protocol = window.location.protocol;
var path = window.location.pathname;

var _aTodoEntries;

init();

function init() {
	refreshList();
}

/**
 * refreshes the items via a get request on the todoentries
 * @returns
 */
function refreshList() {
	var that = this;
	_aTodoEntries = [];
	
	$.get("/todoentries", function(aData) {	
		_aTodoEntries = aData;
		
		var oList = document.getElementById("idEntryList");
		oList.innerHTML = "";
		
		for(var i = 0; i < _aTodoEntries.length;  i++) {		
			var oListItem = _createListItem(_aTodoEntries[i]);
			oList.appendChild(oListItem);			
		}
		
	});
	
}

/**
 * triggered when the add button is pressed:
 * makes a new http post with the data in the text and title field
 * triggers the refresh function
 * @returns
 */
function addTodoentry() {
	var sTitle = document.getElementById("idTitleInput").value;
	var sText = document.getElementById("idTextInput").value;
	
	$.post("/todoentries", {title: sTitle, text: sText }, function(bSuccess) {
		if(bSuccess) refreshList();
		else alert("Entry not created!");
	});
}

/**
 * triggered when the delete button is pressed:
 * makes a http delete on the entity for the todo entries
 * @returns
 */
function deleteTodoentry(sEntryId) {
	
	$.ajax({
	    url: "/todoentries(" + sEntryId + ")",
	    method: "DELETE",
	    success: function(result) {
	    	refreshList();
	    },
	    error: function(request,msg,error) {
	        // handle failure
	    }
	});
}

/**
 * triggered when the done button is pressed:
 * makes a http PUT on the entity to set it to done=true
 * @returns
 */
function setEntryToDone(sEntryId) {
	
	$.ajax({
	    url: "/todoentries(" + sEntryId + ")",
	    method: "PUT",
	    data: {
	    	done: true
	    },
	    success: function(result) {
	    	refreshList();
	    },
	    error: function(request,msg,error) {
	        // handle failure
	    }
	});
}

/**
 * Helper function for setting up the listitem
 */
function _createListItem(oEntry) {
	oListItem = document.createElement("li");
	var sText = oEntry.title + " : " + oEntry.text;
	oListItem.appendChild(document.createTextNode(sText));
	
	//If the entry is marked as "done" set the checked class
	if(oEntry.done) oListItem.classList.add("checked");
	else  oListItem.classList.remove("checked");
	
	var that = this;
	
	//add the done Button
	var oDoneButton = document.createElement("span");
	oDoneButton.innerHTML = "Done";
	oDoneButton.classList.add("listBtn");
	oDoneButton.classList.add("verticalAlignButton");
	
	//add listener function with param
	oDoneButton.onclick = function() {
		that.setEntryToDone(oEntry.id);
	}
	
	//add the delete Button
	var oDeleteButton = document.createElement("span");
	oDeleteButton.innerHTML = "Delete";
	oDeleteButton.classList.add("listBtn");
	oDeleteButton.classList.add("verticalAlignButton");
	
	
	//add listener function with param
	oDeleteButton.onclick = function() {
		that.deleteTodoentry(oEntry.id);
	}
	
	oListItem.appendChild(oDeleteButton);
	if(!oEntry.done) oListItem.appendChild(oDoneButton);		
	
	//set attributes
	oListItem.setAttribute("id", oEntry.id);
	oListItem.setAttribute("title", oEntry.title);
	oListItem.setAttribute("text", oEntry.text);
	oListItem.setAttribute("done", oEntry.done);
	
	return oListItem;
}






