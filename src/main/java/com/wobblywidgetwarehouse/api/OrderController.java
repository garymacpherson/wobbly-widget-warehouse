package com.wobblywidgetwarehouse.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wobblywidgetwarehouse.api.contracts.Widget;
import com.wobblywidgetwarehouse.api.contracts.WidgetOrderRequest;
import com.wobblywidgetwarehouse.api.contracts.WidgetType;
import com.wobblywidgetwarehouse.api.contracts.WidgetUpdateRequest;

import org.apache.logging.log4j.Logger;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.springframework.ui.Model;

@RestController()
@RequestMapping("/order")
public class OrderController {
    static Logger logger = LogManager.getLogger(OrderController.class);
	private static ObjectMapper objectMapper = new ObjectMapper()
		.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);


	@GetMapping("/widget")
	public String getWidget(Model model) {
		model.addAttribute("request", new WidgetOrderRequest());

		Widget widget = new Widget(WidgetType.LARGE);
		try {
			String widgetAsString = objectMapper.writeValueAsString(widget);

			return widgetAsString;
		} catch (JsonProcessingException e) {
			logger.error(e);
		}

		return "Unable to get widget";
	}

	@PostMapping("/widget")
	public String createWidget(@ModelAttribute WidgetOrderRequest request, Model model) {
		model.addAttribute("request", request);

		logger.info("new widget order received");
		logger.info(request.getType());
		logger.info(request.getSpecialRequest());

		return "widget order received";
	}

	@PutMapping("/widget")
	public String updateWidget(@ModelAttribute WidgetUpdateRequest request, Model model) {
		try {
			Widget widget = objectMapper.readValue(request.getSerialisedObject(), Widget.class);
			logger.info(objectMapper.writeValueAsString(widget));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "updated!";
	}
}