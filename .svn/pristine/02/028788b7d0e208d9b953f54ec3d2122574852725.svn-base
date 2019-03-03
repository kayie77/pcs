/**
* <p>Title: Test.java</p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2015-2018</p>
* <p>Company: Yunforge</p>
* @author Oliver Wen
* @date 2015年9月26日
* @version 1.0
*/
package com.yunforge.core.security.encoder;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * <p>Title: Test</p>
 * <p>Description: </p>
 * <p>Company: Yunforge</p> 
 * @author Oliver Wen
 * @date 2015年9月26日
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String algorithmName = "md5";  
		String username = "admin";  
		String password = "123";  
		String salt1 = username;  
		//String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();  
		int hashIterations = 2;  
		  
		SimpleHash hash = new SimpleHash(algorithmName, password, salt1 + "cab2d2f3f68b22caa8c72d85d205d5ea", hashIterations);  
		//System.out.println(salt2);
		System.out.println(hash);
         
	}

}
