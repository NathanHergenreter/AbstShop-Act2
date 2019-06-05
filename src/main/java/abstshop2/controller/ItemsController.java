package abstshop2.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import abstshop2.entity.Item;
import abstshop2.service.ItemService;

@Controller
@RequestMapping(value="/items")
public class ItemsController {

	@Autowired
	ItemService service;
	
	@GetMapping("/display")
	public String displayAvailable(Model model) {
		model.addAttribute("items", available());
		return "items-display";
	}
	
    @GetMapping("/available")
    @ResponseBody
    public List<Item> available() {
      return service.findAll().stream().filter(this::noBuyer).collect(Collectors.toList());
    }
    
    private boolean noBuyer(Item item) { return item.getBuyer() == null; }
}
