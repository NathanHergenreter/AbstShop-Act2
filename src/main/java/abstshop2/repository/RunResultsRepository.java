package abstshop2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import abstshop2.entity.RunResults;

public interface RunResultsRepository extends JpaRepository<RunResults, Long> {

	RunResults findByNum(int num);
}
