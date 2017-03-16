function ChartController($scope) {
    var vm = this;

    vm.metric = vm.metric || {};

    console.log(vm.metric);

    vm.colors = ["rgb(154,50,30)", "rgb(255,204,0)", "rgb(50,204,40)"];
    vm.labels = ['Latest', '2017-01-01'];
    vm.series = ["Green", "Yellow", "Red"];
    vm.data = [
        [vm.metric.redIndicators, 1], // red
        [vm.metric.yellowIndicators, 1], // yellow
        [vm.metric.greenIndicators, 5] // green
    ];

    vm.options = {
        scales: {
            yAxes: [{
                stacked: true
            }]
        }
    };

    $scope.$watch('vm.metric', function () {
        vm.data = [
                [vm.metric.redIndicators, 1], // red
                [vm.metric.yellowIndicators, 1], // yellow
                [vm.metric.greenIndicators, 5] // green
            ];
    });

}

angular.module("quality-retro").component("chartComponent", {
    templateUrl: "/page/home/chart-component.html",
    controller: ChartController,
    controllerAs: "vm",
    bindings: {
        "metric": "<"
    }
});
