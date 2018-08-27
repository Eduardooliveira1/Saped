import { Routes, RouterModule } from '@angular/router';

import { ComunicacaoListComponent } from './comunicacao-list/comunicacao-list.component'

export const comunicacaoRoute: Routes = [
    { path: '', component: ComunicacaoListComponent }
];
