package go4Code.Restaurante.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import go4Code.Restaurante.dto.MenuDTO;
import go4Code.Restaurante.dto.MenuPageDTO;
import go4Code.Restaurante.model.Menu;
import go4Code.Restaurante.repository.MenuRepository;
import go4Code.Restaurante.service.MenuService;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/api/menu")
public class MenuController {
	@Autowired
	MenuService menuService;
	
	 @GetMapping( params = {"page", "size"})
	    public ResponseEntity<MenuPageDTO> getAllMenus(Pageable pageable) {
	    	Page<Menu> menus = menuService.findAll(pageable);
	    	MenuPageDTO pageDto = new MenuPageDTO(
	    			dtosFromMenus(menus.getContent()), 
	    			menus.isLast()
	    	);
	    	
	    	return new ResponseEntity<>(pageDto, HttpStatus.OK);
	    }
	 
	 private List<MenuDTO> dtosFromMenus(List<Menu> menus) {
	    	ArrayList<MenuDTO> dtos = new ArrayList<>();
	        for (Menu menu: menus) {
	        	dtos.add(new MenuDTO(menu));
	        }
	        
	        return dtos;
	    }
	    
//	    private List<MenuDTO> dtoListFromMenu(Menu menu) {
//	    	ArrayList<MenuDTO> dtos = new ArrayList<>();
//	    	dtos.add(new MenuDTO(menu));
//	        
//	        return dtos;
//	    }
	
	@GetMapping
	public ResponseEntity<List<Menu>> getAllMenus(){
		List<Menu> menus = menuService.findAll();
		return new ResponseEntity<List<Menu>>(menus, HttpStatus.OK);
	}
	

//    @GetMapping
//    public ResponseEntity<List<MenuDTO>> getMenus(@RequestParam(required = false, defaultValue="") String name) {
//        if (name.isEmpty()) {
//            List<Menu> menus = menuService.findAll();
//            ArrayList<MenuDTO> dtos = new ArrayList<>();
//            for (Menu menu: menus) {
//            	dtos.add(new MenuDTO(menu));
//            }
//            return new ResponseEntity<>(dtos, HttpStatus.OK);
//        }
//
//        Menu menu = menuService.findByName(name);
//        if (menu == null) {
//            return new ResponseEntity(HttpStatus.NOT_FOUND);
//        }
//
//        return new ResponseEntity<>(dtoListFromMenu(menu), HttpStatus.OK);
//    }
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Menu> getById(@PathVariable Long id) {
		Optional<Menu> menu = menuService.findOne(id);
		
		if (menu.isPresent()) {
			return new ResponseEntity<Menu>(menu.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<Menu>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<Menu> create(@RequestBody Menu menu) {
		Menu retVal = menuService.save(menu);

		return new ResponseEntity<Menu>(retVal, HttpStatus.CREATED);
	}
	

	@PutMapping(path = "/{id}")
	public ResponseEntity<Menu> update(@PathVariable Long id,
			@RequestBody Menu menu) {
		if (menuService.findOne(id).isEmpty()) {
			return new ResponseEntity<Menu>(HttpStatus.NOT_FOUND);
		}
		menu.setId(id);
		Menu retVal = menuService.save(menu);

		return new ResponseEntity<Menu>(retVal, HttpStatus.OK);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		Optional<Menu> menu = menuService.findOne(id);
		if (menu.isPresent()) {
			menuService.remove(menu.get());
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(params = "name")
	public ResponseEntity<List<Menu>> getByName(@RequestParam String name){
		List<Menu> menus = menuService.findByName(name);
		return new ResponseEntity<List<Menu>>(menus, HttpStatus.OK);
	}

	@GetMapping(params = "infix")
	public ResponseEntity<List<Menu>> getByNameContaining(@RequestParam String infix){
		List<Menu> menus = menuService.findByNameContainig(infix);
		return new ResponseEntity<List<Menu>>(menus, HttpStatus.OK);
	}
	
}

