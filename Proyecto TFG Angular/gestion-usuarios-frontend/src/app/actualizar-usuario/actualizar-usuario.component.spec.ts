import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActualizarUsuarioComponent } from './actualizar-usuario.component';

describe('ActualizarUsuarioComponent', () => {
  let component: ActualizarUsuarioComponent;
  let fixture: ComponentFixture<ActualizarUsuarioComponent>;

  beforeEach(async () => {
    // Configuración inicial antes de ejecutar las pruebas
    await TestBed.configureTestingModule({
      declarations: [ ActualizarUsuarioComponent ]
    })
    .compileComponents(); // Compila el componente y sus plantillas

    fixture = TestBed.createComponent(ActualizarUsuarioComponent);
    // Crea una instancia del componente en un entorno de prueba
    component = fixture.componentInstance;
    // Obtiene una referencia al componente para realizar las pruebas
    fixture.detectChanges();
    // Detecta los cambios iniciales en el componente
  });

  it('should create', () => {
    // Prueba que el componente se haya creado correctamente
    expect(component).toBeTruthy();
    // Verifica que el componente exista (no sea nulo)
  });
});
