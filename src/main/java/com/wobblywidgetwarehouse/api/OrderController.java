package com.wobblywidgetwarehouse.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import com.wobblywidgetwarehouse.api.Contracts.WidgetOrderRequest;

@RestController()
@RequestMapping("/order")
public class OrderController {

	@PostMapping()
	public String index() {
		return "Greetings from Spring Boot!";
	}

	@PostMapping()
	public String widgetOrder(@ModelAttribute WidgetOrderRequest request, Model model) {
		model.addAttribute("request", request);

		return "result";
	}
}