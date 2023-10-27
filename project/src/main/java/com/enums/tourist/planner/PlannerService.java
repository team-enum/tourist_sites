package com.enums.tourist.planner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enums.tourist.domain.Planner;

import jakarta.annotation.PostConstruct;

import com.enums.tourist.domain.Member;
import com.enums.tourist.domain.Memo;
import com.enums.tourist.domain.Places;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlannerService {
	private final PlannerRepository plannerRepository;
	private final MemoRepository memoRepository;
	
	@PostConstruct
	public void init(){
		Planner planner = new Planner();
		planner.setTitle("테스트");
		planner.setMemos(new ArrayList<>());
		planner.setCreateDate(LocalDateTime.now());
		plannerRepository.save(planner);
	}

	public Planner getPlanner(Long id){
		Optional<Planner> findPlanner = plannerRepository.findById(id);
		if(!findPlanner.isPresent()){
			throw new NullPointerException("Planner가 존재하지 않음.");
		}
		return findPlanner.get();
	}

	public List<Planner> findByMember(Member member){
		return plannerRepository.findByMember(member);
	}

	@Transactional
	public void save(String title, Member member){
		Planner planner = new Planner();
		planner.setMember(member);
		planner.setTitle(title);
		planner.setCreateDate(LocalDateTime.now());
		plannerRepository.save(planner);
	}

	@Transactional
	public void addMemo(PlannerDTO plannerDTO, Long plannerId) {
		Planner planner = getPlanner(plannerId);
		Memo memo = new Memo();
		
		planner.addMemo(memo);

		memo.setStartdate(plannerDTO.getDate());
		memo.setContent(plannerDTO.getMemo());

		Map<String, String> map = plannerDTO.getPlaces();
		Places emPlace = Places.builder()
				.places_id(Long.parseLong(map.get("id")))
				.place_name(map.get("place_name"))
				.address_name(map.get("address_name"))
				.road_address_name(map.get("road_address_name"))
				.phone(map.get("phone"))
				.place_url(map.get("place_url"))
				.x(Float.parseFloat(map.get("x")))
				.y(Float.parseFloat(map.get("y")))
			.build();
		memo.setPlaces(emPlace);

		memoRepository.save(memo);
	}

	@Transactional
	public List<Memo> memoList(Long plannerId){
		Planner planner = getPlanner(plannerId);
		return memoRepository.findByPlanner(planner);
	}

}
