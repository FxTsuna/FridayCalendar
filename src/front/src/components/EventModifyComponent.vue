<template>
  <div class="flex justify-center h-screen items-center bg-gray-200 antialiased">
    <div class="flex flex-col w-11/12 sm:w-5/6 lg:w-1/2 max-w-2xl mx-auto border border-gray-300 shadow-xl">
      <div class="flex flex-col px-6 py-5 bg-gray-50">
        <p class="mb-2 font-semibold text-gray-700">Event Title</p>
        <input class="w-full mb-5 p-5 bg-white border border-gray-200 rounded shadow-sm appearance-none" type="text" placeholder="Title event" v-model="title">
        <p class="mb-2 font-semibold text-gray-700">Event info</p>
        <textarea type="text" v-model="info" placeholder="Type message..." class="p-5 mb-5 bg-white border border-gray-200 rounded shadow-sm h-36" id=""></textarea>
        <div class="flex flex-col sm:flex-row items-center mb-5 sm:space-x-5">
          <div class="w-full sm:w-1/2">
            <p class="mb-2 font-semibold text-gray-700">Start event</p>
            <input class="w-full p-5 bg-white border border-gray-200 rounded shadow-sm appearance-none" type="text" placeholder="YYYY/MM/DD" v-model="eventStart" required>
          </div>
          <div class="w-full sm:w-1/2 mt-2 sm:mt-0">
            <p class="mb-2 font-semibold text-gray-700">End Event</p>
            <input class="w-full p-5 bg-white border border-gray-200 rounded shadow-sm appearance-none" type="text" placeholder="YYYY/MM/DD" v-model="eventEnd" required>
          </div>
        </div>
        <p class="mb-2 font-semibold text-gray-700">Event recurrence</p>
        <input class="w-full p-5 bg-white border border-gray-200 rounded shadow-sm appearance-none" type="text" placeholder="Pas encore implémenté">
      </div>
      <div class="flex flex-row items-center justify-between p-5 bg-white border-t border-gray-200 rounded-bl-lg rounded-br-lg">
        <p class="font-semibold text-gray-600">Cancel</p>
        <button class="px-4 py-2 text-white font-semibold bg-blue-500 rounded">
          Delete
        </button>
        <button class="px-4 py-2 text-white font-semibold bg-blue-500 rounded">
          Save
        </button>

      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "EventModifyComponent",
  data: () => ({
    title: "",
    info: "",
    eventStart: "",
    eventEnd: ""
  }),

  methods: {

  }
}
</script>

<style scoped>

</style>