package go4Code.Restaurante.dto;

import go4Code.Restaurante.model.Menu;

public class MenuDTO {
	private Long id;
	private String name;
	private String category;
	private Double price;
	
	public MenuDTO() {
		super();
	}

	public MenuDTO(Long id, String name, String category, Double price) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.price = price;
	}

	public MenuDTO(Menu menu) {
		this.id = menu.getId();
        this.name = menu.getName();
        this.category = menu.getCategory();
        this.price = menu.getPrice();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "MenuDTO [name=" + name + ", category=" + category + ", price=" + price + "]";
	}
	
	
}
