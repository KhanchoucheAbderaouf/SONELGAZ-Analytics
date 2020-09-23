

<template>
  <div id="user-add-tab-info">

   

    <!-- Content Row -->
    <div class="vx-row">
      <div class="vx-col md:w-1/2 w-full">
        <vs-input class="w-full mt-4" label="Username" v-model="data_local.username" v-validate="'required|alpha_num'" name="username" />
        <span class="text-danger text-sm"  v-show="errors.has('username')">{{ errors.first('username') }}</span>

      <vs-input class="w-full mt-4" type="password" label="Password" v-model="data_local.password" v-validate="'required|alpha_num'" name="password" />
        <span class="text-danger text-sm"  v-show="errors.has('password')">{{ errors.first('password') }}</span>

      
        <vs-input class="w-full mt-4" label="Name" v-model="data_local.nom" v-validate="'required|alpha_spaces'" name="nom" />
        <span class="text-danger text-sm"  v-show="errors.has('nom')">{{ errors.first('nom') }}</span>

      <vs-input class="w-full mt-4" label="Prenom" v-model="data_local.prenom" v-validate="'required|alpha_spaces'" name="prenom" />
        <span class="text-danger text-sm"  v-show="errors.has('prenom')">{{ errors.first('prenom') }}</span>
      
      </div>

      <div class="vx-col md:w-1/2 w-full">
        <vs-input class="w-full mt-4" label="Email" v-model="data_local.email" type="email" v-validate="'required|email'" name="email" />
        <span class="text-danger text-sm"  v-show="errors.has('email')">{{ errors.first('email') }}</span>

       <vs-input class="w-full mt-4" label="Telephone" v-model="data_local.telephone"  v-validate="'required|alpha_num'" name="telephone" />
        <span class="text-danger text-sm"  v-show="errors.has('telephone')">{{ errors.first('telephone') }}</span>

        <div class="mt-4">
          <label class="vs-input--label">Organisme</label>
          <v-select  :filter="fuseSearch" :options="organismes" v-model="organisme" :getOptionLabel="option => option.type_organisme">
            <template  #option="{ nom_pole,nom_unite,nom_centrale,num_grpe, type_organisme }">
              <cite>{{ type_organisme}}</cite>
              <p v-if="type_organisme==='Pole'"> {{ nom_pole  }}</p>
              <p v-if="type_organisme==='Unite'"> {{ nom_unite  }}</p>
              <p v-if="type_organisme==='Centrale'"> {{ nom_centrale  }}</p>
              <p v-if="type_organisme==='Groupe'"> {{ nom_centrale+ "-"+num_grpe }}</p>
              <br />
            </template>
          </v-select>
        </div>
        <div class="mt-4">
          <label class="vs-input--label">Role</label>
          <v-select v-model="data_local.role" :clearable="false" :options="roleOptions" v-validate="'required'" name="role" :dir="$vs.rtl ? 'rtl' : 'ltr'" />
          <span class="text-danger text-sm"  v-show="errors.has('role')">{{ errors.first('role') }}</span>
        </div>
        
        <!--<vs-input class="w-full mt-4" label="Company" v-model="data_local.company" v-validate="'alpha_spaces'" name="company" />
        <span class="text-danger text-sm"  v-show="errors.has('company')">{{ errors.first('company') }}</span>!-->

      </div>
    </div>

    <!-- Permissions 
    <vx-card class="mt-base" no-shadow card-border>

      <div class="vx-row">
        <div class="vx-col w-full">
          <div class="flex items-end px-3">
            <feather-icon svgClasses="w-6 h-6" icon="LockIcon" class="mr-2" />
            <span class="font-medium text-lg leading-none">Permissions</span>
          </div>
          <vs-divider />
        </div>
      </div>

      <div class="block overflow-x-auto">
        <table class="w-full">
          <tr>
           
            <th class="font-semibold text-base text-left px-3 py-2" v-for="heading in ['Module', 'Read', 'Write', 'Create', 'Delete']" :key="heading">{{ heading }}</th>
          </tr>

          <tr v-for="(val, name) in data_local.permissions" :key="name">
            <td class="px-3 py-2">{{ name }}</td>
            <td v-for="(permission, name) in val" class="px-3 py-2" :key="name+permission">
              <vs-checkbox v-model="val[name]" />
            </td>
          </tr>
        </table>
      </div>

    </vx-card>-->

    <!-- Save & Reset Button -->
    <div class="vx-row">
      <div class="vx-col w-full">
        <div class="mt-8 flex flex-wrap items-center justify-end">
          <vs-button class="ml-4 mt-2" color="danger" @click="retour" >Retour</vs-button>
          <vs-button class="ml-auto mt-2" type="border" color="warning" @click="reset_data">Reset</vs-button>
          <vs-button class="ml-4 mt-2" @click="save_changes" :disabled="!validateForm">Sauvgarder</vs-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import vSelect from 'vue-select'
import Fuse from "fuse.js"
export default {
  components: {
    vSelect
  },
  props: {
    data: {
      type: Object,
      required: true,
    },
  },
  data() {
    return {
      organisme:null,
      organismes:[],
      data_local: JSON.parse(JSON.stringify(this.data)),

      statusOptions: [
        { label: "Active",  value: true },
        { label: "Deactivated",  value: false },
      ],
      roleOptions: [
        { label: "Admin",  value: "admin" },
        { label: "Utilisateur Simple",  value: "simple_user" },
      ],
    }
  },
  computed: {
    status_local: {
      get() {
        return { label: this.capitalize(this.data_local.status),  value: this.data_local.status  }
      },
      set(obj) {
        this.data_local.status = obj.value
      }
    },
    role_local: {
      get() {
        return { label: this.capitalize(this.data_local.role.intitule),  value: this.data_local.role.intitule  }
      },
      set(obj) {
        this.data_local.role.intitule = obj.value
      }
    },
    validateForm() {
      return !this.errors.any()
    }
  },
  methods: {
    capitalize(str) {
      return str.slice(0,1).toUpperCase() + str.slice(1,str.length)
    },
     fuseSearch(options, search) {
      const fuse = new Fuse(options, {
        keys: ["type_organisme","nom_pole", "nom_unite","nom_centrale","num_grpe"],
        shouldSort: true
      });
      return search.length
        ? fuse.search(search).map(({ item }) => item)
        : fuse.list;
    },
    retour(){
 this.$router.push("/apps/user/user-list");
    },
    save_changes() {
      if(!this.validateForm) return

      // Here will go your API call for updating data
      // You can get data in "this.data_local"
     this.data_local.idorganism=this.organisme.code_organisme;
     this.$http.post('http://localhost:8087/users/addUser/'+this.data_local.role.value,{'username':this.data_local.username,'password':this.data_local.password,'nom':this.data_local.nom,'prenom':this.data_local.prenom,'telephone':this.data_local.telephone,'email':this.data_local.email,'idorganism':this.data_local.idorganism},
     {headers : {'Authorization' :"Bearer "  + localStorage.accessToken}})
        .then(this.$vs.notify({
              color: 'success',
              title: 'User Added',
              text: 'The  user was successfully Added'
            }),
      this.$router.push("/apps/user/user-list")
            ).catch(error => {
        this.$vs.loading.close();
         this.$vs.notify({
        title: ' Requet erroné  ',
        text: error,
        color: 'danger'
      })
      });
    },
    reset_data() {
      this.data_local = JSON.parse(JSON.stringify(this.data))
    },
    update_avatar() {
      // You can update avatar Here
      // For reference you can check dataList example
      // We haven't integrated it here, because data isn't saved in DB
    }
  },
  mounted(){
    this.$http.get('http://localhost:8087/requests/select * from bi.dim_organisme' ,{headers : {'Authorization' :"Bearer "  + localStorage.accessToken}}).then((result) => {
          this.organismes=  result.data;
       });

         this.$http.get('http://localhost:8087/requests/organism/code_organisme = '+this.data_local.idorganism,{headers : {'Authorization' :"Bearer "  + localStorage.accessToken}}).then((result) => {
          this.organisme= result.data;
       })
       
  }
}
</script>
