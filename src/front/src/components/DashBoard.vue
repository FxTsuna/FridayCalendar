<template>
  <div>
    <div class="flex place-items-center">
      <!-- buttons -->
      <div class="text-center ml-auto mr-20">
        <button class="p-3 pl-5 pr-5 bg-indigo-500 hover:bg-blue-400 text-gray-100 text-lg rounded-lg focus:border-4 border-indigo-300" @click="deleteAccount">Delete account</button>
      </div>

    </div>
    <div class="flex place-items-center">
      <div class="text-center ml-auto mr-20 my-1">
      <button class="p-3 pl-5 pr-5 bg-indigo-500 hover:bg-blue-400 text-gray-100 text-lg rounded-lg focus:border-4 border-indigo-300">
        <router-link to="/updatePassword">Change password</router-link>
      </button>
      </div>
    </div>
    <div class="flex place-items-center">
      <div class="text-center ml-auto mr-20">
        <button class="p-3 pl-5 pr-5 bg-indigo-500 hover:bg-blue-400 text-gray-100 text-lg rounded-lg focus:border-4 border-indigo-300" @click="logOut">
          Log out
        </button>
      </div>
    </div>
  <!-- Next rdv -->
    <div class="flex-content max-w-screen-lg bg-purple-500 shadow-2xl rounded-lg mx-auto text-center py-12 mt-4">
      <h2 class="text-3xl leading-9 font-bold tracking-tight text-white sm:text-4xl sm:leading-10">
        Prochain rdv + info intelligente
      </h2>
      <div class="mt-8 flex justify-center">
        <div class="inline-flex rounded-md bg-white shadow">
          <a class="text-gray-700 font-bold py-2 px-6" @click="getNextRendezVous">
            {{ title }}
          </a>
        </div>
      </div>
    </div>

    <!-- Liste rdv -->
    <div class="flex-content max-w-screen-lg bg-purple-500 shadow-2xl rounded-lg mx-auto text-center py-12 mt-4">
      <h2 class="text-3xl leading-9 font-bold tracking-tight text-white sm:text-4xl sm:leading-10">
        Liste des rendez-vous du jour
      </h2>
      <div class="mt-8 flex justify-center">
        <div class="inline-flex rounded-md bg-white shadow">
          <a class="text-gray-700 font-bold py-2 px-6" @click="getDayRendezVous">
            Liste de rdv de la journee par ordre chrono
          </a>
        </div>
      </div>
    </div>

    <!-- Calendar -->
    <div class="flex-content max-w-screen-lg bg-purple-500 shadow-2xl rounded-lg mx-auto text-center py-12 mt-4">
      <h2 class="text-3xl leading-9 font-bold tracking-tight text-white sm:text-4xl sm:leading-10">
        Calendrier des rendez-vous du mois
      </h2>
      <div class="mt-8 flex justify-center">
        <div class="inline-flex rounded-md bg-blue-500 shadow">
          <a class="text-gray-200 font-bold py-2 px-6">
            <router-link to="/fullcalendar">Vers le calendrier</router-link>
          </a>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import router from "@/router";

export default {
  name: 'DashBoard',

  data: () => ({
    title: "",
    dateStart: "",
    info:""
  }),

  methods: {

    deleteAccount() {
      const username = JSON.parse(localStorage.getItem('user'));
      fetch("/user/delete/" + username,
          {
            method: 'DELETE',
            headers: {"Content-Type": "application/json"},
          }).then(res => {
            if (res.status === 200) {
              router.push("Connexion")
            }
          }
      )
    },

    getNextRendezVous() {
      const username = JSON.parse(localStorage.getItem('user'));
      fetch("/user/get/recent/" + username, {
        method:'GET',
        headers: {"Content-Type": "application/json"},
      }).then( res => res.json())
      .then(data => {
        this.dateStart = data.start
        this.title = data.title
        this.info = data.info
      })
    },

    getDayRendezVous() {
      const username = JSON.parse(localStorage.getItem('user'));
      fetch("/user/get/day/" + username, {
        method:'GET',
        headers: {"Content-Type": "application/json"},
      }).then(res => res.json())
      .then(data => {
        console.log(data)
      })
    },

    notRegisteredYet() {
      const username = JSON.parse(localStorage.getItem('user'));
      if (username === null) {
        router.push("Connexion")
      }
    },
    logOut() {
      localStorage.setItem('user', null)
      router.push("/")
    }
  },



  mounted() {
    this.notRegisteredYet()
    this.getNextRendezVous()
    this.getDayRendezVous()
  }
}



</script>