angular.module('market-front', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market/';
    $scope.currentPage = 1;

    $scope.titleEdit = function (product) {
        var newTitle = prompt("Input product title:", product.title);
        if (newTitle != null) {
            product.title = newTitle;
            $http.put(contextPath + "api/v1/products/", product)
                .then(function (response) {
                    $scope.loadProducts($scope.currentPage - 1);
                }, function failureCallback(response) {
                    alert(response.data.message);
                });
        }
    };

    $scope.priceEdit = function (product) {
        var newPrice = prompt("Input product title:", product.price);
        if (newPrice != null) {
            product.price = newPrice;

            $http.put(contextPath + "api/v1/products/", product)
                .then(function (response) {
                    $scope.loadProducts($scope.currentPage - 1);
                }, function failureCallback(response) {
                    alert(response.data.message);
                });
        }
    };

    $scope.infoById = function (product) {
        $http.get(contextPath + "api/v1/products/" + product.id)
            .then(function (response) {
                alert(product.title);
            }, function failureCallback(response) {
                alert(response.data.message);
            });
    };

    $scope.loadProducts = function (pageIndex = 0) {
        $http({
            url: contextPath + "api/v1/products",
            method: 'GET',
            params: {
                p: pageIndex
            }
        }).then(function (response) {
            console.log(response.data);
            $scope.productsPage = response.data;
            $scope.totalPages = $scope.productsPage.totalPages;
        });
    };

    $scope.delProductById = function (product) {
        $http.delete(contextPath + "api/v1/products/" + product.id)
            .then(function (response) {
                $scope.loadProducts();
            }, function failureCallback(response) {
                alert(response.data.message);
            });
    };

    $scope.createNewProduct = function () {
        $http.post(contextPath + 'api/v1/products', $scope.new_product)
            .then(function successCallback(response) {
                $scope.loadProducts($scope.currentPage - 1);
                $scope.new_product = null;
            }, function failureCallback(response) {
                alert(response.data.message);
            });
    }

    $scope.toChangePage = function (increment) {
        if (($scope.productsPage.first && increment < 0) || ($scope.productsPage.last && increment > 0)) {
            increment = 0;

        }
        $scope.currentPage = $scope.currentPage + increment;
        $scope.loadProducts($scope.currentPage - 1);
    };

    $scope.loadProducts();
});

