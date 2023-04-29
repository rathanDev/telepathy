package org.jana.planfinder.data;

import java.util.List;

public class PlanRes {

    private List<String> planNames;
    private int amount;

    public PlanRes(List<String> planNames, int amount) {
        this.planNames = planNames;
        this.amount = amount;
    }

    public List<String> getPlanNames() {
        return planNames;
    }

    public int getAmount() {
        return amount;
    }

}
