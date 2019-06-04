package abstshop2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import abstshop2.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{

	List<Item> findByShape(String shape);
	List<Item> findByColor(String color);
}
