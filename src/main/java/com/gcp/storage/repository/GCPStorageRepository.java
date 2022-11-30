package com.gcp.storage.repository;

import com.gcp.storage.model.FPDetails;
import com.google.cloud.spring.data.datastore.repository.DatastoreRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GCPStorageRepository extends DatastoreRepository {
    List<FPDetails> findAllByUpdatedBy(String updatedBy);
}
