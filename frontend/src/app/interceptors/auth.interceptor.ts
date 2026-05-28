import { HttpInterceptorFn } from '@angular/common/http';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  // Intentar token de usuario normal primero, luego experto
  const token =
    localStorage.getItem('authToken') ||
    localStorage.getItem('authTokenExpert');

  if (token) {
    const cloned = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });
    return next(cloned);
  }

  return next(req);
};
