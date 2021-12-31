<template>
  <a class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:shadow-outline">
    <router-link to="/dashboard">Back to Dashboard</router-link>
  </a>
  <FullCalendar :options="calendarOptions"/>
</template>

<script>
//import { reactive } from "vue";
//import '@fullcalendar/core/vdom' // solves problem with Vite
import FullCalendar from '@fullcalendar/vue3'
import dayGridPlugin from '@fullcalendar/daygrid'
import interactionPlugin from '@fullcalendar/interaction'
import router from "@/router";
import { formatDate } from '@fullcalendar/core'
//import EventModal from './ModalComponent'

export default {
  components: {
    FullCalendar // make the <FullCalendar> tag available
  },
  data: () => ({
      calendarOptions: {
        plugins: [ dayGridPlugin, interactionPlugin ],
        initialView: 'dayGridMonth',
        editable: true,
        selectable: true,
        select: (arg) => {

          console.log(formatDate(arg.start, {year: "numeric", month: "2-digit", day: "2-digit", timeZoneName:"short", hour:"2-digit", minute:"2-digit", second:"2-digit", meridiem: false}) + ' to ' +
          formatDate(arg.end, {year: "numeric", month: "2-digit", day: "2-digit", timeZoneName:"short", hour:"2-digit", minute:"2-digit", second:"2-digit", meridiem: false}))

          fetch("/event/save", {
            method: 'POST',
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({title: "default event name",
              start: formatDate(arg.start, {year: "numeric", month: "2-digit", day: "2-digit", timeZoneName:"short", hour:"2-digit", minute:"2-digit", second:"2-digit", meridiem: false}),
              end: formatDate(arg.end, {year: "numeric", month: "2-digit", day: "2-digit", timeZoneName:"short", hour:"2-digit", minute:"2-digit", second:"2-digit", meridiem: false}),
              info: "default info"})
          }).then(res => res.json())
              .then(data => {
                const eventIdOnSave = JSON.stringify(data.id)
                localStorage.setItem('eventIdOnSave', eventIdOnSave)
                const cal = arg.view.calendar
                cal.unselect()
                cal.addEvent({
                  id: data.id,
                  title:"New event, click to modify",
                  start: arg.start,
                  end: arg.end,
                  allDay: true
                })
          })
        },

        eventClick: (arg) => {
          const eventIdOnClick = JSON.stringify(arg.event.id)
          localStorage.setItem('eventIdOnClick', eventIdOnClick)
          router.push('EventModify')
        },

        events: [
          fetch("/event/get/" + JSON.parse(localStorage.getItem('user')), {
            method:'GET',
            headers: {"Content-Type": "application/json"},
          }).then(res => {
            if (res.status === 200) {
              console.log("test 1"+ res.json())
              console.log("test 2"+ res)
            }
          })
        ]},

  }),
/*
  methods:{
    onPage() {
      const eventId = JSON.parse(localStorage.getItem('eventIdOnSave'))
      fetch("/event/get/" + eventId, {
        method:'GET',
        headers: {"Content-Type": "application/json"},
      }).then(res => {
        if (res.status === 200) {
          console.log("test 1"+ res.json())
          console.log("test 2"+ res)
        }
      })
    }
  },

  beforeMount() {
    this.onPage()
  }

 */
  methods: {
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