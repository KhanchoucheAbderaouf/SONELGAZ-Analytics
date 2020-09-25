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
      <vs-button class="ml-auto mt-2" @click="submitForm">Sauvegarder</vs-button>
     
    </div>
  </vx-card>
</template>

<script>
import firebase from 'firebase/app'
import 'firebase/auth'

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
            
     this.logout(),

       

        
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
    },
      logout() {

          // if user is logged in via auth0
        if (this.$auth.profile) this.$auth.logOut();

        // if user is logged in via firebase
        const firebaseCurrentUser = firebase.auth().currentUser

        if (firebaseCurrentUser) {
            firebase.auth().signOut().then(() => {
                this.$router.push('/pages/login').catch(() => {})
            })
        }
        // If JWT login
        if(localStorage.getItem("accessToken")) {
          localStorage.removeItem("accessToken");
           this.$auth.localelogOut();

          this.$router.push('/pages/login').catch(() => {});
        }

        // Change role on logout. Same value as initialRole of acj.js
        this.$acl.change('admin')
        localStorage.removeItem('userInfo');

        // This is just for demo Purpose. If user clicks on logout -> redirect
        this.$router.push('/pages/login').catch(() => {});
        alert("tttt2");
    },
  },
  
  computed: {
    activeUserInfo() {
      return this.$store.state.AppActiveUser
    },
  }
}
</script>
