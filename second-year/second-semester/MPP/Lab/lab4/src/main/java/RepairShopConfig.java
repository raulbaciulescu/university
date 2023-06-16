import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import repository.ComputerRepairRequestRepository;
import repository.ComputerRepairedFormRepository;
import repository.jdbc.ComputerRepairRequestJdbcRepository;
import repository.jdbc.ComputerRepairedFormJdbcRepository;
import services.ComputerRepairServices;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@Configuration
public class RepairShopConfig {
    @Bean
    Properties getProps() {
        Properties properties = new Properties();
        try {
            System.out.println("da");
            properties.load(new FileReader("bd.config"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    @Bean
    ComputerRepairRequestRepository requestsRepo(){
       return new ComputerRepairRequestJdbcRepository(getProps());

    }

    @Bean
    ComputerRepairedFormRepository formsRepo(){
       return new ComputerRepairedFormJdbcRepository(getProps());

    }

    @Bean
    ComputerRepairServices services(){
       return new ComputerRepairServices(requestsRepo(), formsRepo());

    }

}
