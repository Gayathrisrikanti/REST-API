package com.example;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Mono<Reservation> createReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public Flux<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Mono<Reservation> getReservationById(String id) {
        return reservationRepository.findById(id);
    }

    public Flux<Reservation> getReservationsByLastName(String lastName) {
        return reservationRepository.findByLastName(lastName);
    }

 
    public Mono<Reservation> updateReservation(String id, Reservation updatedReservation) {
        return reservationRepository.findById(id)
                .flatMap(reservation -> {
                    
                    reservation.setFirstName(updatedReservation.getFirstName());
                    reservation.setLastName(updatedReservation.getLastName());
                    reservation.setNumberOfPassengers(updatedReservation.getNumberOfPassengers());
                    reservation.setClassType(updatedReservation.getClassType());
                    reservation.setPhoneNumber(updatedReservation.getPhoneNumber());
                    reservation.setTime(updatedReservation.getTime());
                    reservation.setDateOfDeparting(updatedReservation.getDateOfDeparting());

                    
                    return reservationRepository.save(reservation);
                });
    }


    public Mono<Void> deleteReservation(String id) {
        return reservationRepository.deleteById(id);
    }

    
}

