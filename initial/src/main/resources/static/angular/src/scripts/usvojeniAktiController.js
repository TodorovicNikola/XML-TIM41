/**
 * Created by Vuletic on 25.5.2016.
 */
module.exports = [
    '$scope', '$http',
    function myController($scope, $http){
        $http.get("/api/akti").then(function(response) {
            $scope.data = response.data;
        });
    }
];