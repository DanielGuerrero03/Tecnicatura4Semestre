// this === global = true

// Mostrar algo en consola
//console.log();

// Mostrar un mensaje en forma de error
//console.error();

// Ejecutar un codigo despues de un intervalo de tiempo
//setTimeout(() => {});

// Ejecutar un codigo cada cierto intervalo de tiempo
//setInterval(() => {});

// Da prioridad de ejecucion a una funcion asincrona
//setImmediate(() => {});

//console.log(setInterval);

let i = 0;
let intervalo = setInterval(() => {
    console.log("Hola");
    if (i === 3) {
        clearInterval(intervalo); // Detiene el intervalo
    }
    i++;
}, 1000);

setImmediate(() => {
    console.log("Saludo unmediato");
});

//require();

console.log(__filename);

globalThis.miVariable = "mi variable global";
console.log(miVariable);