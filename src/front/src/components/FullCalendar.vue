<template>
  <a class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:shadow-outline">
    <router-link to="/dashboard">Back to Dashboard</router-link>
  </a>
  <FullCalendar :options="calendarOptions"/>
</template>

<script>
import FullCalendar from '@fullcalendar/vue3'
import dayGridPlugin from '@fullcalendar/daygrid'
import interactionPlugin from '@fullcalendar/interaction'
import router from "@/router";
import { formatDate } from '@fullcalendar/core'


export default {
  components: {
    FullCalendar
  },

  data: () => ({
    lst: [],
    calendarOptions: {
      plugins: [dayGridPlugin, interactionPlugin],
      initialView: 'dayGridMonth',
      editable: false,
      selectable: true,
      select: (arg) => {
        fetch("/event/save", {
          method: 'POST',
          headers: {"Content-Type": "application/json"},
          body: JSON.stringify({
            user: JSON.parse(localStorage.getItem("user")),
            title: "default event name",
            start: formatDate(arg.start, {
              year: "numeric",
              month: "2-digit",
              day: "2-digit",
              timeZoneName: "short",
              hour: "2-digit",
              minute: "2-digit",
              second: "2-digit",
              meridiem: false
            }),
            end: formatDate(arg.end, {
              year: "numeric",
              month: "2-digit",
              day: "2-digit",
              timeZoneName: "short",
              hour: "2-digit",
              minute: "2-digit",
              second: "2-digit",
              meridiem: false
            }),
            info: "default info"
          })
        }).then(res => res.json())
            .then(data => {
              const cal = arg.view.calendar
              cal.unselect()
              cal.addEvent({
                id: data.id,
                title: "New event, click to modify",
                start: arg.start,
                end: arg.end,
                allDay: true
              })
            })
      },

      eventClick: (arg) => {
        const eventIdOnClick = JSON.stringify(arg.event)
        localStorage.setItem('eventIdOnClick', eventIdOnClick)
        router.push('EventModify')
      },

      events: fetch("/event/all/" + JSON.parse(localStorage.getItem('user')), {
        method:'GET',
        headers: {"Content-Type": "application/json"},
      }).then( res =>  res.json())
          .then(data => {
            let lst = []
            for (let i = 0; i < data.length; i++) {
              lst.push(JSON.parse(JSON.stringify(data[i])))
            }
            return lst
          })
    }
  }),

    methods: {
      notRegisteredYet() {
        const username = JSON.parse(localStorage.getItem('user'));
        if (username === null) {
          router.push("Connexion")
        }
      },

      beforeMount() {
        this.notRegisteredYet()
      }
    }
  }
</script>