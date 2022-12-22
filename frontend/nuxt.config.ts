import { NuxtConfig } from 'nuxt/config'
import svgloader from 'vite-svg-loader'

// https://v3.nuxtjs.org/api/configuration/nuxt.config
export default defineNuxtConfig({
	modules: ['@nuxtjs/tailwindcss', ['@nuxtjs/axios', { autoImports: ['defineStore'] }]],
	vite: {
		plugins: [svgloader()]
	},
	typescript: {
		typeCheck: true
	},
	srcDir: 'src/',
	axios: {},
	nitro: { present: 'firebase' },
	css: ['~/assets/css/transition.css']
} as NuxtConfig)
