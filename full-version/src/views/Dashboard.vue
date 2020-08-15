<template>
    <div id="dashboard-dashboard">

        <p class="mb-4"><a href="https://github.com/SortableJS/Vue.Draggable" target="_blank" rel="nofollow">Vue.Draggable</a> - Vue component allowing drag-and-drop and synchronization with view model array. Based on and offering all features of Sortable.js</p>

        
        <drag-and-drop-multiple-lists></drag-and-drop-multiple-lists>
        
    </div>
</template>

<script>
import DragAndDropMultipleLists from "./components/extra-components/drag-and-drop/DragAndDropMultipleLists.vue"


export default {
    components: {
       
        DragAndDropMultipleLists,
       
    }
}
</script>