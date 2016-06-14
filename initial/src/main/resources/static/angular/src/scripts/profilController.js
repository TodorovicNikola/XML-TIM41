module.exports = [
    '$scope', '$http',
    function myController($scope, $http){
        $http({
            method: "Get",
            url: "api/state/getState",
            dataType: "json",
            traditional:true
        }).then(function (response) {
            $scope.state = response.data.data;
        });

        var data = $scope.currentUser.username;

        $http({
            method: "Post",
            url: "api/akti/uProceduriKorisnika",
            data: data,
            dataType: "json",
            traditional: true
        }).then(function (response) {
           
            $scope.aktiUProceduri = response.data;

        });

        $http({
            method: "Post",
            url: "api/akti/uNaceluKorisnika",
            data: data,
            dataType: "json",
            traditional: true
        }).then(function (response) {
            $scope.aktiUNacelu = response.data;

        });

        $http({
            method: "Post",
            url: "api/akti/usvojeniKorisnika",
            data: data,
            dataType: "json",
            traditional: true
        }).then(function (response) {
            $scope.aktiUsvojeni = response.data;

        });

        $http({
            method: "Post",
            url: "api/amandmani/korisnika",
            data: data,
            dataType: "json",
            traditional: true
        }).then(function (response) {
            $scope.amandmani = response.data;

        });

        $scope.povuciAkt = function (id) {
            alert(id);
        }

        $scope.povuciAmandman = function (id) {
            alert(id);
        }

    }
];
