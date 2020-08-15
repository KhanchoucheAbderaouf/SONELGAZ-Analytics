<!-- =========================================================================================
    File Name: DragAndDropClone.vue
    Description: Clone from one list to another
    ----------------------------------------------------------------------------------------
    Item Name: Vuexy - Vuejs, HTML & Laravel Admin Dashboard Template
      Author: Pixinvent
    Author URL: http://www.themeforest.net/user/pixinvent
========================================================================================== -->


<template>
    <vx-card title="Clone List" code-toggler>
     
     
     
        <p>Clone list items from another existing list. Add <code>pull</code> and <code>put</code> to <code>group</code> prop</p>

        <!-- List 1 -->
        <div class="vx-row">
            <div class="vx-col w-full sm:w-1/2">
                <h6 class="mt-5 mb-3">Tag Source</h6>
                <draggable :list="list1" :group="{name:'tags',  pull:'clone', put:false }" class="p-2 cursor-move">
                    <vs-chip v-for="(listItem, index) in list1" :key="index">{{ listItem.attributeName }}</vs-chip>
                </draggable>
            </div>
            <div class="vx-col w-full sm:w-1/2">
                <h6 class="mt-5 mb-3">Your Tags</h6>
                <draggable :list="list2" :group="{name: 'tags'}" class="p-2 cursor-move">
                    <vs-chip v-for="(listItem, index) in list2" :key="index">{{ listItem.attributeName }}</vs-chip>
                </draggable>
            </div>
        </div>

        <!-- List 2 -->
        <div class="vx-row">
            <div class="vx-col w-full sm:w-1/2">
                <prism language="js" class="rounded-lg">
Tag Source: {{ list1 }}
                </prism>
            </div>
            <div class="vx-col w-full sm:w-1/2">
                <prism language="js" class="rounded-lg">
Added tags: {{ list2 }}
                </prism>
            </div>
            
        </div>

        <template slot="codeContainer">
&lt;template&gt;
  &lt;div&gt;
    &lt;h6 class=&quot;mt-5 mb-3&quot;&gt;Tag Source&lt;/h6&gt;
    &lt;draggable :list=&quot;list1&quot; :group=&quot;{name:'tags',  pull:'clone', put:false }&quot; class=&quot;p-2 cursor-move&quot;&gt;
      &lt;vs-chip v-for=&quot;(listItem, index) in list1&quot; :key=&quot;index&quot;&gt; {{ "\{\{ listItem \}\}" }} &lt;/vs-chip&gt;
    &lt;/draggable&gt;
  &lt;/div&gt;
  &lt;div&gt;
    &lt;h6 class=&quot;mt-5 mb-3&quot;&gt;Your Tags&lt;/h6&gt;
    &lt;draggable :list=&quot;list2&quot; :group=&quot;{name: 'tags'}&quot; class=&quot;p-2 cursor-move&quot;&gt;
      &lt;vs-chip v-for=&quot;(listItem, index) in list2&quot; :key=&quot;index&quot;&gt; {{ "\{\{ listItem \}\}" }} &lt;/vs-chip&gt;
    &lt;/draggable&gt;
  &lt;/div&gt;
&lt;/template&gt;

&lt;script&gt;
import draggable from 'vuedraggable'

export default {
  data() {
    return {
      list1: [&quot;youtube&quot;, &quot;google&quot;, &quot;facebook&quot;, &quot;twitter&quot;, &quot;instagram&quot;, &quot;pinterest&quot;, &quot;tinder&quot;, &quot;slack&quot;, &quot;discord&quot;, &quot;github&quot;, &quot;gitlab&quot;],
      list2: [&quot;google&quot;, &quot;youtube&quot;]
    }
  },
  components: {
    draggable,
  }
}
&lt;/script&gt;
        </template>
        <vs-button color="success" v-on:click="greet" type="filled">Done</vs-button>
    </vx-card>
</template>

<script>
import draggable from 'vuedraggable'
import Prism from 'vue-prism-component'

export default {
    data() {
        return {
            list1: [
            {attributeName: "densite_fuel",TableFait: "fait_consommation",TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"]},
            {attributeName: "comsommation_combus_fuel_centrale",TableFait: "fait_consommation",TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"]},
            {attributeName: "consommation_combus_gaz_centrale",TableFait: "fait_consommation",TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"]},
            {attributeName: "consommation_specifique_centrale",TableFait: "fait_consommation",TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"]},
            {attributeName: "comsommation_combus_fuel_groupe",TableFait: "fait_consommation",TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"]},
            {attributeName: "consommation_combus_gaz_groupe",TableFait: "fait_consommation",TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"]},
            {attributeName: "consommation_specifique_groupe",TableFait: "fait_consommation",TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"]},
            {attributeName: "stock_fuel_initial",TableFait: "fait_consommation",TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"]},
            {attributeName: "consommation_aux_marche",TableFait: "fait_consommation",TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"]},
            {attributeName: "consommation_aux_arret",TableFait: "fait_consommation",TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"]},
            {attributeName: "consommation_commune",TableFait: "fait_consommation",TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"]},
            {attributeName: "reception_fuel",TableFait: "fait_consommation",TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"]},
            {attributeName: "pcsfuel",TableFait: "fait_consommation",TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"]},
            {attributeName: "pcsgaz",TableFait: "fait_consommation",TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"]},
            {attributeName: "pcsuser",TableFait: "fait_user",TablesDimentions:["dim_reseau","dim_regime_fct","dim_type_centrale","dim_temps","dim_organisme"]},

            ],
            list2: []
        }
    },
    components: {
        draggable,
        Prism
    },
    methods:{
      greet: function () {
      var requet="select ";
      var tables=[];
      this.list2.forEach(element => {
        requet=requet+element.attributeName+" ,"
        
      });
      requet=requet.substring(0, requet.length - 1)+"from ";
        this.list2.forEach(element => {
          if(tables.includes(element.TableFait)==false){

          tables.push(element.TableFait);
        requet=requet+element.TableFait+" ,";}
        
      });
      requet=requet.substring(0, requet.length - 1)+" ;";
      alert(requet);
    }
    }
}
</script>
