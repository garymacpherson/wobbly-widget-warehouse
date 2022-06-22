package com.wobblywidgetwarehouse.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

@RestController()
@RequestMapping("/health")
public class HealthCheckController {
    static Logger logger = LogManager.getLogger(HealthCheckController.class);

    @GetMapping()
	public String index(HttpServletRequest httpRequest) {
        Map<String, List<String>> headersMap = Collections.list(httpRequest.getHeaderNames())    
            .stream()
            .collect(Collectors.toMap(
                Function.identity(), 
                h -> Collections.list(httpRequest.getHeaders(h))
            ));
        logger.info(headersMap.toString());

		return "Health Check!";
	}
}