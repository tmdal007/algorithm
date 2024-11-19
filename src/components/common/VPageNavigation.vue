<script setup>
import { computed } from "vue";

const props = defineProps({ currentPage: Number, totalPage: Number });
const emit = defineEmits(["pageChange"]);

const navigationSize = parseInt(import.meta.env.VITE_ARTICLE_NAVIGATION_SIZE);

const startPage = computed(() => {
  return parseInt((props.currentPage - 1) / navigationSize) * navigationSize + 1;
});

const endPage = computed(() => {
  let lastPage =
    parseInt((props.currentPage - 1) / navigationSize) * navigationSize + navigationSize;
  return props.totalPage < lastPage ? props.totalPage : lastPage;
});

const endRange = computed(() => {
  return parseInt((props.totalPage - 1) / navigationSize) * navigationSize < props.currentPage;
});

// 페이지 변경
function range(start, end) {
  const list = [];
  for (let i = start; i <= end; i++) list.push(i);
  return list;
}

// 페이지 번호 변경
function onPageChange(pg) {
  console.log(pg + "로 이동!!!");
  emit("pageChange", pg);
}

// 이전 페이지 클릭 시 처리
function goPrevPage() {
  // 현재 페이지가 2페이지 이상일 때, 1페이지로 이동
  const prevPage = props.currentPage > 1 ? props.currentPage - 1 : 1;
  onPageChange(prevPage);
}

// 다음 페이지 클릭 시 처리
function goNextPage() {
  // 다음 버튼 클릭 시, 마지막 페이지가 아닌 경우 currentPage를 1 증가시킴
  const nextPage = props.currentPage < props.totalPage ? props.currentPage + 1 : props.totalPage;
  onPageChange(nextPage);
}
</script>

<template>
  <div class="row">
    <ul class="pagination justify-content-center">
      <!-- 최신 페이지 이동 -->
      <li class="page-item">
        <a class="page-link" @click="onPageChange(1)">최신</a>
      </li>
      <!-- 이전 페이지 -->
      <li class="page-item">
        <a class="page-link" @click="goPrevPage">이전</a>
      </li>
      <!-- 페이지 번호 -->
      <template v-for="pg in range(startPage, endPage)" :key="pg">
        <li :class="currentPage === pg ? 'page-item active' : 'page-item'">
          <a class="page-link" @click="onPageChange(pg)">{{ pg }}</a>
        </li>
      </template>
      <!-- 다음 페이지 -->
      <li class="page-item">
        <a class="page-link" @click="goNextPage">다음</a>
      </li>
      <!-- 마지막 페이지 -->
      <li class="page-item"><a class="page-link" @click="onPageChange(totalPage)">마지막</a></li>
    </ul>
  </div>
</template>

<style scoped>
a {
  color: #75423c;
  border-color: #75423c;
}
a:hover {
  background-color: #eee0d1;
  color: black;
}
/* 활성 상태 */
.page-item.active .page-link {
  color: white !important; /* 텍스트 색상 */
  background-color: #75423c !important; /* 배경 색상 */
  border-color: #75423c !important; /* 테두리 색상 */
}
</style>
