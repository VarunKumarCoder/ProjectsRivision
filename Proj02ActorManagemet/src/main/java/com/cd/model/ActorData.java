package com.cd.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActorData {

	private Integer id;
	private String aname;
	private String addrs;
	private String active_sw;
	private Double remuneration;
}
