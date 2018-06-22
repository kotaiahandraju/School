$( document ).ready(function() {
    $("#fee").val("");

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
			    fee: {required: true, number: true},
			},
			messages:
			{
				boardId: {required: 'Choose Board'},
				mediumId: {required: 'Choose Medium'},
				className: {required: 'Choose Class'},
				section: {required: 'Choose Section'},
				fee: {required: 'Enter Fee Amount', number: 'Enter Numeric Characters'},
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
			       else if(element.attr("name") == "fee")
				        error.insertAfter(".fee_error").css("color", "red");
			      else
			        error.insertAfter(element);
			}	
		});

			  $('#cancel').click(function () {
			    $("#cls-form").validate().resetForm();
			    $("#cls-form").removeClass("has-error");
			    $("#boardId").val('');
			    $("#mediumId").val('');
			    $("#classId").val('');
			    $("#section").val('');
			    $("#fee").val('');
			    $("#cls-form").addClass('form-horizontal');
			  });
			  
			  
			  
			  
			  
			  
			  $('#examTypeId').on('change' ,function(){
					
					$("#examPatternTableDiv").show();
					var boardId = $("#boardId").val();
					var classId = $("#classId").val();
					var sectionId = $("#sectionId").val();
					var mediumId = $("#medium").val();
					var examTypeId = $("#examTypeId").val();
					
					
					$.ajax({
						type : "POST",
						url : "examPatternList.json",
						data : "boardId=" + boardId + "&classId=" + classId+ "&sectionId=" + sectionId + "&mediumId=" + mediumId + "&examTypeId="+ examTypeId,
						dataType : "json",
						async:false,
						success : function(response) {
							dynamicInputfields(response)
							/*$.each(response, function(i, tests) {
								var id=tests.subjectId;

								var subjectName=tests.subjectName;
								var maxMarks=tests.maxMarks;
								var subjectId = tests.subjectId;
								$('#subjectDiv').append(
										'<div class="col-md-2">'
										+'<input path="subjectId" type="hidden" class="form-control" tabindex="1"	value="'+subjectId+'"/>'
										+'<label for="inputEmail3" class="col-sm-4 control-label">'+ subjectName+':' 
										+ ' </label>' 
										+'<br>'
										+'<input path="studentMarks" type="number" class="form-control" tabindex="1"	max="'+maxMarks+'" placeholder="Enter Maximum Marks"/>'
										+'<input path="subjectMaxMarks" type="hidden" class="form-control" tabindex="1"	value="'+maxMarks+'"/>'
										+'</div>');
									
								$('#subjectDiv').append();
								//optionsForClass.append(new Option(className, id));
							});
							$('#loadAjax').hide();
							//$('#subjectId').trigger("chosen:updated");
							
							
							
						*/	
						}
						});
			  });
			  
			  
});


function dynamicInputfields(listOfSubjects){
	
	$('#subjectDiv').empty();
	$.each(listOfSubjects, function(i, tests) {

		var subjectName=tests.subjectName;
		var maxMarks=tests.maxMarks;
		var subId = tests.subjectId;
		$('#subjectDiv').append(
				'<div class="col-md-2">'
				+'<label for="inputEmail3" class="col-sm-4 control-label">'+ subjectName+':' 
				+ ' </label>' 
				+'<br>'
				+'<input id="subjectId" name="subjectId" type="hidden" class="form-control" tabindex="1"	value="'+subId+'"/>'
				+'<input id="studentMarks" name="studentMarks" type="number" class="form-control" tabindex="1" min="'+0+'"	max="'+maxMarks+'" placeholder="Enter Maximum Marks"/>'
				+'<input id="subjectMaxMarks" name="subjectMaxMarks" type="hidden" class="form-control" tabindex="1"	min="'+0+'" value="'+maxMarks+'"/>'
				+'</div>');
			
		$('#subjectDiv').append();
		//optionsForClass.append(new Option(className, id));
	});
	$('#loadAjax').hide();
	//$('#subjectId').trigger("chosen:updated");
	
}


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
		$("#id").val(serviceUnitArray[id].classId)
		$('#boardId').val(serviceUnitArray[id].borderId);
		$('#boardId').trigger("chosen:updated");
		$('#className').val(serviceUnitArray[id].className);
		$('#className').trigger("chosen:updated");
		$('#mediumId').val(serviceUnitArray[id].mediamId);
		$('#mediumId').trigger("chosen:updated");
		$('#section').val(serviceUnitArray[id].section);
		$('#section').trigger("chosen:updated");
		$('#fee').val(serviceUnitArray[id].fee);
		$("#submitId").val("Update");
		$("#headId").text("Edit Class");
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
	
	
	function classNameFilter(id){
		var boardId = $("#boardId").val();
		if(boardId.length !=0){
			$('#loadAjax').show();
		$.ajax({
			type : "POST",
			url : "getClassNameFilter1.json",
			data : "boardId=" + boardId,
			dataType : "json",
			async:false,
			success : function(response) {
				 /* alert(response); */  
				var optionsForClass = "";
				optionsForClass = $("#classId").empty();
				optionsForClass.append(new Option("-- Choose Class --", ""));
				$.each(response, function(i, tests) {
					var id=tests.id;
					var className=tests.className;
					optionsForClass.append(new Option(className, id));
				});
				$('#loadAjax').hide();
				$('#classId').trigger("chosen:updated");
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
	function sectionFilter() {
		var boardId = $("#boardId").val();
		var classId = $("#classId").val();
		if (boardId.length != 0 ) {
			$.ajax({
				type : "POST",
				url : "getSectionFilter.json",
				data : "boardId=" + boardId + "&classId=" + classId,
				dataType : "json",
				async:false,
				success : function(response) {
					/* alert(response); */
					var optionsForClass = "";
					optionsForClass = $("#sectionId").empty();
					optionsForClass.append(new Option("-- Choose Section --",""));
					$.each(response, function(i, tests) {
						var id = tests.id;
						var sectionName = tests.sectionName;
						optionsForClass.append(new Option(sectionName, id));
					});
				}
			});
		}
	}
	
	function mediumFilter() {
		var boardId = $("#boardId").val();
		var classId = $("#classId").val();
		var sectionId = $("#sectionId").val();
		if (boardId.length != 0) {
			$.ajax({
				type : "POST",
				url : "getMediumFilter.json",
				data : "boardId=" + boardId + "&classId=" + classId
						+ "&sectionId=" + sectionId,
				dataType : "json",
				async:false,
				success : function(response) {
					/* alert(response); */
					var optionsForClass = "";
					optionsForClass = $("#medium").empty();
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
	}
	
	function studentFilterDropdown() {
		var boardId = $("#boardId").val();
		var classId = $("#classId").val();
		var sectionId = $("#sectionId").val();
		var mediumId = $("#medium").val();
		
			$.ajax({
				type : "POST",
				url : "studentFilterDropdown.json",
				data : "boardId=" + boardId + "&classId=" + classId
						+ "&sectionId=" + sectionId + "&mediumId=" + mediumId,
				dataType : "json",
				async:false,
				success : function(response) {
					// 				 alert(response);  
					var optionsForClass = "";
					optionsForClass = $("#studentId").empty();
					optionsForClass.append(new Option("-- Choose Student --",
							""));
					$.each(response, function(i, tests) {
						var studentId = tests.studentId;
						var studentName = tests.studentName;
						optionsForClass.append(new Option(studentName,
								studentId));
					});
					
				}
			});
	}
	
	function getSubjects(){
		var boardId = $("#boardId").val();
		var classId = $("#classId").val();
		if(boardId.length !=0 && classId.length != 0){
			//$('#loadAjax').show();
		$.ajax({
			type : "POST",
			url : "getSubjectFilter.json",
			data : "boardId="+boardId+"&classId="+classId,
			dataType : "json",
			async:false,
			success : function(response) {
				console.log(response);
				 /* alert(response); */  
				var optionsForClass = "";
				optionsForClass = $("#subjectId").empty();
				//optionsForClass.append(new Option("-- Choose Subject --", ""));
				$.each(response, function(i, tests) {
					var id=tests.subjectId;

					var subjectName=tests.subjectName;
					$('#subjectDiv').append(
							'<div class="col-md-2">'
							+'<label for="inputEmail3" class="col-sm-4 control-label">'+ subjectName+':' 
							+ ' </label>' 
							/*+'<br>'*/
							+'<input path=" '+ id+'" type="number" class="form-control" tabindex="1"	placeholder="Enter Maximum Marks"/>'
							+'</div>');
						
					$('#subjectDiv').append();
					//optionsForClass.append(new Option(className, id));
				});
				$('#loadAjax').hide();
				$('#subjectId').trigger("chosen:updated");
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
		//$('#loadAjax').hide();

		
	}
	}
	
	
	/*function searchStudent(){
		searchbtn=123;
		var studentName = $("#name").val();
		var boardName = $("#boardName").val();
		var admissionNum = $('#admissionNum').val();
		var medium = $('#medium').val();
		var caste = $('#caste').val();
		var email = $('#email').val();
		var studentName = $('#name').val();
		var className = $('#className').val();
		var section = $('#section').val();
		var mobile = $("#mobile").val();
		$('#loadAjax').show();
		$.ajax({
			type : "POST",
			url : "getStudetnDetails.json",
			dataType : "json",
			data : "boardName=" + boardName+"&admissionNum="+admissionNum +"&rollNum="+rollNum +"&medium="+medium+"&caste="+caste+"&email="+email+"&studentName="+studentName+"&className="+className+"&section="+section+"&mobile="+mobile+"&studentName="+studentName,
			success : function(response) {
// 				 alert(response); 
				 displayTable(response);
				 $('#loadAjax').hide();
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
	*/