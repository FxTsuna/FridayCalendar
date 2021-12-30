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

          console.log(arg.start + ' to ' + arg.end)
/*
          fetch("/event/save", {
            method: 'POST',
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({title: "default event name", dateStart: arg.start, dateEnd: arg.end, info: "default info"})
          }).then((res => {
            if (res.status === 201) {
              fetch("event/get l'id somehow et le mettre dans event")
            }
          }))


 */
          const cal = arg.view.calendar
          cal.unselect()
          cal.addEvent({
            id: "",
            title:"ui",
            start: arg.start,
            end: arg.end,
            allDay: true
          })

        },

        eventClick: (arg) => {
          console.log(arg)
          const event = JSON.stringify(arg.event);
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