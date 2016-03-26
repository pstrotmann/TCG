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

    <h:form id="form1" enctype="multipart/form-data">
		
		<h2 align="center">
			<h:outputText value="MailAnListe"/>
   		</h2>
		
		<h2 align="center">
			<h:outputText value="Absender"/>
			<h:outputText value="#{mgr.benutzer.userName}"/>
			<h:outputText value="#{mgr.benutzer.mailFrom}"/>			
   		</h2>
   		   		
   		<h:commandButton image="/images/nurHaken.gif" action="#{mgr.mailKomprAction}" title="Reduzieren auf die Selektierten"/> 
		
		<t:dataTable
				 id="mitTab"
				 rowIndexVar="ind"
				 headerClass="standardTable_Header"
				 var="listEintrag"
				 value="#{mgr.mailliste}"	
				 border="3"
				 bgcolor="white"
				>
					
				<h:column>
					<f:facet name="header">
						<h:outputText id="anzahl" value="#{mgr.anzahlMailEmpfaenger}"/>    
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
				     <h:outputText value="eMail" />
				    </f:facet>
	                <h:outputText value="#{listEintrag.mitglied.email}" />
	            </h:column>
				
		</t:dataTable>
		
		<h:panelGrid columns="2" border="3" title="Mail">
			<h:outputLabel for="form1:betreff" value="Betreff:"/>
			<h:inputText id="betreff" value="#{mgr.betreff}" style="width: 633px"/>
		
			<h:outputLabel for="form1:mailText" value="MailText:"/>
			<h:inputTextarea id="mailText" value="#{mgr.mailText}" style="height: 149px; width: 646px" lang="German"></h:inputTextarea>

			<h:outputLabel for="form1:fileupload1" value="Anhang 1:"/>
			<t:inputFileUpload id="fileupload1"
                               value="#{mgr.upFile1}"
                               storage="file"
                               styleClass="fileUploadInput"
                               required="false"
                               maxlength="200000"/>
            <h:message for="fileupload1" showDetail="true" />
            <f:verbatim><br></f:verbatim>
            <h:outputLabel for="form1:fileupload2" value="Anhang 2:"/>
			<t:inputFileUpload id="fileupload2"
                               value="#{mgr.upFile2}"
                               storage="file"
                               styleClass="fileUploadInput"
                               required="false"
                               maxlength="200000"/>
            <h:message for="fileupload2" showDetail="true" />
            <f:verbatim><br></f:verbatim>
            <h:outputLabel for="form1:fileupload3" value="Anhang 3:"/>
			<t:inputFileUpload id="fileupload3"
                               value="#{mgr.upFile3}"
                               storage="file"
                               styleClass="fileUploadInput"
                               required="false"
                               maxlength="200000"/>
            <h:message for="fileupload3" showDetail="true" />
            <f:verbatim><br></f:verbatim>
            
            
            <h:commandButton value="senden" action="#{mgr.sendeAction}" title="Senden des Textes an die aufgelisteten Mitglieder"/>
            <f:verbatim><br></f:verbatim>
		</h:panelGrid>
		<br/>
		<h:commandButton id="home" value="Zurück" action="#{mgr.homeAction}" immediate="true" ></h:commandButton>
		
    </h:form>
</f:view>

</body>

</html>
