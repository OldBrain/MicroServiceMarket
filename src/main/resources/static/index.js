

(function () {
    angular
        .module('market-front', ['ngRoute','ngStorage'])
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
            .when('/cart', {
                templateUrl: 'cart/cart.html',
                controller: 'cartController'
            })
            .when('/order_confirmation', {
                templateUrl: 'order_confirmation/order_confirmation.html',
                controller: 'orderConfirmationController'
            })

            .when('/reg', {
                templateUrl: 'registration/registration.html',
                controller: 'registrationController'
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




angular.module('market-front').controller('indexController', function ($rootScope, $scope, $http, $localStorage,$location) {
    const contextPath = 'http://localhost:8189/market/';

    $scope.tryToAuth = function () {
        $http.post(contextPath + 'api/v1/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
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
        $scope.clearUser();
        if ($scope.user.username) {
            $scope.user.username = null;
        }
        if ($scope.user.password) {
            $scope.user.password = null;
        }
        $location.path("/");
        $rootScope.setCartSum();
    };

    $scope.clearUser = function () {
        delete $localStorage.webMarketUser;
        $http.defaults.headers.common.Authorization = '';

    };

    $rootScope.isUserLoggedIn = function () {
        if ($localStorage.webMarketUser) {
            return true;
        } else {
            return false;
        }
    };

    $rootScope.setLkName = function(){
        if ($localStorage.webMarketUser) {
            $rootScope.lkname = $localStorage.webMarketUser.username;
        } else {
            $rootScope.lkname = "noName";
        }
    };

    $rootScope.setCartSum = function () {
        $rootScope.cartsumm=0;
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
        if (!$rootScope.isUserLoggedIn()&&!$localStorage.tmpId) {
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



    $rootScope.cartsumm=0;
    $rootScope.getOrSaveTmpId();
    $rootScope.setCartSum();
    $rootScope.setLkName();

});