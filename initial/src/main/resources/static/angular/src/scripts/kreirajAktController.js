/**
 * Created by Aleksa on 6/7/2016.
 */
module.exports = [
    '$scope', '$http', '$window',
    function ctrl($scope, $http, $window){

        var docSpec1 = {

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
                    menu: [{
                        caption: "Obriši",
                        action: Xonomy.deleteElement
                    }]
                },
                "Sadrzaj":{
                    hasText: true,
                    menu: [{
                        caption: "Obriši",
                        action: Xonomy.deleteElement
                    }]
                }
            }

        };


        var docSpec2 = {

            onchange: function(){
                console.log("I been changed now!")
            },
            validate: function(obj){
                console.log("I be validatin' now!")
            },
            elements: {
                "Akt":{

                },
                "Podnosilac":{
                    hasText: true,
                    mustBeBefore: ["Deo"]
                },
                "Deo":{
                    mustBeAfter: ["Podnosilac"]
                },
                "Glava":{

                },
                "Odeljak":{

                },
                "Pododeljak":{

                },
                "Clan":{

                },
                "Stav":{

                },
                "Tacka":{

                },
                "Podtacka":{

                },
                "Alineja":{
                    hasText: true,
                    inlineMenu: [{
                        caption: "Wrap with <Referenca>",
                        action: Xonomy.wrap,
                        actionParameter: {template: "<Referenca ReferencaURI=''>$</Referenca>", placeholder: "$"}
                    }],
                },
                "Sadrzaj":{
                    hasText: true,
                    inlineMenu: [{
                        caption: "Wrap with <Referenca>",
                        action: Xonomy.wrap,
                        actionParameter: {template: "<Referenca ReferencaURI=''>$</Referenca>", placeholder: "$"}
                    }],
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

        var xml="<Akt xmlns='http://www.xmlProjekat.com/akt' Naslov=''><Podnosilac></Podnosilac><Deo></Deo></Akt>";
        var editor=document.getElementById("editor");
        Xonomy.setMode("laic");
        Xonomy.render(xml, editor, docSpec1);
        console.log($scope.currentUser.username );

        $scope.phase1 = true;
        $scope.phaseText = "Faza kreiranja dokumenta";
        $scope.submit1 = function () {

            var reqData = Xonomy.harvest();
            reqData = reqData.replace("<Akt", "<Akt userId='" + $scope.currentUser.username + "'");


            $http.post("/api/akti/dodaj1", reqData).success(function(data, status) {

                if(data.am != "Error") {
                    alert("Uspešno kreiran akt. Prelazi se u fazu popunjavanja referenci.");
                    //$window.location = "#/akti-u-proceduri";
                    $scope.phase1 = false;
                    $scope.phaseText = "Faza kreiranja referenci";
                    xml = data.am;

                    Xonomy.render(xml, editor, docSpec2);
                 }else{

                    alert("Dokument nije dobro formatiran!\n Svaki dokument mora da ima podnosioca i bar neki tekstualni sadrzaj.");
                }

            });
        }

        $scope.submit2 = function () {

            var reqData = Xonomy.harvest();


            $http.post("/api/akti/dodaj2", reqData).success(function(data, status) {

                if(data.am === "Ok") {
                    alert("Uspešno kreiran akt.");
                    $window.location = "#/akti-u-proceduri";

                }else{

                    alert("Nešto nije uredu.");
                }

            });
        }

    }
];