package by.gpsolutions.task.hotelsapi.repository;

import by.gpsolutions.task.hotelsapi.model.entity.Hotel;
import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    @Query("""
            select distinct h
            from Hotel h
            left join h.amenities a
            where (:name is null or lower(h.name) like lower(concat('%', :name, '%')))
              and (:brand is null or lower(h.brand) = lower(:brand))
              and (:city is null or lower(h.address.city) = lower(:city))
              and (:country is null or lower(h.address.country) = lower(:country))
              and (:amenities is null or lower(a.name) in :amenities)
            """)
    Page<Hotel> search(@Param("name") String name, @Param("brand") String brand, @Param("city") String city, @Param("country") String country, @Param("amenities") Collection<String> amenities, Pageable pageable);

    @Query("""
            select h.brand as k, count(h) as v
            from Hotel h
            group by h.brand
            """)
    java.util.List<Object[]> countByBrand();

    @Query("""
            select h.address.city as k, count(h) as v
            from Hotel h
            group by h.address.city
            """)
    java.util.List<Object[]> countByCity();

    @Query("""
            select h.address.country as k, count(h) as v
            from Hotel h
            group by h.address.country
            """)
    java.util.List<Object[]> countByCountry();

    @Query("""
            select a.name as k, count(distinct h.id) as v
            from Hotel h
            join h.amenities a
            group by a.name
            """)
    java.util.List<Object[]> countByAmenity();
}

