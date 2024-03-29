package com.example;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import java.time.LocalDate;

@Repository
public interface ReservationRepository extends ReactiveMongoRepository<Reservation, String> {

   
    Flux<Reservation> findByLastName(String lastName);

    
}