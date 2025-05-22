package com.auditxtoolkit.auditxtoolkit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class AuditxtoolkitApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
		dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));

		String[] requiredVars = { "DATABASE_URL", "DATABASE_USERNAME", "DATABASE_PASSWORD" };
		for (String var : requiredVars) {
			if (System.getProperty(var) == null || System.getProperty(var).isEmpty()) {
				System.err.println("ERROR: Missing required environment variable: " + var);
				System.exit(1);
			}
		}

		SpringApplication.run(AuditxtoolkitApplication.class, args);

		final String CYAN = "\u001B[36m";
		final String RESET = "\u001B[0m";
		System.out.println(
				CYAN +
						"\n" +
						"   #                          #     #    #######                                     \n" +
						"  # #   #    # #####  # #####  #   #        #     ####   ####  #      #    # # ##### \n" +
						" #   #  #    # #    # #   #     # #         #    #    # #    # #      #   #  #   #   \n" +
						"#     # #    # #    # #   #      #          #    #    # #    # #      ####   #   #   \n" +
						"####### #    # #    # #   #     # #         #    #    # #    # #      #  #   #   #   \n" +
						"#     # #    # #    # #   #    #   #        #    #    # #    # #      #   #  #   #   \n" +
						"#     #  ####  #####  #   #   #     #       #     ####   ####  ###### #    # #   #   \n" +
						"                                                                                     \n" +
						"                                                                                     \n" +
						"                                   By: Einartech\n" +
						RESET);
	}

}
