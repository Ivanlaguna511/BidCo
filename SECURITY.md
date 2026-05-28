# Security Policy

## Versiones soportadas

| Versión | Soporte de seguridad |
|---------|----------------------|
| latest (`deploy`) | ✅ Activa |
| `master` (entrega original) | ❌ Sin mantenimiento |

## Reportar una vulnerabilidad

Si encuentras una vulnerabilidad de seguridad en BidCo, **no abras un issue público**.

Contacta directamente a través del apartado **"Report a vulnerability"** en la pestaña [Security](../../security/advisories/new) de este repositorio.

Incluye en el reporte:
- Descripción del problema y su impacto potencial
- Pasos para reproducirlo
- Versión o commit afectado
- Si tienes una propuesta de solución, es bienvenida

Intentaremos responder en un plazo de **72 horas** y publicar un parche lo antes posible.

## Prácticas de seguridad del proyecto

- Dependencias auditadas semanalmente con **Dependabot**
- Actualizaciones `patch` y `minor` se fusionan automáticamente
- Análisis estático de código con **CodeQL**
- Tokens y credenciales gestionados como **GitHub Secrets** (nunca en el código)
- CORS configurado con lista de orígenes permitidos explícita
- JWT con expiración limitada para las sesiones de usuario
- Imágenes y archivos servidos desde **Cloudinary** (sin exposición del servidor)