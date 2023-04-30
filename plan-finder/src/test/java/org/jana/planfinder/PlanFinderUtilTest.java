package org.jana.planfinder;

import org.jana.planfinder.data.Plan;
import org.jana.planfinder.util.PlanFinderUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class PlanFinderUtilTest {

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        Plan plan0 = new Plan("PLAN0", 0, new HashSet<>());
        Plan plan1 = new Plan("PLAN1", 100, new HashSet<>(Arrays.asList("voice", "email")));
        Plan plan2 = new Plan("PLAN2", 150, new HashSet<>(Arrays.asList("email", "database", "admin")));
        Plan plan3 = new Plan("PLAN3", 125, new HashSet<>(Arrays.asList("voice", "admin")));
        Plan plan4 = new Plan("PLAN4", 135, new HashSet<>(Arrays.asList("database", "admin")));
        List<Plan> plans = Arrays.asList(plan0, plan1, plan2, plan3, plan4);

        List<String> requirements = Arrays.asList("email","database", "admin", "voice");

        List<Plan> minPricePlans = PlanFinderUtil.findMinPrice(plans, requirements);
        Collections.sort(minPricePlans);
        int minPrice = minPricePlans.stream().map(Plan::getAmount).reduce(0, Integer::sum);
        List<String> planNames = minPricePlans.stream().map(Plan::getName).collect(Collectors.toList());
        System.out.print(minPrice);
        planNames.forEach(p -> System.out.print(", " + p));
        System.out.println();
    }

}
