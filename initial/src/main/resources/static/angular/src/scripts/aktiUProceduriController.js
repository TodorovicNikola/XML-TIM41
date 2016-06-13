/**
 * Created by Vuletic on 29.5.2016.
 */
module.exports = [
    '$scope', '$http', '$routeParams',
    function myController($scope, $http, $routeParams){

        $http.get("/api/akti/u-proceduri").then(function(response) {
            $scope.data = response.data;
        });
        
    }
];