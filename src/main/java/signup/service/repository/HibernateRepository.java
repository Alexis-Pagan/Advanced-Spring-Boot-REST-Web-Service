package signup.service.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import signup.service.models.User;

@Repository
public interface HibernateRepository extends CrudRepository<User, Integer> {

}
