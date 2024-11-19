import { ref } from "vue";
import { useRouter } from "vue-router";
import { defineStore } from "pinia";
import { useMenuStore } from "@/stores/menu"; // 메뉴 store 추가
import { jwtDecode } from "jwt-decode";

import { userConfirm, findById, tokenRegeneration, logout, updateUserField } from "@/api/user";
import { httpStatusCode } from "@/util/http-status";

export const useMemberStore = defineStore("memberStore", () => {
  const router = useRouter();
  const menuStore = useMenuStore(); // 메뉴 상태 관리
  const isLogin = ref(false);
  const isLoginError = ref(false);
  const userInfo = ref(null);
  const isValidToken = ref(false);

  const userLogin = async (loginUser) => {
    await userConfirm(
      loginUser,
      (response) => {
        if (response.status === httpStatusCode.CREATE) {
          let { data } = response;
          let accessToken = data["access-token"];
          let refreshToken = data["refresh-token"];
          isLogin.value = true;
          isLoginError.value = false;
          isValidToken.value = true;
          sessionStorage.setItem("accessToken", accessToken);
          sessionStorage.setItem("refreshToken", refreshToken);
          menuStore.updateMenuState(true); // 로그인 후 메뉴 업데이트
        }
      },
      (error) => {
        isLogin.value = false;
        isLoginError.value = true;
        isValidToken.value = false;
        console.error(error);
      }
    );
  };

  const getUserInfo = async (token) => {
    try {
      const decodeToken = jwtDecode(token);
      console.log("Decoded Token:", decodeToken);
      await findById(
        decodeToken.userId,
        (response) => {
          console.log("API 응답:", response); // 디버깅 추가
          if (response.status === httpStatusCode.OK) {
            console.log("User Info:", response.data.userInfo); // 디버깅 추가
            userInfo.value = response.data.userInfo;
          } else {
            console.log("유저 정보 없음!!!!");
          }
        },
        async (error) => {
          console.error(
            "토큰 만료되어 사용 불가능: ",
            error.response.status,
            error.response.statusText
          );
          isValidToken.value = false;
          await tokenRegenerate();
        }
      );
    } catch (error) {
      console.error("getUserInfo 실패:", error); // 디버깅 추가
    }
  };

  const userLogout = async () => {
    if (userInfo.value?.userId) {
      await logout(userInfo.value.userId);
    }
    isLogin.value = false;
    userInfo.value = null;
    isValidToken.value = false;
    sessionStorage.removeItem("accessToken");
    sessionStorage.removeItem("refreshToken");
    menuStore.updateMenuState(false); // 로그아웃 후 메뉴 업데이트
  };

  // 회원 정보 업데이트 (비밀번호/이메일)
  // member.js의 defineStore 내부에 추가할 함수
const updateUser = async (params) => {
    try {
      await updateUserField(
        params,
        (response) => {
          if (response.status === httpStatusCode.OK) {
            // 사용자 정보 업데이트 성공
            if (params.column === "email") {
              userInfo.value = {
                ...userInfo.value,
                email: params.value
              };
            }
            return true;
          }
          return false;
        },
        (error) => {
          console.error("사용자 정보 업데이트 실패:", error);
          throw error;
        }
      );
    } catch (error) {
      throw error;
    }
  };

  return {
    isLogin,
    isLoginError,
    userInfo,
    isValidToken,
    userLogin,
    getUserInfo,
    userLogout,
    updateUser,
  };
});
