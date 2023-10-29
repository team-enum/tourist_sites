const placesSearch = function(result, status, pagination) {
   if (status === kakao.maps.services.Status.OK) {
      displayPlaces(result);
      displayPagination(pagination);
   } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
      alert('검색 결과가 존재하지 않습니다.');
      return;
   } else if (status === kakao.maps.services.Status.ERROR) {
      alert('검색 결과 중 오류가 발생했습니다.');
      return;
   }
};

// 검색
function displayPlaces(places){
   const listEl = $("#places_list");
   const bounds = new kakao.maps.LatLngBounds();

   // 검색 결과 목록에 추가된 항목들을 제거합니다
   if(listEl.children().length > 0){
      listEl.empty();
   }
   // 지도에 표시되고 있는 마커를 제거합니다
   removeMarker();

   for(let i = 0; i < places.length; i++){
      const itemEl = createPlaceItem(places[i]);
      listEl.append(itemEl);

      const placePosition = new kakao.maps.LatLng(places[i].y, places[i].x);
      const marker = addMarker(placePosition, i);
      bounds.extend(placePosition);
      addMarkerEvent(marker, places[i].place_name, itemEl);
      
   }

   kakaoMap.setBounds(bounds);
}

// 검색 항목
function createPlaceItem(item){
   const el = $("<li>");
   el.addClass("list-group-item list-group-item-action");
   if(choicePlaceCheck(parseInt(item.id))){
      el.addClass("active");
   }

   let nameEl = $("<h5>");
   nameEl.text(item.place_name);
   nameEl.addClass("mb-1")
   el.append(nameEl);

   let roadAddressEl, addressEl;
   addressEl = $("<span>");
   addressEl.text('🏠' + item.address_name);
   if(item.road_address_name){
      roadAddressEl = $("<span>")
      roadAddressEl.text('🏠' + item.road_address_name);
      el.append(roadAddressEl);
   }
   el.append(addressEl);

   let telEl = $("<span>");
   telEl.text('📞 : ' + item.phone);
   el.append(telEl);

   el.on("click", () => {
      el.addClass("active");
      kakaoMap.setLevel(2, {anchor: new kakao.maps.LatLng(item.y, item.x)});
      return choicePlace(item);
   });
   
   return el;
}

// 페이지
const gotoPagination = (pagination, no) => {
   pagination.gotoPage(no)
};

function displayPagination(pagination){
   const pageEl = $("#places_page");
   if(pageEl.children().length > 0){
      pageEl.empty();
   }

   for(let i=1; i <= pagination.last; i++){
      const noEl = $("<button>");
      noEl.text(i);
      noEl.addClass("btn btn-secondary");

      if(i === pagination.current) {
         noEl.addClass("active");
      } else {
         noEl.on("click", () => {
            return gotoPagination(pagination, i)
         });
      }

      pageEl.append(noEl);
   }
}