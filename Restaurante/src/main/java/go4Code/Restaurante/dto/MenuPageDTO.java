package go4Code.Restaurante.dto;

import java.util.List;

import go4Code.Restaurante.model.Menu;

public class MenuPageDTO {
	List<MenuDTO> content;
	boolean last;
	
	public MenuPageDTO(
			List<MenuDTO> content,
			boolean last
	) {
		this.content = content;
		this.last = last;
	}

	public List<MenuDTO> getContent() {
		return content;
	}

	public void setContent(List<MenuDTO> content) {
		this.content = content;
	}

	public boolean isLast() {
		return last;
	}

	public void setLast(boolean last) {
		this.last = last;
	}
}
