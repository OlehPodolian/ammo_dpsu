package oleg.podolyan.ammodpsu.repository;

import oleg.podolyan.ammodpsu.domain.libs.ClothesType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClothesTypeRepository extends JpaRepository<ClothesType, String> {

    @Override
    ClothesType findOne(String value);

    @Override
    ClothesType save(ClothesType clothesType);

    List<ClothesType> findAll();
}
