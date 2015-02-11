package com.github.averyregier.flyway.simple;

import org.flywaydb.core.Flyway;

/**
 * Created by avery on 2/10/15.
 */
public enum FlywayCommand {
    migrate {
        @Override
        public void execute(Flyway flyway) {
            flyway.migrate();
        }
    },
    repair {
        @Override
        public void execute(Flyway flyway) {
            flyway.repair();
        }
    },
    baseline {
        @Override
        public void execute(Flyway flyway) {
            flyway.baseline();
        }
    },
    clean {
        @Override
        public void execute(Flyway flyway) {
            flyway.clean();
        }
    },
    validate {
        @Override
        public void execute(Flyway flyway) {
            flyway.validate();
        }
    };

    public static FlywayCommand find(String arg) {
        for(FlywayCommand aCommand: values()) {
            if(aCommand.name().equalsIgnoreCase(arg.trim())) {
                 return aCommand;
            }
        }
        return null;
    }

    public abstract void execute(Flyway flyway);
}
