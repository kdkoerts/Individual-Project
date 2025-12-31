<script setup>
import { ref, onMounted } from 'vue'

const message = ref('')

onMounted(async () => {
  const response = await fetch('http://localhost:8080/api/hello')
  message.value = await response.text()
})
</script>

<template>
  <h1>{{ message }}</h1>
</template>
