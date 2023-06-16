package exam.persistence;

import exam.model.Configuration;

public interface IConfigurationRepository extends Repository<Integer, Configuration> {
    Configuration getRandomConfig();
}
