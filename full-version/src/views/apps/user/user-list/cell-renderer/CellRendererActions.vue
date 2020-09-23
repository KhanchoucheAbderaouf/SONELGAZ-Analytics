<template>
    <div :style="{'direction': $vs.rtl ? 'rtl' : 'ltr'}">
      <feather-icon icon="Edit3Icon" svgClasses="h-5 w-5 mr-4 hover:text-primary cursor-pointer" @click="editRecord" />
      <feather-icon icon="Trash2Icon" svgClasses="h-5 w-5 hover:text-danger cursor-pointer" @click="confirmDeleteRecord" />
    </div>
</template>

<script>
    export default {
        name: 'CellRendererActions',
        methods: {
          editRecord() {
            this.$router.push("/apps/user/user-edit/" + this.params.data.iduser).catch(() => {})

            /*
              Below line will be for actual product
              Currently it's commented due to demo purpose - Above url is for demo purpose

              this.$router.push("/apps/user/user-edit/" + this.params.data.id).catch(() => {})
            */
          },
          confirmDeleteRecord() {
            this.$vs.dialog({
              type: 'confirm',
              color: 'danger',
              title: `Confirm Delete`,
              text: `You are about to delete "${this.params.data.username}"`,
              accept: this.deleteRecord,
              acceptText: "Supprimer"
            })
          },
          deleteRecord() {
            /* Below two lines are just for demo purpose */
            

            /* UnComment below lines for enabling true flow if deleting user */
            this.$http.delete("http://localhost:8087/users/deleteUser/"+ this.params.data.iduser ,{headers : {'Authorization' :"Bearer "  + localStorage.accessToken}})
               .then(()   => { this.showDeleteSuccess() ;
                 this.$http.get('http://localhost:8087/users/allUsers' ,{headers : {'Authorization' :"Bearer "  + localStorage.accessToken}}).then((result) => {
        this.$root.$emit('myEvent', result.data);          
       });
               })
               .catch(err => { console.error(err)       })
          },
          showDeleteSuccess() {
            this.$vs.notify({
              color: 'success',
              title: 'User Deleted',
              text: 'The selected user was successfully deleted'
            })
          }
        }
    }
</script>
