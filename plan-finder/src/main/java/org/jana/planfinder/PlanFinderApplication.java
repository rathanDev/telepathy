package org.jana.planfinder;

import org.jana.planfinder.util.CommandLineUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PlanFinderApplication {

    public static void main(String[] args) {
        if (args.length == 2) {
            handleCommandLineArgs(args);
            return;
        }
        SpringApplication.run(PlanFinderApplication.class, args);
    }

    private static void handleCommandLineArgs(String[] args) {
        CommandLineUtil.findMinPricePlans(args);
    }

}
