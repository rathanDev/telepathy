package org.jana.planfinder.data;

import java.util.Set;

public class Plan implements Comparable<Plan> {

    private String name;
    private int amount;
    private Set<String> features;

    public Plan(String name, int amount, Set<String> features) {
        this.name = name;
        this.amount = amount;
        this.features = features;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Set<String> getFeatures() {
        return features;
    }

    public void setFeatures(Set<String> features) {
        this.features = features;
    }

    @Override
    public int compareTo(Plan o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return "Plan{" +
                "name=" + name +
                ", amount=" + amount +
                ", features=" + features +
                '}';
    }

}