

<template>
    <div id="extra-component-drag-and-drop-demo">

        <drag-and-drop-simple></drag-and-drop-simple>
        <drag-and-drop-multiple-lists></drag-and-drop-multiple-lists>
        <drag-and-drop-clone-list></drag-and-drop-clone-list>
        <drag-and-drop-animation></drag-and-drop-animation>
    </div>
</template>

<script>
import DragAndDropSimple from "./DragAndDropSimple.vue"
import DragAndDropMultipleLists from "./DragAndDropMultipleLists.vue"
import DragAndDropCloneList from "./DragAndDropCloneList.vue"
import DragAndDropAnimation from "./DragAndDropAnimation.vue"

export default {
    components: {
        DragAndDropSimple,
        DragAndDropMultipleLists,
        DragAndDropCloneList,
        DragAndDropAnimation,
    }
}
</script>
