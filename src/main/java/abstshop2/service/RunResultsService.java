package abstshop2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import abstshop2.entity.RunResults;
import abstshop2.repository.RunResultsRepository;

@Service
public class RunResultsService {

    @Autowired
    private RunResultsRepository repo;
    
    public void add(RunResults set) { repo.save(set); }
    
    public void update(RunResults set) { repo.save(set); }
    
    public RunResults get(int num) { return repo.findByNum(num); }
    
    public List<RunResults> findAll() { return repo.findAll(); }
    
    public int count() { return (int) repo.count(); }
}
