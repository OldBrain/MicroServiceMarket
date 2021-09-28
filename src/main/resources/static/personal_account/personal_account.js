angular.module('market-front').controller('personalAccountController', function ($scope, $http,$rootScope,$localStorage,$location) {
    const contextPath = 'http://localhost:8189/market/';

    $scope.saveСhanges = function () {
        //TODO сохранение изменений личных данных userdata.detailsUser.*

    };

    $scope.getUserDataByName = function () {
        $http({
            url: contextPath + 'api/v1/auth/orders/'+$localStorage.webMarketUser.username,
            method: 'GET'
        }).then(function (response) {
            $scope.userdata = response.data;
            console.log(response.data);
        });
    };
    $scope.getUserDataByName();
});

