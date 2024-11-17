package dev.da0hn.library.management.system;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

class ModularityTests {

    private static final ApplicationModules modules = ApplicationModules.of(Application.class);

    @Test
    void verifyModularStructure() {
        modules.forEach(System.out::println);

        modules.verify();

        new Documenter(modules)
            .writeDocumentation()
            .writeAggregatingDocument()
            .writeModulesAsPlantUml()
            .writeIndividualModulesAsPlantUml();
    }

}
