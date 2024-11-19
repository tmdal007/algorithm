<!-- TripMap.vue -->
<script setup>
import { ref, onMounted } from 'vue';
import { KakaoMap, KakaoMapMarker } from 'vue3-kakao-maps';

const serviceKey = '7wYN12VZpr%2FiyQ38IXqKf6Qiasy920D0wCanrMephf05Xb85AgQvp6BICarbK2KuWMLMvAzyBd2YAvl2z3MHNw%3D%3D';
const areas = ref([]);
const trips = ref([]);
const selectedArea = ref('0');
const selectedContentId = ref('0');
const searchKeyword = ref('');
const positions = ref([]);
const currentOverlay = ref(null);
const mapCenter = ref({ lat: 37.500613, lng: 127.036431 });
const selectedTypeId = ref(null);

// 지역 정보 가져오기
const fetchAreas = async () => {
  const areaUrl = `https://apis.data.go.kr/B551011/KorService1/areaCode1?serviceKey=${serviceKey}&numOfRows=20&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json`;
  try {
    const response = await fetch(areaUrl);
    const data = await response.json();
    areas.value = data.response.body.items.item;
  } catch (error) {
    console.error('Error fetching areas:', error);
  }
};

// 관광지 검색
const searchTrips = async () => {
  const baseUrl = 'https://apis.data.go.kr/B551011/KorService1/';
  let queryString = `serviceKey=${serviceKey}&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A`;
  
  if (selectedArea.value !== '0') queryString += `&areaCode=${selectedArea.value}`;
  if (selectedContentId.value !== '0') queryString += `&contentTypeId=${selectedContentId.value}`;
  
  const service = searchKeyword.value ? 'searchKeyword1' : 'areaBasedList1';
  if (searchKeyword.value) queryString += `&keyword=${searchKeyword.value}`;
  
  const searchUrl = baseUrl + service + "?" + queryString;
  
  try {
    const response = await fetch(searchUrl);
    const data = await response.json();
    updateTripList(data);
  } catch (error) {
    console.error('Error searching trips:', error);
  }
};

// 마커 이미지 가져오기
const getMarkerImageByType = (typeId) => {
  const markerImages = {
    '12': 'https://ifh.cc/g/nwVDC8.png',
    '14': 'https://ifh.cc/g/0hN30d.png',
    '15': 'https://ifh.cc/g/5awcA3.png',
    '28': 'https://ifh.cc/g/TK3bY2.png',
    '32': 'https://ifh.cc/g/xxptCo.png',
    '38': 'https://ifh.cc/g/0hBO1t.png',
    '39': 'https://ifh.cc/g/3ZqfBV.png'
  };
  return markerImages[typeId] || 'https://ifh.cc/g/Sv0ALb.png';
};

// 여행지 목록 업데이트
const updateTripList = (data) => {
  if (!data.response?.body?.items?.item) return;
  
  trips.value = data.response.body.items.item;
  positions.value = trips.value.map(area => ({
    contentid: area.contentid,
    title: area.title,
    lat: area.mapy,
    lng: area.mapx,
    type: area.contenttypeid,
    firstimage: area.firstimage,
    addr1: area.addr1,
    addr2: area.addr2,
    homepage: area.homepage
  }));
};

// 지도 중심 이동
const moveCenter = (lat, lng) => {
  mapCenter.value = { lat, lng };
};

// 찜하기 기능
const addToFavorites = async (trip) => {
  try {
    const response = await fetch('/board/like?action=insert', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        title: trip.title,
        image1: trip.firstimage,
        addr1: trip.addr1,
        latitude: trip.lat,
        longitude: trip.lng
      })
    });
    
    if (response.ok) {
      alert('찜한 목록에 추가되었습니다.');
    } else {
      alert('찜하기에 실패했습니다.');
    }
  } catch (error) {
    alert('서버와 통신 중 오류가 발생했습니다.');
  }
};

onMounted(() => {
  fetchAreas();
});
</script>

<template>
  <div class="container">
    <div class="col-10 ms-4">
      <div class="title mt-3 text-center fw-bold" role="alert">
        여행지 검색
      </div>
      
      <!-- 검색 폼 -->
      <form class="d-flex my-3" @submit.prevent="searchTrips">
        <select v-model="selectedArea" class="form-select me-2">
          <option value="0">광역시, 도</option>
          <option v-for="area in areas" :key="area.code" :value="area.code">
            {{ area.name }}
          </option>
        </select>
        
        <select v-model="selectedContentId" class="form-select me-2">
          <option value="0">관광지 유형</option>
          <option value="12">관광지</option>
          <option value="14">문화시설</option>
          <option value="15">축제공연행사</option>
          <option value="25">여행코스</option>
          <option value="28">레포츠</option>
          <option value="32">숙박</option>
          <option value="38">쇼핑</option>
          <option value="39">음식점</option>
        </select>
        
        <input v-model="searchKeyword" class="form-control me-2" type="search" placeholder="검색어" />
        <button class="btn btn-outline-success" type="submit">검색</button>
      </form>

      <!-- 카카오 맵 -->
      <KakaoMap 
        :lat="mapCenter.lat" 
        :lng="mapCenter.lng" 
        :draggable="true"
        style="width: 100%; height: 400px"
      >
        <KakaoMapMarker 
          v-for="position in positions"
          :key="position.contentid"
          :lat="position.lat"
          :lng="position.lng"
          :options="{
            image: {
              src: getMarkerImageByType(position.type),
              size: { width: 24, height: 35 }
            }
          }"
          @click="moveCenter(position.lat, position.lng)"
        />
      </KakaoMap>

      <!-- 관광지 목록 -->
      <div class="row mt-3">
        <table class="table table-striped" v-if="trips.length">
          <thead>
            <tr>
              <th>대표이미지</th>
              <th>관광지명</th>
              <th>주소</th>
              <th>찜</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="trip in trips" :key="trip.contentid" @click="moveCenter(trip.mapy, trip.mapx)">
              <td><img :src="trip.firstimage" width="100px" /></td>
              <td>{{ trip.title }}</td>
              <td>{{ trip.addr1 }} {{ trip.addr2 }}</td>
              <td>
                <button class="btn btn-sm btn-primary" @click.stop="addToFavorites(trip)">
                  찜하기
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<style scoped>
.container {
  padding: 20px;
}

.title {
  font-size: 28px;
  font-weight: bold;
  color: #333;
  margin: 40px 0 20px 0;
  padding-bottom: 15px;
  text-align: center;
}

table {
  margin-top: 20px;
}

table img {
  object-fit: cover;
  border-radius: 4px;
}
.btn-outline-success {
  background-color: #75423c;
  border-color: #75423c;
  color: white;
  white-space: nowrap; 
  text-align: center; 
  display: inline-block; 
}

.btn-outline-success:hover {
  background-color: #eee0d1;
  border-color: #eee0d1;
  color: white;
}
.btn-primary {
  background-color: #75423c;
  border-color: #75423c;
  color: white;
  white-space: nowrap; 
  text-align: center; 
  display: inline-block; 
}

.btn-primary:hover {
  background-color: #b4564c;
  border-color: #b4564c;
  color: white;
}

</style>