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
			<h:outputText value="Adressliste"/>
   		</h2>
   		
   		<h:commandButton image="/images/nurHaken.gif" action="#{mgr.adrKomprAction}" title="Reduzieren auf die Selektierten"/> 
		
		<t:dataTable
				 id="mitTab"
				 rowIndexVar="ind"
				 headerClass="standardTable_Header"
				 var="listEintrag"
				 value="#{mgr.adressliste}"	
				 border="3"
				 bgcolor="white"
				>
				
				<h:column>
					<f:facet name="header">
						<h:outputText id="anzahl" value="#{mgr.anzahlAdressen}"/>    
					</f:facet>
					<h:selectBooleanCheckbox immediate="true" value="#{listEintrag.selektiert}"></h:selectBooleanCheckbox>
	            </h:column>
								
				<h:column>
					<f:facet name="header">
				     <h:outputText value="Vorname" />
				    </f:facet>
				   	<h:outputText value="#{listEintrag.mitglied.vorname}" />
	            </h:column>
	            
	            <h:column>
	            	<f:facet name="header">
				     <h:outputText value="Zuname" />
				    </f:facet>
	                <h:outputText value="#{listEintrag.mitglied.zuname}" />
	            </h:column>
	            
	            <h:column>
	            	<f:facet name="header">
				     <h:outputText value="StraßeNrZus" />
				    </f:facet>
	                <h:outputText value="#{listEintrag.mitglied.strasseNrZus}" />
	            </h:column>
	            
	            <h:column>
	            	<f:facet name="header">
				     <h:outputText value="Plz" />
				    </f:facet>
	                <h:outputText value="#{listEintrag.mitglied.postleitzahl}" />
	            </h:column>
	            
	            <h:column>
	            	<f:facet name="header">
				     <h:outputText value="Ort" />
				    </f:facet>
	                <h:outputText value="#{listEintrag.mitglied.ort}" />
	            </h:column>
	            
	            <h:column>
	            	<f:facet name="header">
				     <h:outputText value="Anrede" />
				    </f:facet>
	                <h:outputText value="#{listEintrag.anrede}" />
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
		
		<br/>
		<h:commandButton id="home" value="Zurück" action="#{mgr.homeAction}" immediate="true" ></h:commandButton>
		
    </h:form>
</f:view>

</body>

</html>
