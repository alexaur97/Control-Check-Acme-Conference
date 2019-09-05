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
	<spring:message code="dusit.all" />
</h4>

<display:table pagesize="5" name="dusits" id="dusit"
	requestURI="${requestURI}" class="displaytag table">
	
		<jstl:if test="${((date2-dusit.publicationMoment.time)/86400000)<30}">
	
		<display:column style="color:DarkOrange;" titleKey="dusit.ticker" property="ticker" />
		<display:column style="color:DarkOrange;" titleKey="dusit.mode" property="mode" />
		<display:column maxLength="50" style="color:DarkOrange;" titleKey="dusit.body" property="body" />
		<display:column titleKey="dusit.show">
			<acme:cancel
				url="/dusit/administrator/show.do?dusitId=${dusit.id}"
				code="dusit.show" />
		</display:column>
		<display:column titleKey="dusit.edit">
			<jstl:if test="${dusit.mode eq 'DRAFT'}">
				<acme:cancel
					url="/dusit/administrator/edit.do?dusitId=${dusit.id}"
					code="dusit.edit" />
			</jstl:if>
		</display:column>
	</jstl:if>
	
	<jstl:if test="${((date2-dusit.publicationMoment.time)/86400000)>30 and ((date2-dusit.publicationMoment.time)/86400000)<60}">
	
		<display:column style="color:Pink;" titleKey="dusit.ticker" property="ticker" />
		<display:column style="color:Pink;" titleKey="dusit.mode" property="mode" />
		<display:column maxLength="50" style="color:Pink;" titleKey="dusit.body" property="body" />
		<display:column titleKey="dusit.show">
			<acme:cancel
				url="/dusit/administrator/show.do?dusitId=${dusit.id}"
				code="dusit.show" />
		</display:column>
		<display:column titleKey="dusit.edit">
			<jstl:if test="${dusit.mode eq 'DRAFT'}">
				<acme:cancel
					url="/dusit/administrator/edit.do?dusitId=${dusit.id}"
					code="dusit.edit" />
			</jstl:if>
		</display:column>	
	</jstl:if>
	
	<jstl:if test="${((date2-dusit.publicationMoment.time)/86400000)>60}">
	
		<display:column style="color:AliceBlue;" titleKey="dusit.ticker" property="ticker" />
		<display:column style="color:AliceBlue;" titleKey="dusit.mode" property="mode" />
		<display:column maxLength="50" style="color:AliceBlue;" titleKey="dusit.body" property="body" />
		<display:column titleKey="dusit.show">
			<acme:cancel
				url="/dusit/administrator/show.do?dusitId=${dusit.id}"
				code="dusit.show" />
		</display:column>
		<display:column titleKey="dusit.edit">
			<jstl:if test="${dusit.mode eq 'DRAFT'}">
				<acme:cancel
					url="/dusit/administrator/edit.do?dusitId=${dusit.id}"
					code="dusit.edit" />
			</jstl:if>
		</display:column>	
	</jstl:if>
	
</display:table>

<h4>
	<spring:message code="dusit.mine" />
</h4>

<display:table pagesize="5" name="myDusits" id="myDusit"
	requestURI="${requestURI}" class="displaytag table">
	
			<jstl:if test="${((date2-myDusit.publicationMoment.time)/86400000)<30}">
	
		<display:column style="color:DarkOrange;" titleKey="dusit.ticker" property="ticker" />
		<display:column style="color:DarkOrange;" titleKey="dusit.mode" property="mode" />
		<display:column maxLength="50" style="color:DarkOrange;" titleKey="dusit.body" property="body" />
		<display:column titleKey="dusit.show">
			<acme:cancel
				url="/dusit/administrator/show.do?dusitId=${myDusit.id}"
				code="dusit.show" />
		</display:column>
		<display:column titleKey="dusit.edit">
			<jstl:if test="${myDusit.mode eq 'DRAFT'}">
				<acme:cancel
					url="/dusit/administrator/edit.do?dusitId=${myDusit.id}"
					code="dusit.edit" />
			</jstl:if>
		</display:column>
	</jstl:if>
	
	<jstl:if test="${((date2-myDusit.publicationMoment.time)/86400000)>30 and ((date2-myDusit.publicationMoment.time)/86400000)<60}">
	
		<display:column style="color:Pink;" titleKey="dusit.ticker" property="ticker" />
		<display:column style="color:Pink;" titleKey="dusit.mode" property="mode" />
		<display:column maxLength="50" style="color:Pink;" titleKey="dusit.body" property="body" />
		<display:column titleKey="dusit.show">
			<acme:cancel
				url="/dusit/administrator/show.do?dusitId=${myDusit.id}"
				code="dusit.show" />
		</display:column>
		<display:column titleKey="dusit.edit">
			<jstl:if test="${myDusit.mode eq 'DRAFT'}">
				<acme:cancel
					url="/dusit/administrator/edit.do?dusitId=${myDusit.id}"
					code="dusit.edit" />
			</jstl:if>
		</display:column>	
	</jstl:if>
	
	<jstl:if test="${((date2-myDusit.publicationMoment.time)/86400000)>60}">
	
		<display:column style="color:AliceBlue;" titleKey="dusit.ticker" property="ticker" />
		<display:column style="color:AliceBlue;" titleKey="dusit.mode" property="mode" />
		<display:column maxLength="50" style="color:AliceBlue;" titleKey="dusit.body" property="body" />
		<display:column titleKey="dusit.show">
			<acme:cancel
				url="/dusit/administrator/show.do?dusitId=${myDusit.id}"
				code="dusit.show" />
		</display:column>
		<display:column titleKey="dusit.edit">
			<jstl:if test="${myDusit.mode eq 'DRAFT'}">
				<acme:cancel
					url="/dusit/administrator/edit.do?dusitId=${myDusit.id}"
					code="dusit.edit" />
			</jstl:if>
		</display:column>	
	</jstl:if>
	
</display:table>

<br>

<acme:button code="dusit.create" url="dusit/administrator/create.do" />


