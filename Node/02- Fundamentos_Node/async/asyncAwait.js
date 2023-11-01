// La palabra async no es necesaria en las funciones, porque ya son asincronas por defecto
//Igual proyectan una asincronia visual.
async function hola(nombre) {
    return new Promise(function(resolve, reject) {
        setTimeout(function() {
            console.log("Hola " +nombre);
            resolve(nombre);
        }, 1000);
    });
}

async function hablar(nombre) {
    return new Promise((resolve, reject) => {
        setTimeout(function() {
            console.log("Bla bla bla bla...");
            resolve(nombre);
        }, 1000);
    });
}

async function adios(nombre) {
    return new Promise((resolve, reject) => {
        setTimeout(function() {
            console.log("Adios " +nombre);
            resolve();
        }, 1000);
    });
}

//await hola("Daniel");  // esto es una mala practica porque no se esta usando el async
// await solo es valido solo dentro de una funcion async
async function main() {
    let nombre = await hola("Daniel");
    await hablar();
    await hablar();
    await hablar();
    await adios(nombre);
    console.log("Terminando proceso...");
}

console.log("Iniciando proceso...");
main();
console.log("esta va a ser la Segunda instruccion");
