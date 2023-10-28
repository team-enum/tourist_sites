package com.enums.tourist.planner.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enums.tourist.domain.Memo;
import com.enums.tourist.domain.Planner;
import com.enums.tourist.domain.embedd.Places;
import com.enums.tourist.planner.PlannerDTO;
import com.enums.tourist.planner.repository.MemoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemoService {
   private final MemoRepository memoRepository;

   @Transactional
   public Memo addMemo(PlannerDTO plannerDTO, Planner planner) {
      Memo memo = new Memo();
		memo.setPlanner(planner);
		memo.setStartdate(plannerDTO.getDate());
		memo.setContent(plannerDTO.getMemo());

		Map<String, String> map = plannerDTO.getPlaces();
		Places emPlace = Places.builder()
				.id(Long.parseLong(map.get("id")))
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

      return memo;
   }
   
	public List<Memo> memoList(Planner planner){
		return memoRepository.findByPlanner(planner);
	}
}
