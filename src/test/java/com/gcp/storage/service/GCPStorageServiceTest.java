package com.gcp.storage.service;

import com.gcp.storage.model.FPDetails;
import com.gcp.storage.repository.GCPStorageRepository;
import com.google.cloud.storage.Storage;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class GCPStorageServiceTest {

    @Mock
    GCPStorageRepository gcpStorageRepository;
    @Autowired
    GCPStorageService gcpStorageService;

    @Test
    public void shouldSaveFPDetailsInGoogleDataStore() {
        FPDetails fpDetails = new FPDetails(1L, "sample", "User1");
        when(gcpStorageRepository.save(fpDetails)).thenReturn(fpDetails);

        String savedFpDetails = gcpStorageService.saveData(fpDetails);

        assertEquals(fpDetails.toString(), savedFpDetails);
    }

    @Test
    public void shouldGetFPDetailsByUpdatedByFromGoogleDataStore() {
        FPDetails fpDetails = new FPDetails(1L, "sample", "User1");
        when(gcpStorageRepository.findAllByUpdatedBy("User1")).thenReturn(Collections.singletonList(fpDetails));

        List<FPDetails> actualFPDetails = gcpStorageService.getDataByUpdatedBy("User1");

        assertEquals(Collections.singletonList(fpDetails), actualFPDetails);
    }
}