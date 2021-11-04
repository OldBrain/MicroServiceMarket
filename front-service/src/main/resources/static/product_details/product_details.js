angular.module('market-front').controller('productDetailsController', function ($scope, $http, $location, $rootScope, $localStorage) {
    const contextPath = 'http://localhost:5555/core/';
    var id = $localStorage.productId;
    $scope.username = $localStorage.webMarketUser.username;

    $scope.showProduct = function () {
        $http.get(contextPath + 'api/v1/products/' + id)
            .then(function successCallback(response) {
                $scope.product = response.data;
            }, function failureCallback(response) {
                console.log(response);
                // alert(response.data.messages);
            });


    }
    $scope.addToCart = function (productId) {
        $http({
            url: 'http://localhost:5555/cart/api/v1/cart/' + $localStorage.webMarketGuestCartId + '/add/' + id,
            method: 'GET'
        }).then(function (response) {
            $rootScope.setCartSum();
        });
    };

    $scope.showComments = function () {
        $http.get(contextPath + 'api/v1/comments/' + id)
            .then(function successCallback(response) {
                $scope.comment = response.data;
                console.log($scope.comment);
            });

    }

    $scope.showCurrentComments = function () {
        $http.get('http://localhost:5555/core/api/v1/comments/current/' + id)
            .then(function successCallback(response) {
                $scope.currentComment = response.data;
                console.log($scope.currentComment);
            });

    }

    $scope.isNameExists = function () {
        $http.get('http://localhost:5555/core/api/v1/orders/exists/' + id)
            .then(function successCallback(response) {
                $scope.isExists = response.data;
                // console.log($scope.isExists);
               }, function failureCallback(response) {
                alert(response.data.messages);
            });
    };

    $rootScope.isShow = function () {
       return  $scope.isExists;
    };



    $scope.addComment = function () {
        $scope.new_comment.productId = id;
        $scope.new_comment.username = $scope.username;
        // $scope.new_comment.content= $scope.currentComment.content;

        $http({
        url: 'http://localhost:5555/core/api/v1/comments',
        method: 'POST',
        data: $scope.new_comment
    })
            .then(function successCallback(response) {
                // $scope.new_comment.username = $scope.username;
                // $scope.new_comment = null;
                alert('Комментарий успешно создан');
            }, function failureCallback(response) {
                // console.log(response);
                alert(response.data.messages);
            });
    }

    $scope.showComments();
    $scope.showProduct();
    $scope.isNameExists();
    $scope.showCurrentComments();
});