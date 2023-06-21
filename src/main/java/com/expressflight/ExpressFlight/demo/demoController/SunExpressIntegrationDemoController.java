package com.expressflight.ExpressFlight.demo.demoController;

import com.expressflight.ExpressFlight.demo.demoService.SunExpressIntegrationDemoService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/demo/integrate/SunExpress")
@CrossOrigin(origins = "*")
public class SunExpressIntegrationDemoController {

    SunExpressIntegrationDemoService sunExpressIntegrationDemoService;

    public SunExpressIntegrationDemoController(SunExpressIntegrationDemoService sunExpressIntegrationDemoService) {
        this.sunExpressIntegrationDemoService = sunExpressIntegrationDemoService;
    }

    @GetMapping
    public void integrate() {
        sunExpressIntegrationDemoService.integrate();
    }

}
