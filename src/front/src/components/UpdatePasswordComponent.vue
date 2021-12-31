<template>
  <form class="mx-auto max-w-xs">
    <div class="flex flex-wrap -mx-3 mb-6">
      <div class="w-full px-3">
        <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="password">
          New Password
        </label>
        <input class="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" id="password" type="password" placeholder="Password" v-model="password">
        <p class="text-gray-600 text-xs italic">Can't use special character or not utf-8 character</p>
      </div>
    </div>
    <div class="mb-9">
      <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="confirm">
        Confirm your password
      </label>
      <input class="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" id="confirm" type="password" placeholder="Confirm your password"  v-model="confirm" required>
    </div>
    <div class="flex items-center justify-between">
      <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:shadow-outline" type="button" @click="update();">
        Save
      </button>
      <a class="inline-block align-baseline font-bold text-sm text-blue-500 hover:text-blue-800">
        <router-link to="/dashboard">Cancel</router-link>
      </a>
    </div>
    <div v-if="booleanPassword" class="pt-1.5 text-red-500">Not matching password</div>
  </form>
</template>

<script>
import router from "@/router";

export default {
  name: "UpdatePasswordComponent",

  data: () => ({
    password: '',
    confirm: '',
    booleanPassword: false,
  }),

  methods: {
    update() {
      if (this.password === this.confirm) {
        const username = JSON.parse(localStorage.getItem('user'));
          fetch("/user/update",
            {
              method: 'PUT',
              headers: {"Content-Type": "application/json"},
              body: JSON.stringify({username: username, password: this.password})
            }).then(resp => {
              console.log(resp.json)
            if (resp.status === 201) {
              router.push("DashBoard")
            }
          })
        this.booleanPassword = false
      } else {
        this.booleanPassword = true
      }
    },
    notRegisteredYet() {
      const username = JSON.parse(localStorage.getItem('user'));
      fetch("/user/exist",
          {
            method: 'POST',
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({username: username, password: this.password})
          }).then(function (res) {
        if (res.status !== 200) {
          router.push("Connexion")
        }
      })
    }
  },

  beforeMount() {
    this.notRegisteredYet()
  }


}


</script>

<style scoped>

</style>