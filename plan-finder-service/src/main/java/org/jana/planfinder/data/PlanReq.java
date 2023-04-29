package org.jana.planfinder.data;

import java.util.List;

public class PlanReq {

    private List<Plan> availablePlans;
    private List<String> requirements;

    public PlanReq() {
    }

    public PlanReq(List<Plan> availablePlans, List<String> requirements) {
        this.availablePlans = availablePlans;
        this.requirements = requirements;
    }

    public List<Plan> getAvailablePlans() {
        return availablePlans;
    }

    public void setAvailablePlans(List<Plan> availablePlans) {
        this.availablePlans = availablePlans;
    }

    public List<String> getRequirements() {
        return requirements;
    }

    public void setRequirements(List<String> requirements) {
        this.requirements = requirements;
    }

    @Override
    public String toString() {
        return "PlanReq{" +
                "availablePlans=" + availablePlans +
                ", requirements=" + requirements +
                '}';
    }

}
