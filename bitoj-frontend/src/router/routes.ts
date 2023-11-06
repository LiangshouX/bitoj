const AboutView = () => import("@/views/AboutView.vue");

export const routes = [
    {
        path: "/",
        name: "home",
        component: AboutView,
    },
    {
        path: "/about",
        name: "about",
        component: () => import("../views/AboutView.vue")
    }
];
