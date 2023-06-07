import { Component } from '@angular/core';

@Component({
  selector: 'app-root', // Selector del componente que se utilizará en el HTML
  templateUrl: './app.component.html', // Ruta del archivo HTML asociado al componente
  styleUrls: ['./app.component.css'] // Rutas de los archivos CSS asociados al componente
})
export class AppComponent {
  title = 'Sistema gestión de usuarios'; // Propiedad del componente que almacena el título
}
