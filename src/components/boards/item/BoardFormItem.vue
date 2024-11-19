<script setup>
import { ref, watch } from "vue";
import { useRoute, useRouter } from "vue-router";
import { registArticle, getModifyArticle, modifyArticle } from "@/api/board";

const router = useRouter();
const route = useRoute();

const props = defineProps({ type: String });

const isUseId = ref(false);

const article = ref({
  articleNo: 0,
  userId: "",
  subject: "",
  content: "",
  registerTime: "",
});

if (props.type === "modify") {
  let { articleno } = route.params;
  console.log(articleno + "번 글 수정!");
  getModifyArticle(
    articleno,
    ({ data }) => {
      article.value = data;
      isUseId.value = true;
    },
    (error) => {
      console.log(error);
    }
  );
  isUseId.value = true;
}

const subjectErrMsg = ref("");
const contentErrMsg = ref("");
watch(
  () => article.value.subject,
  (value) => {
    let len = value.length;
    if (len == 0 || len > 30) {
      subjectErrMsg.value = "제목을 확인해 주세요!!!";
    } else subjectErrMsg.value = "";
  },
  { immediate: true }
);
watch(
  () => article.value.content,
  (value) => {
    let len = value.length;
    if (len == 0 || len > 500) {
      contentErrMsg.value = "내용을 확인해 주세요!!!";
    } else contentErrMsg.value = "";
  },
  { immediate: true }
);

function onSubmit() {
  if (subjectErrMsg.value) {
    alert(subjectErrMsg.value);
  } else if (contentErrMsg.value) {
    alert(contentErrMsg.value);
  } else {
    props.type === "regist" ? writeArticle() : updateArticle();
  }
}

function writeArticle() {
  registArticle(
    article.value,
    (response) => {
      let msg = "공지사항 등록 중 문제 발생 !";
      if (response.status == 201) msg = "공지사항 등록 완료 !";
      alert(msg);
      moveList();
    },
    (error) => console.log(error)
  );
}

function updateArticle() {
  modifyArticle(
    article.value,
    (response) => {
      let msg = "공지사항 수정 중 문제 발생 !";
      if (response.status == 200) msg = "공지사항 수정 완료 !";
      alert(msg);
      moveList();
    },
    (error) => console.log(error)
  );
}
// 리스트로 이동
function moveList() {
  router.push({ name: "article-list" });
}
// 상세보기로 이동
function moveView() {
  router.push({ name: "article-view" });
}
</script>

<template>
  <form @submit.prevent="onSubmit">
    <div class="mb-3">
      <label for="userid" class="form-label">관리자 ID </label>
      <input
        type="text"
        class="form-control"
        v-model="article.userId"
        :disabled="isUseId"
        placeholder="임의로 작성 -> ssafy"
      />
    </div>
    <div class="mb-3">
      <label for="subject" class="form-label">제목</label>
      <input type="text" class="form-control" v-model="article.subject" />
    </div>
    <div class="mb-3 content-group">
      <label for="content" class="form-label">내용</label>
      <textarea class="form-control" v-model="article.content" rows="10"></textarea>
    </div>
    <div class="button-group">
      <button type="submit" class="btn" v-if="props.type === 'regist'">작성하기</button>
      <button type="submit" class="btn" v-else>글 수정</button>
      <button type="button" class="btn" @click="moveList">목록으로</button>
      <button type="button" class="btn" v-if="props.type === 'modify'" @click="moveView">돌아가기</button>
    </div>
  </form>
</template>


<style scoped>
.mb-3 {
  display: flex;
  align-items: center;
  margin-bottom: 1rem;
}

.content-group {
  align-items: flex-start;
}

.form-label {
  min-width: 70px;
  margin-bottom: 0;
}

.form-control {
  margin-left: 10px;
  background-color: #faf6f2;
  flex: 1;
}

/* 버튼 그룹 스타일링 */
.button-group {
  width: 100%;
  display: flex;
  justify-content: center; /* 가운데 정렬로 변경 */
  gap: 10px;
  margin-top: 1rem;
  padding-left: 70px; /* form-label의 min-width만큼 padding 추가 */
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

/* 반응형 스타일 */
@media (max-width: 768px) {
  .mb-3 {
    flex-direction: column;
    align-items: flex-start;
  }

  .form-label {
    margin-bottom: 0.5rem;
  }

  .button-group {
    padding-left: 0; /* 모바일에서는 패딩 제거 */
  }
}
</style>


