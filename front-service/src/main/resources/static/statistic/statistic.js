angular.module('market-front').controller('statisticController', function ($scope, $http,$rootScope,$localStorage) {
    const contextPath = 'http://localhost:5555/core/';

    $scope.loadMethodStat = function () {
        $http({
            method: 'GET',
            url: 'http://localhost:5555/core/api/v1/stat'

        }).then(function (response) {
            $scope.statMethod = response.data.mapValue;
            console.log(response.data.mapValue);
        });
    };

    $scope.loadMethodStat();
});

