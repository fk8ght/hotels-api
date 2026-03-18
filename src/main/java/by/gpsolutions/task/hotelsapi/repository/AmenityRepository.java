package by.gpsolutions.task.hotelsapi.repository;

import by.gpsolutions.task.hotelsapi.model.entity.Amenity;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmenityRepository extends JpaRepository<Amenity, Long> {
    List<Amenity> findByNameIn(Collection<String> names);
}

