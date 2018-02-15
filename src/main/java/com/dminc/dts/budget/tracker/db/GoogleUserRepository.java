package com.dminc.dts.budget.tracker.db;

import com.dminc.dts.budget.tracker.model.GoogleUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoogleUserRepository extends CrudRepository<GoogleUser, String> {
    GoogleUser findGoogleUserById(String local_id);
}
