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

<form:form action="dusit/administrator/edit.do" modelAttribute="dusit"
	class="form-horizontal" method="post">
	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<acme:select items="${conferences}" itemLabel="title" code="dusit.conference" path="conference"/>
	<acme:textbox code="dusit.body" path="body" />
	<acme:textbox code="dusit.picture" path="picture" />
	<spring:message code="dusit.mode" />
	<form:select path="mode">
		<form:option value="DRAFT" />
		<form:option value="FINAL" />
	</form:select>
	<br/>
	<acme:submit name="save" code="dusit.save" />
	<jstl:if test="${dusit.id!=0}">
	<acme:submit name="delete" code="dusit.delete" />
	</jstl:if>
	<acme:cancel url="/dusit/administrator/list.do" code="dusit.cancel" />
	
</form:form>


