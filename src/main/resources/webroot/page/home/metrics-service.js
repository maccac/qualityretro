angular.module("quality-retro").service("MetricsService", function ($resource) {
    var vm = this;

    vm.resource = $resource("/metrics/:teamId/:version", {});

    vm.getMetrics = function (teamId, version) {
        return vm.resource.get({
            teamId: teamId,
            version: version
        });
    }
});
