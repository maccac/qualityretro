angular.module("quality-retro").controller("homeCtrl", function ($scope, MetricsService) {
    var vm = this;
    $scope.vm = vm;

    MetricsService.getMetrics("latest").$promise
        .then(function (data) {
            vm.metrics = data;
        });
});
