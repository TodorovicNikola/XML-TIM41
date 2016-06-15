module.exports = [
    '$scope', '$http', '$routeParams', '$route',
    function myController($scope, $http, $routeParams, $route){

        $http.get("/api/akti/u-proceduri").then(function(response) {
            $scope.data = response.data;
        });

        $http({
            method: "Get",
            url: "api/state/getState",
            dataType: "json",
            traditional:true
        }).then(function (response) {
            $scope.state = response.data.data;
        });

        $scope.glasoviZa;
        $scope.glasoviProtiv;
        $scope.glasoviUzdrzani;
        $scope.idAkta;
        $scope.naslov;
        var data = {};

        $scope.setParams = function(id, naslov){
            $scope.idAkta = id;
            $scope.naslov = naslov;
        }

        $scope.ucitajAkteUProceduri=function()
        {
            $http.get("/api/akti/u-proceduri").then(function(response) {
                $scope.data = response.data;
            });
        }
        $scope.pretraga=function(pretraga)
        {
                data = { kriterijum:pretraga,tip:"sadrzaj",status:"U proceduri" };
                $http({
                    method: "post",
                    url: "api/akti/sadrzaj",
                    data: data,
                    dataType: "json",
                    traditional:true
                }).then(function (response) {
                    console.log("ok pretraga");
                    $scope.data=response.data;

                },function(error)
                {
                    console.log('Greska prilikom pretrage akata' );
                });
        }
        $scope.otvoriDatumOdPopup = function() {
            $scope.datumOdPopup.otvoren = true;
        };
        $scope.otvoriDatumDoPopup = function() {
            $scope.datumDoPopup.otvoren = true;
        };
        $scope.datumOdPopup = {
            otvoren: false
        };
        $scope.datumDoPopup = {
            otvoren: false
        };

        $scope.pretraziNapredno=function() {
            var pretragaPodaci = {
                podnosilac: $scope.podnosilac,
                vremeDonosenjaOd: $scope.vremeDonosenjaOd,
                vremeDonosenjaDo: $scope.vremeDonosenjaDo,
                glasnik: "",
                tip:$scope.tipAkta,
                statusAkta:"U proceduri"

            };
            console.log(pretragaPodaci);
            $http({
                method: "post",
                url: "api/akti/pretraga",
                data: pretragaPodaci,
                dataType: "json",
                traditional: true
            }).then(function (response) {
                console.log("Ok pretraga");
                $scope.data = response.data;
                angular.element(document.querySelector('#pretragaModal')).modal('hide');
             
            },function (error) {
                console.log('Greska prilikom napredne pretrage');
            });
        }

        $scope.tipoviAkta= [{naziv:"Zakon", value:"Zakon"}, {naziv:"Nepoznati tip",value:"nepoznatiTip"}];

        $scope.ukloniFiltere=function()
        {
            $scope.podnosilac=null;
            $scope.vremeDonosenjaOd=null;
            $scope.vremeDonosenjaDo=null;
            $scope.glasnik=null;
            $scope.tipAkta=null;
            //posle ponistenih filtera ucitavaju se svi akti ponovo.
            $scope.ucitajAkteUProceduri();
        }



        
        $scope.submitVotes = function(){
            data = { id: $scope.idAkta, glasoviZa: $scope.glasoviZa, glasoviProtiv: $scope.glasoviProtiv, glasoviUzdrzani: $scope.glasoviUzdrzani };
            $http({
                method: "Post",
                url: "api/vote/voteUNacelu",
                data: data,
                dataType: "json",
                traditional:true
            }).then(function (response) {
                alert(response.data.data);
                angular.element(document.querySelector('#voteModal')).modal('hide');
                $route.reload();

            });

        }
    }
];