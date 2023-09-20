
console.log("Hola a todos esto es monohilo");


var i = 0;
setInterval(function() {
    console.log(i);
    i++;

    //  if (i === 5) {
    //     console.log("forzamos error");
    //     var a = 3 + z;
    //}
}, 1000);
console.log("Segunda instruccion");