/**
 * Created by Vuletic on 25.5.2016.
 */
module.exports = [
    '$scope', '$http',
    function myController($scope, $http){
        $http.get("/api/akti/usvojeni").then(function(response) {
            $scope.data = response.data;
        });
        $scope.pretraga=function(pretraga)
        {
            data = { kriterijum:pretraga,tip:"sadrzaj",status:"Usvojen" };
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
    }
];