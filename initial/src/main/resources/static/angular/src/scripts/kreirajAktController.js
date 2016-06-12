/**
 * Created by Aleksa on 6/7/2016.
 */
module.exports = [
    '$scope', '$http',
    function ctrl($scope, $http){

        var docSpec = {

            onchange: function(){
                console.log("I been changed now!")
            },
            validate: function(obj){
                console.log("I be validatin' now!")
            },
            elements: {
                "Akt":{
                    menu: [ {
                                caption: "Dodaj <Podnosilac>",
                                action: Xonomy.newElementChild,
                                actionParameter: "<Podnosilac/>"
                            },
                            {
                                caption: "Dodaj <Deo>",
                                action: Xonomy.newElementChild,
                                actionParameter: "<Deo/>"
                            },
                            {
                                caption: "Dodaj @Naslov",
                                action: Xonomy.newAttribute,
                                actionParameter: {name: "Naslov", value: ""},
                                hideIf: function(jsElement) {
                                    return jsElement.hasAttribute("Naslov");
                                }
                            },
                            {
                                caption: "Dodaj @Redni @broj",
                                action: Xonomy.newAttribute,
                                actionParameter: {name: "RedniBroj", value: ""},
                                hideIf: function(jsElement){
                                    return jsElement.hasAttribute("RedniBroj");
                                }
                            }
                    ],
                    attributes:{
                        "Naslov":{
                            asker: Xonomy.askString
                        },
                        "RedniBroj":{
                            asker: Xonomy.askString
                        },
                        "DatumPodnosenja":{
                            asker: Xonomy.askString
                        },
                        "Status": {
                            asker: Xonomy.askPicklist,
                            askerParameter: [
                                "Usvojen", "U proceduri", "Odbijen"
                            ]
                        }
                    }
                },
                "Podnosilac":{
                    hasText: true,
                    menu: [{
                        caption: "Dodaj novi <Podnosilac>",
                        action: Xonomy.newElementAfter,
                        actionParameter: "<Podnosilac/>"

                    }, {
                        caption: "Obriši",
                        action: Xonomy.deleteElement
                    }],
                    mustBeBefore: ["Deo"]
                },
                "Deo":{
                    menu: [{
                        caption: "Dodaj novi <Deo>",
                        action: Xonomy.newElementAfter,
                        actionParameter: "<Deo/>"

                    }, {
                        caption: "Dodaj novu <Glava>",
                        action: Xonomy.newElementChild,
                        actionParameter: "<Glava/>"

                    },
                    {
                        caption: "Dodaj @Naslov",
                        action: Xonomy.newAttribute,
                        actionParameter: {name: "Naslov", value: ""},
                        hideIf: function(jsElement) {
                            return jsElement.hasAttribute("Naslov");
                        }
                    },
                    {
                        caption: "Dodaj @Redni @broj",
                        action: Xonomy.newAttribute,
                        actionParameter: {name: "RedniBroj", value: ""},
                        hideIf: function(jsElement){
                            return jsElement.hasAttribute("RedniBroj");
                        }
                    },
                    {
                        caption: "Obriši",
                        action: Xonomy.deleteElement
                    }],
                    mustBeAfter: ["Podnosilac"],
                    attributes:{
                        "Naslov":{
                            asker: Xonomy.askString
                        },
                        "RedniBroj":{
                            asker: Xonomy.askString
                        }
                    }
                },
                "Glava":{
                    menu: [{
                        caption: "Dodaj novi <Glava>",
                        action: Xonomy.newElementAfter,
                        actionParameter: "<Glava/>"

                    },{
                        caption: "Dodaj novi <Odeljak>",
                        action: Xonomy.newElementChild,
                        actionParameter: "<Odeljak/>"

                    },{
                        caption: "Dodaj @Naslov",
                        action: Xonomy.newAttribute,
                        actionParameter: {name: "Naslov", value: ""},
                        hideIf: function(jsElement) {
                            return jsElement.hasAttribute("Naslov");
                        }
                    },{
                            caption: "Dodaj @Redni @broj",
                            action: Xonomy.newAttribute,
                            actionParameter: {name: "RedniBroj", value: ""},
                            hideIf: function(jsElement){
                                return jsElement.hasAttribute("RedniBroj");
                            }
                    },{
                        caption: "Obriši",
                        action: Xonomy.deleteElement
                    }],
                    attributes:{
                        "Naslov":{
                            asker: Xonomy.askString
                        },
                        "RedniBroj":{
                            asker: Xonomy.askString
                        }
                    }
                },
                "Odeljak":{
                    menu: [{
                        caption: "Dodaj novi <Odeljak>",
                        action: Xonomy.newElementAfter,
                        actionParameter: "<Odeljak/>"

                    },{
                        caption: "Dodaj novi <Pododeljak>",
                        action: Xonomy.newElementChild,
                        actionParameter: "<Pododeljak/>",
                        hideIf: function(jsElement){
                            return jsElement.hasChildElement("Clan");
                        }
                    },{
                        caption: "Dodaj novi <Clan>",
                        action: Xonomy.newElementChild,
                        actionParameter: "<Clan/>",
                        hideIf: function(jsElement){
                            return jsElement.hasChildElement("Pododeljak");
                        }
                    },{
                        caption: "Dodaj @Naslov",
                        action: Xonomy.newAttribute,
                        actionParameter: {name: "Naslov", value: ""},
                        hideIf: function(jsElement) {
                            return jsElement.hasAttribute("Naslov");
                        }
                    },{
                        caption: "Dodaj @Redni @broj",
                        action: Xonomy.newAttribute,
                        actionParameter: {name: "RedniBroj", value: ""},
                        hideIf: function(jsElement){
                            return jsElement.hasAttribute("RedniBroj");
                        }
                    },{
                        caption: "Obriši",
                        action: Xonomy.deleteElement
                    }],
                    attributes:{
                        "Naslov":{
                            asker: Xonomy.askString
                        },
                        "RedniBroj":{
                            asker: Xonomy.askString
                        }
                    }

                },
                "Pododeljak":{

                    menu: [{
                        caption: "Dodaj novi <Pododeljak>",
                        action: Xonomy.newElementAfter,
                        actionParameter: "<Pododeljak/>"

                    },{
                        caption: "Dodaj novi <Clan>",
                        action: Xonomy.newElementChild,
                        actionParameter: "<Clan/>"
                    },{
                        caption: "Dodaj @Naslov",
                        action: Xonomy.newAttribute,
                        actionParameter: {name: "Naslov", value: ""},
                        hideIf: function(jsElement) {
                            return jsElement.hasAttribute("Naslov");
                        }
                    },{
                        caption: "Dodaj @Redni @broj",
                        action: Xonomy.newAttribute,
                        actionParameter: {name: "RedniBroj", value: ""},
                        hideIf: function(jsElement){
                            return jsElement.hasAttribute("RedniBroj");
                        }
                    },{
                        caption: "Obriši",
                        action: Xonomy.deleteElement
                    }],
                    attributes:{
                        "Naslov":{
                            asker: Xonomy.askString
                        },
                        "RedniBroj":{
                            asker: Xonomy.askString
                        }
                    }

                },
                "Clan":{
                    menu: [{
                        caption: "Dodaj novi <Clan>",
                        action: Xonomy.newElementAfter,
                        actionParameter: "<Clan/>"

                    },{
                        caption: "Dodaj novi <Stav>",
                        action: Xonomy.newElementChild,
                        actionParameter: "<Stav/>"
                    },{
                        caption: "Dodaj novi <Sadrzaj>",
                        action: Xonomy.newElementChild,
                        actionParameter: "<Sadrzaj/>"
                    },{
                        caption: "Dodaj @Naslov",
                        action: Xonomy.newAttribute,
                        actionParameter: {name: "Naslov", value: ""},
                        hideIf: function(jsElement) {
                            return jsElement.hasAttribute("Naslov");
                        }
                    },{
                        caption: "Dodaj @Redni @broj",
                        action: Xonomy.newAttribute,
                        actionParameter: {name: "RedniBroj", value: ""},
                        hideIf: function(jsElement){
                            return jsElement.hasAttribute("RedniBroj");
                        }
                    },{
                        caption: "Obriši",
                        action: Xonomy.deleteElement
                    }],
                    attributes:{
                        "Naslov":{
                            asker: Xonomy.askString
                        },
                        "RedniBroj":{
                            asker: Xonomy.askString
                        }
                    }
                },
                "Stav":{
                    menu: [{
                        caption: "Dodaj novi <Stav>",
                        action: Xonomy.newElementAfter,
                        actionParameter: "<Stav/>"

                    },{
                        caption: "Dodaj novi <Tacka>",
                        action: Xonomy.newElementChild,
                        actionParameter: "<Tacka/>"
                    },{
                        caption: "Dodaj novi <Sadrzaj>",
                        action: Xonomy.newElementChild,
                        actionParameter: "<Sadrzaj/>"
                    },{
                        caption: "Dodaj @Naslov",
                        action: Xonomy.newAttribute,
                        actionParameter: {name: "Naslov", value: ""},
                        hideIf: function(jsElement) {
                            return jsElement.hasAttribute("Naslov");
                        }
                    },{
                        caption: "Dodaj @Redni @broj",
                        action: Xonomy.newAttribute,
                        actionParameter: {name: "RedniBroj", value: ""},
                        hideIf: function(jsElement){
                            return jsElement.hasAttribute("RedniBroj");
                        }
                    },{
                        caption: "Obriši",
                        action: Xonomy.deleteElement
                    }],
                    attributes:{
                        "Naslov":{
                            asker: Xonomy.askString
                        },
                        "RedniBroj":{
                            asker: Xonomy.askString
                        }
                    }
                },
                "Tacka":{
                    menu: [{
                        caption: "Dodaj novi <Tacka>",
                        action: Xonomy.newElementAfter,
                        actionParameter: "<Tacka/>"

                    },{
                        caption: "Dodaj novi <Podtacka>",
                        action: Xonomy.newElementChild,
                        actionParameter: "<Podtacka/>"
                    },{
                        caption: "Dodaj novi <Sadrzaj>",
                        action: Xonomy.newElementChild,
                        actionParameter: "<Sadrzaj/>"
                    },{
                        caption: "Dodaj @Naslov",
                        action: Xonomy.newAttribute,
                        actionParameter: {name: "Naslov", value: ""},
                        hideIf: function(jsElement) {
                            return jsElement.hasAttribute("Naslov");
                        }
                    },{
                        caption: "Dodaj @Redni @broj",
                        action: Xonomy.newAttribute,
                        actionParameter: {name: "RedniBroj", value: ""},
                        hideIf: function(jsElement){
                            return jsElement.hasAttribute("RedniBroj");
                        }
                    },{
                        caption: "Obriši",
                        action: Xonomy.deleteElement
                    }],
                    attributes:{
                        "Naslov":{
                            asker: Xonomy.askString
                        },
                        "RedniBroj":{
                            asker: Xonomy.askString
                        }
                    }
                },
                "Podtacka":{
                    menu: [{
                        caption: "Dodaj novi <Podtacka>",
                        action: Xonomy.newElementAfter,
                        actionParameter: "<Podtacka/>"

                    },{
                        caption: "Dodaj novi <Alineja>",
                        action: Xonomy.newElementChild,
                        actionParameter: "<Alineja/>"
                    },{
                        caption: "Dodaj novi <Sadrzaj>",
                        action: Xonomy.newElementChild,
                        actionParameter: "<Sadrzaj/>"
                    },{
                        caption: "Dodaj @Naslov",
                        action: Xonomy.newAttribute,
                        actionParameter: {name: "Naslov", value: ""},
                        hideIf: function(jsElement) {
                            return jsElement.hasAttribute("Naslov");
                        }
                    },{
                        caption: "Dodaj @Redni @broj",
                        action: Xonomy.newAttribute,
                        actionParameter: {name: "RedniBroj", value: ""},
                        hideIf: function(jsElement){
                            return jsElement.hasAttribute("RedniBroj");
                        }
                    },{
                        caption: "Obriši",
                        action: Xonomy.deleteElement
                    }],
                    attributes:{
                        "Naslov":{
                            asker: Xonomy.askString
                        },
                        "RedniBroj":{
                            asker: Xonomy.askString
                        }
                    }
                },
                "Alineja":{
                    hasText: true,
                    inlineMenu: [{
                        caption: "Wrap with <Referenca>",
                        action: Xonomy.wrap,
                        actionParameter: {template: "<Referenca ReferencaURI=''>$</Referenca>", placeholder: "$"}
                    }],
                    menu: [{
                        caption: "Obriši",
                        action: Xonomy.deleteElement
                    }]
                },
                "Sadrzaj":{
                    hasText: true,
                    inlineMenu: [{
                        caption: "Wrap with <Referenca>",
                        action: Xonomy.wrap,
                        actionParameter: {template: "<Referenca ReferencaURI=''>$</Referenca>", placeholder: "$"}
                    }],
                    menu: [{
                        caption: "Obriši",
                        action: Xonomy.deleteElement
                    }]
                },
                "Referenca": {
                    menu: [{
                        caption: "Obriši",
                        action: Xonomy.deleteElement
                    }],
                    attributes:{
                        "ReferencaURI":{
                            asker: Xonomy.askString
                        }
                    }
                }
            }

        };


        var xml="<Akt xmlns='http://www.xmlProjekat.com/akt' Naslov='' DatumPodnosenja=''><Podnosilac></Podnosilac><Deo></Deo></Akt>";
        var editor=document.getElementById("editor");
        Xonomy.setMode("laic");
        Xonomy.render(xml, editor, docSpec);

        $scope.submit = function () {
            alert("as");

            $http.post("/api/akti/dodaj", Xonomy.harvest()).success(function(data, status) {
                alert("Uspešno dodat akt.");
            })
        }

    }
];