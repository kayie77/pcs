/**   
* @Title: JqGridSearchDetailBean.java 
* @Package com.yunforge.common.bean 
* @Description: TODO 
* @author Oliver Wen  
* @date 2015年10月7日 上午11:21:09 
* @version V1.0   
*/
package com.yunforge.common.bean;

/** 
 * @ClassName: JqGridSearchDetailBean 
 * @Description: TODO
 * @author Oliver Wen
 * @date 2015年10月7日 上午11:21:09 
 *  
 */
public class JqGridSearchDetailBean implements java.io.Serializable{

	private static final long serialVersionUID = -8111268513484588222L;
	private String field;  	//查询字段
	private String op;		//查询操作
	private String data;	//选择的查询值
	
	public JqGridSearchDetailBean(){
		
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
