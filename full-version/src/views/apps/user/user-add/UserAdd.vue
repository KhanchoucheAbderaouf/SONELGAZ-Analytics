

<template>
  <div id="page-user-add">

    <vs-alert color="danger" title="User Not Found" :active.sync="user_not_found">
      <span>User record with id: {{ $route.params.userId }} not found. </span>
      <span>
        <span>Check </span><router-link :to="{name:'page-user-list'}" class="text-inherit underline">All Users</router-link>
      </span>
    </vs-alert>

    <vx-card v-if="user_data">

      <div slot="no-body" class="tabs-container px-6 pt-6">

        <vs-tabs v-model="activeTab" class="tab-action-btn-fill-conatiner">
          <vs-tab label="Compte utilisateur" icon-pack="feather" icon="icon-user">
            <div class="tab-text">
              <user-add-tab-account class="mt-4" :data="user_data" />
            </div>
          </vs-tab>
          <!--<vs-tab label="Information" icon-pack="feather" icon="icon-info">
            <div class="tab-text">
              <user-edit-tab-information class="mt-4" :data="user_data" />
            </div>
          </vs-tab>
          <vs-tab label="Social" icon-pack="feather" icon="icon-share-2">
            <div class="tab-text">
              <user-edit-tab-social class="mt-4" :data="user_data" />
            </div>
          </vs-tab>-->
        </vs-tabs>

      </div>

    </vx-card>
  </div>
</template>

<script>
import UserAddTabAccount     from "./UserAddTabAccount.vue"

// Store Module
import moduleUserManagement from '@/store/user-management/moduleUserManagement.js'

export default {
  components: {
    UserAddTabAccount,
  },
  data() {
    return {
      user_data:  {
        "username": null,
        "password": null,
        "nom": null,
        "prenom": null,
        "telephone": null,
        "email": null,
        "idorganism": null,
        "role":null,
        },
      user_not_found: false,

      /*
        This property is created for fetching latest data from API when tab is changed

        Please check it's watcher
      */
      activeTab: 0,
    }
  },
 
}

</script>
