package com.marketplace.api;

import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.config.ConfigProvider;

import com.marketplace.DAO.UsuarioDAO;
import com.marketplace.exceptions.WSException;
import com.marketplace.model.entity.AccessTokenEntity;

@Interceptor
@Authentication
@ApplicationScoped
@Priority(Interceptor.Priority.PLATFORM_BEFORE)
public class AuthenticationRequestFilter{

	/**
	 * Objeto para acceder a la BD y entity manager
	 */
	@Inject
	UsuarioDAO usuarioDAO;

	/**
	 * Metodo para interceptar las peticiones
	 * @param context El contexto con la informacion de la peticion
	 * @throws WSException si hay problemas revisando el access token
	 */
	@AroundInvoke
	public Object intercept(InvocationContext context) throws WSException {
		Object result = null;
		String bearer =  (String) context.getParameters()[0];
		String bearerTokenNotSent = ConfigProvider.getConfig().getValue("mensaje.no.bearerToken", String.class);
		if (bearer == null || bearer.isEmpty()) {
			throw new WSException(bearerTokenNotSent, Response.Status.UNAUTHORIZED, null);
		} else {
			String bearerTokenBadSent = ConfigProvider.getConfig().getValue("mensaje.bearerToken.malEnviado", String.class);

			String[] bearerSentence = bearer.split(" ");
			if (bearerSentence.length < 2 || !(bearer.contains("bearer") || bearer.contains("Bearer"))) {
				throw new WSException( bearerTokenBadSent, Response.Status.UNAUTHORIZED, null);
			}
			String accessToken = bearer.split(" ")[1];
			AccessTokenEntity register = usuarioDAO.buscarAccessToken(accessToken);
			if (register != null) {
				try {
					result = context.proceed();
				} catch (WSException e) {
					throw new WSException(e.getMessage(), e.getStatus(), e);
				} catch (Exception e) {
					throw new WSException(e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR, e);
				}
			} else {
				String serviceDenied = ConfigProvider.getConfig().getValue("mensaje.accessToken.incorrecto", String.class);
				throw new WSException(serviceDenied, Response.Status.UNAUTHORIZED, null);
			}
		}
		return result;
	}
}
