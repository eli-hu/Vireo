describe('controller: NewSubmissionController', function () {

    var controller, scope;

    beforeEach(function() {
        module('core');
        module('vireo');
        module('mock.managedConfigurationRepo');
        module('mock.modalService');
        module('mock.organizationRepo');
        module('mock.restApi');
        module('mock.storageService');
        module('mock.studentSubmissionRepo');
        module('mock.wsApi');

        inject(function ($controller, $location, _$q_, $rootScope, $window, SubmissionStates, _ManagedConfigurationRepo_, _ModalService_, _OrganizationRepo_, _RestApi_, _StorageService_, _StudentSubmissionRepo_, _WsApi_) {
            installPromiseMatchers();
            scope = $rootScope.$new();

            controller = $controller('NewSubmissionController', {
                $location: $location,
                $q: _$q_,
                $scope: scope,
                $window: $window,
                SubmissionStates: SubmissionStates,
                ManagedConfigurationRepo: _ManagedConfigurationRepo_,
                ModalService: _ModalService_,
                OrganizationRepo: _OrganizationRepo_,
                StorageService: _StorageService_,
                StudentSubmissionRepo: _StudentSubmissionRepo_,
                RestApi: _RestApi_,
                WsApi: _WsApi_
            });

            // ensure that the isReady() is called.
            scope.$digest();
        });
    });

    describe('Is the controller defined', function () {
        it('should be defined', function () {
            expect(controller).toBeDefined();
        });
    });

});
