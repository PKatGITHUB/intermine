<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="im" %>

<tiles:importAttribute/>

<!-- objectView.jsp -->
<html:xhtml/>
<div class="objectView">
  <c:choose>
    <c:when test="${empty leafClds}">
      <fmt:message key="objectDetails.nullField" var="nullFieldText"/>
      <c:set var="maxLength" value="60"/>
      <c:choose>
        <c:when test="${object != null && object.class.name == 'java.lang.String' && fn:length(object) > maxLength}">
          ${fn:substring(object, 0, maxLength)} ...
        </c:when>
        <c:otherwise>
          <c:out value="${object}" default="${nullFieldText}"/>
        </c:otherwise>
      </c:choose>
    </c:when>
    <c:otherwise>
      <c:if test="${fn:substring(param.table, 0, 7) == 'results'}">
        <c:set var="prepend" value="_${param.table}"/>
      </c:if>
      <c:set var="linkAction" value="/objectDetails?id=${object.id}&amp;trail=${prepend}${param.trail}_${object.id}" scope="request"/>
      <span style="white-space:nowrap">
        <c:forEach var="cld" items="${leafClds}">
          <span class="type"><c:out value="${cld.unqualifiedName}"/></span>
        </c:forEach>
        [<html:link action="${linkAction}">
          <fmt:message key="results.details"/>
        </html:link>]
      </span>
      <br/>
      <div style="margin-left: 8px">
        <c:forEach items="${DISPLAY_OBJECT_CACHE[object].fieldExprs}" var="expr">
          <im:eval evalExpression="object.${expr}" evalVariable="outVal"/>
          <c:set var="style" value="white-space:nowrap"/>
          <c:if test="${outVal.class.name == 'java.lang.String' && fn:length(outVal) > 60}">
            <c:set var="outVal" value="${fn:substring(outVal, 0, 60)}..."/>
            <c:set var="style" value=""/>
          </c:if>
          <div style="${style}">
            <span class="attributeField">${expr}</span>
            <span>${outVal}</span>
          </div>
        </c:forEach>
        <c:forEach items="${leafClds}" var="cld">
          <c:if test="${WEBCONFIG.types[cld.name].tableDisplayer != null}">
            <div>
              <c:set var="cld" value="${cld}" scope="request"/>
              <tiles:insert page="${WEBCONFIG.types[cld.name].tableDisplayer.src}"/>
            </div>
          </c:if>
        </c:forEach>
      </div>
    </c:otherwise>
  </c:choose>
</div>
<!-- /objectView.jsp -->
