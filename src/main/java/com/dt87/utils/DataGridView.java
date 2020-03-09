package com.dt87.utils;



/**
 * layui数据表格的格式对象
 * @author LJH
 *
 */

public class DataGridView {

	private Integer code=0;
	private String msg="";
	private Long count=0L;
	private Object data;
	public DataGridView(Object data) {
		super();
		this.data = data;
	}
	public DataGridView(Long count, Object data) {
		super();
		this.count = count;
		this.data = data;
	}
}
