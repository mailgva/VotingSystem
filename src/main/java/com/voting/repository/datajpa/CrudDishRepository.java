package com.voting.repository.datajpa;

import com.voting.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NamedQuery;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudDishRepository extends JpaRepository<Dish, Integer> {

    @Transactional
    Dish save(Dish dish);

    // false if not found
    @Transactional
    @Modifying
    @Query("DELETE FROM Dish d WHERE d.id=:id")
    int delete(@Param("id") int id);

    Dish findById(int id);

    // null if not found
    List<Dish> getByName(@Param("partName") String partName);


    List<Dish> findAll();

    Dish findByNameAndPrice(String name, double price);
}
