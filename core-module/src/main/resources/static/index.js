(function () {
    angular
        .module('market-front', ['ngRoute', 'ngStorage'])
        .config(config)
        .run(run);

    function config($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'welcome/welcome.html',
                controller: 'welcomeController'
            })
            .when('/store', {
                templateUrl: 'store/store.html',
                controller: 'storeController'
            })
            .when('/product_details', {
                templateUrl: 'product_details/product_details.html',
                controller: 'productDetailsController'
            })
            .when('/cart', {
                templateUrl: 'cart/cart.html',
                controller: 'cartController'
            })
            .when('/order_confirmation', {
                templateUrl: 'order_confirmation/order_confirmation.html',
                controller: 'orderConfirmationController'
            })
            .when('/registration', {
                templateUrl: 'registration/registration.html',
                controller: 'registrationController'
            })
            .when('/create_product', {
                templateUrl: 'create_product/create_product.html',
                controller: 'createProductController'
            })
            .when('/personal_account', {
                templateUrl: 'personal_account/personal_account.html',
                controller: 'personalAccountController'
            })
            .when('/statistic', {
                templateUrl: 'statistic/statistic.html',
                controller: 'statisticController'
            })
            .when('/order_show', {
                templateUrl: 'order_show/order_show.html',
                controller: 'orderShowController'
            })
            .otherwise({
                redirectTo: '/'
            });
    }

    function run($rootScope, $http, $localStorage) {
        if ($localStorage.webMarketUser) {
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.webMarketUser.token;
        }
    }

})();


angular.module('market-front').controller('indexController', function ($rootScope, $scope, $http, $localStorage, $location) {
    const contextPath = 'http://localhost:8189/market/';

    $scope.tryToAuth = function () {
        $http.post(contextPath + 'api/v1/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $http.defaults.headers.common.name = 'name ' + $scope.user.username;
                    $localStorage.webMarketUser = {username: $scope.user.username, token: response.data.token};
                    $rootScope.lkname = $scope.user.username;


                    $scope.user.username = null;
                    $scope.user.password = null;
                    $location.path("/");
                    $rootScope.setCartSum();
                }
            }, function errorCallback(response) {
                alert(response.data.message)
            });
    };

    $scope.tryToLogout = function () {
        $location.path("/");
        $scope.clearUser();
        if ($scope.user.username) {
            $scope.user.username = null;
        }
        if ($scope.user.password) {
            $scope.user.password = null;
        }

        $rootScope.setCartSum();
    };

    $scope.clearUser = function () {
        delete $localStorage.webMarketUser;
        $http.defaults.headers.common.Authorization = '';
        $http.defaults.headers.common.name = null;

    };

    $rootScope.isUserLoggedIn = function () {
        if ($localStorage.webMarketUser) {
            return true;
        } else {
            return false;
        }
    };

    $rootScope.setLkName = function () {
        if ($localStorage.webMarketUser) {
            $rootScope.lkname = $localStorage.webMarketUser.username;
        } else {
            $rootScope.lkname = "noName";
        }
    };

    $rootScope.setCartSum = function () {

        $rootScope.cartsumm = 0;
        $http({
            url: contextPath + 'api/v1/cart/sum/',
            method: 'GET'
        }).then(function (response) {
            $scope.totalSum = response.data;
            $rootScope.cartsumm = $scope.totalSum;
        });
    };

    $rootScope.getOrSaveTmpId = function () {
        if ($localStorage.tmpId) {
            $http.defaults.headers.common.tmpId = 'tmpId ' + $localStorage.tmpId.tmpId;
        }
        if ($rootScope.isUserLoggedIn()) {
            $http.defaults.headers.common.name = 'name ' + $localStorage.webMarketUser.username;
        } else {
            $http.defaults.headers.common.name = null;
        }
        if (!$rootScope.isUserLoggedIn() && !$localStorage.tmpId) {
            $http.get(contextPath + 'api/v1/auth/getid')
                .then(function (response) {
                    $scope.tmpId = response.data;
                    $http.defaults.headers.common.tmpId = 'tmpId ' + $scope.tmpId;
                    $localStorage.tmpId = {tmpId: $scope.tmpId};
                    alert($localStorage.tmpId.tmpId);
                });
        }


    };

    $scope.showCart = function () {
        $location.path("/cart");
    };

    $scope.showPersonalAccount = function () {
        $location.path("/personal_account")
    };

    $scope.showOrder = function () {
        $location.path("/order_show")
    };

    $rootScope.cartsumm = 0;
    $rootScope.getOrSaveTmpId();
    $rootScope.setCartSum();
    $rootScope.setLkName();

});