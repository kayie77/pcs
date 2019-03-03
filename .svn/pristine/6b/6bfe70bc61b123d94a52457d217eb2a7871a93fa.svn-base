/**
 * <p>Title: MD5PasswordService.java</p>
 * <p>Description: <／p>
 * <p>Copyright: Copyright (c) 2015-2018</p>
 * <p>Company: Yunforge</p>
 * @author Oliver Wen
 * @date 2015年9月26日
 * @version 1.0
 */
package com.yunforge.core.security;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * Title: PasswordServiceImpl
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: Yunforge
 * </p>
 * 
 * @author Oliver Wen
 * @date 2015年9月26日
 */
@Scope("prototype")
@Service
@Transactional(readOnly = true)
public class PasswordServiceImpl implements PasswordService {

	/**
	 * <p>
	 * Title: encodePassword
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param rawPass
	 * @param salt
	 * @return
	 * @throws Exception
	 *             encodePassword
	 */
	@Override
	public String encryptPassword(String rawPass, String salt,
			String algorithmName, int hashIterations) throws Exception {
		SimpleHash hash = new SimpleHash(algorithmName, rawPass, salt,
				hashIterations);
		return hash.toHex();
	}

}
