<!-- =========================================================================================
  File Name: DashboardAnalytics.vue
  Description: Dashboard Analytics
  ----------------------------------------------------------------------------------------
  Item Name: Vuexy - Vuejs, HTML & Laravel Admin Dashboard Template
  Author: Pixinvent
  Author URL: http://www.themeforest.net/user/pixinvent
========================================================================================== -->

<template>
  <div id="dashboard-analytics">
    <div class="vx-row">

    </div>

    <div class="vx-row">

     
    </div>

        <div class="vx-row">
      
        </div>

    <div class="vx-row">
   
    </div>

  </div>
</template>

<script>
import VueApexCharts from 'vue-apexcharts'
import StatisticsCardLine from '@/components/statistics-cards/StatisticsCardLine.vue'
import analyticsData from './ui-elements/card/analyticsData.js'
import ChangeTimeDurationDropdown from '@/components/ChangeTimeDurationDropdown.vue'
import VxTimeline from "@/components/timeline/VxTimeline"

export default {
    data() {
        return {
            checkpointReward: {},
            subscribersGained: {},
            ordersRecevied: {},
            salesBarSession: {},
            supportTracker: {},
            productsOrder: {},
            salesRadar: {},

            timelineData: [
              {
                color: "primary",
                icon: "PlusIcon",
                title: "Client Meeting",
                desc: "Bonbon macaroon jelly beans gummi bears jelly lollipop apple",
                time: "25 mins Ago"
              },
              {
                color: "warning",
                icon: "MailIcon",
                title: "Email Newsletter",
                desc: "Cupcake gummi bears soufflé caramels candy",
                time: "15 Days Ago"
              },
              {
                color: "danger",
                icon: "UsersIcon",
                title: "Plan Webinar",
                desc: "Candy ice cream cake. Halvah gummi bears",
                time: "20 days ago"
              },
              {
                color: "success",
                icon: "LayoutIcon",
                title: "Launch Website",
                desc: "Candy ice cream cake. Halvah gummi bears Cupcake gummi bears soufflé caramels candy.",
                time: "25 days ago"
              },
              {
                color: "primary",
                icon: "TvIcon",
                title: "Marketing",
                desc: "Candy ice cream cake. Halvah gummi bears Cupcake gummi bears.",
                time: "28 days ago"
              }
            ],


            analyticsData: analyticsData,
            dispatchedOrders: [],
        }
    },
    components: {
        VueApexCharts,
        StatisticsCardLine,
        ChangeTimeDurationDropdown,
        VxTimeline
    },
    created() {
      //  User Reward Card
      this.$http.get("/api/user/checkpoint-reward")
        .then((response) => { this.checkpointReward = response.data })
        .catch((error)   => { console.log(error) })

      // Subscribers gained - Statistics
      this.$http.get("/api/card/card-statistics/subscribers")
        .then((response) => { this.subscribersGained = response.data })
        .catch((error)   => { console.log(error) })

      // Orders - Statistics
      this.$http.get("/api/card/card-statistics/orders")
        .then((response) => { this.ordersRecevied = response.data })
        .catch((error)   => { console.log(error) })

      // Sales bar - Analytics
      this.$http.get("/api/card/card-analytics/sales/bar")
        .then((response) => { this.salesBarSession = response.data })
        .catch((error)   => { console.log(error) })

      // Support Tracker
      this.$http.get("/api/card/card-analytics/support-tracker")
        .then((response) => { this.supportTracker = response.data })
        .catch((error)   => { console.log(error) })

      // Products Order
      this.$http.get("/api/card/card-analytics/products-orders")
        .then((response) => { this.productsOrder = response.data })
        .catch((error)   => { console.log(error) })

      // Sales Radar
      this.$http.get("/api/card/card-analytics/sales/radar")
        .then((response) => { this.salesRadar = response.data })
        .catch((error)   => { console.log(error) })

      // Dispatched Orders
      this.$http.get("/api/table/dispatched-orders")
        .then((response) => { this.dispatchedOrders = response.data })
        .catch((error)   => { console.log(error) })
    }
}
</script>

<style lang="scss">
/*! rtl:begin:ignore */
#dashboard-analytics {
  .greet-user{
    position: relative;

    .decore-left{
      position: absolute;
      left:0;
      top: 0;
    }
    .decore-right{
      position: absolute;
      right:0;
      top: 0;
    }
  }

  @media(max-width: 576px) {
    .decore-left, .decore-right{
      width: 140px;
    }
  }
}
/*! rtl:end:ignore */
</style>
