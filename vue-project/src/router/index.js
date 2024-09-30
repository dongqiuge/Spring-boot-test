import { createRouter, createWebHistory } from 'vue-router'
import {useUserStore} from "@/stores/module/user.js";
import Home from "@/views/home.vue";


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: () => import('@/views/login.vue')
    },

    {
      path: '/login',
      name: 'login',
      component: () => import('@/views/login.vue')
    },
    {
      path: '/user',
      name: 'user',
      component: () => import('@/views/user.vue')
    },

    {
      path: '/',
      name: 'Home',
      component: Home,
      children: [
        {
          path: '/dashboard',
          name: 'dashboard',
          meta: {
            title: 'ホームページ',
            permiss: '1',
          },
          component: () => import(/* webpackChunkName: "dashboard" */ '../views/dashboard.vue'),
        }

  ]
}]

})
router.beforeEach((to, from, next) => {
  // NProgress.start();

  const userStore = useUserStore()
  const token = userStore.token;

  const whiteList = ['/login'];  // no redirect whitelist



  if (whiteList.indexOf(to.path) !== -1) {
    // in the free login whitelist, go directly
    next() ;
  }
  else
  {
    if (token) {
      // other pages that do not have permission to access are redirec    ted to the login page.
      next();
    }
    else {
      next(`/login?redirect=${to.path}`);
    }

  }

  //NProgress.done()
});

//後置ルーター
router.afterEach(() => {
  /*NProgress.done() //コンポーネントの読み込み完了後、プログレスバーの表示をキャンセルする*/
})




export default router
