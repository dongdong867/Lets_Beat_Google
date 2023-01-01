<template>
	<div>
		<div class="sticky top-0 bg-base">
			<div class="flex place-items-end mx-[5vw] py-[2vh] gap-x-[2vw]">
				<nuxt-link to="/"><img :src="Logo" class="w-[4vw]" /></nuxt-link>

				<div
					class="w-[60vw] text-2xl bg-base-dark flex place-content-between place-items-center rounded-full"
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
		<client-only>
			<div class="w-full h-max overflow-y-scroll bg-scroll">
				<div v-for="result in resultList">
					<a href="">{{ result.title }}</a>
					<div>{{ result.score }}</div>
				</div>
			</div>
		</client-only>
	</div>
</template>

<script setup>
import Logo from '~/assets/icons/logo.svg?url'
import Cross from '~/assets/icons/cross.svg?url'
import Search from '~/assets/icons/search.svg?url'
import axios from 'axios'

const route = useRoute()
const results = await axios.get('http://localhost:8080/search/' + route.query.q)
const resultList = reactive(results.data)

const q = ref('')
q.value = route.query.q

const clearInput = () => (q.value = '')
</script>

<style lang="scss" scoped></style>
