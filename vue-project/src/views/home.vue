<template>
	<v-header />
	<v-sidebar />
	<div class="content-box" :class="{ 'content-collapse': sidebar.collapse }">
		<!-- <v-tags></v-tags> -->
		<div class="content">
			<router-view v-slot="{ Component }">
				<transition name="move" mode="out-in">
					<keep-alive :include="tags.nameList">
						<component :is="Component"></component>
					</keep-alive>
				</transition>
			</router-view>
		</div>
	</div>
</template>

<script>
import { useUserStore } from '@/stores/module/user'
import {  ElMessageBox } from 'element-plus'

const userStore = useUserStore()



      export default {
        mounted:function(){

        },

      data:function() {
        return {


          }
      },
      methods: {


      }

    }
</script>
