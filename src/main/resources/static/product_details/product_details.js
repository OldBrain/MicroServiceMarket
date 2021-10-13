angular.module('market-front').controller('productDetailsController', function ($scope, $http, $location,$rootScope) {
    const contextPath = 'http://localhost:8189/market/';

    $scope.showProduct = function () {
        $http.get(contextPath + 'api/v1/products/'+ $rootScope.productId)
            .then(function successCallback (response) {
                $scope.product = response.data;
            }, function failureCallback (response) {
                console.log(response);
                alert(response.data.messages);
            });
    }
    $scope.addToCart = function (productId) {
        $http({
            url: contextPath + 'api/v1/cart/add/' + productId,
            method: 'GET'
        }).then(function (response) {
            $rootScope.setCartSum();
        });
    };

    $scope.showProduct();
});