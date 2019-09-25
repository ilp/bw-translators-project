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
                    getAll();
                }, function (err) {
                    console.log(err);
            });
        }

        /**
         * Updates a translator and stores his new values.
         * @param id id of translator that will be update.
         * @param translator object with new values.
         */
        function updateTranslator (id, translator) {
            $http.put('/translators/' + id, translator).
                then(function (response) {
                    console.log(response);
                    getAll();
                }, function (err) {
                    console.log(err);
            });
        }

        /**
         * Deletes a translator on database.
         * @param id translator's id that will be deleted.
         */
        function deleteTranslator(id) {
            $http.delete('/translators/' + id).
                then(function (response) {
                    console.log(response);
                    getAll();
            }, function (err) {
                console.log(err);
            });
        }

        $scope.showConfirm = function(id, ev) {
            var confirm = $mdDialog.confirm()
                .title('Are you sure?')
                .textContent('This translator will be deleted permanently')
                .targetEvent(ev)
                .ok('Yes')
                .cancel('No');

            $mdDialog.show(confirm).then(function() {
                deleteTranslator(id);
            }, function() {
                console.log("Canceled");
            });
        };

        $scope.showDialog = function(_translator, ev) {
            $mdDialog.show({
                controller: DialogController,
                templateUrl: 'templates/translator-dialog.html',
                parent: angular.element(document.body),
                targetEvent: ev,
                controllerAs: 'ctrl',
                locals: {translator: _translator},
                clickOutsideToClose: true,
                fullscreen: $scope.customFullscreen
            })
                .then(function(answer) {
                    $scope.status = answer;
                }, function() {
                    $scope.status = 'Closed!';
                });
        };

        function DialogController($scope, $mdDialog, translator) {

            $scope.newTranslator = {
                name: '',
                email: '',
                sourceLanguage: '',
                targetLanguage: ''
            };

            if(translator !== undefined) {
                $scope.newTranslator.name = translator.name;
                $scope.newTranslator.email = translator.email;
                $scope.newTranslator.sourceLanguage = translator.sourceLanguage;
                $scope.newTranslator.targetLanguage = translator.targetLanguage;
                $scope.titleDialog = "Update translator";
            } else {
                $scope.titleDialog = "Add new translator";
            }

            $scope.languages = ['en_gb', 'en_us', 'es_es', 'pt_br'];

            $scope.saveBtn = function () {
                if(translator !== undefined){
                    updateTranslator(translator.id, $scope.newTranslator);
                } else {
                    createNewTranslator($scope.newTranslator);
                }
                $mdDialog.hide();
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