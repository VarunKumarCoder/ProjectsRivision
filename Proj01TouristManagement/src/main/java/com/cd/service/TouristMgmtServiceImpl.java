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
	
	

}
