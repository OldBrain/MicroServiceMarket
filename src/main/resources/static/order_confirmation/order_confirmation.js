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
            data: $scope.userDto
        }).then(function (response) {
            $http({
                url: contextPath + 'api/v1/orders',
                method: 'PUT',
                data: $scope.cart

            }).then(function (response){
                alert('Ваш заказ успешно сформирован');
                $location.path('/');
                $rootScope.setCartSum();
            });

        });
    };



    $scope.disabledCheckOut = function () {
        alert("Для оформления заказа необходимо войти в учетную запись");
    }

    $scope.getUserDtoByName = function () {
        if ($rootScope.isUserLoggedIn()) {

            $http.get(contextPath + 'api/v1/auth/'+ $rootScope.lkname)
                .then(function successCallback (response) {
                    $scope.userDto = response.data;
                    console.log(response);

                },function errorCallback(response) {
                alert(response.data.message)
            });

        }
    };

    $rootScope.setCartSum();
    $rootScope.loadCart();

    $scope.getUserDtoByName();
});