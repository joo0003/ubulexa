package es.ubu.ubulexa.skill.requestinterceptors.steps;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;

public interface RequestInterceptorStep {

  void run(HandlerInput handlerInput);
}
