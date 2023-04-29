package org.jana.planfinder.service;

import org.jana.planfinder.data.Plan;
import org.jana.planfinder.data.PlanReq;
import org.jana.planfinder.data.PlanRes;
import org.jana.planfinder.util.PlanFinderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlanService {
    private static final Logger log = LoggerFactory.getLogger(PlanService.class);

    public PlanRes findPlans(PlanReq req) {
        log.info("findPlans req:{}", req);
        List<Plan> minPricePlans = PlanFinderUtil.findMinPrice(req.getAvailablePlans(), req.getRequirements());
        Collections.sort(minPricePlans);
        int minPrice = minPricePlans.stream().map(Plan::getAmount).reduce(0, Integer::sum);
        List<String> planNames = minPricePlans.stream().map(Plan::getName).collect(Collectors.toList());
        log.info("Result minPrice:{} planNames:{}", minPrice, planNames);
        return new PlanRes(planNames, minPrice);
    }

}
