package org.jana.planfinder.util;

import org.jana.planfinder.data.Plan;

import java.util.*;

public class PlanFinderUtil {

    public static List<Plan> convertToAvailablePlans(List<String> lines) {
        List<Plan> availablePlans = new ArrayList<>();
        for (String line : lines) {
            String[] splits = line.split(",");

            String planName = splits[0].trim();
            int amount = Integer.parseInt(splits[1].trim());

            Set<String> features = new HashSet<>();
            for (int i = 2; i < splits.length; i++) {
                features.add(splits[i].trim());
            }

            Plan plan = new Plan(planName, amount, features);
            availablePlans.add(plan);
        }
        return availablePlans;
    }

    public static List<String> convertToRequirements(String requirementStr) {
        List<String> requirements = new ArrayList<>();
        String[] splits = requirementStr.split(",");
        for (String split : splits) {
            requirements.add(split.trim());
        }
        return requirements;
    }

    public static List<Plan> findMinPrice(List<Plan> plans, List<String> requirements) {
        plans = addPlan0(plans);

        List<Set<String>> reqCombinations = generateAllPossibleCombinations(requirements);

        int[][] dp = new int[plans.size()][reqCombinations.size()];
        boolean[][] selections = new boolean[plans.size()][reqCombinations.size()];

        for (int i = 0; i < dp.length; i++) {
            int[] ar = dp[i];
            Arrays.fill(ar, Integer.MAX_VALUE);
            dp[i] = ar;
        }

        for (int i = 0; i < plans.size(); i++) {
            Plan currentPlan = plans.get(i);
            for (int j = 0; j < reqCombinations.size(); j++) {
                Set<String> currentReqs = reqCombinations.get(j);
                if (i == 0 || j == 0) {
                    if (j == 0) dp[i][j] = 0;
                    //if i==0 leave max
                } else if (hasChanceToInclude(currentPlan.getFeatures(), reqCombinations.get(j))) {
                    int includedAmount = findIncludedAmount(currentPlan, currentReqs, dp, i, reqCombinations);
                    int excludedAmount = dp[i - 1][j];
                    if (includedAmount < excludedAmount) {
                        dp[i][j] = includedAmount;
                        selections[i][j] = true;
                    } else {
                        dp[i][j] = excludedAmount;
                    }
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        List<Plan> selectedPlans = new ArrayList<>();
        int currentIndex = reqCombinations.size() - 1;
        Set<String> currentReqs = reqCombinations.get(reqCombinations.size() - 1);
        for (int i = plans.size() - 1; i > 0 && currentIndex > 0; i--) {
            Plan currentPlan = plans.get(i);
            if (selections[i][currentIndex]) {
                selectedPlans.add(currentPlan);
                currentReqs = findRemainingReqs(currentPlan, currentReqs);
                currentIndex = findIndexOfReqComb(reqCombinations, currentReqs);
            }
        }

        return selectedPlans;
    }

    public static List<Plan> addPlan0(List<Plan> plans) {
        List<Plan> modified = new ArrayList<>();
        modified.add(new Plan("PLAN__0", 0, new HashSet<>()));
        modified.addAll(plans);
        return modified;
    }

    public static List<Set<String>> generateAllPossibleCombinations(List<String> input) {
        List<List<String>> result = new ArrayList<>();
        generateAllPossibleCombinationsRec(result, new ArrayList<>(), input, 0);

        List<Set<String>> sets = new ArrayList<>();
        for (int i = 0; i <= input.size(); i++) {
            for (List<String> list : result) {
                if (list.size() == i) {
                    sets.add(new HashSet<>(list));
                }
            }
        }

        return sets;
    }

    private static void generateAllPossibleCombinationsRec(List<List<String>> result, List<String> tempList, List<String> input, int start) {
        result.add(new ArrayList<>(tempList));
        for (int i = start; i < input.size(); i++) {
            tempList.add(input.get(i));
            generateAllPossibleCombinationsRec(result, tempList, input, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    private static boolean hasChanceToInclude(Set<String> features, Set<String> reqs) {
        for (String req : reqs) {
            if (features.contains(req)) {
                return true;
            }
        }
        return false;
    }

    private static int findIncludedAmount(Plan currentPlan, Set<String> reqs, int[][] dp, int i, List<Set<String>> reqCombinations) {
        // int currentPlanAmount = currentPlan.getAmount();

        Set<String> remainingReqs = findRemainingReqs(currentPlan, reqs);

        int remainingAmount = 0;
        if (!remainingReqs.isEmpty()) {
            int index = findIndexOfReqComb(reqCombinations, remainingReqs);
            remainingAmount = dp[i - 1][index];
        }

        return remainingAmount == Integer.MAX_VALUE ? remainingAmount : currentPlan.getAmount() + remainingAmount;
    }

    private static Set<String> findRemainingReqs(Plan currentPlan, Set<String> reqs) {
        Set<String> remainingReqs = new HashSet<>();
        for (String req : reqs) {
            if (!currentPlan.getFeatures().contains(req)) {
                remainingReqs.add(req);
            }
        }
        return remainingReqs;
    }

    private static int findIndexOfReqComb(List<Set<String>> reqCombinations, Set<String> remainingReqs) {
        int index = -1;
        for (int k = 0; k < reqCombinations.size(); k++) {
            Set<String> reqCombination = reqCombinations.get(k);
            HashSet<String> reqCombSet = new HashSet<>(reqCombination);
            HashSet<String> remainingReqSet = new HashSet<>(remainingReqs);
            if (reqCombSet.equals(remainingReqSet)) {
                index = k;
                break;
            }
        }
        return index;
    }


}

