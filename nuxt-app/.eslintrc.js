module.exports = {
  env: {
    browser: true,
    es2021: true
  },
  extends: ['plugin:vue/vue3-essential', 'standard-with-typescript', 'eslint-config-prettier'],
  overrides: [],
  parserOptions: {
    ecmaVersion: 'latest',
    sourceType: 'module',
    project: 'nuxt-app/tsconfig.json'
  },
  plugins: ['vue'],
  rules: {}
}
