angular.module('market-front').controller('storeController', function ($scope, $location, $http,$rootScope,$localStorage) {
    const contextPath = 'http://localhost:5555/core/';
    $scope.currentPage = 1;

    $scope.addToCart = function (productId) {
        $http({
            url: 'http://localhost:5555/cart/api/v1/cart/' + $localStorage.webMarketGuestCartId + '/add/' + productId,
            method: 'GET'
        }).then(function (response) {
            $rootScope.setCartSum();
        });
    };

    $scope.aboutProduct = function (productId) {
        $rootScope.productId = productId;
        $location.path("/product_details");
    };

    $scope.loadProducts = function (pageIndex = 0) {
        $http({
            url: $rootScope.coreContextPath + "api/v1/products",
            method: 'GET',
            params: {
                p: pageIndex,
                title: $scope.filter ? $scope.filter.title : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null
            }
        }).then(function (response) {

            console.log(response.data);
            $scope.productsPage = response.data;
            $scope.totalPages = $scope.productsPage.totalPages;
        });
    };

    $scope.toChangePage = function (increment) {
        if (($scope.productsPage.first && increment < 0) || ($scope.productsPage.last && increment > 0)) {
            increment = 0;

        }
        $scope.currentPage = $scope.currentPage + increment;
        $scope.loadProducts($scope.currentPage - 1);
    };


    $scope.loadProducts();
    $rootScope.setCartSum();
    $rootScope.setLkName();
});

