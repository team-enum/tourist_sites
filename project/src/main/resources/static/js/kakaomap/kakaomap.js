console.log("🗺️kakaomap.js");

// 🗺️ Map 생성
const container = document.getElementById('map');
const options = {
   center: new kakao.maps.LatLng(33.450701, 126.570667),
   level: 3,
   draggable: true,
   scrollwheel: true
};

const map = new kakao.maps.Map(container, options);
// ＃장소 검색 서비스.
const places = new kakao.maps.services.Places();

// ＃맵 타일, 줌 토글
const mapTypeControl = new kakao.maps.MapTypeControl();
const zoomControl = new kakao.maps.ZoomControl();
map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);
map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

// 검색
