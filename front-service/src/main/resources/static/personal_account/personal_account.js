angular.module('market-front').controller('personalAccountController', function ($scope, $http,$rootScope,$localStorage,$location) {
    const contextPath = 'http://localhost:8189/market/';

    // $scope.saveСhanges = function () {
    //     //TODO сохранение изменений личных данных userdata.detailsUser.*
    //
    // };

    // $scope.getUserDataByName = function () {
    //     if ($rootScope.isUserLoggedIn()) {
    //
    //         $http.get(contextPath + 'api/v1/auth/'+ $rootScope.lkname)
    //             .then(function successCallback (response) {
    //                 $scope.userdata = response.data;
    //                 console.log(response);
    //
    //             },function errorCallback(response) {
    //                 alert(response.data.message)
    //             });
    //
    //     }
    // };

    // $scope.getUserDataByName();
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

    $scope.loadOrders = function () {
        $http({
            url: 'http://localhost:5555/core/api/v1/orders',
            method: 'GET'
        }).then(function (response) {
            $scope.orders = response.data;
        });
    };


    $scope.getUser();
    $scope.loadOrders();
});

