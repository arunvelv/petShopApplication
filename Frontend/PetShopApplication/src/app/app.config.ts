import { ApplicationConfig, importProvidersFrom, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';
import { JwtInterceptor, JwtModule } from "@auth0/angular-jwt";
import { routes } from './app.routes';
import { HTTP_INTERCEPTORS, provideHttpClient, withFetch, withInterceptorsFromDi } from '@angular/common/http';



export function tokenGetter() {
  const token = localStorage.getItem("token");
  console.log("Token retrieved:", token);
  return token;
}

export const appConfig: ApplicationConfig = {
  providers: [provideZoneChangeDetection({ eventCoalescing: true }), provideRouter(routes), provideHttpClient(withFetch(), withInterceptorsFromDi()),
    importProvidersFrom(JwtModule.forRoot({
      config: {
          tokenGetter: tokenGetter,
          allowedDomains: ["localhost:9999"],
      },
  }),),
  // { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
  ],
};
 
 