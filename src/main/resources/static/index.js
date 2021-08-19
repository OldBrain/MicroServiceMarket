angular.module('market-front', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market/';
    $scope.currentPage = 0;

    $scope.loadProducts = function (pageIndex = 0) {
        $http({
            url: contextPath + "products",
            method: 'GET',
            params: {
                p: pageIndex
            }
        }).then(function (response) {
            console.log(response);
            $scope.productsPage = response.data;
        });
    };

    $scope.delProductById = function (product) {
        $http.get(contextPath + "products/del/" + product.id)
            .then(function (response) {
                $scope.loadProducts();
            });
    };

    $scope.toChangePage = function (increment) {
        $scope.currentPage = $scope.currentPage + increment;
        /**Не удалось достать $scope.currentPage.data.totalPages поэтому проверку на
         * индекс страницы на сделал
         */

        if ($scope.currentPage < 0) {
            $scope.currentPage = 0;
        }
        $scope.loadProducts($scope.currentPage);
    };

    $scope.loadProducts();
});

