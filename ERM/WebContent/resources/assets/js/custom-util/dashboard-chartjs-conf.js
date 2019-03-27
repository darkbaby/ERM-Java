 
//console.log($("#apiUrl").val());
let apiURL = $("#apiUrl").val() + "/chartByERAutoAPIAction";
let apiURL2 = $("#apiUrl").val() + "/chartByERManualAPIAction";
jQuery.ajax({
    type: "GET",
    url: "erm2/chartByERAutoAPIAction",
    contentType: "application/json; charset=utf-8",
    dataType: "json",
    success: function (dataApi, status, jqXHR) {
    	var chartAuto = c3.generate({
    		bindto: '#chartAuto',
    	    data: {
    	        columns: [
    	            ['remaining', dataApi.remaining],
    	            ['success',dataApi.success],
    	            ['fail', dataApi.fail],
    	        ], 
    	        type : 'donut',
    	        /*onclick: function (d, i) { console.log("onclick", d, i); },
    	        onmouseover: function (d, i) { console.log("onmouseover", d, i); },
    	        onmouseout: function (d, i) { console.log("onmouseout", d, i); },*/
    	        colors: {
        	 		remaining: '#3498db',
        	 		success: '#2ecc71',
        	 		fail: '#e90e0a',
    	        }
    	    },
    	});
    	chartAuto.legend.hide();
    	$('#legendSucChartAuto').text(dataApi.success);
    	$('#legendFailChartAuto').text(dataApi.fail);
    	$('#legendRemChartAuto').text(dataApi.remaining);
    },

    error: function (jqXHR, status) {
        // error handler
    }
});
jQuery.ajax({
    type: "GET",
    url: "chartByERManualAPIAction",
    contentType: "application/json; charset=utf-8",
    dataType: "json",
    success: function (dataApi, status, jqXHR) {
    	var chartAuto = c3.generate({
    		bindto: '#chartManual',
    	    data: {
    	        columns: [
    	            ['remaining', dataApi.remaining],
    	            ['success',dataApi.success],
    	        ], 
    	        type : 'donut',
    	        /*onclick: function (d, i) { console.log("onclick", d, i); },
    	        onmouseover: function (d, i) { console.log("onmouseover", d, i); },
    	        onmouseout: function (d, i) { console.log("onmouseout", d, i); },*/
    	        colors: {
        	 		remaining: '#3498db',
        	 		success: '#2ecc71',
    	        }
    	    },
    	});
    	chartAuto.legend.hide();
    	$('#legendSucChartManual').text(dataApi.success);
    	$('#legendRemChartManual').text(dataApi.remaining);
    },

    error: function (jqXHR, status) {
        // error handler
    }
});



 
 

  