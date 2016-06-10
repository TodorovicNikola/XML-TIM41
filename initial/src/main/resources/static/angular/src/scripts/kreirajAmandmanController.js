/**
 * Created by Vuletic on 9.6.2016.
 */
//TODO ogranicenja: datum,  referenca
module.exports = [
    '$scope', '$http', '$interval',
    function ctrl($scope, $http, $interval){
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
                            caption: "Dodaj <ElementAmandmana>",
                            action: Xonomy.newElementChild,
                            actionParameter: "<ElementAmandmana Akcija='Dodaj' Referencira=''/>"
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
                    hasText: true,
                    menu: [{
                        caption: "Dodaj novi <ElementAmandmana>",
                        action: Xonomy.newElementAfter,
                        actionParameter: "<ElementAmandmana Akcija='Dodaj' Referencira=''/>"

                    }, {
                        caption: "Obriši",
                        action: Xonomy.deleteElement
                    }],
                    attributes: {
                        "Akcija": {
                            asker: Xonomy.askPicklist,
                            askerParameter: [
                                "Dodaj", "Izmeni", "Obrisi"
                            ]
                        },
                        "Referencira": {
                            asker: Xonomy.askString
                        }
                    },
                    mustBeAfter: ["Podnosilac"]
                }
            }
        };

        var xml="<Amandman Id='' xmlns='http://www.xmlProjekat.com/amandman'><Podnosilac></Podnosilac><ElementAmandmana Akcija='Dodaj' Referencira=''></ElementAmandmana></Amandman>";
        var editor=document.getElementById("editor");
        Xonomy.setMode("laic");
        Xonomy.render(xml, editor, docSpec);

        $scope.submit = function () {

            $http.post("/api/amandmani/dodaj", Xonomy.harvest()).success(function(data, status) {
                alert("Uspešno dodat amandman.");
            })
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