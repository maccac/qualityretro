angular.module("quality-retro").service("MetricsService", function ($resource) {
    var vm = this;

    vm.resource = $resource("/metrics/fluffy-bunnies", {});

    vm.getMetrics = function () {
        return vm.resource.get({});
    }
});
