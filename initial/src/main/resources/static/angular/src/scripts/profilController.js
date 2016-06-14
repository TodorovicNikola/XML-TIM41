module.exports = [
    '$scope', '$http', '$route',
    function myController($scope, $http, $route){
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
            if (confirm("DA LI STE SIGURNI DA ŽELITE DA POVUČETE AKT?")) {
                $http({
                    method: "Post",
                    url: "api/akti/obrisi",
                    data: id,
                    dataType: "json",
                    traditional: true
                }).then(function (response) {
                    alert(response.data.data);
                    $route.reload();

                });
            }
        }

        $scope.povuciAmandman = function (id) {
            $http({
                method: "Post",
                url: "api/amandmani/obrisi",
                data: id,
                dataType: "json",
                traditional: true
            }).then(function (response) {
                alert(response.data.data);
                $route.reload();

            });
        }

    }
];
