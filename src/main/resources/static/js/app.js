angular.module('App', ['ngMaterial', 'ngMessages'])
    .controller("TranslatorCtrl", function ($scope, $http, $mdDialog) {

        getAll();

        /**
         * Gets all translators on database.
         */
        function getAll () {
            $http.get('/translators').
            then(function(response) {
                $scope.translators = response.data['content'];
            });
        }

        /**
         * Creates a new translator and stores on database.
         */
        function createNewTranslator (translator) {
            $http.post('/translators', translator).
            then(function (response) {
                console.log(response);
            }, function (err) {
                console.log(err);
            });
        }

        $scope.showDialog = function(ev) {
            $mdDialog.show({
                controller: DialogController,
                templateUrl: 'templates/translator-dialog.html',
                parent: angular.element(document.body),
                targetEvent: ev,
                clickOutsideToClose:true,
                fullscreen: $scope.customFullscreen
            })
                .then(function(answer) {
                    $scope.status = 'You said the information was "' + answer + '".';
                }, function() {
                    $scope.status = 'You cancelled the dialog.';
                });
        };

        function DialogController($scope, $mdDialog, $http) {

            $scope.newTranslator = {
                name: '',
                email: '',
                sourceLanguage: '',
                targetLanguage: ''
            };

            $scope.languages = ['en_gb', 'en_us', 'es_es', 'pt_br'];

            $scope.createTranslator = function () {
                createNewTranslator($scope.newTranslator);
            };

            $scope.hide = function () {
                $mdDialog.hide();
            };

            $scope.cancel = function () {
                $mdDialog.cancel();
            };

            $scope.answer = function (answer) {
                $mdDialog.hide(answer);
            };
        }

    });