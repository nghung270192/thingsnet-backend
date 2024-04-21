package org.thingsnet.transportmicroservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transport")
public class TransportController {


    @GetMapping
    public String getTransport() {
        return "Hello. I am transport micro service";
    }
}
