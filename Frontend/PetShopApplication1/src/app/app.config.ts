import { ApplicationConfig, importProvidersFrom, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';
import { JwtModule } from "@auth0/angular-jwt";
import { routes } from './app.routes';
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
export function tokenGetter() {
  return localStorage.getItem("token");
}



export const appConfig: ApplicationConfig = {
  providers: [provideZoneChangeDetection({ eventCoalescing: true }), provideRouter(routes), provideHttpClient(withInterceptorsFromDi()),
    importProvidersFrom(JwtModule.forRoot({
      config: {
          tokenGetter: tokenGetter,
          allowedDomains: ["localhost:9999"],
      },
  }),)
  ],
};
 
 