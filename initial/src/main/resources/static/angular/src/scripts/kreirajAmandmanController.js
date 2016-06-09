/**
 * Created by Vuletic on 9.6.2016.
 */
//TODO ogranicenja: id je broj, datum, prvo podnosioci, pa elementi amandmana, referenca
module.exports = [
    '$scope', '$http',
    function ctrl($scope){
        var docSpec={
            onchange: function(){
                console.log("I been changed now!")
            },
            validate: function(obj){
                console.log("I be validatin' now!")
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
                            actionParameter: "<ElementAmandmana/>"
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
                    }]
                },
                "ElementAmandmana": {
                    hasText: true,
                    menu: [{
                        caption: "Dodaj novi <ElementAmandmana>",
                        action: Xonomy.newElementAfter,
                        actionParameter: "<ElementAmandmana/>"

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
                    }
                }
            }
        };

        var xml="<Amandman Id=''><Podnosilac></Podnosilac><ElementAmandmana Akcija='Dodaj' Referencira=''></ElementAmandmana></Amandman>";
        var editor=document.getElementById("editor");
        Xonomy.setMode("laic");
        Xonomy.render(xml, editor, docSpec);

        $scope.submit = function () {
            alert(Xonomy.harvest());
        }

    }
];