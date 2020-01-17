package route.data;

import org.springframework.data.repository.CrudRepository;

public interface TripRepository extends CrudRepository<Trip, Short> {

    Trip findFirstByStateOrStateNull(String state);

}
