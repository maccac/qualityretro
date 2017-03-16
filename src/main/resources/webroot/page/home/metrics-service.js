angular.module("quality-retro").service("MetricsService", function ($resource) {
    var vm = this;

    vm.resource = $resource("/metrics/fluffy-bunnies/:version", {});

    vm.getMetrics = function (version) {
        return vm.resource.get({version: version});
    }
});
