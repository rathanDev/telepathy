package org.jana.planfinder.controller;

import org.jana.planfinder.data.PlanReq;
import org.jana.planfinder.data.PlanRes;
import org.jana.planfinder.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/plan")
public class PlanController {

    @Autowired
    PlanService planService;

    @PostMapping
    public PlanRes findPlans(@RequestBody PlanReq req) {
        return planService.findPlans(req);
    }

}
