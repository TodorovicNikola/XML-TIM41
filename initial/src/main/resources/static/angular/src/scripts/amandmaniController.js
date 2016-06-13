module.exports = [
    '$scope', '$http', '$routeParams', '$route',
    function myController($scope, $http, $routeParams, $route){
        $http.get("/api/amandmani/svi").then(function(response) {
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
        $scope.idAmandmana;
        $scope.naslov;

        var data = {};


        $scope.setParams = function(id, naslov){
            $scope.idAkta = id;
            $scope.naslov = naslov;

        }

        $scope.submitVotes = function(){

            data = { id: $scope.idAmandmana, glasoviZa: $scope.glasoviZa, glasoviProtiv: $scope.glasoviProtiv, glasoviUzdrzani: $scope.glasoviUzdrzani };

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