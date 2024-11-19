<script setup>
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import { detailArticle, deleteArticle } from "@/api/board";

const route = useRoute();
const router = useRouter();

// const articleno = ref(route.params.articleno);
const { articleno } = route.params;

const article = ref({});

onMounted(() => {
  getArticle();
});

const getArticle = () => {
  detailArticle(
    articleno,
    ({ data }) => {
      article.value = data;
    },
    (error) => {
      console.log(error);
    }
  );
};

function moveList() {
  router.push({ name: "article-list" });
}

function moveModify() {
  router.push({ name: "article-modify", params: { articleno } });
}

function onDeleteArticle() {
  deleteArticle(
    articleno,
    (response) => {
      if (response.status == 200) moveList();
    },
    (error) => {
      console.log(error);
    }
  );
}
</script>

<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-lg-10">
        <h2 class="board-title">
          공지사항 보기
        </h2>
      </div>
      <div class="col-lg-10">
        <div class="board-container">
          <!-- 제목 영역 -->
          <div class="article-header">
            <h2 class="article-title">{{ article.subject }}</h2>
            <span class="article-number">No. {{ article.articleNo }}</span>
          </div>

          <!-- 관리자, 작성일, 조회수 영역 -->
          <div class="article-info">
            <div class="info-container">
              <div class="author-info">
                <img
                  class="avatar"
                  src="@/assets/mimi.PNG"
                  alt="관리자 프로필"
                />
                <span class="author-name">관리자</span>
              </div>
              <div class="post-meta">
                <span>{{ article.registerTime }}</span>
                <span class="hit-count">조회 {{ article.hit }}</span>
              </div>
            </div>
          </div>

          <!-- 내용 영역 -->
          <div class="article-content">
            {{ article.content }}
          </div>

          <!-- 버튼 영역 -->
          <div class="article-actions">
            <button type="button" class="btn btn-action" @click="moveList">
              목록으로
            </button>
            <button type="button" class="btn btn-action" @click="moveModify">
              수정하기
            </button>
            <button type="button" class="btn btn-action" @click="onDeleteArticle">
              삭제하기
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.board-title {
  font-size: 28px;
  font-weight: bold;
  color: #333;
  margin: 40px 0 20px 0;
  padding-bottom: 15px;
  text-align: center;
}

.board-container {
  background: #fff;
  border: 1px solid #e1e1e1;
  border-radius: 8px;
  padding: 30px;
  margin-bottom: 40px;
}

.article-header {
  position: relative;
  padding-bottom: 20px;
  border-bottom: 1px solid #e1e1e1;
}

.article-title {
  font-size: 24px;
  font-weight: 600;
  color: #333;
  margin: 0;
  padding-right: 100px;
  text-align: left; /* 제목 왼쪽 정렬 */
}

.article-number {
  position: absolute;
  right: 0;
  top: 5px;
  color: #666;
  font-size: 14px;
}

.article-info {
  padding: 20px 0;
  border-bottom: 1px solid #e1e1e1;
}

.info-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.author-info {
  display: flex;
  align-items: center;
}

.avatar {
  width: 50px;
  height: 50px;
  border-radius: 25px;
  object-fit: cover;
  border: 1px solid #e1e1e1;
  margin-right: 15px;
}

.author-name {
  font-weight: 600;
  color: #333;
  font-size: 15px;
}

.post-meta {
  color: #666;
  font-size: 14px;
}

.hit-count {
  margin-left: 15px;
}

.article-content {
  padding: 30px 0;
  min-height: 400px;
  color: black;
  font-size: 20px;
  text-align: left; /* 내용 왼쪽 정렬 */
}

.article-actions {
  display: flex;
  justify-content: flex-end;
  gap: 8px;
  padding-top: 20px;
  border-top: 1px solid #e1e1e1;
}

.btn-action  {
  width: 100px;
  font-weight: 600;
  transition: all 0.3s ease-in-out;
  color: #75423c;
  border-color: #75423c;
}

.btn-action:hover {
  transform: scale(1.05);
  background-color: #eee0d1;
  color: black;
}


/* 반응형 디자인 */
@media (max-width: 768px) {
  .board-title {
    font-size: 24px;
    margin: 20px 0 15px 0;
  }

  .board-container {
    padding: 20px;
  }

  .article-title {
    font-size: 20px;
    padding-right: 0;
    margin-bottom: 10px;
  }

  .article-number {
    position: static;
    display: block;
    margin-bottom: 10px;
  }

  .article-content {
    padding: 20px 0;
    font-size: 14px;
  }

  .info-container {
    flex-direction: column;
    align-items: flex-start;
    gap: 10px;
  }

  .post-meta {
    align-self: flex-end;
  }

  .btn-action {
    font-size: 13px;
    padding: 6px 12px;
  }
}
</style>