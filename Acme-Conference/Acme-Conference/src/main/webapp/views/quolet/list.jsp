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

<h4>
	<spring:message code="quolet.all" />
</h4>

<display:table pagesize="5" name="quolets" id="quolet"
	requestURI="${requestURI}" class="displaytag table">
	
		<jstl:if test="${((date2-quolet.publicationMoment.time)/86400000)<30}">
	
		<display:column style="color:red;" titleKey="quolet.ticker" property="ticker" />
		<display:column style="color:red;" titleKey="quolet.mode" property="mode" />
		<display:column style="color:red;" titleKey="quolet.x1" property="x1" />
		<display:column titleKey="quolet.show">
			<acme:cancel
				url="/quolet/administrator/show.do?quoletId=${quolet.id}"
				code="quolet.show" />
		</display:column>
		<display:column titleKey="quolet.edit">
			<jstl:if test="${quolet.mode eq 'DRAFT'}">
				<acme:cancel
					url="/quolet/administrator/edit.do?quoletId=${quolet.id}"
					code="quolet.edit" />
			</jstl:if>
		</display:column>
	</jstl:if>
	
	<jstl:if test="${((date2-quolet.publicationMoment.time)/86400000)>30 and ((date2-quolet.publicationMoment.time)/86400000)<60}">
	
		<display:column style="color:blue;" titleKey="quolet.ticker" property="ticker" />
		<display:column style="color:blue;" titleKey="quolet.mode" property="mode" />
		<display:column style="color:blue;" titleKey="quolet.x1" property="x1" />
		<display:column titleKey="quolet.show">
			<acme:cancel
				url="/quolet/administrator/show.do?quoletId=${quolet.id}"
				code="quolet.show" />
		</display:column>
		<display:column titleKey="quolet.edit">
			<jstl:if test="${quolet.mode eq 'DRAFT'}">
				<acme:cancel
					url="/quolet/administrator/edit.do?quoletId=${quolet.id}"
					code="quolet.edit" />
			</jstl:if>
		</display:column>	
	</jstl:if>
	
	<jstl:if test="${((date2-quolet.publicationMoment.time)/86400000)>60}">
	
		<display:column style="color:green;" titleKey="quolet.ticker" property="ticker" />
		<display:column style="color:green;" titleKey="quolet.mode" property="mode" />
		<display:column style="color:green;" titleKey="quolet.x1" property="x1" />
		<display:column titleKey="quolet.show">
			<acme:cancel
				url="/quolet/administrator/show.do?quoletId=${quolet.id}"
				code="quolet.show" />
		</display:column>
		<display:column titleKey="quolet.edit">
			<jstl:if test="${quolet.mode eq 'DRAFT'}">
				<acme:cancel
					url="/quolet/administrator/edit.do?quoletId=${quolet.id}"
					code="quolet.edit" />
			</jstl:if>
		</display:column>	
	</jstl:if>
	
</display:table>

<h4>
	<spring:message code="quolet.mine" />
</h4>

<display:table pagesize="5" name="myQuolets" id="myQuolet"
	requestURI="${requestURI}" class="displaytag table">
	
			<jstl:if test="${((date2-myQuolet.publicationMoment.time)/86400000)<30}">
	
		<display:column style="color:red;" titleKey="quolet.ticker" property="ticker" />
		<display:column style="color:red;" titleKey="quolet.mode" property="mode" />
		<display:column style="color:red;" titleKey="quolet.x1" property="x1" />
		<display:column titleKey="quolet.show">
			<acme:cancel
				url="/quolet/administrator/show.do?quoletId=${myQuolet.id}"
				code="quolet.show" />
		</display:column>
		<display:column titleKey="quolet.edit">
			<jstl:if test="${myQuolet.mode eq 'DRAFT'}">
				<acme:cancel
					url="/quolet/administrator/edit.do?quoletId=${myQuolet.id}"
					code="quolet.edit" />
			</jstl:if>
		</display:column>
	</jstl:if>
	
	<jstl:if test="${((date2-myQuolet.publicationMoment.time)/86400000)>30 and ((date2-myQuolet.publicationMoment.time)/86400000)<60}">
	
		<display:column style="color:blue;" titleKey="quolet.ticker" property="ticker" />
		<display:column style="color:blue;" titleKey="quolet.mode" property="mode" />
		<display:column style="color:blue;" titleKey="quolet.x1" property="x1" />
		<display:column titleKey="quolet.show">
			<acme:cancel
				url="/quolet/administrator/show.do?quoletId=${myQuolet.id}"
				code="quolet.show" />
		</display:column>
		<display:column titleKey="quolet.edit">
			<jstl:if test="${myQuolet.mode eq 'DRAFT'}">
				<acme:cancel
					url="/quolet/administrator/edit.do?quoletId=${myQuolet.id}"
					code="quolet.edit" />
			</jstl:if>
		</display:column>	
	</jstl:if>
	
	<jstl:if test="${((date2-myQuolet.publicationMoment.time)/86400000)>60}">
	
		<display:column style="color:green;" titleKey="quolet.ticker" property="ticker" />
		<display:column style="color:green;" titleKey="quolet.mode" property="mode" />
		<display:column style="color:green;" titleKey="quolet.x1" property="x1" />
		<display:column titleKey="quolet.show">
			<acme:cancel
				url="/quolet/administrator/show.do?quoletId=${myQuolet.id}"
				code="quolet.show" />
		</display:column>
		<display:column titleKey="quolet.edit">
			<jstl:if test="${myQuolet.mode eq 'DRAFT'}">
				<acme:cancel
					url="/quolet/administrator/edit.do?quoletId=${myQuolet.id}"
					code="quolet.edit" />
			</jstl:if>
		</display:column>	
	</jstl:if>
	
</display:table>

<br>

<acme:button code="quolet.create" url="quolet/administrator/create.do" />


