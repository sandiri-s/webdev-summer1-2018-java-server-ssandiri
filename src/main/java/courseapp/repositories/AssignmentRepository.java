package courseapp.repositories;

import org.springframework.data.repository.CrudRepository;

import courseapp.models.Assignment;

public interface AssignmentRepository extends CrudRepository<Assignment, Integer> {

}