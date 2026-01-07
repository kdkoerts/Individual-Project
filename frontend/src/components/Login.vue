<template>
  <div>
    <h2>Login</h2>
    <form @submit.prevent="login">
      <input v-model="username" placeholder="Username" />
      <input v-model="password" type="password" placeholder="Password" />
      <button type="submit">Login</button>
    </form>
    <p>
      Don’t have an account?
      <RouterLink to="/register">Register here</RouterLink>
    </p>
    <p v-if="message">{{ message }}</p>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter, RouterLink } from 'vue-router'
import axios from 'axios'

const router = useRouter()
const username = ref('')
const password = ref('')
const message = ref('')

async function login() {
  try {
    await axios.post('http://localhost:8080/api/auth/login', {
      username: username.value,
      password: password.value
    }, {
      withCredentials: true
    })
    // On success, go to home
    router.push('/home')
  } catch (err) {
    message.value = err.response?.data || 'Login failed'
  }
}
</script>
