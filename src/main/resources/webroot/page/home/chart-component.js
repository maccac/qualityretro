function ChartController($scope) {
    var vm = this;

    vm.metric = vm.metric || {};

    vm.colors = ["rgb(154,50,30)", "rgb(255,204,0)", "rgb(50,204,40)"];
    vm.labels = [''];
    vm.series = ["Green", "Yellow", "Red"];
    setChartData();

    vm.options = {
        scales: {
            yAxes: [{
                stacked: true,
                display: false
            }],
            xAxes: [{
                display: false
            }]
        }
    };

    function setChartData() {
        if (vm.metric.hasOwnProperty("redIndicators")) {
            vm.data = [
                [vm.metric.redIndicators], // red
                [vm.metric.yellowIndicators], // yellow
                [vm.metric.greenIndicators] // green
            ];
        }
    }
    $scope.$watch('vm.metric', function () {
        setChartData();
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
