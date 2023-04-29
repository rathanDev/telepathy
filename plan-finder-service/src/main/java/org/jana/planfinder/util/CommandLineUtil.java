package org.jana.planfinder.util;

import org.jana.planfinder.data.Plan;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CommandLineUtil {

    public static void findMinPricePlans(String[] args) {
        String filePath = args[0];
        String requirementStr = args[1];

        List<String> lines = FileReaderUtil.readFile(filePath);
        List<Plan> availablePlans = PlanFinderUtil.convertToAvailablePlans(lines);
        List<String> requirements = PlanFinderUtil.convertToRequirements(requirementStr);
        List<Plan> minPricePlans = PlanFinderUtil.findMinPrice(availablePlans, requirements);
        Collections.sort(minPricePlans);
        int minPrice = minPricePlans.stream().map(Plan::getAmount).reduce(0, Integer::sum);
        List<String> planNames = minPricePlans.stream().map(Plan::getName).collect(Collectors.toList());
        System.out.print(minPrice);
        planNames.forEach(p -> System.out.print(", " + p));
    }

}
