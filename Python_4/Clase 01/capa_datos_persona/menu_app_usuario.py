from Usuario import Usuario
from usuario_dao import UsuarioDAO
from logger_base import log


opcion = None
while opcion != 5:
    print('Opciones:')
    print('1. Listar usuarios')
    print('2. Agregar usuario')
    print('3. Modificar usuario')
    print('4. Eliminar usuario')
    print('5. Salir')
    opcion = int(input('Ingrese una opción (1-5): '))
    if opcion == 1:
        usuarios = UsuarioDAO.seleccionar()
        for usuario in usuarios:
            log.info(usuario)
    elif opcion == 2:
        username = input('Ingrese el username: ')
        password = input('Ingrese el password: ')
        usuario = Usuario(username=username, password=password)
        usuarios_insertados = UsuarioDAO.insertar(usuario)
        log.info(f'Usuarios insertados: {usuarios_insertados}')
    elif opcion == 3:
        id_usuario = int(input('Ingrese el id_usuario: '))
        username = input('Ingrese el username: ')
        password = input('Ingrese el password: ')
        usuario = Usuario(id_usuario=id_usuario, username=username, password=password)
        usuarios_actualizado = UsuarioDAO.actualizar(usuario)
        log.info(f'Usuarios actualizado: {usuarios_actualizado}')
    elif opcion == 4:
        id_usuario = int(input('Ingrese el id_usuario: '))
        usuario = Usuario(id_usuario=id_usuario)
        usuarios_eliminados = UsuarioDAO.eliminar(usuario)
        log.info(f'Usuarios eliminados: {usuarios_eliminados}')
    elif opcion == 5:
        log.info('Salimos de la aplicacion, Hasta pronto!!!')
    else:
        log.error('Opción no válida')