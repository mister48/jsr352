'use strict';

angular.module('jberetUI.details',
    ['ui.router', 'ngAnimate', 'ngTouch', 'ui.grid', 'ui.grid.selection', 'ui.grid.exporter'])

    .config(['$stateProvider', function ($stateProvider) {
        $stateProvider.state('details', {
            url: '/jobexecutions/:jobExecutionId',
            templateUrl: 'details/details.html',
            controller: 'DetailsCtrl',
            params: {jobExecutionEntity: null}
        });
    }])

    .controller('DetailsCtrl', ['$scope', '$http', '$stateParams', '$location',
        function ($scope, $http, $stateParams, $location) {
            var dateCellTemp =
                '<div ng-class="col.colIndex()">{{COL_FIELD | date:"yyyy-MM-dd HH:mm:ss"}}</div>';

            $scope.gridOptions = {
                enableGridMenu: true,
                enableSelectAll: true,
                exporterCsvFilename: 'step-execution.csv',

                enableFiltering: true,
                showGridFooter: true,
                minRowsToShow: 5,
                rowHeight: 40,

                columnDefs: [
                    {name: 'stepExecutionId', type: 'number'},
                    {name: 'stepName', cellTooltip: true},
                    {name: 'persistentUserData', cellTooltip: true},
                    {name: 'batchStatus'},
                    {name: 'exitStatus', cellTooltip: true},
                    {name: 'startTime', cellTemplate: dateCellTemp, cellTooltip: true, type: 'date'},
                    {name: 'endTime', cellTemplate: dateCellTemp, cellTooltip: true, type: 'date'},
                    {name: 'metrics'}
                ]
            };

            if ($stateParams.jobExecutionEntity) {
                $scope.jobExecutionEntity = $stateParams.jobExecutionEntity;

                $http.get($scope.jobExecutionEntity.href + '/stepexecutions')
                    .then(function (responseData) {
                        $scope.gridOptions.data = responseData.data;
                    }, function (responseData) {
                        console.log(responseData);
                    });
            } else {
                var paths = $location.path().split('/');
                var length = paths.length;
                var idPart = length == 1 ? paths[0] :
                    paths[length - 1].length == 0 ? paths[length - 2] : paths[length - 1];
                $http.get('http://localhost:8080/restAPI/api/jobexecutions/' + idPart)
                    .then(function (responseData) {
                        $scope.jobExecutionEntity = responseData.data;

                        $http.get($scope.jobExecutionEntity.href + '/stepexecutions')
                            .then(function (responseData) {
                                $scope.gridOptions.data = responseData.data;
                            }, function (responseData) {
                                console.log(responseData);
                            });
                    }, function (responseData) {
                        console.log(responseData);
                    });
            }
        }]);
