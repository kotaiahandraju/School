<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

	<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-2.1.3.min.js"></script>
	<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.0/jquery.validate.min.js"></script>
	<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.0/additional-methods.min.js"></script>
	<script type="text/javascript" src="js/boardCreation.js"></script>
	<style>
	.btnsm {
	margin:0.5px;
	
	}
	
	</style>
		<!-- Dashboard Wrapper starts -->
		<div class="dashboard-wrapper">

			<!-- Top Bar starts -->
			<div class="top-bar">
				<div class="page-title" id="headId">Exam Marks</div>
			</div>
			<!-- Top Bar ends -->

			<!-- Main Container starts -->
			<div class="main-container">

				<!-- Container fluid Starts -->
				<div class="container-fluid">

					<!-- Spacer starts -->
					<div class="spacer">
					
						<!-- Row Starts -->
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
								<div class="blog" style="border: 1px solid;">
									<!-- <div class="blog-header">
										<h5 class="blog-title">Class Creation</h5>
									</div> -->
									<div class="panel-heading heading">
									<div class="heading" style="color: black; font-size: 14px;">Filters</div>
									</div>
									<div class="blog-body1">
<%-- 									<%${message} %> --%>
			
									<form:form action="addBoard.htm" commandName="examMarkscmd" method="post" id="cls-form" class="form-horizontal">
											<div class="form-group row">
											    <span class="col-md-4"><label class="control-label">Class:</label><span >
											    <select class="padd">
											    	
														<option value="" > -- Select Class -- </option>
														<c:forEach var="className" items="${classNames}">
															<option value="">${className.value}</option>
													 	</c:forEach>
														    
											    </select>
											    </span>
											     </span>
											    <span class="col-md-4"><label class="control-label">Section:</label><span >
											    <select class="padd">
											    	 
													    <option value="" > -- Select Section -- </option>
													    <c:forEach var="sections" items="${sections}">
													    	<option value="">${sections.value} </option>
													  	</c:forEach>
												 </select>
											    </span>
											     </span>
											    <span class="col-md-4"><label class="control-label">Exam Type:</label><span >
											    <select class="padd">
												    <option value="0" selected="selected" label="FA-1"></option>
												    <option value="1" selected="selected" label="FA-2"></option>
												    <option value="2" selected="selected" label="FA-3"></option>
												    <option value="3" selected="selected" label="FA-4"></option>
												    <option value="4" selected="selected" label="SA-1"></option>
												    <option value="5" selected="selected" label="SA-2"></option>
												    <option value="6" selected="selected" label="SA-3"></option>
											    </select>
											    </span>
											    </span>
											</div>
<%-- 											<form:hidden path="id"/> --%>
												
												<div class="form-group">
													<div class="col-sm-8 col-sm-offset-4">
													<%
														String message = null;
														message=(String)session.getAttribute("message");
												        if(message!=null)
												        {
															out.println("<span class='animated fadeIn' style='animation-iteration-count: 7;animation-duration: 1s;color: red;'>"+message+"</span>");
															session.setAttribute("message", null);
														}
											        %>
													</div>
												</div>
									</form:form>
									</div>
									
									</div>
								</div>
							</div>
						</div>
						<!-- Row Ends -->
						
						<!-- Row Starts -->
						<div class="row gutter">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
								<div class="blog">
									<div class="blog-header">
									<div class="col-md-12">
									<div class="col-md-4">
										<h4>Exam Marks</h4></div>
								
									<div class="col-md-8" align="right" >
									<span class="btn btn-primary btnsm"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span> Save </span>
									<span  class="btn btn-warning btnsm" ><span class="glyphicon glyphicon-remove" aria-hidden="true"></span> Cancel </span>
									<span class="btn btn-success btnsm"><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span> SMS Marks</span></div>
									
									</div>
									<div class="blog-body">
										<div class="table-responsive">
										<div class="panel-body"><table class="table table-striped table-selectable table-bordered">
										<tbody><tr><th><input type="checkbox"  name="filter-checkbox" value="" style="float:left" ></th><th><span class="cursor-pointer">Roll No</span></th>
										<th><span  class="cursor-pointer">Full Name </span></th><th >Contact Number</th><th class="centered">Total (max0)</th><th class="centered">GPA</th></tr>
										<tr >
										<td><input type="checkbox" ></td>
										<td ></td>
										<td><a   href="#398/1/">BALA HARSHA AVVARU</a></td>
										<td >9550860252</td>
										<td><div class="row"><span class="col-xs-6"><span >0</span> </span><span class="col-xs-6 right-align"></div></td>
				                        <td><div class="row"><label class="col-ms-6 control-label" title="GPA"></label></div></td></tr><tr ><td><input type="checkbox" ></td><td ></td><td><a   href="#">HARSHA DODDA</a></td><td >9603858746</td><td><div class="row"><span class="col-xs-6"><span >0</span> </span><span class="col-xs-6 right-align">
				                            
				                        </div></td><td><div class="row"><label class="col-ms-6 control-label" title="GPA"></label></div></td></tr><tr ><td><input type="checkbox" ></td><td ></td><td><a   href="#">Jahnavi Punna</a></td><td >7674851388</td><td><div class="row"><span class="col-xs-6"><span >0</span> </span><span class="col-xs-6 right-align">
				                            
				                        </div></td><td><div class="row"><label class="col-ms-6 control-label" title="GPA"></label></div></td></tr><tr ><td><input type="checkbox" ></td><td ></td><td><a   href="#">Nakshatra Kalakoti</a></td><td >9703044060</td><td><div class="row"><span class="col-xs-6"><span >0</span> </span><span class="col-xs-6 right-align">
				                            
				                        </div></td><td><div class="row"><label class="col-ms-6 control-label" title="GPA"></label></div></td></tr><tr ><td><input type="checkbox" ></td><td ></td><td><a   href="#">Moksha Vaka</a></td><td >9948381488</td><td><div class="row"><span class="col-xs-6"><span >0</span> </span><span class="col-xs-6 right-align">
				                            
                        </div></td><td><div class="row"><label class="col-ms-6 control-label" title="GPA"></label></div></td></tr><tr ><td><input type="checkbox" ></td><td ></td><td><a   href="#">Chaitanya Naga Jyothi. Damarla</a></td><td >7036430279</td><td><div class="row"><span class="col-xs-6"><span >0</span> </span><span class="col-xs-6 right-align">
                            
                         <td></td>
                            
                       </tr></tbody></table></div>	
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- Row Ends -->
						
					</div>
					<!-- Spacer ends -->

				</div>
				<!-- Container fluid ends -->

			</div>
			<!-- Main Container ends -->

		</div>
		<!-- Dashboard Wrapper ends -->

<!-- <script src="http://code.jquery.com/jquery-1.10.2.js"></script> -->
<!-- <script src="http://code.jquery.com/ui/1.11.2/jquery-ui.js"></script> -->
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script type="text/javascript">

var listOrders1 = ${allOrders1};
if (listOrders1 != "") {
	displayTable(listOrders1);
}else{
	$("#emptyMessageId").val("No Results Found");
}	
</script>
