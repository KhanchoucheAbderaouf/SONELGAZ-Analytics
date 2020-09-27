

<template>
  <div id="dashboard-reports" class="data-list-container">

    <data-view-sidebar :isSidebarActive="addNewDataSidebar" @closeSidebar="toggleDataSidebar" :data="sidebarData" />

    <vs-table v-show="showRequestCreater" ref="table" multiple v-model="selected" pagination :max-items="itemsPerPage" search :data="products">

      <div slot="header" class="flex flex-wrap-reverse items-center flex-grow justify-between">

        <div class="flex flex-wrap-reverse items-center data-list-btn-container">

        

          <!-- ADD NEW -->
          <div class="btn-add-new p-3 mb-4 mr-4 rounded-lg cursor-pointer flex items-center justify-center text-lg font-medium text-base text-primary border border-solid border-primary" @click="activePrompt3=true">
              <feather-icon icon="PlusIcon" svgClasses="h-4 w-4" />
              <span class="ml-2 text-base text-primary">Ajouter Rapport</span>
          </div>
        </div>

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
                <p class="product-price">{{ tr.titre }}</p>
              </vs-td>

              <vs-td class="whitespace-no-wrap">
                <feather-icon icon="ShareIcon" v-if="tr.creator==$store.state.AppActiveUser.username" svgClasses="w-5 h-5 hover:text-primary stroke-current" @click.stop="editData(tr)" />
                <feather-icon icon="EyeIcon"  svgClasses="w-5 h-5 hover:text-dark stroke-current" class="ml-2" @click.stop="createTable(tr)" />
                 <feather-icon icon="SlashIcon" v-if="tr.creator==$store.state.AppActiveUser.username" svgClasses="w-5 h-5 hover:text-warning stroke-current" class="ml-2" @click.stop="supprimePermission(tr)" />
               <feather-icon icon="TrashIcon" v-if="tr.creator==$store.state.AppActiveUser.username" svgClasses="w-5 h-5 hover:text-danger stroke-current" class="ml-2" @click.stop="supprimeRapport(tr)" />
              </vs-td>

            </vs-tr>
          </tbody>
        </template>
    </vs-table>
      <!--prompt requet Begin -->
     <vs-prompt title="Create Report" class="export-options" @cancle="clearFields" @accept="saveReport" accept-text="Save" @close="clearFields" :active.sync="activePrompt3">
         <vs-input label-placeholder="Title" v-model="titreRaport"/>
        
    </vs-prompt>
    <!--prompt requet End -->
           <!--prompt Excel Begin -->
        <vs-prompt title="Exporter les Résultats" class="export-options" @cancle="clearFields" @accept="exportToExcel" accept-text="Exporter" @close="clearFields" :active.sync="activePrompt">
        <vs-input v-model="fileName" placeholder="Enter File Name.." class="w-full" />
        <v-select v-model="selectedFormat" :options="formats" class="my-4" />
        <div class="flex">
          <span class="mr-4">Cell Auto Width:</span>
          <vs-switch v-model="cellAutoWidth">Cell Auto Width</vs-switch>
        </div>
    </vs-prompt>
    <!--prompt Excel End -->

    
    <div v-for="table in tableau_des_resultas" >
           <vx-card >
           
<!--prompt Graphe Begin -->
     <vs-prompt title="Créer des Graphes" class="export-options" @cancle="clearFields" @accept="createGraphe(table)" accept-text="créer" @close="clearFields" :active.sync="table.prom">
        <v-select v-model="attributeGraphe" :options="table.operationGraphe"  class="my-4" />
        <v-select v-model="DimGraphe" :options="table.setsGraphe" label="value" class="my-4" />
        <v-select v-model="table.typeGraphe" :options="typesGraphes" class="my-4" />
        
    </vs-prompt>
    <!--prompt Graphe End -->
   


    <div  class="export-table">
      <vs-table :data="table.tableData" v-model="selectedUsers" pagination max-items="10" multiple search>

        <template slot="header" >
          <vs-button @click="activePrompt=true" style="margin-right:30px;">Exporter les Résultats</vs-button>
        </template>
        <template slot="header">
          <vs-button style="margin-right:30px;" @click="table.prom=true">Créer des Graphes</vs-button>
        </template>
       
  <template slot="thead">
          <vs-th  v-for="heading in table.header" :key="heading" :sort-key="heading">{{ heading }}</vs-th>
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
    <div v-for="graphe in table.graphesCree">
     <vx-card v-if="graphe.showGraphe" >
        
          <vs-button style="margin:0 0 30px 30px;" @click=" graphe.showGraphe=false" class="bg-danger" >Supprimer</vs-button>
        
                    <vue-apex-charts :type="graphe.typeGraphe" height="700" :options="lineAreaChartSpline.chartOptions" :series="graphe.seriesLine"></vue-apex-charts>

                   
                </vx-card>
                <vx-card v-if="graphe.showGraphe2" >
        
          <vs-button style="margin:0 0 30px 30px;" @click=" graphe.showGraphe2=false" class="bg-danger" >Back</vs-button>
        
                    <vue-apex-charts type="pie" height="700" :options="pieChart.chartOptions" :series="graphe.seriesPie"></vue-apex-charts>
                    
                   
                </vx-card>
    </div>
     </div>
  </div>
</template>

<script>
import VueApexCharts from 'vue-apexcharts'
import DataViewSidebar from '@/views/ui-elements/data-list/DataViewSidebarRapport.vue'
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
      intergraphe:{typeGraphe:'area',seriesLine:[{
                name: 'series1',
                data:[],
            }],seriesPie:[],
            showGraphe:false,
            showGraphe2:false,},
      selected: [],
      products: [],
      itemsPerPage: 4,
      isMounted: false,
      activePrompt3:false,
      // Data Sidebar
      addNewDataSidebar: false,
      sidebarData: {},
     valueDorF:false,
      //data table resulta
          attributeGraphe:'',
          setsGraphe:[],
          DimGraphe:'',
          
          operationGrapheControle:['sum_','avg_','min_','max_'],
          operationGraphe:[],
          typesGraphes:["area","bar","pie"],
         showGraphe:false,
         showGraphe2:false,
         showTable:false,
         showRequestCreater:true,
        tableau_des_resultas:[],
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
      titreRaport:'',
      requetOlap:'',
      titreRequet:'',
      header : [],
      tableData:[],
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
        this.$vs.loading();
  this.$http.get('http://localhost:8087/rapports/addRapport/' + this.$store.state.AppActiveUser.uid + '/' + this.titreRaport,{headers : {'Authorization' :"Bearer "  + localStorage.accessToken}}).then((result) => {
         this.$vs.loading.close();
         this.$vs.notify({
        title: ' Report saved ',
        text: this.titreRequet,
        color: 'success'
      }),
       this.$http.get('http://localhost:8087/users/rapports/'+this.$store.state.AppActiveUser.uid,{headers : {'Authorization' :"Bearer "  + localStorage.accessToken}})
      .then((result) => {this.products=result.data;
      });
       }).catch(error => {
        this.$vs.loading.close();
         this.$vs.notify({
        title: ' Report Not Saved  ',
        text: error,
        color: 'danger'
      })
      });

      },
    deleteData(id) {
      this.$store.dispatch("dataList/removeItem", id).catch(err => { console.error(err) })
    },
    supprimeRapport(tr){
       this.$vs.loading();
         this.$http.delete('http://localhost:8087/rapports/deleteRapport/'+tr.idrapport,{headers : {'Authorization' :"Bearer "  + localStorage.accessToken}})
      .then((result) => {
        this.$vs.loading.close();
            
        this.$vs.notify({
          
        title: ' rapport supprimer avec succés  ',
        text: 'votre requet a été supprimer avec succès',
        color: 'success'
      })
       this.$http.get('http://localhost:8087/users/rapports/'+this.$store.state.AppActiveUser.uid,{headers : {'Authorization' :"Bearer "  + localStorage.accessToken}})
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
    editData(data) {
      // this.sidebarData = JSON.parse(JSON.stringify(this.blankData))
      this.sidebarData = data
      this.toggleDataSidebar(true)
    },
    supprimePermission(tr){
      this.$vs.loading();
       this.$http.delete('http://localhost:8087/rapports/deleteAuthorizationOthers/'+tr.idrapport,{headers : {'Authorization' :"Bearer "  + localStorage.accessToken}})
      .then((result) => {
        this.$vs.loading.close();
            
        this.$vs.notify({
          
        title: ' Requet envoyé  ',
        text: 'votre requet a été envoyé avec succès',
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
       createGraphe:function(table){
        var value=false;
       
       this.lineAreaChartSpline.chartOptions.labels=[];
       this.lineAreaChartSpline.series[0].data=[];
       this.pieChart.chartOptions.labels=[];
       this.pieChart.series=[];
      
        //console.log(this.attributeGraphe);
        var contrlerIndex=table.header.indexOf(this.DimGraphe);
        var contrlerGraphe=table.header[contrlerIndex+1];
         
        table.operationGraphe.forEach(op => {

          //if next is an operation
       if(contrlerGraphe.includes(op.toLowerCase())){
              value=true;
              
            }
        });
     
      
      
          if(value){
            table.tableData.forEach(element2=>{
              if(element2[this.DimGraphe].includes("ALL")===false){ 
              if(table.typeGraphe=="pie"){
          this.pieChart.chartOptions.labels.push(element2[this.DimGraphe])  ;
          
          this.intergraphe.seriesPie.push(element2[contrlerGraphe]); 
              }else{
           this.lineAreaChartSpline.chartOptions.labels.push(element2[this.DimGraphe])  ;   
          this.intergraphe.seriesLine[0].data.push(element2[this.attributeGraphe.toLowerCase()]); } }  
          console.log(element2[this.attributeGraphe.toLowerCase()]);
           });
          }
          else{
              table.tableData.forEach(element3=>{
            if(element3[this.DimGraphe].includes("ALL")===false){   
            if(element3[contrlerGraphe].includes("ALL")){
              if(table.typeGraphe=="pie"){
          this.pieChart.chartOptions.labels.push(element3[this.DimGraphe]);  

          this.intergraphe.seriesPie.push(element3[this.attributeGraphe.toLowerCase()]);
          
          
              }else{

          this.lineAreaChartSpline.chartOptions.labels.push(element3[this.DimGraphe]);   
          this.intergraphe.seriesLine[0].data.push(element3[this.attributeGraphe.toLowerCase()]);} } }  
           console.log(element3[this.attributeGraphe.toLowerCase()]);
           });

          }
        if(table.typeGraphe=="pie"){

        
        this.intergraphe.showGraphe2=true;this.intergraphe.typeGraphe="pie"; }else{
        this.intergraphe.showGraphe=true;this.intergraphe.typeGraphe=table.typeGraphe;
        };
       
        table.graphesCree.push(this.intergraphe);
        this.intergraphe={typeGraphe:'area',seriesLine:[{
                name: 'series1',
                data:[],
            }],seriesPie:[],
            showGraphe:false,
            showGraphe2:false,};
            
        this.showTable=false;


        
      },
      createTable(tr){
       this.tableau_des_resultas=[];
       
        tr.listQueries.forEach(val => {
            this.$vs.loading();
             this.$http.get('http://localhost:8087/mongo/getOneResult/'+val.titre,{headers : {'Authorization' :"Bearer "  + localStorage.accessToken}})
      .then((result) => {
      this.$vs.loading.close();
            
        this.$vs.notify({
          
        title: ' Requet envoyé  ',
        text: 'votre requet a été envoyé avec succès',
        color: 'success'
      }),

      this.showRequestCreater=false;
      var  resulta={header:Object.getOwnPropertyNames(result.data[0]),tableData : result.data};

        
        
     
      //this.showTable=true;
     // this.showRequestCreater=false;
         resulta.header.forEach(h => {
         var valueDorF=false;
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
         
        })
        resulta={header:Object.getOwnPropertyNames(result.data[0]),tableData : result.data,operationGraphe:this.operationGraphe,setsGraphe:this.setsGraphe,
        prom:false,showGraphe2:false,showGraphe:false,typeGraphe:'area',seriesLine:[{
                name: 'series1',
                data:[],
            }],seriesPie:[],graphesCree:[],};
       this.operationGraphe=[];
       this.setsGraphe=[];
       this.tableau_des_resultas.push(resulta);
      }).catch(error => {
         this.$http.get('http://localhost:8087/requests/'+val.valeur,{headers : {'Authorization' :"Bearer "  + localStorage.accessToken}})
      .then((result) => {
        this.$vs.loading.close();
          this.$vs.notify({
          
        title: ' Requet envoyé  ',
        text: 'votre requet a été envoyé avec succès',
        color: 'success'
      }),

      this.showRequestCreater=false;
      var  resulta={header:Object.getOwnPropertyNames(result.data[0]),tableData : result.data};

        this.$http.post('http://localhost:8087/mongo/addResult',{title:val.titre,JsonAnswer: resulta.tableData},{headers : {'Authorization' :"Bearer "  + localStorage.accessToken}}).then((result) => {        
       
       });
        
     
      //this.showTable=true;
     // this.showRequestCreater=false;
         resulta.header.forEach(h => {
         var valueDorF=false;
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
         
        })
        resulta={header:Object.getOwnPropertyNames(result.data[0]),tableData : result.data,operationGraphe:this.operationGraphe,setsGraphe:this.setsGraphe,
        prom:false,showGraphe2:false,showGraphe:false,typeGraphe:'area',seriesLine:[{
                name: 'series1',
                data:[],
            }],seriesPie:[],graphesCree:[],};
       this.operationGraphe=[];
       this.setsGraphe=[];
       this.tableau_des_resultas.push(resulta);
      }).catch(error => {});

      });
       
      

      });
    //end
      console.log(this.tableau_des_resultas);
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
    this.$http.get('http://localhost:8087/users/rapports/'+this.$store.state.AppActiveUser.uid,{headers : {'Authorization' :"Bearer "  + localStorage.accessToken}})
      .then((result) => {this.products=result.data;
      console.log(this.products);
      });
    
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
