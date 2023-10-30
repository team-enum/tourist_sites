let infowindow = new kakao.maps.InfoWindow({zIndex:1});
let markers = [];

// 마커를 생성하고 지도 위에 마커를 표시하는 함수
const addMarker = (position, idx) => {
   let imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
      imageSize = new kakao.maps.Size(36, 37),  // 마커 이미지의 크기
      imgOptions =  {
         spriteSize : new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
         spriteOrigin : new kakao.maps.Point(0, (idx*46)+10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
         offset: new kakao.maps.Point(13, 37) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
      },
      markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
         marker = new kakao.maps.Marker({
         position: position, // 마커의 위치
         image: markerImage 
      });

   marker.setMap(kakaoMap); // 지도 위에 마커를 표출합니다
   markers.push(marker);  // 배열에 생성된 마커를 추가합니다

   return marker;
}

// 마커와 검색결과 항목에 mouseover 했을때
// 해당 장소에 인포윈도우에 장소명을 표시합니다
// mouseout 했을 때는 인포윈도우를 닫습니다
const addMarkerEvent = (marker, title, itemEl) => {
   kakao.maps.event.addListener(marker, 'mouseover', function() {
      displayInfowindow(marker, title);
   });

   kakao.maps.event.addListener(marker, 'mouseout', function() {
      infowindow.close();
   });

   itemEl.onmouseover =  function () {
      displayInfowindow(marker, title);
   };

   itemEl.onmouseout =  function () {
      infowindow.close();
   };
};

// 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
// 인포윈도우에 장소명을 표시합니다
function displayInfowindow(marker, title) {
   let content = '<div style="padding:5px;z-index:1;">' + title + '</div>';

   infowindow.setContent(content);
   infowindow.open(kakaoMap, marker);
}

// 지도 위에 표시되고 있는 마커를 모두 제거합니다
function removeMarker() {
   for ( let i = 0; i < markers.length; i++ ) {
      markers[i].setMap(null);
   }   
   markers = [];
}