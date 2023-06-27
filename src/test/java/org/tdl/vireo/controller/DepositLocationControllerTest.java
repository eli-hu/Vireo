package org.tdl.vireo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import edu.tamu.weaver.response.ApiResponse;
import edu.tamu.weaver.response.ApiStatus;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import org.tdl.vireo.model.DepositLocation;
import org.tdl.vireo.model.repo.DepositLocationRepo;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class DepositLocationControllerTest extends AbstractControllerTest {

    @Mock
    private DepositLocationRepo depositLocationRepo;

    @InjectMocks
    private DepositLocationController depositLocationController;

    private DepositLocation mockDepositLocation1;
    private DepositLocation mockDepositLocation2;
    private DepositLocation mockDepositLocation3;
    private DepositLocation mockDepositLocation4;

    private static List<DepositLocation> mockDepositLocations;

    @BeforeEach
    public void setup() {
        mockDepositLocation1 = new DepositLocation("Location 1", "http://localhost/1", "Collection 1", "User 1", "Password 1", "Behalf Of 1", null, "Depsitor 1", 100);
        mockDepositLocation1.setId(1L);
        mockDepositLocation1.setPosition(1L);

        mockDepositLocation2 = new DepositLocation("Location 2", "http://localhost/2", "Collection 2", "User 2", "Password 2", "Behalf Of 2", null, "Depsitor 2", 200);
        mockDepositLocation2.setId(2L);
        mockDepositLocation2.setPosition(2L);

        mockDepositLocation3 = new DepositLocation("Location 3", "http://localhost/3", "Collection 3", "User 3", "Password 3", "Behalf Of 3", null, "Depsitor 3", 300);
        mockDepositLocation3.setId(3L);
        mockDepositLocation3.setPosition(3L);

        mockDepositLocation4 = new DepositLocation("Location 4", "http://localhost/4", "Collection 4", "User 4", "Password 4", "Behalf Of 4", null, "Depsitor 4", 400);
        mockDepositLocation4.setId(4L);
        mockDepositLocation4.setPosition(4L);

        mockDepositLocations = new ArrayList<DepositLocation>(Arrays.asList(new DepositLocation[] { mockDepositLocation1, mockDepositLocation2, mockDepositLocation3 }));
    }

    @Test
    public void testAllDepositLocations() {
        when(depositLocationRepo.findAllByOrderByPositionAsc()).thenReturn(mockDepositLocations);

        ApiResponse response = depositLocationController.allDepositLocations();
        assertEquals(ApiStatus.SUCCESS, response.getMeta().getStatus());

        List<?> list = (ArrayList<?>) response.getPayload().get("ArrayList<DepositLocation>");
        assertEquals(mockDepositLocations.size(), list.size());
    }

    @Test
    public void testCreateDepositLocation() {
        when(depositLocationRepo.create(anyMap())).thenReturn(mockDepositLocation4);

        Map<String, Object> map = new HashMap<>();

        ApiResponse response = depositLocationController.createDepositLocation(map);
        assertEquals(ApiStatus.SUCCESS, response.getMeta().getStatus());

        DepositLocation depositLocation = (DepositLocation) response.getPayload().get("DepositLocation");
        assertEquals(mockDepositLocation4.getId(), depositLocation.getId());
    }

    @Test
    public void testUpdateDepositLocation() {
        when(depositLocationRepo.update(any(DepositLocation.class))).thenReturn(mockDepositLocation4);

        ApiResponse response = depositLocationController.updateDepositLocation(mockDepositLocation3);
        assertEquals(ApiStatus.SUCCESS, response.getMeta().getStatus());

        DepositLocation depositLocation = (DepositLocation) response.getPayload().get("DepositLocation");
        assertEquals(mockDepositLocation4.getId(), depositLocation.getId());
    }

    @Test
    public void testRemoveDepositLocation() {
        doNothing().when(depositLocationRepo).remove(any(DepositLocation.class));

        ApiResponse response = depositLocationController.removeDepositLocation(mockDepositLocation1);
        assertEquals(ApiStatus.SUCCESS, response.getMeta().getStatus());

        verify(depositLocationRepo, times(1)).remove(any(DepositLocation.class));
    }

    @Test
    public void testReorderDepositLocations() {
        doNothing().when(depositLocationRepo).reorder(any(Long.class), any(Long.class));

        ApiResponse response = depositLocationController.reorderDepositLocations(1L, 2L);
        assertEquals(ApiStatus.SUCCESS, response.getMeta().getStatus());

        verify(depositLocationRepo, times(1)).reorder(any(Long.class), any(Long.class));
    }

}
