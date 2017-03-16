angular.module("quality-retro").controller("homeCtrl", function ($scope, $rootScope, MetricsService) {
    var vm = this;
    $scope.vm = vm;

    MetricsService.getMetrics("latest").$promise
        .then(function (data) {
            $rootScope.team = {
                teamName: data.teamName,
                teamLogo: data.teamLogo,
                teamId: data.teamId,
                date: data.date
            };
            vm.metrics = data;
        });
});
