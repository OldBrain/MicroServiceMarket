angular.module('market-front').controller('registrationController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:8189/market/';

    $scope.createUser = function () {
        if ($scope.new_user == null) {
            alert('Форма не заполнена');
            return;
        }
        $http.put(contextPath + 'api/v1/reg', $scope.new_user)
            .then(function successCallback (response) {
                    $scope.new_user = null;
                    alert('Регистрация прошла успешно');
                    $location.path('/');
            }, function failureCallback (response) {
                console.log(response);
                alert(response.data.message);
            });
    }
});