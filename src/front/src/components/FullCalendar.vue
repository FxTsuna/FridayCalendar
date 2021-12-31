<template>
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

          console.log(formatDate(arg.start, {year: "numeric", month: "2-digit", day: "2-digit", timeZoneName:"short", hour:"2-digit", minute:"2-digit", second:"2-digit", meridiem: false}))

          fetch("/event/save", {
            method: 'POST',
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({title: "default event name",
              start: formatDate(arg.start, {year: "numeric", month: "2-digit", day: "2-digit", timeZoneName:"short", hour:"2-digit", minute:"2-digit", second:"2-digit", meridiem: false}),
              end: formatDate(arg.end, {year: "numeric", month: "2-digit", day: "2-digit", timeZoneName:"short", hour:"2-digit", minute:"2-digit", second:"2-digit", meridiem: false}),
              info: "default info"})
          }).then((res => {
            if (res.status === 201) {
              const cal = arg.view.calendar
              cal.unselect()
              cal.addEvent({
                id: "",
                title:"New event, click to modify",
                start: arg.start,
                end: arg.end,
                allDay: true
              })
              //fetch("event/get l'id somehow et le mettre dans event")
            }
          }))
        },

        eventClick: (arg) => {
          console.log(arg)
          const event = JSON.stringify(arg.event.id);
          localStorage.setItem('event', event)
          router.push('EventModify')
        },

        events: [
          { title: 'event 1', start: '2022-01-01', end: '2022-01-05' },
          { title: 'event 2', start: '2022-01-02' }
        ]},

  }),
  methods: {
  }
}
</script>

<style scoped>
</style>