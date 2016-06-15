/**
 * Created by Aleksa on 6/15/2016.
 */

module.exports = [
    '$scope', '$http',
    function myController($scope, $http) {
        $scope.tipoviKorisnika = [{naziv: "Odbornik", value: "Odbornik"}];

        $scope.isteLozinke = function () {
            if ($scope.lozinkaKorisnika !== $scope.lozinkaKorisnikaPonovljena)
                return false;
            return true;
        }

        $scope.validirajFormu = function () {
            if (!$scope.imeKorisnika || !$scope.prezimeKorisnika || !$scope.lozinkaKorisnika || !$scope.korisnickoImeKorisnika) {
                return false;
            }
            else if (!$scope.isteLozinke()) {
                return false;
            }
            return true;

        }
        $scope.izbrisiPodatkeOKorisniku=function()
        {
            $scope.imeKorisnika =null;
            $scope.prezimeKorisnika =null;
            $scope.lozinkaKorisnika=null;
            $scope.korisnickoImeKorisnika=null;
            $scope.lozinkaKorisnikaPonovljena=null;
            $scope.tipKorisnika=null;
            $scope.emailKorisnika=null;
            $scope.telefonKorisnika=null;
        }
        $scope.registruj=function()
        {

                var korisnik = {
                    korisnickoIme: $scope.korisnickoImeKorisnika,
                    ime: $scope.imeKorisnika,
                    prezime: $scope.prezimeKorisnika,
                    email: $scope.emailKorisnika,
                    lozinka: $scope.lozinkaKorisnika,
                    tip: "Odbornik",
                    kontaktTelefon: $scope.telefonKorisnika,
                    id: Math.floor((Math.random() * 10000000))
                }

                $http({
                    method: "post",
                    url: "api/korisnici/register",
                    data: korisnik,
                    dataType: "json",
                    traditional:true
                }).then(function (response) {
                    alert(response.data.data);
                    angular.element(document.querySelector('#registrujOdbornika')).modal('hide');
                    $scope.izbrisiPodatkeOKorisniku();

                },function(error)
                {
                    alert("Nepoznata greska");
                    angular.element(document.querySelector('#registrujOdbornika')).modal('hide');
                });
            
        }
        

    }


];