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
                    <fo:block font-family="Arial" font-size="9px" width="70%" margin-left="10%" margin-right="10%">
                        <fo:block font-weight="bold">Datum i vreme podnošenja:
                            <br/>

                            <fo:block>
                                <xsl:value-of select="/*/@DatumIVremePodnosenja"/>
                            </fo:block>
                            <fo:block>Podnosioci:
                            </fo:block>
                            <xsl:for-each select="//am:Podnosilac">
                                <xsl:value-of select="text()"/>
                            </xsl:for-each>


                        </fo:block>

                        <fo:block text-align="center" font-size="20" padding="20">Amandman</fo:block>
                        <xsl:for-each select="//am:ElementAmandmana">

                            <xsl:variable name="REFERENCE"><xsl:value-of select="/*/@IdAkta"/>#<xsl:value-of
                                    select="@Referencira"/>
                            </xsl:variable>
                            <xsl:variable name="pre" select="substring-before($REFERENCE,'#')"/>
                            <xsl:variable name="posle" select="substring-after($REFERENCE,'#')"/>
                            <xsl:variable name="linkPrep" select="concat($pre,'/pdf#')"/>
                            <xsl:variable name="link" select="concat($linkPrep,$posle)"/>
                            <xsl:choose>
                                <xsl:when test="@Akcija = 'Dodaj'">

                                    &#8226; Dodaje se u<fo:basic-link color="blue"
                                                                      external-destination="http://localhost:8080/api/akti/{$link}">
                                    odeljak/pododeljak</fo:basic-link>sledeći član:

                                </xsl:when>
                                <xsl:when test="@Akcija = 'Izmeni'">
                                    &#8226; Zamenjuje se u<fo:basic-link color="blue"
                                                                         external-destination="http://localhost:8080/api/akti/{$link}">
                                    član</fo:basic-link>sledećim članom:
                                </xsl:when>
                                <xsl:when test="@Akcija = 'Obrisi'">
                                    &#8226; Briše se<fo:basic-link color="blue"
                                                                   external-destination="http://localhost:8080/api/akti/{$link}">
                                    član</fo:basic-link>.

                                </xsl:when>
                            </xsl:choose>
                            <xsl:for-each select="akt:Clan">

                                <fo:block text-align="center" padding="20" font-size="15px">
                                    <xsl:value-of select="@Naslov"/>
                                </fo:block>
                                <fo:block text-align="center" padding="16" font-size="13px">
                                    Član
                                    <xsl:value-of select="@RedniBroj"/>
                                </fo:block >
                                <fo:block>

                                    <xsl:apply-templates select="akt:Sadrzaj"/>

                                </fo:block>
                                <xsl:for-each select="akt:Stav">
                                    <fo:block text-align="center" padding="20" font-size="13px">
                                        <xsl:value-of select="@Naslov"/>
                                    </fo:block>

                                    <fo:block>
                                        <xsl:apply-templates select="akt:Sadrzaj"/>
                                    </fo:block>

                                    <xsl:for-each select="akt:Tacka">
                                        <fo:block text-align="center" padding="20" font-size="12px">
                                            <xsl:value-of select="@Naslov"/>
                                        </fo:block>

                                        <fo:block>
                                            <xsl:apply-templates select="akt:Sadrzaj"/>
                                        </fo:block>

                                        <xsl:for-each select="akt:Podtacka">
                                            <fo:block text-align="center" padding="20" font-size="10px">
                                                <xsl:value-of select="@Naslov"/>
                                            </fo:block>

                                            <fo:block>
                                                <xsl:apply-templates select="akt:Sadrzaj"/>
                                                <xsl:apply-templates select="akt:Alineja"/>
                                            </fo:block>


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

    <xsl:template match="akt:Sadrzaj">

        <xsl:apply-templates/>

    </xsl:template>

    <!-- default rule: copy any node beneath <description> -->
    <xsl:template match="akt:Sadrzaj//*">
        <xsl:copy>
            <xsl:copy-of select="@*"/>
            <xsl:apply-templates/>
        </xsl:copy>
    </xsl:template>

    <xsl:template match="akt:Sadrzaj//akt:Referenca">
        <xsl:variable name="ref" select="@ReferencaURI"/>
        <xsl:variable name="pre" select="substring-before($ref,'#')"/>
        <xsl:variable name="posle" select="substring-after($ref,'#')"/>
        <xsl:variable name="linkPrep" select="concat($pre,'/pdf#')"/>
        <xsl:variable name="link" select="concat($linkPrep,$posle)"/>
        <fo:basic-link color="blue" external-destination="http://localhost:8080/api/akti/{$link}">
            <xsl:value-of select="text()"/>
        </fo:basic-link>
    </xsl:template>

    <!-- default rule: ignore any unspecific text node -->
    <xsl:template match="text()"/>

    <!-- override rule: copy any text node beneath description -->
    <xsl:template match="akt:Sadrzaj//text()">
        <xsl:copy-of select="."/>
    </xsl:template>

    <xsl:template match="akt:Alineja">
        <p>
            <xsl:apply-templates/>
        </p>
    </xsl:template>

    <!-- default rule: copy any node beneath <description> -->
    <xsl:template match="akt:Alineja//*">
        <xsl:copy>
            <xsl:copy-of select="@*"/>
            <xsl:apply-templates/>
        </xsl:copy>
    </xsl:template>

    <!-- override rule: <link> nodes get special treatment -->
    <xsl:template match="akt:Alineja//akt:Referenca">
        <xsl:variable name="ref" select="@ReferencaURI"/>
        <xsl:variable name="pre" select="substring-before($ref,'#')"/>
        <xsl:variable name="posle" select="substring-after($ref,'#')"/>
        <xsl:variable name="linkPrep" select="concat($pre,'/pdf#')"/>
        <xsl:variable name="link" select="concat($linkPrep,$posle)"/>
        <fo:basic-link color="blue" external-destination="http://localhost:8080/api/akti/{$link}">
            <xsl:value-of select="text()"/>
        </fo:basic-link>
    </xsl:template>

    <!-- override rule: copy any text node beneath description -->
    <xsl:template match="akt:Alineja//text()">
        <xsl:copy-of select="."/>
    </xsl:template>
</xsl:stylesheet>
