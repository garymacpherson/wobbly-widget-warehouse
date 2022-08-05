package com.wobblywidgetwarehouse.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.ui.Model;
import com.wobblywidgetwarehouse.api.Contracts.WidgetOrderRequest;

@RestController()
@RequestMapping("/order")
public class OrderController {
    static Logger logger = LogManager.getLogger(OrderController.class);

	@GetMapping("/widget")
	public String greetingForm(Model model) {
		model.addAttribute("request", new WidgetOrderRequest());
		return "widget";
	}

	@PostMapping("/widget")
	public String greetingSubmit(@ModelAttribute WidgetOrderRequest request, Model model) {
		model.addAttribute("request", request);

		logger.info("new widget order received");
		logger.info(request.getType());
		logger.info(request.getSpecialRequest());

		return "widget";
	}
}