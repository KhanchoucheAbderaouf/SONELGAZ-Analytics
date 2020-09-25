<template>
  <vx-card no-shadow>

    <vs-input class="w-full mb-base" label-placeholder="username" v-validate="'required'" v-model="username" />
    <vs-input class="w-full mb-base"  ref="password"  type="password" data-vv-validate-on="blur" v-validate="'required'" name="password"
     label-placeholder="New Password"  v-model="new_password" />
    <vs-input class="w-full mb-base" type="password"
      v-validate="'confirmed:password'"
      data-vv-validate-on="blur"
      data-vv-as="password"
      name="confirm_password"
      label-placeholder="Confirm Password"  v-model="confirm_new_password" />

    <!-- Save & Reset Button -->
    <div class="flex flex-wrap items-center justify-end">
      <vs-button class="ml-auto mt-2" @click="submitForm">Save Changes</vs-button>
     
    </div>
  </vx-card>
</template>

<script>
export default {
  data() {
    return {
      username: "",
      new_password: "",
      confirm_new_password: "",
    }
  },
    methods: {
    submitForm() {
      this.$validator.validateAll().then(result => {
        if (result) {
          this.$http.put('http://localhost:8087/users/updateUserNamePass/'+this.$store.state.AppActiveUser.uid,{'username':this.username,'password':this.confirm_new_password},
     {headers : {'Authorization' :"Bearer "  + localStorage.accessToken}})
        .then(this.$vs.notify({
              color: 'success',
              title: 'User Updated',
              text: 'The selected user was successfully Updated'
            }),
           this.$router.push("/dashboard/ecommerce") 
            ).catch(error => {
        this.$vs.loading.close();
         this.$vs.notify({
        title: ' Requet erroné  ',
        text: error,
        color: 'danger'
      })
      });
         
        } else {
          // form have errors
        }
      })
    }
  },
  computed: {
    activeUserInfo() {
      return this.$store.state.AppActiveUser
    },
  }
}
</script>
