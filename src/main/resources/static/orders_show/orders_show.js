angular.module('market-front').controller('ordersShowController', function ($scope, $http,$rootScope,$localStorage,$location) {
    const contextPath = 'http://localhost:8189/market/';

    $scope.loadOrder = function () {
        $http({
            url: contextPath + 'api/v1/orders',
            method: 'GET'

        }).then(function (response) {
            $scope.order =response.data
            console.log(response.data);
        });
    };

    // $scope.showOrderItems = function ($scope.order.id){
    //
    // }



    $scope.loadOrder();
});

