/*
address_name	'서울 서대문구 홍은동'
category_group_code	'AT4'
category_group_name	'관광명소'
category_name	'여행 > 관광,명소 > 도보여행 > 둘레길 > 북한산둘레길'
distance	''
id	'12544587'
phone	'02-900-8085'
place_name	'북한산둘레길 7구간옛성길'
place_url	'http://place.map.kakao.com/12544587'
road_address_name	''
x	'126.94698432423915'
y	'37.60759496019467'
*/
const contents = new Map();

const choicePlace = (place)=> {
   const placeId = parseInt(place.id);

   if(contents.get(placeId) !== undefined) {
      alert('이미 선택한 항목 입니다.');
      return;
   }
   
   let data = {
      memo : "",
      date : "",
      place : {
         name : place.place_name,
         address : place.address_name,
         road_address : place.road_address_name,
         url : place.place_url,
         phone : place.phone,
         x : parseFloat(place.x),
         y : parseFloat(place.y),
      },
   }
   contents.set(placeId, data);

   const el = displayChoicePlace(placeId, data);
   const listEl = $("#choice_list");
   listEl.append(el);

   console.log(placeId, data, contents.size);
};

const displayChoicePlace = (placeId, data) => {
   const el = $("<li>");
   
   let nameEl = $("<h5>");
   nameEl.text(data.place.name);
   el.append(nameEl);

   let roadAddressEl, addressEl;
   addressEl = $("<span>");
   addressEl.text(data.place.address);
   if(data.road_address_name){
      roadAddressEl = $("<span>")
      roadAddressEl.text(data.place.road_address);
      el.append(roadAddressEl);
   }
   el.append(addressEl);

   let telEl = $("<span>");
   telEl.text(data.place.phone);
   el.append(telEl);

   let dateEl = $("<input>");
   dateEl.daterangepicker();
   el.append(dateEl);

   let memoEl = $("<input>");
   el.append(memoEl);

   return el;
};