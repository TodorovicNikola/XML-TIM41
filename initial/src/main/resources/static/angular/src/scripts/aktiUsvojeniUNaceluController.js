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