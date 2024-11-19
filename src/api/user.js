import { localAxios } from "@/util/http-commons";

const local = localAxios();

async function userConfirm(param, success, fail) {
  await local.post(`/user/login`, param).then(success).catch(fail);
}

async function findById(userid, success, fail) {
  local.defaults.headers["Authorization"] = sessionStorage.getItem("accessToken");
  await local.get(`/user/info/${userid}`).then(success).catch(fail);
}

async function tokenRegeneration(user, success, fail) {
  local.defaults.headers["refreshToken"] = sessionStorage.getItem("refreshToken"); //axios header에 refresh-token 셋팅
  await local.post(`/user/refresh`, user).then(success).catch(fail);
}

async function logout(userid, success, fail) {
  await local.get(`/user/logout/${userid}`).then(success).catch(fail);
}

// 아이디 중복 확인
async function idCheck(param, success, fail) {
  await local.post(`/user/check-id`, param).then(success).catch(fail);
}

// 회원가입 처리
async function registerUser(param, success, fail) {
  await local.post(`/user/register`, param).then(success).catch(fail);
}

// 회원 정보 업데이트
async function updateUserField(param, success, fail) {
  await local.put(`/user/update`, param).then(success).catch(fail);
}




export { userConfirm, findById, tokenRegeneration, logout, registerUser, idCheck, updateUserField };