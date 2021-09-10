angular.module('market-front').controller('storeController', function ($scope, $http,$rootScope,$localStorage) {
    const contextPath = 'http://localhost:8189/market/';
    $scope.currentPage = 1;

    $scope.addToCart = function (productId) {
        $http({
            url: contextPath + 'api/v1/cart/add/' + productId,
            method: 'GET'
        }).then(function (response) {
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

    $scope.toChangePage = function (increment) {
        if (($scope.productsPage.first && increment < 0) || ($scope.productsPage.last && increment > 0)) {
            increment = 0;

        }
        $scope.currentPage = $scope.currentPage + increment;
        $scope.loadProducts($scope.currentPage - 1);
    };

    $rootScope.getTmpAndSaveId = function () {
        if ($localStorage.tmpId) {
            $http.defaults.headers.common.tmpId = 'tmpId ' + $localStorage.tmpId.tmpId;
        }
        if (!$rootScope.isUserLoggedIn()&&!$localStorage.tmpId) {
        $http.get(contextPath + 'api/v1/auth')
            .then(function (response) {
                $scope.tmpId = response.data;
                $http.defaults.headers.common.tmpId = 'tmpId ' + $scope.tmpId;
                $localStorage.tmpId = {tmpId: $scope.tmpId};
                alert($localStorage.tmpId.tmpId);
            });
        }

    };
    $rootScope.getTmpAndSaveId();
    $scope.loadProducts();
});

