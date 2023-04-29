package org.jana.planfinder.service;

import org.jana.planfinder.data.Plan;
import org.jana.planfinder.data.PlanReq;
import org.jana.planfinder.data.PlanRes;
import org.jana.planfinder.util.PlanFinderUtil;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PlanService {

    public PlanRes findPlans(PlanReq req) {
        System.out.println("req = " + req);
        List<Plan> minPricePlans = PlanFinderUtil.findMinPrice(req.getAvailablePlans(), req.getRequirements());
        Collections.sort(minPricePlans);
        int minPrice = minPricePlans.stream().map(Plan::getAmount).reduce(0, Integer::sum);
        List<String> planNames = minPricePlans.stream().map(Plan::getName).collect(Collectors.toList());
        return new PlanRes(planNames, minPrice);
    }

}
