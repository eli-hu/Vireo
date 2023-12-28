package org.tdl.vireo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tdl.vireo.Application;

@ActiveProfiles(value = { "test" })
@SpringBootTest(classes = { Application.class })
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class SystemDataLoaderTest {

    @Autowired
    private SystemDataLoader systemDataLoader;

    @Autowired
    private ProquestCodesService proquesteCodesService;

    @Autowired
    private DepositorService depositorService;

    @Autowired
    private DefaultSettingsService defaultSettingsService;

    @Autowired
    private DefaultSubmissionListColumnService defaultSubmissionListColumnService;

    @Autowired
    private DefaultFiltersService defaultFiltersService;

    @Test
    public void testLoadSystemData() throws Exception {
        this.assertPersistedSystemData(false);

        // reload to ensure nothing changes
        // technically the cache clears after restart
        // however, this is still an undesired side effect of load system data
        systemDataLoader.loadSystemData();
        this.assertPersistedSystemData(true);
    }

    private void assertPersistedSystemData(boolean isReload) {
        assertEquals(10, this.defaultSubmissionListColumnService.getDefaultSubmissionListColumns().size(),
            isReload
                ? "Incorrect number of default submission list columns after reload"
                : "Incorrect number of default submission list columns");

        assertEquals(5, this.defaultFiltersService.getDefaultFilter().size(),
            isReload
                ? "Incorrect number of default filters after reload"
                : "Incorrect number of default filters");
    }

}
