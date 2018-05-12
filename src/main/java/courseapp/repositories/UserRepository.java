package courseapp.repositories;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import courseapp.models.User;
public interface UserRepository extends CrudRepository<User, Integer> {}