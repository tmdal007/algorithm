<script setup>
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";
import { listArticle } from "@/api/board.js";

import VSelect from "@/components/common/VSelect.vue";
import BoardListItem from "@/components/boards/item/BoardListItem.vue";
import VPageNavigation from "@/components/common/VPageNavigation.vue";

const router = useRouter();

const selectOption = ref([
  { text: "검색조건", value: "" },
  { text: "번호", value: "article_no" },
  { text: "제목", value: "subject" },
]);

const articles = ref([]);
const currentPage = ref(1);
const totalPage = ref(0);
const { VITE_ARTICLE_LIST_SIZE } = import.meta.env;
const param = ref({
  pgno: currentPage.value,
  spp: VITE_ARTICLE_LIST_SIZE,
  key: "",
  word: "",
});

onMounted(() => {
  getArticleList();
});

const changeKey = (val) => {
  console.log("BoarList에서 선택한 검색 조건 : " + val);
  param.value.key = val;
};

const getArticleList = () => {
  console.log("서버에서 글 목록 얻어오기", param.value);
  listArticle(
    param.value,
    ({ data }) => {
      articles.value = data.articles;
      currentPage.value = data.currentPage;
      totalPage.value = data.totalPageCount;
    },
    (error) => {
      console.log(error);
    }
  );
};

const onPageChange = (val) => {
  currentPage.value = val;
  param.value.pgno = val;
  getArticleList();
};

const moveWrite = () => {
  router.push({ name: "article-write" });
};
</script>

<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-lg-10">
        <h2 class="title my-3 py-3 text-center">
          공지사항 목록
        </h2>
      </div>
      <div class="col-lg-10">
        <div class="row align-self-center mb-2">
          <div class="col-md-2 text-start">
            <!-- '글 작성' 버튼에는 .btn 클래스 적용 -->
            <button type="button" class="btn" @click="moveWrite">
              글 작성
            </button>
          </div>
          <div class="col-md-5 offset-5">
            <form class="d-flex">
              <VSelect :selectOption="selectOption" @onKeySelect="changeKey" />
              <div class="input-group input-group-sm mg-2">
                <input
                  type="text"
                  class="form-control"
                  v-model="param.word"
                  placeholder="검색어..."
                />
                <button class="btn search-btn" type="button" @click="getArticleList">검색</button>
              </div>
            </form>
          </div>
        </div>
        <table class="table table-hover">
          <thead>
            <tr class="text-center">
              <th scope="col" class="col-1">번호</th>
              <!-- 제목 컬럼의 너비를 더 크게 설정 -->
              <th scope="col" class="col-6">제목</th>
              <th scope="col" class="col-2">조회수</th>
              <th scope="col" class="col-2">등록일</th>
            </tr>
          </thead>
          <tbody>
            <BoardListItem
              v-for="article in articles"
              :key="article.articleNo"
              :article="article"
            ></BoardListItem>
          </tbody>
        </table>
      </div>
      <VPageNavigation
        :current-page="currentPage"
        :total-page="totalPage"
        @pageChange="onPageChange"
      ></VPageNavigation>
    </div>
  </div>
</template>

<style scoped>
.title {
  font-size: 28px;
  font-weight: bold;
  color: #333;
  margin: 40px 0 20px 0;
  padding-bottom: 15px;
  text-align: center;
}
.btn {
  width: 100px;
  font-weight: 600;
  transition: all 0.3s ease-in-out;
  color: #75423c;
  border-color: #75423c;
}

.btn:hover {
  transform: scale(1.05);
  background-color: #eee0d1;
  color: black;
}

/* 검색 버튼의 스타일은 변경하지 않도록 별도 스타일링 */
.search-btn {
  color: white;
  background-color: black;
  border: 1px solid #ccc;  /* 기본 테두리 스타일 */
}
</style>
