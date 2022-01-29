package com.artzh7.currere.repo;

import com.artzh7.currere.entity.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepo extends CrudRepository<Booking, Long> {
}
