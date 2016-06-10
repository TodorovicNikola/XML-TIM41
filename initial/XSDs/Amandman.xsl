<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:am="http://www.xmlProjekat.com/amandman"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    exclude-result-prefixes="xs"
    version="2.0">
    <xsl:template match="/">
        <html>
            <head>
                <title>Amandman</title>
                <style type="text/css">
                    body {
                    background-color:white;
                    font-family: "Times New Roman", Times, serif;
                    
                    }
                    div.container {
                        width: 70%;
                        margin-left: auto;
                        margin-right: auto;
                        background-color:white;
                        border-color:white;
                        border-style: solid;
                        border-width: 1px;
                    }
                    
                    h1, h2, h3, h4 {
                        text-align: center;
                    }
                    
                    p{
                        text-align:justify;
                        width:60%;
                        margin-left:auto;
                        margin-right:auto;
                        text-indent: 50px;
                        font-size:16px;
                    }
                    
                    h1 {
                        font-size:50px;
                    }
                    h2{
                        font-size:40px;
                    }
                    h3{
                        font-size:35px;
                    }
                    h4{
                        font-size:30px;
                    }
                    h5{
                        font-size:16px;
                        text-align: left;
                    }
                    
                    span{
                        display: inline-block;
                        text-indent: 10px;
                    }
                    
                    li{
                        text-indent: 30px;
                    }
                </style>
            </head>
            <body>
                <div class="container">
                    <h5>
                        Datum i vreme podnošenja:<br />
                        <span><xsl:value-of select="/*/@DatumIVremePodnosenja"/></span><br />
                        <br />
                        
                        Podnosioci:<br />
                        <xsl:for-each select="//am:Podnosilac">
                            <span><xsl:value-of select="text()"/></span><br />
                        </xsl:for-each>
                    </h5>
                    
                    <h2>AMANDMAN</h2>

                            <xsl:for-each select="//am:ElementAmandmana">
                                <p>&#8226;
                                    <xsl:variable name="REFERENCE"><xsl:value-of select="@Referencira"/></xsl:variable>
                                    <xsl:choose>
                                        <xsl:when test="@Akcija = 'Dodaj'">
                                            Dodaje se u <a href="{$REFERENCE}">navedeno</a> sledeće: <xsl:value-of select="text()"/>
                                        </xsl:when>
                                        <xsl:when test="@Akcija = 'Izmeni'">
                                            Zamenjuje se <a href="{$REFERENCE}">navedeno</a> sledećim: <xsl:value-of select="text()"/>
                                        </xsl:when>
                                        <xsl:when test="@Akcija = 'Obrisi'">
                                            Briše se <a href="{$REFERENCE}">navedeno</a>.
                                        </xsl:when>
                                    </xsl:choose>
                                    
                                </p>
                                                   
                            </xsl:for-each>
                </div>
            </body>
        </html>   
    </xsl:template>
</xsl:stylesheet>