<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
<html>
  <head>
    <title>Zebra Puzzle - Solutions</title>
    <style type="text/css">
      table {
		background-color:	#F9F9F9; 
		border:	1px solid #AAAAAA;
		border-collapse:	collapse; 
		color:	black; 
		margin: 1em 1em 1em 0;
      }
      th {
        background-color: #F2F2F2;
        text-align: center;
      }
      th, td {
        border: 1px solid #AAAAAA;
        padding: 0.2em;
      }
    </style>
  </head>
  <body>
  <h1>Zebra Puzzle - Solutions</h1>
  <xsl:for-each select="solutions/solution">
  <table>
    <tr>
      <th>House</th>
      <xsl:for-each select="house/@position">
        <th><xsl:value-of select="."/></th>
      </xsl:for-each>
    </tr>
    <!--PROPERTIES--> 
  </table>
  </xsl:for-each>
  </body>
</html>
</xsl:template>
</xsl:stylesheet>