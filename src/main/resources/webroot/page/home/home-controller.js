angular.module("quality-retro").controller("homeCtrl", function ($scope, MetricsService) {
    var vm = this;
    $scope.vm = vm;

    vm.metrics = MetricsService.getMetrics();
    vm.metrics.$promise.then(function () {
        $scope.colors = ["rgb(154,50,30)", "rgb(255,204,0)", "rgb(50,204,40)",];
        $scope.labels = ['Latest', '2017-01-01'];
        $scope.series = ["Green", "Yellow", "Red"];
        $scope.data = [
            [0, 1], // red
            [0, 1], // yellow
            [7, 5] // green
        ];
        $scope.options = {
            scales: {
                yAxes: [{
                    stacked: true
                }],
            }
        };
    });

});
