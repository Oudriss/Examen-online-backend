<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method='html' version='1.0' encoding='UTF-8' indent='yes'/>
	<xsl:template match="/examen">
		<html>
			<body>
				<center><h1>Sujet d'examen : <xsl:value-of select="sujet" /></h1></center>
				<center><h3>Date : <xsl:value-of select="date" /></h3></center>
				<center><h3>Durée : <xsl:value-of select="duree" /> H</h3></center>
				
				<xsl:for-each select="questions">
				<h5>Question : <xsl:value-of select="question" /></h5>
				<table>
						<xsl:for-each select="reponse">
							<tr><td><pre>         [ ]</pre></td><td><p><xsl:value-of select="." />.</p></td></tr>
						</xsl:for-each>
				</table>
				</xsl:for-each>
			
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>