angular.module("quality-retro", ["ngRoute", "ngResource", "chart.js"]);

angular.module("quality-retro").config(function ($routeProvider) {
    $routeProvider.when("/", {
        templateUrl: "/page/home/home-controller.html",
        controller: "homeCtrl"
    });
});
