describe('controller: OrganizationSideBarController', function () {

    var controller, scope;

    beforeEach(function() {
        module('core');
        module('vireo');
        module('mock.organizationCategoryRepo');
        module('mock.organizationRepo');
        module('mock.modalService');
        module('mock.restApi');
        module('mock.storageService');
        module('mock.wsApi');

        inject(function ($controller, _$q_, $rootScope, $window, _ModalService_, _OrganizationCategoryRepo_, _OrganizationRepo_, _RestApi_, _StorageService_, _WsApi_) {
            installPromiseMatchers();
            scope = $rootScope.$new();

            controller = $controller('OrganizationSideBarController', {
                $q: _$q_,
                $scope: scope,
                $window: $window,
                ModalService: _ModalService_,
                OrganizationCategoryRepo: _OrganizationCategoryRepo_,
                OrganizationRepo: _OrganizationRepo_,
                RestApi: _RestApi_,
                StorageService: _StorageService_,
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
