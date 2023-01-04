<template>
	<div>
		<div v-if="loading == true">
			<Coffee />
		</div>
		<div v-else>
			<div class="sticky top-0 bg-base">
				<div class="flex place-items-end mx-[5vw] py-[2vh] gap-x-[2vw]">
					<nuxt-link to="/"><img :src="Logo" class="w-[4vw]" /></nuxt-link>

					<div
						class="w-[60vw] text-2xl bg-base-dark flex place-content-between place-items-center rounded-full"
					>
						<input
							v-model="q"
							class="w-[53vw] pl-[2vw] py-[0.5vh] bg-transparent"
							type="text"
							@keydown.enter="gotoRoute"
						/>
						<div class="flex gap-x-[1vw] place-items-center">
							<img :src="Cross" class="w-[1vw] mr-[0.3vw] cursor-pointer" @click="clearInput" />
							<div class="w-[0.1vh] h-[3vh] bg-[#a3a3a3]"></div>
							<button v-if="q.length != 0" @click="gotoRoute">
								<img :src="Bean" class="w-[2vw] mr-[1vw]" />
							</button>
							<div v-else class="cursor-pointer select-none">
								<img :src="Bean" class="w-[2vw] mr-[1vw]" />
							</div>
						</div>
					</div>
				</div>
				<div class="w-screen h-[0.1vh] bg-base-light"></div>
			</div>
			<div class="w-[90vw] h-max overflow-y-scroll bg-scroll m-auto mt-[2vh]">
				<div v-for="result in resultList" class="mb-[4vh]">
					<div class="truncate w-[30%] text-sm">{{ result.url }}</div>
					<nuxt-link class="text-xl text-[#75a8fd]" :to="result.url">{{ result.title }}</nuxt-link>
				</div>
			</div>
		</div>
	</div>
</template>

<script setup>
import Logo from '~/assets/icons/logo.svg?url'
import Cross from '~/assets/icons/cross.svg?url'
import Bean from '~/assets/icons/bean.svg?url'
import axios from 'axios'

const loading = ref(true)
const route = useRoute()
const inputRoute = computed(() => route.path + '?q=' + q.value)
let resultList = (await axios.get('http://localhost:8080/search/' + route.query.q)).data

onMounted(() => (loading.value = false))

const q = ref('')
q.value = route.query.q

const clearInput = () => (q.value = '')

const gotoRoute = () => {
	loading.value = true
	useRouter().push('search?q=' + q.value)
	window.location.reload(true)
}
</script>

<style lang="scss" scoped></style>
