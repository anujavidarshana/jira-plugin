
AJS.$(document).ready(function() {

	AJS.$('#checkSuccess').click(function() {
	  AJS.$.ajax({
			type: "GET",
			dataType: "html",
			url: contextPath + "/secure/admin/jira/monitoringAndTroubleshooting!clearcache.jspa",
			cache: false,
			error: function(response, textStatus, errorThrown) {
				alert("Issue is not created " + response);
			} ,
			success: function(response, textStatus) {
			    alert("Issue is created. Issue summary: " +response);
			}
		});
	});

//	AJS.$('#checkError').click(function() {
//    	  AJS.$.ajax({
//    			type: "GET",
//    			dataType: "html",
//    			url: contextPath + "/secure/admin/jira/checksuccess.jspa",
//    			cache: false,
//    			error: function(response, textStatus, errorThrown) {
//                	alert("#checkError responseText :: "+response.responseText);
//    			} ,
//    			success: function(response, textStatus) {
//    			    alert("Success #checkError: message :: "+response);
//    			}
//    		});
//    	});

	/*
     * Simple html encode function to prevent XSS attacks.
     */
    function encodeHtml(toEncode) {
    	return $('<div/>').text(toEncode).html();
    }

});

