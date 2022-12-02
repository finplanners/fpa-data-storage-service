package com.gcp.storage.repository;

import com.gcp.storage.model.FPDetails;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class GCPStorageRepositoryTest {
    @Mock
    private GCPStorageRepository gcpStorageRepository;

    @Test
    public void shouldReturnJsonData() {
        FPDetails fpDetails = new FPDetails(1L, "sample", "User1");

        when(gcpStorageRepository.findAllByUpdatedBy("User1")).thenReturn(Collections.singletonList(fpDetails));

        List<FPDetails> actualFPDetails = gcpStorageRepository.findAllByUpdatedBy("User1");
        assertEquals(actualFPDetails.get(0).getFpData(), "sample");
    }

    @Test
    public void shouldReturnEmptyListWhenNoRecordFoundForGivenUpdatedBy() {
        when(gcpStorageRepository.findAllByUpdatedBy("User1")).thenReturn(Collections.emptyList());

        List<FPDetails> actualFPDetails = gcpStorageRepository.findAllByUpdatedBy("User1");
        assertEquals(actualFPDetails.size(), 0);
    }
}