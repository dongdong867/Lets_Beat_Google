<template>
	<div>
		<NuxtPage />
	</div>
</template>

<script setup>
import axios from 'axios'

axios.defaults.headers.get['Accesss-Control-Allow-Origin'] = '*'
axios.defaults.headers.get['Access-Control-Allow-Method'] = 'GET, POST, DELETE, PUT'
axios.defaults.headers.get['Access-Control-Allow-Headers'] =
	'X-Requested-With, Content-Type, Authorization, Origin, Accept'
</script>

<style lang="scss" scoped></style>
