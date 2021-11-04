angular.module('market-front').controller('orderConfirmationController', function ($rootScope,$localStorage,$scope, $http, $location, $routeParams) {
    const contextPath = 'http://localhost:8189/market/';

    $scope.createOrder = function () {

        $http({
            url: 'http://localhost:5555/core/api/v1/orders',
            method: 'POST',
            data: $scope.userD.detailsUserDtoList[0]
        }).then(function (response) {
            var orderId = response.data.value;

            alert("Ваш заказ успешно сформирован. " + orderId);
            $location.path('/order_pay/' + orderId);
            // $location.path('/personal_account/');
        });
    };

    $scope.disabledCheckOut = function () {
        alert("Для оформления заказа необходимо войти в учетную запись");
    }

    $scope.getUser = function () {
        if ($rootScope.isUserLoggedIn()) {
            $http.get( 'http://localhost:5555/auth/api/v1/users/me')
                .then(function successCallback (response) {
                    $scope.userD = response.data;
                    console.log($scope.userD);

                },function errorCallback(response) {
                alert(response.data.message)
            });

        }
    };

    $scope.loadCart = function () {
        $http({
            url: 'http://localhost:5555/cart/api/v1/cart/' + $localStorage.webMarketGuestCartId,
            method: 'GET'
        }).then(function (response) {
            $scope.cart = response.data;
        });
    };

    $scope.loadCart();
    $scope.getUser();
});