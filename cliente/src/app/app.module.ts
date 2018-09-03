import { HashLocationStrategy, LocationStrategy } from '@angular/common';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AccessbilityModule, AuthService, AUTH_CONFIG, BlockUiModule, DatatableModule, ErrorStackModule, HttpService, PageNotificationModule, SecurityModule, SharedModule } from '@basis/angular-components';
import { NgProgressModule } from '@ngx-progressbar/core';
import { NgProgressHttpModule } from '@ngx-progressbar/http';
import { ConfirmationService } from 'primeng/primeng';
import 'rxjs/add/operator/toPromise';
import { environment } from '../environments/environment';
import { AppComponent } from './app.component';
import { AppFooterComponent } from './app.footer.component';
import { AppMenuComponent, AppSubMenuComponent } from './app.menu.component';
import { InlineProfileComponent } from './app.profile.component';
import { AppRightPanelComponent } from './app.rightpanel.component';
import { AppRoutes } from './app.routes';
import { AppTopBarComponent } from './app.topbar.component';
import { authServiceFactory } from './auth-service-factory';
import { TokenInterceptor } from './auth/interceptor';
import { AppBreadcrumbComponent } from './breadcrumb/app.breadcrumb.component';
import { BreadcrumbService } from './breadcrumb/breadcrumb.service';
import { DiarioErrosComponent } from './diario-erros/diario-erros.component';
import { InicioComponent } from './inicio/inicio.component';
import { LoginLayoutComponent } from './layouts/login-layout/login-layout.component';
import { SecuredLayoutComponent } from './layouts/secured-layout/secured-layout.component';
import { LoginService } from './login-service';
import { LoginSuccessComponent } from './login-success/login-success.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { PRIMENG_IMPORTS } from './primeng-imports';
import { SharedModule as AppSharedModule } from './shared/shared.module';

/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
  imports: [
    
    BrowserModule,
    FormsModule,
    AppRoutes,
    HttpClientModule,
    BrowserAnimationsModule,
    PRIMENG_IMPORTS,
    NgProgressModule.forRoot(),
    NgProgressHttpModule,
    DatatableModule.forRoot(),
    SharedModule.forRoot(),
    ErrorStackModule.forRoot(),
    SecurityModule.forRoot(),
    AppSharedModule.forRoot(),
    AccessbilityModule.forRoot(),
    PageNotificationModule.forRoot(),
    BlockUiModule.forRoot(),
    /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
  ],
  declarations: [
    AppComponent,
    AppMenuComponent,
    AppSubMenuComponent,
    AppTopBarComponent,
    AppFooterComponent,
    AppRightPanelComponent,
    AppBreadcrumbComponent,
    InlineProfileComponent,
    LoginComponent,
    LoginSuccessComponent,
    LogoutComponent,
    DiarioErrosComponent,
    InicioComponent,
    SecuredLayoutComponent,
    LoginLayoutComponent,
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    },
    { provide: LocationStrategy, useClass: HashLocationStrategy },
    { provide: AUTH_CONFIG, useValue: environment.auth },
    { provide: AuthService, deps: [HttpService, AUTH_CONFIG], useFactory: authServiceFactory },
    BreadcrumbService,
    ConfirmationService,
    LoginService,
  ],
  bootstrap: [AppComponent],
})
export class AppModule { }
