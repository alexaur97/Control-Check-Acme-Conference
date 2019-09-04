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


	<acme:display code="quolet.ticker" path="${quolet.ticker}" />
	<acme:display code="quolet.mode" path="${quolet.mode}" />
	
	<spring:message code="quolet.publicationMoment" />:
	
	<jstl:choose>
	<jstl:when test="${lang eq 'en'}">
	<fmt:formatDate type = "date" pattern = "yy/MM/dd HH:mm"
         value = "${quolet.publicationMoment}" />
     </jstl:when>
    <jstl:otherwise>
    	<fmt:formatDate type = "date" pattern = "dd-MM-yy HH:mm"
         value = "${quolet.publicationMoment}" />
    </jstl:otherwise>
</jstl:choose>
	
	<acme:display code="quolet.x1" path="${quolet.x1}" />
	<acme:display code="quolet.x2" path="${quolet.x2}" />
	<acme:display code="quolet.x3" path="${quolet.x3}" />

	<acme:button code="quolet.conference" url="conference/show.do?conferenceId=${quolet.conference.id}"/>

