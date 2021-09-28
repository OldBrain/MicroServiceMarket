angular.module('market-front').controller('statisticController', function ($scope, $http,$rootScope,$localStorage) {
    const contextPath = 'http://localhost:8189/market/';

    $scope.loadMethodStat = function () {
        $http({
            method: 'GET',
            url: contextPath + 'api/v1/stat'

        }).then(function (response) {
            $scope.statMethod = response.data.mapValue;
            console.log(response.data.mapValue);
        });
    };

    $scope.loadMethodStat();
});

