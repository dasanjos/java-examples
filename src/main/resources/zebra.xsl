<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
<html>
  <title>Zebra Puzzle - Solutions</title>
  <body>
  <h1>Zebra Puzzle - Solutions</h1>
  <xsl:for-each select="solutions/solution">
  <table border="1">
    <tr>
      <th>House</th>
      <th>5</th>
      <th>4</th>
      <th>3</th>
      <th>2</th>
      <th>1</th>
    </tr>
  <tr>
    <th>Color</th>
    <td>Ivory</td>
    <td>Green</td>
    <td>Red</td>
    <td>Blue</td>
    <td>Yellow</td>
   </tr>
  </table>
  </xsl:for-each>
  </body>
</html>
</xsl:template>
</xsl:stylesheet> 