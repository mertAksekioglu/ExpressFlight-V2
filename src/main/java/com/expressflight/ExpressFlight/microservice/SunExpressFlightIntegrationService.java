package com.expressflight.ExpressFlight.microservice;

import com.expressflight.ExpressFlight.integration.IIntegration;
import com.expressflight.ExpressFlight.integration.SunExpressIntegration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class SunExpressFlightIntegrationService {

    IIntegration sunExpressIntegration;

    public SunExpressFlightIntegrationService(IIntegration sunExpressIntegration) {
        this.sunExpressIntegration = sunExpressIntegration;
    }

    public void integrate() {
        sunExpressIntegration.getData();
        sunExpressIntegration.addData();
    }
}
