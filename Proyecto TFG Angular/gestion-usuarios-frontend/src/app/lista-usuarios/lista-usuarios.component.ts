import { Component, OnInit } from '@angular/core';
import { Usuario } from '../usuario';
import { UsuarioService } from '../usuario.service';
import { Router } from '@angular/router';
import swal from 'sweetalert2';

@Component({
  selector: 'app-lista-usuarios',
  templateUrl: './lista-usuarios.component.html',
  styleUrls: ['./lista-usuarios.component.css']
})
export class ListaUsuariosComponent implements OnInit {

  usuarios: Usuario[];

  constructor(private usuarioServicio: UsuarioService, private router: Router) { }

  ngOnInit(): void {
    this.obtenerUsuarios();
  }

  // Redirecciona a la página de actualización de usuario
  actualizarUsuario(id: number) {
    this.router.navigate(['actualizar-usuario', id]);
  }

  // Muestra un mensaje de confirmación y elimina un usuario
  eliminarUsuario(id: number) {
    swal({
      title: '¿Estás seguro?',
      text: 'Confirma si deseas eliminar al usuario',
      type: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Si, elimínalo',
      cancelButtonText: 'No, cancelar',
      confirmButtonClass: 'btn btn-sucess',
      cancelButtonClass: 'btn btn-danger',
      buttonsStyling: true
    }).then((result) => {
      if (result.value) {
        this.usuarioServicio.eliminarUsuario(id).subscribe(dato => {
          console.log(dato);
          this.obtenerUsuarios();
          swal(
            'Usuario eliminado',
            'El usuario ha sido eliminado con éxito',
            'success'
          );
        });
      }
    });
  }

  // Redirecciona a la página de detalles de usuario
  verDetallesUsuario(id: number) {
    this.router.navigate(['usuario-detalles', id]);
  }

  // Obtiene la lista de usuarios desde el servicio
  private obtenerUsuarios() {
    this.usuarioServicio.obtenerListaDeUsuarios().subscribe(dato => {
      this.usuarios = dato;
    });
  }
}
