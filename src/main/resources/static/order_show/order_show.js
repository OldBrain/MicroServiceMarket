angular.module('market-front').controller('orderShowController', function ($scope, $http,$rootScope) {
    const contextPath = 'http://localhost:8189/market/';

    $scope.loadOrders = function () {
        $http({
            // url: contextPath + 'api/v1/orders/'+ $rootScope.lkname,
            url: contextPath + 'api/v1/orders',
            method: 'GET'
        }).then(function (response) {
            alert(response.data);
            $scope.orders = response.data;


        });
    };

    $scope.loadOrders();
});