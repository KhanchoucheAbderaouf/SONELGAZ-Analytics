<!-- =========================================================================================
  File Name: AddNewDataSidebar.vue
  Description: Add New Data - Sidebar component
  ----------------------------------------------------------------------------------------
  Item Name: Vuexy - Vuejs, HTML & Laravel Admin Dashboard Template
  Author: Pixinvent
  Author URL: http://www.themeforest.net/user/pixinvent
========================================================================================== -->


<template>
  <vs-sidebar click-not-close position-right parent="body" default-index="1" color="primary" class="add-new-data-sidebar items-no-padding" spacer v-model="isSidebarActiveLocal">
    <div class="mt-6 flex items-center justify-between px-6">
        <h4>Partage Requet</h4>
        <feather-icon icon="XIcon" @click.stop="isSidebarActiveLocal = false" class="cursor-pointer"></feather-icon>
    </div>
    <vs-divider class="mb-0"></vs-divider>

    <VuePerfectScrollbar class="scroll-area--data-list-add-new" :settings="settings" :key="$vs.rtl">

      <div class="p-6">


     

        <!-- CATEGORY -->
        <div class="flex">
          <span class="mr-4"> users:</span>
          <vs-switch v-model="partageUser">partager par users</vs-switch>
        </div>
        <div  v-show="partageUser">
       
        <v-select label="username" name="users" :options="Users_choices" class="mt-5 w-full" v-model="dataUser" :dir="$vs.rtl ? 'rtl' : 'ltr'" />
        <br><br>
        </div>

       <!-- ORDER STATUS -->
        <div class="flex">
          <span class="mr-4"> organisme:</span>
          <vs-switch v-model="partageOrganisme">partager par organisme</vs-switch>
        </div>
        <div  v-show="partageOrganisme">
       
       <v-select name="organismes" class="mt-5 w-full" :filter="fuseSearch" :options="Organismes_choices" v-model="dataOrganisme" :getOptionLabel="option => option.type_organisme">
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
        

       

        
      </div>
    </VuePerfectScrollbar>

    <div class="flex flex-wrap items-center p-6" slot="footer">
      <vs-button class="mr-6" @click="submitData" :disabled="!isFormValid">Submit</vs-button>
      <vs-button type="border" color="danger" @click="isSidebarActiveLocal = false">Cancel</vs-button>
    </div>
  </vs-sidebar>
</template>

<script>
import VuePerfectScrollbar from 'vue-perfect-scrollbar'
import vSelect from 'vue-select'
import Fuse from "fuse.js"
export default {
  props: {
    isSidebarActive: {
      type: Boolean,
      required: true
    },
    data: {
      type: Object,
      default: () => {},
    }
  },
  watch: {
    isSidebarActive(val) {
        this.$http.get('http://localhost:8087/users/allUsers',{headers : {'Authorization' :"Bearer "  + localStorage.accessToken}})
      .then((result) => {
       ;
            
        this.$vs.notify({
          
        title: ' Requet all user envoyé  ',
        text: 'votre requet a été envoyé avec succès',
        color: 'success'
      })
 this.Users_choices=result.data;
      }).catch(error => {
         this.$vs.notify({
        title: ' Requet all user erroné  ',
        text: error,
        color: 'danger'
      })
      });


         this.$http.get('http://localhost:8087/requests/select * from bi.dim_organisme',{headers : {'Authorization' :"Bearer "  + localStorage.accessToken}})
      .then((result) => {
       ;
            
        this.$vs.notify({
          
        title: ' Requet all organisme envoyé  ',
        text: 'votre requet a été envoyé avec succès',
        color: 'success'
      })
 this.Organismes_choices=result.data;
      }).catch(error => {
         this.$vs.notify({
        title: ' Requet all organisme erroné  ',
        text: error,
        color: 'danger'
      })
      });
      if(!val) return
      if(Object.entries(this.data).length === 0) {
        this.initValues()
        this.$validator.reset()
      }else {
        
        this.dataId = this.data.idrapport;
       console.log(this.dataId);
       
        
      }
      // Object.entries(this.data).length === 0 ? this.initValues() : { this.dataId, this.dataName, this.dataCategory, this.dataOrder_status, this.dataPrice } = JSON.parse(JSON.stringify(this.data))
    }
  },
  data() {
    return {

      dataId: null,
    partageOrganisme:false,
    partageUser:false,
      dataUser: null,
      
      dataOrganisme: null,
      

      Users_choices: [],

      Organismes_choices: [],
      settings: { // perfectscrollbar settings
          maxScrollbarLength: 60,
          wheelSpeed: .60,
      },
    }
  },
  computed: {
    isSidebarActiveLocal: {
      get() {
        return this.isSidebarActive
      },
      set(val) {
        if(!val) {
          this.$emit('closeSidebar')
          // this.$validator.reset()
          // this.initValues()
        }
      }
    },
    isFormValid() {
      return !this.errors.any() && (this.dataUser || this.dataOrganisme) 
    }
  },
  methods: {
    initValues() {
      if(this.data.id) return
        this.dataId = null
        
        this.dataUser= null
      
      this.dataOrganisme=null
       
    },
    submitData() {
      this.$validator.validateAll().then(result => {
          if (result) {
            

            if(this.partageUser) {
                    this.$http.get('http://localhost:8087/rapports/addRapportUser/'+this.dataId+'/'+this.dataUser.iduser,{headers : {'Authorization' :"Bearer "  + localStorage.accessToken}})
                          .then((result) => {   
                            ;

                            this.$vs.notify({
                              
                            title: ' Requet partage user envoyé  ',
                            text: 'votre requet a été envoyé avec succès',
                            color: 'success'
                          })
                    
                          }).catch(error => {
                            this.$vs.notify({
                            title: ' Requet partage user erroné  ',
                            text: error,
                            color: 'danger'
                          })
                    });
            };
            if(this.partageOrganisme){
               this.$http.get('http://localhost:8087/rapports/addRapportOrganism/'+this.dataId+'/'+this.dataOrganisme.code_organisme,{headers : {'Authorization' :"Bearer "  + localStorage.accessToken}})
                          .then((result) => {
                          ;
                                
                            this.$vs.notify({
                              
                            title: ' Requet partage organisme envoyé  ',
                            text: 'votre requet a été envoyé avec succès',
                            color: 'success'
                          })
                    
                          }).catch(error => {
                            this.$vs.notify({
                            title: ' Requet partage organisme erroné  ',
                            text: error,
                            color: 'danger'
                          })
                    });
            }

            this.$emit('closeSidebar')
            this.initValues()
          }
      })
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
   
    
  },
  components: {
    VuePerfectScrollbar,
    vSelect,
  }
}
</script>

<style lang="scss" scoped>
.add-new-data-sidebar {
  ::v-deep .vs-sidebar--background {
    z-index: 52010;
  }

  ::v-deep .vs-sidebar {
    z-index: 52010;
    width: 400px;
    max-width: 90vw;

    .img-upload {
      margin-top: 2rem;

      .con-img-upload {
        padding: 0;
      }

      .con-input-upload {
        width: 100%;
        margin: 0;
      }
    }
  }
}

.scroll-area--data-list-add-new {
    // height: calc(var(--vh, 1vh) * 100 - 4.3rem);
    height: calc(var(--vh, 1vh) * 100 - 16px - 45px - 82px);
}
</style>
