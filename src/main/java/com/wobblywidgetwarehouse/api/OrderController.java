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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import org.apache.commons.collections4.*;

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
			byte[] decoded = Base64.getMimeDecoder().decode(request.getSerialisedObject());
			var widget = deserializeFromByteArray(decoded);

			// do something with the widget I guess?
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return "updated!";
	}

    public static byte[] serializeToByteArray(Object object) throws IOException {
        ByteArrayOutputStream serializedObjectOutputContainer = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(serializedObjectOutputContainer);
        objectOutputStream.writeObject(object);
        return serializedObjectOutputContainer.toByteArray();
    }

    public static Object deserializeFromByteArray(byte[] serializedObject) throws IOException, ClassNotFoundException {
        ByteArrayInputStream serializedObjectInputContainer = new ByteArrayInputStream(serializedObject);
        ObjectInputStream objectInputStream = new ObjectInputStream(serializedObjectInputContainer);
        InvocationHandler evilInvocationHandler = (InvocationHandler) objectInputStream.readObject();
        return evilInvocationHandler;
    }
}