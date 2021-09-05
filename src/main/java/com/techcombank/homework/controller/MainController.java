package com.techcombank.homework.controller;

import com.techcombank.homework.exception.InvalidPoolId;
import com.techcombank.homework.model.ACTION;
import com.techcombank.homework.model.AddValuesRequest;
import com.techcombank.homework.model.AddValuesResponse;
import com.techcombank.homework.model.CalculatedQuantileResult;
import com.techcombank.homework.model.QueryPoolRequest;
import com.techcombank.homework.service.PoolService;
import javax.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class MainController {

  @Autowired
  PoolService service;

  @RequestMapping(value = "add", method = RequestMethod.POST)
  @ResponseBody
  AddValuesResponse addValuesToPool(@Valid @RequestBody AddValuesRequest request) {
    ACTION action = service.addToPool(request.getPoolId(), request.getValues());
    AddValuesResponse response = new AddValuesResponse(action);
    log.info("[{}] {} {}", "add", request, response);
    return response;
  }

  @RequestMapping(value = "query", method = RequestMethod.POST)
  @ResponseBody
  CalculatedQuantileResult queryPool(@Valid @RequestBody QueryPoolRequest request)
      throws InvalidPoolId {

    CalculatedQuantileResult response = null;
    response = service.query(request.getPoolId(), request.getPercentile());

    log.info("[{}] {} {}", "query", request, response);
    return response;
  }

  @ExceptionHandler(InvalidPoolId.class)
  protected ResponseEntity<Object> handleEntityNotFound(
      InvalidPoolId ex) {
    return ResponseEntity.badRequest().body(ex.getMessage());
  }
}
