<template>
<div class="log-pothole">
    <h1>"Submit a pothole"></h1> 
    <form>  <!-- need to have form saved for example: <form action="/dsfsdgre.php"> -->
        <label for="address"> Type in address of Pothole:</label>
        <input type="text" id="address" name="address">

        <!-- need to use css to establish a break -->

        <label for="description">Describe the Pothole:</label>
        <input type="text" id="description" name="description">

        <input type="submit" value="Submit">
    <!-- STRETCH CASE - add picture attachement here -->

        </form>
  
</template>

<script>
export default {

}
</script>

<style>

</style>