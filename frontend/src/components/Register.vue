<script setup>
import { ref } from 'vue'

const firstname = ref('')
const lastName = ref('')
const email = ref('')
const username = ref('')
const password = ref('')
const message = ref('')

const register = async () => {
  const response = await fetch('http://localhost:8080/api/auth/register', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({
      firstname: firstname.value,
      lastName: lastName.value,
      email: email.value,
      username: username.value,
      password: password.value
    })
  })

  if (response.ok) {
    message.value = 'Registration successful!'
  } else {
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
    <p>{{ message }}</p>
  </div>
</template>
