package com.cd.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cd.entity.Tourist;
import com.cd.repository.ITouristRepo;

@Service("touristService")
public class TouristMgmtServiceImpl implements ITouristMgmtService {

	private ITouristRepo repo;
	
	public TouristMgmtServiceImpl(ITouristRepo repo) {
		super();
		this.repo = repo;
	}
	@Override
	public String registerTourost(Tourist tourist) {
		int idVal=repo.save(tourist).getId();
		return "Tourist is Registered having the IDValue :: "+idVal;
	}
	@Override
	public List<Tourist> fetchAllTourists() {
		List<Tourist> list=repo.findAll();
		System.out.println(list);
		return list;
	}
	@Override
	public List<Tourist> getTouristNameByCity(String city1, String city2, String city3) {
		List<Tourist> list=repo.findTouristsByCityName(city1,city2,city3);
		return list;
	}
	@Override
	public Optional<Tourist> fetchToouristById(Integer id) {
		Optional<Tourist> tourist=repo.findById(id);
		return tourist;
	}
	@Override
	public Iterable<Tourist> fetchAllByIds(Iterable<Integer> ids) {
		List<Tourist> list=repo.findAllById(ids);
		return list;
	}
	@Override
	public String updateTouristDetails(Tourist tourist) throws TouristNotFoundException {
		Optional<Tourist> name=repo.findById(tourist.getId());
		if(name.isPresent()) {
			repo.save(tourist);
			return tourist.getId()+"Tourist is Updated";
		}
		else {
			throw new TouristNotFoundException(tourist.getId()+"tourist Not Found");
		}
		
	}
	@Override
	public List<Tourist> getTouritByName(String name) {
		List<Tourist> Tname=repo.getTouristByName(name);
		return Tname;
	}
	@Override
	public String updateTouristBudgetById(int tid, double hikePercentage) throws TouristNotFoundException {
		Optional<Tourist> opt=repo.findById(tid);
		if(opt.isPresent()) {
			Tourist tourist=opt.get();//get entire row data of tourist
			double budget=tourist.getBudget();//get budget data
			double newBudget=budget+(budget*hikePercentage/100.0);//formula for new budget
			tourist.setBudget(newBudget);//set new budget data to existing budget
			repo.save(tourist);
			return " Tourist Budget is changed according to the new hikePercentage"+newBudget;
		}
		else {
			throw new TouristNotFoundException(tid+" Tourist was not Found");
		}
		
	}
	@Override
	public String removeTouristById(int id) throws TouristNotFoundException {
		Optional<Tourist> opt=repo.findById(id);
		if(opt.isPresent()) {
			repo.deleteById(id);
			return id+" Number Tourist is deleted";
		}
		else {
			throw new TouristNotFoundException(id+" Number Tourist is not Found");
		}
	}
	@Override
	public String removeTouristByBudgetRange(double start, double end) {
		int count=repo.removeTouristByBudgetRange(start, end);
		return count==0?"Tourist Not Found to Dleete":count+" No.of Tourists got deleted";
	}
	
	

}
