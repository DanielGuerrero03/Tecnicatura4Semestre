from Usuario import Usuario
from cursor_del_pool import CursorDelPool
from logger_base import log

class UsuarioDAO:
    '''
    DAO: Data Access Object para la tabla usuario
    CRUD: Create-Read-Update-Delete entidad usuario
    '''

    # Constantes para facilitar la legibilidad de las sentencias SQL
    _SELECT = 'SELECT * FROM usuario ORDER BY id_usuario'
    _INSERT = 'INSERT INTO usuario(username, password) VALUES (%s, %s)'
    _ACTUALIZAR = 'UPDATE usuario SET username=%s, password=%s WHERE id_usuario=%s'
    _ELIMINAR = 'DELETE FROM usuario WHERE id_usuario=%s'

    @classmethod
    def seleccionar(cls):
        with CursorDelPool() as cursor:
            log.debug('Seleccionando usuarios')
            cursor.execute(cls._SELECT)
            registros = cursor.fetchall()
            usuarios = []
            for registro in registros:
                usuario = Usuario(registro[0], registro[1], registro[2])
                usuarios.append(usuario)
            return usuarios

    @classmethod
    def insertar(cls, usuario):
        with CursorDelPool() as cursor:
            log.debug(f'Insertando usuario: {usuario}')
            valores = (usuario.username, usuario.password)
            cursor.execute(cls._INSERT, valores)
            return cursor.rowcount

    @classmethod
    def actualizar(cls, usuario):
        with CursorDelPool() as cursor:
            log.debug(f'Actualizando usuario: {usuario}')
            valores = (usuario.username, usuario.password, usuario.id_usuario)
            cursor.execute(cls._ACTUALIZAR, valores)
            return cursor.rowcount

    @classmethod
    def eliminar(cls, usuario):
        with CursorDelPool() as cursor:
            log.debug(f'Eliminando usuario: {usuario}')
            valores = (usuario.id_usuario,)
            cursor.execute(cls._ELIMINAR, valores)
            return cursor.rowcount

if __name__ == '__main__':
    # Eliminar usuario
    usuario = Usuario(id_usuario=3)
    usuarios_eliminados = UsuarioDAO.eliminar(usuario)
    log.debug(f'Usuarios eliminados: {usuarios_eliminados}')

    # Actualizar usuario
    usuario = Usuario(2, 'carlos', '12345678')
    usuarios_modificado = UsuarioDAO.actualizar(usuario)
    log.debug(f'Usuarios actualizados: {usuarios_modificado}')

    # Insertar usuario
    usuario = Usuario(username='juan', password='12345678')
    usuario_insertado = UsuarioDAO.insertar(usuario)
    log.debug(f'Usuario insertado: {usuario_insertado}')

    # Seleccionar usuarios o listar usuarios
    usuarios = UsuarioDAO.seleccionar()
    for usuario in usuarios:
        log.debug(usuario)

