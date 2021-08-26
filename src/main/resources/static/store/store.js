angular.module('market-front').controller('storeController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market/';
    $scope.currentPage = 1;

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

    $scope.toChangePage = function (increment) {
        if (($scope.productsPage.first && increment < 0) || ($scope.productsPage.last && increment > 0)) {
            increment = 0;

        }
        $scope.currentPage = $scope.currentPage + increment;
        $scope.loadProducts($scope.currentPage - 1);
    };

    $scope.loadProducts();
});

