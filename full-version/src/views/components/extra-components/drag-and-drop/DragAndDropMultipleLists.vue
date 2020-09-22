


<template>
 
      
    <div title="Multiple Lists">
    <vx-card v-show="showRequestCreater"  >
        

        <!-- List 1 -->
        <div class="vx-row">
            <div class="vx-col w-full md:w-1/3">
                <vs-list>
                    <vs-list-header title="La liste des Attributs" color="primary"></vs-list-header>
                    <vs-collapse :type="type" accordion>
                    <!--fait_consommation-->
                      <vs-collapse-item class="bg-primary text-white"  >
                        <div slot="header">
                          Consommation
                        </div>
                    <draggable :list="list1" group="people" class="p-2 cursor-move">
                      <div v-for="(listItem, index) in list1" :key="index" >
                        <vs-list-item  v-if="listItem.TableFait==='fait_consommation'" :title="listItem.attributeName" ></vs-list-item>
                        </div>
                    </draggable>
                      </vs-collapse-item>
                    
                    <!--fait_production-->
                      <vs-collapse-item class="bg-primary text-white" >
                        <div slot="header">
                          Production
                        </div>
                    <draggable :list="list1" group="people" class="p-2 cursor-move">
                      <div v-for="(listItem, index) in list1" :key="index" >
                        <vs-list-item  v-if="listItem.TableFait==='fait_production'" :title="listItem.attributeName" ></vs-list-item>
                        </div>
                    </draggable>
                      </vs-collapse-item>
                    <!--fait_pv-->
                        <vs-collapse-item class="bg-primary text-white" >
                        <div slot="header">
                          PV
                        </div>
                    <draggable :list="list1" group="people" class="p-2 cursor-move">
                      <div v-for="(listItem, index) in list1" :key="index" >
                        <vs-list-item  v-if="listItem.TableFait==='fait_pv'" :title="listItem.attributeName" ></vs-list-item>
                        </div>
                    </draggable>
                      </vs-collapse-item>
                        <!--fait_qualite_service-->
                        <vs-collapse-item class="bg-primary text-white" >
                        <div slot="header">
                          Qualité de service
                        </div>
                    <draggable :list="list1" group="people" class="p-2 cursor-move">
                      <div v-for="(listItem, index) in list1" :key="index" >
                        <vs-list-item  v-if="listItem.TableFait==='fait_qualite_service'" :title="listItem.attributeName" ></vs-list-item>
                        </div>
                    </draggable>
                      </vs-collapse-item>
                       <!-- fait_separation_reseau-->
                        <vs-collapse-item class="bg-primary text-white" >
                        <div slot="header">
                          Séparation réseau
                        </div>
                    <draggable :list="list1" group="people" class="p-2 cursor-move">
                      <div v-for="(listItem, index) in list1" :key="index" >
                        <vs-list-item  v-if="listItem.TableFait==='fait_separation_reseau'" :title="listItem.attributeName" ></vs-list-item>
                        </div>
                    </draggable>
                      </vs-collapse-item>
                    </vs-collapse>
                </vs-list>
            </div>
            <div class="vx-col w-full md:w-1/3">
                <vs-list>
                    <vs-list-header title="Les Attributs Choisient" color="primary"></vs-list-header>
                    <draggable :list="list2" group="people" class="p-2 cursor-move">
                    <vs-list-item v-for="(listItem, index) in list2" :key="index" :title="listItem.attributeName" >
                      <vs-dropdown class="cursor-pointer flex" vs-custom-content>

                            <!--<feather-icon icon="InfoIcon" color="primary" svgClasses="h-7 w-7" @click.prevent></feather-icon>-->
                             <vs-button radius color="primary" type="flat" iconPack="feather" icon="icon-plus" @click.prevent></vs-button> 

                            <vs-dropdown-menu style="z-index: 200001">
                                    <vs-dropdown-item v-for="(tag, index) in operationsets" :key="index">
                                        <vs-checkbox :vs-value="tag" v-model="listItem.operation" v-on:change="operationWatcher" @click.stop >{{ tag }}</vs-checkbox>
                                    </vs-dropdown-item>
                            </vs-dropdown-menu>
                        </vs-dropdown>
                    </vs-list-item>
                    </draggable>
                </vs-list>
            </div>
            <div class="vx-col w-full md:w-1/3">
                <vs-list>
                    <vs-list-header title="La liste des Dimensions" color="primary"></vs-list-header>
                    
                     

  <vs-collapse :type="type" accordion>

    <vs-collapse-item  v-show="ck_cause">
      <div slot="header">
        Causes
      </div>
      <v-select multiple label="description_cause" placeholder="Type Cause" :options="causes" v-model="contraintes.cause" :dir="$vs.rtl ? 'rtl' : 'ltr'" />
      <br><br><br><br><br><br><br><br><br><br> <br><br><br><br><br><br><br><br><br><br>
    </vs-collapse-item>

    <vs-collapse-item v-show="ck_objectif">
      <div slot="header" >
        Objectif
      </div><v-select multiple label="code_objectif" placeholder="L'Organisme" v-model="contraintes.objectif" :options="objectif" :dir="$vs.rtl ? 'rtl' : 'ltr'" />
      <br><br><br><br><br><br><br><br><br><br> <br><br><br><br><br><br><br><br><br><br>
    </vs-collapse-item>

    <vs-collapse-item v-show="ck_type_organisme">
      <div slot="header">
        Organisme
      </div>
     
    
  <v-select  :filter="fuseSearch" :options="organismes"  v-model="contraintes.organisme" :getOptionLabel="option => option.type_organisme">
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
        Régime Fonctionnel
      </div>
       <v-select multiple label="description_regime" :options="regime" placeholder="Type De Regime" v-model="contraintes.regimeFct" :dir="$vs.rtl ? 'rtl' : 'ltr'" />
      <br><br><br><br><br><br><br><br><br><br> <br><br><br><br><br><br><br><br><br><br>
    </vs-collapse-item>
    <vs-collapse-item v-show="ck_reseau">
      <div slot="header">
        Réseaux
      </div>
       <v-select multiple label="libelle_reseau" :options="reseau" placeholder="Type De Reseau" v-model="contraintes.reseau" :dir="$vs.rtl ? 'rtl' : 'ltr'" />
      <br><br><br><br><br><br><br><br><br><br> <br><br><br><br><br><br><br><br><br><br>
    </vs-collapse-item>
    <vs-collapse-item v-show="ck_temps">
      <div slot="header" >
        Temps
      </div>
      
 <flat-pickr :config="configFromdateTimePicker" v-model="contraintes.tempsDebut" placeholder="From Date" @on-change="onFromChange" />
 <br><br>
  <flat-pickr :config="configTodateTimePicker" v-model="contraintes.tempsFin" placeholder="To Date" @on-change="onToChange" />
  <br><br><br><br><br><br><br><br><br><br> <br><br><br><br><br><br><br><br><br><br>
    </vs-collapse-item>
    <vs-collapse-item v-show="ck_type_centrale">
      <div slot="header">
        Type Centrale
      </div>
     <v-select multiple label="description_type_centrale" :options="type_centrale" placeholder="Type De Centrale"  v-model="contraintes.typeCentrale" :dir="$vs.rtl ? 'rtl' : 'ltr'" />
      <br><br><br><br><br><br><br><br><br><br> <br><br><br><br><br><br><br><br><br><br>
    </vs-collapse-item>
    <vs-collapse-item v-show="ck_type_evenement">
      <div slot="header">
        Type d'évènement
      </div>
      <v-select multiple label="description_evenement" placeholder="Type D'Evenement"  :options="type_evenement" v-model="contraintes.evenment" :dir="$vs.rtl ? 'rtl' : 'ltr'" />
      <br><br><br><br><br><br><br><br><br><br> <br><br><br><br><br><br><br><br><br><br>
    </vs-collapse-item>
    <vs-collapse-item v-show="ck_type_objectif">
      <div slot="header">
        Type Objectif
      </div>
      <v-select multiple label="libelle_objectif" :options="type_objectif" placeholder="Type D'Objectif"  v-model="contraintes.typeObjectif" :dir="$vs.rtl ? 'rtl' : 'ltr'" />
      <br><br><br><br><br><br><br><br><br><br> <br><br><br><br><br><br><br><br><br><br>
    </vs-collapse-item>
    <vs-collapse-item v-show="ck_saisie_objectif">
      <div slot="header">
        Saisie Objectif
      </div>
     <v-select multiple label="libelle_type_saisieobjectif" :options="type_saisie" placeholder="Type De Saisie" v-model="contraintes.saisieObjectif" :dir="$vs.rtl ? 'rtl' : 'ltr'" />
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
      <br>
      <div v-for="(set, index) in sets">
    <v-select multiple :closeOnSelect="false" class="bg-white" v-model="set.value" :key="index" :options="groupeBy" :dir="$vs.rtl ? 'rtl' : 'ltr'" /><br>
   
  </div>
  <br>
  <vs-row vs-type="flex" vs-justify="flex-end">
  <vs-button class="bg-success text-white"  @click="addFind">
    Ajouter
  </vs-button>
  <br>
  </vs-row>
  <br>
  <vs-divider> <h4 class="text-white">Le Rang</h4> </vs-divider>
  <v-select class="bg-white" v-model="orderBy" placeholder="Order BY" :key="index" :options="operationGraphe" :dir="$vs.rtl ? 'rtl' : 'ltr'" /><br>
  <v-select class="bg-white" v-model="partitionBy" placeholder="Partition BY" :key="index" :options="groupeBy" :dir="$vs.rtl ? 'rtl' : 'ltr'" /><br>

   <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
   <br><br><br><br><br>
    </vs-collapse-item>
  </vs-collapse>




        
                </vs-list>
            </div>

        </div>

        

        
        <vs-row vs-type="flex" vs-justify="flex-end">
        <vs-button color="success" v-on:click="greet" type="filled">Valider</vs-button>
        </vs-row>
         </vx-card>
        <!--le tableau des résultas-->
         <vx-card v-show="showTable">
           <!--prompt Excel Begin -->
        <vs-prompt title="Exporter Résultats" class="export-options" @cancle="clearFields" @accept="exportToExcel" accept-text="Exporter" @close="clearFields" :active.sync="activePrompt">
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

    <!--prompt requet Begin -->
     <vs-prompt title="Save Query" class="export-options" @cancle="clearFields" @accept="saveQuery" accept-text="Save" @close="clearFields" :active.sync="activePrompt3">
         <vs-input label-placeholder="Title" v-model="titreRequet"/>
        
    </vs-prompt>
    <!--prompt requet End -->


    <div v-show="showTable" class="export-table">
      <vs-table :data="tableData" v-model="selectedUsers" pagination max-items="10" multiple search>

        <template slot="header" >
          <vs-button @click="activePrompt=true" style="margin-right:30px;">Exporter les Résultats</vs-button>
        </template>
        <template slot="header">
          <vs-button style="margin-right:30px;" @click="activePrompt2=true">Créer des Graphes</vs-button>
        </template>
        <template slot="header">
          <vs-button class="bg-danger" @click="backToCreation">Retour a la création requête</vs-button>
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
      <vs-button class="bg-success" @click="activePrompt3=true">Sauvegarder la requête</vs-button>
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
             yaxis: {
      showAlways: true,
      forceNiceScale:true,
      decimalsInFloat:0,
        labels: {
      /*
      * Allows users to apply a custom formatter function to yaxis labels.
      *
      * @param { String } value - The generated value of the y-axis tick
      * @param { index } index of the tick / currently executing iteration in yaxis labels array
      */
      formatter: function(val, index) {
        return val > 999999 ?  (val / 1000000).toFixed(1) + 'M' : val > 999 ?  (val / 1000).toFixed(1) + 'k' : val;
      }
    }
      
     },
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
      activePrompt3:false,
      requetOlap:'',
      titreRequet:'',
        header : [],
      tableData:[],
          groupeBy:[],
          sets: [{ value: [] }],
          operationsets:["sum","AVG","MIN","MAX"],
           radios2:'GROUPING SETS',
           ck_cause:false,
           ck_reseau:false,
           ck_objectif:false,
           ck_type_centrale:false,
           ck_type_organisme:false,
           ck_regime_fct:false,
           ck_temps:false,
           ck_type_evenement:false,
           ck_type_objectif:false,
           ck_saisie_objectif:false,
          
            ck_cause2:true,
           ck_reseau2:true,
           ck_objectif2:true,
           ck_type_centrale2:true,
           ck_type_organisme2:true,
           ck_regime_fct2:true,
           ck_temps2:true,
           ck_type_evenement2:true,
           ck_type_objectif2:true,
           ck_saisie_objectif2:true,

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
    operation:["sum"],
  },
{attributeName: "comsommation_combus_fuel_centrale",TableFait: "fait_consommation",TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"],  operation:["sum"]},
{attributeName: "consommation_combus_gaz_centrale",TableFait: "fait_consommation",TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"],  operation:["sum"]},
{attributeName: "consommation_specifique_centrale",TableFait: "fait_consommation",TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"],  operation:["sum"]},
{attributeName: "comsommation_combus_fuel_groupe",TableFait: "fait_consommation",TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"],  operation:["sum"]},
{attributeName: "consommation_combus_gaz_groupe",TableFait: "fait_consommation",TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"],  operation:["sum"]},
{attributeName: "consommation_specifique_groupe",TableFait: "fait_consommation",TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"],  operation:["sum"]},
{attributeName: "stock_fuel_initial",TableFait: "fait_consommation",TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"],  operation:["sum"]},
{attributeName: "consommation_aux_marche",TableFait: "fait_consommation",TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"],  operation:["sum"]},
{attributeName: "consommation_aux_arret",TableFait: "fait_consommation",TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"],  operation:["sum"]},
{attributeName: "consommation_commune",TableFait: "fait_consommation",TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"],  operation:["sum"]},
{attributeName: "reception_fuel",TableFait: "fait_consommation",TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"],  operation:["sum"]},
{attributeName: "pcsfuel",TableFait: "fait_consommation",TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"],  operation:["sum"]},
{attributeName: "pcsgaz",TableFait: "fait_consommation",TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"],  operation:["sum"]},
//fait production
{attributeName: "production_bu_centrale",TableFait: "fait_production",TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"],  operation:["sum"]},
{attributeName: "production_ba_centrale",TableFait: "fait_production",TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"],  operation:["sum"]},
{attributeName: "production_bu_grpe",TableFait: "fait_production",TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"],  operation:["sum"]},
{attributeName: "production_ba_grpe",TableFait: "fait_production",TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"],  operation:["sum"]},
{attributeName: "puissance_inst_grpe",TableFait: "fait_production",TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"],  operation:["sum"]},
{attributeName: "puissance_dev_grpe",TableFait: "fait_production",TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"],  operation:["sum"]},
{attributeName: "energie_productible",TableFait: "fait_production",TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"],  operation:["sum"]},
{attributeName: "energie_productible_pointe",TableFait: "fait_production",TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"],  operation:["sum"]},
{attributeName: "heures_jour",TableFait: "fait_production",TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"],  operation:["sum"]},
//fait_objectif
{attributeName: "objectif_annuel",TableFait: "fait_objectif",TablesDimentions:["dim_type_objectif","dim_type_saisieobjectif","dim_objectif","dim_organisme"],  operation:["sum"]},
//fait_pv
{attributeName: "production_bu",TableFait: "fait_pv",TablesDimentions:["dim_temps","dim_organisme"],  operation:["sum"]},
{attributeName: "consommation_gaz",TableFait: "fait_pv",TablesDimentions:["dim_temps","dim_organisme"],  operation:["sum"]},
{attributeName: "consommation_fuel",TableFait: "fait_pv",TablesDimentions:["dim_temps","dim_organisme"],  operation:["sum"]},
{attributeName: "densite_fuel_pv",TableFait: "fait_pv",TablesDimentions:["dim_temps","dim_organisme"],  operation:["sum"]},
{attributeName: "pcsfuel_pv",TableFait: "fait_pv",TablesDimentions:["dim_temps","dim_organisme"],  operation:["sum"]},
{attributeName: "pcsgaz_pv",TableFait: "fait_pv",TablesDimentions:["dim_temps","dim_organisme"],  operation:["sum"]},
//fait_qualite_service
{attributeName: "energie_perdue",TableFait: "fait_qualite_service",TablesDimentions:["dim_reseau","dim_type_evenement","dim_type_centrale","dim_cause","dim_temps","dim_organisme"],  operation:["sum"]},
{attributeName: "energie_perdue_pointe",TableFait: "fait_qualite_service",TablesDimentions:["dim_reseau","dim_type_evenement","dim_type_centrale","dim_cause","dim_temps","dim_organisme"],  operation:["sum"]},
{attributeName: "energie_productible_qualite",TableFait: "fait_qualite_service",TablesDimentions:["dim_reseau","dim_type_evenement","dim_type_centrale","dim_cause","dim_temps","dim_organisme"],  operation:["sum"]},
{attributeName: "energie_productible_pointe_qualite",TableFait: "fait_qualite_service",TablesDimentions:["dim_reseau","dim_type_evenement","dim_type_centrale","dim_cause","dim_temps","dim_organisme"],  operation:["sum"]},
{attributeName: "heure_indispo",TableFait: "fait_qualite_service",TablesDimentions:["dim_reseau","dim_type_evenement","dim_type_centrale","dim_cause","dim_temps","dim_organisme"],  operation:["sum"]},
{attributeName: "heure_indispo_pointe",TableFait: "fait_qualite_service",TablesDimentions:["dim_reseau","dim_type_evenement","dim_type_centrale","dim_cause","dim_temps","dim_organisme"],  operation:["sum"]},
//fait_qualite_service_imed
{attributeName: "energie_perdue_imed",TableFait: "fait_qualite_service_imed",TablesDimentions:["dim_reseau","dim_type_evenement","dim_type_centrale","dim_cause","dim_temps","dim_organisme"],  operation:["sum"]},
{attributeName: "energie_perdue_pointe_imed",TableFait: "fait_qualite_service_imed",TablesDimentions:["dim_reseau","dim_type_evenement","dim_type_centrale","dim_cause","dim_temps","dim_organisme"],  operation:["sum"]},
{attributeName: "energie_productible_imed",TableFait: "fait_qualite_service_imed",TablesDimentions:["dim_reseau","dim_type_evenement","dim_type_centrale","dim_cause","dim_temps","dim_organisme"],  operation:["sum"]},
{attributeName: "energie_productible_pointe_imed",TableFait: "fait_qualite_service_imed",TablesDimentions:["dim_reseau","dim_type_evenement","dim_type_centrale","dim_cause","dim_temps","dim_organisme"],  operation:["sum"]},
{attributeName: "heure_indispo_imed",TableFait: "fait_qualite_service_imed",TablesDimentions:["dim_reseau","dim_type_evenement","dim_type_centrale","dim_cause","dim_temps","dim_organisme"],  operation:["sum"]},
{attributeName: "heure_indispo_pointe_imed",TableFait: "fait_qualite_service_imed",TablesDimentions:["dim_reseau","dim_type_evenement","dim_type_centrale","dim_cause","dim_temps","dim_organisme"],  operation:["sum"]},
{attributeName: "date_debut",TableFait: "fait_qualite_service_imed",TablesDimentions:["dim_reseau","dim_type_evenement","dim_type_centrale","dim_cause","dim_temps","dim_organisme"],  operation:["sum"]},
//fait_separation_reseau
{attributeName: "nombre_dt",TableFait: "fait_separation_reseau",TablesDimentions:["dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"],  operation:["sum"]},
{attributeName: "heure_marche_vide",TableFait: "fait_separation_reseau",TablesDimentions:["dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"],  operation:["sum"]},
{attributeName: "heure_marche_gaz",TableFait: "fait_separation_reseau",TablesDimentions:["dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"],  operation:["sum"]},
{attributeName: "heure_marche_fuel",TableFait: "fait_separation_reseau",TablesDimentions:["dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"],  operation:["sum"]},
{attributeName: "heure_indiponibilite",TableFait: "fait_separation_reseau",TablesDimentions:["dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"],  operation:["sum"]},
{attributeName: "heure_reserve",TableFait: "fait_separation_reseau",TablesDimentions:["dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"],  operation:["sum"]},
{attributeName: "nombre_demarrage",TableFait: "fait_separation_reseau",TablesDimentions:["dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"],  operation:["sum"]},
{attributeName: "nbre_declanchement_reussi",TableFait: "fait_separation_reseau",TablesDimentions:["dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"],  operation:["sum"]},

],
organisme_utilisateur:null,
list2: [],
//les tables de DIM
organismes:[],
causes: [],
objectif:[],
regime:[],
reseau:[],
type_centrale:[],
type_evenement:[],
type_objectif:[],
type_saisie:[],
orderBy:null,
partitionBy:null,


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
 //requet pour avoir Dim organisme
      this.$http.get('http://localhost:8087/requests/codeorganism/' + id_user,{headers : {'Authorization' :"Bearer "  + localStorage.accessToken}}).then((result) => {
      var type_organisme=  result.data[0].type_organisme;
      this.organisme_utilisateur= result.data[0];
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
       this.$http.get('http://localhost:8087/requests/organism/' + requet_organisme,{headers : {'Authorization' :"Bearer "  + localStorage.accessToken}}).then((result) => {
          this.organismes=  result.data;
       })
      });
      //requet pour avoir Dim cause
          this.$http.get('http://localhost:8087/requests/causes',{headers : {'Authorization' :"Bearer "  + localStorage.accessToken}})
          .then((result) => {   
        this.causes=  result.data;
      });

//requet pour avoir Dim Objectif
   this.$http.get('http://localhost:8087/requests/objectif',{headers : {'Authorization' :"Bearer "  + localStorage.accessToken}})
          .then((result) => {   
        this.objectif=  result.data;
      });
      //requet pour avoir Dim regime
   this.$http.get('http://localhost:8087/requests/regime',{headers : {'Authorization' :"Bearer "  + localStorage.accessToken}})
          .then((result) => {   
        this.regime=  result.data;
      });
        //requet pour avoir Dim reseau
   this.$http.get('http://localhost:8087/requests/reseau',{headers : {'Authorization' :"Bearer "  + localStorage.accessToken}})
          .then((result) => {   
        this.reseau=  result.data;
      });
           //requet pour avoir Dim type_centrale
   this.$http.get('http://localhost:8087/requests/typecentrale',{headers : {'Authorization' :"Bearer "  + localStorage.accessToken}})
          .then((result) => {   
        this.type_centrale=  result.data;
      });
        //requet pour avoir Dim type_evenement
   this.$http.get('http://localhost:8087/requests/typeevent',{headers : {'Authorization' :"Bearer "  + localStorage.accessToken}})
          .then((result) => {   
        this.type_evenement=  result.data;
      });
           //requet pour avoir Dim type_objectif
   this.$http.get('http://localhost:8087/requests/typeobjectif',{headers : {'Authorization' :"Bearer "  + localStorage.accessToken}})
          .then((result) => {   
        this.type_objectif=  result.data;
      });
             //requet pour avoir Dim type_saisie
   this.$http.get('http://localhost:8087/requests/typesaisieobjectif',{headers : {'Authorization' :"Bearer "  + localStorage.accessToken}})
          .then((result) => {   
        this.type_saisie=  result.data;
      });
      
     },
    watch: {
    
    list2: function () {
      
      this.groupeBy=["nom_pole","nom_unite","nom_centrale","num_grpe"];
      this.ck_cause2=true;
      this.ck_reseau2=true;
      this.ck_objectif2=true;
      this.ck_type_centrale2=true;
      this.ck_type_organisme2=true;
      this.ck_regime_fct2=true;
      this.ck_temps2=true;
      this.ck_type_evenement2=true;
      this.ck_type_objectif2=true;
      this.ck_saisie_objectif2=true;
      this.ck_cause=false;
      this.ck_type_evenement=false;
      this.ck_type_objectif=false;
      this.ck_saisie_objectif=false;
      this.ck_reseau=false;
      this.ck_objectif=false;
      this.ck_type_centrale=false;
      this.ck_type_organisme=false;
      this.ck_regime_fct=false;
      this.ck_temps=false;
      this.list2.forEach(list => {
        //dim_cause
        if(list.TablesDimentions.includes("dim_cause") && this.ck_cause2){
               
              if(this.ck_cause===false) { this.groupeBy.push("description_cause")};
               this.ck_cause=true;
        }else{ this.ck_cause2=false; 
          if(this.ck_cause) {this.groupeBy.splice(this.groupeBy.indexOf("description_cause"),1)};
          this.ck_cause=false;}
        //dim_objectif
        if(list.TablesDimentions.includes("dim_objectif") && this.ck_objectif2){
               
               if(this.ck_objectif===false) {this.groupeBy.push("code_objectif")};
               this.ck_objectif=true;
        }else{this.ck_ojectif2=false;
          if(this.ck_objectif) {this.groupeBy.splice(this.groupeBy.indexOf("code_objectif"),1)};
          this.ck_objectif=false;}
          //dim_reseau
        if(list.TablesDimentions.includes("dim_reseau") && this.ck_reseau2){
               
              if( this.ck_reseau===false) { this.groupeBy.push("nomreseau")};
              this.ck_reseau=true;
        }else{this.ck_reseau2=false;
        if(this.ck_reseau) {this.groupeBy.splice(this.groupeBy.indexOf("nomreseau"),1)};
        this.ck_reseau=false;}
        //dim_type_centrale
        if(list.TablesDimentions.includes("dim_type_centrale") && this.ck_type_centrale2){
               
              if( this.ck_type_centrale===false) {  this.groupeBy.push("description_type_centrale")};
              this.ck_type_centrale=true;
        }else{this.ck_type_centrale2=false;
        if(this.ck_type_centrale) {this.groupeBy.splice(this.groupeBy.indexOf("description_type_centrale"),1)};
        this.ck_type_centrale=false;}
        //dim_organisme
        if(list.TablesDimentions.includes("dim_organisme") && this.ck_type_organisme2){
               this.ck_type_organisme=true;
        }else{this.ck_type_organisme2=false;this.ck_type_organisme=false}
        //dim_regime_fct
        if(list.TablesDimentions.includes("dim_regime_fct") && this.ck_regime_fct2){
               
                if( this.ck_regime_fct===false) {this.groupeBy.push("description_regime")};
                this.ck_regime_fct=true;
        }else{this.ck_regime_fct2=false;
        if(this.ck_regime_fct) {this.groupeBy.splice(this.groupeBy.indexOf("description_regime"),1)};
        this.ck_regime_fct=false;}
        //dim_temps
        if(list.TablesDimentions.includes("dim_temps") && this.ck_temps2){
          if( this.ck_temps===false) {this.groupeBy.push("annee","trimestre","saison","mois","semaine_dans_annee","jour_du_mois")};
               this.ck_temps=true;
        }else{this.ck_temps2=false;
        if(this.ck_regime_fct) {this.groupeBy.splice(this.groupeBy.indexOf("annee"),6)};
        this.ck_temps=false;}
        //dim_evenement
        if(list.TablesDimentions.includes("dim_type_evenement") && this.ck_type_evenement2){
               
               if( this.ck_type_evenement===false) {this.groupeBy.push("description_evenement")};
               this.ck_type_evenement=true;
        }else{this.ck_type_evenement2=false;
        if(this.ck_type_evenement) {this.groupeBy.splice(this.groupeBy.indexOf("description_evenement"),1)};
        this.ck_type_evenement=false;}
        //dim_type_objectif
        if(list.TablesDimentions.includes("dim_type_objectif") && this.ck_type_objectif2){
               
                if( this.ck_type_objectif===false) {this.groupeBy.push("code_type_objectif")};
                this.ck_type_objectif=true;
        }else{this.ck_type_objectif2=false;
        if(this.ck_type_objectif) {this.groupeBy.splice(this.groupeBy.indexOf("code_type_objectif"),1)};
        this.ck_type_objectif=false;}
      //dim_type_saisie
        if(list.TablesDimentions.includes("dim_saisie_objectif") && this.ck_saisie_objectif2){
               
              if( this.ck_saisie_objectif===false) {this.groupeBy.push("code_typesaisie")};
              this.ck_saisie_objectif=true;
               
        }else{this.ck_saisie_objectif2=false;
       if(this.ck_saisie_objectif) {this.groupeBy.splice(this.groupeBy.indexOf("code_typesaisie"),1)};
        this.ck_saisie_objectif=false;}

        
        });
      
    }
  },
      methods:{
      
      backToCreation:function(){
        this.showRequestCreater=true; 
        this.showTable=false;
        this.contraintes.cause=null;
        this.contraintes.objectif=null;
        this.contraintes.organisme=null;
        this.contraintes.regimeFct=null;
        this.contraintes.reseau=null;
        this.contraintes.tempsDebut=null;
        this.contraintes.tempsFin=null;
        this.contraintes.typeCentrale=null;
        this.contraintes.evenment=null;
        this.contraintes.typeObjectif=null;
        this.contraintes.saisieObjectif=null;

        
      },
      greet: function () {
      var requet="select ";
    
    this.setsGraphe=[];
  this.sets.forEach(set => {
        if (set.value.length > 0){
          if(!this.setsGraphe.includes(set.value[set.value.length-1])) {this.setsGraphe.push(set.value[set.value.length-1]);}
        set.value.forEach(element=>{
         
          requet=requet+"case grouping("+element+" ) when 1 then 'ALL " +element+"s' else cast("+element+" as varchar(255)) end ,";
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
      if(this.orderBy){
      requet=requet+"RANK () OVER ( PARTITION BY "+this.partitionBy +" ORDER BY "+
      this.orderBy+" DESC ) AS classement from " ;}
      else{
        requet=requet.substring(0, requet.length - 1)+"from " ;
      }
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
    //requet admin
        requet=requet+" where ";
       
      if (this.contraintes.typeCentrale && this.contraintes.typeCentrale.length>0){
      requet=requet+"description_type_centrale IN ('";
        this.contraintes.typeCentrale.forEach(set => {
         requet=requet+set.description_type_centrale+"', '";
        });
        requet=requet.substring(0, requet.length -3)+") and ";
      }
      if (this.contraintes.cause && this.contraintes.cause.length>0){
      requet=requet+"description_cause IN ('";
      this.contraintes.cause.forEach(set => {
         requet=requet+set.description_cause+"', '";
        });
        requet=requet.substring(0, requet.length -3)+") and ";
      }
      if (this.contraintes.tempsDebut){
      requet=requet+"date >= '"+this.contraintes.tempsDebut+"' and ";
      
      }
      if (this.contraintes.tempsFin){
      requet=requet+"date <= '"+this.contraintes.tempsFin+"' and ";
      
      }
      
      if (this.contraintes.organisme && this.contraintes.organisme.length>0){
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
      else{
switch (this.organisme_utilisateur.type_organisme) {
        case "Pole":
          requet=requet+"id_pole = "+this.organisme_utilisateur.id_pole+" and ";
          break;
        case "Unite" :
          requet=requet+"id_unite = "+this.organisme_utilisateur.id_unite+" and ";
          break;
        case "Centrale" :
          requet=requet+"id_centrale = "+this.organisme_utilisateur.id_centrale+" and ";
          break;
        case "Groupe" :
          requet=requet+"id_grpe = "+this.organisme_utilisateur.id_grpe+" and ";
          break;
        default:
          requet=requet+"code_organisme = "+this.organisme_utilisateur.code_organisme+" and ";
      }
      }
       if (this.contraintes.objectif && this.contraintes.objectif.length>0){
      requet=requet+"code_objectif IN (";
      this.contraintes.objectif.forEach(set => {
         requet=requet+set.code_objectif+", ";
        });
        requet=requet.substring(0, requet.length -2)+") and ";
      }
       if (this.contraintes.regimeFct && this.contraintes.regimeFct.length>0){
      requet=requet+"code_regime IN (";
       this.contraintes.regimeFct.forEach(set => {
         requet=requet+set.code_regime+", ";
        });
        requet=requet.substring(0, requet.length -2)+") and ";
      }
      if (this.contraintes.reseau && this.contraintes.reseau.length>0){
      requet=requet+"code_reseau IN (";
      this.contraintes.reseau.forEach(set => {
         requet=requet+set.code_reseau+", ";
        });
      requet=requet.substring(0, requet.length -2)+") and ";
      }
       if (this.contraintes.evenment && this.contraintes.evenment.length>0){
      requet=requet+"code_evenement IN (";
      this.contraintes.evenment.forEach(set => {
         requet=requet+set.code_evenement+", ";
        });
      requet=requet.substring(0, requet.length -2)+") and ";
      }
      if (this.contraintes.typeObjectif && this.contraintes.typeObjectif.length>0){
      requet=requet+"code_type_objectif IN (";
      this.contraintes.typeObjectif.forEach(set => {
         requet=requet+set.code_type_objectif+", ";
        });
      requet=requet.substring(0, requet.length -2)+") and ";
      }
      if (this.contraintes.saisieObjectif && this.contraintes.saisieObjectif.length>0){
      requet=requet+"code_typesaisie IN (";
      this.contraintes.saisieObjectif.forEach(set => {
         requet=requet+set.code_typesaisie+", ";
        });
      requet=requet.substring(0, requet.length -2)+") and ";
      }

      //requet admin
      requet=requet.substring(0, requet.length -4);
      
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
      this.requetOlap=requet;
       this.$vs.loading();
             this.$http.get('http://localhost:8087/requests/'+requet,{headers : {'Authorization' :"Bearer "  + localStorage.accessToken}})
      .then((result) => {
        this.$vs.loading.close();
            
        this.$vs.notify({
          
        title: ' Requet envoyé  ',
        text: 'votre requet a été envoyé avec succès',
        color: 'success'
      })
      console.log(result.data);
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
      
   nFormatter(num, digits) {
  var si = [
    { value: 1, symbol: "" },
    { value: 1E3, symbol: "k" },
    { value: 1E6, symbol: "M" },
    { value: 1E9, symbol: "G" },
    { value: 1E12, symbol: "T" },
    { value: 1E15, symbol: "P" },
    { value: 1E18, symbol: "E" }
  ];
  var rx = /\.0+$|(\.[0-9]*[1-9])0+$/;
  var i;
  for (i = si.length - 1; i > 0; i--) {
    if (num >= si[i].value) {
      break;
    }
  }
  return (num / si[i].value).toFixed(digits).replace(rx, "$1") + si[i].symbol;
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
        var contrlerIndex=this.header.indexOf(this.DimGraphe);
        var contrlerGraphe=this.header[contrlerIndex+1];
         
        this.operationGraphe.forEach(op => {

          //if next is an operation
       if(contrlerGraphe===op.toLowerCase()){
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
          this.lineAreaChartSpline.series[0].data.push(element2[contrlerGraphe]); } }  
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
      saveQuery:function(){
        console.log(this.$store.state.AppActiveUser.uid);
        console.log(this.titreRequet);
        console.log(this.requetOlap);
        this.$vs.loading();
  this.$http.post('http://localhost:8087/queries/saveQuery/' + this.$store.state.AppActiveUser.uid,{titre:this.titreRequet,valeur:this.requetOlap},{headers : {'Authorization' :"Bearer "  + localStorage.accessToken}}).then((result) => {
         this.$vs.loading.close();
         this.$vs.notify({
        title: ' Requet saved ',
        text: this.titreRequet,
        color: 'success'
      })
       }).catch(error => {
        this.$vs.loading.close();
         this.$vs.notify({
        title: ' Requet Not Saved  ',
        text: error,
        color: 'danger'
      })
      });
this.$http.post('http://localhost:8087/mongo/addResult',{title:this.titreRequet,JsonAnswer:this.tableData},{headers : {'Authorization' :"Bearer "  + localStorage.accessToken}}).then((result) => {        
         this.$vs.notify({
        title: ' Requet saved ',
        text: this.titreRequet,
        color: 'success'
      })
       }).catch(error => { 
         this.$vs.notify({
        title: ' Requet Not Saved  ',
        text: error,
        color: 'danger'
      })
      });
      },
      operationWatcher:function(){
          this.operationGraphe=[];
        this.list2.forEach(element => {
        element.operation.forEach(op => {
        
        var opera=op+"("+element.attributeName+")";
        this.operationGraphe.push(opera);
        });
      });
      },
    
  }
    }
    
  


</script>