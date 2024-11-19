<script setup>
import { ref, computed } from "vue";
import { storeToRefs } from "pinia";
import { useRouter } from "vue-router";
import { useMemberStore } from "@/stores/member";
import { useMenuStore } from "@/stores/menu";

const router = useRouter();
const memberStore = useMemberStore();
const menuStore = useMenuStore();

const { isLogin, isLoginError } = storeToRefs(memberStore);
const { userLogin, getUserInfo } = memberStore;
const { updateMenuState } = menuStore;

// 로그인 데이터
const loginUser = ref({
  userId: "",
  userPwd: "",
});

// 에러 메시지 관리
const errorMessage = computed(() => {
  if (isLoginError.value) {
    return "아이디 또는 비밀번호가 잘못되었습니다.";
  }
  return "";
});

// 로그인 함수
const login = async () => {
  try {
    // 입력 검증
    if (!loginUser.value.userId || !loginUser.value.userPwd) {
      alert("아이디와 비밀번호를 모두 입력해주세요.");
      return;
    }

    console.log(loginUser);
    await userLogin(loginUser.value); // 로그인 시도
    const token = sessionStorage.getItem("accessToken");
    console.log(token);

    if (isLogin.value) {
      await getUserInfo(token); // 사용자 정보 가져오기
      updateMenuState(); // 메뉴 상태 변경
      router.replace("/"); // 홈 화면으로 이동
    }
  } catch (error) {
    console.error("로그인 실패:", error);
  }
};
</script>

<template>
  <div class="login-container">
    <div class="logo">
      <img src="@/assets/logo.PNG" alt="Miyeo Logo" />
    </div>
    <div class="login-form">
      <!-- 아이디 입력 -->
      <input
        type="text"
        v-model="loginUser.userId"
        placeholder="아이디"
        class="input-field"
      />
      <!-- 비밀번호 입력 -->
      <input
        type="password"
        v-model="loginUser.userPwd"
        placeholder="비밀번호"
        class="input-field"
      />
      <!-- 에러 메시지 -->
      <p v-if="errorMessage" class="error-message">{{ errorMessage }}</p>
      <!-- 로그인 버튼 -->
      <button class="login-button" @click="login">로그인</button>
    </div>
  </div>
</template>

<style scoped>
.login-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: #f7e6d4;
  height: 100vh;
}

.logo img {
  margin-top: 50px;
  width: 150px;
}

.login-form {
  margin-top: 30px;
  display: flex;
  flex-direction: column;
  gap: 15px;
}

.input-field {
  width: 250px;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 25px;
  font-size: 16px;
  text-align: center;
  background-color: #fff;
}

.login-button {
  width: 250px;
  padding: 10px;
  border: none;
  border-radius: 25px;
  font-size: 16px;
  color: #fff;
  background-color: #d5a67d;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.login-button:hover {
  background-color: #c1906d;
}

.error-message {
  color: red;
  font-size: 14px;
  text-align: center;
  margin: -10px 0 10px 0;
}
</style>
