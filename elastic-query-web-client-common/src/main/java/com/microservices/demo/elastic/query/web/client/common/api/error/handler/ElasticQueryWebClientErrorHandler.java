package com.microservices.demo.elastic.query.web.client.common.api.error.handler;

import com.microservices.demo.elastic.query.web.client.common.model.ElasticQueryWebClientRequestModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ElasticQueryWebClientErrorHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ElasticQueryWebClientErrorHandler.class);

    @ExceptionHandler(IllegalArgumentException.class)
    public String handle(IllegalArgumentException e, Model model){

        LOG.error("Illegal Argument Exception", e);
        model.addAttribute("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
        model.addAttribute("error_description", "Illegal Argument exception!"+e.getMessage());
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handle(Exception e,Model model){
        LOG.error("Internal server error", e);
        model.addAttribute("error",HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        model.addAttribute("error_description", "A server error occurred");
        return "error";
    }

    @ExceptionHandler(RuntimeException.class)
    public String handle(RuntimeException e, Model model){
        LOG.error("Service runtime exception!", e);
        model.addAttribute("elasticQueryWebClientRequestModel", ElasticQueryWebClientRequestModel.builder().build());
        model.addAttribute("error", "Could not get response!" + e.getMessage());
        model.addAttribute("error_description", "Service runtime exception"+ e.getMessage());
        return "home";
    }

    @ExceptionHandler({BindException.class})
    public String handle(BindException e, Model model){
        LOG.error("Method argument validation exception", e);
        Map<String,String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error ->
                errors.put(((FieldError)error).getField(),error.getDefaultMessage()));
        model.addAttribute("elasticQueryWebClientRequestModel", ElasticQueryWebClientRequestModel.builder().build());
        model.addAttribute("error",HttpStatus.BAD_REQUEST.getReasonPhrase());
        model.addAttribute("error_description",errors);
        return "home";
    }
}
