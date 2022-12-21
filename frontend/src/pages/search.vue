<template>
	<div class="sticky top-0">
		<div class="flex place-items-end mx-[5vw] py-[2vh] gap-x-[2vw]">
			<img :src="Logo" class="w-[4vw]" />
			<div
				class="w-[60vw] h-[5vh] text-2xl bg-base-dark flex place-content-between place-items-center rounded-full"
			>
				<input v-model="q" class="w-[53vw] pl-[2vw] py-[0.5vh] bg-transparent" type="text" />
				<div class="flex gap-x-[1vw]">
					<img :src="Cross" class="w-[1vw] mr-[0.3vw] cursor-pointer" @click="clearInput" />
					<div class="w-[0.1vh] h-[3vh] bg-[#a3a3a3]"></div>
					<nuxt-link v-if="q.length != 0" :to="{ path: '/search', query: { q } }">
						<img :src="Search" class="w-[2vw] mr-[1vw]" />
					</nuxt-link>
					<div v-else class="cursor-pointer select-none">
						<img :src="Search" class="w-[2vw] mr-[1vw]" />
					</div>
				</div>
			</div>
		</div>
		<div class="w-screen h-[0.1vh] bg-base-light"></div>
	</div>
	<div class="w-full h-[200vh]"></div>
</template>

<script setup>
import Logo from '~/assets/icons/logo.svg?url'
import Cross from '~/assets/icons/cross.svg?url'
import Search from '~/assets/icons/search.svg?url'

const route = useRoute()
const q = ref(route.query.q)

const clearInput = () => (input.value = '')
</script>

<style lang="scss" scoped></style>
