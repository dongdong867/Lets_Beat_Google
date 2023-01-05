<template>
	<div v-if="loading == true">
		<Coffee />
	</div>
	<div
		class="w-screen h-screen flex flex-col place-content-center place-items-center gap-y-[3vh]"
		v-else
	>
		<div class="flex place-content-center place-items-end gap-x-4 select-none cursor-default">
			<img :src="Logo" class="h-[10vh]" />
			<div class="max-[450px]:text-3xl max-[450px]:w-[40%] sm:text-6xl font-bold">
				Let's Beat <span class="max-[450px]:text-[41px]">Google</span>
			</div>
		</div>
		<div
			class="w-[80vw] h-[6vh] bg-neutral-800 flex place-content-between place-items-center rounded-full border-[2px] pr-[1vw] border-neutral-700"
		>
			<input
				v-model="q"
				class="w-full text-[26px] bg-transparent pl-[5%] text-2xl focus:outline-none"
				type="text"
				@keydown.enter="enterClicked"
			/>
			<img :src="Cross" class="h-[3vh] px-3 cursor-pointer" @click="clearInput" />
		</div>
		<nuxt-link
			v-if="q.length != 0"
			class="w-max h-max max-[450px]:px-[2vw] sm:px-[0.5vw] py-[1vh] text-lg bg-neutral-700 rounded-full"
			:to="{ path: '/search?q=', query: { q } }"
			@click="loading = true"
		>
			<div
				class="flex place-content-around place-items-center text-2xl max-[450px]:gap-x-[3vw] sm:gap-x-[2vw] px-[2vw]"
			>
				<img :src="Bean" class="h-[3vh]" />
				<div>Search</div>
			</div>
		</nuxt-link>
		<nuxt-link
			v-else
			class="w-max h-max max-[450px]:px-[2vw] sm:px-[0.5vw] py-[1vh] text-lg bg-neutral-700 rounded-full"
			to="/"
		>
			<div
				class="flex place-content-around place-items-center text-2xl max-[450px]:gap-x-[3vw] sm:gap-x-[2vw] px-[2vw]"
			>
				<img :src="Bean" class="h-[3vh]" />
				<div>Search</div>
			</div>
		</nuxt-link>
	</div>
</template>

<script setup>
import Logo from '~/assets/icons/logo.svg?url'
import Cross from '~/assets/icons/cross.svg?url'
import Bean from '~/assets/icons/bean.svg?url'

const q = ref('')
const clearInput = () => (q.value = '')
const loading = ref(false)

const enterClicked = () => {
	if (q.value.length !== 0) {
		loading.value = true
		useRouter().push('/search?q=' + q.value)
	}
}
</script>

<style lang="scss" scoped></style>
