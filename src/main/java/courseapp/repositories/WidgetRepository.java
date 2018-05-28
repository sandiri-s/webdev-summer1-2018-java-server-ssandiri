package courseapp.repositories;

import org.springframework.data.repository.CrudRepository;

import courseapp.models.Widget;

public interface WidgetRepository extends CrudRepository<Widget, Integer>{

}