<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@page pageEncoding="UTF-8"%>
<%@page errorPage="error.jsp"%>
<%@page import="uk.gov.ofwat.fountain.domain.Model, uk.gov.ofwat.fountain.api.ModelService" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ofwat" uri="http://www.ofwat.gov.uk" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/> 
	<title>Fountain: Create New Data Stream</title>
	<!-- CSS Reset sheet, minified version, written by yahoo -->
	<link rel="stylesheet" type="text/css" href="/Fountain/css/reset-fonts-grids.css"/>
	<!-- 960.gs grid sheet -->
	<link rel="stylesheet" type="text/css" href="/Fountain/css/960grid.css"/>
	<link type="text/css" rel="stylesheet" href="/Fountain/css/layout.css" media="screen"/>
	<!-- <link type="text/css" rel="stylesheet" href="/Fountain/css/bubble.css" media="screen"/> -->
	<link rel="stylesheet" href="<spring:theme code='colourscheme'/>" type="text/css" media="screen"/>
	<link href="/Fountain/css/jquery.pnotify.default.css" media="all" rel="stylesheet" type="text/css" />
	<link type="text/css" rel="stylesheet" href="/Fountain/css/bootstrap.css" media="screen"/>
</head>
<body>
<div class="bannerContent"></div>
<p></p>
<div class="container_12">
<div>
	<div id="reports" class="grid_12">
	<form id="createAgenda" class="form-horizontal">
	<fieldset>
	
	<!-- Form Name -->
	<legend>Create New Data Stream</legend>
	
	<!-- Text input-->
	<div class="control-group">
	  <label class="control-label" for="lblName">Name:</label>
	  <div class="controls">
	    <input id="agendaName" name="agendaName" type="text" placeholder="Data Stream Name" class="input-large" required>
	    <p class="help-block"></p>
	  </div>
	</div>
	
	<!-- Text input-->
	<div class="control-group">
	  <label class="control-label" for="lblName">Code:</label>
	  <div class="controls">
	    <input id="agendaCode" name="agendaCode" type="text" placeholder="Data Stream Code" class="input-large" required>
	    <p class="help-block"></p>
	  </div>
	</div>
	
	<!-- Textarea -->
	<div class="control-group">
	  <label class="control-label" for="agendaDescription">Description:</label>
	  <div class="controls">                     
	    <textarea id="agendaDescription" name="agendaDescription"></textarea>
	  </div>
	</div>
	
	<!-- Button (Double) -->
	<div class="control-group">
	  <label class="control-label" for="btnSave"></label>
	  <div class="controls">
	    <button id="btnSave" name="btnSave" type="submit" class="btn btn-success">Create</button>
	    <button id="btnCancel" name="btnCancel" type="button" class="btn btn-default">Cancel</button>
	  </div>
	</div>
	
	</fieldset>
	</form>

	<div id="modalAgendaCreate" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true">
		<div class="modal-header">
			<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button> -->
			<h3 id="myModalLabel">Your data stream is being created</h3>
		</div>
		<div class="modal-body">
			<div>
				Your data stream is currently being created.
			</div> 
		</div>
		<div class="modal-footer">
		 <!-- <button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button> -->
		 <button id="btnModalOk" class="btn btn-success">Ok</button>
		</div>
	</div>	

	<script type="text/javascript" src="../../../js/json2.js"></script>
	<script type="text/javascript" src="../../../js/handlebars/handlebars.js"></script>	
	<script type="text/javascript" src="../../../js/ofwat/ofwat.js"></script>
	<script type="text/javascript" src="../../../js/ofwat/rest.js"></script>
	<script type="text/javascript" src="../../../js/dojo/dojo.js.uncompressed.js" ></script>
	<script type="text/javascript" src="../../../js/ofwat/listUtils.js"></script> 
	<script type="text/javascript" src="../../../js/ofwat/admin.js"></script>
    <script type="text/javascript" src="../../../js/jquery/jquery-1.10.2.js"></script>
    <script type="text/javascript" src="../../../js/bootstrap.js"></script>	
    <script type="text/javascript" src="../../../js/jqBootstrapValidation.js"></script>
	<script type="text/javascript" src="../../../js/jquery.pnotify.min.js"></script>		
    <script type="text/javascript" src="../../../js/ofwat/createAgenda.js"></script>
    <script type="text/javascript" src="../../../js/moment.js"></script>   

    <script type="text/javascript">
		Handlebars.registerHelper('inRole', ofwat.checkUserInRole); 
		ofwat.loadTemplate(dojo.query(".bannerContent")[0], ofwat.template.banner);
    </script>

	<jsp:include page="../../footer.jsp" />	
	</div>	
</div>
</div>
	
	<script type="text/javascript">
		dojo.addOnLoad(ofwat.createAgenda.onLoad());
		$(function () { $("input,select,textarea,text").not("[type=submit]").jqBootstrapValidation(); } );
	</script>
	
</body>
</html>