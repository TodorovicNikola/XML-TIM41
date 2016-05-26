/**
 * Created by Vuletic on 25.5.2016.
 */
module.exports = [
    '$scope', '$http', 'loginService',
    function myController($scope, $http, loginService){
        $scope.user={
            username: null,
            password: null
        };
        $scope.login=function () {
            loginService.login($scope.user.username,$scope.user.password,loginCbck);
        };
        function loginCbck(success) {
            if (success) {
                alert('success!');
            }
            else{
                alert('failure!');
            }
        }
    }
];