/**
 * Created by Aleksa on 6/7/2016.
 */
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
                            caption: "Dodaj <ElementAmandman>",
                            action: Xonomy.newElementChild,
                            actionParameter: "<Podnosilac/>"
                        }
                    
                    ]
                },
                "Podnosilac": {
                    menu: [{
                        caption: "Add @label=\"something\"",
                        action: Xonomy.newAttribute,
                        actionParameter: {name: "label", value: "something"},
                        hideIf: function(jsElement){
                            return jsElement.hasAttribute("label");
                        }
                    }, {
                        caption: "Delete this <item>",
                        action: Xonomy.deleteElement
                    }, {
                        caption: "New <item> before this",
                        action: Xonomy.newElementBefore,
                        actionParameter: "<item/>"
                    }, {
                        caption: "New <item> after this",
                        action: Xonomy.newElementAfter,
                        actionParameter: "<item/>"
                    }],
                    canDropTo: ["list"],
                    attributes: {
                        "label": {
                            asker: Xonomy.askString,
                            menu: [{
                                caption: "Delete this @label",
                                action: Xonomy.deleteAttribute
                            }]
                        }
                    }
                }
            }
        };

        var xml="<Amandman><Podnosilac></Podnosilac><ElementAmandmana Akcija='Dodaj' Referencira=''></ElementAmandmana></Amandman>";
        var editor=document.getElementById("editor");
        Xonomy.setMode("laic");
        Xonomy.render(xml, editor, docSpec);

        $scope.submit = function () {
            alert(Xonomy.harvest());
        }

    }
];