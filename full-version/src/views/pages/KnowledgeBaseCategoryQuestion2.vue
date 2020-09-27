

<template>
    <div id="knowledge-base-article2">
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
                        <p class="mt-1">Last updated on <span>{{ article.lastUpdated | date(true) }}</span></p>
                    </div>

                    <!-- Content -->
                    <p>les figures suivants representent les  ́etapes pour crée une requête :</p>

                    <img :src="article.img" alt="article-img" class="responsive my-3 rounded" />
                    <img :src="article.img2" alt="article-img" class="responsive my-3 rounded" />

                    <p class="mb-6">L'utilisateur choisit les attributs nécessaires pour sa requête, une fois l'attribut est sélectionné, la liste des contraintes(conditions) lié a cet attribut sera affiché.</p>
                     <img :src="article.img3" alt="article-img" class="responsive my-3 rounded" />
                   <p class="mb-6">Pour finaliser sa requête, l'utilisateur pourra choisir les contraintes et les conditions dont il a besoin, avec la possibilité de choisir les différentes formes d'afficher son résultat(order by, partition by, olap, cube, sets).
                        Une fois les différents attributs et conditions sont sélectionnés, l'utilisateur valide sa requête pour la traiter au sein du Microservices de gestions des requêtes.</p>
                     <img :src="article.img4" alt="article-img" class="responsive my-3 rounded" />
                     <p class="mb-6">Une fois la requête traiter, le résultat sera afficher sous forme de tableau avec la possibilité de l'exporter en excel ou de former des graphes à partir de ces résultats.
                    Il y a aussi la possibilité de sauvegarder la requête pour la réutiliser sans avoir à la reformuler. </p>
                    <img :src="article.img5" alt="article-img" class="responsive my-3 rounded" />
                    <p class="mb-6">Pour crée un graphe, il faut choisir l'attribut et la contrainte pour former les axes du graphe, et aussi choisir le type de graphe sois une courbe(area), un diagramme en barres(bar) ou un diagramme circulaire(pie), chaque graphe peut être exporter sous forme d'une image.</p>
                   <img :src="article.img6" alt="article-img" class="responsive my-3 rounded" />
                    <p class="mb-6">Le résultat sous la forme des différents graphes sera présenté sur les différentes figures suivantes:</p>
                   <img :src="article.img7" alt="article-img" class="responsive my-3 rounded" />
                   <img :src="article.img8" alt="article-img" class="responsive my-3 rounded" />
                    <img :src="article.img9" alt="article-img" class="responsive my-3 rounded" />
                   <p class="mb-6">Pour Exporter le tableau de résultats sous forme Excel, il faut d'abord sélectionner les lignes a exporter pour ensuite choisir le nom du fichier Excel.</p>
                     <img :src="article.img10" alt="article-img" class="responsive my-3 rounded" />
                    <!-- Footer -->
                    <template slot="footer">
                        <div class="flex items-center justify-between">
                            <router-link to="/pages/knowledge-base/category/question1" class="flex items-center"><feather-icon :icon="$vs.rtl ? 'ChevronsRightIcon' : 'ChevronsLeftIcon'" class="mr-2" svgClasses="h-4 w-4"></feather-icon> Précedent</router-link>
                            <router-link to="/pages/knowledge-base/category/question" class="flex items-center">suivant <feather-icon :icon="$vs.rtl ? 'ChevronsLeftIcon' : 'ChevronsRightIcon'" class="ml-2" svgClasses="h-4 w-4"></feather-icon></router-link>
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
                title       : 'Creation De Requête',
                lastUpdated : 'Dim Spt 27 2020 ',
                img         : require("@/assets/images/pages/Screens app/query creator.png"),
                img2         : require("@/assets/images/pages/Screens app/query creator 2.png"),
                img3         : require("@/assets/images/pages/Screens app/query creator 3.png"),
                img4         : require("@/assets/images/pages/Screens app/query creator 4.png"),
                img5         : require("@/assets/images/pages/Screens app/query creator after done.png"),
                img6         : require("@/assets/images/pages/Screens app/create chart.png"),
                img7         : require("@/assets/images/pages/Screens app/chart 1.png"),
                img8         : require("@/assets/images/pages/Screens app/chart 2.png"),
                img9         : require("@/assets/images/pages/Screens app/chart 3.png"),
                img10         : require("@/assets/images/pages/Screens app/export excel.png"),
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
