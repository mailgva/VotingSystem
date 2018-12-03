// $(document).ready(function () {
$(function () {
   $('form :radio').change(function(){
       $('#vote').submit();
   });
});
