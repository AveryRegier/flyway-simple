package com.github.averyregier.flyway.simple;

import org.flywaydb.core.Flyway;

import java.util.Arrays;
import java.util.Properties;

/**
 * Created by avery on 2/10/15.
 */
public class Main {
    private FlywayCommand command = null;
    private boolean debug = false;
    private Properties properties = new Properties();

    public static void main(String... args) {
        new Main().build(args).execute();
    }

    private Main build(String[] args) {
        for(String arg: args) {
            if("-x".equalsIgnoreCase(arg) || "-debug".equalsIgnoreCase(arg)) {
                debug = true;
            } else if(arg.matches("-.+?=.*")) {
                int loc = arg.indexOf('=');
                if(loc > 0) {
                    String key = arg.substring(0, loc).trim().substring(1);
                    String value = arg.substring(loc + 1).trim();
                    properties.put("flyway."+key, value);
                } else {
                    badArgument(arg);
                }
            } else if(command == null) {
                command = FlywayCommand.find(arg);
            } else {
                badArgument(arg);
            }
        }
        return this;
    }

    private void execute() {
        if(debug) {
            properties.list(System.out);
        }

        if(command != null) {
            Flyway flyway = new Flyway();
            flyway.configure(properties);
            command.execute(flyway);
        } else {
            System.out.println("Command required: "+ Arrays.toString(FlywayCommand.values()));
        }
    }

    private static void badArgument(String arg) {
        System.out.println(arg + " has no effect");
    }
}
