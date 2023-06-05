import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Usuario } from './usuario';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  //Esta URL obtiene el listado de todos los usuarios en el backend
  private baseURL = "http://localhost:9000/api/v1/usuarios";

  constructor(private httpCliente : HttpClient) { }

  //Este método nos sirve para obtener los usuarios
  obtenerListaDeUsuarios():Observable<Usuario[]> {
    return this.httpCliente.get<Usuario[]>(`${this.baseURL}`);
  }

  //Este método nos sirve para registrar un usuario
  registrarUsuario(usuario:Usuario)  : Observable<Object> {
    return this.httpCliente.post(`${this.baseURL}`, usuario);
  }

  //Este método sirve para actualizar el usuario
  actualizarUsuario(id:number, usuario:Usuario):Observable<Object> {
    return this.httpCliente.put(`${this.baseURL}/${id}`,usuario);
  }

  //Este método sirve para obtener o buscar un usuario.
  obtenerUsuarioPorid(id:number):Observable<Usuario> {
    return this.httpCliente.get<Usuario>(`${this.baseURL}/${id}`);
  }

  //Este método sirve para eliminar un usuario.
  eliminarUsuario(id:number):Observable<Object> {
    return this.httpCliente.delete(`${this.baseURL}/${id}`);
  }
}
