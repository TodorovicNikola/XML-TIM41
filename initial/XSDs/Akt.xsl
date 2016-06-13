<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:akt="http://www.xmlProjekat.com/akt"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    exclude-result-prefixes="xs"
    
    version="2.0">
    <xsl:template match="/">
      <html>
          <head>
              <title>Akt - <xsl:value-of select="/*/@Naslov"/></title>
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
                    font-size:14px;
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
              </style>              
          </head>
          <body>
             <div class="container">
                <h1> <xsl:value-of select="/*/@Naslov"/></h1>
                 
                 <xsl:for-each select="//akt:Deo">
                     <h2><xsl:value-of select="@Naslov"/></h2>
                     <xsl:for-each select="akt:Glava">
                            <h3><xsl:value-of select="@Naslov"/></h3>
                         <xsl:for-each select="akt:Odeljak">
                                    <h4><xsl:value-of select="@Naslov"/></h4>
                                    
                                    <xsl:choose>
                                        <xsl:when test="akt:Pododeljak">
                                            <xsl:for-each select="akt:Pododeljak">
                                                <h5><xsl:value-of select="@Naslov"/></h5>
                                                
                                                <xsl:for-each select="akt:Clan">
                                                    <h5><xsl:value-of select="@Naslov"/></h5>
                                                    
                                                    <p><xsl:value-of select="akt:Sadrzaj"/></p>
                                                    
                                                    <xsl:for-each select="akt:Stav">
                                                        <h6><xsl:value-of select="@Naslov"/></h6>
                                                        
                                                        <p><xsl:value-of select="akt:Sadrzaj"/></p>
                                                        
                                                        <xsl:for-each select="akt:Tacka">
                                                            <h6 class="TackaNaslov"><xsl:value-of select="@Naslov"/></h6>
                                                            
                                                            <p><xsl:value-of select="akt:Sadrzaj"/></p>
                                                            
                                                            <xsl:for-each select="akt:Podtacka">
                                                                <h6 class="PodtackaNaslov"><xsl:value-of select="@Naslov"/></h6>
                                                                
                                                                <p><xsl:value-of select="akt:Sadrzaj"/></p>
                                                                
                                                                <xsl:for-each select="akt:Alineja">                                                            
                                                                    <p><xsl:value-of select="akt:Sadrzaj"/></p>
                                                                    
                                                                </xsl:for-each>
                                                                
                                                            </xsl:for-each>
                                                            
                                                        </xsl:for-each>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                        
                                            </xsl:for-each>
                                        </xsl:when>
                                        <xsl:otherwise>
                                            <xsl:for-each select="akt:Clan">
                                                <h5><xsl:value-of select="@Naslov"/></h5>
                                                
                                                <p><xsl:value-of select="akt:Sadrzaj"/></p>
                                                
                                                <xsl:for-each select="akt:Stav">
                                                    <h6><xsl:value-of select="@Naslov"/></h6>
                                                    
                                                    <p><xsl:value-of select="akt:Sadrzaj"/></p>
                                                    
                                                    <xsl:for-each select="akt:Tacka">
                                                        <h6 class="TackaNaslov"><xsl:value-of select="@Naslov"/></h6>
                                                        
                                                        <p><xsl:value-of select="akt:Sadrzaj"/></p>
                                                        
                                                        <xsl:for-each select="akt:Podtacka">
                                                            <h6 class="PodtackaNaslov"><xsl:value-of select="@Naslov"/></h6>
                                                            
                                                            <p><xsl:value-of select="akt:Sadrzaj"/></p>
                                                            
                                                            <xsl:for-each select="akt:Alineja">                                                            
                                                                <p><xsl:value-of select="akt:Sadrzaj"/></p>
                                                                
                                                            </xsl:for-each>
                                                            
                                                        </xsl:for-each>
                                                        
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </xsl:for-each>
                                        </xsl:otherwise>
                                    </xsl:choose>
                                </xsl:for-each> 
                    </xsl:for-each>
                 </xsl:for-each>
             </div> 
          </body>
      </html>  
    </xsl:template>
</xsl:stylesheet>