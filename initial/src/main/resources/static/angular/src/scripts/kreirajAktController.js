/**
 * Created by Aleksa on 6/7/2016.
 */
module.exports = [
    '$scope', '$http',
    function ctrl($scope){

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
                            }
                    ]
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

                    },{
                        caption: "Obriši",
                        action: Xonomy.deleteElement
                    }],
                    mustBeAfter: ["Podnosilac"]
                },
                "Glava":{
                    menu: [{
                        caption: "Dodaj novi <Odeljak>",
                        action: Xonomy.newElementChild,
                        actionParameter: "<Odeljak/>"

                    },{
                        caption: "Obriši",
                        action: Xonomy.deleteElement
                    }]
                },
                "Odeljak":{
                    menu: [{
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
                        caption: "Obriši",
                        action: Xonomy.deleteElement
                    }]

                },
                "Pododeljak":{

                    menu: [{
                        caption: "Dodaj novi <Clan>",
                        action: Xonomy.newElementChild,
                        actionParameter: "<Clan/>"
                    },{
                        caption: "Obriši",
                        action: Xonomy.deleteElement
                    }]

                },
                "Clan":{
                    menu: [{
                        caption: "Dodaj novi <Stav>",
                        action: Xonomy.newElementChild,
                        actionParameter: "<Stav/>",
                        hideIf: function(jsElement){
                            return jsElement.hasChildElement("Sadrzaj");
                        }
                    },{
                        caption: "Dodaj novi <Sadrzaj>",
                        action: Xonomy.newElementChild,
                        actionParameter: "<Sadrzaj/>",
                        hideIf: function(jsElement){
                            return jsElement.hasChildElement("Sadrzaj") ||
                                jsElement.hasChildElement("Stav");
                        }
                    },{
                        caption: "Obriši",
                        action: Xonomy.deleteElement
                    }]
                },
                "Stav":{
                    menu: [{
                        caption: "Dodaj novi <Tacka>",
                        action: Xonomy.newElementChild,
                        actionParameter: "<Tacka/>",
                        hideIf: function(jsElement){
                            return jsElement.hasChildElement("Sadrzaj");
                        }
                    },{
                        caption: "Dodaj novi <Sadrzaj>",
                        action: Xonomy.newElementChild,
                        actionParameter: "<Sadrzaj/>",
                        hideIf: function(jsElement){
                            return jsElement.hasChildElement("Sadrzaj") ||
                                jsElement.hasChildElement("Tacka");
                        }
                    },{
                        caption: "Obriši",
                        action: Xonomy.deleteElement
                    }]
                },
                "Tacka":{
                    menu: [{
                        caption: "Dodaj novi <Podtacka>",
                        action: Xonomy.newElementChild,
                        actionParameter: "<Podtacka/>",
                        hideIf: function(jsElement){
                            return jsElement.hasChildElement("Sadrzaj");
                        }
                    },{
                        caption: "Dodaj novi <Sadrzaj>",
                        action: Xonomy.newElementChild,
                        actionParameter: "<Sadrzaj/>",
                        hideIf: function(jsElement){
                            return jsElement.hasChildElement("Sadrzaj") ||
                                jsElement.hasChildElement("Podtacka");
                        }
                    },{
                        caption: "Obriši",
                        action: Xonomy.deleteElement
                    }]
                },
                "Podtacka":{
                    menu: [{
                        caption: "Dodaj novi <Alineja>",
                        action: Xonomy.newElementChild,
                        actionParameter: "<Alineja/>",
                        hideIf: function(jsElement){
                            return jsElement.hasChildElement("Sadrzaj");
                        }
                    },{
                        caption: "Dodaj novi <Sadrzaj>",
                        action: Xonomy.newElementChild,
                        actionParameter: "<Sadrzaj/>",
                        hideIf: function(jsElement){
                            return jsElement.hasChildElement("Sadrzaj") ||
                                jsElement.hasChildElement("Alineja");
                        }
                    },{
                        caption: "Obriši",
                        action: Xonomy.deleteElement
                    }]
                },
                "Alineja":{
                    hasText: true,
                    inlineMenu: [{
                        caption: "Wrap with <Referenca>",
                        action: Xonomy.wrap,
                        actionParameter: {template: "<Referenca>$</Referenca>", placeholder: "$"}
                    }]

                },
                "Sadrzaj":{
                    hasText: true,
                    inlineMenu: [{
                        caption: "Wrap with <Referenca>",
                        action: Xonomy.wrap,
                        actionParameter: {template: "<Referenca>$</Referenca>", placeholder: "$"}
                    }]
                }
            }

        };


        var xml="<Akt><Podnosilac></Podnosilac><Deo></Deo></Akt>";
        var editor=document.getElementById("editor");
        Xonomy.setMode("laic");
        Xonomy.render(xml, editor, docSpec);

        $scope.submit = function () {
            alert(Xonomy.harvest());
        }

    }
];