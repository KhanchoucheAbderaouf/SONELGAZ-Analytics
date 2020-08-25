<!-- =========================================================================================
    File Name: DragAndDropMultipleLists.vue
    Description: Drag and Drop to multiple lists
    ----------------------------------------------------------------------------------------
    Item Name: Vuexy - Vuejs, HTML & Laravel Admin Dashboard Template
      Author: Pixinvent
    Author URL: http://www.themeforest.net/user/pixinvent
========================================================================================== -->


<template>
 
      
    <div title="Multiple Lists">
    <vx-card v-show="showRequestCreater"  code-toggler>
        

        <!-- List 1 -->
        <div class="vx-row">
            <div class="vx-col w-full md:w-1/3">
                <vs-list>
                    <vs-list-header title="La List Des Attributes" color="primary"></vs-list-header>
                    <vs-collapse :type="type" accordion>
                      <vs-collapse-item class="bg-primary text-white"  >
                        <div slot="header">
                          fait_consommation
                        </div>
                    <draggable :list="list1" group="people" class="p-2 cursor-move">
                      <div v-for="(listItem, index) in list1" :key="index" >
                        <vs-list-item  v-if="listItem.TableFait==='fait_consommation'" :title="listItem.attributeName" ></vs-list-item>
                        </div>
                    </draggable>
                      </vs-collapse-item>


                      <vs-collapse-item class="bg-primary text-white" >
                        <div slot="header">
                          fait_production
                        </div>
                    <draggable :list="list1" group="people" class="p-2 cursor-move">
                      <div v-for="(listItem, index) in list1" :key="index" >
                        <vs-list-item  v-if="listItem.TableFait==='fait_production'" :title="listItem.attributeName" ></vs-list-item>
                        </div>
                    </draggable>
                      </vs-collapse-item>
                    </vs-collapse>
                </vs-list>
            </div>
            <div class="vx-col w-full md:w-1/3">
                <vs-list>
                    <vs-list-header title="Les Attributes Choisi" color="primary"></vs-list-header>
                    <draggable :list="list2" group="people" class="p-2 cursor-move">
                    <vs-list-item v-for="(listItem, index) in list2" :key="index" :title="listItem.attributeName" >
                      <vs-dropdown class="cursor-pointer flex" vs-custom-content>

                            <!--<feather-icon icon="InfoIcon" color="primary" svgClasses="h-7 w-7" @click.prevent></feather-icon>-->
                             <vs-button radius color="primary" type="flat" iconPack="feather" icon="icon-code" @click.prevent></vs-button> 

                            <vs-dropdown-menu style="z-index: 200001">
                                    <vs-dropdown-item v-for="(tag, index) in operationsets" :key="index">
                                        <vs-checkbox @click.stop  :vs-value="tag" v-model="listItem.operation">{{ tag }}</vs-checkbox>
                                    </vs-dropdown-item>
                            </vs-dropdown-menu>
                        </vs-dropdown>
                    </vs-list-item>
                    </draggable>
                </vs-list>
            </div>
            <div class="vx-col w-full md:w-1/3">
                <vs-list>
                    <vs-list-header title="La List Des Dimentions" color="primary"></vs-list-header>
                    
                     

  <vs-collapse :type="type" accordion>

    <vs-collapse-item  v-show="ck_cause">
      <div slot="header">
        Dim-cause
      </div>
      <v-select label="description" :options="causes" v-model="contraintes.cause" :dir="$vs.rtl ? 'rtl' : 'ltr'" />
      <br><br><br><br><br><br><br><br><br><br> <br><br><br><br><br><br><br><br><br><br>
    </vs-collapse-item>

    <vs-collapse-item v-show="ck_objectif">
      <div slot="header" >
        Dim-objectif
      </div><v-select label="code_objectif" v-model="contraintes.objectif" :options="objectif" :dir="$vs.rtl ? 'rtl' : 'ltr'" />
      <br><br><br><br><br><br><br><br><br><br> <br><br><br><br><br><br><br><br><br><br>
    </vs-collapse-item>

    <vs-collapse-item v-show="ck_organisme">
      <div slot="header">
        Dim-organisme
      </div>
     
    
  <v-select :filter="fuseSearch" :options="organismes" v-model="contraintes.organisme" :getOptionLabel="option => option.type_organisme">
    <template  #option="{ nom_pole,nom_unite,nom_centrale,num_grpe, type_organisme }">
      <cite>{{ type_organisme}}</cite>
     <p v-if="type_organisme==='Pole'"> {{ nom_pole  }}</p>
     <p v-if="type_organisme==='Unite'"> {{ nom_unite  }}</p>
     <p v-if="type_organisme==='Centrale'"> {{ nom_centrale  }}</p>
     <p v-if="type_organisme==='Groupe'"> {{ nom_centrale+ "-"+num_grpe }}</p>
      <br />
      
    </template>
    
  </v-select>

      <br><br><br><br><br><br><br><br><br><br> <br><br><br><br><br><br><br><br><br><br>
    </vs-collapse-item>

    <vs-collapse-item v-show="ck_regime_fct">
      <div slot="header" >
        Dim-regime-fct
      </div>
       <v-select label="description" :options="regime" v-model="contraintes.regimeFct" :dir="$vs.rtl ? 'rtl' : 'ltr'" />
      <br><br><br><br><br><br><br><br><br><br> <br><br><br><br><br><br><br><br><br><br>
    </vs-collapse-item>
    <vs-collapse-item v-show="ck_reseau">
      <div slot="header">
        Dim-reseau
      </div>
       <v-select label="libelle" :options="reseau"  v-model="contraintes.reseau" :dir="$vs.rtl ? 'rtl' : 'ltr'" />
      <br><br><br><br><br><br><br><br><br><br> <br><br><br><br><br><br><br><br><br><br>
    </vs-collapse-item>
    <vs-collapse-item v-show="ck_temps">
      <div slot="header" >
        Dim-temps
      </div>
      
 <flat-pickr :config="configFromdateTimePicker" v-model="contraintes.tempsDebut" placeholder="From Date" @on-change="onFromChange" />
 <br><br>
  <flat-pickr :config="configTodateTimePicker" v-model="contraintes.tempsFin" placeholder="To Date" @on-change="onToChange" />
  <br><br><br><br><br><br><br><br><br><br> <br><br><br><br><br><br><br><br><br><br>
    </vs-collapse-item>
    <vs-collapse-item v-show="ck_type_centrale">
      <div slot="header">
        Dim-type-centrale
      </div>
     <v-select label="description" :options="type_centrale" v-model="contraintes.typeCentrale" :dir="$vs.rtl ? 'rtl' : 'ltr'" />
      <br><br><br><br><br><br><br><br><br><br> <br><br><br><br><br><br><br><br><br><br>
    </vs-collapse-item>
    <vs-collapse-item v-show="ck_type_evenement">
      <div slot="header">
        Dim-type-evenement
      </div>
      <v-select label="description" :options="type_evenement" v-model="contraintes.evenment" :dir="$vs.rtl ? 'rtl' : 'ltr'" />
      <br><br><br><br><br><br><br><br><br><br> <br><br><br><br><br><br><br><br><br><br>
    </vs-collapse-item>
    <vs-collapse-item v-show="ck_type_objectif">
      <div slot="header">
        Dim-type-objectif
      </div>
      <v-select label="libelle_objectif" :options="type_objectif" v-model="contraintes.typeObjectif" :dir="$vs.rtl ? 'rtl' : 'ltr'" />
      <br><br><br><br><br><br><br><br><br><br> <br><br><br><br><br><br><br><br><br><br>
    </vs-collapse-item>
    <vs-collapse-item v-show="ck_saisie_objectif">
      <div slot="header">
        Dim-saisie-objectif
      </div>
     <v-select label="libelle" :options="type_saisie" v-model="contraintes.saisieObjectif" :dir="$vs.rtl ? 'rtl' : 'ltr'" />
      <br><br><br><br><br><br><br><br><br><br> <br><br><br><br><br><br><br><br><br><br>
    </vs-collapse-item>
    <vs-collapse-item class="bg-primary text-white" >
      <div slot="header" >
        Grouping By
      </div>
      <ul class="demo-alignment">
      <li>
        <vs-radio color="warning" v-model="radios2" vs-value="ROLLUP">Rollup</vs-radio>
      </li>
      <li>
        <vs-radio color="success" v-model="radios2" vs-value="CUBE">Cube</vs-radio>
      </li>
      <li>
        <vs-radio color="danger" v-model="radios2" vs-value="GROUPING SETS">Sets</vs-radio>
      </li>
      
      </ul>
      <div v-for="(set, index) in sets">
    <v-select multiple :closeOnSelect="false" class="bg-white" v-model="set.value" :key="index" :options="groupeBy" :dir="$vs.rtl ? 'rtl' : 'ltr'" /><br>
   
  </div>
  <br><br> 
  <vs-row vs-type="flex" vs-justify="flex-end">
  <vs-button class="bg-success text-white"  @click="addFind">
    add
  </vs-button>
  </vs-row>
   <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
   <br><br><br><br><br>
    </vs-collapse-item>
  </vs-collapse>




        
                </vs-list>
            </div>

        </div>

        

        
        <vs-row vs-type="flex" vs-justify="flex-end">
        <vs-button color="success" v-on:click="greet" type="filled">Done</vs-button>
        </vs-row>
         </vx-card>
        <!--le tableau des résultas-->
         <vx-card v-show="showTable">
           <!--prompt Excel Begin -->
        <vs-prompt title="Export To Excel" class="export-options" @cancle="clearFields" @accept="exportToExcel" accept-text="Export" @close="clearFields" :active.sync="activePrompt">
        <vs-input v-model="fileName" placeholder="Enter File Name.." class="w-full" />
        <v-select v-model="selectedFormat" :options="formats" class="my-4" />
        <div class="flex">
          <span class="mr-4">Cell Auto Width:</span>
          <vs-switch v-model="cellAutoWidth">Cell Auto Width</vs-switch>
        </div>
    </vs-prompt>
    <!--prompt Excel End -->

    <!--prompt Graphe Begin -->
     <vs-prompt title="Create Charts" class="export-options" @cancle="clearFields" @accept="createGraphe" accept-text="Export" @close="clearFields" :active.sync="activePrompt2">
        <v-select v-model="attributeGraphe" :options="operationGraphe"  class="my-4" />
        <v-select v-model="DimGraphe" :options="setsGraphe" label="value" class="my-4" />
        <v-select v-model="typeGraphe" :options="typesGraphes" class="my-4" />
        
    </vs-prompt>
    <!--prompt Graphe End -->

    <div v-show="showTable" class="export-table">
      <vs-table :data="tableData" v-model="selectedUsers" pagination max-items="10" multiple search>

        <template slot="header" >
          <vs-button @click="activePrompt=true" style="margin-right:30px;">Export Selected</vs-button>
        </template>
        <template slot="header">
          <vs-button style="margin-right:30px;" @click="activePrompt2=true">Create Charts</vs-button>
        </template>
        <template slot="header">
          <vs-button class="bg-danger" @click="showRequestCreater=true, showTable=false">Back To Creation</vs-button>
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
import Fuse from "fuse.js"
import draggable from 'vuedraggable'
import Prism from 'vue-prism-component'
import vSelect from 'vue-select'

import flatPickr from 'vue-flatpickr-component';
import 'flatpickr/dist/flatpickr.css';

export default {
   
    data() {
        return {
 //------------------------variable du graphe-----------------------------------------------
          attributeGraphe:'',
          setsGraphe:[],
          DimGraphe:'',
          typeGraphe:'area',
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
        header : [],
      tableData:[],
          groupeBy:["description_cause","code_objectif","description_regime","nomreseau","description_type_centrale","code_type_objectif","code_typesaisie",
          "nom_pole","nom_unite","nom_centrale","num_grpe",
          "annee","trimestre","saison","mois","semaine_dans_annee","jour_du_mois"],
          sets: [],
          operationsets:["sum","AVG","MIN","MAX"],
           radios2:'primary',
           ck_cause:false,
           ck_reseau:false,
           ck_objectif:false,
           ck_type_centrale:false,
           ck_type_organisme:false,
           ck_type_regime_fct:false,
           ck_temps:false,
           ck_type_evenement:false,
           ck_type_objectif:false,
           ck_saisie_objectif:false,
          
            configFromdateTimePicker: {
              minDate: null,
              maxDate: null
            },
            configTodateTimePicker: {
              minDate: null
            },
          type: 'margin',
          
          contraintes:{
            cause:null,
            objectif:null,
            organisme:null,
            regimeFct:null,
            reseau:null,
            tempsDebut:null,
            tempsFin:null,
            typeCentrale:null,
            evenment:null,
            typeObjectif:null,
            saisieObjectif:null

          },
             list1: [
            {
              attributeName: "densite_fuel",
              TableFait: "fait_consommation",
              TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"],
              operation:[],
              },
            {attributeName: "comsommation_combus_fuel_centrale",TableFait: "fait_consommation",TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"],operation:[]},
            {attributeName: "consommation_combus_gaz_centrale",TableFait: "fait_consommation",TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"],operation:[]},
            {attributeName: "consommation_specifique_centrale",TableFait: "fait_consommation",TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"],operation:[]},
            {attributeName: "comsommation_combus_fuel_groupe",TableFait: "fait_consommation",TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"],operation:[]},
            {attributeName: "consommation_combus_gaz_groupe",TableFait: "fait_consommation",TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"],operation:[]},
            {attributeName: "consommation_specifique_groupe",TableFait: "fait_consommation",TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"],operation:[]},
            {attributeName: "stock_fuel_initial",TableFait: "fait_consommation",TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"],operation:[]},
            {attributeName: "consommation_aux_marche",TableFait: "fait_consommation",TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"],operation:[]},
            {attributeName: "consommation_aux_arret",TableFait: "fait_consommation",TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"],operation:[]},
            {attributeName: "consommation_commune",TableFait: "fait_consommation",TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"],operation:[]},
            {attributeName: "reception_fuel",TableFait: "fait_consommation",TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"],operation:[]},
            {attributeName: "pcsfuel",TableFait: "fait_consommation",TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"],operation:[]},
            {attributeName: "pcsgaz",TableFait: "fait_consommation",TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"],operation:[]},
            {attributeName: "production_bu_centrale",TableFait: "fait_production",TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"],operation:[]},
            {attributeName: "production_ba_centrale",TableFait: "fait_production",TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"],operation:[]},
            {attributeName: "production_bu_grpe",TableFait: "fait_production",TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"],operation:[]},
            {attributeName: "production_ba_grpe",TableFait: "fait_production",TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"],operation:[]},
            {attributeName: "puissance_inst_grpe",TableFait: "fait_production",TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"],operation:[]},
            {attributeName: "puissance_dev_grpe",TableFait: "fait_production",TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"],operation:[]},
            {attributeName: "energie_productible",TableFait: "fait_production",TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"],operation:[]},
            {attributeName: "energie_productible_pointe",TableFait: "fait_production",TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"],operation:[]},
            {attributeName: "heures_jour",TableFait: "fait_production",TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"],operation:[]},
 

            ],

            list2: [],
  organismes:[],
  
  


        causes: [],
objectif:[
  {
    "code_objectif": 2409,
    "annee": 2015,
    "date_saisie": "2015-04-27 00:00:00",
    "date_revesion": "2015-01-01 00:00:00"
  },
  {
    "code_objectif": 2410,
    "annee": 2015,
    "date_saisie": "2015-04-27 00:00:00",
    "date_revesion": "2015-01-01 00:00:00"
  },
  {
    "code_objectif": 2411,
    "annee": 2015,
    "date_saisie": "2015-04-27 00:00:00",
    "date_revesion": "2015-01-01 00:00:00"
  }],
  regime:[
  {
    "code_regime": 1,
    "description": "Sans Regime",
    "date_chargement": "2015-04-16 14:38:49.636"
  },
  {
    "code_regime": 38,
    "description": "TG Pointe",
    "date_chargement": "2015-04-16 14:47:39.422"
  },
  {
    "code_regime": 39,
    "description": "TG Basse",
    "date_chargement": "2015-04-16 14:47:39.426"
  },
  {
    "code_regime": 41,
    "description": "CC Base",
    "date_chargement": "2018-07-20 01:00:01.802"
  },
  {
    "code_regime": 42,
    "description": "TG Base",
    "date_chargement": "2018-07-23 01:00:05.007"
  },
  {
    "code_regime": 43,
    "description": "TG Base",
    "date_chargement": "2018-07-23 01:00:05.012"
  },
  {
    "code_regime": 44,
    "description": "TG Base",
    "date_chargement": "2018-07-23 01:00:05.015"
  }
],
reseau:[
  {
    "code_reseau": 12,
    "nomreseau": "Réseau Isoles",
    "libelle": "Réseau Isoles",
    "date_chargement": "2015-01-29"
  },
  {
    "code_reseau": 13,
    "nomreseau": "Réseau Interconnecté",
    "libelle": "Réseau Interconnecté",
    "date_chargement": "2015-01-29"
  },
  {
    "code_reseau": 11,
    "nomreseau": "Boucle Adrar",
    "libelle": "Boucle Adrar",
    "date_chargement": "2015-01-29"
  }
],
type_centrale:[
  {
    "code_type_centrale": 53,
    "description": "MD",
    "date_chargement": "2015-04-16",
    "id_type_centrale": 11
  },
  {
    "code_type_centrale": 54,
    "description": "EO",
    "date_chargement": "2015-04-16",
    "id_type_centrale": 12
  },
  {
    "code_type_centrale": 55,
    "description": "CC",
    "date_chargement": "2015-04-16",
    "id_type_centrale": 13
  }],
  type_evenement:[
  {
    "code_evenement": 40,
    "description": "Réduction de charge pour redémarrage (cogénération/CC)",
    "date_chargement": "2015-04-16",
    "id_type_evenement": 1
  },
  {
    "code_evenement": 41,
    "description": "Déclenchement de groupe",
    "date_chargement": "2015-04-16",
    "id_type_evenement": 2
  },
  {
    "code_evenement": 42,
    "description": "Arrêt groupe (hors programme) sur ordre DN",
    "date_chargement": "2015-04-16",
    "id_type_evenement": 3
  },
  {
    "code_evenement": 43,
    "description": "Arrêt groupe accordé par l'OS",
    "date_chargement": "2015-04-16",
    "id_type_evenement": 4
  }],
  type_objectif:[
  {
    "code_type_objectif": 1,
    "libelle_objectif": "Production",
    "unite_mesure": "GWH"
  },
  {
    "code_type_objectif": 2,
    "libelle_objectif": "Consommation specifique",
    "unite_mesure": "TH/KWH"
  },
  {
    "code_type_objectif": 7,
    "libelle_objectif": "Consommation combustible",
    "unite_mesure": "MTH"
  },
  {
    "code_type_objectif": 8,
    "libelle_objectif": "Consommation combustible gaz",
    "unite_mesure": "MTH"
  },
  {
    "code_type_objectif": 9,
    "libelle_objectif": "Consommation combustible fuel",
    "unite_mesure": "MTH"
  }],
  type_saisie:[
  {
    "code_typesaisie": 1,
    "libelle": "Total"
  },
  {
    "code_typesaisie": 2,
    "libelle": "Total thermique"
  },
  {
    "code_typesaisie": 11,
    "libelle": "TG>=100MW"
  },
  {
    "code_typesaisie": 12,
    "libelle": "TG<100MW"
  }]
  

        }
    },
    components: {
        draggable,
        Prism,
        vSelect,
        flatPickr,
        VueApexCharts
    },
     mounted(){
      var id_user=this.$store.state.AppActiveUser.code_organisme;
      var requet_user='select * from bi.dim_organisme where code_organisme='+id_user
 
      this.$http.get('http://localhost:8087/requests/' + requet_user,{headers : {'Authorization' :"Bearer "  + localStorage.accessToken}}).then((result) => {
        console.log(result.data);
      var type_organisme=  result.data[0].type_organisme;
      console.log(type_organisme);
      var requet_organisme='';
         switch (type_organisme) {
        case "Pole":
          requet_organisme="id_pole = "+result.data[0].id_pole;
          break;
        case "Unite" :
          requet_organisme="id_unite = "+result.data[0].id_unite;
          break;
        case "Centrale" :
         requet_organisme="id_centrale = "+result.data[0].id_centrale;
          break;
        case "Groupe" :
         requet_organisme="id_grpe = "+result.data[0].id_grpe;
          break;
        default:
          requet_organisme="code_organisme = "+result.data[0].code_organisme
      }
       this.$http.get('http://localhost:8087/requests/select * from bi.dim_organisme where ' + requet_organisme,{headers : {'Authorization' :"Bearer "  + localStorage.accessToken}}).then((result) => {
          this.organismes=  result.data;
       })
      }).catch(error => {
        this.$vs.loading.close();
         this.$vs.notify({
        title: ' Requet organisme  ',
        text: error,
        color: 'danger'
      })
      });
      
          this.$http.get('http://localhost:8087/requests/select * from bi.dim_cause',{headers : {'Authorization' :"Bearer "  + localStorage.accessToken}}).then((result) => {
        this.$vs.loading.close();
            
        this.$vs.notify({
          
        title: ' Requet causes ',
        text: 'votre requet a été envoyé avec succès',
        color: 'success'
      })
      this.causes=  result.data;
      }).catch(error => {
        this.$vs.loading.close();
         this.$vs.notify({
        title: ' Requet causes  ',
        text: error,
        color: 'danger'
      })
      });


      

     },
    watch: {
    
    list2: function () {
      
      this.ck_cause=false;
      this.ck_type_evenement=false;
      this.ck_type_objectif=false;
      this.ck_saisie_objectif=false;
      this.ck_reseau=false;
      this.ck_objectif=false;
      this.ck_type_centrale=false;
      this.ck_organisme=false;
      this.ck_regime_fct=false;
      this.ck_temps=false;
      this.list2.forEach(list => {
        if(list.TablesDimentions.includes("dim_cause")){
               this.ck_cause=true;
        }
        if(list.TablesDimentions.includes("dim_objectif")){
               this.ck_objectif=true;
        }
        if(list.TablesDimentions.includes("dim_reseau")){
               this.ck_reseau=true;
        }
        if(list.TablesDimentions.includes("dim_type_centrale")){
               this.ck_type_centrale=true;
        }
        if(list.TablesDimentions.includes("dim_organisme")){
               this.ck_organisme=true;
        }
        if(list.TablesDimentions.includes("dim_regime_fct")){
               this.ck_regime_fct=true;
        }
        if(list.TablesDimentions.includes("dim_temps")){
               this.ck_temps=true;
        }
        if(list.TablesDimentions.includes("dim_type_evenement")){
               this.ck_type_evenement=true;
        }
        if(list.TablesDimentions.includes("dim_type_objectif")){
               this.ck_type_objectif=true;
        }
        if(list.TablesDimentions.includes("dim_saisie_objectif")){
               this.ck_saisie_objectif=true;
        }

        
        });
      
    }
  },
      methods:{
      
      
      greet: function () {
      var requet="select ";
    
    this.setsGraphe=[];
  this.sets.forEach(set => {
        if (set.value.length > 0){
          this.setsGraphe.push(set);
        set.value.forEach(element=>{
        
          requet=requet+"case grouping("+element+" ) when 1 then 'ALL " +element+"' else "+element+" end ,";
        });}
        
      });

      this.operationGraphe=[];
      var tables=[];
      this.list2.forEach(element => {
        element.operation.forEach(op => {
        requet=requet+""+op+"("+element.attributeName+") AS " +op+"_"+element.attributeName+" ,";
        var opera=op+"_"+element.attributeName;
        this.operationGraphe.push(opera);
        });
      });
      requet=requet.substring(0, requet.length - 1)+"from ";
        this.list2.forEach(element => {
          if(tables.includes(element.TableFait)==false){

          tables.push(element.TableFait);
        requet=requet+"bi."+element.TableFait+" natural join ";
        
        }
         element.TablesDimentions.forEach(elementDim => {
        if(tables.includes(elementDim)==false){

          tables.push(elementDim);
        requet=requet+"bi."+elementDim+" natural join ";
        
        }
         });
        
        
      });
      var condition=false;
      requet=requet.substring(0, requet.length - 14)
      for (var prop in this.contraintes) {
        if(this.contraintes[prop]){
       condition=true;
        }
      }
      if(condition===true){
        requet=requet+" where ";
      } 
      if (this.contraintes.typeCentrale){
      requet=requet+"description_type_centrale = '"+this.contraintes.typeCentrale.description+"' and ";
      }
      if (this.contraintes.cause){
      requet=requet+"description_cause = '"+this.contraintes.cause.description+"' and ";
      }
      if (this.contraintes.tempsDebut){
      requet=requet+"date >= '"+this.contraintes.tempsDebut+"' and ";
      
      }
      if (this.contraintes.tempsFin){
      requet=requet+"date <= '"+this.contraintes.tempsFin+"' and ";
      
      }
      
      if (this.contraintes.organisme){
      switch (this.contraintes.organisme.type_organisme) {
        case "Pole":
          requet=requet+"id_pole = "+this.contraintes.organisme.id_pole+" and ";
          break;
        case "Unite" :
          requet=requet+"id_unite = "+this.contraintes.organisme.id_unite+" and ";
          break;
        case "Centrale" :
          requet=requet+"id_centrale = "+this.contraintes.organisme.id_centrale+" and ";
          break;
        case "Groupe" :
          requet=requet+"id_grpe = "+this.contraintes.organisme.id_grpe+" and ";
          break;
        default:
          requet=requet+"code_organisme = "+this.contraintes.organisme.code_organisme+" and ";
      }
      }
       if (this.contraintes.objectif){
      requet=requet+"code_objectif = "+this.contraintes.objectif.code_objectif+" and ";
      }
       if (this.contraintes.regimeFct){
      requet=requet+"code_regime = "+this.contraintes.regimeFct.code_regime+" and ";
      }
      if (this.contraintes.reseau){
      requet=requet+"code_reseau = "+this.contraintes.reseau.code_reseau+" and ";
      }
       if (this.contraintes.evenment){
      requet=requet+"code_evenement = "+this.contraintes.evenment.code_evenement+" and ";
      }
      if (this.contraintes.typeObjectif){
      requet=requet+"code_type_objectif = "+this.contraintes.typeObjectif.code_type_objectif+" and ";
      }
      if (this.contraintes.saisieObjectif){
      requet=requet+"code_typesaisie = "+this.contraintes.saisieObjectif.code_typesaisie+" and ";
      }

      if(condition===true){
      requet=requet.substring(0, requet.length -4);
      }
      requet=requet+" GROUP BY "+this.radios2+"( ";



      this.sets.forEach(set => {
        if(set.value.length > 0 ){
        requet=requet+"(";
        
        set.value.forEach(element=>{

          requet=requet+element+" ,";
        });
        requet=requet.substring(0, requet.length -1)+" ) ,";
        }
      });
      requet=requet.substring(0, requet.length -1)+" ) ";
      alert(requet);
       console.log(requet);
       this.$vs.loading();
             this.$http.get('http://localhost:8087/requests/'+requet)
      .then((result) => {
        this.$vs.loading.close();
            
        this.$vs.notify({
          
        title: ' Requet envoyé  ',
        text: 'votre requet a été envoyé avec succès',
        color: 'success'
      })
            this.header=Object.getOwnPropertyNames(result.data[0]);
        
        
      this.tableData = result.data;
      this.showTable=true;
      this.showRequestCreater=false;
      }).catch(error => {
        this.$vs.loading.close();
         this.$vs.notify({
        title: ' Requet erroné  ',
        text: error,
        color: 'danger'
      })
      });
      
      
            
            
            
           
            
            
            
    },
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


  addFind: function () {
      this.sets.push({ value: [] });
      
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
    
     






     onFromChange(selectedDates, dateStr, instance) {
        this.$set(this.configTodateTimePicker, 'minDate', dateStr);
      },
      onToChange(selectedDates, dateStr, instance) {
        this.$set(this.configFromdateTimePicker, 'maxDate', dateStr);
      },
      createGraphe:function(){
        var value=false;
       this.lineAreaChartSpline.chartOptions.labels=[];
       this.lineAreaChartSpline.series[0].data=[];
       this.pieChart.chartOptions.labels=[];
       this.pieChart.series=[];
      // alert( this.attributeGraphe+";"+this.DimGraphe+";"+this.typeGraphe);
        //console.log(this.attributeGraphe);
        var contrlerIndex=this.header.indexOf(this.DimGraphe.value[0]);
        var contrlerGraphe=this.header[contrlerIndex+1];
         
        this.operationGraphe.forEach(op => {
       if(contrlerGraphe===op.toLowerCase()){
              value=true;
              
            }
        });
      
      
          if(value){
            this.tableData.forEach(element2=>{
              if(element2[this.DimGraphe.value[0]].includes("ALL")===false){ 
              if(this.typeGraphe=="pie"){
          this.pieChart.chartOptions.labels.push(element2[this.DimGraphe.value[0]])  ;
          
          this.pieChart.series.push(element2[contrlerGraphe]); 
              }else{
           this.lineAreaChartSpline.chartOptions.labels.push(element2[this.DimGraphe.value[0]])  ;   
          this.lineAreaChartSpline.series[0].data.push(element2[contrlerGraphe]); } }  
           });
          }
          else{
               this.tableData.forEach(element3=>{
            if(element3[this.DimGraphe.value[0]].includes("ALL")===false){   
            if(element3[contrlerGraphe].includes("ALL")){
              if(this.typeGraphe=="pie"){
          this.pieChart.chartOptions.labels.push(element3[this.DimGraphe.value[0]]);   
          this.pieChart.series.push(element3[this.attributeGraphe.toLowerCase()]);
          
              }else{

          this.lineAreaChartSpline.chartOptions.labels.push(element3[this.DimGraphe.value[0]]);   
          this.lineAreaChartSpline.series[0].data.push(element3[this.attributeGraphe.toLowerCase()]);} } }  
           });

          }
        if(this.typeGraphe=="pie"){

        
        this.showGraphe2=true;}else{
          this.showGraphe=true;
        }

        this.showTable=false;


        
      }
    
  }
    }
    
  


</script>
