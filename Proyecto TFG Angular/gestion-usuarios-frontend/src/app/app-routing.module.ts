import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListaUsuariosComponent } from './lista-usuarios/lista-usuarios.component';
import { RegistrarUsuarioComponent } from './registrar-usuario/registrar-usuario.component';
import { ActualizarUsuarioComponent } from './actualizar-usuario/actualizar-usuario.component';
import { UsuarioDetallesComponent } from './usuario-detalles/usuario-detalles.component';

const routes: Routes = [
  { path: 'usuarios', component: ListaUsuariosComponent }, // Ruta para mostrar la lista de usuarios
  { path: '', redirectTo: 'usuarios', pathMatch: 'full' }, // Ruta por defecto, redirige a la lista de usuarios
  { path: 'registrar-usuario', component: RegistrarUsuarioComponent }, // Ruta para registrar un nuevo usuario
  { path: 'actualizar-usuario/:id', component: ActualizarUsuarioComponent }, // Ruta para actualizar un usuario existente, requiere el ID del usuario
  { path: 'usuario-detalles/:id', component: UsuarioDetallesComponent } // Ruta para mostrar los detalles de un usuario, requiere el ID del usuario
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
