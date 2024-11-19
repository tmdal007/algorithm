<script setup>
import { watchEffect } from "vue";
import { useMenuStore } from "@/stores/menu"; // 메뉴 store 가져오기
import { useMemberStore } from "@/stores/member"; // 로그인 상태 store
import { storeToRefs } from "pinia"; // pinia의 storeToRefs로 상태 추출

const menuStore = useMenuStore();
const memberStore = useMemberStore();
const { menuList } = storeToRefs(menuStore);
const { isLogin } = storeToRefs(memberStore);
const { userLogout } = memberStore; // 로그아웃 함수 가져오기

// 로그인 상태 변화에 따라 메뉴 업데이트
watchEffect(() => {
  menuStore.updateMenuState(isLogin.value);
});

// 로그아웃 처리
const logout = async () => {
  console.log("로그아웃 호출 시작!");
  try {
    await userLogout();
    console.log("로그아웃 성공") 
  } catch (error) {
    console.log("로그아웃 중 오류 발생: ", error); 
  }
};
</script>

<template>
  <nav class="custom-navbar navbar navbar-expand-lg sticky-top">
    <div class="container">
      <!-- Logo -->
      <router-link :to="{ name: 'main' }" class="navbar-brand">
        <img src="@/assets/logo.PNG" alt="Miye Logo" class="logo-image" />
      </router-link>

      <!-- Main Menu -->
      <div class="main-menu">
        <router-link to="/search" class="nav-link">여행지 검색</router-link>
        <router-link to="/board/list" class="nav-link">게시글</router-link>
        <router-link to="/board" class="nav-link">공지사항</router-link>
      </div>

      <!-- Auth Menu -->
      <div class="auth-menu">
        <template v-for="menu in menuList" :key="menu.routeName">
          <template v-if="menu.show">
            <template v-if="menu.routeName === 'user-logout'">
              <router-link to="/" @click.prevent="logout" class="nav-link">
                {{ menu.name }}
              </router-link>
            </template>
            <template v-else>
              <router-link :to="{ name: menu.routeName }" class="nav-link">
                {{ menu.name }}
              </router-link>
            </template>
          </template>
        </template>
      </div>
    </div>
  </nav>
</template>


<style scoped>
.custom-navbar {
  background-color: #C4A484;
  padding: 8px 0;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.container {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.logo-image {
  height: 40px;
  width: auto;
  object-fit: contain;
}

.main-menu {
  display: flex;
  gap: 2rem;
  margin: 0 auto;
}

.auth-menu {
  display: flex;
  gap: 1rem;
}

.nav-link {
  color: #4a4a4a !important;
  text-decoration: none;
  font-size: 1rem;
  transition: color 0.2s ease;
  padding: 0.5rem;
}

.nav-link:hover {
  color: #2c2c2c !important;
}

.nav-link.router-link-active {
  color: #2c2c2c !important;
  font-weight: 500;
}

/* Optional: Add responsive styles if needed */
@media (max-width: 992px) {
  .main-menu {
    gap: 1rem;
  }
}
</style>
