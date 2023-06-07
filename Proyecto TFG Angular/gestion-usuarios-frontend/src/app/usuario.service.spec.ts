import { TestBed } from '@angular/core/testing';
import { UsuarioService } from './usuario.service';

describe('UsuarioService', () => {
  let service: UsuarioService;

  beforeEach(() => {
    TestBed.configureTestingModule({}); // Configuración del entorno de pruebas de Angular
    service = TestBed.inject(UsuarioService); // Inyección del servicio UsuarioService
  });

  it('should be created', () => {
    expect(service).toBeTruthy(); // Verificación de que el servicio se haya creado correctamente
  });
});
