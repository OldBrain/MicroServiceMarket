angular.module('market-front').controller('orderConfirmationController', function ($rootScope,$localStorage,$scope, $http, $location) {
    const contextPath = 'http://localhost:8189/market/';

    $scope.createOrder = function () {
        if ($scope.orderDto.detailsUser.firstName == null||$scope.orderDto.detailsUser.lastName == null||
            $scope.orderDto.detailsUser.patronymic == null) {
            alert("Форма не заполнена");
            return;
        }
        $http({
            url: contextPath + 'api/v1/orders',
            method: 'POST',
            data: $scope.orderDto
        }).then(function successCallback(response){
                alert('Ваш заказ успешно сформирован');
                $location.path('/');
                $rootScope.setCartSum();
            },function errorCallback(response) {
            console.log(response.data);
            alert(response.data.message)
        });
    };



    $scope.disabledCheckOut = function () {
        alert("Для оформления заказа необходимо войти в учетную запись");
    }

    $scope.getOrderDtoByUserName = function () {
        if ($rootScope.isUserLoggedIn()) {

            $http.get(contextPath + 'api/v1/auth/'+ $rootScope.lkname)
                .then(function successCallback (response) {
                    $scope.orderDto = response.data;
                    console.log(response);

                },function errorCallback(response) {
                alert(response.data.message)
            });

        }
    };

    $scope.getOrderDtoByUserName();
});