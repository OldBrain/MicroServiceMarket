angular.module('market-front').controller('productDetailsController', function ($scope, $http, $location, $rootScope, $localStorage) {
    const contextPath = 'http://localhost:5555/core/';
    var id = $localStorage.productId;

    $scope.showProduct = function () {
        $http.get(contextPath + 'api/v1/products/' + id)
            .then(function successCallback(response) {
                $scope.product = response.data;
            }, function failureCallback(response) {
                console.log(response);
                // alert(response.data.messages);
            });


    }
    $scope.addToCart = function (productId) {
        $http({
            url: 'http://localhost:5555/cart/api/v1/cart/' + $localStorage.webMarketGuestCartId + '/add/' + id,
            method: 'GET'
        }).then(function (response) {
            $rootScope.setCartSum();
        });
    };

    $scope.showComments = function () {
        $http.get(contextPath + 'api/v1/comments/' + id)
            .then(function successCallback(response) {
                $scope.comment = response.data;
                console.log($scope.comment);
            });

    }

    $scope.showComments();
    $scope.showProduct();
});