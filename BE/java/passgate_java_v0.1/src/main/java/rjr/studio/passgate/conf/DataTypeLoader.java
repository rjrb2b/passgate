package rjr.studio.passgate.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.opencsv.CSVReader;

import rjr.studio.passgate.api.view.User;
import rjr.studio.passgate.api.view.type.TypeApp;
import rjr.studio.passgate.api.view.type.TypeRole;
import rjr.studio.passgate.business.UserBusiness;
import rjr.studio.passgate.business.type.impl.TypeAppBusinessImpl;
import rjr.studio.passgate.business.type.impl.TypeRoleBusinessImpl;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class DataTypeLoader {

	private final TypeAppBusinessImpl typeAppBusinessImpl;
	private final TypeRoleBusinessImpl typeRoleBusinessImpl;
	private final UserBusiness userBusiness;

	@Autowired
	public DataTypeLoader(TypeAppBusinessImpl typeAppBusinessImpl, TypeRoleBusinessImpl typeRoleBusinessImpl,
			UserBusiness userBusiness) {
		this.typeAppBusinessImpl = typeAppBusinessImpl;
		this.typeRoleBusinessImpl = typeRoleBusinessImpl;
		this.userBusiness = userBusiness;
	}

	@Bean
	public CommandLineRunner loadData() {
		return args -> {
			try (

					Reader reader = new InputStreamReader(
							getClass().getClassLoader().getResourceAsStream("DataTypes.csv"), StandardCharsets.UTF_8);
					CSVReader csvReader = new CSVReader(reader)) {

				List<String[]> records = csvReader.readAll();

				for (String[] record : records) {
					if (record.length == 4) {
						String table = record[0].replace("\"", "");
						String code = record[1].replace("\"", "");
						String name = record[2].replace("\"", "");
						String description = record[3].replace("\"", "");

						this.insertIfNotExists(table, code, name, description);
					}
				}
				this.insertUsers();
			} catch (Exception e) {
				System.out.println("Error while loading data from CSV: " + e.getMessage());
			}
		};
	}

	private void insertUsers() throws Exception {

//		Set<TypeRole> roles = new HashSet<>();
//		roles.add(
//				TypeRole.builder().code("ADMIN").name("ROLE_ADMIN").description("Amministratore del sistema").build());
//		roles.add(TypeRole.builder().code("GUEST").name("ROLE_GUEST").description("Utente ospite").build());
//		roles.add(TypeRole.builder().code("SYSTEM").name("ROLE_SYSTEM").description("Sistemista del sistema").build());
//		roles.add(TypeRole.builder().code("USER").name("ROLE_USER").description("Utente generico").build());
//
//		Set<TypeApp> apps = new HashSet<>();
//		apps.add(TypeApp.builder().code("LIB24").name("LIBRARY_2024").description("App per gestire una libreria")
//				.build());
//		apps.add(TypeApp.builder().code("PG24").name("PASSGATE_2024")
//				.description("App per gestire gli utenti e l'accesso alle applicazioni").build());

		Set<TypeRole> roles = typeRoleBusinessImpl.findAll();
		Set<TypeApp> apps = typeAppBusinessImpl.findAll();

		//@formatter:off
		User admin = User.builder()
				.name("ADMIN")
				.surname("ADMIN")
				.username("ADMIN")
				.password("ADMIN")
				.roles(roles)
				.apps(apps)
				.build();
		//@formatter:on
		userBusiness.save(admin);

		//@formatter:off
		User system = User.builder()
				.name("SYSTEM")
				.surname("SYSTEM")
				.username("SYSTEM")
				.password("SYSTEM")
				.roles(Stream.of(typeRoleBusinessImpl.findByCode("SYSTEM")).collect(Collectors.toSet()))
				.apps(apps)
				.build();
		//@formatter:on
		userBusiness.save(system);

		//@formatter:off
		User user = User.builder()
				.name("USER")
				.surname("USER")
				.username("USER")
				.password("USER")
				.roles(Stream.of(typeRoleBusinessImpl.findByCode("USER")).collect(Collectors.toSet()))
				.apps(apps)
				.build();
		//@formatter:on
		userBusiness.save(user);

		//@formatter:off
		User guest = User.builder()
				.name("GUEST")
				.surname("GUEST")
				.username("GUEST")
				.password("GUEST")
				.roles(Stream.of(typeRoleBusinessImpl.findByCode("GUEST")).collect(Collectors.toSet()))
				.apps(apps)
				.build();
		//@formatter:on
		userBusiness.save(guest);

	}

	private void insertIfNotExists(String table, String code, String name, String description) {

		switch (table) {
		case "TypesApp":
			typeAppBusinessImpl.save(TypeApp.builder().code(code).name(name).description(description).build());
			break;
		case "TypesRole":
			typeRoleBusinessImpl.save(TypeRole.builder().code(code).name(name).description(description).build());
			break;
		default:
			System.out.println("ERROR: table " + table + " is not exist!");
		}

	}

}
