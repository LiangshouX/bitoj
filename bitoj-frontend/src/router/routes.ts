import { RouteRecordRaw } from "vue-router";
import ExampleView from "@/views/ExampleView.vue";
import AdminView from "@/views/AdminView.vue";
import { meta } from "@typescript-eslint/parser";
import NoAuthView from "@/views/NoAuthView.vue";
import ACCESS_ENUM from "@/access/accessEnum";
import UserLoginView from "@/views/user/UserLoginView.vue";
import UserLayout from "@/layouts/UserLayout.vue";
import UserRegisterView from "@/views/user/UserRegisterView.vue";
import ManageQuestionView from "@/views/question/ManageQuestionView.vue";
import AddQuestionView from "@/views/question/AddQuestionView.vue";
import AboutView from "@/views/AboutView.vue";
import UserInfoView from "@/views/user/UserInfoView.vue";
import QuestionsView from "@/views/question/QuestionsView.vue";
import ViewQuestionsView from "@/views/question/ViewQuestionsView.vue";

export const routes: Array<RouteRecordRaw> = [
  {
    path: "/user",
    name: "用户",
    component: UserLayout,
    children: [
      {
        path: "/user/login",
        name: "用户登录",
        component: UserLoginView,
      },
      {
        path: "/user/register",
        name: "用户注册",
        component: UserRegisterView,
      },
      {
        path: "/user/info",
        name: "用户信息",
        component: UserInfoView,
      },
    ],
    meta: {
      hideInMenu: true,
    },
  },

  {
    path: "/question/view/:id",
    name: "在线做题",
    component: ViewQuestionsView,
    props: true, // 开启接收动态id
    meta: {
      access: ACCESS_ENUM.USER,
      hideInMenu: true,
    },
  },
  {
    path: "/add/question",
    name: "创建题目",
    component: AddQuestionView,
    meta: {
      access: ACCESS_ENUM.ADMIN,
    },
  },
  {
    path: "/update/question",
    name: "更新题目",
    component: AddQuestionView,
    meta: {
      access: ACCESS_ENUM.ADMIN,
      hideInMenu: true,
    },
  },
  {
    path: "/manage/question/",
    name: "管理题目",
    component: ManageQuestionView,
    meta: {
      access: ACCESS_ENUM.ADMIN,
    },
  },

  {
    path: "/",
    name: "浏览题目",
    component: QuestionsView,
  },
  {
    path: "/noAuth",
    name: "无权限",
    component: NoAuthView,
    meta: {
      hideInMenu: true,
    },
  },

  {
    path: "/admin",
    name: "ROOT",
    component: AdminView,
    meta: {
      access: ACCESS_ENUM.ADMIN,
    },
  },
  {
    path: "/about",
    name: "About",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: AboutView,
  },
];
