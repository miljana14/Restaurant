package go4Code.Restaurante.repository;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import go4Code.Restaurante.model.Menu;

@Component
public interface MenuRepository extends JpaRepository<Menu, Long>{
		public List<Menu> findByName(String name);
		public List<Menu> findByNameContaining(String infix);
}
