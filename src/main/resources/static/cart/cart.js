angular.module('market-front').controller('cartController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market/';

    $scope.loadProducts = function () {
        $http.get(contextPath + 'api/v1/cart')
       .then(function (response) {
            console.log(response.data);
           $scope.productsInCart = response.data;

        },function failureCallback(response) {
            alert("Ошибка"+response.data.message);
       });
    };

    $scope.delProductById = function (product) {
        $http.delete(contextPath + "api/v1/cart/" + product.id)
            .then(function (response) {
                $scope.loadProducts();
            }, function failureCallback(response) {
                alert(response.data.message);
            });
    };

    $scope.priceEdit = function (product) {
        var newPrice = prompt("Input product title:", product.quantity);
        if (newPrice != null) {
            product.price = newPrice;

            $http.put(contextPath + "api/v1/cart/", product)
                .then(function (response) {
                    $scope.loadProducts();
                }, function failureCallback(response) {
                    alert(response.data.message);
                });
        }
    };

    $scope.loadProducts();
});

