package com.msb.stock.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class StockController {

    @GetMapping("/stock/reduce/{produceId}")
    public String reduce(@PathVariable Integer produceId){
        try {
            Thread.sleep(3*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("减库存成功");
        return "减库存成功";
    }
}
