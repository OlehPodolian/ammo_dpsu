package oleg.podolyan.ammodpsu.repository.military;

import oleg.podolyan.ammodpsu.domain.military.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

	Department findByName(String departmentName);

	List<Department> findAll();
}
