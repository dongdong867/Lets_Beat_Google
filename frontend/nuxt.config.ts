import { NuxtConfig } from 'nuxt/config'
import svgloader from 'vite-svg-loader'

// https://v3.nuxtjs.org/api/configuration/nuxt.config
export default defineNuxtConfig({
	modules: ['@nuxtjs/tailwindcss'],
	vite: {
		plugins: [svgloader()]
	},
	typescript: {
		typeCheck: true
	},
	srcDir: 'src/'
} as NuxtConfig)
