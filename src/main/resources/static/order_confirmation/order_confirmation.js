angular.module('market-front').controller('orderConfirmationController', function ($rootScope,$localStorage,$scope, $http, $location) {
    const contextPath = 'http://localhost:8189/market/';

    $rootScope.loadCart = function () {
        $http({
            url: contextPath + 'api/v1/cart',
            method: 'GET'
        }).then(function (response) {
            $scope.cart = response.data;
        });
    };

    $scope.createOrder = function () {
        $http({
            url: contextPath + 'api/v1/orders',
            method: 'POST',
            data: $scope.orderDetails
        }).then(function (response) {

            alert('Ваш заказ успешно сформирован');
            $location.path('/');
        });
    };



    $scope.disabledCheckOut = function () {
        alert("Для оформления заказа необходимо войти в учетную запись");
    }

    $scope.createOrder = function () {
        //TO DOO
    };

    $rootScope.setCartSum();
    // $rootScope.setLkName();
    $rootScope.loadCart();


});