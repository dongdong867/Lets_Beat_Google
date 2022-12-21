<template>
	<div class="w-screen h-screen flex flex-col place-content-center place-items-center gap-y-[5vh]">
		<div class="flex place-items-end gap-x-3 select-none cursor-default">
			<img :src="Logo" class="w-[10vw]" />
			<div class="text-6xl font-bold">Let's Beat Google</div>
		</div>
		<div
			class="w-[60vw] h-[5vh] bg-neutral-800 rounded-full flex place-content-around border-[2px] border-neutral-700"
		>
			<input
				v-model="q"
				class="w-[55vw] text-3xl bg-transparent pl-[2vw] focus:outline-none"
				type="text"
			/>
			<img :src="Cross" class="w-[4vh] pr-[1vw] p-[0.5vh] cursor-pointer" @click="clearInput" />
		</div>
		<nuxt-link
			v-if="q.length != 0"
			class="w-max h-max px-[1.5vw] py-[1vh] text-lg bg-neutral-700 rounded-full"
			:to="{
				path: '/search',
				query: { q }
			}"
		>
			<div class="flex place-content-around place-items-center text-2xl gap-x-[1vw]">
				<img :src="Search" class="w-[2vw]" />
				<div>Search</div>
			</div>
		</nuxt-link>
		<nuxt-link
			v-else
			class="w-max h-max px-[1.5vw] py-[1vh] text-lg bg-neutral-700 rounded-full"
			to="/"
		>
			<div class="flex place-content-around place-items-center text-2xl gap-x-[1vw]">
				<img :src="Search" class="w-[2vw]" />
				<div>Search</div>
			</div>
		</nuxt-link>
	</div>
</template>

<script setup>
import Logo from '~/assets/icons/logo.svg?url'
import Cross from '~/assets/icons/cross.svg?url'
import Search from '~/assets/icons/search.svg?url'

const q = ref('')
const clearInput = () => (q.value = '')
</script>

<style lang="scss" scoped></style>
