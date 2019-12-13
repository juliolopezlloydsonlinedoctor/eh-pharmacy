package com.experthealth.pharmacy.ms.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.experthealth.pharmacy.ms.lang.PharmacyAPIs.PHARMACY_API_BASE_ENDPOINT;
import static com.experthealth.pharmacy.ms.lang.PharmacyAPIs.PRODUCES_APPLICATION_JSON;


@RestController
@RequestMapping(value = PHARMACY_API_BASE_ENDPOINT, produces = PRODUCES_APPLICATION_JSON)
public class PharmacyBaseController {

}
