<script setup>
import { ref, onMounted } from "vue";
import { useMemberStore } from "@/stores/member";
import { useRouter } from "vue-router";

const router = useRouter();
const memberStore = useMemberStore();

// 유저 정보 바인딩 변수
const name = ref("");
const userId = ref("");
const userPwd = ref("");
const email = ref("");

// 비밀번호 변경을 위한 변수들
const newPassword = ref("");
const confirmPassword = ref("");
const showPasswordModal = ref(false);

// 페이지 로드 시 사용자 정보 가져오기
onMounted(async () => {
  const token = sessionStorage.getItem("accessToken");
  if (token) {
    console.log("토큰 확인:", token); // 디버깅: 토큰 확인
    await memberStore.getUserInfo(token);
    
    // userInfo 확인
    console.log("가져온 userInfo:", memberStore.userInfo);
    
    if (memberStore.userInfo) {
      name.value = memberStore.userInfo.userName || "";
      userId.value = memberStore.userInfo.userId || "";
      email.value = `${memberStore.userInfo.emailId || ""}@${memberStore.userInfo.emailDomain || ""}`;
      userPwd.value = memberStore.userInfo.userPwd || "";
    } else {
      console.error("userInfo를 가져오지 못했습니다.");
      alert("유저 정보를 불러올 수 없습니다.");
      router.push({ name: "login" });
    }
  } else {
    alert("로그인이 필요합니다.");
    router.push({ name: "login" });
  }
});


// 페이지 이동 함수
const navigateTo = (routeName) => {
  router.push({ name: routeName });
};

// 비밀번호 변경 함수
const changePassword = async () => {
  if (!userPwd.value) {
    alert("현재 비밀번호를 입력해주세요.");
    return;
  }
  if (!newPassword.value || !confirmPassword.value) {
    alert("새 비밀번호와 확인을 모두 입력해주세요.");
    return;
  }
  if (newPassword.value !== confirmPassword.value) {
    alert("새 비밀번호가 일치하지 않습니다.");
    return;
  }

  try {
    await memberStore.updateUser({
      userId: userId.value,
      column: "userPwd",
      value: newPassword.value,
      currentPassword: password.value
    });
    alert("비밀번호가 성공적으로 변경되었습니다.");
    password.value = "";
    newPassword.value = "";
    confirmPassword.value = "";
    showPasswordModal.value = false;
  } catch (error) {
    alert("비밀번호 변경에 실패했습니다.");
  }
};

// 이메일 변경 함수
const changeEmail = async () => {
  if (!email.value) {
    alert("이메일을 입력해주세요.");
    return;
  }

  try {
    await memberStore.updateUser({
      userId: userId.value,
      column: "email",
      value: email.value
    });
    alert("이메일이 성공적으로 변경되었습니다.");
  } catch (error) {
    alert("이메일 변경에 실패했습니다.");
  }
};
</script>

<template>
  <div class="mypage-container">
    <div class="mypage-header">
      <span @click="navigateTo('plan')">나의 계획</span>
      <span @click="navigateTo('reviews')">나의 후기</span>
      <span @click="navigateTo('favorites')">즐겨찾기</span>
      <span class="active">회원정보 수정</span>
    </div>
    <div class="mypage-content">
      <h2>회원정보</h2>
      <div class="form-group">
        <label>이름</label>
        <input type="text" v-model="name" class="input-field" disabled />
      </div>
      <div class="form-group">
        <label>아이디</label>
        <input type="text" v-model="userId" class="input-field" disabled />
      </div>
      
      <!-- 비밀번호 변경 섹션 -->
      <div class="form-group">
        <label>현재 비밀번호</label>
        <input type="password" v-model="userPwd" class="input-field" />
      </div>
      <div v-if="showPasswordModal" class="password-change-section">
        <div class="form-group">
          <label>새 비밀번호</label>
          <input type="password" v-model="newPassword" class="input-field" />
        </div>
        <div class="form-group">
          <label>새 비밀번호 확인</label>
          <input type="password" v-model="confirmPassword" class="input-field" />
        </div>
      </div>
      <button 
        class="change-button" 
        @click="showPasswordModal ? changePassword() : showPasswordModal = true"
      >
        {{ showPasswordModal ? '비밀번호 변경' : '비밀번호 변경하기' }}
      </button>

      <div class="form-group">
        <label>이메일</label>
        <input type="email" v-model="email" class="input-field" />
      </div>
      <button class="update-button" @click="changeEmail">이메일 수정</button>
    </div>
  </div>
</template>

<style scoped>
.mypage-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: #f7e6d4;
  min-height: 100vh;
}

.mypage-header {
  display: flex;
  justify-content: center;
  margin-top: 20px;
  gap: 20px;
  font-size: 16px;
}

.mypage-header span {
  cursor: pointer;
  padding: 5px 10px;
  border-radius: 20px;
  color: #755645;
}

.mypage-header .active {
  background-color: #d5a67d;
  color: #fff;
}

.mypage-content {
  margin-top: 30px;
  width: 80%;
  max-width: 600px;
  position: relative; /* 추가 */
}

h2 {
  text-align: center;
  margin-bottom: 20px;
}

.form-group {
  margin-bottom: 15px;
  position: relative; /* 추가 */
}

.form-group label {
  display: block;
  margin-bottom: 5px;
  color: #755645;
}

.input-field {
  width: 100%;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 25px;
  font-size: 16px;
  background-color: #fff;
}

/* password-group 스타일 수정 */
.password-group {
  position: relative;
  display: flex;
  gap: 10px; /* 입력창과 버튼 사이 간격 */
}

.password-group .input-field {
  flex: 1; /* 남은 공간을 모두 차지하도록 설정 */
  padding-right: 15px; /* 기존 패딩값 제거 */
  margin: 0; /* 마진 제거 */
}

.change-button {
  position: static; /* absolute 제거 */
  transform: none; /* transform 제거 */
  padding: 10px 20px; /* 패딩 조정 */
  border: none;
  border-radius: 25px;
  font-size: 14px;
  color: #fff;
  background-color: #d5a67d;
  cursor: pointer;
  white-space: nowrap;
  height: auto; /* 높이 자동 조정 */
  line-height: normal; /* 라인 높이 기본값으로 */
  min-width: 80px; /* 최소 너비 설정 */
  align-self: flex-start; /* 상단 정렬 */
  margin-top: 0; /* 상단 마진 제거 */
}

/* 입력창 비활성화 시 스타일 */
.input-field:disabled {
  background-color: #f5f5f5;
  color: #666;
}
.change-button:hover {
  background-color: #c1906d;
}

.update-button {
  display: block;
  width: 100%;
  padding: 10px;
  border: none;
  border-radius: 25px;
  font-size: 16px;
  color: #fff;
  background-color: #d5a67d;
  cursor: pointer;
}

.update-button:hover {
  background-color: #c1906d;
}
</style>

<!-- <script setup>
import { ref, onMounted } from "vue";
import { useMemberStore } from "@/stores/member"; // member store 가져오기
import { useRouter } from "vue-router";

const router = useRouter();
const memberStore = useMemberStore();

// 유저 정보 바인딩 변수
const name = ref(""); // 유저 이름
const userId = ref(""); // 유저 아이디
const password = ref(""); // 비밀번호
const email = ref(""); // 이메일

// 페이지 이동 함수
const navigateTo = (routeName) => {
  router.push({ name: routeName });
};

// 비밀번호 변경 처리
const handleChangePassword = async () => {
  if (!password.value) {
    alert("새로운 비밀번호를 입력해주세요.");
    return;
  }
  try {
    await memberStore.updatePassword(password.value); // 비밀번호 업데이트 API 호출
    alert("비밀번호가 성공적으로 변경되었습니다.");
    password.value = ""; // 변경 후 초기화
  } catch (error) {
    console.error("비밀번호 변경 실패:", error);
    alert("비밀번호 변경 중 오류가 발생했습니다.");
  }
};

// 회원 정보 수정 처리
const handleUpdate = async () => {
  if (!email.value) {
    alert("이메일을 입력해주세요.");
    return;
  }
  try {
    await memberStore.updateEmail(email.value); // 이메일 업데이트 API 호출
    alert("회원 정보가 성공적으로 수정되었습니다.");
  } catch (error) {
    console.error("회원 정보 수정 실패:", error);
    alert("회원 정보 수정 중 오류가 발생했습니다.");
  }
};
</script>

<template>
  <div class="mypage-container">
    <div class="mypage-header">
      <span @click="navigateTo('plan')">나의 계획</span>
      <span @click="navigateTo('reviews')">나의 후기</span>
      <span @click="navigateTo('favorites')">즐겨찾기</span>
      <span class="active">회원정보 수정</span>
    </div>
    <div class="mypage-content">
      <h2>회원정보</h2>
      <div class="form-group">
        <label>이름</label>
        <input
          type="text"
          v-model="name"
          class="input-field"
          disabled
        />
      </div>
      <div class="form-group">
        <label>아이디</label>
        <input
          type="text"
          v-model="userId"
          class="input-field"
          disabled
        />
      </div>
      <div class="form-group password-group">
        <label>비밀번호</label>
        <input
          type="password"
          v-model="password"
          class="input-field"
        />
        <button class="change-button" @click="changePassword">변경</button>
      </div>
      <div class="form-group">
        <label>이메일</label>
        <input
          type="email"
          v-model="email"
          class="input-field"
        />
      </div>
      <button class="update-button" @click="changeEmail">수정</button>
    </div>
  </div>
</template>

<style scoped>
.mypage-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: #f7e6d4;
  min-height: 100vh;
}

.mypage-header {
  display: flex;
  justify-content: center;
  margin-top: 20px;
  gap: 20px;
  font-size: 16px;
}

.mypage-header span {
  cursor: pointer;
  padding: 5px 10px;
  border-radius: 20px;
  color: #755645;
}

.mypage-header .active {
  background-color: #d5a67d;
  color: #fff;
}

.mypage-content {
  margin-top: 30px;
  width: 80%;
  max-width: 600px;
  position: relative; /* 추가 */
}

h2 {
  text-align: center;
  margin-bottom: 20px;
}

.form-group {
  margin-bottom: 15px;
  position: relative; /* 추가 */
}

.form-group label {
  display: block;
  margin-bottom: 5px;
  color: #755645;
}

.input-field {
  width: 100%;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 25px;
  font-size: 16px;
  background-color: #fff;
}

/* password-group 스타일 수정 */
.password-group {
  position: relative;
  display: flex;
  gap: 10px; /* 입력창과 버튼 사이 간격 */
}

.password-group .input-field {
  flex: 1; /* 남은 공간을 모두 차지하도록 설정 */
  padding-right: 15px; /* 기존 패딩값 제거 */
  margin: 0; /* 마진 제거 */
}

.change-button {
  position: static; /* absolute 제거 */
  transform: none; /* transform 제거 */
  padding: 10px 20px; /* 패딩 조정 */
  border: none;
  border-radius: 25px;
  font-size: 14px;
  color: #fff;
  background-color: #d5a67d;
  cursor: pointer;
  white-space: nowrap;
  height: auto; /* 높이 자동 조정 */
  line-height: normal; /* 라인 높이 기본값으로 */
  min-width: 80px; /* 최소 너비 설정 */
  align-self: flex-start; /* 상단 정렬 */
  margin-top: 0; /* 상단 마진 제거 */
}

/* 입력창 비활성화 시 스타일 */
.input-field:disabled {
  background-color: #f5f5f5;
  color: #666;
}
.change-button:hover {
  background-color: #c1906d;
}

.update-button {
  display: block;
  width: 100%;
  padding: 10px;
  border: none;
  border-radius: 25px;
  font-size: 16px;
  color: #fff;
  background-color: #d5a67d;
  cursor: pointer;
}

.update-button:hover {
  background-color: #c1906d;
}
</style> -->
  