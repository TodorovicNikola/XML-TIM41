/**
 * Created by Vuletic on 9.6.2016.
 */
//TODO ogranicenja: datum,  referenca
module.exports = [
    '$scope', '$http', '$interval', '$routeParams',
    function ctrl($scope, $http, $interval, $routeParams){
        alert($routeParams.id);
        $scope.warns = 0;
        var docSpec={

            validate: function(jsElement){
                //Cycle through the element's attributes:
                for(var i=0; i<jsElement.attributes.length; i++) {
                    var jsAttribute=jsElement.attributes[i];
                    //Make sure item/@label is not an empty string:
                    if(jsElement.name=="Amandman" && jsAttribute.name=="Id") {
                        if(!isNormalInteger(jsAttribute.value)) {
                            Xonomy.warnings.push({
                                htmlID: jsAttribute.htmlID,
                                text: "Id mora biti pozitivan ceo broj."}
                            );
                        }
                    }
                };
                    //Cycle through the element's children:
                for(var i=0; i<jsElement.children.length; i++) {
                    var jsChild=jsElement.children[i];
                    if(jsChild.type=="element") { //if element
                        docSpec.validate(jsChild); //recursion
                    }
                }

            },
            elements: {
                "Amandman": {
                    menu: [{
                        caption: "Dodaj <Podnosilac>",
                        action: Xonomy.newElementChild,
                        actionParameter: "<Podnosilac/>"
                        },
                        {
                            caption: "Dodaj @Datum",
                            action: Xonomy.newAttribute,
                            actionParameter: {name: "DatumIVremePodnosenja", value: "1.1.2001."},
                            hideIf: function(jsElement){
                                return jsElement.hasAttribute("DatumIVremePodnosenja");
                            }
                        }

                    ],
                    attributes: {
                        "Id": {
                            asker: Xonomy.askString
                        },
                        "DatumIVremePodnosenja":{
                            asker: Xonomy.askString
                        }
                    }
                },
                "Podnosilac": {
                    hasText: true,
                    menu: [{
                        caption: "Dodaj novi <Podnosilac>",
                        action: Xonomy.newElementAfter,
                        actionParameter: "<Podnosilac/>"

                    }, {
                        caption: "Obriši",
                        action: Xonomy.deleteElement
                    }],
                    mustBeBefore: ["ElementAmandmana"]
                },
                "ElementAmandmana": {
                    menu: [{
                        caption: "Obriši",
                        action: Xonomy.deleteElement
                    }],
                    mustBeAfter: ["Podnosilac"]
                },
                "ns2:Clan":{
                    menu: [{
                        caption: "Dodaj novi <ns2:Stav>",
                        action: Xonomy.newElementChild,
                        actionParameter: "<ns2:Stav xmlns:ns2='http://www.xmlProjekat.com/akt'/>"
                    },{
                        caption: "Dodaj novi <ns2:Sadrzaj>",
                        action: Xonomy.newElementChild,
                        actionParameter: "<ns2:Sadrzaj xmlns:ns2='http://www.xmlProjekat.com/akt'/>"
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
                        actionParameter: {name: "Redni broj", value: ""},
                        hideIf: function(jsElement){
                            return jsElement.hasAttribute("Redni broj");
                        }
                    },{
                        caption: "Obriši",
                        action: Xonomy.deleteElement
                    }],
                    attributes:{
                        "Naslov":{
                            asker: Xonomy.askString
                        },
                        "Redni broj":{
                            asker: Xonomy.askString
                        }
                    }
                },
                "ns2:Stav":{
                    menu: [{
                        caption: "Dodaj novi <ns2:Stav>",
                        action: Xonomy.newElementAfter,
                        actionParameter: "<ns2:Stav xmlns:ns2='http://www.xmlProjekat.com/akt'/>"

                    },{
                        caption: "Dodaj novi <ns2:Tacka>",
                        action: Xonomy.newElementChild,
                        actionParameter: "<ns2:Tacka xmlns:ns2='http://www.xmlProjekat.com/akt'/>"
                    },{
                        caption: "Dodaj novi <ns2:Sadrzaj>",
                        action: Xonomy.newElementChild,
                        actionParameter: "<ns2:Sadrzaj xmlns:ns2='http://www.xmlProjekat.com/akt'/>"
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
                        actionParameter: {name: "Redni broj", value: ""},
                        hideIf: function(jsElement){
                            return jsElement.hasAttribute("Redni broj");
                        }
                    },{
                        caption: "Obriši",
                        action: Xonomy.deleteElement
                    }],
                    attributes:{
                        "Naslov":{
                            asker: Xonomy.askString
                        },
                        "Redni broj":{
                            asker: Xonomy.askString
                        }
                    }
                },
                "ns2:Tacka":{
                    menu: [{
                        caption: "Dodaj novi <ns2:Tacka>",
                        action: Xonomy.newElementAfter,
                        actionParameter: "<ns2:Tacka xmlns:ns2='http://www.xmlProjekat.com/akt'/>"

                    },{
                        caption: "Dodaj novi <ns2:Podtacka>",
                        action: Xonomy.newElementChild,
                        actionParameter: "<ns2:Podtacka xmlns:ns2='http://www.xmlProjekat.com/akt'/>"
                    },{
                        caption: "Dodaj novi <ns2:Sadrzaj>",
                        action: Xonomy.newElementChild,
                        actionParameter: "<ns2:Sadrzaj xmlns:ns2='http://www.xmlProjekat.com/akt'/>"
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
                        actionParameter: {name: "Redni broj", value: ""},
                        hideIf: function(jsElement){
                            return jsElement.hasAttribute("Redni broj");
                        }
                    },{
                        caption: "Obriši",
                        action: Xonomy.deleteElement
                    }],
                    attributes:{
                        "Naslov":{
                            asker: Xonomy.askString
                        },
                        "Redni broj":{
                            asker: Xonomy.askString
                        }
                    }
                },
                "ns2:Podtacka":{
                    menu: [{
                        caption: "Dodaj novi <ns2:Podtacka>",
                        action: Xonomy.newElementAfter,
                        actionParameter: "<ns2:Podtacka xmlns:ns2='http://www.xmlProjekat.com/akt'/>"

                    },{
                        caption: "Dodaj novi <ns2:Alineja>",
                        action: Xonomy.newElementChild,
                        actionParameter: "<ns2:Alineja xmlns:ns2='http://www.xmlProjekat.com/akt'/>"
                    },{
                        caption: "Dodaj novi <ns2:Sadrzaj>",
                        action: Xonomy.newElementChild,
                        actionParameter: "<ns2:Sadrzaj xmlns:ns2='http://www.xmlProjekat.com/akt'/>"
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
                        actionParameter: {name: "Redni broj", value: ""},
                        hideIf: function(jsElement){
                            return jsElement.hasAttribute("Redni broj");
                        }
                    },{
                        caption: "Obriši",
                        action: Xonomy.deleteElement
                    }],
                    attributes:{
                        "Naslov":{
                            asker: Xonomy.askString
                        },
                        "Redni broj":{
                            asker: Xonomy.askString
                        }
                    }
                },
                "ns2:Alineja":{
                    hasText: true,
                    inlineMenu: [{
                        caption: "Wrap with <ns2:Referenca>",
                        action: Xonomy.wrap,
                        actionParameter: {template: "<ns2:Referenca xmlns:ns2='http://www.xmlProjekat.com/akt' ReferencaURI=''>$</ns2:Referenca>", placeholder: "$"}
                    }],
                    menu: [{
                        caption: "Obriši",
                        action: Xonomy.deleteElement
                    }]
                },
                "ns2:Sadrzaj":{
                    hasText: true,
                    inlineMenu: [{
                        caption: "Wrap with <ns2:Referenca>",
                        action: Xonomy.wrap,
                        actionParameter: {template: "<ns2:Referenca xmlns:ns2='http://www.xmlProjekat.com/akt' ReferencaURI=''>$</ns2:Referenca>", placeholder: "$"}
                    }],
                    menu: [{
                        caption: "Obriši",
                        action: Xonomy.deleteElement
                    }]
                },
                "ns2:Referenca": {
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

        var xml="<Amandman Id='' xmlns='http://www.xmlProjekat.com/amandman'  xmlns:ns2='http://www.xmlProjekat.com/akt'><Podnosilac></Podnosilac></Amandman>";
        var editor=document.getElementById("editor");
        Xonomy.setMode("laic");
        Xonomy.render(xml, editor, docSpec);
        
        
        
        
        $scope.form = {};
        $scope.form.reference = '';
        $scope.form.action = '';
        $scope.form.submit = function(){

            var dto = {};
            dto.amandman = Xonomy.harvest();
            dto.reference = $scope.form.reference;
            dto.action = $scope.form.action;
            dto.aktId = "4cc7c811-7788-4401-a36d-1755501bc577";

            $http.post("/api/amandmani/dogradi", dto).success(function(data, status) {

                if($scope.form.reference === '' && $scope.form.action === '')
                    return;


                alert("Uspešno dodat amandman.");
                Xonomy.render(data.am, editor, docSpec);

                $scope.form.reference = '';
                $scope.form.action = '';
            });

        }
        
        $scope.submit = function () {

            var dto = {};
            dto.amandman = Xonomy.harvest();
            dto.reference = "";
            dto.action = "";
            dto.aktId = "4cc7c811-7788-4401-a36d-1755501bc577";

            $http.post("/api/amandmani/dodaj", dto).success(function(data, status) {

                alert("Uspešno dodat amandman.");

            });



        }

        $interval(function() {
            $scope.warns = Xonomy.warnings.length;
        }, 500);

        function isNormalInteger(str) {
            var n = ~~Number(str);
            return String(n) === str && n > 0;
        }

    }
];