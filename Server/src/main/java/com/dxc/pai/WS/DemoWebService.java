package com.dxc.pai.WS;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface DemoWebService {
	/**
	 * a demo WebService
	 * @return
	 */
	@WebMethod
	public String helloChad();
}
