<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:a="http://www.xmlProjekat.com/akt"
                xmlns:fox="http://xmlgraphics.apache.org/fop/extensions"
                xmlns:fo="http://www.w3.org/1999/XSL/Format"
                xmlns:xs="http://www.w3.org/2001/XMLSchema"
                exclude-result-prefixes="xs"

                version="2.0">

    <xsl:template match="/">


        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="akt-strana">
                    <fo:region-body margin="1in"/>

                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="akt-strana">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block font-family="Arial" font-size="10px" width="70%" margin-left="10%" margin-right="10%">
                        <fo:block font-family="Arial" text-align="center" font-size="23" margin="20px">
                            <xsl:value-of select="/*/@Naslov"/>


                        </fo:block>


                            <xsl:for-each select="//a:Deo">
                                <fo:block id="{@Id}"  font-family="Arial" text-align="center" font-size="21"
                                          margin="20px">
                                    <fox:destination internal-destination="{@Id}"/>
                                    <xsl:value-of select="@Naslov"/>





                                </fo:block>
                                <fo:block font-family="Arial" text-align="center" font-size="21" margin="20px">
                                    Deo
                                    <xsl:value-of select="@RedniBroj"/>
                                </fo:block>


                                <xsl:for-each select="a:Glava">
                                    <fo:block id="{@Id}" font-family="Arial" text-align="center" font-size="19"
                                              margin="20px">
                                        <fox:destination internal-destination="{@Id}"/>
                                        <xsl:value-of select="@Naslov"/>

                                    </fo:block>
                                    <fo:block font-family="Arial" text-align="center" font-size="19" margin="20px">
                                        Glava
                                        <xsl:value-of select="@RedniBroj"/>
                                    </fo:block>

                                    <xsl:for-each select="a:Odeljak">

                                        <fo:block id="{@Id}" font-family="Arial" text-align="center" font-size="17"
                                                  margin="20px">
                                            <fox:destination internal-destination="{@Id}"/>
                                            <xsl:value-of select="@Naslov"/>

                                        </fo:block>
                                        <fo:block font-family="Arial" text-align="center" font-size="17" margin="20px">
                                            Odeljak
                                            <xsl:value-of select="@RedniBroj"/>

                                        </fo:block>

                                        <xsl:choose>
                                            <xsl:when test="a:Pododeljak">

                                                <xsl:for-each select="a:Pododeljak">
                                                    <fo:block id="{@Id}" font-family="Arial" text-align="center"
                                                              font-size="15" margin="20px">
                                                        <fox:destination internal-destination="{@Id}"/>
                                                        <xsl:value-of select="@Naslov"/>

                                                    </fo:block>
                                                    <fo:block font-family="Arial" text-align="center" font-size="15"
                                                              margin="20px">
                                                        Pododeljak
                                                        <xsl:value-of select="@RedniBroj"/>

                                                    </fo:block>


                                                    <xsl:for-each select="a:Clan">

                                                        <fo:block id="{@Id}" font-family="Arial" text-align="center"
                                                                  font-size="14" margin="20px">
                                                            <fox:destination internal-destination="{@Id}"/>
                                                            <xsl:value-of select="@Naslov"/>

                                                        </fo:block>


                                                        <fo:block font-family="Arial" text-align="center" font-size="14"
                                                                  margin="20px">
                                                            Član
                                                            <xsl:value-of select="@RedniBroj"/>

                                                            <xsl:apply-templates select="a:Sadrzaj"/>


                                                        </fo:block>

                                                        <xsl:for-each select="a:Stav">
                                                            <fo:block id="{@Id}" font-family="Arial" text-align="center"
                                                                      font-size="13" margin="20px">
                                                                <fox:destination internal-destination="{@Id}"/>
                                                                <xsl:value-of select="@Naslov"/>
                                                            </fo:block>
                                                            <fo:block font-family="Arial" font-size="8">
                                                                <xsl:apply-templates select="a:Sadrzaj"/>

                                                            </fo:block>
                                                            <xsl:for-each select="a:Tacka">
                                                                <fo:block id="{@Id}" font-family="Arial"
                                                                          text-align="center" font-size="12"
                                                                          margin="20px">
                                                                    <fox:destination internal-destination="{@Id}"/>
                                                                    <xsl:value-of select="@Naslov"/>
                                                                </fo:block>
                                                                <fo:block font-family="Arial" font-size="8">
                                                                    <xsl:apply-templates select="a:Sadrzaj"/>


                                                                </fo:block>
                                                                <xsl:for-each select="a:Podtacka">
                                                                    <fo:block id="{@Id}" font-family="Arial"
                                                                              text-align="center" font-size="11"
                                                                              margin="20px">
                                                                        <fox:destination internal-destination="{@Id}"/>
                                                                        <xsl:value-of select="@Naslov"/>
                                                                    </fo:block>
                                                                    <fo:block font-family="Arial" font-size="8">
                                                                        <xsl:apply-templates select="a:Sadrzaj"/>
                                                                        <xsl:apply-templates select="a:Alineja"/>


                                                                    </fo:block>


                                                                </xsl:for-each>
                                                            </xsl:for-each>
                                                        </xsl:for-each>

                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </xsl:when>
                                            <xsl:otherwise>

                                                <xsl:for-each select="a:Clan">

                                                    <fo:block id="{@Id}" font-family="Arial" text-align="center"
                                                              font-size="14" margin="20px">
                                                        <fox:destination internal-destination="{@Id}"/>
                                                        <xsl:value-of select="@Naslov"/>

                                                    </fo:block>
                                                    <fo:block font-family="Arial" text-align="center" font-size="14"
                                                              margin="20px">
                                                        Član
                                                        <xsl:value-of select="@RedniBroj"/>

                                                    </fo:block>
                                                    <fo:block font-family="Arial" font-size="8">


                                                        <xsl:apply-templates select="a:Sadrzaj"/>


                                                    </fo:block>
                                                    <xsl:for-each select="a:Stav">

                                                        <fo:block id="{@Id}" font-family="Arial" text-align="center"
                                                                  font-size="13" margin="20px">
                                                            <fox:destination internal-destination="{@Id}"/>
                                                            <xsl:value-of select="@Naslov"/>
                                                            <xsl:apply-templates select="a:Sadrzaj"/>
                                                        </fo:block>
                                                        <fo:block font-family="Arial" font-size="8">
                                                            <xsl:apply-templates select="a:Sadrzaj"/>

                                                        </fo:block>
                                                        <xsl:for-each select="a:Tacka">
                                                            <fo:block id="{@Id}" font-family="Arial" text-align="center"
                                                                      font-size="12" margin="20px">
                                                                <fox:destination internal-destination="{@Id}"/>
                                                                <xsl:value-of select="@Naslov"/>
                                                            </fo:block>
                                                            <fo:block font-family="Arial" font-size="8">
                                                                <xsl:apply-templates select="a:Sadrzaj"/>

                                                            </fo:block>
                                                            <xsl:for-each select="a:Podtacka">
                                                                <fo:block id="{@Id}" font-family="Arial"
                                                                          text-align="center" font-size="11"
                                                                          margin="20px">
                                                                    <fox:destination internal-destination="{@Id}"/>
                                                                    <xsl:value-of select="@Naslov"/>
                                                                    <xsl:apply-templates select="a:Sadrzaj"/>
                                                                    <xsl:apply-templates select="a:Alineja"/>
                                                                </fo:block>



                                                            </xsl:for-each>
                                                        </xsl:for-each>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </xsl:otherwise>
                                        </xsl:choose>

                                    </xsl:for-each>

                                </xsl:for-each>

                            </xsl:for-each>



                    </fo:block>
                </fo:flow>
            </fo:page-sequence>

        </fo:root>
    </xsl:template>

    <xsl:template match="a:Sadrzaj">

            <xsl:apply-templates />

    </xsl:template>
    <xsl:template match="a:Sadrzaj//*">
        <xsl:copy>
            <xsl:copy-of select="@*" />
            <xsl:apply-templates />
        </xsl:copy>
    </xsl:template>
    <xsl:template match="a:Sadrzaj//a:Referenca">
        <xsl:variable name="ref" select="@ReferencaURI"/>
        <xsl:variable name="pre" select="substring-before($ref,'#')"/>
        <xsl:variable name="posle" select="substring-after($ref,'#')"/>
        <xsl:variable name="linkPrep" select="concat($pre,'/pdf#')"/>
        <xsl:variable name="link" select="concat($linkPrep,$posle)"/>
        <fo:basic-link color="blue" external-destination="http://localhost:8080/api/akti/{$link}">
            <xsl:value-of select="text()"/></fo:basic-link>
    </xsl:template>

    <xsl:template match="text()" />

    <!-- override rule: copy any text node beneath description -->
    <xsl:template match="a:Sadrzaj//text()">
        <xsl:copy-of select="." />
    </xsl:template>

    <xsl:template match="a:Alineja">

            <xsl:apply-templates />

    </xsl:template>

    <!-- default rule: copy any node beneath <description> -->
    <xsl:template match="a:Alineja//*">
        <xsl:copy>
            <xsl:copy-of select="@*" />
            <xsl:apply-templates />
        </xsl:copy>
    </xsl:template>

    <!-- override rule: <link> nodes get special treatment -->
    <xsl:template match="a:Alineja//a:Referenca">
        <xsl:variable name="ref" select="@ReferencaURI"/>
        <xsl:variable name="pre" select="substring-before($ref,'#')"/>
        <xsl:variable name="posle" select="substring-after($ref,'#')"/>
        <xsl:variable name="linkPrep" select="concat($pre,'/pdf#')"/>
        <xsl:variable name="link" select="concat($linkPrep,$posle)"/>
        <fo:basic-link color="blue" external-destination="http://localhost:8080/api/akti/{$link}">
            <xsl:value-of select="text()"/></fo:basic-link>


    </xsl:template>

    <!-- override rule: copy any text node beneath description -->
    <xsl:template match="a:Alineja//text()">
        <xsl:copy-of select="." />
    </xsl:template>



</xsl:stylesheet>
