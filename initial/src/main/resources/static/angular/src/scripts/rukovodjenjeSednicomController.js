module.exports = [
    '$scope', '$http',
    function myController($scope, $http){
        /*
        $http.get("/api/state/getState").then(function(response) {
            $scope.data = response.data;
            console.log("hasan");
        });*/

        $http({
            method: "Get",
            url: "api/state/getState",
            dataType: "json",
            traditional:true
        }).then(function (response) {
                $scope.data = response.data.data;
        });

        $scope.nextState = function(){
            $http({
                method: "Get",
                url: "api/state/nextState",
                dataType: "json",
                traditional:true
            }).then(function (response) {
                $scope.data = response.data.data;
            });
        }
    }
];
