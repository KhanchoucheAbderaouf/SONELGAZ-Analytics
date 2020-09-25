

<template>
  <div id="dashboard-analytics" class="data-list-container">

    <data-view-sidebar :isSidebarActive="addNewDataSidebar" @closeSidebar="toggleDataSidebar" :data="sidebarData" />

    <vs-table v-show="showRequestCreater" ref="table" multiple v-model="selected" pagination :max-items="itemsPerPage" search :data="products">

      <div slot="header" class="flex flex-wrap-reverse items-center flex-grow justify-between">

        

        <!-- ITEMS PER PAGE -->
        <vs-dropdown vs-trigger-click class="cursor-pointer mb-4 mr-4 items-per-page-handler">
          <div class="p-4 border border-solid d-theme-border-grey-light rounded-full d-theme-dark-bg cursor-pointer flex items-center justify-between font-medium">
            <span class="mr-2">{{ currentPage * itemsPerPage - (itemsPerPage - 1) }} - {{ products.length - currentPage * itemsPerPage > 0 ? currentPage * itemsPerPage : products.length }} of {{ queriedItems }}</span>
            <feather-icon icon="ChevronDownIcon" svgClasses="h-4 w-4" />
          </div>
          <!-- <vs-button class="btn-drop" type="line" color="primary" icon-pack="feather" icon="icon-chevron-down"></vs-button> -->
          <vs-dropdown-menu>

            <vs-dropdown-item @click="itemsPerPage=4">
              <span>4</span>
            </vs-dropdown-item>
            <vs-dropdown-item @click="itemsPerPage=10">
              <span>10</span>
            </vs-dropdown-item>
            <vs-dropdown-item @click="itemsPerPage=15">
              <span>15</span>
            </vs-dropdown-item>
            <vs-dropdown-item @click="itemsPerPage=20">
              <span>20</span>
            </vs-dropdown-item>
          </vs-dropdown-menu>
        </vs-dropdown>
      </div>

      <template slot="thead">
        <vs-th sort-key="name">Createur</vs-th>
        <vs-th sort-key="category">Date De Creation</vs-th>
       
        <vs-th sort-key="price">Titre</vs-th>
        <vs-th>Action</vs-th>
      </template>

        <template slot-scope="{data}">
          <tbody>
            <vs-tr :data="tr" :key="indextr" v-for="(tr, indextr) in data">

              <vs-td>
                <p class="product-name font-medium truncate">{{ tr.creator }}</p>
              </vs-td>

              <vs-td>
                <p class="product-category">{{ tr.date_creation }}</p>
              </vs-td>

              

              

              <vs-td>
                <p class="product-price">{{ tr.titre }}</p>
              </vs-td>

              <vs-td class="whitespace-no-wrap">
                <feather-icon icon="ShareIcon" v-if="tr.creator==$store.state.AppActiveUser.username" svgClasses="w-5 h-5 hover:text-primary stroke-current" @click.stop="editData(tr)" />
                <feather-icon icon="EyeIcon"  svgClasses="w-5 h-5 hover:text-dark stroke-current" class="ml-2" @click.stop="createTable(tr)" />
                <feather-icon icon="FolderPlusIcon"  svgClasses="w-5 h-5 hover:text-success stroke-current" class="ml-2" @click.stop="activePrompt4=true,queryRapport=tr.idquery" />
                 <feather-icon icon="SlashIcon" v-if="tr.creator==$store.state.AppActiveUser.username" svgClasses="w-5 h-5 hover:text-warning stroke-current" class="ml-2" @click.stop="supprimePermission(tr)" />
                  <feather-icon icon="TrashIcon" v-if="tr.creator==$store.state.AppActiveUser.username" svgClasses="w-5 h-5 hover:text-danger stroke-current" class="ml-2" @click.stop="supprimeRequet(tr)" />
              </vs-td>

            </vs-tr>
          </tbody>
        </template>
    </vs-table>
           <vx-card v-show="showTable">
             <!--prompt requet Begin -->
     <vs-prompt title="Create Report" class="export-options" @cancle="clearFields" @accept="saveReport" accept-text="Save" @close="clearFields" :active.sync="activePrompt3">
         <vs-input label-placeholder="Title" v-model="titreRaport"/>
        
    </vs-prompt>
    <!--prompt requet End -->
         <!--prompt requet Begin -->
     <vs-prompt title="insert into Report" class="export-options" @cancle="clearFields" @accept="addToRapport" accept-text="Save" @close="clearFields" :active.sync="activePrompt4">
         <v-select v-model="rapport" :options="rapports" class="my-4" label="titre" />
        
    </vs-prompt>
    <!--prompt requet End -->
           <!--prompt Excel Begin -->
        <vs-prompt title="Exporter les résultats" class="export-options" @cancle="clearFields" @accept="exportToExcel" accept-text="Exporter" @close="clearFields" :active.sync="activePrompt">
        <vs-input v-model="fileName" placeholder="Enter File Name.." class="w-full" />
        <v-select v-model="selectedFormat" :options="formats" class="my-4" />
        <div class="flex">
          <span class="mr-4">Cell Auto Width:</span>
          <vs-switch v-model="cellAutoWidth">Cell Auto Width</vs-switch>
        </div>
    </vs-prompt>
    <!--prompt Excel End -->

    <!--prompt Graphe Begin -->
     <vs-prompt title="Créer des Graphes" class="export-options" @cancle="clearFields" @accept="createGraphe" accept-text="Créer" @close="clearFields" :active.sync="activePrompt2">
        <v-select v-model="attributeGraphe" :options="operationGraphe"  class="my-4" />
        <v-select v-model="DimGraphe" :options="setsGraphe" label="value" class="my-4" />
        <v-select v-model="typeGraphe" :options="typesGraphes" class="my-4" />
        
    </vs-prompt>
    <!--prompt Graphe End -->

   


    <div v-show="showTable" class="export-table">
      <vs-table :data="tableData" v-model="selectedUsers" pagination max-items="10" multiple search>

        <template slot="header" >
          <vs-button @click="activePrompt=true" style="margin-right:30px;">Exporter les Résultats</vs-button>
        </template>
        <template slot="header">
          <vs-button style="margin-right:30px;" @click="activePrompt2=true">Créer des Graphes</vs-button>
        </template>
        <template slot="header">
          <vs-button class="bg-danger" @click="showRequestCreater=true, showTable=false">Retour aux requêtes</vs-button>
        </template>
  <template slot="thead">
          <vs-th  v-for="heading in header" :key="heading" :sort-key="heading">{{ heading }}</vs-th>
  </template>
          <template slot-scope="{data}">
          <vs-tr :data="tr" :key="indextr" v-for="(tr, indextr) in data">
            <vs-td :data="col" v-for="(col, indexcol) in data[indextr]" :key="indexcol + col">
              {{ col }}
            </vs-td>
          </vs-tr>
          </template>
        
      </vs-table>
      </div>
     
    </vx-card>
      <vx-card v-if="showGraphe" >
        
          <vs-button style="margin:0 0 30px 30px;" @click="showTable=true, showGraphe=false" class="bg-danger" >Back</vs-button>
        
                    <vue-apex-charts :type="typeGraphe" height="500" :options="lineAreaChartSpline.chartOptions" :series="lineAreaChartSpline.series"></vue-apex-charts>

                   
                </vx-card>
                <vx-card v-if="showGraphe2" >
        
          <vs-button style="margin:0 0 30px 30px;" @click="showTable=true, showGraphe2=false" class="bg-danger" >Back</vs-button>
        
                    <vue-apex-charts type="pie" height="500" :options="pieChart.chartOptions" :series="pieChart.series"></vue-apex-charts>
                    
                   
                </vx-card>
  </div>
</template>

<script>
import VueApexCharts from 'vue-apexcharts'
import DataViewSidebar from '@/views/ui-elements/data-list/DataViewSidebar.vue'
import moduleDataList from "@/store/data-list/moduleDataList.js"
import vSelect from 'vue-select'

export default {
  components: {
    DataViewSidebar,
     VueApexCharts,
     vSelect,
  },
  data() {
    return {
      selected: [],
      products: [],
      itemsPerPage: 4,
      isMounted: false,
      activePrompt3:false,
      // Data Sidebar
      addNewDataSidebar: false,
      sidebarData: {},
      queryRapport:null,
      //data table resulta
          attributeGraphe:'',
          setsGraphe:[],
          DimGraphe:'',
          typeGraphe:'area',
          operationGrapheControle:['sum_','avg_','min_','max_'],
          operationGraphe:[],
          typesGraphes:["area","bar","pie"],
         showGraphe:false,
         showGraphe2:false,
         showTable:false,
         showRequestCreater:true,

 lineAreaChartSpline: {
        series: [{
                name: 'series1',
                data:[],
            }],
        chartOptions: {
            dataLabels: {enabled: false},
            labels:[],
            stroke: {
                curve: 'smooth'
            },
            colors: ['#7367F0', '#28C76F', '#EA5455', '#FF9F43', '#1E1E1E'],
           // xaxis: {categories: []},
      

            }
        },
         pieChart: {
        series:[],
        chartOptions: {
            labels: [],
            colors: ['#7367F0', '#28C76F', '#EA5455', '#FF9F43', '#1E1E1E'],
            responsive: [{
                breakpoint: 480,
                options: {
                    chart: {
                        width: 200
                    },
                    legend: {
                        position: 'bottom'
                    }
                }
            }]
        }
    },
    



          fileName: "",
      formats:["xlsx", "csv", "txt"] ,
      cellAutoWidth: true,
      selectedFormat: "xlsx",
      selectedUsers: [],
      activePrompt: false,
      activePrompt2: false,
      activePrompt4: false,
      titreRaport:'',
      requetOlap:'',
      titreRequet:'',
      header : [],
      tableData:[],
      rapports:[],
      rapport:null,
    }
  },
  computed: {
    currentPage() {
      if(this.isMounted) {
        return this.$refs.table.currentx
      }
      return 0
    },
  
    queriedItems() {
      return this.$refs.table ? this.$refs.table.queriedResults.length : this.products.length
    }
  },
  methods: {
    //pour le tableau de requet
 
        saveReport:function(){
        console.log(this.$store.state.AppActiveUser.uid);
        console.log(this.titreRaport);
        this.$vs.loading();
  this.$http.post('http://localhost:8087/rapports/addRapport/' + this.$store.state.AppActiveUser.uid,{titre:this.titreRaport},{headers : {'Authorization' :"Bearer "  + localStorage.accessToken}}).then((result) => {
         this.$vs.loading.close();
         this.$vs.notify({
        title: ' Rapport sauvgarder ',
        text: this.titreRequet,
        color: 'success'
      })
       }).catch(error => {
        this.$vs.loading.close();
         this.$vs.notify({
        title: ' Rapport No Sauvgarder  ',
        text: error,
        color: 'danger'
      })
      });

      },
    deleteData(id) {
      this.$store.dispatch("dataList/removeItem", id).catch(err => { console.error(err) })
    },
    editData(data) {
      // this.sidebarData = JSON.parse(JSON.stringify(this.blankData))
      this.sidebarData = data
      this.toggleDataSidebar(true)
    },
    addToRapport(){
       this.$http.get(' http://localhost:8087/rapports/addQueryRapport/'+this.rapport.idrapport+'/'+this.queryRapport,{headers : {'Authorization' :"Bearer "  + localStorage.accessToken}})
                          .then((result) => {
                          ;
                                
                            this.$vs.notify({
                              
                            title: ' Requete partager avec Rapport  ',
                            text: 'votre requet a été envoyé avec succès',
                            color: 'success'
                          })
                    
                          }).catch(error => {
                            this.$vs.notify({
                            title: ' Requet partage Rapport erroné  ',
                            text: error,
                            color: 'danger'
                          })
                    });
    },
    supprimePermission(tr){
      this.$vs.loading();
       this.$http.delete('http://localhost:8087/queries/deleteAuthorizationOthers/'+tr.idquery,{headers : {'Authorization' :"Bearer "  + localStorage.accessToken}})
      .then((result) => {
        this.$vs.loading.close();
            
        this.$vs.notify({
          
        title: ' Autorisation supprimer  avec  succès',
        text: 'votre requet a été supprimer avec succès',
        color: 'success'
      })
      }).catch(error => {
        this.$vs.loading.close();
         this.$vs.notify({
        title: ' Requet erroné  ',
        text: error,
        color: 'danger'
      })
      });
    },
    supprimeRequet(tr){
       this.$vs.loading();
         this.$http.delete('http://localhost:8087/queries/deleteQuery/'+tr.idquery,{headers : {'Authorization' :"Bearer "  + localStorage.accessToken}})
      .then((result) => {
        this.$vs.loading.close();
            
        this.$vs.notify({
          
        title: ' requete supprimer avec succés  ',
        text: 'votre requet a été supprimer avec succès',
        color: 'success'
      })
       this.$http.get('http://localhost:8087/users/queriesUser/'+this.$store.state.AppActiveUser.uid,{headers : {'Authorization' :"Bearer "  + localStorage.accessToken}})
      .then((result) => {this.products=result.data;
      });
      }).catch(error => {
        this.$vs.loading.close();
         this.$vs.notify({
        title: ' Requet erroné  ',
        text: error,
        color: 'danger'
      })
      });

    },
    getOrderStatusColor(status) {
      if(status == 'on_hold') return "warning"
      if(status == 'delivered') return "success"
      if(status == 'canceled') return "danger"
      return "primary"
    },
    getPopularityColor(num) {
      if(num > 90) return "success"
      if(num >70) return "primary"
      if(num >= 50) return "warning"
      if(num < 50) return "danger"
      return "primary"
    },
    toggleDataSidebar(val=false) {
      this.addNewDataSidebar = val
    },
      //pour le tableau des resultas
    exportToExcel() {
      import('@/vendor/Export2Excel').then(excel => {
        const list = this.selectedUsers
        const data = this.formatJson(this.header, list)
        excel.export_json_to_excel({
          header: this.header,
          data,
          filename: this.fileName,
          autoWidth: this.cellAutoWidth,
          bookType: this.selectedFormat
        })
        this.clearFields()
      })
    },
    formatJson(filterVal, jsonData) {
      return jsonData.map(v => filterVal.map(j => {
        // Add col name which needs to be translated
        // if (j === 'timestamp') {
        //   return parseTime(v[j])
        // } else {
        //   return v[j]
        // }

        return v[j]
      }))
    },
    clearFields() {
      this.fileName = ""
      this.cellAutoWidth = true
      this.selectedFormat = "xlsx"
    },
       createGraphe:function(){
        var value=false;
       
       this.lineAreaChartSpline.chartOptions.labels=[];
       this.lineAreaChartSpline.series[0].data=[];
       this.pieChart.chartOptions.labels=[];
       this.pieChart.series=[];
      // alert( this.attributeGraphe+";"+this.DimGraphe+";"+this.typeGraphe);
        //console.log(this.attributeGraphe);
        var contrlerIndex=this.header.indexOf(this.DimGraphe);
        var contrlerGraphe=this.header[contrlerIndex+1];
        
        this.operationGraphe.forEach(op => {

          //if next is an operation
       if(contrlerGraphe.includes(op.toLowerCase())){
              value=true;
              
            }
        });
     
      
      
          if(value){
            this.tableData.forEach(element2=>{
              if(element2[this.DimGraphe].includes("ALL")===false){ 
              if(this.typeGraphe=="pie"){
          this.pieChart.chartOptions.labels.push(element2[this.DimGraphe])  ;
          
          this.pieChart.series.push(element2[contrlerGraphe]); 
              }else{
           this.lineAreaChartSpline.chartOptions.labels.push(element2[this.DimGraphe])  ;   
          this.lineAreaChartSpline.series[0].data.push(element2[this.attributeGraphe.toLowerCase()]); } }  
           });
          }
          else{
               this.tableData.forEach(element3=>{
            if(element3[this.DimGraphe].includes("ALL")===false){   
            if(element3[contrlerGraphe].includes("ALL")){
              if(this.typeGraphe=="pie"){
          this.pieChart.chartOptions.labels.push(element3[this.DimGraphe]);   
          this.pieChart.series.push(element3[this.attributeGraphe.toLowerCase()]);
          
              }else{

          this.lineAreaChartSpline.chartOptions.labels.push(element3[this.DimGraphe]);   
          this.lineAreaChartSpline.series[0].data.push(element3[this.attributeGraphe.toLowerCase()]);} } }  
           });

          }
        if(this.typeGraphe=="pie"){

        
        this.showGraphe2=true;}else{
          this.showGraphe=true;
        }

        this.showTable=false;


        
      },
      createTable(tr){
        this.$vs.loading();
             this.$http.get('http://localhost:8087/mongo/getOneResult/'+tr.titre,{headers : {'Authorization' :"Bearer "  + localStorage.accessToken}})
      .then((result) => {
        this.$vs.loading.close(); 
        this.$vs.notify({
          
        title: ' resultas reçus',
        text: 'votre requet a été envoyé avec succès',
        color: 'success'
      })

      
      var valueDorF=false;
     
      console.log(result);
      this.header=Object.getOwnPropertyNames(result.data[0]); 
      this.tableData = result.data;       
      this.showTable=true;
      this.showRequestCreater=false;
      this.operationGraphe=[];
          this.setsGraphe=[];
         this.header.forEach(h => {
          valueDorF=false;
         this.operationGrapheControle.forEach(op => {
            
           if(h.includes(op.toLowerCase())){
              valueDorF=true;
              
            }
         
        });
         
          
        if (valueDorF===true){
          this.operationGraphe.push(h);
        }else{
          this.setsGraphe.push(h);
        }
         
        });
      }).catch(error => {

        this.$http.get('http://localhost:8087/requests/'+tr.valeur,{headers : {'Authorization' :"Bearer "  + localStorage.accessToken}})
      .then((result) => {
        this.$vs.loading.close();
        var valueDorF=false;
      this.header=Object.getOwnPropertyNames(result.data[0]); 
      this.tableData = result.data;  
      this.$http.post('http://localhost:8087/mongo/addResult',{title:tr.titre,JsonAnswer:this.tableData},{headers : {'Authorization' :"Bearer "  + localStorage.accessToken}}).then((result) => {        
       
       });
      console.log(this.tableData);     
      this.showTable=true;
      this.showRequestCreater=false;
      this.operationGraphe=[];
          this.setsGraphe=[];
         this.header.forEach(h => {
          valueDorF=false;
         this.operationGrapheControle.forEach(op => {
            
           if(h.includes(op.toLowerCase())){
              valueDorF=true;
              
            }
         
        });
        this.setsGraphe=[];
        if (valueDorF===true){
          this.operationGraphe.push(h);
        }else{
          this.setsGraphe.push(h);
        }
         
        });  
         this.$vs.notify({
          
        title: ' resultas reçus',
        text: 'votre requet a été envoyé avec succès',
        color: 'success'
      })
      });
      
      })
      }
  },
  created() {
    if(!moduleDataList.isRegistered) {
      this.$store.registerModule('dataList', moduleDataList)
      moduleDataList.isRegistered = true
    }
 
  },
  mounted() {
    this.isMounted = true;
    this.$http.get('http://localhost:8087/users/queriesUser/'+this.$store.state.AppActiveUser.uid,{headers : {'Authorization' :"Bearer "  + localStorage.accessToken}})
      .then((result) => {this.products=result.data;
      });
    this.$http.get('http://localhost:8087/users/rapports/'+this.$store.state.AppActiveUser.uid,{headers : {'Authorization' :"Bearer "  + localStorage.accessToken}})
      .then((result) => {this.rapports=result.data});
  },

}
</script>

<style lang="scss">
#data-list-list-view {
  .vs-con-table {

    /*
      Below media-queries is fix for responsiveness of action buttons
      Note: If you change action buttons or layout of this page, Please remove below style
    */
    @media (max-width: 689px) {
      .vs-table--search {
        margin-left: 0;
        max-width: unset;
        width: 100%;

        .vs-table--search-input {
          width: 100%;
        }
      }
    }

    @media (max-width: 461px) {
      .items-per-page-handler {
        display: none;
      }
    }

    @media (max-width: 341px) {
      .data-list-btn-container {
        width: 100%;

        .dd-actions,
        .btn-add-new {
          width: 100%;
          margin-right: 0 !important;
        }
      }
    }

    .product-name {
      max-width: 23rem;
    }

    .vs-table--header {
      display: flex;
      flex-wrap: wrap;
      margin-left: 1.5rem;
      margin-right: 1.5rem;
      > span {
        display: flex;
        flex-grow: 1;
      }

      .vs-table--search{
        padding-top: 0;

        .vs-table--search-input {
          padding: 0.9rem 2.5rem;
          font-size: 1rem;

          &+i {
            left: 1rem;
          }

          &:focus+i {
            left: 1rem;
          }
        }
      }
    }

    .vs-table {
      border-collapse: separate;
      border-spacing: 0 1.3rem;
      padding: 0 1rem;

      tr{
          box-shadow: 0 4px 20px 0 rgba(0,0,0,.05);
          td{
            padding: 20px;
            &:first-child{
              border-top-left-radius: .5rem;
              border-bottom-left-radius: .5rem;
            }
            &:last-child{
              border-top-right-radius: .5rem;
              border-bottom-right-radius: .5rem;
            }
          }
          td.td-check{
            padding: 20px !important;
          }
      }
    }

    .vs-table--thead{
      th {
        padding-top: 0;
        padding-bottom: 0;

        .vs-table-text{
          text-transform: uppercase;
          font-weight: 600;
        }
      }
      th.td-check{
        padding: 0 15px !important;
      }
      tr{
        background: none;
        box-shadow: none;
      }
    }

    .vs-table--pagination {
      justify-content: center;
    }
  }
}
</style>
