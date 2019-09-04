<%--
 * list.jsp
 *
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="quolet/administrator/edit.do" modelAttribute="quolet"
	class="form-horizontal" method="post">
	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<acme:select items="${conferences}" itemLabel="title" code="quolet.conference" path="conference"/>
	<acme:textbox code="quolet.x1" path="x1" />
	<acme:textbox code="quolet.x2" path="x2" />
	<acme:textbox code="quolet.x3" path="x3" />
	<spring:message code="quolet.mode" />
	<form:select path="mode">
		<form:option value="DRAFT" />
		<form:option value="FINAL" />
	</form:select>
	<br/>
	<acme:submit name="save" code="quolet.save" />
	<jstl:if test="${quolet.id!=0}">
	<acme:submit name="delete" code="quolet.delete" />
	</jstl:if>
	<acme:cancel url="/quolet/administrator/list.do" code="quolet.cancel" />
	
</form:form>


