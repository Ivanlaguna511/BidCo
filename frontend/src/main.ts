import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { AppComponent } from './app/app.component';
import { environment } from './environments/environment';

// Deshabilitar console.log en producción para no exponer datos en la consola del navegador
if (environment.production) {
  console.log = () => {};
  console.debug = () => {};
}

bootstrapApplication(AppComponent, appConfig)
  .catch((err) => console.error(err));