

<template>
    <div id="knowledge-base-article1">
        <div class="vx-row">
            <div class="vx-col w-full md:w-2/5 lg:w-1/4">
                <vx-card title="Autres informations">
                    <ul class="bordered-items">
                        <li v-for="que in relatedQuestions" :key="que.id" class="py-2">
                          <router-link :to="que.answerUrl">{{ que.question }}</router-link>
                        </li>
                    </ul>
                </vx-card>
            </div>
            <div class="vx-col w-full md:w-3/5 lg:w-3/4 mt-12 md:mt-0">
                <vx-card>
                    <!-- Title -->
                    <div class="article-title mb-6">
                        <h1>{{ article.title }}</h1>
                        <p class="mt-1"> <span>{{ article.lastUpdated | date(true) }}</span></p>
                    </div>

                    <!-- Content -->
                    <p>Afin d’accéder au système il faut tout d’abord s’authentifier par les deux paramètres username et password, soit pour l’administrateur ou pour l’utilisateur.</p>

                    <img :src="article.img" id="img" alt="article-img" class="responsive my-3 rounded" />
                      <img :src="article.img2" id="img2" alt="article-img" class="responsive my-3 rounded" />

                   

                    <!-- Footer -->
                    <template slot="footer">
                        <div class="flex items-center justify-between">
                            <router-link to="/pages/knowledge-base/category/question" class="flex items-center"><feather-icon :icon="$vs.rtl ? 'ChevronsRightIcon' : 'ChevronsLeftIcon'" class="mr-2" svgClasses="h-4 w-4"></feather-icon> Précedent</router-link>
                            <router-link to="/pages/knowledge-base/category/question2" class="flex items-center">Suivant <feather-icon :icon="$vs.rtl ? 'ChevronsLeftIcon' : 'ChevronsRightIcon'" class="ml-2" svgClasses="h-4 w-4"></feather-icon></router-link>
                        </div>
                    </template>
                </vx-card>
            </div>
        </div>
    </div>
</template>

<script>

export default{
    data() {
        return {
            article: {
                title       : 'Authentification',
                lastUpdated : 'Dim Spt 27 2020 ',
                img         : require("@/assets/images/pages/Screens app/login.png"),
                img2:require("@/assets/images/pages/Screens app/login chargement.png"),
                topics      : ['Pastry jelly chocolate bar caramels', 'Donut chupa chups oat cake', 'Marshmallow icing topping toffee caramels dessert carrot cake']
            },
            relatedQuestions: [
                {
                    "id"        : 0,
                    "question"  : 'Authentification',
                    "answerUrl" : "/pages/knowledge-base/category/question1"
                },
                {
                    "id"        : 1,
                    "question"  : 'Dashboard',
                    "answerUrl" : "/pages/knowledge-base/category/question"
                },
                {
                    "id"        : 2,
                    "question"  : 'Creation De Requête',
                    "answerUrl" : "/pages/knowledge-base/category/question2"
                }
               
            ]
        }
    },
    mounted() {
        this.$emit('changeRouteTitle', 'Question');
    }
}
</script>
