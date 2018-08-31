import {ModuleWithProviders, NgModule} from '@angular/core';

/* jhipster-needle-add-shared-service-import - JHipster will add shared services imports here */

@NgModule({})
export class SharedModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: SharedModule,
      providers: [
        /* jhipster-needle-add-shared-services - JHipster will add shared services here */
      ]
    };
  }
}
