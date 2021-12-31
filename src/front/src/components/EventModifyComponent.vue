<template>
  <div class="flex justify-center h-screen items-center bg-gray-200 antialiased">
    <div class="flex flex-col w-11/12 sm:w-5/6 lg:w-1/2 max-w-2xl mx-auto border border-gray-300 shadow-xl">
      <div class="flex flex-col px-6 py-5 bg-gray-50">
        <p class="mb-2 font-semibold text-gray-700">Event Title</p>
        <input class="w-full mb-5 p-5 bg-white border border-gray-200 rounded shadow-sm appearance-none" type="text" v-model="title">
        <p class="mb-2 font-semibold text-gray-700">Event info</p>
        <textarea type="text" v-model="info" placeholder="Type message..." class="p-5 mb-5 bg-white border border-gray-200 rounded shadow-sm h-36"></textarea>
        <div class="flex flex-col sm:flex-row items-center mb-5 sm:space-x-5">
          <div class="w-full sm:w-1/2">
            <p class="mb-2 font-semibold text-gray-700">Start event</p>
            <input class="w-full p-5 bg-white border border-gray-200 rounded shadow-sm appearance-none" type="text" placeholder="YYYY/MM/DD hh:mm:ss" v-model="eventStart" required>
          </div>
          <div class="w-full sm:w-1/2 mt-2 sm:mt-0">
            <p class="mb-2 font-semibold text-gray-700">End Event</p>
            <input class="w-full p-5 bg-white border border-gray-200 rounded shadow-sm appearance-none" type="text" placeholder="YYYY/MM/DD hh:mm:ss" v-model="eventEnd" required>
          </div>
        </div>
        <p class="mb-2 font-semibold text-gray-700">Event recurrence</p>
        <input class="w-full p-5 bg-white border border-gray-200 rounded shadow-sm appearance-none" type="text" placeholder="Pas encore implémenté">
      </div>
      <div class="flex flex-row items-center justify-between p-5 bg-white border-t border-gray-200 rounded-bl-lg rounded-br-lg">
        <p class="font-semibold text-gray-600">
          <router-link to="FullCalendar">Cancel</router-link>
        </p>
        <button class="px-4 py-2 text-white font-semibold bg-blue-500 rounded" @click="deleteEvent">
          Delete
        </button>
        <button class="px-4 py-2 text-white font-semibold bg-blue-500 rounded" @click="updateEvent">
          Save
        </button>

      </div>
    </div>
  </div>
</template>

<script>

import router from "@/router";
import {formatDate} from "@fullcalendar/core";

export default {
  name: "EventModifyComponent",
  data: () => ({
    title: JSON.parse(localStorage.getItem('eventIdOnClick')).title,
    info: JSON.parse(localStorage.getItem('eventIdOnClick')).info,
    eventStart: formatDate(JSON.parse(localStorage.getItem('eventIdOnClick')).start, {year: "numeric", month: "2-digit", day: "2-digit", timeZoneName:"short", hour:"2-digit", minute:"2-digit", second:"2-digit", meridiem: false}),
    eventEnd: formatDate(JSON.parse(localStorage.getItem('eventIdOnClick')).end, {year: "numeric", month: "2-digit", day: "2-digit", timeZoneName:"short", hour:"2-digit", minute:"2-digit", second:"2-digit", meridiem: false}),
  }),

  methods: {
    deleteEvent() {
      const event = JSON.parse(localStorage.getItem('eventIdOnClick'));
      fetch("/event/delete/" + event.id, {
        method:'DELETE',
        headers: {"Content-Type": "application/json"},
      }).then(res => {
        if (res.status === 200) {
          router.push("FullCalendar");
        }
      })
    },

    updateEvent() {
      const event = JSON.parse(localStorage.getItem('eventIdOnClick'));
      const username = JSON.parse(localStorage.getItem('user'))
      fetch("/event/update/" + event.id, {
        method: 'PUT',
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({user : username ,title: this.title, info: this.info, start: this.eventStart , end: this.eventEnd })
      }).then(res => {
        if (res.status === 202) {
          router.push("FullCalendar");
        }
      })
    },

    notRegisteredYet() {
      const username = JSON.parse(localStorage.getItem('user'));
      if (username === null) {
        router.push("Connexion")
      }
    }
  },

  beforeMount() {
    this.notRegisteredYet()
  }
}
</script>

<style scoped>

</style>