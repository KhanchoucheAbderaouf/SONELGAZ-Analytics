

<template>
    <div>
        <div class="vx-row">
            <div class="vx-col w-full sm:w-1/2 md:w-1/2 lg:w-1/4 xl:w-1/4 mb-base">
                <statistics-card-line
                  v-if="subscribersGained.analyticsData "
                  icon="ActivityIcon"
                  :statistic="subscribersGained.analyticsData | k_formatter "
                  statisticTitle="Consommation Du Mois"
                  :chartData="subscribersGained.series"
                  type='area' />
            </div>

            <div class="vx-col w-full sm:w-1/2 md:w-1/2 lg:w-1/4 xl:w-1/4 mb-base">
                <statistics-card-line
                  v-if="revenueGenerated.analyticsData"
                  icon="ClipboardIcon"
                  :statistic="revenueGenerated.analyticsData | k_formatter"
                  statisticTitle="Production Du Mois"
                  :chartData="revenueGenerated.series"
                  color='success'
                  type='area' />
            </div>

            <div class="vx-col w-full sm:w-1/2 md:w-1/2 lg:w-1/4 xl:w-1/4 mb-base">
                <statistics-card-line
                  v-if="quarterlySales.analyticsData"
                  icon="ClockIcon"
                  :statistic="quarterlySales.analyticsData | k_formatter"
                  statisticTitle="Heures De Marche Du Mois"
                  :chartData="quarterlySales.series"
                  color='danger'
                  type='area' />
            </div>
            <div class="vx-col w-full sm:w-1/2 md:w-1/2 lg:w-1/4 xl:w-1/4 mb-base">
                <statistics-card-line
                  v-if="ordersRecevied.analyticsData"
                  icon="ZapIcon"
                  :statistic="ordersRecevied.analyticsData | k_formatter"
                  statisticTitle=" Energie Perdu Du Mois"
                  :chartData="ordersRecevied.series"
                  color='warning'
                  type='area' />
            </div>
        </div>

        <div class="vx-row">

            <!-- LINE CHART -->
            <div class="vx-col w-full md:w-2/3 mb-base">
                <vx-card title="Energie Perdu vs Production Ce Mois">
                    <template slot="actions">
                        <feather-icon icon="SettingsIcon" svgClasses="w-6 h-6 text-grey"></feather-icon>
                    </template>
                    <div slot="no-body" class="p-6 pb-0">
                        <div class="flex" v-if="revenueComparisonLine.analyticsData">
                            <div class="mr-6">
                                <p class="mb-1 font-semibold">Production</p>
                                <p class="text-3xl text-success"><sup class="text-base mr-1"></sup>{{ revenueComparisonLine.analyticsData.thisMonth | k_formatter}}</p>
                            </div>
                            <div>
                                <p class="mb-1 font-semibold">Energie Perdu</p>
                                <p class="text-3xl"><sup class="text-base mr-1"></sup>{{ revenueComparisonLine.analyticsData.lastMonth | k_formatter }}</p>
                            </div>
                        </div>
                        <vue-apex-charts
                          type=line
                          height=266
                          :options="analyticsData.revenueComparisonLine.chartOptions"
                          :series="revenueComparisonLine.series" />
                    </div>
                </vx-card>
            </div>

            <!-- RADIAL CHART -->
            <div class="vx-col w-full md:w-1/3 mb-base">
                <vx-card title="Heures De Marche ">
                    <template slot="actions">
                        <feather-icon icon="HelpCircleIcon" svgClasses="w-6 h-6 text-grey"></feather-icon>
                    </template>

                    <!-- CHART -->
                    <template slot="no-body">
                        <div class="mt-10">
                            <vue-apex-charts type=radialBar height=240 :options="analyticsData.goalOverviewRadialBar.chartOptions" :series="goalOverview.series" />
                        </div>
                    </template>

                    <!-- DATA -->
                    <div class="flex justify-between text-center mt-6" slot="no-body-bottom">
                        <div class="w-1/2 border border-solid d-theme-border-grey-light border-r-0 border-b-0 border-l-0">
                            <p class="mt-4">Heures De Marche</p>
                            <p class="mb-4 text-3xl font-semibold">143</p>
                        </div>
                        <div class="w-1/2 border border-solid d-theme-border-grey-light border-r-0 border-b-0">
                            <p class="mt-4">Heures D'Arrête</p>
                            <p class="mb-4 text-3xl font-semibold">25</p>
                        </div>
                    </div>
                </vx-card>
            </div>
        </div>

        <div class="vx-row">
        
            <!-- CUSTOMERS CHART -->
            <div class="vx-col w-full lg:w-1/3 lg:mt-0 mt-base">
                <vx-card title="Consommation Par Pole">
                    <!-- SLOT = ACTIONS -->
                    <template slot="actions">
                        <change-time-duration-dropdown />
                    </template>

                    <div slot="no-body">
                        <!-- CHART -->
                        <vue-apex-charts type=pie height=345 class="mt-10 mb-10" :options="analyticsData.customersPie.chartOptions" :series="customersData.series" />

                        <!-- CHART DATA -->
                        <ul class="mb-1">
                            <li v-for="customerData in customersData.analyticsData" :key="customerData.customerType" class="flex justify-between py-3 px-6 border d-theme-border-grey-light border-solid border-r-0 border-l-0 border-b-0">
                                <span class="flex items-center">
                                    <span class="inline-block h-3 w-3 rounded-full mr-2" :class="`bg-${customerData.color}`"></span>
                                    <span class="font-semibold">{{ customerData.customerType }}</span>
                                </span>
                                <span>{{ customerData.counts }}</span>
                            </li>
                        </ul>
                    </div>

                </vx-card>
            </div>
            

            <div class="vx-col w-full md:w-2/3">
                <vx-card title="State D'Energie Cette Année">
                    <div class="flex">
                        <span class="flex items-center"><div class="h-3 w-3 rounded-full mr-1 bg-primary"></div><span>Energie Perdu</span></span>
                        <span class="flex items-center ml-4"><div class="h-3 w-3 rounded-full mr-1 bg-danger"></div><span>Energie Productible</span></span>
                    </div>
                    <vue-apex-charts type=bar height=277 :options="analyticsData.clientRetentionBar.chartOptions" :series="clientRetentionBar.series" />
                </vx-card>
            </div>
        </div>

    </div>

</template>

<script>
import VuePerfectScrollbar from 'vue-perfect-scrollbar'
import VueApexCharts from 'vue-apexcharts'
import StatisticsCardLine from '@/components/statistics-cards/StatisticsCardLine.vue'
import analyticsData from './ui-elements/card/analyticsData.js'
import ChangeTimeDurationDropdown from '@/components/ChangeTimeDurationDropdown.vue'

export default{
    data() {
        return {
            remplisseur:{data:[],name:null,},
            remplisseur1:{data:[],name:null,},
            remplisseur2:{data:[],name:null,},
            remplisseur3:{data:[],name:null,},
            remplisseur4:{data:[],name:null,},
            remplisseur5:{data:[],name:null,},
            subscribersGained: {analyticsData:null,
            series:[]},
            revenueGenerated: {analyticsData:null,
            series:[]},
            quarterlySales:{analyticsData:null,
            series:[]},
            ordersRecevied: {analyticsData:null,
            series:[]},

            revenueComparisonLine:{analyticsData:{thisMonth:null,
            lastMonth:null,},
            series:[]},
            goalOverview: {},

            browserStatistics: [],
            clientRetentionBar: {series:[]},

            sessionsData: {},
            chatLog: [],
            chatMsgInput: '',
            customersData: {},

            analyticsData: analyticsData,
            settings: { // perfectscrollbar settings
                maxScrollbarLength: 60,
                wheelSpeed: .60,
            },
        }
    },
    components: {
        VueApexCharts,
        StatisticsCardLine,
        VuePerfectScrollbar,
        ChangeTimeDurationDropdown
    },
    mounted() {
        this.$refs.chatLogPS.$el.scrollTop = this.$refs.chatLog.scrollHeight;
    },
    created() {
      // Subscribers gained - Statistics
      this.$http.get("http://localhost:8087/dashboard/getOneResult/consommation du mois",{headers : {'Authorization' :"Bearer "  + localStorage.accessToken}})
        .then((response) => { 
            response.data.forEach(element => {
                if(element.jour_du_mois==="ALL jour_du_moiss"){
                    this.subscribersGained.analyticsData=element.sum_consommation_commune.toFixed(0);
                }else{
                    this.remplisseur.data.push(element.sum_consommation_commune.toFixed(0));
                }
            });
        this.remplisseur.name="consommation";
        this.subscribersGained. series.push(this.remplisseur);
        this.revenueComparisonLine.analyticsData.thisMonth=this.subscribersGained.analyticsData;
        this.revenueComparisonLine.series.push(this.remplisseur);
      
        })
        .catch((error) => { console.log(error) })

     // Revenue Generated
      this.$http.get("http://localhost:8087/dashboard/getOneResult/production du mois",{headers : {'Authorization' :"Bearer "  + localStorage.accessToken}})
        .then((response) => { 
          response.data.forEach(element => {
                if(element.jour_du_mois==="ALL jour_du_moiss"){
                    this.revenueGenerated.analyticsData=element.sum_production.toFixed(0);
                }else{
                    this.remplisseur1.data.push(element.sum_production.toFixed(0));
                }
            });
        this.remplisseur1.name="production";
        this.revenueGenerated. series.push(this.remplisseur1);
        
        })
        .catch((error) => { console.log(error) })

      

      // Sales
      this.$http.get("http://localhost:8087/dashboard/getOneResult/heures du mois",{headers : {'Authorization' :"Bearer "  + localStorage.accessToken}})
        .then((response) => { 
          response.data.forEach(element => {
                if(element.jour_du_mois==="ALL jour_du_moiss"){
                    this.quarterlySales.analyticsData=element.sum_heure.toFixed(0);
                }else{
                    this.remplisseur2.data.push(element.sum_heure.toFixed(0));
                }
            });
        this.remplisseur2.name="heure";
        this.quarterlySales.series.push(this.remplisseur2);
    
        })
        .catch((error) => { console.log(error) })
     //order
      this.$http.get("http://localhost:8087/dashboard/getOneResult/energie perdue mois",{headers : {'Authorization' :"Bearer "  + localStorage.accessToken}})
        .then((response) => { 
          response.data.forEach(element => {
                if(element.jour_du_mois==="ALL jour_du_moiss"){
                    this.ordersRecevied.analyticsData=element.sum_energie.toFixed(0);
                }else{
                    this.remplisseur3.data.push(element.sum_energie.toFixed(0));
                }
            });
        this.remplisseur3.name="Energie Perdu";
        this.ordersRecevied.series.push(this.remplisseur3);
        this.revenueComparisonLine.analyticsData.lastMonth=this.ordersRecevied.analyticsData;
        this.revenueComparisonLine.series.push(this.remplisseur3);
        })
        .catch((error) => { console.log(error) })
     
    

      // Goal Overview
      this.$http.get("/api/card/card-analytics/goal-overview")
        .then((response) => { this.goalOverview = response.data })
        .catch((error) => { console.log(error) })

      // Browser Analytics
      this.$http.get("/api/card/card-analytics/browser-analytics")
        .then((response) => { this.browserStatistics = response.data })
        .catch((error) => { console.log(error) })

      // Client Retention

        this.$http.get("http://localhost:8087/dashboard/getOneResult/energie productible annee",{headers : {'Authorization' :"Bearer "  + localStorage.accessToken}})
        .then((response) => { 
            console.log(response.data);
          response.data.forEach(element => {
                if(element.jour_du_mois==="ALL moiss"){}else{
                    this.remplisseur4.data.push(element.sum_energie.toFixed(0));
                }
            });
            this.remplisseur4.name="Energie Productible";
        this.clientRetentionBar.series.push(this.remplisseur4);
        })
        .catch((error) => { console.log(error) })


          this.$http.get("http://localhost:8087/dashboard/getOneResult/energie perdue annee",{headers : {'Authorization' :"Bearer "  + localStorage.accessToken}})
        .then((response) => { 
            console.log(response.data);
          response.data.forEach(element => {
                if(element.jour_du_mois==="ALL moiss"){}else{
                    this.remplisseur5.data.push(-Math.abs(element.sum_energie.toFixed(0)));
                }
            });
            this.remplisseur5.name="Energie perdu";
        this.clientRetentionBar.series.push(this.remplisseur5);
        })
        .catch((error) => { console.log(error) })

      // Sessions By Device
      this.$http.get("/api/card/card-analytics/session-by-device")
        .then((response) => { this.sessionsData = response.data })
        .catch((error) => { console.log(error) })

      // Chat Log
      this.$http.get("/api/chat/demo-1/log")
        .then((response) => { this.chatLog = response.data })
        .catch((error) => { console.log(error) })

      // Customers
      this.$http.get("/api/card/card-analytics/customers")
        .then((response) => { this.customersData = response.data ;
        console.log(this.customersData)})
        .catch((error) => { console.log(error) })
    }
}
</script>

<style lang="scss">
.chat-card-log {
    height: 400px;

    .chat-sent-msg {
        background-color: #f2f4f7 !important;
    }
}
</style>
