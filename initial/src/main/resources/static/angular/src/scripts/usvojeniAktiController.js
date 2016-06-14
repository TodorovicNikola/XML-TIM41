/**
 * Created by Vuletic on 25.5.2016.
 */
module.exports = [
    '$scope', '$http',
    function myController($scope, $http){
        $http.get("/api/akti/usvojeni").then(function(response) {
            $scope.data = response.data;
        });
        $scope.pretraga=function(pretraga)
        {
            data = { kriterijum:pretraga,tip:"sadrzaj",status:"Usvojen" };
            $http({
                method: "post",
                url: "api/akti/sadrzaj",
                data: data,
                dataType: "json",
                traditional:true
            }).then(function (response) {
                $scope.data=response.data;
            }).then(function(error)
            {
                console.log('Greska prilikom pretrage akata' );
            });
        }
        $scope.ucitajUsvojeneAkte=function()
        {
            $http.get("/api/akti/usvojeni").then(function(response) {
                $scope.data = response.data;
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
                glasnik: $scope.glasnik,
                tip:$scope.tipAkta,
                statusAkta:"Usvojen"

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
            $scope.ucitajUsvojeneAkte();
        }



    }
];