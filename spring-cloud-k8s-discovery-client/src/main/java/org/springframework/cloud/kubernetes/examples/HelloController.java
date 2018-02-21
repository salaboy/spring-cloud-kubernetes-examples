package org.springframework.cloud.kubernetes.examples;

import java.util.List;

import io.fabric8.kubernetes.client.KubernetesClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private static final Log log = LogFactory.getLog(HelloController.class);
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private KubernetesClient client;

    @RequestMapping("/")
    public String hello() {

        List<String> services = this.discoveryClient.getServices();
        for (String s : services) {
            log.info("Service ID: " + s);
            List<ServiceInstance> instances = this.discoveryClient.getInstances(s);
            for (ServiceInstance si : instances) {
                log.info("Service Instance: " + si);
            }
        }

        return "Hello World \n";
    }
}
