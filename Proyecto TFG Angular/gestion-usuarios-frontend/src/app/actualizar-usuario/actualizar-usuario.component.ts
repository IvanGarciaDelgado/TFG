import { Component, OnInit } from '@angular/core';
import { Usuario } from '../usuario';
import { UsuarioService } from '../usuario.service';
import { ActivatedRoute, Router } from '@angular/router';
import swal from 'sweetalert2';

@Component({
  selector: 'app-actualizar-usuario',
  templateUrl: './actualizar-usuario.component.html',
  styleUrls: ['./actualizar-usuario.component.css']
})
export class ActualizarUsuarioComponent  implements OnInit{


  id:number;
  usuario : Usuario = new Usuario();
  constructor(private usuarioService:UsuarioService, private router:Router, private route:ActivatedRoute) { }


  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.usuarioService.obtenerUsuarioPorid(this.id).subscribe(dato =>{
      this.usuario = dato;
    },error => console.log(error));
  }

/*   guardarUsuario() {
    this.usuarioServicio.registrarUsuario(this.usuario).subscribe(dato => {
      console.log(dato);
      this.irALaListaDeUsuarios();
    }, error => console.log(error));
  }
 */

  irAlaListaDeUsuarios() {
    // Navegar a la lista de usuarios
    this.router.navigate(['/usuarios']);
    // Mostrar una notificación de éxito
    swal('Usuario actualizado', `El usuario ${this.usuario.nombre} ha sido actualizado con éxito`, 'success');
  }

  onSubmit() {
    // Enviar los datos actualizados del usuario al servicio para su actualización
    this.usuarioService.actualizarUsuario(this.id, this.usuario).subscribe(dato => {
      this.irAlaListaDeUsuarios();
    }, error => console.log(error));
  }
}
