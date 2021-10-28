angular.module('market-front').controller('cartController', function ($scope, $http, $location, $localStorage, $rootScope) {
    const contextPath = 'http://localhost:5555/cart/';

    $scope.loadCart = function () {
        $http({
            url: contextPath + 'api/v1/cart/' + $localStorage.webMarketGuestCartId,
            method: 'GET'
        }).then(function (response) {
            $scope.cart = response.data;
        });
    };

    $scope.incrementItem = function (productId) {
        $http({
            url: contextPath + 'api/v1/cart/' + $localStorage.webMarketGuestCartId + '/add/' + productId,
            method: 'GET'
        }).then(function (response) {
            $scope.loadCart();
            $rootScope.setCartSum();
        });
    };

    $scope.decrementItem = function (productId) {
        $http({
            url: contextPath + 'api/v1/cart/' + $localStorage.webMarketGuestCartId + '/decrement/' + productId,
            method: 'GET'
        }).then(function (response) {
            $scope.loadCart();
            $rootScope.setCartSum();
        });
    };

    $scope.removeItem = function (productId) {
        $http({
            url: contextPath + 'api/v1/cart/' + $localStorage.webMarketGuestCartId + '/remove/' + productId,
            method: 'GET'
        }).then(function (response) {
            $scope.loadCart();
            $rootScope.setCartSum();
        });
    };

    $scope.checkOut = function () {
        $location.path("/order_confirmation");
    }

    $scope.disabledCheckOut = function () {
        alert("Для оформления заказа необходимо войти в учетную запись");
    }

    $scope.loadCart();
    $rootScope.setCartSum();
});