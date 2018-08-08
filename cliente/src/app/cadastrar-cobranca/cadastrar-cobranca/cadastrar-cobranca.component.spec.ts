import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CadastrarCobrancaComponent } from './cadastrar-cobranca.component';

describe('CadastrarCobrancaComponent', () => {
  let component: CadastrarCobrancaComponent;
  let fixture: ComponentFixture<CadastrarCobrancaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CadastrarCobrancaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CadastrarCobrancaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
