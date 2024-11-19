<script setup>
import { ref, computed } from "vue";
import { useRouter } from "vue-router";
import { registerUser, idCheck } from "@/api/user"; // 회원가입 API

const router = useRouter();

const userId = ref(""); // 아이디
const userPwd = ref(""); // 비밀번호
const confirmPassword = ref(""); // 비밀번호 확인
const userName = ref(""); // 이름
const email = ref(""); // 이메일 (전체)

const isDuplicateChecked = ref(false); // 중복 확인 여부
const isLoading = ref(false); // 로딩 상태

const idError = ref(""); // 아이디 에러 메시지
const passwordError = ref(""); // 비밀번호 에러 메시지
const emailError = ref(""); // 이메일 에러 메시지
const nameError = ref(""); // 이름 에러 메시지

// 중복체크
const checkDuplicateId = async () => {
  try {
    const param = { userId: userId.value };
    await idCheck(param);
    isDuplicateChecked.value = true;
    idError.value = "";
  } catch (error) {
    idError.value = "이미 존재하는 아이디입니다.";
  }
};

// 비밀번호 유효성 검사
const validatePassword = () => {
  if (userPwd.value.length < 6) {
    passwordError.value = "비밀번호는 6자 이상이어야 합니다.";
  } else if (userPwd.value !== confirmPassword.value) {
    passwordError.value = "비밀번호와 비밀번호 확인이 일치하지 않습니다.";
  } else {
    passwordError.value = "";
  }
};

// 이메일 유효성 검사
const validateEmail = () => {
  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  if (!emailRegex.test(email.value)) {
    emailError.value = "유효한 이메일 주소를 입력해주세요.";
  } else {
    emailError.value = "";
  }
};

// 이메일 분리 함수
const splitEmail = () => {
  const [emailId, emailDomain] = email.value.split("@");
  if (!emailId || !emailDomain) {
    throw new Error("이메일 주소가 올바르지 않습니다.");
  }
  return { emailId, emailDomain };
};

// 폼 유효성 검사
const isFormValid = computed(() => {
  return (
    userId.value &&
    isDuplicateChecked.value &&
    !idError.value &&
    userPwd.value &&
    !passwordError.value &&
    userName.value &&
    !nameError.value &&
    email.value &&
    !emailError.value
  );
});

// 회원가입 처리
const handleRegister = async () => {
  if (!isFormValid.value) return;

  isLoading.value = true;
  try {
    const { emailId, emailDomain } = splitEmail(); // 이메일 분리
    const param = {
      userId: userId.value,
      userPwd: userPwd.value,
      userName: userName.value,
      emailId,
      emailDomain,
    };
    await registerUser(param);
    alert("회원가입이 성공적으로 완료되었습니다.");
    router.push({name: "user-login"}); //회원가입 성공 시 로그인 페이지로 이동
  } catch (error) {
    console.error("회원가입 실패:", error);
    alert("회원가입 중 오류가 발생했습니다.");
  } finally {
    isLoading.value = false;
  }
};
</script>

<template>
  <div class="register-container">
    <div class="logo">
      <img src="@/assets/logo.PNG" alt="Miyeo Logo" />
    </div>
    <div class="register-form">
      <div class="form-group">
        <input
          type="text"
          v-model.trim="userId"
          placeholder="아이디"
          class="input-field"
          :disabled="isDuplicateChecked"
        />
        <button
          class="check-button"
          @click="checkDuplicateId"
          :disabled="!userId || isDuplicateChecked"
        >
          중복체크
        </button>
      </div>
      <p v-if="idError" class="error-message">{{ idError }}</p>
      <input
        type="password"
        v-model="userPwd"
        placeholder="비밀번호 (6자 이상)"
        class="input-field"
        @input="validatePassword"
      />
      <input
        type="password"
        v-model="confirmPassword"
        placeholder="비밀번호 확인"
        class="input-field"
        @input="validatePassword"
      />
      <p v-if="passwordError" class="error-message">{{ passwordError }}</p>
      <input
        type="text"
        v-model.trim="userName"
        placeholder="이름"
        class="input-field"
      />
      <p v-if="nameError" class="error-message">{{ nameError }}</p>
      <input
        type="email"
        v-model.trim="email"
        placeholder="이메일"
        class="input-field"
        @input="validateEmail"
      />
      <p v-if="emailError" class="error-message">{{ emailError }}</p>
      <button
        class="register-button"
        @click="handleRegister"
        :disabled="!isFormValid || isLoading"
      >
        {{ isLoading ? "처리중..." : "회원가입" }}
      </button>
    </div>
  </div>
</template>




<style scoped>
/* 이전 스타일은 동일하게 유지하되, 로딩 상태에 대한 스타일 추가 */
.register-button:disabled {
  background-color: #cccccc;
  cursor: not-allowed;
  opacity: 0.7;
}

.input-field:disabled {
  background-color: #f5f5f5;
  cursor: not-allowed;
}

.register-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: #f7e6d4;
  height: 100vh;
}

.logo img {
  margin-top: 20px;
  width: 150px;
}

.register-form {
  margin-top: 30px;
  display: flex;
  flex-direction: column;
  gap: 15px;
}

.form-group {
  display: flex;
  justify-content: space-between;
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

.check-button {
  width: 80px;
  padding: 10px;
  border: none;
  border-radius: 25px;
  font-size: 14px;
  color: #fff;
  background-color: #d5a67d;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.check-button:hover {
  background-color: #c1906d;
}

.register-button {
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

.register-button:hover {
  background-color: #c1906d;
}

.error-message {
  color: red;
  font-size: 12px;
  text-align: left;
  margin-left: 10px;
}
</style>
