angular.module('market-front').controller('registrationController', function ($scope, $http, $localStorage, $location, $rootScope) {
    // const contextPath = 'http://localhost:5555/auth/api/v1/auth/';


    $scope.createUser = function () {
        if ($scope.new_user.username == null || $scope.new_user.password == null || $scope.new_user.email == null) {
            alert('Форма не заполнена до конца');
            return;
        }
        $http.put('http://localhost:5555/auth/api/v1/auth/new', $scope.new_user)

            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.webMarketUser = {username: $scope.new_user.username, token: response.data.token};
                    $rootScope.lkname = $scope.new_user.username;
                    $location.path('/');
                    $scope.new_user = null;
                    alert('Регистрация прошла успешно ');
                    $scope.user.username = null;
                    $scope.user.password = null;

                }
            }, function failureCallback(response) {
                console.log(response);
                alert(response.data.message);
            });
    }

    $scope.create = function () {
        alert("Ok")
    };

    $rootScope.setCartSum();
});