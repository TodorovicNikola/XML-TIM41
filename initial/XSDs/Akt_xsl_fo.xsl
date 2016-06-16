<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:a="http://www.xmlProjekat.com/akt"
                xmlns:fox="http://xmlgraphics.apache.org/fop/extensions"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0">

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
                        <xsl:for-each select="a:Akt">

                            <fo:block font-family="Arial" text-align="center" font-size="23" margin="20px">
                                <xsl:value-of select="@Naslov"/>

                            </fo:block>
                            <xsl:for-each select="//a:Deo">
                                <fo:block id="{@Id}" font-family="Arial" text-align="center" font-size="21"
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
                                        <fo:block font-family="Arial" font-size="8">
                                            <xsl:value-of select="a:Sadrzaj/text()"/>
                                            <xsl:for-each select="a:Sadrzaj/a:Referenca">
                                                <fo:block font-family="Arial" font-size="10">
                                                    <xsl:variable name="ref" select="@ReferencaURI"/>
                                                    <xsl:variable name="pre" select="substring-before($ref,'#')"/>
                                                    <xsl:variable name="posle" select="substring-after($ref,'#')"/>
                                                    <xsl:variable name="linkPrep" select="concat($pre,'/pdf#')"/>
                                                    <xsl:variable name="link" select="concat($linkPrep,$posle)"/>

                                                    <fo:basic-link color="blue"
                                                                   external-destination="http://localhost:8080/api/akti/{$link}">
                                                        <xsl:value-of select="text()"/>
                                                    </fo:basic-link>
                                                </fo:block>
                                            </xsl:for-each>
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
                                                    <fo:block font-family="Arial" font-size="8">
                                                        <xsl:value-of select="a:Sadrzaj/text()"/>
                                                        <xsl:for-each select="a:Sadrzaj/a:Referenca">
                                                            <fo:block font-family="Arial" font-size="10">
                                                                <xsl:variable name="ref" select="@ReferencaURI"/>
                                                                <xsl:variable name="pre"
                                                                              select="substring-before($ref,'#')"/>
                                                                <xsl:variable name="posle"
                                                                              select="substring-after($ref,'#')"/>
                                                                <xsl:variable name="linkPrep"
                                                                              select="concat($pre,'/pdf#')"/>
                                                                <xsl:variable name="link"
                                                                              select="concat($linkPrep,$posle)"/>

                                                                <fo:basic-link color="blue"
                                                                               external-destination="http://localhost:8080/api/akti/{$link}">
                                                                    <xsl:value-of select="text()"/>
                                                                </fo:basic-link>
                                                            </fo:block>
                                                        </xsl:for-each>
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

                                                        </fo:block>

                                                        <fo:block font-family="Arial" font-size="8">
                                                            <xsl:value-of select="a:Sadrzaj/text()"/>
                                                            <xsl:for-each select="a:Sadrzaj/a:Referenca">

                                                                <fo:block font-family="Arial" font-size="10">

                                                                    <xsl:variable name="ref" select="@ReferencaURI"/>
                                                                    <xsl:variable name="pre"
                                                                                  select="substring-before($ref,'#')"/>
                                                                    <xsl:variable name="posle"
                                                                                  select="substring-after($ref,'#')"/>
                                                                    <xsl:variable name="linkPrep"
                                                                                  select="concat($pre,'/pdf#')"/>
                                                                    <xsl:variable name="link"
                                                                                  select="concat($linkPrep,$posle)"/>

                                                                    <fo:basic-link color="blue"
                                                                                   external-destination="http://localhost:8080/api/akti/{$link}">
                                                                        g
                                                                    </fo:basic-link>
                                                                </fo:block>
                                                            </xsl:for-each>

                                                        </fo:block>
                                                        <xsl:for-each select="a:Stav">
                                                            <fo:block id="{@Id}" font-family="Arial" text-align="center"
                                                                      font-size="13" margin="20px">
                                                                <fox:destination internal-destination="{@Id}"/>
                                                                <xsl:value-of select="@Naslov"/>
                                                            </fo:block>
                                                            <fo:block font-family="Arial" font-size="8">
                                                                <xsl:value-of select="a:Sadrzaj/text()"/>
                                                                <xsl:for-each select="a:Sadrzaj/a:Referenca">
                                                                    <fo:block font-family="Arial" font-size="10">
                                                                        <xsl:variable name="ref"
                                                                                      select="@ReferencaURI"/>
                                                                        <xsl:variable name="pre"
                                                                                      select="substring-before($ref,'#')"/>
                                                                        <xsl:variable name="posle"
                                                                                      select="substring-after($ref,'#')"/>
                                                                        <xsl:variable name="linkPrep"
                                                                                      select="concat($pre,'/pdf#')"/>
                                                                        <xsl:variable name="link"
                                                                                      select="concat($linkPrep,$posle)"/>

                                                                        <fo:basic-link color="blue"
                                                                                       external-destination="http://localhost:8080/api/akti/{$link}">
                                                                            <xsl:value-of select="text()"/>
                                                                        </fo:basic-link>
                                                                    </fo:block>
                                                                </xsl:for-each>
                                                            </fo:block>
                                                            <xsl:for-each select="a:Tacka">
                                                                <fo:block id="{@Id}" font-family="Arial"
                                                                          text-align="center" font-size="12"
                                                                          margin="20px">
                                                                    <fox:destination internal-destination="{@Id}"/>
                                                                    <xsl:value-of select="@Naslov"/>
                                                                </fo:block>
                                                                <fo:block font-family="Arial" font-size="8">
                                                                    <xsl:value-of select="a:Sadrzaj/text()"/>
                                                                    <xsl:for-each select="a:Sadrzaj/a:Referenca">
                                                                        <fo:block font-family="Arial" font-size="10">
                                                                            <xsl:variable name="ref"
                                                                                          select="@ReferencaURI"/>
                                                                            <xsl:variable name="pre"
                                                                                          select="substring-before($ref,'#')"/>
                                                                            <xsl:variable name="posle"
                                                                                          select="substring-after($ref,'#')"/>
                                                                            <xsl:variable name="linkPrep"
                                                                                          select="concat($pre,'/pdf#')"/>
                                                                            <xsl:variable name="link"
                                                                                          select="concat($linkPrep,$posle)"/>

                                                                            <fo:basic-link color="blue"
                                                                                           external-destination="http://localhost:8080/api/akti/{$link}">
                                                                                <xsl:value-of select="text()"/>
                                                                            </fo:basic-link>
                                                                        </fo:block>
                                                                    </xsl:for-each>

                                                                </fo:block>
                                                                <xsl:for-each select="a:Podtacka">
                                                                    <fo:block id="{@Id}" font-family="Arial"
                                                                              text-align="center" font-size="11"
                                                                              margin="20px">
                                                                        <fox:destination internal-destination="{@Id}"/>
                                                                        <xsl:value-of select="@Naslov"/>
                                                                    </fo:block>
                                                                    <fo:block font-family="Arial" font-size="8">
                                                                        <xsl:value-of select="a:Sadrzaj/text()"/>
                                                                        <xsl:for-each select="a:Sadrzaj/a:Referenca">
                                                                            <fo:block font-family="Arial"
                                                                                      font-size="10">
                                                                                <xsl:variable name="ref"
                                                                                              select="@ReferencaURI"/>
                                                                                <xsl:variable name="pre"
                                                                                              select="substring-before($ref,'#')"/>
                                                                                <xsl:variable name="posle"
                                                                                              select="substring-after($ref,'#')"/>
                                                                                <xsl:variable name="linkPrep"
                                                                                              select="concat($pre,'/pdf#')"/>
                                                                                <xsl:variable name="link"
                                                                                              select="concat($linkPrep,$posle)"/>

                                                                                <fo:basic-link color="blue"
                                                                                               external-destination="http://localhost:8080/api/akti/{$link}">
                                                                                    <xsl:value-of select="text()"/>
                                                                                </fo:basic-link>
                                                                            </fo:block>
                                                                        </xsl:for-each>

                                                                    </fo:block>

                                                                    <xsl:for-each select="a:Alineja">
                                                                        <fo:block font-family="Arial" font-size="10">
                                                                            <xsl:value-of select="text()"/>
                                                                        </fo:block>
                                                                        <xsl:for-each select="a:Referenca">
                                                                            <fo:block font-family="Arial"
                                                                                      font-size="10">
                                                                                <xsl:variable name="ref"
                                                                                              select="@ReferencaURI"/>
                                                                                <xsl:variable name="pre"
                                                                                              select="substring-before($ref,'#')"/>
                                                                                <xsl:variable name="posle"
                                                                                              select="substring-after($ref,'#')"/>
                                                                                <xsl:variable name="linkPrep"
                                                                                              select="concat($pre,'/pdf#')"/>
                                                                                <xsl:variable name="link"
                                                                                              select="concat($linkPrep,$posle)"/>

                                                                                <fo:basic-link color="blue"
                                                                                               external-destination="http://localhost:8080/api/akti/{$link}">
                                                                                    <xsl:value-of select="text()"/>
                                                                                </fo:basic-link>


                                                                            </fo:block>

                                                                        </xsl:for-each>
                                                                    </xsl:for-each>
                                                                </xsl:for-each>
                                                            </xsl:for-each>
                                                        </xsl:for-each>

                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </xsl:when>
                                            <xsl:otherwise>

                                                <xsl:for-each select="a:Clan">
                                                    Ovde2
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


                                                        <xsl:value-of select="a:Sadrzaj/text()"/>
                                                        <xsl:for-each select="a:Sadrzaj/a:Referenca">
                                                            <fo:block font-family="Arial" font-size="10">
                                                                <xsl:variable name="ref" select="@ReferencaURI"/>
                                                                <xsl:variable name="pre"
                                                                              select="substring-before($ref,'#')"/>
                                                                <xsl:variable name="posle"
                                                                              select="substring-after($ref,'#')"/>
                                                                <xsl:variable name="linkPrep"
                                                                              select="concat($pre,'/pdf#')"/>
                                                                <xsl:variable name="link"
                                                                              select="concat($linkPrep,$posle)"/>

                                                                <fo:basic-link color="blue"
                                                                               external-destination="http://localhost:8080/api/akti/{$link}">
                                                                    <xsl:value-of select="text()"/>
                                                                </fo:basic-link>
                                                            </fo:block>
                                                        </xsl:for-each>

                                                    </fo:block>
                                                    <xsl:for-each select="a:Stav">
                                                        stav
                                                        <fo:block id="{@Id}" font-family="Arial" text-align="center"
                                                                  font-size="13" margin="20px">
                                                            <fox:destination internal-destination="{@Id}"/>
                                                            <xsl:value-of select="@Naslov"/>
                                                        </fo:block>
                                                        <fo:block font-family="Arial" font-size="8">
                                                            <xsl:value-of select="a:Sadrzaj/text()"/>
                                                            <xsl:for-each select="a:Sadrzaj/a:Referenca">
                                                                <fo:block font-family="Arial" font-size="10">
                                                                    <xsl:variable name="ref" select="@ReferencaURI"/>
                                                                    <xsl:variable name="pre"
                                                                                  select="substring-before($ref,'#')"/>
                                                                    <xsl:variable name="posle"
                                                                                  select="substring-after($ref,'#')"/>
                                                                    <xsl:variable name="linkPrep"
                                                                                  select="concat($pre,'/pdf#')"/>
                                                                    <xsl:variable name="link"
                                                                                  select="concat($linkPrep,$posle)"/>

                                                                    <fo:basic-link color="blue"
                                                                                   external-destination="http://localhost:8080/api/akti/{$link}">
                                                                        <xsl:value-of select="text()"/>
                                                                    </fo:basic-link>
                                                                </fo:block>
                                                            </xsl:for-each>
                                                        </fo:block>
                                                        <xsl:for-each select="a:Tacka">
                                                            <fo:block id="{@Id}" font-family="Arial" text-align="center"
                                                                      font-size="12" margin="20px">
                                                                <fox:destination internal-destination="{@Id}"/>
                                                                <xsl:value-of select="@Naslov"/>
                                                            </fo:block>
                                                            <fo:block font-family="Arial" font-size="8">
                                                                <xsl:value-of select="a:Sadrzaj/text()"/>
                                                                <xsl:for-each select="a:Sadrzaj/a:Referenca">
                                                                    <fo:block font-family="Arial" font-size="10">
                                                                        <xsl:variable name="ref"
                                                                                      select="@ReferencaURI"/>
                                                                        <xsl:variable name="pre"
                                                                                      select="substring-before($ref,'#')"/>
                                                                        <xsl:variable name="posle"
                                                                                      select="substring-after($ref,'#')"/>
                                                                        <xsl:variable name="linkPrep"
                                                                                      select="concat($pre,'/pdf#')"/>
                                                                        <xsl:variable name="link"
                                                                                      select="concat($linkPrep,$posle)"/>

                                                                        <fo:basic-link color="blue"
                                                                                       external-destination="http://localhost:8080/api/akti/{$link}">
                                                                            <xsl:value-of select="text()"/>
                                                                        </fo:basic-link>
                                                                    </fo:block>
                                                                </xsl:for-each>
                                                            </fo:block>
                                                            <xsl:for-each select="a:Podtacka">
                                                                <fo:block id="{@Id}" font-family="Arial"
                                                                          text-align="center" font-size="11"
                                                                          margin="20px">
                                                                    <fox:destination internal-destination="{@Id}"/>
                                                                    <xsl:value-of select="@Naslov"/>
                                                                </fo:block>
                                                                <fo:block font-family="Arial" font-size="8">
                                                                    <xsl:value-of select="a:Sadrzaj/text()"/>
                                                                    <xsl:for-each select="a:Sadrzaj/a:Referenca">
                                                                        <fo:block font-family="Arial" font-size="10">
                                                                            <xsl:variable name="ref"
                                                                                          select="@ReferencaURI"/>
                                                                            <xsl:variable name="pre"
                                                                                          select="substring-before($ref,'#')"/>
                                                                            <xsl:variable name="posle"
                                                                                          select="substring-after($ref,'#')"/>
                                                                            <xsl:variable name="linkPrep"
                                                                                          select="concat($pre,'/pdf#')"/>
                                                                            <xsl:variable name="link"
                                                                                          select="concat($linkPrep,$posle)"/>

                                                                            <fo:basic-link color="blue"
                                                                                           external-destination="http://localhost:8080/api/akti/{$link}">
                                                                                <xsl:value-of select="text()"/>
                                                                            </fo:basic-link>
                                                                        </fo:block>
                                                                    </xsl:for-each>
                                                                </fo:block>

                                                                <xsl:for-each select="a:Alineja">
                                                                    <fo:block font-family="Arial" font-size="10">
                                                                        <xsl:value-of select="text()"/>
                                                                    </fo:block>
                                                                    <xsl:for-each select="a:Referenca">
                                                                        <fo:block font-family="Arial" font-size="10">
                                                                            <xsl:variable name="ref"
                                                                                          select="@ReferencaURI"/>
                                                                            <xsl:variable name="pre"
                                                                                          select="substring-before($ref,'#')"/>
                                                                            <xsl:variable name="posle"
                                                                                          select="substring-after($ref,'#')"/>
                                                                            <xsl:variable name="linkPrep"
                                                                                          select="concat($pre,'/pdf#')"/>
                                                                            <xsl:variable name="link"
                                                                                          select="concat($linkPrep,$posle)"/>

                                                                            <fo:basic-link color="blue"
                                                                                           external-destination="http://localhost:8080/api/akti/{$link}">
                                                                                <xsl:value-of select="text()"/>
                                                                            </fo:basic-link>


                                                                        </fo:block>

                                                                    </xsl:for-each>
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
                        </xsl:for-each>


                    </fo:block>
                </fo:flow>
            </fo:page-sequence>

        </fo:root>
    </xsl:template>


</xsl:stylesheet>
