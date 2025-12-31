<script setup>
import { ref, onMounted } from 'vue'
import { RouterView } from 'vue-router'

const message = ref('')

onMounted(async () => {
  try {
    const response = await fetch('http://localhost:8080/api/hello')
    message.value = await response.text()
  } catch (err) {
    message.value = 'Failed to fetch'
  }
})
</script>

<template>
  <div>
    <p>{{ message }}</p>   <!-- show fetched message -->
    <RouterView />         <!-- render current route component -->
  </div>
</template>
