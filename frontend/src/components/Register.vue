<script setup>
import { ref } from 'vue'
import { useRouter, RouterLink } from 'vue-router'

const router = useRouter()
const firstname = ref('')
const lastName = ref('')
const email = ref('')
const username = ref('')
const password = ref('')
const message = ref('')

const register = async () => {
  try {
    const response = await fetch('http://localhost:8080/api/auth/register', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      credentials: 'include',
      body: JSON.stringify({
        firstname: firstname.value,
        lastName: lastName.value,
        email: email.value,
        username: username.value,
        password: password.value
      })
    })

    if (response.ok) {
      message.value = 'Registration successful! Redirecting to login...'
      // Brief pause so the user sees the success message
      setTimeout(() => router.push('/login'), 600)
    } else {
      const text = await response.text()
      message.value = text || 'Registration failed'
    }
  } catch (err) {
    message.value = 'Registration failed'
  }
}
</script>

<template>
  <div>
    <h2>Register</h2>
    <input v-model="firstname" placeholder="First Name" />
    <input v-model="lastName" placeholder="Last Name" />
    <input v-model="email" placeholder="Email" />
    <input v-model="username" placeholder="Username" />
    <input type="password" v-model="password" placeholder="Password" />
    <button @click="register">Register</button>
    <p>
      Already have an account?
      <RouterLink to="/login">Back to login</RouterLink>
    </p>
    <p>{{ message }}</p>
  </div>
</template>
