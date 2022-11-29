package com.gcp.storage.repository;

import com.gcp.storage.model.FPDetails;
import com.google.cloud.spring.data.datastore.repository.DatastoreRepository;

import java.util.List;

public interface GCPStorageRepository extends DatastoreRepository {
    List<FPDetails> findAllByUpdatedBy(String updatedBy);
}