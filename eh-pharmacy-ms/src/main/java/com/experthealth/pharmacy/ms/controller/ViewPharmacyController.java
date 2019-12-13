package com.experthealth.pharmacy.ms.controller;

import com.experthealth.pharmacy.ms.exception.MalformedJsonException;
import com.experthealth.pharmacy.ms.model.Pharmacy;
import com.experthealth.pharmacy.ms.service.PharmacyDAO;
import com.experthealth.pharmacy.ms.service.PharmacyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ViewPharmacyController extends PharmacyBaseController {


    static final Logger logger = LoggerFactory.getLogger(ViewPharmacyController.class);

    @Autowired
    PharmacyService pharmacyService;


    @GetMapping
    public List<Pharmacy> getPharmacies() throws MalformedJsonException {
        return pharmacyService.getStores();
    }

//    @GetMapping(value = BOOKING_ENDPOINT)
//    public ResponseEntity<Booking> getBooking(@PathVariable("id") long id, JwtAuthenticationToken authentication) throws NotFoundException, NotAValidUserException {
//
//
//        logger.info("ENTRY GET /bookings: " + id);
//
//        Booking result = bookingService.findById(id);
//
//        authorisedUser.authenticate(authentication, result.getPatientId());
//
//        ResponseEntity<Booking> response = new ResponseEntity<>(result, HttpStatus.OK);
//
//        logger.info("EXIT GET /bookings: " + result);
//
//        return response;
//    }

}
