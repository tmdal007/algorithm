<script setup>
import BoardFormItem from "./item/BoardFormItem.vue";
</script>

<template>
  <div class="container form-container">
    <div class="row justify-content-center">
      <div class="col-lg-10">
        <h2 class="title my-3 py-3 text-center">
          공지사항 작성
        </h2>
      </div>
      <div class="col-lg-10 text-start form-content">
        <BoardFormItem type="regist" />
      </div>
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

/* 반응형 디자인 */
@media (max-width: 768px) {
  .form-container {
    padding: 20px;
  }

  h2 {
    font-size: 20px;
  }
}
</style>

