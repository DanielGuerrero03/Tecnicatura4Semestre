
# dar formato a un string

nombre = "Daniel"
edad = 32
mensaje_con_formato = "Hola, mi nombre es %s y tengo %d años" %(nombre, edad)

# Creamos una tupla
persona = ("Carla", "Gonzalez", 5000.00)
mensaje_con_formato = "Hola %s %s . Tu sueldo es $ %.2f" # %persona # Aqui le pasamos el objeto que es una tupla
print(mensaje_con_formato %persona)