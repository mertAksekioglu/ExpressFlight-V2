package com.expressflight.ExpressFlight.microservice;

import com.expressflight.ExpressFlight.integration.SunExpressIntegration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SunExpressFlightIntegrationService {

    @Autowired
    SunExpressIntegration sunExpressIntegration;


    public void integrate() {
        sunExpressIntegration.getData();
        sunExpressIntegration.addData();
    }

}
