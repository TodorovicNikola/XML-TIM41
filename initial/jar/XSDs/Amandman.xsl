<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:am="http://www.xmlProjekat.com/amandman" xmlns:akt="http://www.xmlProjekat.com/akt"
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
                    
                    h1, h2, h3, h4, h5, h6 {
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
                        font-size:25px;
                    }
                    h6{
                        font-size:20px;
                    }
                    
                    h6.TackaNaslov{
                        font-size:18px;
                        font-weight:bold;
                        text-align:center;
                    }
                    
                    h6.PodtackaNaslov{
                        font-size:16px;
                        font-weight:bold;
                        text-align:center;
                    }
                    
                    h5.header {
                        text-align:left !important;
                        font-size:16px !important;
                    }
                    h6.part {
                        font-style: italic;
                    }
                </style>
            </head>
            <body>
                <div class="container">
                    <h5 class="header">
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
                            <xsl:variable name="REFERENCE"><xsl:value-of select="/*/@IdAkta"/>#<xsl:value-of select="@Referencira"/></xsl:variable>
                            <xsl:choose>
                                <xsl:when test="@Akcija = 'Dodaj'">
                                    <p> &#8226; Dodaje se u <a href="http://localhost:8080/api/akti/{$REFERENCE}" target="_blank">odeljak/pododeljak</a> sledeći član:</p> 
                                </xsl:when>
                                <xsl:when test="@Akcija = 'Izmeni'">
                                    <p>&#8226; Zamenjuje se <a href="http://localhost:8080/api/akti/{$REFERENCE}" target="_blank">član</a> sledećim članom:</p> 
                                </xsl:when>
                                <xsl:when test="@Akcija = 'Obrisi'">
                                    <p>&#8226; Briše se <a href="http://localhost:8080/api/akti/{$REFERENCE}" target="_blank">član</a>.</p>
                                </xsl:when>
                            </xsl:choose>
                            <xsl:for-each select="akt:Clan">
                                <h5><xsl:value-of select="@Naslov"/></h5>
                                <h6 class="part">Član <xsl:value-of select="@RedniBroj"/></h6>
                                
                                <xsl:apply-templates select="akt:Sadrzaj"/>
                                
                                <xsl:for-each select="akt:Stav">
                                    <h6><xsl:value-of select="@Naslov"/></h6>
                                    
                                    <xsl:apply-templates select="akt:Sadrzaj"/>
                                    
                                    <xsl:for-each select="akt:Tacka">
                                        <h6 class="TackaNaslov"><xsl:value-of select="@Naslov"/></h6>
                                        
                                        <xsl:apply-templates select="akt:Sadrzaj"/>
                                        
                                        <xsl:for-each select="akt:Podtacka">
                                            <h6 class="PodtackaNaslov"><xsl:value-of select="@Naslov"/></h6>
                                            
                                            <xsl:apply-templates select="akt:Sadrzaj"/>
                                            <xsl:apply-templates select="akt:Alineja"/>
                                            
                                        </xsl:for-each>
                                        
                                    </xsl:for-each>
                                </xsl:for-each>
                            </xsl:for-each>      
                        </xsl:for-each>
                </div>
            </body>
        </html>   
    </xsl:template>
    <xsl:template match="akt:Sadrzaj">
        <p>
            <xsl:apply-templates />
        </p>
    </xsl:template>
    
    <!-- default rule: copy any node beneath <description> -->
    <xsl:template match="akt:Sadrzaj//*">
        <xsl:copy>
            <xsl:copy-of select="@*" />
            <xsl:apply-templates />
        </xsl:copy>
    </xsl:template>
    
    <!-- override rule: <link> nodes get special treatment -->
    <xsl:template match="akt:Sadrzaj//akt:Referenca">
        <a href="{@ReferencaURI}">
            <xsl:apply-templates />
        </a>
    </xsl:template>
    
    <!-- default rule: ignore any unspecific text node -->
    <xsl:template match="text()" />
    
    <!-- override rule: copy any text node beneath description -->
    <xsl:template match="akt:Sadrzaj//text()">
        <xsl:copy-of select="." />
    </xsl:template>
    
    <xsl:template match="akt:Alineja">
        <p>
            <xsl:apply-templates />
        </p>
    </xsl:template>
    
    <!-- default rule: copy any node beneath <description> -->
    <xsl:template match="akt:Alineja//*">
        <xsl:copy>
            <xsl:copy-of select="@*" />
            <xsl:apply-templates />
        </xsl:copy>
    </xsl:template>
    
    <!-- override rule: <link> nodes get special treatment -->
    <xsl:template match="akt:Alineja//akt:Referenca">
        <a href="{@ReferencaURI}">
            <xsl:apply-templates />
        </a>
    </xsl:template>
    
    <!-- override rule: copy any text node beneath description -->
    <xsl:template match="akt:Alineja//text()">
        <xsl:copy-of select="." />
    </xsl:template>
    
</xsl:stylesheet>