import { ref } from "vue";
import { defineStore } from "pinia";

export const useMenuStore = defineStore("menuStore", () => {
  const menuList = ref([
    { name: "회원가입", show: true, routeName: "user-join" },
    { name: "로그인", show: true, routeName: "user-login" },
    { name: "마이페이지", show: false, routeName: "user-mypage" },
    { name: "로그아웃", show: false, routeName: "user-logout" },
  ]);

  // 로그인 상태에 따라 메뉴 변경
  const updateMenuState = (isLoggedIn) => {
    menuList.value = menuList.value.map((menu) => {
      if (menu.routeName === "user-join" || menu.routeName === "user-login") {
        return { ...menu, show: !isLoggedIn }; // 로그인 상태에서 숨김
      } else if (menu.routeName === "user-mypage" || menu.routeName === "user-logout") {
        return { ...menu, show: isLoggedIn }; // 로그인 상태에서 표시
      }
      return menu;
    });
  };

  return {
    menuList,
    updateMenuState,
  };
});
