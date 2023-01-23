<template>
	<div>
		<div v-if="loading == true">
			<Coffee />
		</div>
		<div v-else>
			<div>
				<div class="sticky top-0 bg-base">
					<div class="flex place-items-end mx-[5vw] py-[2vh] gap-x-[2vw]">
						<nuxt-link to="/"><img :src="Logo" class="h-[8vh]" /></nuxt-link>

						<div
							class="w-[75vw] sm:text-xl md:text-2xl bg-base-dark flex place-content-between place-items-center rounded-full"
						>
							<input
								v-model="q"
								class="w-[73%] max-[450px]:pl-[5%] max-[500px]:py-[4%] sm:pl-[3%] sm:py-[1%] bg-transparent"
								type="text"
								@keydown.enter="gotoRoute"
							/>
							<div class="flex space-x-[1vw] place-items-center mr-[1vw]">
								<img :src="Cross" class="h-[2vh] mr-[0.3vw] cursor-pointer" @click="clearInput" />
								<div class="w-[0.1vh] h-[3vh] bg-[#a3a3a3]"></div>
								<a v-if="q.length != 0" :href="gotoRoute">
									<img :src="Bean" class="mr-[1vw] h-[3vh]" />
								</a>
								<div v-else class="cursor-pointer select-none">
									<img :src="Bean" class="h-[3vh] mr-[1vw]" />
								</div>
							</div>
						</div>
					</div>
					<div class="w-screen h-[0.1vh] bg-base-light"></div>
				</div>
				<ClientOnly>
					<div
						class="md:flex space-x-[5vw] md:place-content-center place-items-start m-auto mt-[1vh]"
					>
						<div class="w-[80vw] lg:w-[50vw] m-auto h-max overflow-y-scroll bg-scroll">
							<div v-for="result in resultList" class="mb-[4vh]">
								<div v-if="result.url.length != 0 && result.title.length != 0">
									<div class="truncate w-[60%] text-sm">
										{{ result.url }}
									</div>
									<nuxt-link
										class="text-xl text-[#75a8fd] underline-offset-2 hover:underline"
										:to="result.url"
										>{{ result.title }}</nuxt-link
									>
								</div>
							</div>
						</div>
						<div class="sm:w-[80vw] md:w-[30vw]">
							<div class="font-bold">相關搜索</div>
							<div v-for="relativeKeyword in relativeKeywords">
								<button
									class="max-[450px]:w-[70vw] sm:w-[20vw] max-[450px]:px-[4%] md:px-[2%] py-[1.5vh] m-auto bg-base-dark mt-[1.5vh] rounded-[50px] hover:bg-neutral-500"
									@click="relativeClicked(relativeKeyword)"
								>
									<div class="text-lg text-left px-[5%]">{{ relativeKeyword }}</div>
								</button>
							</div>
						</div>
					</div>
				</ClientOnly>
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
const resultList = (await axios.get('http://localhost:8080/search/' + route.query.q)).data
const relativeKeywords = (await axios.get('http://localhost:8080/search/relative/' + route.query.q))
	.data

onMounted(() => (loading.value = false))

const q = ref('')
q.value = route.query.q

const clearInput = () => (q.value = '')

const gotoRoute = async () => {
	loading.value = true
	await useRouter().push('search?q=' + q.value)
	window.location.reload(true)
}

const relativeClicked = async (relativeWord) => {
	loading.value = true
	await useRouter().push('search?q=' + relativeWord)
	window.location.reload(true)
}
</script>

<style lang="scss" scoped></style>
