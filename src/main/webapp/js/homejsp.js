$( document ).ready(function() {
   $("#admissionFee").val("");
   $("#tutionFee").val("");
   $("#transportationFee").val("");
   $("#hostelFee").val("");
   $("#stationaryFee").val("");

$("#cls-form").validate(
		{
			errorElement: 'span',
		    errorClass: 'has-error',
			rules:
			{
				boardId: {required: true},
			    mediumId: {required: true},
				className: {required: true},
			    section: {required: true},
			    admissionFee: {required: true, number: true},
			    tutionFee: {required: true, number: true},
			    transportationFee: {required: true, number: true},
			    hostelFee: {required: true, number: true},
			    stationaryFee: {required: true, number: true},
			},
			messages:
			{
				boardId: {required: 'Choose Board'},
				mediumId: {required: 'Choose Medium'},
				className: {required: 'Choose Class'},
				section: {required: 'Choose Section'},
				admissionFee: {required: 'Admission Fee', number: 'Numeric Characters'},
				tutionFee: {required: 'Tuition Fee', number: 'Numeric Characters'},
				transportationFee: {required: 'Transportation Fee', number: 'Numeric Characters'},
				hostelFee: {required: 'Hostel Fee', number: 'Numeric Characters'},
				stationaryFee: {required: 'Stationary Fee', number: 'Numeric Characters'},
			},
			errorPlacement: function(error, element)
			{
			      if(element.attr("name") == "className")
			        error.insertAfter(".className_error").css("color", "red");
			      else if(element.attr("name") == "boardId")
			        error.insertAfter(".boardId_error").css("color", "red"); 
			       else if(element.attr("name") == "mediumId")
			        error.insertAfter(".mediumId_error").css("color", "red");
			       else if(element.attr("name") == "section")
			        error.insertAfter(".section_error").css("color", "red");
			      
			      else
			        error.insertAfter(element);
			}	
		});

			  $('#cancel').click(function () {
			    $("#cls-form").validate().resetForm();
			    $("#cls-form").removeClass("has-error");
			    $("#id").val(0);
			    $("#boardId").val('');
			    $("#mediumId").val('');
			    $("#className").val('');
			    $("#section").val('');
			    $("#admissionFee").val('');
			    $("#tutionFee").val('');
			    $("#transportationFee").val('');
			    $("#hostelFee").val('');
			    $("#stationaryFee").val('');
			    $("#cls-form").addClass('form-horizontal');
			    $("#submitId").val("Submit");
			    $("#headId").text("Class Fee");
			  });
});



function displayTable(listOrders) {
			$("#basicExample tr td").remove();
			$("#basicExample td").remove();
			serviceUnitArray = {};
			$
					.each(
							listOrders,
							function(i, orderObj) {
								serviceUnitArray[orderObj.classId] = orderObj;
								var id = '"' + orderObj.classId + '"';
								var tblRow = "<tr align='center' role='row' class='odd'>" + "<td'><a  id='"
										+ orderObj.classId
										+ "' href='javascript:forOrderDetails("
										+ id
										+ ")' style='font-color:red'>"
										+ orderObj.orderId
										+ "</a></td>"
										+ "<td  title='"+orderObj.bordName+"'>"
										+ orderObj.bordName
										+ "</td>"
										+ "<td class='hidden-sm hidden-xs' title='"+orderObj.medium+"'>"
										+ orderObj.medium
										+ "</td>"
										+ "<td title='"+orderObj.cname+"'>"
										+ orderObj.cname
										+ "</td>"
										+ "<td class='hidden-sm hidden-xs' title='"+orderObj.sname+"' >"
										+ orderObj.sname
										+ "<td class='hidden-sm hidden-xs' title='"+orderObj.fee+"' >"
										+ orderObj.admissionFee
										+ "/-</td>"
										+ "<td  class='hidden-sm hidden-xs'>"
										+ orderObj.tutionFee
										+  "/-</td>"
										+ "<td  class='hidden-sm hidden-xs'>"
										+ orderObj.transportationFee
										+  "/-</td>"
										+ "<td  class='hidden-sm hidden-xs'>"
										+ orderObj.hostelFee
										+  "/-</td>"
										+ "<td  class='hidden-sm hidden-xs'>"
										+ orderObj.stationaryFee
										+  "/-</td>"
										+ "<td class='hidden-sm hidden-xs' title='"+orderObj.fee+"' >"
										+ orderObj.fee
										+ "/-</td>"
										+ "<td>"
										+ '<a href="javascript:void(0)" onclick=editPack('
										+ orderObj.classId + ')'
										+ '  ><i style="color: green;" class="fa fa-edit"></i></a>' + '&nbsp; | &nbsp;'
										+ '<a style="color: red;" href="javascript:void(0)" onclick=deleteClass('
										+ orderObj.classId + ')'
										+ '  ><i class="fa fa-trash-o"></i></a>' + '</td>'
									
										+ '</tr>';
								$(tblRow).appendTo("#basicExample");
							});
	}
	function editPack(id) {
		var transactionId = serviceUnitArray[id].classId;
		 $("#cls-form").validate().resetForm();
		$("#id").val(serviceUnitArray[id].classId)
		$('#boardId').val(serviceUnitArray[id].borderId);
		$('#boardId').trigger("chosen:updated");
		classNameFilter();
		$('#className').val(serviceUnitArray[id].className);
		$('#className').trigger("chosen:updated");
		sectionFilter();
		$('#section').val(serviceUnitArray[id].section);
		$('#section').trigger("chosen:updated");
		mediumFilter();
		$('#mediumId').val(serviceUnitArray[id].mediamId);
		$('#mediumId').trigger("chosen:updated");
		
		$('#admissionFee').val(serviceUnitArray[id].admissionFee);
		$('#tutionFee').val(serviceUnitArray[id].tutionFee);
		$('#transportationFee').val(serviceUnitArray[id].transportationFee);
		$('#hostelFee').val(serviceUnitArray[id].hostelFee);
		$('#stationaryFee').val(serviceUnitArray[id].stationaryFee);
		$("#submitId").val("Update");
		$("#headId").text("Edit Class");
		$(window).scrollTop($('#boardId').offset().top);
	}
	
	
	function deleteClass(id){
		var classId = id;
		var count = 0;
		var checkstr =  confirm('Are you sure you want to delete this?');
		if(checkstr == true){
		  // do your code
//			$('#loadAjax').show();
		  
		  $.ajax({
					type : "POST",
					url : "deleteClass.json",
					data : "classId=" + classId ,
					success : function(response) {
						displayTable(response);
//						$('#loadAjax').hide();
						window.location.href='HomeControl1';
					},
					error : function(e) {
//						$('#loadAjax').hide();
					}
				});
		
		/* 	$.ajax({
				type : "POST",
				url : "deleteClass.htm",
				data : "classId=" + classId ,
				dataType : "json",
				success : function(response) {
					alert(response);
					if (response != "") {
						displayTable(response);
					}
				
				},
				error : function(e) {
				}
			}); */
			
		}else{
		return false;
		}
	}
	
	
	function classNameFilter(){
		var boardId = $("#boardId").val();
		if(boardId.length !=0){
			$('#loadAjax').show();
		$.ajax({
			type : "POST",
			url : "getClassNameFilter.json",
			data : "boardId=" + boardId,
			dataType : "json",
			async:false,
			success : function(response) {
				 /* alert(response); */  
				var optionsForClass = "";
				optionsForClass = $("#className").empty();
				optionsForClass.append(new Option("-- Choose Class --", ""));
				$.each(response, function(i, tests) {
					var id=tests.id;
					var className=tests.className;
					optionsForClass.append(new Option(className, id));
				});
				$('#loadAjax').hide();
				$('#className').trigger("chosen:updated");
			},
			error : function(e) {
				$('#loadAjax').hide();
			},
			statusCode : {
				406 : function() {
					$('#loadAjax').hide();
			
				}
			}
		});
		$('#loadAjax').hide();

		}
	}
	
	function sectionFilter(){
		var boardId = $("#boardId").val();
		var classId = $("#className").val();
		if(boardId.length !=0 && classId.length != 0){
			$('#loadAjax').show();
		$.ajax({
			type : "POST",
			url : "getSectionFilter.json",
			data : "boardId=" + boardId+"&classId="+classId,
			dataType : "json",
			async:false,
			success : function(response) {
				 /* alert(response); */  
				var optionsForClass = "";
				optionsForClass = $("#section").empty();
				optionsForClass.append(new Option("-- Choose Section --", ""));
				$.each(response, function(i, tests) {
					var id=tests.id;
					var sectionName=tests.sectionName;
					optionsForClass.append(new Option(sectionName, id));
				});
				$('#loadAjax').hide();
				$('#section').trigger("chosen:updated");
			},
			error : function(e) {
				$('#loadAjax').hide();
			},
			statusCode : {
				406 : function() {
					$('#loadAjax').hide();
			
				}
			}
		});
		$('#loadAjax').hide();
		}
	} 
	
	function mediumFilter() {
		var boardId = $("#boardId").val();
		var classId = $("#className").val();
		var sectionId = $("#section").val();
		
			$.ajax({
				type : "POST",
				url : "getMediumFilter.json",
				data : "boardId=" + boardId + "&classId=" + classId + "&sectionId=" + sectionId,
				dataType : "json",
				async:false,
				success : function(response) {
					/* alert(response); */
					var optionsForClass = "";
					optionsForClass = $("#mediumId").empty();
					optionsForClass
							.append(new Option("-- Choose Medium --", ""));
					$.each(response, function(i, tests) {
						var id = tests.id;
						var mediumName = tests.mediumName;
						optionsForClass.append(new Option(mediumName, id));
					});
				}
			});
	}