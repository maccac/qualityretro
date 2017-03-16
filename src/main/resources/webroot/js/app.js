angular.module("quality-retro", ["ngRoute"]);

angular.module("quality-retro").config(function ($routeProvider) {
    $routeProvider.when("/", {
        templateUrl: "/page/home/home-controller.html",
        controller: "homeCtrl"
    });
});
