import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistrarUsuarioComponent } from './registrar-usuario.component';

describe('RegistrarUsuarioComponent', () => {
  let component: RegistrarUsuarioComponent;
  let fixture: ComponentFixture<RegistrarUsuarioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegistrarUsuarioComponent ]  // Declara el componente que se va a probar
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegistrarUsuarioComponent);  // Crea una instancia del componente
    component = fixture.componentInstance;  // Obtiene la instancia del componente
    fixture.detectChanges();  // Realiza la detección de cambios en el componente
  });

  it('should create', () => {
    expect(component).toBeTruthy();  // Verifica que el componente se haya creado correctamente
  });
});
