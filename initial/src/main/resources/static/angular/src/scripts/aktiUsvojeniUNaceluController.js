module.exports = [
    '$scope', '$http', '$routeParams',

    function myController($scope, $http, $routeParams){
        $http.get("/api/akti/u-nacelu").then(function(response) {
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
        $scope.pretraga=function(pretraga)
        {
            data = { kriterijum:pretraga,tip:"sadrzaj",status:"U nacelu" };
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

        $scope.submitVotes = function(){
            data = { idAkta: $scope.idAkta, glasoviZa: $scope.glasoviZa, glasoviProtiv: $scope.glasoviProtiv, glasoviUzdrzani: $scope.glasoviUzdrzani };

            $http({
                method: "Post",
                url: "api/vote/voteUCelosti",
                data: data,
                dataType: "json",
                traditional:true
            }).then(function (response) {
                alert(response.data.data);

            });

            angular.element(document.querySelector('#voteModal')).modal('hide');
        }
    }

];
