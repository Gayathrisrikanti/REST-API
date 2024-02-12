package com.example;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/flight-reservations")
public class ReservationWebController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationWebController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    
    @GetMapping("/add")
    public String addFlightReservationForm(Model model) {
        model.addAttribute("flightReservation", new Reservation());
        return "add-flight-reservation";
    }

   
    @PostMapping
    public Mono<String> submitFlightReservationForm(@ModelAttribute Reservation flightReservation) {
        return reservationService.createReservation(flightReservation)
                .thenReturn("redirect:/flight-reservations");
    }

   
    @GetMapping
    public String listFlightReservations(Model model) {
        Flux<Reservation> flightReservations = reservationService.getAllReservations();
        model.addAttribute("flightReservations", flightReservations);
        return "list-flight-reservation";
    }

 
}