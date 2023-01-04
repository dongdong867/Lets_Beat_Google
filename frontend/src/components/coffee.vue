<template>
	<div class="h-screen flex flex-col place-content-center place-items-center">
		<img :src="bean" class="w-[20vw] animate-[bean-animation_1.5s_linear_infinite] m-[10vh]" />
		<div class="text-5xl font-bold">LOADING...</div>
	</div>
</template>

<script setup>
import bean from '~/assets/icons/bean.svg?url'
</script>

<style>
@keyframes bean-animation {
	0%,
	10% {
		transform: scale(1) rotate(0deg);
	}
	15% {
		transform: scale(1.2) rotate(0deg);
	}
	30% {
		transform: scale(1.2) rotate(-40deg);
	}
	50% {
		transform: scale(1.2) rotate(180deg);
	}
	65% {
		transform: scale(1.2) rotate(400deg);
	}
	75%,
	80% {
		transform: scale(1.2) rotate(360deg);
	}
	90%,
	100% {
		transform: scale(1) rotate(360deg);
	}
}
</style>
