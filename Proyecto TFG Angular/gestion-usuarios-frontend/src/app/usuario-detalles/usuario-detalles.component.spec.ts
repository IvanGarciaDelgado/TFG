import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UsuarioDetallesComponent } from './usuario-detalles.component';

describe('UsuarioDetallesComponent', () => {
  let component: UsuarioDetallesComponent;
  let fixture: ComponentFixture<UsuarioDetallesComponent>;

  beforeEach(async () => {
    // Configuración inicial antes de ejecutar las pruebas
    await TestBed.configureTestingModule({
      declarations: [ UsuarioDetallesComponent ]
    })
    .compileComponents(); // Compila el componente y sus plantillas

    fixture = TestBed.createComponent(UsuarioDetallesComponent);
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
