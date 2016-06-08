/**
 * Created by Aleksa on 6/7/2016.
 */
module.exports = [
    '$scope', '$http',
    function ctrl($scope){
        $scope.init=function()
        {
            console.log('Kreiraj akt controller pokrenut');
        }
        $scope.init()

    }
];