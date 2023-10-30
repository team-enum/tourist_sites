console.log("🗺️map.js");

const kakaoMap = new kakao.maps.Map($("#map")[0], {
   center: new kakao.maps.LatLng(33.450701, 126.570667),
   level: 3,
   draggable: true,
   scrollwheel: true
});
kakaoMap.addControl(new kakao.maps.MapTypeControl(), kakao.maps.ControlPosition.TOPRIGHT);
kakaoMap.addControl(new kakao.maps.ZoomControl(), kakao.maps.ControlPosition.RIGHT);

const kakaoPlaces = new kakao.maps.services.Places(map);

$("#search_form").on("submit", (e)=>{
   let keyword = e.target.keyword.value;
   if (!keyword.replace(/^\s+|\s+$/g, '')) {
      alert('키워드를 입력해주세요!');
      return false;
   }
   kakaoPlaces.keywordSearch(keyword, placesSearch);
   return false;
});