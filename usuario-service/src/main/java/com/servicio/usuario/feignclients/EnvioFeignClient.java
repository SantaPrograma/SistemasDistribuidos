package com.servicio.usuario.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.servicio.usuario.model.Envio;

@FeignClient(name = "envio-service", url = "http://localhost:8003")
public interface EnvioFeignClient {
	
	@PostMapping("/envio")
	public Envio save(@RequestBody Envio envio);
	
	@GetMapping("/envio/usuario/{usuarioId}")
	public List<Envio> getEnvios(@PathVariable("usuarioId") int usuario);
}
