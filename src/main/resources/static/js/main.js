$(function(){
  console.log('Ça marche')
})

function calc() {
  var a = document.getElementById("a").value;
  var b = document.getElementById("b").value;

  var sum = parseInt(a) + parseInt(b)
  alert(parseInt(a) + " + " + parseInt(b) +" = " + sum);

  return false;
}