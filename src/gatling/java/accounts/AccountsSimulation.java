package videogamedb;

import io.gatling.javaapi.core.OpenInjectionStep;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;

public class AccountsSimulation extends Simulation {
    private static final String ACCOUNTS_URL = System.getProperty("ACCOUNTS_URL", "https://videogamedb.uk/api");
    // RUNTIME PARAMETERS
    private static final int USER_COUNT = Integer.parseInt(System.getProperty("USERS", "5"));
    private static final int RAMP_DURATION = Integer.parseInt(System.getProperty("RAMP_DURATION", "10"));

    // Http Configuration
    private final HttpProtocolBuilder httpConfig;

    {
        httpConfig = http
                .baseUrl(ACCOUNTS_URL)
                .acceptHeader("application/json")
                .contentTypeHeader("application/json");
    }

    // Define authentication scenario
    public static class AuthenticationScenario {
        public static ScenarioBuilder build() {
            return scenario("Authentication")
                    .exec(
                            http("Authentication Request")
                                    .post("/login")
                                    .formParam("username", "yourUsername")
                                    .formParam("password", "yourPassword")
                                    .check(jsonPath("$.token").saveAs("jwt"))
                    );
        }
    }

    // Define load testing scenario for getAllAccounts
    public static class GetAllAccountsScenario {
        public static ScenarioBuilder build() {
            return scenario("Get All Accounts")
                    .exec(
                            http("Get All Accounts Request")
                                    .get("/accounts")
                                    .header("Authorization", "Bearer ${jwt}")
                    );
        }
    }

    // Set up injection profile for authentication scenario
    private static final OpenInjectionStep authenticationInjectionProfile =
            rampUsers(1).during(Duration.ofSeconds(1));

    // Set up injection profile for load testing scenario
    private static final OpenInjectionStep loadTestingInjectionProfile =
            rampUsers(100).during(Duration.ofSeconds(10));

    // Load Simulation
    public void run() {
        setUp(
                AuthenticationScenario.build().injectOpen(authenticationInjectionProfile),
                GetAllAccountsScenario.build().injectOpen(loadTestingInjectionProfile)
        ).protocols(httpConfig);
    }

    public static void main(String[] args) {
        AccountsSimulation simulation = new AccountsSimulation();
        simulation.run();
    }
}
