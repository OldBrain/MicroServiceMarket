angular.module('market-front').controller('cartController', function ($scope, $http, $location,$localStorage,$rootScope) {
    const contextPath = 'http://localhost:8189/market/';

    $scope.loadCart = function () {
        $rootScope.cartsumm=0;
        $http({
            url: contextPath + 'api/v1/cart',
            method: 'GET'
        }).then(function (response) {
            $scope.cart = response.data;
            $rootScope.cartsumm = $scope.cart.totalPrice;
        });
    };

    $scope.incrementItem = function (productId) {
        $http({
            url: contextPath + 'api/v1/cart/add/' + productId,
            method: 'GET'
        }).then(function (response) {
            $rootScope.loadCart();
        });
    };

    $scope.decrementItem = function (productId) {
        $http({
            url: contextPath + 'api/v1/cart/decrement/' + productId,
            method: 'GET'
        }).then(function (response) {
            $rootScope.loadCart();
        });
    };

    $scope.removeItem = function (productId) {
        $http({
            url: contextPath + 'api/v1/cart/remove/' + productId,
            method: 'GET'
        }).then(function (response) {
            $rootScope.loadCart();
        });
    };



    $scope.checkOut = function () {
        $location.path("/order_confirmation");
    }

    $scope.disabledCheckOut = function () {
        alert("Для оформления заказа необходимо войти в учетную запись");
    }

    $scope.loadCart();
});