<template>
	<Header />
	<Sidebar />

	<div class="content-box" >
		<div class="content">
			<router-view v-slot="{ Component }">
			</router-view>
		</div>
	</div>
</template>

<script>
//import { useUserStore } from '@/stores/module/user'
//const userStore = useUserStore()

import Header from '@/components/header.vue';
import Sidebar from '@/components/sidebar.vue';



export default {
	mounted: function () {

	},

	data: function () {
		return {


		}
	},
	methods: {


	},
	components: {
		"Header":Header,
		"Sidebar":Sidebar,

	}


}
</script>
