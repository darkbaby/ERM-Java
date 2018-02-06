/**
 * 
 */

	function deleteRow(tableID,startRow,cellChkBok) {
		try {
		var table = document.getElementById(tableID);
		var rowCount = table.rows.length;
		for(var i=startRow; i<rowCount; i++) {
			var row = table.rows[i];
			var chkbox = row.cells[cellChkBok].childNodes[0];
			console.log(chkbox);
			 if(null != chkbox && true == chkbox.checked) {
				var exchangeRateDetailList = "exchangeRateManualForm.exchangeRateDetailList["+(i-2)+"]";
				  
				 var cell1  = document.getElementById(exchangeRateDetailList+".firstCurrency");
				 var cell2  = document.getElementById(exchangeRateDetailList+".pairCurrency");
				 var cell3  = document.getElementById(exchangeRateDetailList+".value");
				 var cell4  = document.getElementById(exchangeRateDetailList+".buyingRate");
				 var cell5  = document.getElementById(exchangeRateDetailList+".sellingRate");		
			 	 
			 	 var aux1 = cell1.parentNode;
			 	 var aux2 = cell2.parentNode;
			 	 var aux3 = cell3.parentNode;
			 	 var aux4 = cell4.parentNode;
			 	 var aux5 = cell5.parentNode;
				 
				 aux1.removeChild(cell1);
				 aux2.removeChild(cell2);
				 aux3.removeChild(cell3);
				 aux4.removeChild(cell4);
				 aux5.removeChild(cell5);  
				 
			    table.deleteRow(i);
				rowCount--;
				i--;
			}  
		}
		}catch(e) {
			alert(e);
		}
	}	
 		 
	function save(){
	    	$('#exchangeRateManualForm').attr('action','createExchangeRateManual.action').submit();
		    	
	}
	function removeFormList(){
		 var table = document.getElementById("rateTbl");
		 var rowCount = table.rows.length;
		 rowCount = rowCount-2;
		 var chk = false;
		 for(var i=0;i<rowCount;i++){
			 var id="exchangeRateManualForm.exchangeRateDetailList["+i+"].chk";
			 var el = document.getElementById(id);
			 if(el.checked){
				 chk = true;
				 break;
			 }
		 }
		if(chk){
		    bootbox.confirm({
		        title: "Confirm Remove!",
		        message: "Do you want to remove Reccord now? This cannot be undone.",
		        buttons: {
		            cancel: {
		                label: '<i class="fa fa-times"></i> Cancel'
		            },
		            confirm: {
		                label: '<i class="fa fa-check"></i> Confirm'
		            }
		        },
		        callback: function (result) {
		            if(result){
		            	$('#exchangeRateManualForm')
		            		.attr('action','removeExchangeRateManualForm.action')
		            		.submit();
		            }
		        }
		    });
		}else{
			var dialog = bootbox.dialog({
    		    message: '<h4 class="has-error"><span class="glyphicon glyphicon-warning-sign"></span>Plese select OPT </h4>',
    		    closeButton:true
    		});
		}
	}
	function removeFile(id){
				    bootbox.confirm({
				        title: "Confirm Remove!",
				        message: "Do you want to remove File now? This cannot be undone.",
				        buttons: {
				            cancel: {
				                label: '<i class="fa fa-times"></i> Cancel'
				            },
				            confirm: {
				                label: '<i class="fa fa-check"></i> Confirm'
				            }
				        },
				        callback: function (result) {
				        	$('#receiptsForm').append('<input type="hidden" name="myparam " value="abc" />');
				            if(result){
				            	var pararm = "<input type='hidden' name='parm' value='"+id+"' />";
				            	$('#exchangeRateManualForm').append(pararm);
				            	$('#exchangeRateManualForm')
				            		.attr('action','removeFileUploadERByManual.action')
				            		.submit();
				            }
				        }
				    });

	}
	function remove(){
		    bootbox.confirm({
		        title: "Confirm Remove ?",
		        message: "Do you want to remove Exchange Rate now? This cannot be undone.",
		        buttons: {
		            cancel: {
		                label: '<i class="fa fa-times"></i> Cancel'
		            },
		            confirm: {
		                label: '<i class="fa fa-check"></i> Confirm'
		            }
		        },
		        callback: function (result) {
		            if(result){
		            	$(location).attr('href', 'removeExchangeRateByManual.action');
		            }
		        }
		    });

	}
		// Fake file upload
	function browseFile(filesinputupload,fakefileinputname,fakefileinputnametext,removeBtn){
		  	document.getElementById(filesinputupload).click();
		    document.getElementById(filesinputupload).addEventListener('change', function() {
		    	var maxSize = 1048576; //Byte
		    	if(this.files[0].size > maxSize){
		    		document.getElementById(filesinputupload).value = null;
		    		"limit of 1 MB/file.";
		    		//bootbox.alert("limit of 1 MB/file.");
		    		var dialog = bootbox.dialog({
		    		    message: '<h4 class="has-error"><span class="glyphicon glyphicon-warning-sign"></span> limit of 1 MB/file.</h4>',
		    		    closeButton:true
		    		});
		    	}else{
		    		document.getElementById(fakefileinputname).value = this.value;
		    		document.getElementById(removeBtn).style.display = 'inline';
		    	}
		  	
		  	 
		  });
	}
		

		
	