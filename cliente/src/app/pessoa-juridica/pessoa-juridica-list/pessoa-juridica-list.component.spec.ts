import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PessoaJuridicaListComponent } from './pessoa-juridica-list.component';

describe('PessoaJuridicaListComponent', () => {
  let component: PessoaJuridicaListComponent;
  let fixture: ComponentFixture<PessoaJuridicaListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PessoaJuridicaListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PessoaJuridicaListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
