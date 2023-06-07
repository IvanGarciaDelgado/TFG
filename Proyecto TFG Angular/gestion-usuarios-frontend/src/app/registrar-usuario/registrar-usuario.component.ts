import { Component } from '@angular/core';
import { Usuario } from '../usuario';
import { UsuarioService } from '../usuario.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registrar-usuario',
  templateUrl: './registrar-usuario.component.html',
  styleUrls: ['./registrar-usuario.component.css']
})
export class RegistrarUsuarioComponent {

  usuario: Usuario = new Usuario(); // Instancia de la clase Usuario para almacenar los datos del usuario a registrar

  constructor(private usuarioServicio: UsuarioService, private router: Router) { }
  // Inyección de dependencias: UsuarioService para interactuar con la API de usuarios y Router para la navegación

  ngOnInit(): void {
    // Método del ciclo de vida que se ejecuta al inicializar el componente
  }

  guardarUsuario() {
    // Método para guardar un nuevo usuario
    this.usuarioServicio.registrarUsuario(this.usuario).subscribe(dato => {
      // Llama al método del servicio para registrar el usuario y suscribe a la respuesta
      console.log(dato); // Imprime la respuesta en la consola
      this.irALaListaDeUsuarios(); // Redirige a la lista de usuarios después de guardar
    }, error => console.log(error)); // Manejo de errores en caso de fallo en la petición
  }

  irALaListaDeUsuarios() {
    // Método para redirigir a la lista de usuarios
    this.router.navigate(['/usuarios']); // Navega a la ruta '/usuarios'
  }

  onSubmit() {
    // Método que se ejecuta al enviar el formulario
    this.guardarUsuario(); // Llama al método para guardar el usuario
  }
}
