package com.expressflight.ExpressFlight.demo.demoService;

import com.expressflight.ExpressFlight.microservice.SunExpressFlightIntegrationService;
import org.springframework.stereotype.Service;

@Service
public class SunExpressIntegrationDemoService {

    SunExpressFlightIntegrationService sunExpressFlightIntegrationService;

    public SunExpressIntegrationDemoService(SunExpressFlightIntegrationService sunExpressFlightIntegrationService) {
        this.sunExpressFlightIntegrationService = sunExpressFlightIntegrationService;
    }

    public void integrate() {
        sunExpressFlightIntegrationService.integrate();
    }

}
