import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaUsuariosComponent } from './lista-usuarios.component';

describe('ListaUsuariosComponent', () => {
  let component: ListaUsuariosComponent; // Variable para almacenar una instancia del componente a probar.
  let fixture: ComponentFixture<ListaUsuariosComponent>; // Variable para manejar el fixture que envuelve al componente.

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListaUsuariosComponent ] // Configura el módulo de pruebas con el componente a probar.
    })
    .compileComponents(); // Compila los componentes declarados en el módulo de pruebas.

    fixture = TestBed.createComponent(ListaUsuariosComponent); // Crea una instancia del componente dentro del fixture.
    component = fixture.componentInstance; // Obtiene la instancia del componente a través del fixture.
    fixture.detectChanges(); // Detecta los cambios y actualiza la vista del componente.
  });

  it('should create', () => {
    expect(component).toBeTruthy(); // Verifica que el componente se haya creado correctamente.
  });
});
