package com.gcp.storage.controller;

import com.gcp.storage.model.FPDetails;
import com.gcp.storage.service.GCPStorageService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class GCPStorageControllerTest {

    @Mock
    private GCPStorageService gcpStorageService = mock(GCPStorageService.class);

    @InjectMocks
    GCPStorageController gcpStorageController = new GCPStorageController(gcpStorageService);

    @Test
    public void shouldReturnJsonDataFromDataStore() throws Exception {
        FPDetails fpDetails = new FPDetails(1L, "Sample", "User1");
        when(gcpStorageService.getDataByUpdatedBy("User1")).thenReturn(Collections.singletonList(fpDetails));

        List<FPDetails> fpDetailsList = gcpStorageController.getData("User1");

        assertEquals(Collections.singletonList(fpDetails), fpDetailsList);
    }
}