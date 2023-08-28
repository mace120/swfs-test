package com.mace.swfs.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mace.swfs.controllers.response.ResponseWrapper;
import com.mace.swfs.gateways.jubliee.JubileeApi;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
public class PartnerController {
	private JubileeApi jubileeApi;

	// sample third party API integration
	@GetMapping("/jubilee-test")
	public ResponseEntity<ResponseWrapper> getCountriesList() {
		return ResponseEntity.ok().body(ResponseWrapper.builder().data(jubileeApi.getRoles()).build());
	}

}
