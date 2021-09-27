package go4Code.Restaurante.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import go4Code.Restaurante.model.Menu;
import go4Code.Restaurante.repository.MenuRepository;

@Component
public class MenuService {
	@Autowired
	MenuRepository menuRepository;
	
	public List<Menu> findAll() {
		return menuRepository.findAll();
	}
	
	public Page<Menu> findAll(Pageable page) {
		return menuRepository.findAll(page);
	}

	public Optional<Menu> findOne(Long id) {
		return menuRepository.findById(id);
	}

	public Menu save(Menu menu) {
		return menuRepository.save(menu);
	}

	public void remove(Menu menu) {
		 menuRepository.delete(menu);
	}

	public List<Menu> findByName(String name) {
		return menuRepository.findByName(name);
	}
	
	public List<Menu> findByNameContainig(String infix) {
		return menuRepository.findByNameContaining(infix);
	}
}
