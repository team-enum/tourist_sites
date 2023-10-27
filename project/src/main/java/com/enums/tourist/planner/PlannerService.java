package com.enums.tourist.planner;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enums.tourist.domain.Planner;
import com.enums.tourist.domain.Memo;
import com.enums.tourist.domain.Places;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlannerService {
	private final PlannerRepository plannerRepository;
	private final MemoRepository memoRepository;
	
	public Optional<Planner> findOne(Long id) {
		return plannerRepository.findById(id);
	}
	
	@Transactional
	public void addMemo(PlannerDTO plannerDTO, Long plannerId) {
		
//		plannerRepository.findById(plannerId);

		Memo memo = new Memo();
		memo.setStartdate(LocalDateTime.now());
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

}
