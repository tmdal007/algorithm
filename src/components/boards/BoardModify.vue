<script setup>
import BoardFormItem from "./item/BoardFormItem.vue";
</script>

<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-lg-10">
        <div class="title my-3 py-3 text-center">
          공지사항 수정
        </div>
      </div>
      <div class="col-lg-10 text-start">
        <BoardFormItem type="modify" />
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
</style>
