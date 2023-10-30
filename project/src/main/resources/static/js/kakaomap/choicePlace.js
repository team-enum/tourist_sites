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
const uri = location.pathname + "/content";
const contents = new Map();

// 여행 계획 내용 불러오기
window.onpageshow = () => {
   $.ajax({
      type: "GET",
      url: uri,
      contentType: "application/json; charset=utf-8",
      success: function(resp) {
         console.table(resp);
         for(let i = 0, l = resp.length; i < l; i++){
            createPlace(resp[i].place.id, resp[i]);
         }
      },
      error: function(xhr, status, error) {
         console.log(status, error);
         alert("불러오기 실패!");
      }
   })
};

// 내용 삭제
const deleteContent = (id, placeId, el) => {
   $.ajax({
      type: "DELETE",
      url: uri + "/" + id,
      contentType: "application/json; charset=utf-8",
      success: function(resp) {
         console.table(resp);
         if(resp){
            el.empty();
            contents.delete(placeId);
         }
      },
      error: function(xhr, status, error) {
         console.log(status, error);
         alert("삭제 실패!");
      }
   })
   return false;
};

// 내용 수정
const updateContent = (e, id) => {
   $.ajax({
      type: "POST",
      url: uri + "/" + id,
      data: {
         memo : e.target.memo.value,
         date : e.target.date.value,
      },
      success: function(resp) {
         console.table(resp);
         alert("메모 작성 완료");
      },
      error: function(xhr, status, error) {
         console.log(status, error);
         alert("수정 실패!");
      }
   })
   return false;
};

// 중복 선택 확인
const choicePlaceCheck = (id)=> {return contents.get(id) !== undefined};

// 장소가 선택될 경우
const choicePlace = (place)=> {
   const placeId = parseInt(place.id);

   if(choicePlaceCheck(placeId)) {
      alert('이미 선택한 항목 입니다.');
      return;
   }
   
   let data = {
      memo : null,
      date : null,
      place : {
         id : placeId,
         name : place.place_name,
         address : place.address_name,
         road_address : place.road_address_name,
         url : place.place_url,
         phone : place.phone,
         x : parseFloat(place.x),
         y : parseFloat(place.y),
      },
   }

   $.ajax({
      type: "POST",
      url: uri,
      data: JSON.stringify(data),
      contentType: "application/json; charset=utf-8",
      success: function(resp) {
         console.table(resp);
         createPlace(resp.place.id, resp);
      },
      error: function(xhr, status, error) {
         console.log(status, error);
         alert("여행지 추가 실패!");
      }
   })
};

// 선택한 장소 생성
const createPlace = (placeId, data) => {
   contents.set(placeId, data);
   const el = displayChoicePlace(placeId, data);
   const listEl = $("#choice_list");
   listEl.append(el);
};

const displayChoicePlace = (placeId, data) => {
   const el = $("<li>");
   el.addClass("list-group-item");

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

   let formEl = $("<form>");
   formEl.addClass("form-group");
   el.append(formEl);

   let dateLabelEl = $("<label>");
   dateLabelEl.text("날짜");
   dateLabelEl.attr('for', 'date' + placeId);
   formEl.append(dateLabelEl);

   let dateEl = $("<input>");
   dateEl.addClass("form-control");
   dateEl.attr({
      id : 'date' + placeId,
      name : 'date',
   });
   dateEl.daterangepicker();
   dateEl.val(data.date);
   formEl.append(dateEl);

   let memoLabelEl = $("<label>");
   memoLabelEl.text("메모");
   memoLabelEl.attr('for', 'memo' + placeId);
   formEl.append(memoLabelEl);

   let memoEl = $("<textarea>");
   memoEl.addClass("form-control");
   memoEl.attr({
      rows : 3,
      id : 'memo' + placeId,
      name : 'memo'
   });
   memoEl.val(data.memo);
   formEl.append(memoEl);

   let submitBtn = $("<button>");
   submitBtn.addClass("btn btn-info");
   submitBtn.text("저장");
   submitBtn.attr('type', 'submit');
   formEl.append(submitBtn);
   formEl.on("submit", (e)=>{
      return updateContent(e, data.id);
   });

   let deleteBtn = $("<button>");
   deleteBtn.addClass("btn btn-warning");
   deleteBtn.text("삭제");
   formEl.append(deleteBtn);
   deleteBtn.on("click", ()=>{
      return deleteContent(data.id, placeId, el);
   });

   return el;
};