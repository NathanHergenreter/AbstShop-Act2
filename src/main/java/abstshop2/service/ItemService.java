package abstshop2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import abstshop2.entity.Item;
import abstshop2.repository.ItemRepository;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repo;
    
    public void add(Item item) { repo.save(item); }
    
    public boolean hasItems() { return repo.count() > 0; }
    
    public List<Item> findAll() { return repo.findAll(); }
}
