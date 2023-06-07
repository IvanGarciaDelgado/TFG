import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ListaUsuariosComponent } from './lista-usuarios/lista-usuarios.component';
import { HttpClientModule } from '@angular/common/http';
import { RegistrarUsuarioComponent } from './registrar-usuario/registrar-usuario.component';
import { FormsModule } from '@angular/forms';
import { ActualizarUsuarioComponent } from './actualizar-usuario/actualizar-usuario.component';
import { UsuarioDetallesComponent } from './usuario-detalles/usuario-detalles.component';

@NgModule({
  declarations: [
    AppComponent, // Componente principal de la aplicación
    ListaUsuariosComponent, // Componente para listar usuarios
    RegistrarUsuarioComponent, // Componente para registrar usuarios
    ActualizarUsuarioComponent, // Componente para actualizar usuarios
    UsuarioDetallesComponent // Componente para mostrar los detalles de un usuario
  ],
  imports: [
    BrowserModule, // Módulo para el funcionamiento básico en un navegador web
    AppRoutingModule, // Módulo de enrutamiento de la aplicación
    HttpClientModule, // Módulo para realizar solicitudes HTTP
    FormsModule // Módulo para el uso de formularios
  ],
  providers: [], // Proveedores de servicios
  bootstrap: [AppComponent] // Componente raíz de la aplicación
})
export class AppModule { }
