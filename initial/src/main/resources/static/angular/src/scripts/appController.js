/**
 * Created by Vuletic on 27.5.2016.
 */
module.exports = [
    '$scope', '$http', 'loginService', '$localStorage',
    function myController($scope, $http, loginService, $localStorage){

        $scope.currentUser  = $localStorage.currentUser;

        $scope.logout=function () {
            loginService.logout();
            $scope.refreshUser();
        };

        $scope.refreshUser = function() {
            $scope.currentUser  = $localStorage.currentUser;
        }

        $http({
            method: "Get",
            url: "api/state/getState",
            dataType: "json",
            traditional:true

        }).then(function (response) {
            $scope.state = response.data.data;

        });

    }
];