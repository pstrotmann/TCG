<%@ page session="false" contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://myfaces.apache.org/sandbox" prefix="s"%>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=Cp1252"/>
	<title>TCG</title>
</head>

<body>

<f:view>

	<h1 align="center">
		<t:graphicImage value="/images/tcg-logo-mittel.jpg"></t:graphicImage>
		<h:outputText value="Mitgliederverwaltung"/>				
		<t:graphicImage value="/images/tcg-logo-mittel.jpg"></t:graphicImage>
	</h1>

    <h:form>
    
    	<h2 align="center">
			<h:outputText value="Anmeldung"/>
  		</h2>
          			
		<h:panelGrid border="3" columns="3" align="center">
           <h:outputText  value="Benutzer" />
           <h:outputText  value="Kennwort" />
           <h:outputText  value=" " />
			<h:inputText  value="#{anmelder.benutzer}" />
			<h:inputSecret  value="#{anmelder.kennwort}" />
			<h:commandButton value="#{anmelder}" title="Anmelden" action="#{anmelder.anmeldeAction}"
 			 image="/images/tennismaenchen.jpg"  >
     	    </h:commandButton>
		</h:panelGrid>
		
		<h:panelGrid columns="1" align="center">
			<h:outputText id="nachricht" value="#{anmelder.nachricht}"/>
		</h:panelGrid>		
    </h:form>
</f:view>

</body>

</html>
