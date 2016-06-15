<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:am="http://www.xmlProjekat.com/amandman"
                xmlns:akt="http://www.xmlProjekat.com/akt"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0">

    <xsl:template match="/">


        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="amandman-strana">
                    <fo:region-body margin="1in"/>

                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="amandman-strana">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block font-family="Arial" font-size="9px"   width="70%" margin-left="10%" margin-right="10%" >
                        <fo:block font-weight="bold">Datum i vreme podnošenja:<br />
                        <xsl:for-each select="am:Amandman">
                            <fo:block><xsl:value-of select="@DatumIVremePodnosenja"/>
                            </fo:block>
                            <fo:block>Podnosioci:
                            </fo:block>
                            <xsl:for-each select="am:Podnosilac">
                                <xsl:value-of select="text()"/>
                            </xsl:for-each>



                        </xsl:for-each>
                            </fo:block>

                        <fo:block text-align="center" font-size="20" padding="20">Amandman</fo:block>
                        <xsl:for-each select="//am:ElementAmandmana">

                            <xsl:variable name="REFERENCE"><xsl:value-of select="@Referencira"/></xsl:variable>
                            <xsl:choose>
                                <xsl:when test="@Akcija = 'Dodaj'">
                                    &#8226; Dodaje se ispod <a href="{$REFERENCE}">člana</a> sledeći član:
                                </xsl:when>
                                <xsl:when test="@Akcija = 'Izmeni'">
                                  &#8226; Zamenjuje se <a href="{$REFERENCE}">član</a> sledećim članom:
                                </xsl:when>
                                <xsl:when test="@Akcija = 'Obrisi'">
                                    <p>&#8226; Briše se <a href="{$REFERENCE}">član</a>.</p>
                                </xsl:when>
                            </xsl:choose>
                            <xsl:for-each select="akt:Clan">

                            <fo:block text-align="center" padding="20" font-size="15px" >
                               <xsl:value-of select="@Naslov"/>
                            </fo:block>

                                <fo:block>

                               <xsl:value-of select="akt:Sadrzaj"/>

                                </fo:block>
                                <xsl:for-each select="akt:Stav">
                                    <fo:block text-align="center" padding="20" font-size="13px" >
                                        <xsl:value-of select="@Naslov"/>
                                    </fo:block>

                                    <fo:block >
                                        <xsl:value-of select="akt:Sadrzaj"/>
                                    </fo:block>

                                    <xsl:for-each select="akt:Tacka">
                                        <fo:block text-align="center" padding="20" font-size="12px" >
                                            <xsl:value-of select="@Naslov"/>
                                        </fo:block>

                                        <fo:block >
                                            <xsl:value-of select="akt:Sadrzaj"/>
                                        </fo:block>

                                        <xsl:for-each select="akt:Podtacka">
                                            <fo:block text-align="center" padding="20" font-size="10px" >
                                                <xsl:value-of select="@Naslov"/>
                                            </fo:block>

                                            <fo:block >
                                                <xsl:value-of select="akt:Sadrzaj"/>
                                            </fo:block>

                                            <xsl:for-each select="//akt:Alineja">
                                                <fo:block >
                                                    <xsl:value-of select="akt:Sadrzaj"/>
                                                </fo:block>

                                            </xsl:for-each>

                                        </xsl:for-each>

                                    </xsl:for-each>
                                </xsl:for-each>
                            </xsl:for-each>
                        </xsl:for-each>


                    </fo:block>
                </fo:flow>
            </fo:page-sequence>

        </fo:root>
    </xsl:template>
</xsl:stylesheet>
