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
			<h:outputText value="Beitragsliste"/>
   		</h2>
		
		<t:dataTable
				 id="mitTab"
				 rowIndexVar="ind"
				 headerClass="standardTable_Header"
				 var="mitglied"
				 value="#{mgr.beitragsliste}"	
				 border="3"
				 bgcolor="white"
				>
				
				<h:column>
					<f:facet name="header">
						<h:outputText id="anzahl" value="#{mgr.anzahlBeitragszahler}"/>    
					</f:facet>
					<h:outputText id="filler" value=" "/>
	            </h:column>
								
				<h:column>
					<f:facet name="header">
				     <h:outputText value="Vorname" />
				    </f:facet>
				   	<h:outputText value="#{mitglied.vorname}" />
	            </h:column>
	            
	            <h:column>
	            	<f:facet name="header">
				     <h:outputText value="Zuname" />
				    </f:facet>
	                <h:outputText value="#{mitglied.zuname}" />
	            </h:column>
	            
	            <h:column>
	               <f:facet name="header">
				     <h:outputText value="Zahlungsart" />
				   </f:facet> 
	               <h:outputText value="#{mitglied.zahlungsart}" converter="zahlungsartConverter"/>
	            </h:column>
	            
	            <h:column>
	               <f:facet name="header">
				     <h:outputText value="Beitragsklasse" />
				   </f:facet>
	               <h:outputText value="#{mitglied.beitragsklasse}" converter="beitragsklasseConverter"/>
	            </h:column>
	            
	            <h:column>
	               <f:facet name="header">
				     <h:outputText value="Beitrag" />
				   </f:facet>
	               <h:outputText value="#{mitglied.beitragsklasse}" converter="beitragConverter"/>
	            </h:column>
	            
	            <h:column>
	               <f:facet name="header">
				     <h:outputText value="Bankleitzahl" />
				   </f:facet>
	               <h:outputText value="#{mitglied.bankleitzahl}" />
	            </h:column>
	            
	            <h:column>
	               <f:facet name="header">
				     <h:outputText value="Kontonummer" />
				   </f:facet>
	               <h:outputText value="#{mitglied.kontonummer}" />
	            </h:column>
	            
	            <h:column>
	               <f:facet name="header">
				     <h:outputText value="Kontoinhaber" />
				   </f:facet>
	               <h:outputText value="#{mitglied.kontoinhaber}" />
	            </h:column>
	            
	            <h:column>
	               <f:facet name="header">
				     <h:outputText value="IBAN" />
				   </f:facet>
	               <h:outputText value="#{mitglied.IBAN}" />
	            </h:column>
	            
	            <h:column>
	               <f:facet name="header">
				     <h:outputText value="Status" />
				   </f:facet>
				   <h:outputText value="#{mitglied.status}"/>
	            </h:column>
	            
	            <h:column>
					<f:facet name="header">
				     <h:outputText value="MitgliedId" />
				    </f:facet>
				   	<h:outputText value="#{mitglied.mitgliedId}" />
	            </h:column>
				
		</t:dataTable>
		
		<h2 align="center">
			<h:outputText value="Ausgeben der Liste in eine Excel Datei"/>
		</h2>
		
		<h2 align="center">
		<s:excelExport for="mitTab">
			<h:commandButton action="" value="Export nach Excel" image="/images/table24.gif" title="Ausgeben der Liste in eine Excel Datei"/>
		</s:excelExport>
		</h2>
		
		<h2 align="center">
			<h:outputText value="Erstellen der Abbuchungsdatei"/>
		</h2>
		
		<h2 align="center">
			<h:commandButton action="#{mgr.abbuchAction}" value="Erstellen der Abbuchungsdatei" image="/images/table24.gif" title="Erstellen der Abbuchungsdatei"/>
		</h2>
		
		<br/>
		<h:commandButton id="home" value="Zurück" action="#{mgr.homeAction}" immediate="true" ></h:commandButton>
		
    </h:form>
</f:view>

</body>

</html>
