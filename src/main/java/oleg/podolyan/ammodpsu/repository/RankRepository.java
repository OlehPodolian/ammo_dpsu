package oleg.podolyan.ammodpsu.repository;

import oleg.podolyan.ammodpsu.domain.libs.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RankRepository extends JpaRepository<Rank, String>{

	@Override
	Rank getOne(String value);

	@Override
	Rank save(Rank rank);

	@Override
	void delete(String value);

	@Query("select r from Rank r order by r.ordinal")
	List<Rank> findAll();
}
