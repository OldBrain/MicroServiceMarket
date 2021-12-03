angular.module('market-front').controller('orderShowController', function ($scope, $http,$rootScope) {
    const contextPath = 'http://localhost:8189/market/';

    // $scope.loadOrders = function () {
    //     $http({
    //         'http://localhost:5555/core/api/v1/orders',
    //         url: contextPath + 'api/v1/orders',
    //         method: 'GET'
    //     }).then(function (response) {
    //         // alert(response.data);
    //         console.log(response.data);
    //         $scope.orders = response.data;
    //
    //
    //     });
    // };

    $scope.loadOrders = function (status = 0) {
        $http({
            url: 'http://localhost:5555/core/api/v1/orders',
            method: 'GET',
            params: {
                status:status
            }
        }).then(function (response) {
            $scope.orders = response.data;
            $scope.orderId = $scope.orders.id;

            console.log(response.data);
        });
    };

    // $scope.loadOrderStatus = function (id = 0) {
    //     $http({
    //         url: 'http://localhost:5555/core/api/v1/orders'+,
    //         method: 'GET',
    //         params: {
    //             status:status
    //         }
    //     }).then(function (response) {
    //         $scope.orders = response.data;
    //         console.log(response.data);
    //     });
    // };


    $scope.loadOrders(0);
});